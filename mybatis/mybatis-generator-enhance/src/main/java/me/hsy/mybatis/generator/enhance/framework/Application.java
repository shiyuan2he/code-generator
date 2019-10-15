package me.hsy.mybatis.generator.enhance.framework;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import me.hsy.mybatis.generator.enhance.framework.task.TaskFactory;
import me.hsy.mybatis.generator.enhance.framework.context.ApplicationContext;
import me.hsy.mybatis.generator.enhance.log.LogFactory;
import org.apache.logging.log4j.Logger;

import me.hsy.mybatis.generator.enhance.util.ClassUtils;
import me.hsy.mybatis.generator.enhance.util.DateUtil;
import me.hsy.mybatis.generator.enhance.util.JsonHelper;
import me.hsy.mybatis.generator.enhance.util.PropertyUtil;
import me.hsy.mybatis.generator.enhance.util.StringUtil;


/**
 * Function: 应用程序类. <br/>
 * @author heshiyuan
 */

public class Application {
    private Logger logger;
    private String applicationId;
    private String applicationName;
    private ApplicationContext context;

    public Application(String applicationId) {
        this.applicationId = applicationId;
        this.context = new ApplicationContext();
        this.logger = LogFactory.getApplicationLogger(applicationId);
    }

    public void work() {
        PropertyUtil.setLogger(logger);
        workWithoutCallback();
    }

    @SuppressWarnings("unused")
    private void tryCallback(int flag, String callbackUrl, String taskHistoryId) {
        if (StringUtil.isEmpty(callbackUrl) || StringUtil.isEmpty(taskHistoryId)) {
            logger.info("回调参数为空，不进行回调！");
            return;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        if (flag == 0) {
            //执行前回调
            map.put("id", Long.valueOf(taskHistoryId));
            map.put("execStartTime", new Date());
            map.put("runStatus", 1);  //运行中
        } else {
            //执行后回调
            map.put("id", Long.valueOf(taskHistoryId));
            map.put("execEndTime", new Date());
            map.put("runStatus", 2);  //运行完毕
        }

        int tryTimes = 0;
        while (tryTimes < 3) {
            logger.info("当前已经尝试次数为：" + tryTimes + "次");
            try {
                String utf8URL = URLDecoder.decode(callbackUrl, "UTF-8");
                logger.info("开始建立回调连接！URL[" + utf8URL + "]");
                URL url = new URL(utf8URL);
                //建立连接
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setUseCaches(false);
                urlConnection.connect();
                //传参数
                OutputStreamWriter out = new OutputStreamWriter(urlConnection.getOutputStream());

                String json = JsonHelper.getJSONString(map);
                String param = "param=" + URLEncoder.encode(json, "UTF-8");
                logger.info("发送请求，参数为：" + param);
                out.write(param);
                out.flush();
                out.close();
                logger.info("发送请求完毕，开始关闭连接！");
                //==================Read Response Input Stream=====================
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line = null;
                while ((line = reader.readLine()) != null) {
                    logger.info(" [Read Http line] " + line);
                }
                urlConnection.disconnect();
                tryTimes = 3;
            } catch (Exception e) {
                logger.info("回调异常！", e);
                tryTimes++;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ie) {
                }
            }
        }

    }


    private void workWithoutCallback() {

        if (TaskFactory.haveNoTasks()) {
            logger.error("无法启动应用程序，由于应用程序中没有任务！");
            return;
        }
        Date startTime = new Date();
        logger.info("应用程序{}执行开始时间：{}", this.applicationName, startTime);


        execute();//任务执行


        Date endTime = new Date();
        logger.info("应用程序{}执行结束时间：{}", this.applicationName, endTime);
        long executeTime = DateUtil.calExecuteSecondTime(startTime, endTime);
        logger.info("应用程序{}执行总耗时(秒)：{},总耗时(毫秒):{}", this.applicationName, executeTime, DateUtil.calExecuteMilliTime(startTime, endTime));
    }

    private void execute() {
        while (TaskFactory.haveTasks()) {
            ApplicationTask task = TaskFactory.consume();
            try {
                boolean result = task.perform(this.context);
                if (result) {
                    logger.info("跳出整个应用程序任务链！跳出之前执行的任务为：" + task.getClass().getName());
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("任务{}执行出现异常！", task.getClass().getName(), e);
            }
        }
    }

    public Application addTask(Class<? extends ApplicationTask> taskClass) {
        ApplicationTask task = ClassUtils.newInstance(taskClass.getName(), ApplicationTask.class);
        if (task == null) return this;
        //初始化任务类的日志
        task.initLogger(taskClass.getSimpleName(), this.applicationId);
        this.logger.info("添加新的应用程序任务{}", taskClass.getSimpleName());
        return addTask(task);
    }

    private Application addTask(ApplicationTask task) {
        TaskFactory.provide(task);
        return this;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

}


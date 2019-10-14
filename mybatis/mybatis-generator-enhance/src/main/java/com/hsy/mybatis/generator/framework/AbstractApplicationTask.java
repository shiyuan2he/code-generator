package com.hsy.mybatis.generator.framework;

import com.hsy.mybatis.generator.framework.context.ApplicationContext;
import com.hsy.mybatis.generator.log.LogFactory;
import com.hsy.mybatis.generator.util.PropertyUtil;
import org.apache.logging.log4j.Logger;

public abstract class AbstractApplicationTask implements ApplicationTask {

    //日志类
    protected Logger logger;

    //VAR_SKIP_NEXT: 全局变量：是否跳过以下一个节点.
    protected final static String VAR_SKIP_NEXT = "isSkipNext";

    //isSkipNext: 是否跳过下一个任务.
    private boolean isSkipNext = false;

    public AbstractApplicationTask() {
        super();
    }
    
    /**
     * 
     * initLogger:初始化日志. <br/>
     */
    public void initLogger(String applicationTaskId, String applicationId) {
        this.logger = LogFactory.getApplicationTaskLogger(applicationTaskId, applicationId);
    }

    @Override
    public boolean perform(ApplicationContext context) throws Exception {
        // 如果在执行应用程序任务逻辑之前的操作成功，那么进入执行应用程序任务
        if (doBefore(context)) {
            boolean isReturnAll = doInternal(context);
            doAfter(context);
            return isReturnAll;
        } else {
            return false;
        }
    }

    private boolean doBefore(ApplicationContext context) throws Exception {
        PropertyUtil.setLogger(this.logger);
        return true;
    }

    protected abstract boolean doInternal(ApplicationContext context) throws Exception;

    protected void doAfter(ApplicationContext context) throws Exception {
        // 设置是否跳过下一个变量
        if (isSkipNext) {
            String skipMessage = "跳过此应用程序任务的下一个任务！";
            logger.info(skipMessage);
        }
    }

    @Override
    public void skipNext() {
        this.isSkipNext = true;
    }
}

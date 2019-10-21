package me.hsy.mybatis.generator.enhance.task;

import me.hsy.mybatis.generator.enhance.framework.AbstractApplicationTask;
import me.hsy.mybatis.generator.enhance.framework.context.ApplicationContext;
import me.hsy.mybatis.generator.enhance.handler.BaseHandler;
import me.hsy.mybatis.generator.enhance.handler.impl.ServiceInfoHandle;
import me.hsy.mybatis.generator.enhance.model.ServiceInfo;

import java.util.List;

/**
 * @author heshiyuan
 */
public class ServiceTask extends AbstractApplicationTask {
    private static String Service_FTL = "template/Service.ftl";
    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context){
        try{
            logger.info("开始生成Service");
            List<ServiceInfo> dtoList = context.getServiceInfoList();
            BaseHandler<ServiceInfo> handler = null;
            for (ServiceInfo serviceInfo : dtoList) {
                handler = new ServiceInfoHandle(Service_FTL, serviceInfo);
                handler.execute();
            }
            logger.info("结束生成Service");
            return false;
        }catch (Exception e){
            logger.info("异常，至此结束", e);
            return true;
        }
    }

}

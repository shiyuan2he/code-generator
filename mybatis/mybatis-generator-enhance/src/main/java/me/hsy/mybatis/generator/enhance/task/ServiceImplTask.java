package me.hsy.mybatis.generator.enhance.task;

import me.hsy.mybatis.generator.enhance.framework.AbstractApplicationTask;
import me.hsy.mybatis.generator.enhance.framework.context.ApplicationContext;
import me.hsy.mybatis.generator.enhance.handler.BaseHandler;
import me.hsy.mybatis.generator.enhance.handler.impl.ServiceImplHandle;
import me.hsy.mybatis.generator.enhance.handler.impl.ServiceInfoHandle;
import me.hsy.mybatis.generator.enhance.model.ServiceImplInfo;
import me.hsy.mybatis.generator.enhance.model.ServiceInfo;

import java.util.List;

/**
 * @author heshiyuan
 */
public class ServiceImplTask extends AbstractApplicationTask {
    private static String Service_Impl_FTL = "template/ServiceImpl.ftl";
    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context){
        try{
            logger.info("开始生成ServiceImpl");
            List<ServiceImplInfo> dtoList = context.getServiceImplInfoList();
            BaseHandler<ServiceImplInfo> handler = null;
            for (ServiceImplInfo serviceImplInfo : dtoList) {
                handler = new ServiceImplHandle(Service_Impl_FTL, serviceImplInfo);
                handler.execute();
            }
            logger.info("结束生成ServiceImpl");
            return false;
        }catch (Exception e){
            logger.info("异常，至此结束", e);
            return true;
        }
    }

}

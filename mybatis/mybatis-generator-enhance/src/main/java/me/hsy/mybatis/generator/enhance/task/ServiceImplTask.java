package me.hsy.mybatis.generator.enhance.task;

import me.hsy.mybatis.generator.enhance.common.Constants;
import me.hsy.mybatis.generator.enhance.config.Configuration;
import me.hsy.mybatis.generator.enhance.framework.AbstractApplicationTask;
import me.hsy.mybatis.generator.enhance.framework.context.ApplicationContext;
import me.hsy.mybatis.generator.enhance.handler.BaseHandler;
import me.hsy.mybatis.generator.enhance.handler.impl.ServiceImplHandle;
import me.hsy.mybatis.generator.enhance.handler.impl.ServiceInfoHandle;
import me.hsy.mybatis.generator.enhance.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            context.getServiceInfoList().stream().forEach(serviceInfo -> {
                BaseHandler<ServiceImplInfo> handler = new ServiceImplHandle(Service_Impl_FTL,
                        generateServiceImplInfo(serviceInfo));
                handler.execute();
            });
            logger.info("结束生成ServiceImpl");
            return false;
        }catch (Exception e){
            logger.info("异常，至此结束", e);
            return true;
        }
    }
    private ServiceImplInfo generateServiceImplInfo(ServiceInfo serviceInfo) {
        String packageNameService = Configuration.getString("serviceImpl.package");
        String packageNameDao = Configuration.getString("dao.package");
        ServiceImplInfo serviceInfoImpl = new ServiceImplInfo();
        serviceInfoImpl.setPackageStr(packageNameService);
        serviceInfoImpl.setClassName(serviceInfo.getEntityInfo().getClassName() + Constants.SERVICE_IMPL_SUFFIX);
        List<String> importStrList = new ArrayList<>();
        String convertClassName = serviceInfo.getEntityInfo().getClassName() + Constants.CONVERT_UTILS_SUFFIX;
        importStrList.add(Configuration.getString("convert.package") + "." + convertClassName);
        importStrList.add(serviceInfo.getPackageStr() + "." + serviceInfo.getClassName());
        importStrList.add(packageNameDao + "." + serviceInfo.getEntityInfo().getClassName() + Constants.DAO_SUFFIX);
        importStrList.add(serviceInfo.getEntityInfo().getEntityPackage() + "." + serviceInfo.getEntityInfo().getClassName());
        importStrList.addAll(serviceInfo.getImportStrList());
        serviceInfoImpl.setImportStrList(importStrList);
        serviceInfoImpl.setServiceInfo(serviceInfo);
        serviceInfoImpl.setConvertClassName(convertClassName);
        return serviceInfoImpl;
    }
}

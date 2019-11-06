package me.hsy.mybatis.generator.enhance.task;

import me.hsy.mybatis.generator.enhance.common.Constants;
import me.hsy.mybatis.generator.enhance.config.Configuration;
import me.hsy.mybatis.generator.enhance.framework.AbstractApplicationTask;
import me.hsy.mybatis.generator.enhance.framework.context.ApplicationContext;
import me.hsy.mybatis.generator.enhance.handler.BaseHandler;
import me.hsy.mybatis.generator.enhance.handler.impl.ControllerHandler;
import me.hsy.mybatis.generator.enhance.handler.impl.ServiceImplHandle;
import me.hsy.mybatis.generator.enhance.model.ControllerInfo;
import me.hsy.mybatis.generator.enhance.model.ServiceImplInfo;
import me.hsy.mybatis.generator.enhance.model.ServiceInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author heshiyuan
 */
public class ControllerTask extends AbstractApplicationTask {
    private static String CONTROLLER_FTL = "template/Controller.ftl";
    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context){
        try{
            logger.info("开始生成Controller");
            context.getServiceInfoList().stream().forEach(serviceInfo -> {
                BaseHandler<ControllerInfo> handler = new ControllerHandler(CONTROLLER_FTL,
                        generateControllerInfo(serviceInfo));
                handler.execute();
            });
            logger.info("结束生成Controller");
            return false;
        }catch (Exception e){
            logger.info("异常，至此结束", e);
            return true;
        }
    }
    private ControllerInfo generateControllerInfo(ServiceInfo serviceInfo) {
        String packageNameService = Configuration.getString("controller.package");
        String packageNameDao = Configuration.getString("dao.package");
        ControllerInfo controllerInfo = new ControllerInfo();
        controllerInfo.setPackageStr(packageNameService);
        controllerInfo.setClassName(serviceInfo.getEntityInfo().getClassName() + Constants.CONTROLLER_SUFFIX);
        List<String> importStrList = new ArrayList<>();
        importStrList.add(serviceInfo.getPackageStr() + "." + serviceInfo.getClassName());
        importStrList.add(packageNameDao + "." + serviceInfo.getEntityInfo().getClassName() + Constants.DAO_SUFFIX);
        importStrList.add(serviceInfo.getEntityInfo().getEntityPackage() + "." + serviceInfo.getEntityInfo().getClassName());
        importStrList.addAll(serviceInfo.getImportStrList());
        controllerInfo.setImportStrList(importStrList);
        controllerInfo.setServiceInfo(serviceInfo);
        return controllerInfo;
    }
}

package me.hsy.mybatis.generator.enhance.task;

import me.hsy.mybatis.generator.enhance.common.Constants;
import me.hsy.mybatis.generator.enhance.config.Configuration;
import me.hsy.mybatis.generator.enhance.framework.AbstractApplicationTask;
import me.hsy.mybatis.generator.enhance.framework.context.ApplicationContext;
import me.hsy.mybatis.generator.enhance.handler.BaseHandler;
import me.hsy.mybatis.generator.enhance.handler.impl.ServiceInfoHandle;
import me.hsy.mybatis.generator.enhance.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author heshiyuan
 */
public class ServiceTask extends AbstractApplicationTask {
    private static String Service_FTL = "template/Service.ftl";
    private List<ServiceInfo> serviceInfoList = new ArrayList<>();
    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context){
        try{
            logger.info("开始生成Service");
            context.getEntityInfoList().stream().forEach(entity -> {
                ServiceInfo serviceInfo = generateServiceInfo(entity, context.getTableInfoMap().get(entity.getTableName()));
                BaseHandler<ServiceInfo> handler = new ServiceInfoHandle(Service_FTL, serviceInfo);
                handler.execute();
                serviceInfoList.add(serviceInfo);
            });
            logger.info("结束生成Service");
            return false;
        }catch (Exception e){
            logger.info("异常，至此结束", e);
            return true;
        }
    }
    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);
        context.setServiceInfoList(serviceInfoList);
    }
    private ServiceInfo generateServiceInfo(EntityInfo entityInfo, TableInfo tableInfo) {
        String packageNameService = Configuration.getString("service.package");
        String packageNameVo = Configuration.getString("vo.package");
        String packageNameDto = Configuration.getString("dto.package");
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setPackageStr(packageNameService);
        serviceInfo.setClassName("I" + entityInfo.getClassName() + Constants.SERVICE_SUFFIX);
        serviceInfo.setServiceDesc(entityInfo.getEntityDesc());
        serviceInfo.setVoClassName(entityInfo.getClassName() + Constants.VO_SUFFIX);
        serviceInfo.setDtoClassName(entityInfo.getClassName() +  Constants.DTO_SUFFIX);
        List<String> importStrList = new ArrayList<>();
        importStrList.add(packageNameVo + "." + serviceInfo.getVoClassName());
        importStrList.add(packageNameDto + "." + serviceInfo.getDtoClassName());
        serviceInfo.setImportStrList(importStrList);
        serviceInfo.setTableInfo(tableInfo);
        serviceInfo.setEntityInfo(entityInfo);
        return serviceInfo;
    }
}

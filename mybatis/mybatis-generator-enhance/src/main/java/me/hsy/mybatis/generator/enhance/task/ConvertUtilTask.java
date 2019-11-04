package me.hsy.mybatis.generator.enhance.task;

import me.hsy.mybatis.generator.enhance.common.Constants;
import me.hsy.mybatis.generator.enhance.config.Configuration;
import me.hsy.mybatis.generator.enhance.framework.AbstractApplicationTask;
import me.hsy.mybatis.generator.enhance.framework.context.ApplicationContext;
import me.hsy.mybatis.generator.enhance.handler.BaseHandler;
import me.hsy.mybatis.generator.enhance.handler.impl.ConvertUtilHandler;
import me.hsy.mybatis.generator.enhance.model.ConvertUtilsInfo;
import me.hsy.mybatis.generator.enhance.model.ServiceInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author heshiyuan
 */
public class ConvertUtilTask extends AbstractApplicationTask {
    private static String CONVERT_FTL = "template/ConvertUtil.ftl";
    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context){
        try{
            logger.info("开始生成convert");
            context.getServiceInfoList().stream().forEach(entity -> {
                BaseHandler<ConvertUtilsInfo> handler = new ConvertUtilHandler(CONVERT_FTL, generateConvertInfo(entity));
                handler.execute();
            });
            logger.info("结束生成convert");
            return false;
        }catch (Exception e){
            logger.info("异常，至此结束", e);
            return true;
        }
    }

    private ConvertUtilsInfo generateConvertInfo(ServiceInfo entity) {
        String packageNameVo = Configuration.getString("vo.package");
        String packageNameDto = Configuration.getString("dto.package");
        String packageNameEntity = Configuration.getString("entity.package");
        ConvertUtilsInfo convertUtilsInfo = new ConvertUtilsInfo();
        convertUtilsInfo.setClassName(entity.getEntityInfo().getClassName() + Constants.CONVERT_UTILS_SUFFIX);
        convertUtilsInfo.setPackageStr(Configuration.getString("convert.package"));
        convertUtilsInfo.setServiceInfo(entity);
        List<String> importList = new ArrayList();
        importList.add(packageNameVo + "." + entity.getVoClassName());
        importList.add(packageNameDto + "." + entity.getDtoClassName());
        importList.add(packageNameEntity + "." + entity.getEntityInfo().getClassName());
        convertUtilsInfo.setImportStrList(importList);
        return convertUtilsInfo;
    }
}

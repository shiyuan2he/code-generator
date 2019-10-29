package me.hsy.mybatis.generator.enhance.task;

import me.hsy.mybatis.generator.enhance.common.Constants;
import me.hsy.mybatis.generator.enhance.config.Configuration;
import me.hsy.mybatis.generator.enhance.framework.AbstractApplicationTask;
import me.hsy.mybatis.generator.enhance.framework.context.ApplicationContext;
import me.hsy.mybatis.generator.enhance.handler.BaseHandler;
import me.hsy.mybatis.generator.enhance.handler.impl.DtoHandler;
import me.hsy.mybatis.generator.enhance.handler.impl.VoHandler;
import me.hsy.mybatis.generator.enhance.model.DtoInfo;
import me.hsy.mybatis.generator.enhance.model.VoInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author heshiyuan
 */
public class DtoTask extends AbstractApplicationTask {
    private static String DTO_FTL = "template/Dto.ftl";
    private List<DtoInfo> dtoInfoList = new ArrayList<>();
    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context){
        try{
            logger.info("开始生成Dto");
            context.getEntityInfoList().stream().forEach(entity -> {
                DtoInfo dtoInfo = new DtoInfo();
                dtoInfo.setClassName(entity.getEntityName() + Constants.DTO_SUFFIX);
                dtoInfo.setEntityInfo(entity);
                dtoInfo.setPackageStr(Configuration.getString("dto.package"));
                BaseHandler<DtoInfo> handler = new DtoHandler(DTO_FTL, dtoInfo);
                handler.execute();
                dtoInfoList.add(dtoInfo);
            });
            logger.info("结束生成Dto");
            return false;
        }catch (Exception e){
            logger.info("异常，至此结束", e);
            return true;
        }
    }
    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);
        context.setDtoInfoList(dtoInfoList);
    }
}

package me.hsy.mybatis.generator.enhance.task;

import me.hsy.mybatis.generator.enhance.framework.AbstractApplicationTask;
import me.hsy.mybatis.generator.enhance.framework.context.ApplicationContext;
import me.hsy.mybatis.generator.enhance.handler.BaseHandler;
import me.hsy.mybatis.generator.enhance.handler.impl.DtoHandler;
import me.hsy.mybatis.generator.enhance.handler.impl.VoHandler;
import me.hsy.mybatis.generator.enhance.model.DtoInfo;
import me.hsy.mybatis.generator.enhance.model.VoInfo;

import java.util.List;

/**
 * @author heshiyuan
 */
public class DtoTask extends AbstractApplicationTask {
    private static String DTO_FTL = "template/Dto.ftl";
    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context){
        try{
            logger.info("开始生成Dto");
            List<DtoInfo> dtoList = context.getDtoInfoList();
            BaseHandler<DtoInfo> handler = null;
            for (DtoInfo dtoInfo : dtoList) {
                handler = new DtoHandler(DTO_FTL, dtoInfo);
                handler.execute();
            }
            logger.info("结束生成Dto");
            return false;
        }catch (Exception e){
            logger.info("异常，至此结束", e);
            return true;
        }
    }

}

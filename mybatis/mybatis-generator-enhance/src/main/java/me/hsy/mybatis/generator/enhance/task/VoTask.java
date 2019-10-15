package me.hsy.mybatis.generator.enhance.task;

import java.util.List;

import me.hsy.mybatis.generator.enhance.framework.AbstractApplicationTask;
import me.hsy.mybatis.generator.enhance.framework.context.ApplicationContext;
import me.hsy.mybatis.generator.enhance.handler.BaseHandler;
import me.hsy.mybatis.generator.enhance.handler.impl.VoHandler;
import me.hsy.mybatis.generator.enhance.model.VoInfo;

public class VoTask extends AbstractApplicationTask {

    private static String VO_FTL = "template/Vo.ftl";
    
    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成vo");
        List<VoInfo> voList = context.getVoList();
        
        BaseHandler<VoInfo> handler = null;
        for (VoInfo voInfo : voList) {
            handler = new VoHandler(VO_FTL, voInfo);
            handler.execute();
        }
        logger.info("结束生成vo");
        return false;
    }

}

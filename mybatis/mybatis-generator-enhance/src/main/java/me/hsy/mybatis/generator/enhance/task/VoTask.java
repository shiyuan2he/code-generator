package me.hsy.mybatis.generator.enhance.task;

import java.util.ArrayList;
import java.util.List;

import me.hsy.mybatis.generator.enhance.common.Constants;
import me.hsy.mybatis.generator.enhance.config.Configuration;
import me.hsy.mybatis.generator.enhance.framework.AbstractApplicationTask;
import me.hsy.mybatis.generator.enhance.framework.context.ApplicationContext;
import me.hsy.mybatis.generator.enhance.handler.BaseHandler;
import me.hsy.mybatis.generator.enhance.handler.impl.DaoHandler;
import me.hsy.mybatis.generator.enhance.handler.impl.VoHandler;
import me.hsy.mybatis.generator.enhance.model.DaoInfo;
import me.hsy.mybatis.generator.enhance.model.VoInfo;

/**
 * @author heshiyuan
 */
public class VoTask extends AbstractApplicationTask {
    private static String VO_FTL = "template/Vo.ftl";
    private List<VoInfo> voInfoList = new ArrayList<>();
    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context){
        try{
            logger.info("开始生成vo");
            context.getEntityInfoList().stream().forEach(entity -> {
                VoInfo voInfo = new VoInfo();
                voInfo.setClassName(entity.getEntityName() + Constants.VO_SUFFIX);
                voInfo.setEntityInfo(entity);
                voInfo.setPackageStr(Configuration.getString("vo.package"));
                BaseHandler<VoInfo> handler = new VoHandler(VO_FTL, voInfo);
                handler.execute();
                voInfoList.add(voInfo);
            });
            logger.info("结束生成vo");
            return false;
        }catch (Exception e){
            logger.info("异常，至此结束", e);
            return true;
        }
    }
    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);
        context.setVoList(voInfoList);
    }
}

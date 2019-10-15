package me.hsy.mybatis.generator.enhance.task;
import java.util.ArrayList;
import java.util.List;

import me.hsy.mybatis.generator.enhance.common.Constants;
import me.hsy.mybatis.generator.enhance.framework.AbstractApplicationTask;
import me.hsy.mybatis.generator.enhance.model.DaoInfo;
import me.hsy.mybatis.generator.enhance.config.Configuration;
import me.hsy.mybatis.generator.enhance.framework.context.ApplicationContext;
import me.hsy.mybatis.generator.enhance.handler.BaseHandler;
import me.hsy.mybatis.generator.enhance.handler.impl.EntityHandler;
import me.hsy.mybatis.generator.enhance.model.EntityInfo;
import me.hsy.mybatis.generator.enhance.model.VoInfo;

/**
 * @author heshiyuan
 */
public class EntityTask extends AbstractApplicationTask {
    private static String ENTITY_FTL = "template/Entity.ftl";
    
    private List<EntityInfo> entityInfoList;

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成实体");
        //获取实体信息
        entityInfoList = context.getEntityInfoList();
        BaseHandler<EntityInfo> handler = null;
        for (EntityInfo entityInfo : entityInfoList) {
            handler = new EntityHandler(ENTITY_FTL, entityInfo);
            handler.execute();
        }
        logger.info("生成实体类完成");
        return false;
    }
    
    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);
        
        List<DaoInfo> daoList = new ArrayList<>();
        List<VoInfo> voList = new ArrayList<>();
        //组装Dao信息、组装Vo信息
        DaoInfo daoInfo = null;
        VoInfo voInfo = null;
        for (EntityInfo entityInfo : entityInfoList) {
            daoInfo = new DaoInfo();
            daoInfo.setClassName(entityInfo.getEntityName() + Constants.DAO_SUFFIX);
            daoInfo.setEntityInfo(entityInfo);
            daoInfo.setImportStr("import " + entityInfo.getEntityPackage() + "." + entityInfo.getClassName() + ";");
            daoInfo.setPackageStr(Configuration.getString("dao.package"));
            daoList.add(daoInfo);
            
            voInfo = new VoInfo();
            voInfo.setPackageStr(Configuration.getString("vo.package"));
            voInfo.setClassName(entityInfo.getEntityName() + Constants.VO_SUFFIX);
            voInfo.setEntityInfo(entityInfo);
            voList.add(voInfo);
        }
        context.setDaoList(daoList);
        context.setVoList(voList);
    }
}

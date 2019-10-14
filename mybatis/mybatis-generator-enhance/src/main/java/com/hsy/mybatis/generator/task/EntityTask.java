package com.hsy.mybatis.generator.task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.hsy.mybatis.generator.common.Constants;
import com.hsy.mybatis.generator.framework.AbstractApplicationTask;
import com.hsy.mybatis.generator.util.PropertyUtil;
import com.hsy.mybatis.generator.model.DaoInfo;
import com.hsy.mybatis.generator.config.Configuration;
import com.hsy.mybatis.generator.framework.context.ApplicationContext;
import com.hsy.mybatis.generator.handler.BaseHandler;
import com.hsy.mybatis.generator.handler.impl.EntityHandler;
import com.hsy.mybatis.generator.model.EntityInfo;
import com.hsy.mybatis.generator.model.VoInfo;

public class EntityTask extends AbstractApplicationTask {
    private static String ENTITY_FTL = "template/Entity.ftl";
    
    private List<EntityInfo> entityInfos;

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成实体");
        
        //获取实体信息
        entityInfos = (List<EntityInfo>) context.getAttribute("entityInfos");
        
        BaseHandler<EntityInfo> handler = null;
        for (EntityInfo entityInfo : entityInfos) {
            handler = new EntityHandler(ENTITY_FTL, entityInfo);
            handler.execute();
        }
        logger.info("生成实体类完成");
        return false;
    }
    
    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);
        
        List<DaoInfo> daoList = new ArrayList<DaoInfo>();
        List<VoInfo> voList = new ArrayList<VoInfo>();
        //组装Dao信息、组装Vo信息
        DaoInfo daoInfo = null;
        VoInfo voInfo = null;
        for (EntityInfo entityInfo : entityInfos) {
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
        
        context.setAttribute("daoList", daoList);
        context.setAttribute("voList", voList);
    }
    
    public static void main(String[] args) {
        File file = new File("/D:\\devsoftware\\workspace\\winit-java-generator\\target\\classes\\template\\Entity.ftl");
        System.out.println(EntityTask.class.getClassLoader().getResource("").getPath());
        System.out.println(file.exists());
        
        PropertyUtil.setProperty("name", "qyk1");
        PropertyUtil.setProperty("NAME", "qyk22");
    }

}

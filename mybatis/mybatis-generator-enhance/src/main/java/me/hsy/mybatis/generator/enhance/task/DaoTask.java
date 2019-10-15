package me.hsy.mybatis.generator.enhance.task;

import java.util.ArrayList;
import java.util.List;

import me.hsy.mybatis.generator.enhance.common.Constants;
import me.hsy.mybatis.generator.enhance.framework.AbstractApplicationTask;
import me.hsy.mybatis.generator.enhance.model.DaoInfo;
import me.hsy.mybatis.generator.enhance.framework.context.ApplicationContext;
import me.hsy.mybatis.generator.enhance.handler.BaseHandler;
import me.hsy.mybatis.generator.enhance.handler.impl.DaoHandler;
import me.hsy.mybatis.generator.enhance.model.MapperInfo;

/**
 * @author heshiyuan
 */
public class DaoTask extends AbstractApplicationTask {
    private static String DAO_FTL = "template/Dao.ftl";
    
    private List<DaoInfo> daoInfoLists;
    
    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成dao");
        daoInfoLists = context.getDaoList();
        BaseHandler<DaoInfo> handler = null;
        for (DaoInfo daoInfo : daoInfoLists) {
            handler = new DaoHandler(DAO_FTL, daoInfo);
            handler.execute();
        }
        logger.info("生成dao完成");
        return false;
    }
    
    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);
        List<MapperInfo> mapperInfoList = new ArrayList<MapperInfo>();
        MapperInfo mapperInfo = null;
        for (DaoInfo daoInfo : daoInfoLists) {
            mapperInfo = new MapperInfo();
            mapperInfo.setDaoInfo(daoInfo);
            mapperInfo.setEntityInfo(daoInfo.getEntityInfo());
            mapperInfo.setFileName(daoInfo.getEntityInfo().getEntityName() + Constants.MAPPER_XML_SUFFIX);
            mapperInfo.setNamespace(daoInfo.getPackageStr() + "." + daoInfo.getClassName());
            mapperInfoList.add(mapperInfo);
        }
        context.setMapperInfoList(mapperInfoList);
    }
}

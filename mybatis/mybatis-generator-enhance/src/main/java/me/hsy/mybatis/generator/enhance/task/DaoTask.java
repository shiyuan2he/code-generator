package me.hsy.mybatis.generator.enhance.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import me.hsy.mybatis.generator.enhance.common.Constants;
import me.hsy.mybatis.generator.enhance.config.Configuration;
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
    
    private List<DaoInfo> daoInfoLists = new ArrayList<>();
    
    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成dao");
        context.getEntityInfoList().stream().forEach(entity -> {
            DaoInfo daoInfo = new DaoInfo();
            daoInfo.setClassName(entity.getEntityName() + Constants.DAO_SUFFIX);
            daoInfo.setEntityInfo(entity);
            daoInfo.setTableInfo(context.getTableInfoMap().get(entity.getTableName()));
            daoInfo.setImportStr("import " + entity.getEntityPackage() + "." + entity.getClassName() + ";");
            daoInfo.setPackageStr(Configuration.getString("dao.package"));
            BaseHandler<DaoInfo> handler = new DaoHandler(DAO_FTL, daoInfo);
            handler.execute();
            daoInfoLists.add(daoInfo);
        });
        logger.info("生成dao完成");
        return false;
    }
    
    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);
        context.setDaoList(daoInfoLists);
    }
}

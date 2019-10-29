package me.hsy.mybatis.generator.enhance.task;

import java.util.ArrayList;
import java.util.List;

import me.hsy.mybatis.generator.enhance.common.Constants;
import me.hsy.mybatis.generator.enhance.framework.AbstractApplicationTask;
import me.hsy.mybatis.generator.enhance.framework.context.ApplicationContext;
import me.hsy.mybatis.generator.enhance.handler.BaseHandler;
import me.hsy.mybatis.generator.enhance.handler.impl.MapperHandler;
import me.hsy.mybatis.generator.enhance.model.DaoInfo;
import me.hsy.mybatis.generator.enhance.model.MapperInfo;

/**
 * @author heshiyuan
 */
public class MapperTask extends AbstractApplicationTask {
    private static String MAPPER_FTL = "template/Mapper.ftl";
    List<MapperInfo> mapperInfoList = new ArrayList<>();
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成Mapper");
        context.getDaoList().stream().forEach(dao -> {
            MapperInfo mapperInfo = new MapperInfo();
            mapperInfo.setDaoInfo(dao);
            mapperInfo.setEntityInfo(dao.getEntityInfo());
            mapperInfo.setFileName(dao.getEntityInfo().getEntityName() + Constants.MAPPER_XML_SUFFIX);
            mapperInfo.setNamespace(dao.getPackageStr() + "." + dao.getClassName());
            new MapperHandler(MAPPER_FTL, mapperInfo).execute();
            mapperInfoList.add(mapperInfo);
        });
        logger.info("生成Mapper完成");
        return false;
    }
    
    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);
        context.setMapperInfoList(mapperInfoList);
    }
}

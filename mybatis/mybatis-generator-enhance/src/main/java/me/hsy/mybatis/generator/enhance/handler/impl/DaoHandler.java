package me.hsy.mybatis.generator.enhance.handler.impl;

import java.io.File;

import me.hsy.mybatis.generator.enhance.handler.BaseHandler;
import me.hsy.mybatis.generator.enhance.model.DaoInfo;
import me.hsy.mybatis.generator.enhance.config.Configuration;
import me.hsy.mybatis.generator.enhance.util.StringHelper;

/**
 * @author heshiyuan
 */
public class DaoHandler extends BaseHandler<DaoInfo> {
    public DaoHandler(String ftlName, DaoInfo info) {
        this.ftlName = ftlName;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir")
                + File.separator + mavenPath
                + File.separator + StringHelper.join(File.separator, Configuration.getString("dao.package").split("\\."))
                + File.separator + info.getClassName() + ".java";
    }
    
    @Override
    public void combineParams(DaoInfo info) {
        this.param.put("packageStr", info.getPackageStr());
        this.param.put("importStr", info.getImportStr());
        this.param.put("entityDesc", info.getEntityInfo().getEntityDesc());
        this.param.put("className", info.getClassName());
        this.param.put("entityClassName", info.getEntityInfo().getClassName());
        this.param.put("entityName", info.getEntityInfo().getEntityName());
    }
}

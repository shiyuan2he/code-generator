package com.hsy.mybatis.generator.handler.impl;

import java.io.File;

import com.hsy.mybatis.generator.handler.BaseHandler;
import com.hsy.mybatis.generator.model.DaoInfo;
import com.hsy.mybatis.generator.config.Configuration;


public class DaoHandler extends BaseHandler<DaoInfo> {
    public DaoHandler(String ftlName, DaoInfo info) {
        this.ftlName = ftlName;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir") 
                + File.separator + Configuration.getString("dao.path")
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

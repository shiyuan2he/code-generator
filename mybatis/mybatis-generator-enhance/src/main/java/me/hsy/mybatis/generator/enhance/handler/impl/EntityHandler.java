package me.hsy.mybatis.generator.enhance.handler.impl;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import me.hsy.mybatis.generator.enhance.handler.BaseHandler;
import me.hsy.mybatis.generator.enhance.config.Configuration;
import me.hsy.mybatis.generator.enhance.model.EntityInfo;
import me.hsy.mybatis.generator.enhance.util.StringHelper;

/**
 * @author heshiyuan
 */
public class EntityHandler extends BaseHandler<EntityInfo> {
    
    public EntityHandler(String ftlName, EntityInfo info) {
        this.ftlName = ftlName;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir")
                + File.separator + mavenPath
                + File.separator + StringHelper.join(File.separator, Configuration.getString("entity.package").split("\\."))
                + File.separator + info.getClassName() + ".java";
    }
    
    @Override
    public void combineParams(EntityInfo entityInfo) {
        this.param.put("packageStr", entityInfo.getEntityPackage());
        StringBuilder sb = new StringBuilder();
        for (String str : entityInfo.getImports()) {
            sb.append("import ").append(str).append(";\r\n");
        }
        this.param.put("importStr", sb.toString());
        this.param.put("tableComment", entityInfo.getEntityDesc());
        this.param.put("className", entityInfo.getClassName());

        //生成属性，getter,setter方法
        sb = new StringBuilder();
        Map<String, String> propRemarks = entityInfo.getPropRemarks();
        for (Entry<String, String> entry : entityInfo.getPropTypes().entrySet()) {
            String propName = entry.getKey();
            String propType = entry.getValue();
            //注释、类型、名称
            sb.append("\r\n\t/**\r\n\t * ").append(propRemarks.get(propName)).append("\r\n\t */\r\n")
            .append("\tprivate ").append(propType).append(" ").append(propName)
            .append(";\r\n");
        }
        this.param.put("propertiesStr", sb.toString());
    }
}

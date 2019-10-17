package me.hsy.mybatis.generator.enhance.handler.impl;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import me.hsy.mybatis.generator.enhance.handler.BaseHandler;
import me.hsy.mybatis.generator.enhance.config.Configuration;
import me.hsy.mybatis.generator.enhance.model.EntityInfo;
import me.hsy.mybatis.generator.enhance.model.VoInfo;
import me.hsy.mybatis.generator.enhance.util.StringHelper;

/**
 * @author heshiyuan
 */
public class VoHandler extends BaseHandler<VoInfo> {
    public VoHandler(String ftlName, VoInfo info){
        this.ftlName = ftlName;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir")
                + File.separator + mavenPath
                + File.separator + StringHelper.join(File.separator, Configuration.getString("vo.package").split("\\."))
                + File.separator + info.getClassName() + ".java";
    }

    @Override
    public void combineParams(VoInfo voInfo) {
        this.param.put("packageStr", voInfo.getPackageStr());
        this.param.put("entityDesc", voInfo.getEntityInfo().getEntityDesc());
        this.param.put("className", voInfo.getClassName());

        StringBuilder sb = new StringBuilder();
        //生成属性，getter,setter方法
        Map<String, String> propRemarks = voInfo.getEntityInfo().getPropRemarks();
        for (Entry<String, String> entry : voInfo.getEntityInfo().getPropTypes().entrySet()) {
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

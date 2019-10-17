package me.hsy.mybatis.generator.enhance.handler.impl;

import me.hsy.mybatis.generator.enhance.config.Configuration;
import me.hsy.mybatis.generator.enhance.handler.BaseHandler;
import me.hsy.mybatis.generator.enhance.model.DtoInfo;
import me.hsy.mybatis.generator.enhance.model.VoInfo;
import me.hsy.mybatis.generator.enhance.util.StringHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author heshiyuan
 */
public class DtoHandler extends BaseHandler<DtoInfo> {
    public DtoHandler(String ftlName, DtoInfo info){
        this.ftlName = ftlName;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir")
                + File.separator + mavenPath
                + File.separator + StringHelper.join(File.separator, Configuration.getString("dto.package").split("\\."))
                + File.separator + info.getClassName() + ".java";
    }

    @Override
    public void combineParams(DtoInfo dtoInfo) {
        this.param.put("packageStr", dtoInfo.getPackageStr());
        this.param.put("entityDesc", dtoInfo.getEntityInfo().getEntityDesc());
        this.param.put("className", dtoInfo.getClassName());
        this.param.put("needToJsonString", false);
        StringBuilder sb = new StringBuilder();
        for (String str : dtoInfo.getEntityInfo().getImports()) {
            sb.append("import ").append(str).append(";\r\n");
        }
        this.param.put("importStr", sb.toString());
        List<Map<String, Object>> columnList = new ArrayList<>();
        for (Entry<String, String> entry : dtoInfo.getEntityInfo().getPropTypes().entrySet()) {
            Map<String,Object> column = new HashMap(30);
            String propName = entry.getKey();
            String propType = entry.getValue();
            if("Long".equals(propType)){
                this.param.put("needToJsonString", true);
            }
            Map<String, String> comment = dtoInfo.getEntityInfo().getPropRemarks();
            column.put("columnComment", null==comment?"":comment.get(propName));
            column.put("propType", propType);
            column.put("propName", propName);
            columnList.add(column);
        }
        this.param.put("columnList", columnList);
    }
}

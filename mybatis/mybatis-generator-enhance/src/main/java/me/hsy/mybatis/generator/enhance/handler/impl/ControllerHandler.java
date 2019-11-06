package me.hsy.mybatis.generator.enhance.handler.impl;

import me.hsy.mybatis.generator.enhance.config.Configuration;
import me.hsy.mybatis.generator.enhance.handler.BaseHandler;
import me.hsy.mybatis.generator.enhance.model.ControllerInfo;
import me.hsy.mybatis.generator.enhance.model.ServiceImplInfo;
import me.hsy.mybatis.generator.enhance.util.StringHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

/**
 * @author heshiyuan
 */
public class ControllerHandler extends BaseHandler<ControllerInfo> {
    public ControllerHandler(String ftlName, ControllerInfo info){
        this.ftlName = ftlName;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir")
                + File.separator + mavenPath
                + File.separator + StringHelper.join(File.separator, Configuration.getString("controller.package").split("\\."))
                + File.separator + info.getClassName() + ".java";
    }

    @Override
    public void combineParams(ControllerInfo controllerInfo) {
        this.param.put("packageStr", controllerInfo.getPackageStr());
        this.param.put("className", controllerInfo.getClassName());
        String humpEntityName = StringHelper.toLowerCaseFirstOne(controllerInfo.getServiceInfo().getEntityInfo().getClassName());
        this.param.put("humpEntityName", humpEntityName);
        this.param.put("entityName", controllerInfo.getServiceInfo().getEntityInfo().getClassName());
        StringBuilder sb = new StringBuilder();
        for (String str : Optional.ofNullable(controllerInfo.getImportStrList()).orElse(new ArrayList<>())) {
            if(str.contains("import")){
                sb.append(str).append(";\r\n");
            }else{
                sb.append("import ").append(str).append(";\r\n");
            }
        }
        this.param.put("importStr", sb.toString());
        this.param.put("pkPropName", controllerInfo.getServiceInfo().getTableInfo().getPkInfo().getJavaColumnField());
        this.param.put("pkColumnType", controllerInfo.getServiceInfo().getTableInfo().getPkInfo().getJavaColumnType());
        this.param.put("pkComment", controllerInfo.getServiceInfo().getTableInfo().getPkInfo().getColumnComment());
        this.param.put("pkPropNameList", controllerInfo.getServiceInfo().getTableInfo().getPkInfo().getJavaColumnField() + "List");
        this.param.put("voClassName", controllerInfo.getServiceInfo().getVoClassName());
        String voClassNameToHump = StringHelper.toLowerCaseFirstOne(controllerInfo.getServiceInfo().getVoClassName());
        this.param.put("voClassNameToHump", voClassNameToHump);
        this.param.put("voClassNameToHumpList", voClassNameToHump + "List");
        this.param.put("dtoName", controllerInfo.getServiceInfo().getDtoClassName());
    }
}

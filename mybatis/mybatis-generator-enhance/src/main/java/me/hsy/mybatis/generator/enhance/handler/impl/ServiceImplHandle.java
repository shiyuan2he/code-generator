package me.hsy.mybatis.generator.enhance.handler.impl;

import me.hsy.mybatis.generator.enhance.config.Configuration;
import me.hsy.mybatis.generator.enhance.handler.BaseHandler;
import me.hsy.mybatis.generator.enhance.model.ServiceImplInfo;
import me.hsy.mybatis.generator.enhance.model.ServiceInfo;
import me.hsy.mybatis.generator.enhance.util.StringHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

/**
 * @author heshiyuan
 */
public class ServiceImplHandle extends BaseHandler<ServiceImplInfo> {
    public ServiceImplHandle(String ftlName, ServiceImplInfo info){
        this.ftlName = ftlName;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir")
                + File.separator + mavenPath
                + File.separator + StringHelper.join(File.separator, Configuration.getString("serviceImpl.package").split("\\."))
                + File.separator + info.getClassName() + ".java";
    }

    @Override
    public void combineParams(ServiceImplInfo serviceImplInfo) {
        this.param.put("packageStr", serviceImplInfo.getPackageStr());
        this.param.put("className", serviceImplInfo.getClassName());
        this.param.put("serviceClassName", serviceImplInfo.getServiceInfo().getClassName());
        String tableNameToHump = StringHelper.lineToHump(serviceImplInfo.getServiceInfo().getTableInfo().getName());
        this.param.put("tableClassName", StringHelper.toUpperCaseFirstOne(tableNameToHump));
        this.param.put("tableNameToHump", StringHelper.toLowerCaseFirstOne(tableNameToHump));
        StringBuilder sb = new StringBuilder();
        for (String str : Optional.ofNullable(serviceImplInfo.getServiceInfo().getImportStrList()).orElse(new ArrayList<>())) {
            sb.append("import ").append(str).append(";\r\n");
        }
        this.param.put("importStr", sb.toString());
        this.param.put("dtoName", serviceImplInfo.getServiceInfo().getDtoClassName());
        this.param.put("pkPropName", serviceImplInfo.getServiceInfo().getTableInfo().getPkInfo().getJavaColumnField());
        this.param.put("pkColumnType", serviceImplInfo.getServiceInfo().getTableInfo().getPkInfo().getJavaColumnType());
        this.param.put("pkComment", serviceImplInfo.getServiceInfo().getTableInfo().getPkInfo().getColumnComment());
        this.param.put("pkPropNameList", serviceImplInfo.getServiceInfo().getTableInfo().getPkInfo().getJavaColumnField() + "List");
        this.param.put("voClassName", serviceImplInfo.getServiceInfo().getVoClassName());
        String voClassNameToHump = StringHelper.toLowerCaseFirstOne(serviceImplInfo.getServiceInfo().getVoClassName());
        this.param.put("voClassNameToHump", voClassNameToHump);
        this.param.put("voClassNameToHumpList", voClassNameToHump + "List");
    }
}

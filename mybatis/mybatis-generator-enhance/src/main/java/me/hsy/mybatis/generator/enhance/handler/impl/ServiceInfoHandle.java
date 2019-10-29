package me.hsy.mybatis.generator.enhance.handler.impl;

import me.hsy.mybatis.generator.enhance.config.Configuration;
import me.hsy.mybatis.generator.enhance.handler.BaseHandler;
import me.hsy.mybatis.generator.enhance.model.DtoInfo;
import me.hsy.mybatis.generator.enhance.model.ServiceInfo;
import me.hsy.mybatis.generator.enhance.util.StringHelper;

import java.io.File;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author heshiyuan
 */
public class ServiceInfoHandle extends BaseHandler<ServiceInfo> {
    public ServiceInfoHandle(String ftlName, ServiceInfo info){
        this.ftlName = ftlName;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir")
                + File.separator + mavenPath
                + File.separator + StringHelper.join(File.separator, Configuration.getString("service.package").split("\\."))
                + File.separator + info.getClassName() + ".java";
    }

    @Override
    public void combineParams(ServiceInfo serviceInfo) {
        this.param.put("packageStr", serviceInfo.getPackageStr());
        this.param.put("className", serviceInfo.getClassName());
        StringBuilder sb = new StringBuilder();
        for (String str : Optional.ofNullable(serviceInfo.getImportStrList()).orElse(new ArrayList<>())) {
            sb.append("import ").append(str).append(";\r\n");
        }
        this.param.put("importStr", sb.toString());
        this.param.put("dtoName", serviceInfo.getDtoClassName());
        this.param.put("voClassName", serviceInfo.getVoClassName());
        String voClassNameToHump = StringHelper.toLowerCaseFirstOne(serviceInfo.getVoClassName());
        this.param.put("voClassNameToHump", voClassNameToHump);
        this.param.put("entityClassName", serviceInfo.getEntityInfo().getClassName());
        String entityClassNameToHump = StringHelper.toLowerCaseFirstOne(serviceInfo.getEntityInfo().getClassName());
        this.param.put("entityClassNameToHump", entityClassNameToHump);
        this.param.put("dtoClassName", serviceInfo.getDtoClassName());
    }
}

package me.hsy.mybatis.generator.enhance.handler.impl;

import me.hsy.mybatis.generator.enhance.config.Configuration;
import me.hsy.mybatis.generator.enhance.handler.BaseHandler;
import me.hsy.mybatis.generator.enhance.model.ConvertUtilsInfo;
import me.hsy.mybatis.generator.enhance.model.VoInfo;
import me.hsy.mybatis.generator.enhance.util.StringHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

/**
 * @author heshiyuan
 */
public class ConvertUtilsHandler extends BaseHandler<ConvertUtilsInfo> {
    public ConvertUtilsHandler(String ftlName, ConvertUtilsInfo info){
        this.ftlName = ftlName;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir")
                + File.separator + mavenPath
                + File.separator + StringHelper.join(File.separator, Configuration.getString("convert.package").split("\\."))
                + File.separator + info.getClassName() + ".java";
    }

    @Override
    public void combineParams(ConvertUtilsInfo convertUtilsInfo) {
        this.param.put("packageStr", convertUtilsInfo.getPackageStr());
        this.param.put("className", convertUtilsInfo.getClassName());
        StringBuilder sb = new StringBuilder();
        for (String str : Optional.ofNullable(convertUtilsInfo.getServiceInfo().getImportStrList()).orElse(new ArrayList<>())) {
            sb.append("import ").append(str).append(";\r\n");
        }
    }
}

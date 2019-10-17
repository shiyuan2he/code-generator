package me.hsy.mybatis.generator.enhance.handler;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import me.hsy.mybatis.generator.enhance.util.DateUtil;
import me.hsy.mybatis.generator.enhance.util.FileHelper;
import me.hsy.mybatis.generator.enhance.util.FreeMarkerUtil;

/**
 * 基类处理器
 * @author heshiyuan
 */
public abstract class BaseHandler<T> {
    protected String mavenPath = "src" +
                                 File.separator + "main"+
                                 File.separator + "java";
    protected String mavenResourcePath = "src" +
                                File.separator + "main"+
                                File.separator + "resources";
    protected String ftlName;
    protected String savePath;
    protected Map<String, Object> param = new HashMap<>();
    protected T info;
    
    private String generateFinalStr() {
        String temp = FileHelper.readFileToString(this.getClass().getClassLoader().getResource("").getPath() + ftlName);
        return FreeMarkerUtil.getProcessValue(param, temp);
    }
    
    /**
     * 
     * 保存到文件
     */
    private void saveToFile(String str) {
        FileHelper.writeToFile(savePath, str);
    }
    
    /**
     * 组装参数
     * @param info
     */
    public abstract void combineParams(T info);
    
    /**
     * 
     * 设置一些公共的参数.
     */
    private void beforeGenerate() {
        String time = DateUtil.formatDataToStr(new Date(), null);
        param.put("author", System.getProperty("user.name"));
        param.put("time", time);
    }
    
    /**
     * 生成文件
     */
    public void execute() {
        combineParams(info);
        beforeGenerate();
        String str = generateFinalStr();
        saveToFile(str);
    }
}

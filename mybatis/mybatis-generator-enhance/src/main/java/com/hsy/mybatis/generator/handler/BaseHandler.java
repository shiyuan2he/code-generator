package com.hsy.mybatis.generator.handler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.hsy.mybatis.generator.util.DateUtil;
import com.hsy.mybatis.generator.util.FileHelper;
import com.hsy.mybatis.generator.util.FreeMarkerUtil;

public abstract class BaseHandler<T> {
    protected String ftlName;
    protected String savePath;
    protected Map<String, String> param = new HashMap<String, String>();
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
     */
    public abstract void combineParams(T info);
    
    /**
     * 
     * 设置一些公共的参数.
     */
    private void beforeGenerate() {
        String time = DateUtil.formatDataToStr(new Date(), "yyyy年MM月dd日");
        param.put("author", System.getProperty("user.name"));
        param.put("time", time);
    }
    
    /**
     * 生成文件
     */
    public void execute() {
        String str = null;
        combineParams(info);
        beforeGenerate();
        str = generateFinalStr();
        saveToFile(str);
    }
    
}

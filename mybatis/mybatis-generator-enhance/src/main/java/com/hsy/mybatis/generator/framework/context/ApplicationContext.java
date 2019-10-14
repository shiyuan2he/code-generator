package com.hsy.mybatis.generator.framework.context;

import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 应用程序上下文环境
 * @author heshiyuan
 */
public abstract class ApplicationContext {
    @Setter
    protected Map<String, Object> ctx = new HashMap<>();
    
    public abstract void setAttribute(String key, Object obj);
    
    public abstract Object getAttribute(String key);
}

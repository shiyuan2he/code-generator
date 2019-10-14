package com.hsy.mybatis.generator.framework;

import com.hsy.mybatis.generator.framework.context.ApplicationContext;

public interface ApplicationTask extends Skipable {

    boolean perform(ApplicationContext context) throws Exception;

    void initLogger(String applicationTaskId, String applicationId);
}

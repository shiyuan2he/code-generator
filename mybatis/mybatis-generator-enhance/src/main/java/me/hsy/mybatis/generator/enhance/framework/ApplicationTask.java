package me.hsy.mybatis.generator.enhance.framework;

import me.hsy.mybatis.generator.enhance.framework.context.ApplicationContext;

/**
 * @author heshiyuan
 */
public interface ApplicationTask extends Skipable {

    boolean perform(ApplicationContext context) throws Exception;

    void initLogger(String applicationTaskId, String applicationId);
}

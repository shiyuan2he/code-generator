package com.hsy.mybatis.generator;

import com.hsy.mybatis.generator.framework.Application;
import com.hsy.mybatis.generator.task.CombineTask;
import com.hsy.mybatis.generator.task.DaoTask;
import com.hsy.mybatis.generator.task.EntityTask;
import com.hsy.mybatis.generator.task.InitTask;
import com.hsy.mybatis.generator.task.MapperTask;
import com.hsy.mybatis.generator.task.VoTask;

/**
 * 应用程序入口
 * @author heshiyuan
 */
public class GeneratorApplication {
    public static void main(String[] args) {
        Application application = new Application(GeneratorApplication.class.getSimpleName());
        application.setApplicationName(GeneratorApplication.class.getName());
        //添加任务
        application.addTask(InitTask.class)
                .addTask(CombineTask.class)
                .addTask(EntityTask.class)
                .addTask(DaoTask.class)
                .addTask(MapperTask.class)
                .addTask(VoTask.class);
        application.work();
    }
}

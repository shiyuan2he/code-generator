package me.hsy.mybatis.generator.enhance;

import me.hsy.mybatis.generator.enhance.framework.Application;
import me.hsy.mybatis.generator.enhance.task.*;

/**
 * 应用程序入口
 * @author heshiyuan
 */
public class GeneratorApplication {
    public static void main(String[] args) {
        Application application = new Application(GeneratorApplication.class.getSimpleName());
        application.setApplicationName(GeneratorApplication.class.getName());
        //添加任务
        application
                .addTask(InitTask.class)
                .addTask(CombineTask.class)
                .addTask(EntityTask.class)
                .addTask(DaoTask.class)
                .addTask(MapperTask.class)
                .addTask(VoTask.class)
                .addTask(DtoTask.class)
                .addTask(ServiceTask.class)
                .addTask(ConvertUtilTask.class)
                .addTask(ServiceImplTask.class)
        ;
        application.work();
    }
}

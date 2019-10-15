package me.hsy.mybatis.generator.enhance;

import me.hsy.mybatis.generator.enhance.framework.Application;
import me.hsy.mybatis.generator.enhance.task.CombineTask;
import me.hsy.mybatis.generator.enhance.task.DaoTask;
import me.hsy.mybatis.generator.enhance.task.EntityTask;
import me.hsy.mybatis.generator.enhance.task.InitTask;
import me.hsy.mybatis.generator.enhance.task.MapperTask;
import me.hsy.mybatis.generator.enhance.task.VoTask;

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
//                .addTask(VoTask.class)
        ;
        application.work();
    }
}

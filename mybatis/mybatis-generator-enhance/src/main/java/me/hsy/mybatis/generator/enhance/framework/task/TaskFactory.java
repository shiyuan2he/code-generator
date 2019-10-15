package me.hsy.mybatis.generator.enhance.framework.task;

import me.hsy.mybatis.generator.enhance.framework.ApplicationTask;

import java.util.LinkedList;

/**
 * Created by Lewis on 2018/3/20
 */

public class TaskFactory {

    public static LinkedList<ApplicationTask> taskList = new LinkedList<>();

    public static synchronized ApplicationTask consume() {
        return taskList.remove();
    }

    public static void provide(ApplicationTask task) {
        taskList.add(task);
    }

    public static boolean haveNoTasks() {
        return taskList.isEmpty();
    }

    public static boolean haveTasks() {
        return !taskList.isEmpty();
    }
}

package me.hsy.mybatis.generator.enhance.model;


import lombok.Data;

/**
 * @author heshiyuan
 */
@Data
public class DaoInfo {
    /**
     * 包路径
     */
    private String packageStr;

    /**
     * 需要导入的包
     */
    private String importStr;

    /**
     * 类名
     */
    private String className;

    /**
     * 实体信息
     */
    private EntityInfo entityInfo;
    private TableInfo tableInfo;
}

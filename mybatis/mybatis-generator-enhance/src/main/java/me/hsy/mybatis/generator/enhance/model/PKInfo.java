package me.hsy.mybatis.generator.enhance.model;

import lombok.Data;

import java.util.List;

/**
 * 表主键信息
 * @author heshiyuan
 */
@Data
public class PKInfo {
    /**
     * 表类别
     */
    private String tableCat;
    /**
     * 表模式
     */
    private String tableSchema ;
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 列名称
     */
    private String columnName;

    /**
     * 主键中的序列号
     */
    private short keySeq;
    /**
     * 主键的名称
     */
    private String pkName;
    /**
     * 列属性
     */
    private String javaColumnField;
    /**
     * 列类型
     */
    private String javaColumnType;
    /**
     * 列备注
     */
    private String columnComment;
}

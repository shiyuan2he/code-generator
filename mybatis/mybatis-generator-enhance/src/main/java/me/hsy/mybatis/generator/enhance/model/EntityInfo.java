package me.hsy.mybatis.generator.enhance.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * @author heshiyuan
 */
@Data
public class EntityInfo {

    /**
     * 实体名
     */
    private String entityName;

    /**
     * 实体描述
     */
    private String entityDesc;

    /**
     * 实体所在包路径
     */
    private String entityPackage;

    /**
     * 实体类名
     */
    private String className;

    /**
     * 包路径 + 类名
     */
    private String packageClassName;

    /**
     * 表名
     */
    private String tableName;
    /**
     * 需要导入的包
     */
    private Set<String> imports = new HashSet<String>();
    /**
     * 属性名以及对应的类型
     */
    private Map<String, String> propTypes;
    /**
     * 属性名以及注释的对应
     */
    private Map<String, String> propRemarks;
    /**
     * 属性名和jdbc类型的映射
     */
    private Map<String, String> propJdbcTypes;
    /**
     * 属性名和字段名的映射
     */
    private Map<String, String> propNameColumnNames;
}

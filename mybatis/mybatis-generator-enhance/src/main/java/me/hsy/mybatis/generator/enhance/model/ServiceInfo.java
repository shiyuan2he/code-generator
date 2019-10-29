package me.hsy.mybatis.generator.enhance.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author heshiyuan
 */
@Data
@ToString
public class ServiceInfo {
    /**
     * 包路径
     */
    private String packageStr;
    /**
     * 需要导入的包
     */
    private List<String> importStrList;
    /**
     * 类名
     */
    private String className;
    /**
     * 业务描述
     */
    private String serviceDesc;
    /**
     * 主键数据类型
     */
    private String pkColumnType;
    /**
     * 主键字段名
     */
    private String pkPropName;
    private TableInfo tableInfo;
    private String voClassName;
    private String dtoClassName;
    private EntityInfo entityInfo;
}

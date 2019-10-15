package me.hsy.mybatis.generator.enhance.model;

import lombok.Data;

import java.util.List;
/**
 * 数据库表相关信息
 * @author heshiyuan
 */
@Data
public class TableInfo {
    private String name;
    private String type;
    private String remark;
    private List<ColumnInfo> columnList;
}

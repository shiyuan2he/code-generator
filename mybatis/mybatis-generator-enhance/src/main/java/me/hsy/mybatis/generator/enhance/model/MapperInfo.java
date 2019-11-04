package me.hsy.mybatis.generator.enhance.model;

import lombok.Data;

/**
 * @author heshiyuan
 */
@Data
public class MapperInfo {
    private String fileName;
    private String namespace;
    private DaoInfo daoInfo;
    private EntityInfo entityInfo;
    private TableInfo tableInfo;
}

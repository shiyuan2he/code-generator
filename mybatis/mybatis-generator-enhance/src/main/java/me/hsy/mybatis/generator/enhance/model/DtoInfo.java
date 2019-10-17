package me.hsy.mybatis.generator.enhance.model;

import lombok.Data;

/**
 * @author heshiyuan
 */
@Data
public class DtoInfo {
    /**
     * 包路径
     */
    private String packageStr;

    /**
     * 类名
     */
    private String className;

    /**
     * 实体信息
     */
    private EntityInfo entityInfo;
}

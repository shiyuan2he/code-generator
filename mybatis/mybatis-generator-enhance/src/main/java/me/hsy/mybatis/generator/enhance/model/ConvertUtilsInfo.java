package me.hsy.mybatis.generator.enhance.model;

import lombok.Data;

import java.util.List;

/**
 * @author heshiyuan
 */
@Data
public class ConvertUtilsInfo {
    /**
     * 需要导入的包
     */
    private List<String> importStrList;
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
    private ServiceInfo serviceInfo;
}

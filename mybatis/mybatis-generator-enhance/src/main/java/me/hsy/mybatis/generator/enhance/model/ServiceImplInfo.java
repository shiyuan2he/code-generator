package me.hsy.mybatis.generator.enhance.model;

import lombok.Data;

import java.util.List;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path code-generator/me.hsy.mybatis.generator.enhance.model
 * @date 2019/10/22 09:27
 */
@Data
public class ServiceImplInfo {
    private ServiceInfo serviceInfo;
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
    private String convertClassName;
}

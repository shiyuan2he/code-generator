package me.hsy.mybatis.generator.enhance.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author heshiyuan
 */
@Data
@ToString
public class ControllerInfo {
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

    private ServiceInfo serviceInfo;
}

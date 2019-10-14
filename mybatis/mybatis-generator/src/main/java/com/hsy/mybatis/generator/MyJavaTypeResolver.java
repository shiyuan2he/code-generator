package com.hsy.mybatis.generator;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.sql.Types;

/**
 * @author heshiyuan
 * @description <p></p>
 * @path mybatis/com.hsy.mybatis.generator
 * @date 29/03/2019 22:49
 * @github http://github.com/shiyuan2he
 * @email shiyuan4work@126.com
 * Copyright (c) 2019 shiyuan4work@126.com All rights reserved.
 * @price ¥5    微信：hewei1109
 */
public class MyJavaTypeResolver extends JavaTypeResolverDefaultImpl{
    /**
     * 将tinyint转换为Integer，这里是关键所在
     */
    public MyJavaTypeResolver() {
        super();
        super.typeMap.put(Types.BIT, new JavaTypeResolverDefaultImpl.JdbcTypeInformation("BIT", new FullyQualifiedJavaType(Boolean.class.getName())));
        //super.typeMap.put(Types.TINYINT, new JavaTypeResolverDefaultImpl.JdbcTypeInformation("TINYINT", new FullyQualifiedJavaType(Integer.class.getName())));
    }
}

/**
 * Project Name:qyk-util-base
 * File Name:Configuration.java
 * Package Name:org.qyk.base.util
 * Date:2017年2月16日下午5:49:13
 * Copyright (c) 2017, Thinkive(http://www.thinkive.com/) All Rights Reserved.
 */

package me.hsy.mybatis.generator.enhance.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.hsy.mybatis.generator.enhance.common.Constants;
import me.hsy.mybatis.generator.enhance.util.StringHelper;
import me.hsy.mybatis.generator.enhance.util.XMLHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;


/**
 * 加载配置
 * @author heshiyuan
 */
public class Configuration {

    private static final Logger logger = LogManager.getLogger(Configuration.class);

    private static final Map items = new HashMap();


    static {
        try {
            loadConfig();
        } catch (RuntimeException ex) {
            logger.error("读入配置文件出错", ex);
        }
    }

    /**
     * 读入配置文件
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static void loadConfig() {
        Document document = XMLHelper.getDocument(Configuration.class, Constants.CONFIG_FILE_NAME);
        Element root = document.getRootElement();

        //数据库信息
        List<Element> list = root.element("jdbc").elements();

        for (Element element : list) {
            items.put(Constants.PREFIX_JDBC + "." + element.attributeValue("name"), element.attributeValue("value"));
        }

        List<Element> catList = root.elements("category");
        for (Element element : catList) {
            String catName = element.attributeValue("name");
            List<Element> itemList = element.elements("item");
            for (Element item : itemList) {
                String itemName = item.attributeValue("name");
                String value = item.attributeValue("value");
                if (StringHelper.isNotEmpty(itemName)) {
                    Configuration.items.put(catName + "." + itemName, value);
                }
            }
        }
        logger.info("{}", items);
    }

    /**
     * 获得字串配置值
     */
    public static String getString(String name) {
        String value = (String) items.get(name);
        return (value == null) ? "" : value;
    }

}


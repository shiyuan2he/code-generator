package com.hsy.mybatis.generator.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.hsy.mybatis.generator.common.Constants;
import com.hsy.mybatis.generator.framework.AbstractApplicationTask;
import com.hsy.mybatis.generator.framework.context.ApplicationContext;
import com.hsy.mybatis.generator.util.PropertyUtil;
import com.hsy.mybatis.generator.util.StringUtil;
import com.hsy.mybatis.generator.model.TableInfo;
import com.hsy.mybatis.generator.config.Configuration;
import com.hsy.mybatis.generator.model.ColumnInfo;
import com.hsy.mybatis.generator.model.EntityInfo;

public class CombineTask extends AbstractApplicationTask {

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("组装信息");
        
        //获取实体相关的配置
        String packageName = Configuration.getString("entity.package");
        //存放路径
        String path = Configuration.getString("entity.path");
        
        logger.info("所有实体的包名为{}， 路径为：{}", packageName, path);
        
        //获取表和实体的映射集合
        Map<String, String> table2Entities = (Map<String, String>) context.getAttribute("tableName.to.entityName");
        Map<String, String> entity2Desc = (Map<String, String>) context.getAttribute("entityName.to.desc");
        Map<String, TableInfo> tableInfos = (Map<String, TableInfo>) context.getAttribute("tableInfos");
        
        List<EntityInfo> entityInfos = new ArrayList<>();
        for (Entry<String, String> entry : table2Entities.entrySet()) {
            EntityInfo entityInfo = new EntityInfo();
            
            //表名
            String tableName = entry.getKey();
            //实体名
            String entityName= entry.getValue();
            //表信息
            TableInfo tableInfo = tableInfos.get(tableName);
            
            Set<String> imports = new HashSet<>();
            Map<String, String> propTypes = new LinkedHashMap<>();
            Map<String, String> propRemarks = new LinkedHashMap<>();
            Map<String, String> propJdbcTypes = new LinkedHashMap<>();
            Map<String, String> propName2ColumnNames = new LinkedHashMap<>();
            
            entityInfo.setTableName(tableName);
            entityInfo.setEntityName(entityName);
            entityInfo.setEntityDesc(entity2Desc.get(entityName));
            entityInfo.setClassName(entityName + Constants.ENTITY_SUFFIX);
            entityInfo.setEntityPackage(packageName);
          
            //遍历表字段信息
            List<ColumnInfo> columns = tableInfo.getColumnList();
            for (ColumnInfo columnInfo : columns) {
                String fieldName = columnInfo.getName();
                String fieldType = columnInfo.getType();
                
                //通过字段名生成属性名
                String propName = StringUtil.convertFieldName2PropName(fieldName);
                String propType = PropertyUtil.getValueByKey(fieldType);
                
                propTypes.put(propName, propType);
                propRemarks.put(propName, columnInfo.getRemark());
                propJdbcTypes.put(propName, PropertyUtil.getValueByKey("_" + propType));
                propName2ColumnNames.put(propName, columnInfo.getName().toUpperCase());
            }
            logger.info("属性类型：{}", propTypes);
            logger.info("属性jdbcTypes：{}", propJdbcTypes);
            
            //获取此实体所有的类型
            Collection<String> types =  propTypes.values();
            
            for (String type : types) {
                if (!StringUtil.isEmpty(PropertyUtil.getValueByKey(type))) {
                    imports.add(PropertyUtil.getValueByKey(type));
                }
            }
            logger.info("imports:{}", imports);
            entityInfo.setPropTypes(propTypes);
            entityInfo.setPropRemarks(propRemarks);
            entityInfo.setPropJdbcTypes(propJdbcTypes);
            entityInfo.setPropNameColumnNames(propName2ColumnNames);
            entityInfo.setImports(imports);
            entityInfo.setPackageClassName(entityInfo.getEntityPackage() + "." + entityInfo.getClassName());
            entityInfos.add(entityInfo);
        }
        
        context.setAttribute("entityInfos", entityInfos);
        return false;
    }
    
}

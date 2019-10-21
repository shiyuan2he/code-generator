package me.hsy.mybatis.generator.enhance.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import me.hsy.mybatis.generator.enhance.common.Constants;
import me.hsy.mybatis.generator.enhance.framework.AbstractApplicationTask;
import me.hsy.mybatis.generator.enhance.framework.context.ApplicationContext;
import me.hsy.mybatis.generator.enhance.model.*;
import me.hsy.mybatis.generator.enhance.util.PropertyUtil;
import me.hsy.mybatis.generator.enhance.util.StringUtil;
import me.hsy.mybatis.generator.enhance.config.Configuration;

/**
 * 组合任务
 * @author heshiyuan
 */
public class CombineTask extends AbstractApplicationTask {

    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) {
        logger.info("组装信息");

        //获取实体相关的配置
        String packageName = Configuration.getString("entity.package");
        String packageNameVo = Configuration.getString("vo.package");
        String packageNameDto = Configuration.getString("dto.package");

        logger.info("所有实体的包名为{}", packageName);
        
        //获取表和实体的映射集合
        Map<String, String> table2Entities = context.getTable2Entity();
        Map<String, String> entity2Desc = context.getTable2Desc();
        Map<String, TableInfo> tableInfoMap = context.getTableInfoMap();
        
        List<EntityInfo> entityInfoList = new ArrayList<>();
        List<VoInfo> voInfoList = new ArrayList<>();
        List<DtoInfo> dtoInfoList = new ArrayList<>();
        List<ServiceInfo> serviceInfoList = new ArrayList<>();
        for (Entry<String, String> entry : table2Entities.entrySet()) {
            EntityInfo entityInfo = new EntityInfo();
            VoInfo voInfo = new VoInfo();
            DtoInfo dtoInfo = new DtoInfo();
            //表名
            String tableName = entry.getKey();
            //实体名
            String entityName= entry.getValue();
            //表信息
            TableInfo tableInfo = tableInfoMap.get(tableName);
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

            voInfo.setClassName(entityName + Constants.VO_SUFFIX);
            voInfo.setPackageStr(packageNameVo);

            dtoInfo.setClassName(entityName + Constants.DTO_SUFFIX);
            dtoInfo.setPackageStr(packageNameDto);
            //遍历表字段信息
            List<ColumnInfo> columns = tableInfo.getColumnList();
            for (ColumnInfo columnInfo : columns) {
                String fieldName = columnInfo.getName();
                String fieldType = columnInfo.getType();

                //通过字段名生成属性名
                String propName = StringUtil.convertFieldName2PropName(fieldName);
                String propType = PropertyUtil.getValueByKey(fieldType);
                if(fieldName.equals(tableInfo.getPkInfo().getColumnName())){
                    tableInfo.getPkInfo().setJavaColumnType(propType);
                    tableInfo.getPkInfo().setJavaColumnField(propName);
                    tableInfo.getPkInfo().setColumnComment(columnInfo.getRemark());
                }

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
            entityInfoList.add(entityInfo);
            voInfo.setEntityInfo(entityInfo);
            voInfoList.add(voInfo);
            dtoInfo.setEntityInfo(entityInfo);
            dtoInfoList.add(dtoInfo);
            serviceInfoList.add(generateServiceInfo(entry, entityInfo, voInfo, dtoInfo, tableInfo));
        }
        context.setEntityInfoList(entityInfoList);
        context.setVoList(voInfoList);
        context.setDtoInfoList(dtoInfoList);
        context.setServiceInfoList(serviceInfoList);
        return false;
    }

    private ServiceInfo generateServiceInfo(Entry<String, String> entry, EntityInfo entityInfo, VoInfo voInfo, DtoInfo dtoInfo, TableInfo tableInfo) {
        String packageNameService = Configuration.getString("service.package");
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setPackageStr(packageNameService);
        serviceInfo.setClassName("I" + entry.getValue() + Constants.SERVICE_SUFFIX);
        serviceInfo.setServiceDesc(entityInfo.getEntityDesc());
        List<String> importStrList = new ArrayList<>();
        importStrList.add(voInfo.getPackageStr() + "." + voInfo.getClassName());
        importStrList.add(dtoInfo.getPackageStr() + "." + dtoInfo.getClassName());
        serviceInfo.setImportStrList(importStrList);
        serviceInfo.setVoInfo(voInfo);
        serviceInfo.setDtoInfo(dtoInfo);
        serviceInfo.setTableInfo(tableInfo);
        return serviceInfo;
    }
}

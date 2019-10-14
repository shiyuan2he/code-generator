package com.hsy.mybatis.generator.task;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hsy.mybatis.generator.framework.AbstractApplicationTask;
import com.hsy.mybatis.generator.util.DbUtil;
import com.hsy.mybatis.generator.util.PropertyUtil;
import com.hsy.mybatis.generator.util.StringUtil;
import com.hsy.mybatis.generator.model.TableInfo;
import com.hsy.mybatis.generator.config.Configuration;
import com.hsy.mybatis.generator.framework.context.ApplicationContext;
import com.hsy.mybatis.generator.model.ColumnInfo;

public class InitTask extends AbstractApplicationTask {

    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("初始化任务");
        
        //加载属性文件
        //字段类型与属性类型的映射
        PropertyUtil.loadProp("columnType2PropType.properties");
        
        //属性类型与包类名的映射
        PropertyUtil.loadProp("propType2Package.properties");
        
        //属性类型与jdbc类型的映射，注意这里为了防止与上面冲突，属性类型前加了_
        PropertyUtil.loadProp("propType2JdbcType.properties");
        
        //获取所有需要生成的表名
        List<String> tableList = StringUtil.splitStr2List(Configuration.getString("base.tableNames"), ",");
        logger.info("需要生成的表：{}", tableList);
        
        //对应的实体名
        List<String> entityNames = StringUtil.splitStr2List(Configuration.getString("base.entityNames"), ",");
        
        //实体对应的描述
        List<String> entityDescs = StringUtil.splitStr2List(Configuration.getString("base.entityDescs"), ",");
        
        //添加映射关系
        Map<String, String> table2Entity = new HashMap<>();
        for (int i = 0; i < tableList.size(); i++) {
            table2Entity.put(tableList.get(i), entityNames.get(i));
        }
        
        Map<String, String> entity2Desc = new HashMap<>();
        for (int i = 0; i < entityNames.size(); i++) {
            entity2Desc.put(entityNames.get(i), entityDescs.get(i));
        }
        
        //放入上下文
        context.setAttribute("tableName.to.entityName", table2Entity);
        context.setAttribute("entityName.to.desc", entity2Desc);
        
        //连接数据库
        Connection conn = null;
        ResultSet tableRS = null;
        ResultSet columnRS = null;
        
        try {
            conn = DbUtil.getConn();
            DatabaseMetaData dbMetaData = conn.getMetaData();
            logger.info("数据库类型：{}", dbMetaData.getDatabaseProductName());
            logger.info("数据库版本：{}", dbMetaData.getDatabaseProductVersion());
            String schemaPattern = Configuration.getString("base.schemaPattern");
            //获取表的结果集
            tableRS = dbMetaData.getTables(null, schemaPattern, null, new String[] {"TABLE"});

            //遍历
            Map<String, TableInfo> tableInfos = new HashMap<>(10);
            while(tableRS.next()) {
                //表名
                String tableName = tableRS.getString("TABLE_NAME").toLowerCase();
                if (tableList.contains(tableName.toLowerCase())) {
                    logger.info("*****************************");
                    logger.info("tableName:{}", tableName);
                    
                    TableInfo tableInfo = new TableInfo();
                    tableInfo.setName(tableName);
                    
                    //表注释
                    String tableRemark = tableRS.getString("REMARKS");
                    tableInfo.setRemark(tableRemark);
                    logger.info("表{}的注释:{}", tableName, tableRemark);
                    
                    //表类型
                    String tableType = tableRS.getString("TABLE_TYPE");
                    tableInfo.setType(tableType);
                    logger.info("表{}的类型:{}", tableName, tableType);
                    
                    
                    //字段
                    //获取列的结果集
                    columnRS = dbMetaData.getColumns(null,schemaPattern, tableName, null);
                    
                    List<ColumnInfo> columnList = new ArrayList<ColumnInfo>();
                    while(columnRS.next()) {
                        String columnName = columnRS.getString("COLUMN_NAME").toLowerCase();
                        String columnType = columnRS.getString("TYPE_NAME").toLowerCase();
                        String columnRemark = columnRS.getString("REMARKS");
                        logger.info("字段名称：{}, 字段类型：{}, 字段注释：{}", columnName, columnType, columnRemark);
                        
o                        int len = columnRS.getInt("COLUMN_SIZE");
                        logger.info("字段长度：{}", len);

                        int precision = columnRS.getInt("DECIMAL_DIGITS");
                        logger.info("字段类型精度：{}", precision);

                        if (columnName == null || "".equals(columnName)) {
                            continue;
                        }
                        
                        ColumnInfo ci = new ColumnInfo();
                        ci.setName(columnName);
                        ci.setType(columnType);
                        ci.setRemark(columnRemark);
                        ci.setLen(len);
                        ci.setPrecision(precision);
                        
                        columnList.add(ci);
                    }
                    logger.info("*****************************");
                    tableInfo.setColumnList(columnList);
                    tableInfos.put(tableName, tableInfo);
                }
                
               
            }
            
            //放入上下文
            context.setAttribute("tableInfos", tableInfos);
        } catch (Exception e) {
            logger.info("初始化任务异常", e);
            e.printStackTrace();
        } finally {
            DbUtil.closeConn(conn, null, tableRS);
            DbUtil.closeConn(null, null, columnRS);
        }
        
        return false;
    }

}

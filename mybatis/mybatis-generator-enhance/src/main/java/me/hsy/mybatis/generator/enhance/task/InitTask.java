package me.hsy.mybatis.generator.enhance.task;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import me.hsy.mybatis.generator.enhance.framework.AbstractApplicationTask;
import me.hsy.mybatis.generator.enhance.util.DbUtil;
import me.hsy.mybatis.generator.enhance.util.PropertyUtil;
import me.hsy.mybatis.generator.enhance.util.StringHelper;
import me.hsy.mybatis.generator.enhance.util.StringUtil;
import me.hsy.mybatis.generator.enhance.model.TableInfo;
import me.hsy.mybatis.generator.enhance.config.Configuration;
import me.hsy.mybatis.generator.enhance.framework.context.ApplicationContext;
import me.hsy.mybatis.generator.enhance.model.ColumnInfo;

import javax.xml.transform.Result;

/**
 * @author heshiyuan
 */
public class InitTask extends AbstractApplicationTask {
    private List<String> tableList = null;
    private String schemaPattern = null;
    private ResultSet tableRS = null;
    private ResultSet columnRS = null;
    /**
     * 任务初始化
     * 1. 加载配置文件
     * 2. 连接数据库,获取数据库，表，列等信息
     * @param context
     * @return
     */
    @Override
    protected boolean doInternal(ApplicationContext context) throws SQLException {
        // 1.
        logger.info("初始化任务");
        //字段类型与属性类型的映射
        PropertyUtil.loadProp("columnType2PropType.properties");
        //属性类型与包类名的映射
        PropertyUtil.loadProp("propType2Package.properties");
        //属性类型与jdbc类型的映射，注意这里为了防止与上面冲突，属性类型前加了_
        PropertyUtil.loadProp("propType2JdbcType.properties");
        schemaPattern = Configuration.getString("base.schemaPattern");
        //获取所有需要生成的表名
        tableList = StringUtil.splitStr2List(Configuration.getString("base.tableNames"), ",");
        logger.info("需要生成的表：{}", tableList);
        //对应的实体名
        List<String> entityNames = tableList.stream().map(table -> StringHelper.lineToHump(table)).collect(Collectors.toList());
        logger.info("需要生成的表：{}", entityNames);

        //添加映射关系
        Map<String, String> table2Entity = new HashMap<>(10);
        for (int i = 0; i < tableList.size(); i++) {
            table2Entity.put(tableList.get(i), entityNames.get(i));
        }
        //放入上下文
        context.setTable2Entity(table2Entity);
        //2
        doDb(context);
        return false;
    }

    private void doDb(ApplicationContext context) throws SQLException {
        Connection conn = null;
        try {
            conn = DbUtil.getConn();
            DatabaseMetaData dbMetaData = conn.getMetaData();
            logger.info("数据库类型：{}", dbMetaData.getDatabaseProductName());
            logger.info("数据库版本：{}", dbMetaData.getDatabaseProductVersion());

            //放入上下文
            doTableInfo(context, dbMetaData, tableRS, columnRS);
        } catch (Exception e) {
            logger.info("初始化任务异常", e);
            e.printStackTrace();
        } finally {
            DbUtil.closeConn(conn, null, tableRS);
            DbUtil.closeConn(null, null, columnRS);
        }
    }

    private void doTableInfo(ApplicationContext context, DatabaseMetaData dbMetaData, ResultSet tableRS, ResultSet columnRS) throws Exception{
        Map<String, TableInfo> tableInfoMap = new HashMap<>(10);
        Map<String, String> table2Desc = new HashMap<>(10);
        //获取表的结果集
        tableRS = dbMetaData.getTables(null, schemaPattern, null, new String[] {"TABLE"});

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
                table2Desc.put(tableName, tableRemark);
                //表类型
                String tableType = tableRS.getString("TABLE_TYPE");
                tableInfo.setType(tableType);
                logger.info("表{}的类型:{}", tableName, tableType);
                //字段
                //获取列的结果集
                tableInfo.setColumnList(doColumn(dbMetaData, tableName));
                logger.info("*****************************");
                tableInfoMap.put(tableName, tableInfo);
            }
        }
        context.setTableInfoMap(tableInfoMap);
        context.setTable2Desc(table2Desc);
    }

    private List<ColumnInfo> doColumn(DatabaseMetaData dbMetaData, String tableName) throws SQLException {
        columnRS = dbMetaData.getColumns(null,schemaPattern, tableName, null);
        List<ColumnInfo> columnList = new ArrayList<>();
        while(columnRS.next()) {
            String columnName = columnRS.getString("COLUMN_NAME").toLowerCase();
            String columnType = columnRS.getString("TYPE_NAME").toLowerCase();
            String columnRemark = columnRS.getString("REMARKS");
            int len = columnRS.getInt("COLUMN_SIZE");
            int precision = columnRS.getInt("DECIMAL_DIGITS");
            logger.info("字段名称：{}, 字段类型：{}, 字段注释：{}，字段长度：{}，字段类型精度：{}", columnName, columnType, columnRemark, len, precision);

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
        return columnList;
    }
}

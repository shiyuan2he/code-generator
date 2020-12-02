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
import me.hsy.mybatis.generator.enhance.model.PKInfo;
import me.hsy.mybatis.generator.enhance.util.DbUtil;
import me.hsy.mybatis.generator.enhance.util.PropertyUtil;
import me.hsy.mybatis.generator.enhance.util.StringHelper;
import me.hsy.mybatis.generator.enhance.util.StringUtil;
import me.hsy.mybatis.generator.enhance.model.TableInfo;
import me.hsy.mybatis.generator.enhance.config.Configuration;
import me.hsy.mybatis.generator.enhance.framework.context.ApplicationContext;
import me.hsy.mybatis.generator.enhance.model.ColumnInfo;
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
     *
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
        List<String> entityNames = tableList.stream().map(table -> {
            if(table.startsWith("t_")){
                table = table.substring(1, table.length());
            }
            return StringHelper.lineToHump(table);
        }).collect(Collectors.toList());
        logger.info("需要生成的类：{}", entityNames);

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
            generateDBInfo(dbMetaData);
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

    private void generateDBInfo(DatabaseMetaData dbMetaData) throws SQLException {
        logger.info("数据库已知的用户: " + dbMetaData.getUserName());
        logger.info("数据库的系统函数的逗号分隔列表: " + dbMetaData.getSystemFunctions());
        logger.info("数据库的时间和日期函数的逗号分隔列表: " + dbMetaData.getTimeDateFunctions());
        logger.info("数据库的字符串函数的逗号分隔列表: " + dbMetaData.getStringFunctions());
        logger.info("数据库供应商用于 'schema' 的首选术语: " + dbMetaData.getSchemaTerm());
        logger.info("数据库URL: " + dbMetaData.getURL());
        logger.info("是否允许只读:" + dbMetaData.isReadOnly());
        logger.info("数据库的产品名称:" + dbMetaData.getDatabaseProductName());
        logger.info("数据库的版本:" + dbMetaData.getDatabaseProductVersion());
        logger.info("驱动程序的名称:" + dbMetaData.getDriverName());
        logger.info("驱动程序的版本:" + dbMetaData.getDriverVersion());
        logger.info("数据库中使用的表类型");
        ResultSet rs = dbMetaData.getTableTypes();
        while (rs.next()) {
            logger.info(rs.getString(1));
        }
    }

    private void doTableInfo(ApplicationContext context, DatabaseMetaData dbMetaData, ResultSet tableRS, ResultSet columnRS) throws Exception {

        Map<String, TableInfo> tableInfoMap = new HashMap<>(10);
        Map<String, String> table2Desc = new HashMap<>(10);
        //获取表的结果集
        tableRS = dbMetaData.getTables(null, schemaPattern, null, new String[]{"TABLE"});
        while (tableRS.next()) {
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
                tableInfo.setPkInfo(getAllPrimaryKeys(dbMetaData, tableName));
                logger.info("*****************************");
                tableInfoMap.put(tableName, tableInfo);
            }
        }
        context.setTableInfoMap(tableInfoMap);
        context.setTable2Desc(table2Desc);
    }

    private List<ColumnInfo> doColumn(DatabaseMetaData dbMetaData, String tableName) throws SQLException {
        columnRS = dbMetaData.getColumns(null, schemaPattern, tableName, null);
        outputColumnInfo(dbMetaData, tableName);
        List<ColumnInfo> columnList = new ArrayList<>();
        while (columnRS.next()) {
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
    /**
     * 获得一个表的主键信息
     */
    public PKInfo getAllPrimaryKeys(DatabaseMetaData dbMetaData, String tableName) throws SQLException {
        PKInfo pkInfo = new PKInfo();
        ResultSet rs = null;
        try{
            rs = dbMetaData.getPrimaryKeys(null, schemaPattern, tableName);
            while (rs.next()){
                //列名
                String columnName = rs.getString("COLUMN_NAME");
                //序列号(主键内值1表示第一列的主键，值2代表主键内的第二列)
                short keySeq = rs.getShort("KEY_SEQ");
                //主键名称
                String pkName = rs.getString("PK_NAME");
                logger.info("主键信息{}", columnName + "-" + keySeq + "-" + pkName);
                pkInfo.setColumnName(columnName);
                pkInfo.setKeySeq(keySeq);
                pkInfo.setPkName(pkName);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DbUtil.closeConn(null, null, rs);
        }
        return pkInfo;
    }
    private void outputColumnInfo(DatabaseMetaData dbMetaData, String tableName) {
        try {
            ResultSet rs = dbMetaData.getColumns(null, schemaPattern, tableName, "%");
            while (rs.next()) {
                //表目录（可能为空）
                String tableCat = rs.getString("TABLE_CAT");
                //表的架构（可能为空）;
                String tableSchemaName = rs.getString("TABLE_SCHEM");
                //表名
                String tableName_ = rs.getString("TABLE_NAME");
                //列名
                String columnName = rs.getString("COLUMN_NAME");
                //对应的java.sql.Types类型
                int dataType = rs.getInt("DATA_TYPE");
                //java.sql.Types类型   名称
                String dataTypeName = rs.getString("TYPE_NAME");
                //列大小;
                int columnSize = rs.getInt("COLUMN_SIZE");
                //小数位数
                int decimalDigits = rs.getInt("DECIMAL_DIGITS");
                //基数（通常是10或2）
                int numPrecRadix = rs.getInt("NUM_PREC_RADIX");
                //是否允许为null
                int nullAble = rs.getInt("NULLABLE");
                //列描述
                String remarks = rs.getString("REMARKS");
                //默认值
                String columnDef = rs.getString("COLUMN_DEF");
                //sql数据类型
                int sqlDataType = rs.getInt("SQL_DATA_TYPE");
                //SQL日期时间分?
                int sqlDatetimeSub = rs.getInt("SQL_DATETIME_SUB");
                //char类型的列中的最大字节数
                int charOctetLength = rs.getInt("CHAR_OCTET_LENGTH");
                //表中列的索引（从1开始）
                int ordinalPosition = rs.getInt("ORDINAL_POSITION");
                /**
                 * ISO规则用来确定某一列的为空性。
                 * 是---如果该参数可以包括空值;
                 * 无---如果参数不能包含空值
                 * 空字符串---如果参数为空性是未知的
                 */
                String isNullAble = rs.getString("IS_NULLABLE");

                /**
                 * 指示此列是否是自动递增
                 * 是---如果该列是自动递增
                 * 无---如果不是自动递增列
                 * 空字串---如果不能确定它是否
                 * 列是自动递增的参数是未知
                 */
                String isAutoincrement = rs.getString("IS_AUTOINCREMENT");
                logger.info("{}", tableCat + "-" + tableSchemaName + "-" + tableName_ + "-" + columnName + "-"
                        + dataType + "-" + dataTypeName + "-" + columnSize + "-" + decimalDigits + "-" + numPrecRadix
                        + "-" + nullAble + "-" + remarks + "-" + columnDef + "-" + sqlDataType + "-" + sqlDatetimeSub
                        + charOctetLength + "-" + ordinalPosition + "-" + isNullAble + "-" + isAutoincrement + "-");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得该用户下面的所有视图
     */
    /*public void getAllViewList(DatabaseMetaData dbMetaData, String schemaName) {
        try {
            String[] types = {"VIEW"};
            ResultSet rs = dbMetaData.getTables(null, schemaName, "%", types);
            while (rs.next()) {
                String viewName = rs.getString("TABLE_NAME"); //视图名
                String viewType = rs.getString("TABLE_TYPE"); //视图类型
                String remarks = rs.getString("REMARKS");        //视图备注
                System.out.println(viewName + "-" + viewType + "-" + remarks);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
    /**
     * 获得一个表的索引信息
     */
    /*public void getIndexInfo(String schemaName, String tableName) {
        try{
            ResultSet rs = dbMetaData.getIndexInfo(null, schemaName, tableName, true, true);
            while (rs.next()){
                boolean nonUnique = rs.getBoolean("NON_UNIQUE");//非唯一索引(Can index values be non-unique. false when TYPE is  tableIndexStatistic   )
                String indexQualifier = rs.getString("INDEX_QUALIFIER");//索引目录（可能为空）
                String indexName = rs.getString("INDEX_NAME");//索引的名称
                short type = rs.getShort("TYPE");//索引类型
                short ordinalPosition = rs.getShort("ORDINAL_POSITION");//在索引列顺序号
                String columnName = rs.getString("COLUMN_NAME");//列名
                String ascOrDesc = rs.getString("ASC_OR_DESC");//列排序顺序:升序还是降序
                int cardinality = rs.getInt("CARDINALITY");   //基数
                System.out.println(nonUnique + "-" + indexQualifier + "-" + indexName + "-" + type + "-" + ordinalPosition + "-" + columnName + "-" + ascOrDesc + "-" + cardinality);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }*/


}

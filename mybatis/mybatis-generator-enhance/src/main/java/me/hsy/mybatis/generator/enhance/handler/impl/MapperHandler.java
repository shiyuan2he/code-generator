package me.hsy.mybatis.generator.enhance.handler.impl;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import me.hsy.mybatis.generator.enhance.handler.BaseHandler;
import me.hsy.mybatis.generator.enhance.config.Configuration;
import me.hsy.mybatis.generator.enhance.model.MapperInfo;
import me.hsy.mybatis.generator.enhance.util.StringHelper;

/**
 * @author heshiyuan
 */
public class MapperHandler extends BaseHandler<MapperInfo> {
    public MapperHandler(String ftlName, MapperInfo info) {
        this.ftlName = ftlName;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir")
                + File.separator + mavenResourcePath
                + File.separator + StringHelper.join(File.separator, Configuration.getString("mapperXml.path").split("\\."))
                + File.separator + info.getFileName() + ".xml";
    }
    
    @Override
    public void combineParams(MapperInfo info) {
        this.param.put("namespace", info.getNamespace());
        this.param.put("entityType", info.getEntityInfo().getPackageClassName());
        this.param.put("tableName", info.getEntityInfo().getTableName());
        this.param.put("entityName", info.getEntityInfo().getEntityName());
        
        StringBuilder resultMap = new StringBuilder();
        StringBuilder baseColumn = new StringBuilder();
        StringBuilder insertIfColumns = new StringBuilder();
        StringBuilder insertIfProps = new StringBuilder();
        StringBuilder insertBatchColumns = new StringBuilder();
        StringBuilder insertBatchProps = new StringBuilder();
        StringBuilder updateColProps = new StringBuilder();
        StringBuilder updateBatchColProps = new StringBuilder();
        //resultMap
        Map<String, String> propJdbcTypes = info.getEntityInfo().getPropJdbcTypes();
        for (Entry<String, String> entry : info.getEntityInfo().getPropNameColumnNames().entrySet()) {
            Name name = new Name(resultMap, baseColumn, updateColProps, updateBatchColProps, propJdbcTypes, entry).invoke();
            String propName = name.getPropName();
            String columnName = name.getColumnName();

            if (!("updated".equals(propName)) && !("updatedby".equals(propName))) {
                /**
                 * <if test="id != null">
                     ID,
                   </if>
                 */
                insertIfColumns.append("      <if test=\"").append(propName).append(" != null\">\r\n        ")
                    .append(columnName).append(",\r\n").append("      </if>\r\n");
                /**
                 * <if test="id != null">
                    #{id,jdbcType=BIGINT},
                  </if>
                 */
                insertIfProps.append("      <if test=\"").append(propName).append(" != null\">\r\n        #{")
                .append(propName).append(",jdbcType=").append(propJdbcTypes.get(propName)).append("},\r\n").append("      </if>\r\n");
                
                insertBatchColumns.append(columnName).append(",");
                
                /**
                 * #{item.id,jdbcType=BIGINT},
                 */
                insertBatchProps.append("#{item.")
                .append(propName).append(",jdbcType=").append(propJdbcTypes.get(propName)).append("},");
            }
        }
        this.param.put("resultMap", resultMap.toString());
        this.param.put("baseColumn", baseColumn.substring(0, baseColumn.length() - 1));
        this.param.put("insertIfColumns", insertIfColumns.toString());
        this.param.put("insertIfProps", insertIfProps.toString());
        this.param.put("insertBatchColumns", insertBatchColumns.substring(0, insertBatchColumns.length() - 1));
        this.param.put("insertBatchProps", insertBatchProps.substring(0, insertBatchProps.length() - 1));
        this.param.put("updateColProps", updateColProps.toString());
        this.param.put("updateBatchColProps", updateBatchColProps.toString());
        this.param.put("pkPropName", info.getTableInfo().getPkInfo().getJavaColumnField());
        this.param.put("pkColumnType", info.getTableInfo().getPkInfo().getJavaColumnType());
    }

    private class Name {
        private StringBuilder resultMap;
        private StringBuilder baseColumn;
        private StringBuilder updateColProps;
        private StringBuilder updateBatchColProps;
        private Map<String, String> propJdbcTypes;
        private Entry<String, String> entry;
        private String propName;
        private String columnName;

        public Name(StringBuilder resultMap, StringBuilder baseColumn, StringBuilder updateColProps, StringBuilder updateBatchColProps, Map<String, String> propJdbcTypes, Entry<String, String> entry) {
            this.resultMap = resultMap;
            this.baseColumn = baseColumn;
            this.updateColProps = updateColProps;
            this.updateBatchColProps = updateBatchColProps;
            this.propJdbcTypes = propJdbcTypes;
            this.entry = entry;
        }

        public String getPropName() {
            return propName;
        }

        public String getColumnName() {
            return columnName;
        }

        public Name invoke() {
            propName = entry.getKey();
            columnName = entry.getValue();

            if (!("id".equals(propName))) {
                resultMap.append("    <result column=\"").append(columnName).append("\" jdbcType=\"")
                .append(propJdbcTypes.get(propName)).append("\" property=\"").append(propName)
                .append("\" />\r\n");

                if ((!("created".equals(propName))) && !("createdby".equals(propName))) {
                    /**
                     * <if test="code != null">
                        CODE = #{code,jdbcType=VARCHAR},
                      </if>
                     */
                    updateColProps.append("      <if test=\"").append(propName).append(" != null\">\r\n        ").append(columnName).append("=#{")
                    .append(propName).append(",jdbcType=").append(propJdbcTypes.get(propName)).append("},\r\n").append("      </if>\r\n");

                    /**
                     * <if test="isDelete != null">
                        IS_DELETE = #{item.isDelete,jdbcType=VARCHAR},
                      </if>
                     */
                    updateBatchColProps.append("        <if test=\"item.").append(propName).append(" != null\">\r\n          ").append(columnName).append("=#{item.")
                    .append(propName).append(",jdbcType=").append(propJdbcTypes.get(propName)).append("},\r\n").append("        </if>\r\n");
                }

            }
            baseColumn.append(columnName).append(",");
            return this;
        }
    }
}

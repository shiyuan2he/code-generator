package me.hsy.mybatis.generator.enhance.framework.context;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import me.hsy.mybatis.generator.enhance.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 应用程序上下文环境
 * @author heshiyuan
 */
@Data
public class ApplicationContext {
    /**
     * tableName, entityName
     */
    private Map<String, String> table2Entity;
    /**
     * tableName, TableInfo
     */
    Map<String, TableInfo> tableInfoMap;
    /**
     * tableName, tableRemark
     */
    Map<String, String> table2Desc;
    /**
     * 需要生成的实体
     */
    private List<EntityInfo> entityInfoList;
    /**
     * 需要生成的daoList
     */
    private List<DaoInfo> daoList;
    /**
     * voInfo
     */
    private List<VoInfo> voList;
    private List<MapperInfo> mapperInfoList;
    private List<DtoInfo> dtoInfoList;
    private List<ServiceInfo> serviceInfoList;
}

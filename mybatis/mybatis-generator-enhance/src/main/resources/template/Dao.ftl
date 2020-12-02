package ${packageStr};
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
${importStr}

/**
 * ${entityDesc}Dao
 *  @author ${author}
 *  @date  ${time}
 */
@Mapper
public interface ${className}{

    Integer insert${entityName}(${entityClassName} entity);
    
    Integer insertBatch(@Param("list") List<${entityClassName}> list);
    
    Integer update${entityName}(${entityClassName} entity);
    
    Integer updateBatch(@Param("list") List<${entityClassName}> list);
    
    Integer delete${entityName}(${entityClassName} entity);

    Integer deleteById(${pkColumnType} ${pkPropName});

    Integer deleteBatch(@Param("list") List<${pkColumnType}> list);

    List<${entityClassName}> findList(${entityClassName} entity);

    ${entityClassName} get${entityName}(${entityClassName} entity);

    ${entityClassName} findById(${pkColumnType} ${pkPropName});

    /**
     * 通用单条记录查询  根据实体名称和字段名称和字段值获取唯一记录
     */
    //${entityClassName} queryUniqueByProperty(@Param("propertyName") String propertyName, @Param("value") Object value);
}
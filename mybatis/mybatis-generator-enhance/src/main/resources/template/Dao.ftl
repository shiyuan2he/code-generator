package ${packageStr};
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
${importStr}

/**
 * ${entityDesc}Dao
 * <pre>
 *  @author ${author}
 *  @date  ${time}
 * </pre>
 */
@Mapper
public interface ${className}{

    Integer insert${entityName}(${entityClassName} entity);
    
    Integer insertBatch(@Param("list")List<${entityClassName}> list);
    
    Integer update${entityName}(${entityClassName} entity);
    
    Integer updateBatch(@Param("list")List<${entityClassName}> list);
    
    Integer delete${entityName}(${entityClassName} entity);
    
    Integer deleteBatch(@Param("list")List<${entityClassName}> list);
    
    List<${entityClassName}> findList(${entityClassName} entity);
    
    ${entityClassName} get${entityName}(${entityClassName} entity);

    /**
     * 通用单条记录查询  根据实体名称和字段名称和字段值获取唯一记录
     */
    //${entityClassName} queryUniqueByProperty(@Param("propertyName") String propertyName, @Param("value") Object value);
}
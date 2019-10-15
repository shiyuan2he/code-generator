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

    long insert${entityName}(${entityClassName} entity);
    
    Long insertBatch(@Param("list")List<${entityClassName}> list);
    
    long update${entityName}(${entityClassName} entity);
    
    long updateBatch(@Param("list")List<${entityClassName}> list);
    
    long delete${entityName}(${entityClassName} entity);
    
    long deleteBatch(@Param("list")List<${entityClassName}> list);
    
    List<${entityClassName}> findList(${entityClassName} entity);
    
    ${entityClassName} get${entityName}(${entityClassName} entity);
}
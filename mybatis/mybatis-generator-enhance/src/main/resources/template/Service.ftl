package ${packageStr};
import java.util.List;
import com.github.pagehelper.PageInfo;
${importStr}

/**
 * ${serviceDesc} 业务实现接口
 * @author ${author}
 * @date ${time}
 */
public interface ${className} {

    /**
     * findById
     * @param ${pkPropName} ${pkComment}
     * @return ${dtoName} 实体转换对象
     */
    ${dtoName} findById(${pkColumnType} ${pkPropName});

    /**
     * 理论上不应该存在此接口，所有的list返回都要做分页，方式巨型出参返回
     * findByParam
     * @param ${voClassNameToHump} ${serviceDesc}请求对象
     * @return ${dtoName} 实体转换对象
     */
    List<${dtoName}> findByParam(${voClassName} ${voClassNameToHump});

    /**
     * findPageListByParam
     * @param page  当前页数
     * @param limit 每页条数
     * @param sort 排序字段
     * @param ${voClassNameToHump} ${serviceDesc}请求对象
     * @return ${dtoName} 实体转换对象
     */
    PageInfo<List<${dtoName}>> findPageListByParam(Integer page, Integer limit, String sort, ${voClassName} ${voClassNameToHump});

    /**
     * create 单体创建
     * @param ${voClassNameToHump} ${serviceDesc}请求对象
     * @return Boolean 创建成功标识 true：成功 false：失败
     */
     Boolean create(${voClassName} ${voClassNameToHump});

    /**
     * create 单体创建
     * @param ${voClassNameToHumpList} ${serviceDesc}请求对象
     * @return ${dtoName} 实体转换对象
     */
    Integer create(List<${voClassName}> ${voClassNameToHumpList});

    /**
     * update 单体更新
     * @param ${voClassNameToHump} ${serviceDesc}请求对象
     * @return Boolean true:更新成功， false：更新失败
     */
    Boolean update(${voClassName} ${voClassNameToHump});

    /**
     * update 单体更新
     * @param ${voClassNameToHumpList} 批量更新实体list
     * @return Integer 批量更新的条数
     */
    Integer updateBatch(List<${voClassName}> ${voClassNameToHumpList});

    /**
     * delete 单体删除
     * @param ${pkPropName} ${pkComment}
     * @return Boolean true:删除成功， false：删除失败
     */
     Boolean delete(${pkColumnType} ${pkPropName});

    /**
     * delete 批量删除
     * @param ${pkPropNameList} ${pkComment}
     * @return Integer 删除成功的条数
     */
    Integer delete(List<${pkColumnType}> ${pkPropNameList});
}
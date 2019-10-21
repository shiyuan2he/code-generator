package ${packageStr};
import java.util.List;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
${importStr}

/**
 * ${serviceDesc} 业务实现类
 * @author ${author}
 * @date ${time}
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class ${className} implements ${serviceClassName} {

    @Autowired
    private ${entityClassName}Mapper ${entityClassNameToHump}Mapper;

    /**
     * findById
     * @param ${pkPropName} ${pkComment}
     * @return ${dtoName} 实体转换对象
     */
    ${dtoName} findById(${pkColumnType} ${pkPropName}){
        return ${dtoName}.convert(${entityClassNameToHump}Mapper.findById(${pkPropName}));
    }

    /**
     * findByParam
     * @param ${voClassNameToHump} ${serviceDesc}请求对象
     * @return ${dtoName} 实体转换对象
     */
    List<${dtoName}> findByParam(${voClassName} ${voClassNameToHump}){
        List<${dtoName}> list = Optional.ofNullable(
                ${entityClassNameToHump}Mapper.findList(${entityClassName}.convert(${voClassNameToHump})))
                .orElse(new ArrayList<>());
        return list.stream.map(entity -> ${dtoName}.convert(entity)).collect(Collectors.toList());
    }

    /**
     * findPageListByParam
     * @param ${voClassNameToHump} ${serviceDesc}请求对象
     * @return ${dtoName} 实体转换对象
     */
    PageInfo<List<${dtoName}>> findPageListByParam(Integer page, Integer limit, String sort, ${voClassName} ${voClassNameToHump}){
        PageHelper.startPage(page, limit, sort);
        List<${dtoName}> list = Optional.ofNullable(
                ${entityClassNameToHump}Mapper.findList(${entityClassName}.convert(${voClassNameToHump})))
                .orElse(new ArrayList<>());
        return new PageInfo(list.stream.map(entity -> ${dtoName}.convert(entity)).collect(Collectors.toList()));
    }

    /**
     * create 单体创建
     * @param ${voClassNameToHump} ${serviceDesc}请求对象
     * @return Boolean 创建成功标识 true：成功 false：失败
     */
    Boolean create(${voClassName} ${voClassNameToHump}){
        if(1 == ${entityClassNameToHump}Mapper.insert${entityClassName}(${entityClassName}.convert(${voClassNameToHump}))){
            return true;
        }
        return false;
    }

    /**
     * create 批量创建
     * @param ${voClassNameToHumpList} ${serviceDesc}请求对象
     * @return ${dtoName} 实体转换对象
     */
    Integer create(List<${voClassName}> ${voClassNameToHumpList}){
        return ${entityClassNameToHump}Mapper.insertBatch(
               ${voClassNameToHumpList}.stream.map(entity ->
                    ${dtoName}.convert(entity)).collect(Collectors.toList())
        ;
    }

    /**
     * update 单体更新
     * @param ${voClassNameToHump} ${serviceDesc}请求对象
     * @return Boolean true:更新成功， false：更新失败
     */
    Boolean update(${voClassName} ${voClassNameToHump}){
        if(1 == ${entityClassNameToHump}Mapper.update${entityClassName}(${entityClassName}.convert(${voClassNameToHump}))){
            return true;
        }
        return false;
    }

    /**
     * update 单体更新
     * @param ${voClassNameToHumpList} 批量更新实体list
     * @return Integer 批量更新的条数
     */
    Integer updateBatch(List<${voClassName}> ${voClassNameToHumpList}){
        return ${entityClassNameToHump}Mapper.updateBatch(
                ${voClassNameToHumpList}.stream.map(entity ->
                ${dtoName}.convert(entity)).collect(Collectors.toList())
        ;
    }

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
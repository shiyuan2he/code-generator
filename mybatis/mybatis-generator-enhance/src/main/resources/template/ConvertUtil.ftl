package ${packageStr};
import org.springframework.beans.BeanUtils;
${importStr}

/**
 * ${tableComment} JavaBean转换工具
 *  @author ${author}
 *  @date  ${time}
 */
public class ${className}{

    public static ${entityClassName} convert(${voClassName} ${voClassNameToHump}){
        ${entityClassName} entity = new ${entityClassName}();
        if(null != ${voClassNameToHump}){
            BeanUtils.copyProperties(${voClassNameToHump}, entity);
        }
        return entity;
    }

    public static ${dtoClassName} convert(${entityClassName} ${entityClassNameToHump}){
        ${dtoClassName} dto = new ${dtoClassName}();
        if(null!=${entityClassNameToHump}){
            BeanUtils.copyProperties(${entityClassNameToHump}, dto);
        }
        return dto;
    }
}
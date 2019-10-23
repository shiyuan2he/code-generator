package ${packageStr};
import org.springframework.beans.BeanUtils;
${importStr}

/**
 * ${tableComment}实体
 * <pre>
 *  @author ${author}
 *  @date  ${time}
 * </pre>
 */
public class ${className} implements Serializable {
    public static ${className} convert(${voClassName} ${voClassNameToHump}){
        ${className} entity = new ${className}();
        BeanUtils.copyProperties(${voClassNameToHump}, entity);
        return entity;
    }
}
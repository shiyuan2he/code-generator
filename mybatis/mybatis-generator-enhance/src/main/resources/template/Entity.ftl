package ${packageStr};
import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
${importStr}

/**
 * ${entityDesc}实体
 * <pre>
 *  @author ${author}
 *  @date  ${time}
 * </pre>
 */
@Data
@ToString
public class ${className} implements Serializable {
    private static final long serialVersionUID = 1L;
    ${propertiesStr}
    ${methodStr}

    public static ${className} convert(${voClassName} ${voClassNameToHump}){
        ${className} entity = new ${className};
        BeanUtils.copyProperties(${voClassNameToHump}, entity);
        return entity;
    }
}
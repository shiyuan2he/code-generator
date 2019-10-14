package ${packageStr};
import java.io.Serializable;
${importStr}

/**
 * 
 * ${entityDesc}实体
 * 
 * @version 
 * <pre>
 *  Author ${author}
 *  Date  ${time}
 * </pre>
 */
@Data
@ToString
public class ${className} implements Serializable {
    private static final long serialVersionUID = 1L;
    ${propertiesStr}
    ${methodStr}
}
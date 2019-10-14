package ${packageStr};
import java.io.Serializable;
${importStr}

/**
 * ${entityDesc}vo
 * <pre>
 *  Author ${author}
 *  Date  ${time}
 * </pre>
 */
public class ${className}Vo implements Serializable {
    private static final long serialVersionUID = 1L;
    ${propertiesStr}
    ${methodStr}
}
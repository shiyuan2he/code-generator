package ${packageStr};
import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
/**
 * ${entityDesc}vo
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
}
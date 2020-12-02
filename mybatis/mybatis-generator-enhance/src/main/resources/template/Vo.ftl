package ${packageStr};
import java.io.Serializable;
import lombok.Data;
import lombok.ToString;
/**
 * ${entityDesc}vo
 *  @author ${author}
 *  @date  ${time}
 */
@Data
@ToString
public class ${className} implements Serializable {
    private static final long serialVersionUID = 1L;
    ${propertiesStr}
}
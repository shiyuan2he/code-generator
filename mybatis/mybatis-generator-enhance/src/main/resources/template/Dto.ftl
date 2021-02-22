package ${packageStr};
${importStr}
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
<#if needToJsonString>
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
</#if>
/**
 *  @author ${author}
 *  @date  ${time}
 */
@Data
@ToString
public class ${className} implements Serializable {
<#if columnList??>
<#list columnList as column>
    <#if column.columnComment != ''>
    /**
     * ${column.columnComment}
     */
    </#if>
    <#if column.propType = 'Long'>
    @JsonSerialize(using= ToStringSerializer.class)
    </#if>
    private ${column.propType} ${column.propName};
</#list>
</#if>
}
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
    // 处理精度丢失问题
    @JsonSerialize(using= ToStringSerializer.class)
    </#if>
    private ${column.propType} ${column.propName};
</#list>
</#if>

    public static ${className} convert(${entityClassName} ${entityClassNameToHump}){
        ${className} dto = new ${className};
        BeanUtils.copyProperties(${entityClassNameToHump}, dto);
        return dto;
    }
}
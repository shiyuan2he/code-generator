package ${packageStr};
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
${importStr}
/**
 * @author ${author}
 * @date ${time}
 */
@Api(tags = {"${entityName}"})
@RestController
@RequestMapping("/${humpEntityName}")
public class ${className} {

    @Autowired
    private I${entityName}Service ${humpEntityName}Service;

    @ApiOperation(value="查询", notes="查询接口")
    @GetMapping
    public ResponseEntity<${dtoName}> get(@RequestParam ${pkColumnType} ${pkPropName}){
        return new ResponseEntity(${humpEntityName}Service.findById(${pkPropName}), HttpStatus.OK);
    }

    @ApiOperation(value="列表查询", notes="列表查询接口")
    @GetMapping(value = "/list")
    public ResponseEntity list(@RequestBody ${voClassName} ${voClassNameToHump}){
        return new ResponseEntity(${humpEntityName}Service.findByParam(${voClassNameToHump}), HttpStatus.OK);
    }

    @ApiOperation(value="单个创建", notes="单个创建接口")
    @PostMapping
    public ResponseEntity<Boolean> create(@Validated @RequestBody ${voClassName} ${voClassNameToHump}){
        return new ResponseEntity(${humpEntityName}Service.create(${voClassNameToHump}), HttpStatus.CREATED);
    }

    @ApiOperation(value="单个更新", notes="单个更新接口")
    @PutMapping
    public ResponseEntity<Boolean> update(@Validated @RequestBody ${voClassName} ${voClassNameToHump}){
        return new ResponseEntity(${humpEntityName}Service.update(${voClassNameToHump}), HttpStatus.OK);
    }

    @ApiOperation(value="单个删除", notes="单个删除接口")
    @DeleteMapping
    public ResponseEntity<Boolean> delete(@PathVariable ${pkColumnType} ${pkPropName}){
        return new ResponseEntity(${humpEntityName}Service.delete(${pkPropName}), HttpStatus.OK);
    }

    @ApiOperation(value="批量删除", notes="批量删除接口")
    @DeleteMapping(value = "/batch")
    public ResponseEntity<Integer> delete(@RequestParam List<${pkColumnType}> ${pkPropNameList}){
        return new ResponseEntity(${humpEntityName}Service.delete(${pkPropNameList}), HttpStatus.OK);
    }
}
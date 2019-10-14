package ${package}.service;

import ${package}.domain.${className};
import ${package}.service.dto.${className}DTO;

/**
 * @author ${author}
 * @date ${date}
 */
@CacheConfig(cacheNames = "${changeClassName}")
public interface ${className}Service {

    /**
     * findById
     * @param ${pkChangeColName}
     * @return
     */
    ${className}DTO findById(${pkColumnType} ${pkChangeColName});

    /**
     * create
     * @param resources
     * @return
     */
    ${className}DTO create(${className} resources);

    /**
     * update
     * @param resources
     */
    void update(${className} resources);

    /**
     * delete
     * @param ${pkChangeColName}
     */
    void delete(${pkColumnType} ${pkChangeColName});
}
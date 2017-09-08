package ${packageStr};
import java.util.List;
import java.util.Map;

${importStr}

/**
 * 
 * ${entityDesc}Service
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * ${author} 	1.0  		${time} 	Created
 *
 * </pre>
 * @since 1.
 */
public interface ${className} {

    List<${entityClassName}> query${entityName}ListPage(Map map);
    
    List<${entityClassName}> query${entityName}List(Map map);
    
    ${entityClassName} query${entityName}First(${entityClassName} entity);
    int insert${entityName}(${entityClassName} entity);
    
    long insert${entityName}Batch(List<${entityClassName}> list);
    
    int update${entityName}(${entityClassName} entity);
    
    long update${entityName}Batch(List<${entityClassName}> list);
    
    int delete${entityName}(${entityClassName} entity);
    
    long delete${entityName}Batch(List<${entityClassName}> list);
    
}
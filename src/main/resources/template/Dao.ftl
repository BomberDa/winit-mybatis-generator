package ${packageStr};
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

import com.winit.common.orm.mybatis.MyBatisPageRepo;
import com.winit.common.orm.mybatis.MyBatisRepo;
import com.winit.common.orm.mybatis.PageBase;
${importStr}

/**
 * 
 * ${entityDesc}Dao
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
    int insert${entityName}(${entityClassName} entity);
    
    long insert${entityName}Batch(@Param("list")List<${entityClassName}> list);
    
    int update${entityName}(${entityClassName} entity);
    
    long update${entityName}Batch(@Param("list")List<${entityClassName}> list);
    
    int delete${entityName}(${entityClassName} entity);
    
    long delete${entityName}Batch(@Param("list")List<${entityClassName}> list);
    
    List<${entityClassName}> query${entityName}ListPage(Map map);
    
    List<${entityClassName}> query${entityName}List(Map map);
    
    ${entityClassName} query${entityName}First(${entityClassName} entity);
}
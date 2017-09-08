package ${packageStr};
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

${importStr}

/**
 * 
 * ${entityDesc}ServiceImpl
 * 
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * ${author} 	1.0  		${time} 	Created
 *
 * </pre>
 * @since 1.
 */
@Service("${entityObjName}Service")
public class ${className} implements ${serviceClassName} {
	@Autowired
	private ${daoClassName} ${daoObjName};
	
	public List<${entityClassName}> query${entityName}ListPage(Map map) {
		return this.${daoObjName}.query${entityName}ListPage(map);
	}

	public List<${entityClassName}> query${entityName}List(Map map) {
		return this.${daoObjName}.query${entityName}List(map);
	}

	public ${entityClassName} query${entityName}First(${entityClassName} entity) {
		// TODO Auto-generated method stub
		return null;
	}

	public int insert${entityName}(${entityClassName} entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	public long insert${entityName}Batch(List<${entityClassName}> list) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update${entityName}(${entityClassName} entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	public long update${entityName}Batch(List<${entityClassName}> list) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete${entityName}(${entityClassName} entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	public long delete${entityName}Batch(List<${entityClassName}> list) {
		// TODO Auto-generated method stub
		return 0;
	}
    
}
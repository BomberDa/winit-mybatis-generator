package ${packageStr};

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.lobster.common.util.BeanUtil;
import com.lobster.common.util.page.PageParameter;

${importStr}  
/**
 * 
 * ${entityDesc}Controller
 * @version 
 * <pre>
 * Author	Version		Date		Changes
 * ${author} 	1.0  		${time} 	Created
 * </pre>
 * @since 1.
 */
  
@Controller  
@RequestMapping("/${entityNameLower}")  
public class ${className} {  
    private static Logger log=LoggerFactory.getLogger(${className}.class);
      
    @Autowired  
    private ${serviceClassName} ${serviceObjName};  

   /**
    * 获取页面
    */   
    @RequestMapping(value="/getListPage",method=RequestMethod.GET)  
    public ModelAndView getListPage(HttpServletRequest request,HttpServletResponse response){  
		ModelAndView mav=new ModelAndView("platform/${entityNameLower}/query${entityName}List");
		return mav; 
    }  
    @RequestMapping(value="/getAddPage",method=RequestMethod.GET)  
    public ModelAndView getAddPage(HttpServletRequest request,HttpServletResponse response){  
		ModelAndView mav=new ModelAndView("platform/${entityNameLower}/add${entityName}Record");
		return mav; 
    }  
    @RequestMapping(value="/getUpdatePage",method=RequestMethod.GET)  
    public ModelAndView getUpdatePage(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav=new ModelAndView("platform/${entityNameLower}/query${entityName}Detail");
		return mav; 
    }  
    
   /**
    * 获取数据
    */       
    @RequestMapping(value="/queryList",produces="application/json;charset=UTF-8",method=RequestMethod.GET) 
    @ResponseBody
    public Map queryList(@RequestParam Map map){  
    	System.out.println("进入queryList方法");
    	int currentPage = Integer.parseInt((String)map.get("page"));
    	int pageSize    = Integer.parseInt((String)map.get("rows"));  	
        PageParameter page = new PageParameter(currentPage,pageSize);
    	map.put("page", page);
        Map result  = ${serviceObjName}.query${entityName}ListPage(map);        
		return result; 
    }  
    
    @RequestMapping(value="/queryDetail",produces="application/json;charset=UTF-8",method=RequestMethod.GET) 
    @ResponseBody
    public ${entityClassName} queryDetail(@RequestParam Map map){  
    	System.out.println("进入queryDetail方法");
    	map.put("${keyName}", map.get("key"));
    	${entityClassName} entity = ${serviceObjName}.query${entityName}First(map);
        return entity; 
    }  
    
    @RequestMapping(value="/addFormRecord",consumes="application/json;charset=UTF-8",method=RequestMethod.POST) 
    @ResponseBody
    public Map addFormRecord(@RequestBody Map map) throws Exception{  
    	System.out.println("进入addFormRecord方法");
    	Map result  = new HashMap();  
    	${entityClassName} entity = new ${entityClassName}();
    	int flag=0;
    	try {       	
        	BeanUtil.transMap2Bean(map, entity);
        	 flag = ${serviceObjName}.insert${entityName}(entity);           		
    	}catch(Exception e) {
    		throw e;
    	}finally {
            if(flag>0) {
            	result.put("flag", "success");
            	result.put("key", entity.${keyGet});
            }else {
            	result.put("flag", "false");
            }   		
    	}       
		return result; 
    }  
    
    //consumes="application/json;charset=UTF-8", @RequestBody 
    @RequestMapping(value="/saveFormRecord",consumes="application/json;charset=UTF-8",method=RequestMethod.POST) 
    @ResponseBody
    public Map saveFormRecord(@RequestBody Map map) throws Exception{  
    	System.out.println("进入saveFormRecord方法");
    	Map result  = new HashMap();  
    	${entityClassName} entity = new ${entityClassName}();
    	int flag=0;
    	try {       	
        	BeanUtil.transMap2Bean(map, entity);
        	 flag = ${serviceObjName}.update${entityName}(entity);           		
    	}catch(Exception e) {
    		throw e;
    	}finally {
            if(flag>0) {
            	result.put("flag", "success");
            	result.put("key", entity.${keyGet});
            }else {
            	result.put("flag", "false");
            }   		
    	}       
		return result; 
    }  

    @RequestMapping(value="/deleteRecord",produces="application/json;charset=UTF-8",method=RequestMethod.GET) 
    @ResponseBody
    public Map deleteRecord(@RequestParam Map map) throws Exception{  
    	System.out.println("进入deleteRecord方法");
    	Map result  = new HashMap();  
    	map.put("${keyName}", map.get("key"));
    	int flag=0;
    	try {       	
        	 flag = ${serviceObjName}.delete${entityName}(map);          		
    	}catch(Exception e) {
    		throw e;
    	}finally {
            if(flag>0) {
            	result.put("flag", "success");
            }else {
            	result.put("flag", "false");
            }   		
    	}       
		return result; 
    }         
}
package com.winit.generator.handler.impl.jsp;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import com.winit.generator.config.Configuration;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.model.EntityInfo;
import com.winit.generator.model.JspInfo;
import com.winit.generator.util.StringUtil;

public class ListJspHandler extends BaseHandler<JspInfo> {
    
    public ListJspHandler(String ftlName, JspInfo info) {
        this.ftlName = ftlName;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir") 
                + File.separator + Configuration.getString("listJsp.path")
                + File.separator + "query"+info.getClassName() + "List.jsp";
        
    }
    
    @Override
    public void combileParams(JspInfo jspInfo) {
        StringBuilder sbParam = new StringBuilder();
        StringBuilder sbColumn = new StringBuilder();
        this.param.put("className", jspInfo.getClassName());
        this.param.put("entityNameLower", info.getEntityInfo().getEntityName().toLowerCase());
        //生成属性，table列表
        Map<String, String> propRemarks = jspInfo.getEntityInfo().getPropRemarks();
        for (Entry<String, String> entry : jspInfo.getEntityInfo().getPropNameColumnNames().entrySet()) {
            String propName = entry.getKey();
            String columnName = entry.getValue().toUpperCase();
            
            //<ex:text id="USR_CDE" label="客户编号"  />
            sbColumn.append("				<ex:text id=\"").append(columnName)
            .append("\" label=\"").append(propRemarks.get(propName))
            .append("\" />\r\n");
        }
        if(info.getEntityInfo().getKeyList()!=null&&info.getEntityInfo().getKeyList().size()>0) {
        	String keyName = info.getEntityInfo().getKeyList().get(0);
        	String keyGet = StringUtil.convertFieldName2PropName("get_"+keyName.toLowerCase())+"()";
            this.param.put("keyName", keyName);       
            this.param.put("keyGet", keyGet);  
        }else {
        	 this.param.put("keyName", "key");     
        	 this.param.put("keyGet", "");
        }        

        this.param.put("columnStr", sbColumn.toString());        
        
    }
}

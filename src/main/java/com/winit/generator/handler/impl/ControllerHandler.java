package com.winit.generator.handler.impl;

import java.io.File;

import com.winit.generator.config.Configuration;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.model.ControllerInfo;
import com.winit.generator.model.ServiceImplInfo;
import com.winit.generator.util.StringHelper;
import com.winit.generator.util.StringUtil;


public class ControllerHandler extends BaseHandler<ControllerInfo> {
    public ControllerHandler(String ftlName, ControllerInfo info) {
        this.ftlName = ftlName;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir") 
                + File.separator + Configuration.getString("controller.path")
                + File.separator + info.getClassName() + ".java";
        
    }
    
    @Override
    public void combileParams(ControllerInfo info) {
        this.param.put("packageStr", info.getPackageStr());
        this.param.put("importStr", info.getImportStr());
        String tablename = info.getEntityInfo().getEntityName().toLowerCase();
        this.param.put("entityNameLower", tablename);
        this.param.put("className", info.getClassName());
        this.param.put("serviceClassName", info.getServiceInfo().getClassName());  
        this.param.put("serviceObjName", StringHelper.toLowOneChar(info.getServiceInfo().getClassName(),0));
        this.param.put("entityName", info.getEntityInfo().getEntityName());
        this.param.put("entityClassName", info.getEntityInfo().getClassName());
        this.param.put("entityDesc", info.getEntityInfo().getEntityDesc());
        if(info.getEntityInfo().getKeyList()!=null&&info.getEntityInfo().getKeyList().size()>0) {
        	String keyName = info.getEntityInfo().getKeyList().get(0);
        	String keyGet = StringUtil.convertFieldName2PropName("get_"+keyName.toLowerCase())+"()";
            this.param.put("keyName", keyName);       
            this.param.put("keyGet", keyGet);  
        }else {
        	 this.param.put("keyName", "key");     
        	 this.param.put("keyGet", "");
        }        
                
    }


}

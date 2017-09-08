package com.winit.generator.handler.impl;

import java.io.File;

import com.winit.generator.config.Configuration;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.model.ServiceImplInfo;
import com.winit.generator.model.ServiceInfo;
import com.winit.generator.util.StringHelper;


public class ServiceImplHandler extends BaseHandler<ServiceImplInfo> {
    public ServiceImplHandler(String ftlName, ServiceImplInfo info) {
        this.ftlName = ftlName;
        this.info = info;
        this.savePath = Configuration.getString("base.baseDir") 
                + File.separator + Configuration.getString("serviceImpl.path")
                + File.separator + info.getClassName() + ".java";
        
    }
    
    @Override
    public void combileParams(ServiceImplInfo info) {
        this.param.put("packageStr", info.getPackageStr());
        this.param.put("importStr", info.getImportStr());
        this.param.put("entityDesc", info.getEntityInfo().getEntityDesc());
        this.param.put("className", info.getClassName());
        this.param.put("serviceClassName", info.getServiceInfo().getClassName());  
        this.param.put("daoClassName", info.getDaoInfo().getClassName()); 
        this.param.put("daoObjName", StringHelper.toLowOneChar(info.getDaoInfo().getClassName(),0));
        this.param.put("entityClassName", info.getEntityInfo().getClassName());
        this.param.put("entityObjName", StringHelper.toLowOneChar(info.getEntityInfo().getEntityName(), 0));
        this.param.put("entityName", info.getEntityInfo().getEntityName());
    }


}

package com.winit.generator.task;

import java.util.ArrayList;
import java.util.List;

import com.winit.generator.Constants;
import com.winit.generator.config.Configuration;
import com.winit.generator.framework.AbstractApplicationTask;
import com.winit.generator.framework.context.ApplicationContext;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.handler.impl.ServiceImplHandler;
import com.winit.generator.model.ControllerInfo;
import com.winit.generator.model.DaoInfo;
import com.winit.generator.model.EntityInfo;
import com.winit.generator.model.ServiceImplInfo;
import com.winit.generator.model.ServiceInfo;

public class ServiceImplTask extends AbstractApplicationTask {
    private static String SERVICEIMPL_FTL = "template/ServiceImpl.ftl";
    
    private List<ServiceImplInfo> implInfos;
    
    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成serviceImpl");
        
        implInfos = (List<ServiceImplInfo>) context.getAttribute("serviceImplList");
        
        BaseHandler<ServiceImplInfo> handler = null;
        for (ServiceImplInfo implInfo : implInfos) {
            handler = new ServiceImplHandler(SERVICEIMPL_FTL, implInfo);
            handler.execute();
        }
        
        logger.info("生成serviceImpl完成");
        return false;
    }
    
    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);
        List<ControllerInfo> controllerInfos = new ArrayList<ControllerInfo>();
        List<ServiceInfo> serviceInfos = (List<ServiceInfo>) context.getAttribute("serviceList");
        ControllerInfo controllerInfo = null;
        if(serviceInfos!=null&&serviceInfos.size()>0) {
        	for(int i=0;i<serviceInfos.size();i++) {        		
        		ServiceInfo serviceInfo = serviceInfos.get(i);
        		EntityInfo entityInfo = serviceInfo.getEntityInfo();
        		
        		controllerInfo = new ControllerInfo();
        		controllerInfo.setClassName(entityInfo.getEntityName()+Constants.CONTROLLER_SUFFIX);
            	controllerInfo.setServiceInfo(serviceInfo);
            	controllerInfo.setEntityInfo(entityInfo);
            	String importStr = "import "+ entityInfo.getEntityPackage() + "." + entityInfo.getClassName() + ";\n";
            	       importStr += "import "+ serviceInfo.getPackageStr() + "." + serviceInfo.getClassName() + ";\n";
            	controllerInfo.setImportStr(importStr);
            	controllerInfo.setPackageStr(Configuration.getString("controller.package"));
            	controllerInfos.add(controllerInfo);
        		
        	}
        }
        context.setAttribute("controllerInfoList", controllerInfos);        
    }

}

package com.winit.generator.task;

import java.util.ArrayList;
import java.util.List;

import com.winit.generator.Constants;
import com.winit.generator.config.Configuration;
import com.winit.generator.framework.AbstractApplicationTask;
import com.winit.generator.framework.context.ApplicationContext;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.handler.impl.ServiceHandler;
import com.winit.generator.model.DaoInfo;
import com.winit.generator.model.EntityInfo;
import com.winit.generator.model.ServiceImplInfo;
import com.winit.generator.model.ServiceInfo;

public class ServiceTask extends AbstractApplicationTask {
    private static String SERVICE_FTL = "template/Service.ftl";
    
    private List<ServiceInfo> serviceInfos;
    
    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成service");
        
        serviceInfos = (List<ServiceInfo>) context.getAttribute("serviceList");
        
        BaseHandler<ServiceInfo> handler = null;
        for (ServiceInfo serviceInfo : serviceInfos) {
            handler = new ServiceHandler(SERVICE_FTL, serviceInfo);
            handler.execute();
        }
        
        logger.info("生成service完成");
        return false;
    }
    
    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);
        List<ServiceImplInfo> implInfos = new ArrayList<ServiceImplInfo>();
        List<ServiceInfo> serviceInfos = (List<ServiceInfo>) context.getAttribute("serviceList");
        List<DaoInfo> daoInfos = (List<DaoInfo>) context.getAttribute("daoList");
        ServiceImplInfo implInfo = null;
        if(daoInfos!=null&&serviceInfos!=null&&daoInfos.size()==serviceInfos.size()) {
        	for(int i=0;i<serviceInfos.size();i++) {
        		
        		ServiceInfo serviceInfo = serviceInfos.get(i);
        		DaoInfo daoInfo = daoInfos.get(i);
        		EntityInfo entityInfo = serviceInfo.getEntityInfo();
        		
        		implInfo = new ServiceImplInfo();
        		implInfo.setClassName(entityInfo.getEntityName()+Constants.SERVICE_IMPL_SUFFIX);
            	implInfo.setDaoInfo(daoInfo);
            	implInfo.setServiceInfo(serviceInfo);
            	implInfo.setEntityInfo(entityInfo);
            	String importStr = "import "+ entityInfo.getEntityPackage() + "." + entityInfo.getClassName() + ";\n";
            	       importStr += "import "+ serviceInfo.getPackageStr() + "." + serviceInfo.getClassName() + ";\n";
            	       importStr += "import "+ daoInfo.getPackageStr() + "." + daoInfo.getClassName() + ";\n";            	
            	implInfo.setImportStr(importStr);
            	implInfo.setPackageStr(Configuration.getString("serviceImpl.package"));
            	implInfos.add(implInfo);
        		
        	}
        }
        context.setAttribute("serviceImplList", implInfos);        
    }

}

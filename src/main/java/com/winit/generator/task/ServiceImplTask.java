package com.winit.generator.task;

import java.util.List;

import com.winit.generator.framework.AbstractApplicationTask;
import com.winit.generator.framework.context.ApplicationContext;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.handler.impl.ServiceImplHandler;
import com.winit.generator.model.ServiceImplInfo;

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
    }

}

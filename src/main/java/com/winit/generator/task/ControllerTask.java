package com.winit.generator.task;

import java.util.ArrayList;
import java.util.List;

import com.winit.generator.Constants;
import com.winit.generator.config.Configuration;
import com.winit.generator.framework.AbstractApplicationTask;
import com.winit.generator.framework.context.ApplicationContext;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.handler.impl.ControllerHandler;
import com.winit.generator.handler.impl.ServiceImplHandler;
import com.winit.generator.model.ControllerInfo;
import com.winit.generator.model.DaoInfo;
import com.winit.generator.model.EntityInfo;
import com.winit.generator.model.ServiceImplInfo;
import com.winit.generator.model.ServiceInfo;

public class ControllerTask extends AbstractApplicationTask {
    private static String CONTROLLER_FTL = "template/Controller.ftl";
    
    private List<ControllerInfo> controllerInfos;
    
    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成Controller");
        
        controllerInfos = (List<ControllerInfo>) context.getAttribute("controllerInfoList");
        
        BaseHandler<ControllerInfo> handler = null;
        for (ControllerInfo controllerInfo : controllerInfos) {
            handler = new ControllerHandler(CONTROLLER_FTL, controllerInfo);
            handler.execute();
        }
        
        logger.info("生成controller完成");
        return false;
    }
    
    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);
    }

}

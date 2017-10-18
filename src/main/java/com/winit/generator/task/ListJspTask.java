package com.winit.generator.task;

import java.util.List;

import com.winit.generator.framework.AbstractApplicationTask;
import com.winit.generator.framework.context.ApplicationContext;
import com.winit.generator.handler.BaseHandler;
import com.winit.generator.handler.impl.jsp.ListJspHandler;
import com.winit.generator.model.ControllerInfo;
import com.winit.generator.model.JspInfo;

public class ListJspTask extends AbstractApplicationTask {
    private static String LISTJSP_FTL = "template/jsp/List.ftl";
    
    private List<JspInfo> jspInfos;
    
    @SuppressWarnings("unchecked")
    @Override
    protected boolean doInternal(ApplicationContext context) throws Exception {
        logger.info("开始生成ListJsp");
        
        jspInfos = (List<JspInfo>) context.getAttribute("jspInfoList");
        
        BaseHandler<JspInfo> handler = null;
        for (JspInfo jspInfo : jspInfos) {
            handler = new ListJspHandler(LISTJSP_FTL, jspInfo);
            handler.execute();
        }
        
        logger.info("生成ListJsp完成");
        return false;
    }
    
    @Override
    protected void doAfter(ApplicationContext context) throws Exception {
        super.doAfter(context);
    }

}

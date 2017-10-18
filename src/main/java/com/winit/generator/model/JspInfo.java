package com.winit.generator.model;


public class JspInfo {
    /**
     * 类名
     */
    private String className;
    
    /**
    * 实体信息
    */
    private EntityInfo entityInfo;
    
    public String getClassName() {
        return className;
    }

    
    public void setClassName(String className) {
        this.className = className;
    }

    
    public EntityInfo getEntityInfo() {
        return entityInfo;
    }

    
    public void setEntityInfo(EntityInfo entityInfo) {
        this.entityInfo = entityInfo;
    }
    
}

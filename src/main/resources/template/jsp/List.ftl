<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/emp-jquery.tld" prefix="ex"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
<!DOCTYPE html>
<%@ include file="/EUIInclude.jsp"%>
<ex:page>
<html>
<head>
 <base href="<%=basePath%>">
</head>
<body>
	<div style="margin:20px 0;"></div>
	<div style="margin:20px 0;"></div>
	   <ex:panel id="" title="New Topic"  style="width:700px" >
		<div style="padding:10px 60px 20px 60px">
		    <form id="ff" class="easyui-form" method="post" data-options="novalidate:true">
	    			${queryParamStr}  	    			
	        </form>
	    	<div style="text-align:center;padding:5px">
	    	<ex:button id="query" text="查询" style="margin-right:20px"/>
	    	<ex:button id="clearForm" text="清空"  />
	    	</div>
	    </div>
	   
	   </ex:panel>
	<br/>
	<div id="toolBar"  style="padding:5px 0;">
		<ex:button id="add" text="新增" iconCls="icon-mini-add"/>
		<ex:button id="edit" text="编辑" iconCls="icon-mini-edit"/>
		<ex:button id="delete" text="删除" iconCls="icon-mini-delete"/>
		<ex:button id="view" text="查看" iconCls="icon-search"/>
	</div>
	
<!-- 		<table id="dg" style="width:700px;height:400px" -->
<!-- 			data-options="rownumbers:true,singleSelect:true,pagination:true,url:'${entityNameLower}/queryList',method:'get',toolbar:toolBar"> -->	

	<ex:table id="dg" style="width:700px;height:400px" url="${entityNameLower}/queryList" toolbar="toolBar"
			dataOptions="rownumbers:true,singleSelect:true,pagination:true,method:'get'">
${columnStr}
	</ex:table>
    <div id="w" class="easyui-window" data-options="title:'Inline Window',inline:true"  closed="true" style="width:650px;height:550px;padding:10px">
			This window stay inside its parent
	</div>
<script type="text/javascript">
$(function(){
})

 function doQuery(){
	  var param  = EMP.searchParams('ff');
	  $('#dg').datagrid('load',param);
	}
 function doAdd(){
	    $('#w').window({width:'650px',height:'550px',modal:true});
	    $('#w').window('open');
	    $('#w').window('refresh', '${entityNameLower}/getAddPage');  
	   // window.open ( "${entityNameLower}/getpage" , "_blank" ,"height=700,width=500");
 }
 function doEdit(){
	 var row = $('#dg').datagrid('getSelected');
	    $('#w').window({width:'650px',height:'550px',modal:true});
	    $('#w').window('open');
	    $('#w').window('refresh', '${entityNameLower}/getUpdatePage?key='+row.${keyName});  
 }
 function doView(){
	 var row = $('#dg').datagrid('getSelected');
	    $('#w').window({width:'650px',height:'550px',modal:true});
	    $('#w').window('open');
	    $('#w').window('refresh', '${entityNameLower}/getUpdatePage?view=view&key='+row.${keyName});  
 } 
 function doDelete(){
	 var row = $('#dg').datagrid('getSelected');
	 $.ajax({
		  url: "${entityNameLower}/deleteRecord?key="+row.${keyName}, 
		  type: "GET", 
		  dataType: "json",
		  success: function(data, textStatus){
				alert("删除成功！");
				query();
				}, 
		   error: function (data, textStatus, errorThrown) { 
				},		
		});
 }
 
 
</script>
</body>
</html>
</ex:page>
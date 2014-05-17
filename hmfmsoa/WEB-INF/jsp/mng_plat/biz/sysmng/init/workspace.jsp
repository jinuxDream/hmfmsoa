<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="mng_plat.biz.sysmng.init.TaskTipDTO"%>
<%@ include file="/jsp/commons/taglibs.jsp"%>
<%@ page import="hmfms.util.*"%>
<%
	User user = ActionUtil.getUser(request);
	Map<String,Result> map = (Map<String,Result>)request.getAttribute("map");
	Result rsRole = map.get("rsRole");
	Result rsGroup = map.get("rsGroup");
	String htmlMenu = (String)request.getAttribute("htmlMenu");
	
	 
%>
<%@page import="mng_plat.service.workgroup.WorkGroup"%>
<%@page import="hmfms.web.User"%>
<%@page import="java.util.Map"%>
<%@page import="fd.commons.jdbc.Result"%>
<%@page import="fd.exception.BusinessException"%><HTML>
<HEAD>
</HEAD>
<%@ include file="/jsp/commons/meta.jsp" %>
<link href="<c:out value="${ctx}"/>/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" /> 
<script src="<c:out value="${ctx}"/>/ligerUI/js/ligerui.min.js" type="text/javascript"></script> 
<script language="javascript">
window.parent.htmlMenu("<%=htmlMenu%>");
$(document).ready(function(){
	$('.grid2').datagrid({trselect:false});
	<%if(ObjectUtil.isEmpty(htmlMenu)){%>
		selectMenu();
	<%}%>
});
function selectMenu(){
	$.webUtil.openWindow(
		{title:"选择用户或组",applyTo:$(".grid1"),width:300,height:400,buttons:[
			{btn:'<button>确定</button>',onclick:function(e){
				$.webUtil.submit("workspace.do");
				$(e.data.target).closeWebDialog();
			}
			},
			{btn:'<button>关闭</button>',onclick:function(e){$(e.data.target).closeWebDialog();}}
		]});
	$('.btn').PicButton();
	$('.grid1').datagrid({hashead:false,trselect:false});
}
//小区维护信息提示
function gotoBuLu(sect_id){
	$.webUtil.submit("<c:out value="${ctx}"/>/cspplatinfo/baseinfo/sectmodify/addModifySectView.do?sect_id="+sect_id);
}
function gototran(url){
	$.webUtil.submit("<c:out value="${ctx}"/>/"+url);
}
//小区整改情况信息提示
function gotoSectCorrect(){
	$.webUtil.submit("<c:out value="${ctx}"/>/fourcheckmng/specialcheck/sectspecialcheck/correctaudit/index.do");
}
</script>
<script type="text/javascript">$(function (){$("#accordion1").ligerAccordion({height: 420});});</script>

<style type="text/css">
body{ padding:20px; margin:0}
#accordion1{ width:250px;overflow:hidden;}
</style>

<body >
<form name="form1" action="" method="post">
 <table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<table width="760" border="0" cellspacing="0" cellpadding="0" align="center" height="100%">
        		<tr>
		        	<td width="90"   rowspan="2"  align="center" >&nbsp;</td>
					<td  colspan="2"  valign="top">
					<div >
						 	<div title="程序上线提醒">
						  		 
						  	</div>
						  </div>
					</td>
        		</tr>
        		<tr>
          			<td  height="10">&nbsp;<font color="red"><b></b></font></td>
        		</tr>
      		</table>
		</td>
		<td width="150">&nbsp;</td>
		<td>
		 
			 	 
		</td>
		
	</tr>
</table>
<table class="grid1" width="80%" style="display:none" >
	<tr>
		<td align="center">角色</td>
	</tr>
	<tr>
		<td>
		<%for(int i=0;i<rsRole.getRowCount();i++){ %>
			<input type="checkbox" name="roled_id" value="<%=rsRole.getString(i,"ro_roleid") %>"/><%=rsRole.getString(i,"ro_name") %>
		<%} %>
		</td>
	</tr>
	
	<tr>
		<td align="center">组</td>
	</tr>
	<tr>
		<td>
		<%for(int i=0;i<rsGroup.getRowCount();i++){ %>
			<input type="checkbox" name="group_id" value="<%=rsGroup.getString(i,"group_id") %>"/><%=rsGroup.getString(i,"group_name") %>
		<%} %>
		</td>
	</tr>
</table>
</form>
</BODY>
</HTML>

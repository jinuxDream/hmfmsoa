<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/jsp/commons/taglibs.jsp"%>
<%@page import="hmfms.util.ObjectUtil"%>
<%@page import="hmfms.base.ActionResultHmfms"%>

<%
//消息页面标题
String pageok_tilte = (String)request.getParameter(ActionResultHmfms.MESSAGE_PAGETITLE); // 功能导航信息。例如：制定维修决案->新增
if (ObjectUtil.isEmpty(pageok_tilte)) pageok_tilte="操作失败"; 
//消息内容
String msg_content = (String)request.getParameter(ActionResultHmfms.MESSAGE_CONTENT);
if (ObjectUtil.isEmpty(msg_content)) msg_content="操作失败!";
//消息的详细信息或附加信息，如申请编号等
String message_detail = (String)request.getParameter(ActionResultHmfms.MESSAGE_DETAIL);
if(ObjectUtil.isEmpty(message_detail)) message_detail=" ";


%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<link rel="stylesheet" type="text/css" href="<c:out value="${ctx}"/>/vcol/style/globalcss.css" />
<%@ include file="/jsp/commons/meta.jsp" %>
<title>操作成功</title>
<script language="javascript">
$(document).ready(function(){
	$('.btn').PicButton();
	var g=$('.grid1').datagrid({hashead:true,trselect:false});
	$("#msg2").hide();
	if($("#msg").html()=="") $("#msg2").show();
});	
</SCRIPT>
</head>
<!-- exp_http.jsp -->
<body>
<form name="form1" method="post" action="">
<table border="0" cellpadding="0" align="center" cellspacing="0" width="680"  style="margin:0 auto">
	<tr><td><div class="headline"><div class="headarrow"> &nbsp; &nbsp; &nbsp;</div><%=pageok_tilte%></div></td></tr>
	<tr>
		<td height="20" ></td>
	</tr>
	<tr><td>
	<table class="grid1" width="100%" align="center" >
		<thead><tr><td><div align="center">操作失败!</div></td></tr></thead>
		<tr>
			<td align="center" style="padding:20px">
			<table border="0" cellpadding="0" cellspacing="0" width="80%">
			<tr>
				<td width="100%" align="center" style="padding:30px;">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<tr><td width="100%" align="center" style="border:none">
						<div   class="error_icon"><br><br><br><br><br><br></div>	 <br/></td>
						</tr>
						<tr>
							<td width="100%" align="center" height="10" >
							<div id="error_msg"> <br>
								<div id="msg"><html:errors /></div><br>
								<div id="msg2" >
									<% String errmsg=(String)request.getAttribute("G_FD_EXPMSG");
									if(errmsg!=null && errmsg.length()>150) errmsg=errmsg.substring(0,150)+"...";
									%>系统异常，请联系系统管理员!<br>
									<%= errmsg%>
									
									<br><!-- 错误类型:<%= request.getAttribute("javax.servlet.error.exception_type")%> -->
								</div>
							</div>
							</td>
						</tr>
						<tr height="30">
							<td align="center" height="30" style="border:none">
							<button class="btn" onclick="window.history.back();return false;">返回</button>  
						</td>
						</tr>
		
					</table>		
				</td>
			</tr>
			</table>
			</td>
		</tr>
		</table>	
	</td></tr>
</table>
</form>
</body>
</html>

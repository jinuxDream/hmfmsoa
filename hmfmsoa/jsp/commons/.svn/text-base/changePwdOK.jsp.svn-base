<%@ page language="java" contentType="text/html; charset=GBK"  pageEncoding="GBK"%>
<%@ include file="/jsp/commons/taglibs.jsp" %>
<%@taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%
String pagepath = (String)request.getParameter("pagepath");//功能导航信息。例如：制定维修决案->新增
if(ObjectUtil.isEmpty(pagepath)) pagepath="";

String msg = (String)request.getParameter("rdok_tips");
if(ObjectUtil.isEmpty(msg)) msg="无法判断操作结果！";

String ok = (String)request.getParameter("ok");

String backtourl = (String)request.getAttribute("return");
if(ObjectUtil.isEmpty(backtourl)) backtourl="javascript:history.back();";
else backtourl="gotopage('" + backtourl + "')";
%> 
<%@page import="hmfms.util.ObjectUtil"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<c:out value="${ctx}"/>/vcol/style/globalcss.css" />
<%@ include file="/jsp/commons/meta.jsp" %>
<script type="text/javascript">
$(document).ready(function(){	
	$('.btn').PicButton();
	var g=$('.grid1').datagrid({trselect:false});//或执行代码 g[0].grid.setBar(bar);	也可以
});	
</script>
</head>
<body>
<table border="0" cellpadding="0" align="center" cellspacing="0" width="680"  style="margin:0 auto">
	<tr><td><div class="headline"><div class="headarrow"> &nbsp; &nbsp; &nbsp;</div>修改密码</div></td></tr>
	<tr>
		<td height="20" ></td>
	</tr>
	<tr><td>
	<table class="grid1" width="100%" align="center" >
		<thead><tr><td><div align="center">操作成功!</div></td></tr></thead>
		<tr>
			<td align="center" style="padding:20px">
			<table border="0" cellpadding="0" cellspacing="0" width="80%">
			<tr>
				<td width="100%" align="center" style="padding:30px;">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr><td width="100%" align="center" style="border:none">
							<%if("0".equals(ok)) {%>
                            	<div   class="error_icon"><br><br><br><br><br><br></div>	 <br />
                            <%}else{ %>
                           		<div   class="success_icon"><br><br><br><br><br><br></div>	 <br />
                            <%} %>
							</td>
							</tr>
							<tr height="30">
								<td align="center" style="border:none">
								 <%=msg %>
								</td>
							</tr>
							<tr height="30">
								<td align="center" height="30" style="border:none">
							<%if("0".equals(ok)) {%>
                            	<div style="width:45px;margin:0 auto;"><button class="btn" type="button" onclick="<%=backtourl%>">返回</button></div>
                            <% }else{%>
                            <div style="width:45px;margin:0 auto;"><button class="btn" type="button" onclick="window.close();return false;">关闭</button></div>
                             <%} %>
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
</body>
</html>
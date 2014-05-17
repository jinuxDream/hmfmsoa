<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/jsp/commons/taglibs.jsp"%>
<%@page import="hmfms.base.ActionResultHmfms"%>
<%@page import="hmfms.util.ObjectUtil"%>
<%@page import="fd.exception.BusinessException"%>
<%
/**说明：本页面支持7类参数:1：页面标题，2：提示消息，3：消息详细信息，
4：上一个请求的URL ,5：返回URL,6：其他按钮组,7：任意个需要从成功页面传递到后续页面的隐藏域
一、默认情况，不需要传递任何参数，各参数默认值为：
	1：默认为上一个提交页面的标题。2：提示“操作成功”，3：详细信息为空串
	4：上一个请求页面的URL。5：当前包的index.do。6：只有一个返回按钮，没有其他任何按钮。7：没有任何隐藏域传递。
二、常用的是设置第7项、第6项，详细参见操作员新增的写法及ActionResultHmfms.toPageOkWithParam()和ActionResultHmfms.toPageOk()
*/
//User user = (User)request.getSession().getAttribute(hmfms.base.BaseAction.USER_SESSION);
//消息页面标题
String pageok_tilte = (String)request.getParameter(ActionResultHmfms.MESSAGE_PAGETITLE); // 功能导航信息。例如：制定维修决案->新增
if (ObjectUtil.isEmpty(pageok_tilte)) pageok_tilte="操作成功"; 
//消息内容
String msg_content = (String)request.getParameter(ActionResultHmfms.MESSAGE_CONTENT);
if (ObjectUtil.isEmpty(msg_content)) msg_content="操作成功!";
//消息的详细信息或附加信息，如申请编号等
String message_detail = (String)request.getParameter(ActionResultHmfms.MESSAGE_DETAIL);
if(ObjectUtil.isEmpty(message_detail)) message_detail=" ";
String is_close = (String)request.getParameter(ActionResultHmfms.IS_CLOSE);
String back_home_url = (String)request.getParameter(ActionResultHmfms.BACK_HOME_URL);
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
	var g=$('.grid1').datagrid({hashead:true,trselect:false,striped:false});
});	

function toClose(){

//"is_close"参数  "0"表示显示"返回"按钮而不显示"关闭"按钮,"1"表是关闭弹窗，并刷新打开窗口。"2"表示返回首页
<% if("1".equals(is_close)){%>
	window.opener.location.reload();
	window.close();
<% }else if("2".equals(is_close)){%>
	window.parent.location.href="<%=back_home_url%>";
<% }else if("3".equals(is_close)){%>
	parent.window.location.href="<%=back_home_url%>";
<%}%>

}
</SCRIPT>
</head>
<body>
<form name="form1" method="post" action="">
<%=ActionResultHmfms.convertParams2Html(request)%>
<table border="0" cellpadding="0" align="center" cellspacing="0" width="680"  style="margin:0 auto">
	<tr><td><div class="headline"><div class="headarrow"> &nbsp; &nbsp; &nbsp;</div><%=pageok_tilte%></div></td></tr>
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
							<div   class="success_icon"><br><br><br><br><br><br></div>	 <br /></td>
							</tr>
							<tr height="25">
								<td width="100%" align="center" style="border:none"><%=msg_content%></td>
							</tr>
							<tr height="30">
								<td align="center" style="border:none">
								 <%=message_detail %>
								</td>
							</tr>
							<tr height="30">
								<td align="center" style="border:none">
								 可以选择如下操作：
								</td>
							</tr>
							 
							<tr height="30">
								<td align="center" height="30" style="border:none">
							<%if(!"0".equals(is_close)){ 
								if("3".equals(is_close)){%>
									<button type="button" class="btn" onclick="toClose();return false;">返回</button>
								<%}else{%>
									<button type="button" class="btn" onclick="toClose();return false;">关闭</button>
								<% }%>
							<%}else{ %>	
								<%=ActionResultHmfms.convertButton2Html(request) %>
							<% } %>	
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

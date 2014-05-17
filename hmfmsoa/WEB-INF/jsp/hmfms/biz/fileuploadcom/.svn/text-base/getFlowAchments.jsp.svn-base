<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@page import="hmfms.util.Property"%>
<%@page import="hmfms.util.ObjectUtil"%>
<%@ include file="/jsp/commons/taglibs.jsp"%>
<%
String file_path =(String)request.getAttribute("filePath");
if(file_path== null || file_path.equals("")){
	file_path = "";
}
%>
<html>
<head>
<%@ include file="/jsp/commons/meta.jsp" %>
<link rel="stylesheet" type="text/css" href="<c:out value="${ctx}"/>/style/style.css"  />
<script type="text/javascript">
$(document).ready(function(){
	var bar=$('.btn_toolbar').BtnToolBar();
	$('.grid1').datagrid({fixhead:false,toolbar:bar,trselect:true});
	$('.grid_fixhead').datagrid({hashead:false,trselect:false});
});

function gotoreturn()
{
	window.close();
}

</script>
</head>
<body>
<div class="parent-wrap" style="width:90%">
<form name="form1" method="post" action="">
<input type="hidden" name="fileurl" id="fileurl" value="<c:out value="${ctx}"/><%=Property.getString("webFileRoot","")%><%=file_path%>">
	<div class="sub-bg">
		<img src="<c:out value="${ctx}"/>/images/home_title_leftarrow.gif"/>
		<strong>附件信息查看</strong>
	</div>
		
	<div class="wrap-btnarea">
		<div class="btn_toolbar" >
				<button class="btn" type="button" value="2" onclick="gotoreturn()">关闭</button>
				<div class="btn_condition" >
					<div id='condition_null'>关闭</div>
				</div>
		</div>
	</div>	
	<table border="0" width="100%">
			<tr>
				<td align="left"   colspan="2" >
				<% if(!ObjectUtil.isEmpty(file_path)){ %>
				<span>
					<iframe id="HtmlEdit" src="<c:out value="${ctx}"/><%=Property.getString("webFileRoot","")%><%=file_path%>" width="100%"  height="600"></iframe>
			</span>
			<%}else{ %>
			<span>没有附件！</span>
			<%} %>
			</td>
		</tr>
	</table>
</form>
</div>
</body>
</html>

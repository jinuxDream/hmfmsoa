<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="fd.commons.jdbc.Result"%>
<%@ include file="/jsp/commons/taglibs.jsp" %>

<% 
	Result rsCspMan = (Result)request.getAttribute("rsCspMan");
%>


<%@page import="hmfms.services.codes.CertType"%><html>
<%@ include file="/jsp/commons/meta.jsp" %>
<script type="text/javascript">

/* 本页面的初始处理。 本函数必须有，如果没有初始处理，则函数中不需要写任何代码。*/
$(document).ready(function(){	
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_condition 三个样式名称不能变之外其他都可以改变)
	var g=$('.grid1').datagrid({toolbar:bar,trselect:false});//或执行代码 g[0].grid.setBar(bar);	也可以
});	
//查询小区经理信息变更历史
function gotoCspManModiHistory()
{
	$.webUtil.submit("getQueryCspManModiHistory.do");
}

</script>
<body>
<form name="form1" method="post" action="">
<!-- 隐藏域存放小区经理ID -->
<input type="hidden" id="csm_id" name="csm_id" value="<%=rsCspMan.getString(0,"csm_id") %>" /> 

<table border="0" cellpadding="0" cellspacing="0" width="960" align="center">
<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>小区经理详细信息</div></td></tr>
	<tr><td height="<%=_PAGEBLOCK_HEIGHT%>"></td></tr>
	<!--间距 不可删除--><!--功能按钮区 start--> 
	<tr>
		<td width="100%">
			<button class="btn" type="button" value="2" onclick="history.back()"  force >返回</button>
		</td>
	</tr>
	<!--功能按钮区 end-->
	<tr><td height="<%=_PAGEBLOCK_HEIGHT %>"></td></tr><!--间距 不可删除-->
	<tr>
		<td width="100%"><!--内容区 start-->
			<table width="100%"  class="grid1">
				<thead >
					<tr>
						<th colspan="2">小区经理详细信息</th>
					</tr>
				</thead>
					<tr>
						<td align="center">经理名称</td>
						<td align="left"><%=rsCspMan.getString(0,"csm_name") %></td>
					</tr>
					<tr>
						<td align="center">证件类型</td>
						<td align="left" ><%=CertType.getValue(rsCspMan.getString(0,"csm_cert_type")) %></td>
					</tr>
					<tr>
						<td align="center">证件号码</td>
						<td align="left"><%=rsCspMan.getString(0,"csm_cert_code") %></td>
					</tr>
					<tr>
						<td align="center">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机&nbsp;</td>
						<td align="left"><%=rsCspMan.getString(0,"csm_phone") %></td>
					</tr>
					<tr>
						<td align="center">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话&nbsp;</td>
						<td align="left"><%=rsCspMan.getString(0,"csm_tel") %></td>
					</tr>
					<tr>
						<td align="center">上岗证号</td>
						<td align="left"><%=rsCspMan.getString(0,"csm_job_code") %></td>
					</tr>
					<tr>
						<td nowrap width="20%" align="center">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注&nbsp;</td>
						<td nowrap width="80%" align="left"><%=rsCspMan.getString(0,"csm_remak") %></td>
					</tr>
					<tr>
						<td nowrap width="20%" align="center">相关链接</td>
						<td nowrap width="80%" align="left"><a href="#" onclick="gotoCspManModiHistory()">小区经理信息变更历史</a></td>
					</tr>
			</table>
			<!--内容区 end-->
		</td>
	</tr>
</table>
</body>
</html>


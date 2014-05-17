<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="fd.commons.jdbc.Result"%>
<%@page import="hmfms.util.ObjectUtil"%>
<%@page import="hmfms.util.StringUtil"%>
<%@page import="hmfms.web.User"%>
<%@page import="hmfms.util.ActionUtil"%>
<%@ include file="/jsp/commons/taglibs.jsp" %>
<%
	Result rsCspinfo = (Result)request.getAttribute("rsCspMngOffInfo");
String batch_no = StringUtil.getString(request.getParameter("batch_no"));
String data_type = StringUtil.getString(rsCspinfo.getString(0,"data_type"));
%>

<%@page import="hmfms.services.codes.StaffType"%>
<%@page import="fd.commons.jdbc.Page"%>
<%@page import="mng_plat.biz.baseinfo.choice.ChoiceManager"%>
<%@page import="hmfms.web.commons.PageCounter"%>
<%@page import="hmfms.util.DateUtil"%>
<%@page import="hmfms.services.codes.OrgNature"%>
<%@page import="hmfms.services.codes.QualificationLevel"%><html>
<head>
<%@ include file="/jsp/commons/meta.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	$('.btn').PicButton();
	$('.grid_cspOffInfo').datagrid({defaultSel:false, trselect:false});//固定表头
});
</script>
</head>
<body>
<form name="form1" method="post" action="">
<table border="0" cellpadding="0" cellspacing="0" width="890" align="center">
	<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>小区管理处信息</div></td></tr>
	<tr><td height="<%=_PAGEBLOCK_HEIGHT %>"></td></tr><!--间距 不可删除-->
	<tr>
		<td width="100%">
			<%if("del".equals(request.getParameter("flag"))){ %>
				<button class="btn" istip="1"  type="button" btn_href="<c:out value="${ctx}"/>/mng_plat/biz/baseinfo/cspmng_office/delCspMngOffice.do?org_id=<%=rsCspinfo.getString(0,"org_id") %>&batch_no=<%=batch_no %>&data_type=<%=data_type %>">删除</button>
			<%} %>
			<button class="btn" type="button" onclick="javascript:history.back(); return false;" >返回</button>
		</td>
	</tr>
	<tr><td height="<%=_PAGEBLOCK_HEIGHT %>"></td></tr><!--间距 不可删除-->
	<tr>
		<td>
			<table class="grid_cspOffInfo" width="100%">			
				<thead>
					<tr>
						<td width="10%" colspan="4">小区管理处信息</td>
					</tr>
				</thead>
				<tr>
					<td align="right" width="20%">小区管理处编码:</td>
					<td width="30%">&nbsp;&nbsp;<%=rsCspinfo.getString(0,"org_code") %></td>
					<td align="right" width="15%">小区管理处名称:</td>
					<td >&nbsp;&nbsp;<%=rsCspinfo.getString(0,"org_name") %></td>
				</tr>
				<tr>
					<td align="right" width="20%">地        址 :</td>
					<td colspan="3">&nbsp;&nbsp;<%=rsCspinfo.getString(0,"org_addr") %></td>
				</tr>
				<tr>
					<td align="right">小区管理处联系人:</td>
					<td>&nbsp;&nbsp;<%=rsCspinfo.getString(0,"contacter") %></td>
					<td align="right">邮政编码:</td>
					<td>&nbsp;&nbsp;<%=rsCspinfo.getString(0,"postcode") %></td>
				</tr>
				<tr>
					<td align="right">小区管理处联系人联系方式:</td>
					<td>&nbsp;&nbsp;<%=rsCspinfo.getString(0,"tel") %></td>
					<td align="right">投诉电话:</td>
					<td>&nbsp;&nbsp;<%=rsCspinfo.getString(0,"COMPLAINT_TEL") %></td>
				</tr>
				<tr>
					<td align="right">日间报修电话:</td>
					<td>&nbsp;&nbsp;<%=rsCspinfo.getString(0,"day_tel") %></td>
					<td align="right">夜间保修电话:</td>
					<td>&nbsp;&nbsp;<%=rsCspinfo.getString(0,"night_tel") %></td>
				</tr>
				<tr>
					<td align="right">小区管理处传真:</td>
					<td>&nbsp;&nbsp;<%=rsCspinfo.getString(0,"fax") %></td>
					<td align="right">备        注 :</td>
					<td>&nbsp;&nbsp;<%=rsCspinfo.getString(0,"sect_remark") %></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>

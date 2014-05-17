<%@page import="hmfms.util.ActionUtil"%>
<%@page import="hmfms.web.User"%>
<%@page import="hmfms.util.StringUtil"%>
<%@page import="hmfms.util.ObjectUtil"%>
<%@page import="fd.exception.BusinessException"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*,fd.commons.jdbc.Result"%>
<%@ include file="/jsp/commons/taglibs.jsp"%>
<%
	User user =	ActionUtil.getUser(request);
	Result rs=(Result)request.getAttribute("rs");
	request.setAttribute("rs", rs);

	%> 

<%@page import="hmfms.services.codes.SectKind"%><html>
	<%@ include file="/jsp/commons/meta.jsp"%>
	<script type="text/javascript">
		/* 本页面的初始处理。 本函数必须有，如果没有初始处理，则函数中不需要写任何代码。*/
		$(document).ready(function(){	
			$('.btn').PicButton();
			var bar=$('.btn_toolbar').BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_condition 三个样式名称不能变之外其他都可以改变)
			var g=$('.grid1').datagrid({toolbar:bar});//或执行代码 g[0].grid.setBar(bar);	也可以
		});	
		function onSelectRow(sect_id){
				$("#sect_id").val(sect_id);
			}
		
	</script> 
	<body>
		<form name="form1" method="post" action="">
		<input type="hidden" name="sect_id" id="sect_id" value="" /> 
		<input type="hidden" name="sect_type" id="sect_type" value="<%=request.getParameter("sect_type") %>" /> 
		<input type="hidden" name="st_name_frst" id="st_name_frst" value="<%=request.getParameter("st_name_frst") %>" /> 
		<input type="hidden" name="st_addr_frst" id="st_addr_frst" value="<%=request.getParameter("st_addr_frst") %>" /> 
		<input type="hidden" name="unitaddr" id="unitaddr" value="<%=request.getParameter("unitaddr") %>" /> 
		<input type="hidden" name="st_kind" id="st_kind" value="<%=request.getParameter("st_kind") %>" />
		<input type="hidden" name="hpb_id" id="hpb_id" value="<%=request.getParameter("hpb_id") %>" /> 
		<input type="hidden" name="ho_name" id="ho_name" value="<%=request.getParameter("ho_name") %>" />
		<input type="hidden" name="str_name" id="str_name" value="<%=request.getParameter("str_name") %>" /> 
		<input type="hidden" name="sect_curr_stat" id="sect_curr_stat" value="<%=request.getParameter("sect_curr_stat") %>" />
			<table border="0" cellpadding="0" cellspacing="0" width="960" style="margin:0 auto">
			<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>小区信息查询→导出</div></td></tr>
				<tr>
					<td height="<%=_PAGEBLOCK_HEIGHT%>"></td>
				</tr>
				<tr>
					<td width="100%">
						<div class="btn_toolbar">
						<button class="btn" type="button" value="1" btn_href="printInExcel.do" istip="1" ismask="0"  force>导出</button>
						<button class="btn" type="button" value="2" onclick="javascript:history.back();" force>返回</button>
					</div>
					</td>
				</tr>
				<tr>
					<td height="<%=_PAGEBLOCK_HEIGHT%>"></td>
				</tr>
			</table>
			<% if(rs.getRowCount()!=0){  int y=0;%>
			<table border="0" cellpadding="0" cellspacing="0" width="100%"  align="center" style="margin:0 auto" background="<c:out value="${ctx}"/>/images/yulan_bg.gif">	
				<tr>
					<td>
						<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolor="#000000">
							<tr align="center" height="40px">
								<td width="3%">序号</td>
								<td width="10%">小区编码</td>
								<td width="15%">小区名称</td>
								<td width="20%">小区地址</td>
								<td width="6%">小区性质</td>
								<td width="15%">物业公司名称</td>
								<td width="10%">小区管理处</td>
								<td width="10%">房办</td>
								<td>归属街道</td>
							</tr>
							<%for(int i=0;i<rs.getRowCount();i++){%>
								<tr height="30px">
									<td align="center"><%=i+1 %></td>
									<td align="center"><%=rs.getString(i,"st_code") %></td>
									<td align="left"><%=rs.getString(i, "st_name_frst") %></td>
									<td align="left"><%=rs.getString(i, "st_addr_frst") %></td>
									<td align="center"><%=SectKind.getValue(rs.getString(i, "st_kind")) %></td>
									<td align="left"><%=rs.getString(i, "csp_name") %></td>
									<td align="left"><%=rs.getString(i, "cs_name") %></td>
									<td align="left"><%=rs.getString(i, "ho_name") %></td>
									<td align="left"><%=rs.getString(i, "str_name") %></td>
								</tr>
							<%} %>
						</table>
					</td>
				</tr>
				<tr>
					<td height="<%=_PAGEBLOCK_HEIGHT%>"></td>
				</tr>
			</table>
			
			<%} else{ %> 
			<table border="0" cellpadding="0" cellspacing="0" width="960" style="margin:0 auto">
				<tr>
					<td align="center">
						没有找到匹配的小区信息！
					</td>
				</tr>
			</table>
			<%} %>
		</form>
	</body>
</html>

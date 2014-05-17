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
	Result list_rs = (Result)request.getAttribute("list_rs");
	//	查询参数
	String st_name_frst = (String)request.getParameter("st_name_frst");
	if(st_name_frst == null) st_name_frst = "";
	String st_addr_frst= (String)request.getParameter("st_addr_frst");
	if(st_addr_frst == null) st_addr_frst = "";
	Result rsHpb = (Result)request.getAttribute("rsHpb");
%> 

<%@page import="hmfms.services.codes.DeptType"%>
<%@page import="hmfms.web.commons.SelectBoxHtml"%><html>
	<%@ include file="/jsp/commons/meta.jsp"%>
	<script type="text/javascript">
		/* 本页面的初始处理。 本函数必须有，如果没有初始处理，则函数中不需要写任何代码。*/
		$(document).ready(function(){	
			$('.btn').PicButton();
			var bar=$('.btn_toolbar').BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_condition 三个样式名称不能变之外其他都可以改变)
			var g=$('.grid1').datagrid({toolbar:bar});//或执行代码 g[0].grid.setBar(bar);	也可以
		});	
		
		//查询街道
		function gotoQuery()
		{
			//处理前后空格
			$("input[type=text]").each(function(){
				$(this).val($.trim($(this).val()));
			});
			$("#ho_name").val("");
			
			$.webUtil.submit("querySect.do?flag=1");
		}
		
		//查询房办
		function gotoQueryInfo()
		{
			//处理前后空格
			$("input[type=text]").each(function(){
				$(this).val($.trim($(this).val()));
			});
			$("#str_name").val("");
			$.webUtil.submit("querySect.do?flag=2");
		}
	</script> 
	<body>
		<form name="form1" method="post" action="">
			<input type="hidden" name="sect_id" id="sect_id" value="" /> 
			<table border="0" cellpadding="0" cellspacing="0" width="960" style="margin:0 auto">
			<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>小区基本情况汇总表</div></td></tr>
				<tr>
					<td height="<%=_PAGEBLOCK_HEIGHT%>"></td>
				</tr>
				<!--间距 不可删除--><!--查询条件区 start--> 
				<tr>
					<td width="100%" class="frameblue">
						<div class="wrap-paramarea">
							<div class="fl mr5 mt2">
							<% if(DeptType.ShiJu.equals(user.getDeptType())){%>
							区县：<select  id="hpb_id" name = "hpb_id" v_empty="0" v_min="1" v_max="20">
									<%=SelectBoxHtml.genOptionsHpbHtml(true,StringUtil.getString(request.getParameter("hpb_id"))) %>
									</select>
							<%} %>
							街道名称：<input type="text" id="str_name" name="str_name" size="25" maxlength="200" value = "<%=StringUtil.getString(request.getParameter("str_name"))%>" />&nbsp;
									<button class="btn" type="button" onClick="gotoQuery()" mask>街道查询</button> 
							</div>
							
							<div class="fl mr5 mt2">
							房管办：<input type="text" id="ho_name" name="ho_name" size="25" maxlength="200" value = "<%=StringUtil.getString(request.getParameter("ho_name"))%>" />&nbsp;
									<button class="btn" type="button" onClick="gotoQueryInfo()" mask>房办查询</button> 
							</div>
							
							<div>
								<div class="clear"></div>
							</div>
						</div>
					</td>
				</tr>
				<!--查询条件区 end--> 
				<tr>
					<td height="<%=_PAGEBLOCK_HEIGHT%>"></td>
				</tr>
			</table>
		</form>
	</body>
</html>

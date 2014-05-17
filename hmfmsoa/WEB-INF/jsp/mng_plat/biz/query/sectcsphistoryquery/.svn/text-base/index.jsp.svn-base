<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="fd.commons.jdbc.Result"%>
<%@page import="hmfms.util.DateUtil"%>
<%@page import="hmfms.util.ObjectUtil"%>
<%@ include file="/jsp/commons/taglibs.jsp"%>

<%
	Result rsSectCsp = (Result)request.getAttribute("rsSectCsp");
	User user = ActionUtil.getUser(request);
	//参数查询
	String st_name_frst = (String)request.getParameter("st_name_frst");
	if(st_name_frst == null) st_name_frst = "";
	String csp_name= (String)request.getParameter("csp_name");
	if(csp_name == null) csp_name = "";
	String hpb_id = (String)request.getParameter("hpb_id");
	if(hpb_id == null) hpb_id = "";
%>


<%@page import="hmfms.web.User"%>
<%@page import="hmfms.util.ActionUtil"%>
<%@page import="hmfms.services.codes.DeptType"%>
<%@page import="hmfms.web.commons.SelectBoxHtml"%>
<%@page import="hmfms.util.StringUtil"%><html>
<head>
<%@ include file="/jsp/commons/meta.jsp" %>

<script type="text/javascript">
$(document).ready(function(){
	$("#searchBtn").PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();
	$('.grid_fixhead').datagrid({fixhead:false,toolbar:bar});
});

//选中行时取得小区id，删除后无法实现高亮显示
function onSelectRow(index,elm)
{
	$("#sect_id_detail").val(elm);
}
//查询
function gotoQuery()
{
	//处理前后空格
	$("input[type=text]").each(function(){
		$(this).val($.trim($(this).val()));
	});
	$.webUtil.submit("index.do");
}
//查看选中小区物业服务历史的信息
function gotoView(id){
	if(id){$("#sect_id_detail").val(id);}
	$.webUtil.submit("getQuerySectCspHistoryList.do");
}
</script>
</head>
<body>
<div class="parent-wrap">
<form id="form1" name="form1" method="post" action="">
    <!-- 隐藏域存放小区id -->
	<input type="hidden" id="sect_id_detail" name="sect_id_detail" value="" />
	<div class="sub-bg">
		<img src="<c:out value="${ctx}"/>/images/home_title_leftarrow.gif"/>
		<strong>小区物业服务历史查询</strong>
	</div>
	<div class="wrap-paramarea">
		<div class="fl mr5 mt2">
			小区名称：<input name="st_name_frst" value="<%=st_name_frst %>"/>
			物业公司：<input name="csp_name" value="<%=csp_name %>"/>
			<% if(DeptType.ShiJu.equals(user.getDeptType())){%>所属区县：
			<select id="hpb_id" name = "hpb_id" v_empty="1">
            <%=SelectBoxHtml.genOptionsHpbHtml(true,StringUtil.getString(request.getParameter("hpb_id"))) %></select>
			<% }%>
		</div>
		<div><button id="searchBtn" type="button" onClick="gotoQuery()">查询</button></div>
		<div><span><font color = "red">现不提供没有物业的小区物业服务历史查询信息</font></span></div>
		<div class="clear"></div>
	</div>
	<div class="wrap-btnarea">
		<div class="btn_toolbar" >
			<button class="btn" type="button" onclick="gotoView();">查看</button>
			<div class="btn_condition" >
				<div id='0'>查看</div>
			</div>
		</div>
	</div>
	<div class="wrap-content">
		<table class="grid_fixhead" width="100%">
			<tr>
				<td width="3%">序号</td>
				<td width="20%">小区名称</td>
				<td width="22%">小区地址</td>
				<td width="25%">物业名称</td>
				<td width="8%">小区所属区县</td>
				<td width="10%">物业服务合同<br>开始日期</td>
				<td width="">物业服务合同<br>结束日期</td>				
			</tr>
			<!--JSP的for循环在下面开始写-->
			<%
			for(int i=0; i<rsSectCsp.getRowCount(); i++){						
			//TODO 下面开始写自己的处理代码。
			%>
			<tr onclick="onSelectRow(<%=i%>,<%=rsSectCsp.getString(i,"sect_id")%>)">
				<td height="24" align="center" btn_condition='0'><%=(i+1) %>
				</td>
				<td align="left">
					<a href="#" onclick="gotoView('<%=rsSectCsp.getString(i,"sect_id")%>');return false;"><%=rsSectCsp.getString(i,"st_name_frst")%></a>
				</td>
				<td >&nbsp;<%=rsSectCsp.getString(i,"st_addr_frst")%></td>
				<td >&nbsp;<%=rsSectCsp.getString(i,"csp_name")%></td>
				<td align="center"><%=rsSectCsp.getString(i,"hp_name")%></td>			
				<td align="center"><%=DateUtil.formatFromDB(rsSectCsp.getString(i,"st_csp_contract_start_date"))%></td>
				<td align="center"><%=DateUtil.formatFromDB(rsSectCsp.getString(i,"st_csp_contract_end_date"))%></td>
			</tr>
			<%} %><!--JSP的for循环结束--> 
		</table>
	</div>
	<div class="wrap-page">
		<%=rsSectCsp.getPage() != null ? rsSectCsp.getPage().getPageHtml("form1","index.do"):"" %>
	</div>
	
</form>
</div>
</body>
</html>

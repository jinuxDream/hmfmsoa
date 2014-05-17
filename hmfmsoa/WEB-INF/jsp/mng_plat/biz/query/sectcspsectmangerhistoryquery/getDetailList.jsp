<%@page import="fd.commons.jdbc.Result"%>
<%@page import="hmfms.util.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/jsp/commons/taglibs.jsp"%>
<%
	Result rs = (Result) request.getAttribute("rs");
    String sect_id = (String) request.getAttribute("sect_id");
%>
<html>
<%@ include file="/jsp/commons/meta.jsp" %>
<link rel="stylesheet" type="text/css" href="<c:out value="${ctx}"/>/style/style.css"  />
<script type="text/javascript">
$(document).ready(function(){	
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_condition 三个样式名称不能变之外其他都可以改变)
	var g=$('.grid1').datagrid({toolbar:bar,trselect:false});//或执行代码 g[0].grid.setBar(bar);	也可以
});	
function onSelectRow(index,elm){
	var csp_id = $(elm).find("input[name=csp_id_arr]").val();
	var sect_id = $(elm).find("input[name=sect_id_arr]").val();
	$("#csp_id").val(csp_id);
	$("#sect_id").val(sect_id);
}

</script>
<body>
<div class="parent-wrap">
<form id="form1" name="form1" method="post" action=""><input type="hidden" name="csp_id" id="csp_id"  value="<%=rs.getString(0, "csp_id")%>" />
<input type="hidden" name="sect_id" id="sect_id" value="<%=sect_id%>" />
<div class="sub-bg">
		<img src="<c:out value="${ctx}"/>/images/home_title_leftarrow.gif"/>
		<strong>小区经理服务历史查询→详细信息</strong>
	</div>
	 				<div class="wrap-btnarea">
		                <div class="btn_toolbar" >
						<button type="button" class="btn"   btn_href="index.do" issubmit="0" istip="0" >返回</button>
						<div class="btn_condition" >
				        <div id='condition_null'>返回</div>
						</div>
						</div>
	</div>
        <div class="wrap-content">
			<table width="100%"  class="grid1">
			<tr>
					<td width="10%" >序号</td>
					<td width="30%" >物业名称</td>
					<td width="15%" >小区经理</td>
					<td width="25%" >更换日期</td>
					<td width="" >更换时间</td>
				</tr>
				
			<%
				for (int i = 0; i < rs.getRowCount(); i++) {
			%>
			     
			 <tr>
			        <td align="center" height="50" btn_condition='0'><%=(i+1) %>
			        <input type="hidden" name="sect_id" id="sect_id" value="" />
					<input type="hidden" name="csp_id" value="<%=rs.getString(i, "csp_id")%>" />
					<input type="hidden" name="sect_id" value="<%=rs.getString(i, "sect_id")%>" /></td>
				    <td   height="24" align="left">&nbsp;<%=rs.getString(i, "csp_name")%></td>
				    <td   height="24" align="center"><%=rs.getString(i, "csm_name")%></td>
				    <td   height="20" align="center"><%=DateUtil.formatFromDB(rs.getString(i, "create_date"))%></td>
					 <td   height="20" align="center"><%=DateUtil.formatTimeFromDB(rs.getString(i, "create_time"))%></td>
					</tr>
					<%
				}
			%>
					</table>
        </div>
        	  <div style="float: right;"><%=rs.getPage()!=null? rs.getPage().getPageHtml("form1","getDetailList.do"):""%></div>
		  	
	<!-- 内容区域 end -->
</form>
</div>
</body>
</html>
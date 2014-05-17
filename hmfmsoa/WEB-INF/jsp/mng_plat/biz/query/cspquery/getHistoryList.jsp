
<%@page import="hmfms.util.DateUtil"%>
<%@page import="fd.commons.jdbc.Result"%>
<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/jsp/commons/taglibs.jsp"%>
<%
	Result rs = (Result) request.getAttribute("rs");
    String csp_id = (String) request.getAttribute("csp_id");
%>
<html>
<%@ include file="/jsp/commons/meta.jsp" %>
<script type="text/javascript">
$(document).ready(function(){	
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_condition 三个样式名称不能变之外其他都可以改变)
	var g=$('.grid1').datagrid({toolbar:bar,trselect:true});//或执行代码 g[0].grid.setBar(bar);	也可以
});	
function onSelectRow(index,elm){
	var csp_id = $(elm).find("input[name=csp_id_arr]").val();
	$("#csp_id").val(csp_id);
}

</script>
<body>
<div class="parent-wrap">
<form id="form1" name="form1" method="post" action="">
<input type="hidden" name="csp_id" id="csp_id" value="<%=csp_id%>" />
<div class="sub-bg">
		<img src="<c:out value="${ctx}"/>/images/home_title_leftarrow.gif"/>
		<strong>物业信息变更历史→详细信息</strong>
	</div>
	<div class="wrap-btnarea">
      <button type="button" class="btn" btn_href="index.do" issubmit="0" istip="0">返回</button>                
	</div>
        <div class="wrap-content">
			<table width="100%"  class="grid1">
			<tr>
					<td width="10%" >序号</td>
					<td width="30%" >物业名称</td>
					<td width="20%" >组织机构代码</td>
					<td width="20%" >更换日期</td>
					<td width="" >更换时间</td>
				</tr>
				
			<%
				for (int i = 0; i < rs.getRowCount(); i++) {
			%>
			     
			 <tr>
			        <td align="center" height="50" ><%=(i+1) %>
					<input type="hidden" name="csp_id" value="<%=rs.getString(i, "csp_id")%>" />
					</td>
				    <td   height="24" align="left">&nbsp;<%=rs.getString(i, "csp_name")%></td>
				    <td   height="24" align="center"><%=rs.getString(i, "csp_org_code")%></td>
				    <td   height="20" align="center"><%=DateUtil.formatFromDB(rs.getString(i, "create_date"))%></td>
					 <td   height="20" align="center"><%=DateUtil.formatTimeFromDB(rs.getString(i, "create_time"))%></td>
					</tr>
					<%
				}
			%>
					</table>
        </div>
        
        	  <div style="float: right;"><%=rs.getPage()!=null? rs.getPage().getPageHtml("form1","getHistoryList.do"):""%></div>
	<!-- 内容区域 end -->
</form>
</div>
</body>
</html>
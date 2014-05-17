<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="fd.commons.jdbc.Result"%>
<%@page import="hmfms.util.DateUtil"%>
<%@ include file="/jsp/commons/taglibs.jsp"%>

<%
	Result rsSectCsp = (Result)request.getAttribute("rsSectCsp");
%>

<html>
<head>
<%@ include file="/jsp/commons/meta.jsp" %>

<script type="text/javascript">
$(document).ready(function(){
	$(".btn").PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();
	$('.grid_fixhead').datagrid({fixhead:false,toolbar:bar,trselect:false});
});

</script>
</head>
<body>
<div class="parent-wrap">
<form id="form1" name="form1" method="post" action="">
    <!-- 隐藏域 -->
	<input type="hidden" id="" name="" value="" />
	<div class="sub-bg">
		<img src="<c:out value="${ctx}"/>/images/home_title_leftarrow.gif"/>
		<strong>查看小区物业服务历史信息</strong>
	</div>
	<div class="wrap-btnarea">
	  <button class="btn" type="button" value="2" onclick="history.back()"  issubmit="0" istip="0" >返回</button>
	</div>	
	<div class="wrap-content">
		<table class="grid_fixhead" width="100%">
			<tr>
				<td width="10%">序号</td>
				<td width="25%">物业名称</td>
				<td width="25%">注册区县</td>
				<td width="20%">交易的创建日期</td>	
				<td width="">交易的创建时间</td>			
			</tr>
			<!--JSP的for循环在下面开始写-->
			<%
			for(int i=0; i<rsSectCsp.getRowCount(); i++){						
			//TODO 下面开始写自己的处理代码。
			%>
			<tr>
				<td height="24" align="center" btn_condition='<%=i%2%>'><%=(i+1)%>
				</td>
				<td>&nbsp;<%=rsSectCsp.getString(i,"csp_name")%></td>
				<td align="center"><%=rsSectCsp.getString(i,"hp_name")%></td>
				<td align="center"><%=DateUtil.formatFromDB(rsSectCsp.getString(i,"sect_creat_date"))%></td>		
				<td align="center"><%=DateUtil.formatTimeFromDB(rsSectCsp.getString(i,"sect_creat_time"))%></td>
			</tr>
			<%} %><!--JSP的for循环结束--> 
		</table>
	</div>
	<div class="wrap-page">
		<%=rsSectCsp.getPage() != null ? rsSectCsp.getPage().getPageHtml("form1","getQuerySectCspHistoryList.do"):"" %>
	</div>
	
</form>
</div>
</body>
</html>

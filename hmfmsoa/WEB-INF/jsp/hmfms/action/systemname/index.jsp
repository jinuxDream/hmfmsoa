<%@page import="hmfms.services.codes.ChangeStat"%>
<%@page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="fd.commons.jdbc.Result"%>
<%@page import="hmfms.util.ObjectUtil"%>
<%@ include file="/jsp/commons/taglibs.jsp" %>
<%@page import="hmfms.util.DateUtil"%>
<%
	Result rs = (Result)request.getAttribute("rs");
%>



<html>
<%@ include file="/jsp/commons/meta.jsp" %>
<script type="text/javascript">
/* 本页面的初始处理。 本函数必须有，如果没有初始处理，则函数中不需要写任何代码。*/
$(document).ready(function(){	
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_condition 三个样式名称不能变之外其他都可以改变)
	//var g=$('.grid1').datagrid({toolbar:bar});//或执行代码 g[0].grid.setBar(bar);	也可以
	$('.grid1').datagrid({fixhead:false,toolbar:bar,trselect:true,onSelect:onSelectRow});

	function onSelectRow(inx,v1){
		var tr=$(v1);
		var dt_no_arr = $("input[name='dt_no_arr']",tr).val();
		$('#dt_no').val(dt_no_arr);
	}
});	

//查看
function contView(){
	$.webUtil.submit("contView.do");
}

</script>
<body>
	<form name="form1" method="post" action="">
	<input type="hidden" name="dt_no" value="" id="dt_no">
	<table border="0" cellpadding="0" cellspacing="0" width="900" style="margin:0 auto">
			<tr>
				<td>
					<div class="headline">
						<div class="headarrow">&nbsp;</div>系统管理
					</div>
				</td>
			</tr>
			
			<tr>
				<td height="<%=_PAGEBLOCK_HEIGHT %>"></td><!-- 间距不可删除 -->
			</tr>
			
			<tr>
				<td width="100%">
					<div class="btn_toorbar">
						<button class="btn" type="button" value="1" btn_href="add.do" force>新增</button>
						<button class="btn" type="button" value="3" btn_href="edit.do" >编辑</button>
						
					</div>
				</td>
			</tr>
			
			<!-- 功能按钮区 -->
			<tr>
				<td height="<%=_PAGEBLOCK_HEIGHT %>"></td><!-- 间距不可删除 -->
			</tr>
			
			<!-- 表格内容区 start -->
			<tr>
				<td width="100%">
					<table width="100%" class="grid1">
						<tr>
							<td width="8%">序号</td>
							<td width="37%">系统名称</td>
						</tr>
						
						<!-- JSP的for循环开始 -->
						<% 
							for(int i = 0;i < rs.getRowCount();i++){
						%>
						<tr>
							<td align="center" height="24" btn_condition='<%=rs.getString(i,"sn_id") %>'>
							<%=(i+1) %><input type="hidden" name="dt_no_arr" value="<%=rs.getString(i, "sn_id") %>"/>
							</td>
							<%-- <td align="center"><%=ChangeStat.getValue(rs.getString(i,"sn_name")) %></td> --%>
							<td align="center"><%=rs.getString(i,"sn_name") %></td>
						</tr>
						
						<%} %>
					</table>
				</td>	
			</tr>
			<tr>
				<td align = "right">
					<table >
						<tr>
							<td class = "wrap-page" >
								<%=rs.getPage() != null ? rs.getPage().getPageHtml("form1","index.do"):"" %>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
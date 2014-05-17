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
						<div class="headarrow">&nbsp;</div>系统添加
					</div>
				</td>
			</tr>
			
			<tr>
				<td height="<%=_PAGEBLOCK_HEIGHT %>"></td><!-- 间距不可删除 -->
			</tr>
			
			<tr>
				<td width="100%">
					<div class="btn_toorbar">
						<button class="btn" type="button" value="1" btn_href="addCheck.do" force>新增</button>
						<button class="btn" type="button" value="3" btn_href="editCheck.do" >编辑</button>
						<button class="btn" type="button" value="1" btn_href="addCheck.do" force>删除</button>
						
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
							<td width="10%">提出日期</td>
							<td width="10%">保存时间</td>
							<td width="10%">开始日期</td>
							<td width="10%">结束日期</td>
							<td width="15%">状态</td>
						</tr>
						
						<!-- JSP的for循环开始 -->
						<% 
							for(int i = 0;i < rs.getRowCount();i++){
						%>
						<tr>
							<td align="center" height="24" btn_condition='<%=rs.getString(i,"dt_state") %>'>
							<%=(i+1) %><input type="hidden" name="dt_no_arr" value="<%=rs.getString(i, "dt_no") %>"/>
							</td>
							<td align="left"><a href="#" onclick="contView();return false;"><%=rs.getString(i,"dt_sys") %></a></td>
							<td align="center"><%=DateUtil.formatFromDB(rs.getString(i,"dt_date")) %></td>
							<td align="center"><%=DateUtil.formatDateTimeFromDB(rs.getString(i,"dt_sav_time")) %></td>
							<td align="center"><%=DateUtil.formatFromDB(rs.getString(i,"dt_stat_date")) %></td>
							<td align="center"><%=DateUtil.formatFromDB(rs.getString(i,"dt_end_date")) %></td>
							<td align="center"><%=ChangeStat.getValue(rs.getString(i,"dt_state")) %></td>
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
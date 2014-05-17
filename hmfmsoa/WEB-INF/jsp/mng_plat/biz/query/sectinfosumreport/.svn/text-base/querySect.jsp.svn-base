<%@page import="java.math.BigDecimal"%>
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
	
	
	Result hoInfo=(Result)request.getAttribute("hoInfo");
	
	
	BigDecimal sum=new BigDecimal(BigDecimal.ZERO.toString());
	%> 
<html>
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
		//查看详细信息
		function goToSee(){
			var flag=$("#flag").val();
			if(confirm("确认要导出吗?")){
				$.webUtil.submit('printInExcel.do?flag='+flag);
			}
		}
	</script> 
	<body>
		<form name="form1" method="post" action="">
		<input type="hidden" name="flag" id="flag" value="<%=request.getParameter("flag") %>" /> 
		<input type="hidden" name="sect_id" id="sect_id" value="" /> 
			<table border="0" cellpadding="0" cellspacing="0" width="960" style="margin:0 auto">
			<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>小区基本情况汇总表</div></td></tr>
				<tr>
					<td height="<%=_PAGEBLOCK_HEIGHT%>"></td>
				</tr>
				<tr>
					<td width="100%">
						<div class="btn_toolbar">
						<%if(rs!=null){ %>
						<%if(rs.getRowCount()!=0) {%>
						<button class="btn" type="button" value="1" btn_href="printInExcel.do?flag=1" istip="1" ismask="0" force>导出</button>&nbsp;
						<%}}else if(hoInfo!=null){  if(hoInfo.getRowCount()!=0){%>
						<button class="btn" type="button" value="1" btn_href="printInExcel.do?flag=2" istip="1" ismask="0" force>导出</button>&nbsp;
						<%}} %>
						<button class="btn" type="button" value="2" onclick="javascript:history.back();" force>返回</button>
					</div>
					</td>
				</tr>
				<tr>
					<td height="<%=_PAGEBLOCK_HEIGHT%>"></td>
				</tr>
			<% 
			if(rs!=null){
				if(rs.getRowCount()!=0){ 
			%>
			<%if(rs.getRowCount()>9){%>
				<table  border="0" cellpadding="0" width="1240" cellspacing="0"  align="center" >
			<% }else{%>
			
			<table  border="0" cellpadding="0" cellspacing="0"  align="center" >	
			<% }%>
				<tr> 
					<td>
						<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolor="#000000" background="<c:out value="${ctx}"/>/images/yulan_bg.gif">
							<tr align="left">
								
								<td width="180" colspan="4" align="center" >序号</td>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="center"><%=i+1 %></td>
								<%} %>
								<td width="80" align="center" rowspan="2">合计</td>
							</tr>
							<tr>
								<td width="180" colspan="4" align="center">街道</td>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="center"><%=rs.getString(i, "str_name").equals("")?"未匹配":rs.getString(i, "str_name") %></td>
								<%} %>
							</tr>
							<%--
							<tr>
								<td width="180" colspan="4">居委会数</td>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_cmt_id") %></td>
								<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_cmt_id"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							 --%>
							<tr>
								<td width="40" rowspan="5" align="center" >住宅小区数</td>
								<td width="140" colspan="3" align="center" >总数</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "sect") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "sect"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="3" align="center" >商品房</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_spf") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_spf"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="3" align="center">售后房</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_shf") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_shf"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="3" align="center">公房</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_gf") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_gf"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="3" align="center">混合</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_hh") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_hh"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">非住宅小区数</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_fzz") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_fzz"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">物业公司数</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_csp_name") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_csp_name"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">业委会数</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_hoc_id") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_hoc_id"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">小区经理</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_csm_name") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_csm_name"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">电梯数</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "lift_num").equals("")?"0":rs.getString(i, "lift_num") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "lift_num").equals("")?"0":rs.getString(i, "lift_num"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">地面机动车位数</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_op") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_op"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">地下机动车位数</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_up").equals("")?0:rs.getString(i, "tot_up") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_up").equals("")?"0":rs.getString(i, "tot_up"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">建筑面积</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_cnst_area").equals("")?0:rs.getString(i, "tot_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_cnst_area").equals("")?"0":rs.getString(i, "tot_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<%--
							<tr>
								<td width="10" colspan="4">居住面积</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_live_area").equals("")?0:rs.getString(i, "tot_live_area") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_live_area").equals("")?"0":rs.getString(i, "tot_live_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4">计租面积</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_rent_area").equals("")?0:rs.getString(i, "tot_rent_area") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_rent_area").equals("")?"0":rs.getString(i, "tot_rent_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							 --%>
							<tr>
								<td width="10" colspan="4" align="center">占地面积</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_land_area").equals("")?0:rs.getString(i, "tot_land_area") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_land_area").equals("")?"0":rs.getString(i, "tot_land_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">绿化面积</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_aff_area").equals("")?0:rs.getString(i, "tot_aff_area") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_aff_area").equals("")?"0":rs.getString(i, "tot_aff_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<!-- 小区面积类 先省略-->
							<tr>
								<td width="40" rowspan="9" align="center">小区面积</td>
								<td width="30" rowspan="4" align="center">住宅</td>
								<td width="100" colspan="2" align="center">商品房面积</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_condo_hous_area").equals("")?"0":rs.getString(i, "tot_condo_hous_area") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_condo_hous_area").equals("")?"0":rs.getString(i, "tot_condo_hous_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="2" align="center">公房面积</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "xt_cnst_area").equals("")?"0":rs.getString(i, "xt_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "xt_cnst_area").equals("")?"0":rs.getString(i, "xt_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="2" align="center" >售后房面积</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "zg_cnst_area").equals("")?"0":rs.getString(i, "zg_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "zg_cnst_area").equals("")?"0":rs.getString(i, "zg_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="2" align="center">其他产权房面积</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "qt_cnst_area").equals("")?"0":rs.getString(i, "qt_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "qt_cnst_area").equals("")?"0":rs.getString(i, "qt_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
							<td width="30" rowspan="4" align="center">非住宅</td>
								<td width="100" colspan="2" align="center">商品房面积</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "fzz_tot_condo_hous_area").equals("")?"0":rs.getString(i, "fzz_tot_condo_hous_area") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "fzz_tot_condo_hous_area").equals("")?"0":rs.getString(i, "fzz_tot_condo_hous_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="2" align="center">公房面积</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "fzz_xt_cnst_area").equals("")?"0":rs.getString(i, "fzz_xt_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "fzz_xt_cnst_area").equals("")?"0":rs.getString(i, "fzz_xt_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="2" align="center">售后房面积</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "fzz_zg_cnst_area").equals("")?"0":rs.getString(i, "fzz_zg_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "fzz_zg_cnst_area").equals("")?"0":rs.getString(i, "fzz_zg_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="2" align="center">其他产权房面积</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "fzz_qt_cnst_area").equals("")?"0":rs.getString(i, "fzz_qt_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "fzz_qt_cnst_area").equals("")?"0":rs.getString(i, "fzz_qt_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td colspan="3" align="center">合计</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
									<%BigDecimal suminfo=new BigDecimal("0"); %>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_condo_hous_area").equals("")?"0":rs.getString(i, "tot_condo_hous_area"))).add(new BigDecimal(rs.getString(i, "xt_cnst_area").equals("")?"0":rs.getString(i, "xt_cnst_area"))).add(new BigDecimal(rs.getString(i, "zg_cnst_area").equals("")?"0":rs.getString(i, "zg_cnst_area"))).add(new BigDecimal(rs.getString(i, "qt_cnst_area").equals("")?"0":rs.getString(i, "qt_cnst_area"))).add(new BigDecimal(rs.getString(i, "fzz_tot_condo_hous_area").equals("")?"0":rs.getString(i, "fzz_tot_condo_hous_area"))).add(new BigDecimal(rs.getString(i, "fzz_xt_cnst_area").equals("")?"0":rs.getString(i, "fzz_xt_cnst_area"))).add(new BigDecimal(rs.getString(i, "fzz_zg_cnst_area").equals("")?"0":rs.getString(i, "fzz_zg_cnst_area"))).add(new BigDecimal(rs.getString(i, "fzz_qt_cnst_area").equals("")?"0":rs.getString(i, "fzz_qt_cnst_area")));%>
								<td width="80" align="right">
									<%=suminfo.add(new BigDecimal(rs.getString(i, "tot_condo_hous_area").equals("")?"0":rs.getString(i, "tot_condo_hous_area"))).add(new BigDecimal(rs.getString(i, "xt_cnst_area").equals("")?"0":rs.getString(i, "xt_cnst_area"))).add(new BigDecimal(rs.getString(i, "zg_cnst_area").equals("")?"0":rs.getString(i, "zg_cnst_area"))).add(new BigDecimal(rs.getString(i, "qt_cnst_area").equals("")?"0":rs.getString(i, "qt_cnst_area"))).add(new BigDecimal(rs.getString(i, "fzz_tot_condo_hous_area").equals("")?"0":rs.getString(i, "fzz_tot_condo_hous_area"))).add(new BigDecimal(rs.getString(i, "fzz_xt_cnst_area").equals("")?"0":rs.getString(i, "fzz_xt_cnst_area"))).add(new BigDecimal(rs.getString(i, "fzz_zg_cnst_area").equals("")?"0":rs.getString(i, "fzz_zg_cnst_area"))).add(new BigDecimal(rs.getString(i, "fzz_qt_cnst_area").equals("")?"0":rs.getString(i, "fzz_qt_cnst_area")))%>
								</td>
								<%} %>									
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<!-- 测试结束 -->
							<tr>
								<td width="10" colspan="4" align="center">总户数</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_hous").equals("")?"0":rs.getString(i, "tot_hous") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_hous").equals("")?"0":rs.getString(i, "tot_hous"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">水箱数</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_tanks").equals("")?"0":rs.getString(i, "tot_tanks") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_tanks").equals("")?"0":rs.getString(i, "tot_tanks"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">蓄水池数</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_reservoirs").equals("")?"0":rs.getString(i, "tot_reservoirs") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_reservoirs").equals("")?"0":rs.getString(i, "tot_reservoirs"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">生活水泵数</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_pumps").equals("")?"0":rs.getString(i, "tot_pumps") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_pumps").equals("")?"0":rs.getString(i, "tot_pumps"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
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
						没有找到匹配的街道小区信息！
					</td>
				</tr>
			</table>
			<%} %>
			<%} %>
			<% if(hoInfo!=null){
				if(hoInfo.getRowCount()!=0){ 
			
			%>
			<table>
				<tr>
					<td height="<%=_PAGEBLOCK_HEIGHT%>"></td>
				</tr>
				<tr>
					<td height="<%=_PAGEBLOCK_HEIGHT%>"></td>
				</tr>
				<tr>
					<td height="<%=_PAGEBLOCK_HEIGHT%>"></td>
				</tr>
				<tr>
					<td height="<%=_PAGEBLOCK_HEIGHT%>"></td>
				</tr>
			</table>
			<%if(hoInfo.getRowCount()>9){%>
				<table border="0" cellpadding="0" width="1240" cellspacing="0"  align="center" >
			<% }else{%>
			
			<table border="0" cellpadding="0" cellspacing="0"  align="center" >	
			<% }%>
				<tr> 
					<td>
						<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolor="#000000" background="<c:out value="${ctx}"/>/images/yulan_bg.gif">
							<tr align="left">
								
								<td width="180" colspan="4" align="center">序号</td>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="center"><%=i+1 %></td>
								<%} %>
								<td width="80" align="center" rowspan="2">合计</td>
							</tr>
							<tr>
								<td width="180" colspan="4" align="center">房办</td>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="center"><%=hoInfo.getString(i, "ho_name").equals("")?"未匹配":hoInfo.getString(i, "ho_name") %></td>
								<%} %>
							</tr>
							<%--
							<tr>
								<td width="180" colspan="4">居委会数</td>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_cmt_id") %></td>
								<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_cmt_id"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							 --%>
							<tr>
								<td width="40" rowspan="5" align="center">住宅小区数</td>
								<td width="140" colspan="3" align="center">总数</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "sect") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "sect"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="3" align="center">商品房</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_spf") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_spf"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="3" align="center">售后房</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_shf") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_shf"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="3" align="center">公房</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_gf") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_gf"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="3" align="center">混合</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_hh") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_hh"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">非住宅小区数</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_fzz") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_fzz"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">物业公司数</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_csp_name") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_csp_name"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">业委会数</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_hoc_id") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_hoc_id"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">小区经理</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_csm_name") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_csm_name"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">电梯数</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "lift_num").equals("")?"0":hoInfo.getString(i, "lift_num") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "lift_num").equals("")?"0":hoInfo.getString(i, "lift_num"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">地面机动车位数</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_op") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_op"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">地下机动车位数</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_up").equals("")?0:hoInfo.getString(i, "tot_up") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_up").equals("")?"0":hoInfo.getString(i, "tot_up"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							
							<tr>
								<td width="10" colspan="4" align="center">建筑面积</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_cnst_area").equals("")?0:hoInfo.getString(i, "tot_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_cnst_area").equals("")?"0":hoInfo.getString(i, "tot_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<%--
							<tr>
								<td width="10" colspan="4">居住面积</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_live_area").equals("")?0:hoInfo.getString(i, "tot_live_area") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_live_area").equals("")?"0":hoInfo.getString(i, "tot_live_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							
							<tr>
								<td width="10" colspan="4">计租面积</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_rent_area").equals("")?0:hoInfo.getString(i, "tot_rent_area") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_rent_area").equals("")?"0":hoInfo.getString(i, "tot_rent_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							 --%>
							<tr>
								<td width="10" colspan="4" align="center">占地面积</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_land_area").equals("")?0:hoInfo.getString(i, "tot_land_area") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_land_area").equals("")?"0":hoInfo.getString(i, "tot_land_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">绿化面积</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_aff_area").equals("")?0:hoInfo.getString(i, "tot_aff_area") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_aff_area").equals("")?"0":hoInfo.getString(i, "tot_aff_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<!-- 小区面积类 先省略-->
							<tr>
								<td width="40" rowspan="9" align="center">小区面积</td>
								<td width="30" rowspan="4" align="center">住宅</td>
								<td width="100" colspan="2" align="center">商品房面积</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_condo_hous_area").equals("")?"0":hoInfo.getString(i, "tot_condo_hous_area") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_condo_hous_area").equals("")?"0":hoInfo.getString(i, "tot_condo_hous_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="2" align="center">公房面积</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "xt_cnst_area").equals("")?"0":hoInfo.getString(i, "xt_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "xt_cnst_area").equals("")?"0":hoInfo.getString(i, "xt_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="2" align="center">售后房面积</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "zg_cnst_area").equals("")?"0":hoInfo.getString(i, "zg_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "zg_cnst_area").equals("")?"0":hoInfo.getString(i, "zg_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="2" align="center">其他产权房面积</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "qt_cnst_area").equals("")?"0":hoInfo.getString(i, "qt_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "qt_cnst_area").equals("")?"0":hoInfo.getString(i, "qt_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
							<td width="30" rowspan="4" align="center">非住宅</td>
								<td width="100" colspan="2" align="center">商品房面积</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "fzz_tot_condo_hous_area").equals("")?"0":hoInfo.getString(i, "fzz_tot_condo_hous_area") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "fzz_tot_condo_hous_area").equals("")?"0":hoInfo.getString(i, "fzz_tot_condo_hous_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="2" align="center">公房面积</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "fzz_xt_cnst_area").equals("")?"0":hoInfo.getString(i, "fzz_xt_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "fzz_xt_cnst_area").equals("")?"0":hoInfo.getString(i, "fzz_xt_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="2" align="center">售后房面积</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "fzz_zg_cnst_area").equals("")?"0":hoInfo.getString(i, "fzz_zg_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "fzz_zg_cnst_area").equals("")?"0":hoInfo.getString(i, "fzz_zg_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="2" align="center">其他产权房面积</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "fzz_qt_cnst_area").equals("")?"0":hoInfo.getString(i, "fzz_qt_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "fzz_qt_cnst_area").equals("")?"0":hoInfo.getString(i, "fzz_qt_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td colspan="3" align="center">合计</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
									<%BigDecimal suminfo=new BigDecimal("0"); %>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_condo_hous_area").equals("")?"0":hoInfo.getString(i, "tot_condo_hous_area"))).add(new BigDecimal(hoInfo.getString(i, "xt_cnst_area").equals("")?"0":hoInfo.getString(i, "xt_cnst_area"))).add(new BigDecimal(hoInfo.getString(i, "zg_cnst_area").equals("")?"0":hoInfo.getString(i, "zg_cnst_area"))).add(new BigDecimal(hoInfo.getString(i, "qt_cnst_area").equals("")?"0":hoInfo.getString(i, "qt_cnst_area"))).add(new BigDecimal(hoInfo.getString(i, "fzz_tot_condo_hous_area").equals("")?"0":hoInfo.getString(i, "fzz_tot_condo_hous_area"))).add(new BigDecimal(hoInfo.getString(i, "fzz_xt_cnst_area").equals("")?"0":hoInfo.getString(i, "fzz_xt_cnst_area"))).add(new BigDecimal(hoInfo.getString(i, "fzz_zg_cnst_area").equals("")?"0":hoInfo.getString(i, "fzz_zg_cnst_area"))).add(new BigDecimal(hoInfo.getString(i, "fzz_qt_cnst_area").equals("")?"0":hoInfo.getString(i, "fzz_qt_cnst_area")));%>
								<td width="80" align="right">
									<%=suminfo.add(new BigDecimal(hoInfo.getString(i, "tot_condo_hous_area").equals("")?"0":hoInfo.getString(i, "tot_condo_hous_area"))).add(new BigDecimal(hoInfo.getString(i, "xt_cnst_area").equals("")?"0":hoInfo.getString(i, "xt_cnst_area"))).add(new BigDecimal(hoInfo.getString(i, "zg_cnst_area").equals("")?"0":hoInfo.getString(i, "zg_cnst_area"))).add(new BigDecimal(hoInfo.getString(i, "qt_cnst_area").equals("")?"0":hoInfo.getString(i, "qt_cnst_area"))).add(new BigDecimal(hoInfo.getString(i, "fzz_tot_condo_hous_area").equals("")?"0":hoInfo.getString(i, "fzz_tot_condo_hous_area"))).add(new BigDecimal(hoInfo.getString(i, "fzz_xt_cnst_area").equals("")?"0":hoInfo.getString(i, "fzz_xt_cnst_area"))).add(new BigDecimal(hoInfo.getString(i, "fzz_zg_cnst_area").equals("")?"0":hoInfo.getString(i, "fzz_zg_cnst_area"))).add(new BigDecimal(hoInfo.getString(i, "fzz_qt_cnst_area").equals("")?"0":hoInfo.getString(i, "fzz_qt_cnst_area")))%>
								</td>
								<%} %>									
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<!-- 测试结束 -->
							<tr>
								<td width="10" colspan="4" align="center">总户数</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_hous").equals("")?"0":hoInfo.getString(i, "tot_hous") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_hous").equals("")?"0":hoInfo.getString(i, "tot_hous"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">水箱数</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_tanks").equals("")?"0":hoInfo.getString(i, "tot_tanks") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_tanks").equals("")?"0":hoInfo.getString(i, "tot_tanks"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">蓄水池数</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_reservoirs").equals("")?"0":hoInfo.getString(i, "tot_reservoirs") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_reservoirs").equals("")?"0":hoInfo.getString(i, "tot_reservoirs"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">生活水泵数</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_pumps").equals("")?"0":hoInfo.getString(i, "tot_pumps") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_pumps").equals("")?"0":hoInfo.getString(i, "tot_pumps"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
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
						没有找到匹配的房办小区信息！
					</td>
				</tr>
			</table>
			<%} }%>
		</form>
	</body>
</html>

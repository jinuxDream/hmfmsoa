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
		/* ��ҳ��ĳ�ʼ���� �����������У����û�г�ʼ���������в���Ҫд�κδ��롣*/
		$(document).ready(function(){	
			$('.btn').PicButton();
			var bar=$('.btn_toolbar').BtnToolBar();//��ʼ����ť������(��һ��button��ɵ�div,������htmlʵ����.btn_toolbar .btn .btn_condition ������ʽ���Ʋ��ܱ�֮�����������Ըı�)
			var g=$('.grid1').datagrid({toolbar:bar});//��ִ�д��� g[0].grid.setBar(bar);	Ҳ����
		});	
		function onSelectRow(sect_id){
				$("#sect_id").val(sect_id);
			}
		//�鿴��ϸ��Ϣ
		function goToSee(){
			var flag=$("#flag").val();
			if(confirm("ȷ��Ҫ������?")){
				$.webUtil.submit('printInExcel.do?flag='+flag);
			}
		}
	</script> 
	<body>
		<form name="form1" method="post" action="">
		<input type="hidden" name="flag" id="flag" value="<%=request.getParameter("flag") %>" /> 
		<input type="hidden" name="sect_id" id="sect_id" value="" /> 
			<table border="0" cellpadding="0" cellspacing="0" width="960" style="margin:0 auto">
			<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>С������������ܱ�</div></td></tr>
				<tr>
					<td height="<%=_PAGEBLOCK_HEIGHT%>"></td>
				</tr>
				<tr>
					<td width="100%">
						<div class="btn_toolbar">
						<%if(rs!=null){ %>
						<%if(rs.getRowCount()!=0) {%>
						<button class="btn" type="button" value="1" btn_href="printInExcel.do?flag=1" istip="1" ismask="0" force>����</button>&nbsp;
						<%}}else if(hoInfo!=null){  if(hoInfo.getRowCount()!=0){%>
						<button class="btn" type="button" value="1" btn_href="printInExcel.do?flag=2" istip="1" ismask="0" force>����</button>&nbsp;
						<%}} %>
						<button class="btn" type="button" value="2" onclick="javascript:history.back();" force>����</button>
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
								
								<td width="180" colspan="4" align="center" >���</td>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="center"><%=i+1 %></td>
								<%} %>
								<td width="80" align="center" rowspan="2">�ϼ�</td>
							</tr>
							<tr>
								<td width="180" colspan="4" align="center">�ֵ�</td>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="center"><%=rs.getString(i, "str_name").equals("")?"δƥ��":rs.getString(i, "str_name") %></td>
								<%} %>
							</tr>
							<%--
							<tr>
								<td width="180" colspan="4">��ί����</td>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_cmt_id") %></td>
								<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_cmt_id"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							 --%>
							<tr>
								<td width="40" rowspan="5" align="center" >סլС����</td>
								<td width="140" colspan="3" align="center" >����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "sect") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "sect"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="3" align="center" >��Ʒ��</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_spf") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_spf"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="3" align="center">�ۺ�</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_shf") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_shf"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="3" align="center">����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_gf") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_gf"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="3" align="center">���</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_hh") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_hh"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">��סլС����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_fzz") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_fzz"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">��ҵ��˾��</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_csp_name") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_csp_name"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">ҵί����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_hoc_id") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_hoc_id"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">С������</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_csm_name") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_csm_name"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">������</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "lift_num").equals("")?"0":rs.getString(i, "lift_num") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "lift_num").equals("")?"0":rs.getString(i, "lift_num"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">���������λ��</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_op") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_op"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">���»�����λ��</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_up").equals("")?0:rs.getString(i, "tot_up") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_up").equals("")?"0":rs.getString(i, "tot_up"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">�������</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_cnst_area").equals("")?0:rs.getString(i, "tot_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_cnst_area").equals("")?"0":rs.getString(i, "tot_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<%--
							<tr>
								<td width="10" colspan="4">��ס���</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_live_area").equals("")?0:rs.getString(i, "tot_live_area") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_live_area").equals("")?"0":rs.getString(i, "tot_live_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4">�������</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_rent_area").equals("")?0:rs.getString(i, "tot_rent_area") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_rent_area").equals("")?"0":rs.getString(i, "tot_rent_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							 --%>
							<tr>
								<td width="10" colspan="4" align="center">ռ�����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_land_area").equals("")?0:rs.getString(i, "tot_land_area") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_land_area").equals("")?"0":rs.getString(i, "tot_land_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">�̻����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_aff_area").equals("")?0:rs.getString(i, "tot_aff_area") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_aff_area").equals("")?"0":rs.getString(i, "tot_aff_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<!-- С������� ��ʡ��-->
							<tr>
								<td width="40" rowspan="9" align="center">С�����</td>
								<td width="30" rowspan="4" align="center">סլ</td>
								<td width="100" colspan="2" align="center">��Ʒ�����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_condo_hous_area").equals("")?"0":rs.getString(i, "tot_condo_hous_area") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_condo_hous_area").equals("")?"0":rs.getString(i, "tot_condo_hous_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="2" align="center">�������</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "xt_cnst_area").equals("")?"0":rs.getString(i, "xt_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "xt_cnst_area").equals("")?"0":rs.getString(i, "xt_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="2" align="center" >�ۺ����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "zg_cnst_area").equals("")?"0":rs.getString(i, "zg_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "zg_cnst_area").equals("")?"0":rs.getString(i, "zg_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="2" align="center">������Ȩ�����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "qt_cnst_area").equals("")?"0":rs.getString(i, "qt_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "qt_cnst_area").equals("")?"0":rs.getString(i, "qt_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
							<td width="30" rowspan="4" align="center">��סլ</td>
								<td width="100" colspan="2" align="center">��Ʒ�����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "fzz_tot_condo_hous_area").equals("")?"0":rs.getString(i, "fzz_tot_condo_hous_area") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "fzz_tot_condo_hous_area").equals("")?"0":rs.getString(i, "fzz_tot_condo_hous_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="2" align="center">�������</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "fzz_xt_cnst_area").equals("")?"0":rs.getString(i, "fzz_xt_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "fzz_xt_cnst_area").equals("")?"0":rs.getString(i, "fzz_xt_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="2" align="center">�ۺ����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "fzz_zg_cnst_area").equals("")?"0":rs.getString(i, "fzz_zg_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "fzz_zg_cnst_area").equals("")?"0":rs.getString(i, "fzz_zg_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="2" align="center">������Ȩ�����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "fzz_qt_cnst_area").equals("")?"0":rs.getString(i, "fzz_qt_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "fzz_qt_cnst_area").equals("")?"0":rs.getString(i, "fzz_qt_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td colspan="3" align="center">�ϼ�</td>
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
							<!-- ���Խ��� -->
							<tr>
								<td width="10" colspan="4" align="center">�ܻ���</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_hous").equals("")?"0":rs.getString(i, "tot_hous") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_hous").equals("")?"0":rs.getString(i, "tot_hous"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">ˮ����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_tanks").equals("")?"0":rs.getString(i, "tot_tanks") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_tanks").equals("")?"0":rs.getString(i, "tot_tanks"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">��ˮ����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<rs.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=rs.getString(i, "tot_reservoirs").equals("")?"0":rs.getString(i, "tot_reservoirs") %></td>
									<%sum=sum.add(new BigDecimal(rs.getString(i, "tot_reservoirs").equals("")?"0":rs.getString(i, "tot_reservoirs"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">����ˮ����</td>
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
						û���ҵ�ƥ��Ľֵ�С����Ϣ��
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
								
								<td width="180" colspan="4" align="center">���</td>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="center"><%=i+1 %></td>
								<%} %>
								<td width="80" align="center" rowspan="2">�ϼ�</td>
							</tr>
							<tr>
								<td width="180" colspan="4" align="center">����</td>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="center"><%=hoInfo.getString(i, "ho_name").equals("")?"δƥ��":hoInfo.getString(i, "ho_name") %></td>
								<%} %>
							</tr>
							<%--
							<tr>
								<td width="180" colspan="4">��ί����</td>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_cmt_id") %></td>
								<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_cmt_id"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							 --%>
							<tr>
								<td width="40" rowspan="5" align="center">סլС����</td>
								<td width="140" colspan="3" align="center">����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "sect") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "sect"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="3" align="center">��Ʒ��</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_spf") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_spf"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="3" align="center">�ۺ�</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_shf") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_shf"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="3" align="center">����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_gf") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_gf"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="3" align="center">���</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_hh") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_hh"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">��סլС����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_fzz") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_fzz"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">��ҵ��˾��</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_csp_name") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_csp_name"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">ҵί����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_hoc_id") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_hoc_id"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">С������</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_csm_name") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_csm_name"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">������</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "lift_num").equals("")?"0":hoInfo.getString(i, "lift_num") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "lift_num").equals("")?"0":hoInfo.getString(i, "lift_num"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">���������λ��</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_op") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_op"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">���»�����λ��</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_up").equals("")?0:hoInfo.getString(i, "tot_up") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_up").equals("")?"0":hoInfo.getString(i, "tot_up"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							
							<tr>
								<td width="10" colspan="4" align="center">�������</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_cnst_area").equals("")?0:hoInfo.getString(i, "tot_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_cnst_area").equals("")?"0":hoInfo.getString(i, "tot_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<%--
							<tr>
								<td width="10" colspan="4">��ס���</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_live_area").equals("")?0:hoInfo.getString(i, "tot_live_area") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_live_area").equals("")?"0":hoInfo.getString(i, "tot_live_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							
							<tr>
								<td width="10" colspan="4">�������</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_rent_area").equals("")?0:hoInfo.getString(i, "tot_rent_area") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_rent_area").equals("")?"0":hoInfo.getString(i, "tot_rent_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							 --%>
							<tr>
								<td width="10" colspan="4" align="center">ռ�����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_land_area").equals("")?0:hoInfo.getString(i, "tot_land_area") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_land_area").equals("")?"0":hoInfo.getString(i, "tot_land_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">�̻����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_aff_area").equals("")?0:hoInfo.getString(i, "tot_aff_area") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_aff_area").equals("")?"0":hoInfo.getString(i, "tot_aff_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<!-- С������� ��ʡ��-->
							<tr>
								<td width="40" rowspan="9" align="center">С�����</td>
								<td width="30" rowspan="4" align="center">סլ</td>
								<td width="100" colspan="2" align="center">��Ʒ�����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_condo_hous_area").equals("")?"0":hoInfo.getString(i, "tot_condo_hous_area") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_condo_hous_area").equals("")?"0":hoInfo.getString(i, "tot_condo_hous_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="2" align="center">�������</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "xt_cnst_area").equals("")?"0":hoInfo.getString(i, "xt_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "xt_cnst_area").equals("")?"0":hoInfo.getString(i, "xt_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="2" align="center">�ۺ����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "zg_cnst_area").equals("")?"0":hoInfo.getString(i, "zg_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "zg_cnst_area").equals("")?"0":hoInfo.getString(i, "zg_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="2" align="center">������Ȩ�����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "qt_cnst_area").equals("")?"0":hoInfo.getString(i, "qt_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "qt_cnst_area").equals("")?"0":hoInfo.getString(i, "qt_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
							<td width="30" rowspan="4" align="center">��סլ</td>
								<td width="100" colspan="2" align="center">��Ʒ�����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "fzz_tot_condo_hous_area").equals("")?"0":hoInfo.getString(i, "fzz_tot_condo_hous_area") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "fzz_tot_condo_hous_area").equals("")?"0":hoInfo.getString(i, "fzz_tot_condo_hous_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="2" align="center">�������</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "fzz_xt_cnst_area").equals("")?"0":hoInfo.getString(i, "fzz_xt_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "fzz_xt_cnst_area").equals("")?"0":hoInfo.getString(i, "fzz_xt_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="2" align="center">�ۺ����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "fzz_zg_cnst_area").equals("")?"0":hoInfo.getString(i, "fzz_zg_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "fzz_zg_cnst_area").equals("")?"0":hoInfo.getString(i, "fzz_zg_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="100" colspan="2" align="center">������Ȩ�����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "fzz_qt_cnst_area").equals("")?"0":hoInfo.getString(i, "fzz_qt_cnst_area") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "fzz_qt_cnst_area").equals("")?"0":hoInfo.getString(i, "fzz_qt_cnst_area"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td colspan="3" align="center">�ϼ�</td>
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
							<!-- ���Խ��� -->
							<tr>
								<td width="10" colspan="4" align="center">�ܻ���</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_hous").equals("")?"0":hoInfo.getString(i, "tot_hous") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_hous").equals("")?"0":hoInfo.getString(i, "tot_hous"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">ˮ����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_tanks").equals("")?"0":hoInfo.getString(i, "tot_tanks") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_tanks").equals("")?"0":hoInfo.getString(i, "tot_tanks"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">��ˮ����</td>
								<%sum=new BigDecimal(BigDecimal.ZERO.toString()); %>
								<% for(int i=0;i<hoInfo.getRowCount();i++){ %>
									<%if(i==12) break; %>
								<td width="80" align="right"><%=hoInfo.getString(i, "tot_reservoirs").equals("")?"0":hoInfo.getString(i, "tot_reservoirs") %></td>
									<%sum=sum.add(new BigDecimal(hoInfo.getString(i, "tot_reservoirs").equals("")?"0":hoInfo.getString(i, "tot_reservoirs"))); } %>
								<td width="80" align="right"><%=sum %></td>
							</tr>
							<tr>
								<td width="10" colspan="4" align="center">����ˮ����</td>
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
						û���ҵ�ƥ��ķ���С����Ϣ��
					</td>
				</tr>
			</table>
			<%} }%>
		</form>
	</body>
</html>

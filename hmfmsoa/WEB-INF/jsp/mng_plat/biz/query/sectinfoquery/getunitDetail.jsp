<%@page import="hmfms.util.DateUtil"%>
<%@page import="hmfms.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*,fd.commons.jdbc.Result" %>
<%@ include file="/jsp/commons/taglibs.jsp" %>
<%
	Result rs = (Result)request.getAttribute("rs");
%>


<%@page import="hmfms.services.codes.UnitKind"%>
<%@page import="hmfms.services.codes.LiftFlag"%>
<%@page import="hmfms.services.codes.InfoStatus"%><html>
<%@ include file="/jsp/commons/meta.jsp" %>
<script type="text/javascript">

/* 本页面的初始处理。 本函数必须有，如果没有初始处理，则函数中不需要写任何代码。*/
$(document).ready(function(){	
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_condition 三个样式名称不能变之外其他都可以改变)
	var g=$('.grid1').datagrid({toolbar:bar,trselect:false});//或执行代码 g[0].grid.setBar(bar);	也可以
	
	$('#finish_date').webDatepicker();

});	
</script>
<body>
<form name="form1" method="post" action="">
<table border="0" cellpadding="0" cellspacing="0" width="960" style="margin:0 auto">
	<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>小区信息查询→门牌详细信息</div></td></tr>
	<tr><td height="<%=_PAGEBLOCK_HEIGHT%>"></td></tr>
	<tr>
		<td width="100%">
			<div class="btn_toolbar" >
				<button class="btn" type="button" value="2" onclick="window.history.back()"  force>返回</button>
			</div>
		</td>
	</tr>
	<tr><td height="<%=_PAGEBLOCK_HEIGHT %>"></td></tr>
	<tr>
		<td width="100%">
			<table width="100%"  class="grid1">
				<thead>
			    	<tr>
			            <th colspan="4">门牌详细信息</th>
			        </tr>
			    </thead>
			    <tr>
					<td width="15%" align="center">单元号</td>
					<td width="35%" align="left" ><%=rs.getString(0,"unit_no") %></td>
					<td width="15%" align="center" >门牌地址</td>
				    <td width="35%" align="left" ><%=rs.getString(0,"unit_addr") %>
					</td>
				</tr>
				 <tr>
					<td width="15%" align="center">属性</td>
					<td width="35%" align="left" ><%=UnitKind.getValue(rs.getString(0,"unit_kind"))%>
					</td>
					<td width="15%" align="center" >电梯</td>
					<td width="35%" align="left" ><%=LiftFlag.getValue(rs.getString(0,"unit_flag"))%></td>
				</tr>
				<tr>
					<td width="15%" align="center">占地面积</td>
					<td width="35%" align="left" ><%=StringUtil.format2Decimal(rs.getString(0,"unit_area"))%></td>
					<td width="15%" align="center" >建筑面积</td>
					<td width="35%" align="left" ><%=StringUtil.format2Decimal(rs.getString(0,"unit_cnst_area"))%></td>
				</tr>
				<tr>
					<td width="15%" align="center">地下面积</td>
					<td width="35%" align="left" ><%=StringUtil.format2Decimal(rs.getString(0,"unit_undgrnd_area"))%></td>
					<td width="15%" align="center" >总层数</td>
					<td width="35%" align="left" ><%=rs.getString(0,"unit_tot_floor")%></td>
				</tr>
				
				<tr>
					<td width="15%" align="center" >总面积</td>
					<td width="35%" align="left" ><%=StringUtil.format2Decimal(rs.getString(0,"unit_tot_area"))%>
					</td>
					<td width="15%" align="center" >总户数</td>
					<td width="35%" align="left" ><%=rs.getString(0,"unit_tot_unit") %>
					</td>
				</tr>
				<tr>
					<td width="15%" align="center">电梯数</td>
				    <td width="35%"  align="left" ><%=rs.getString(0,"unit_tot_elevator") %>
					</td>
					<td width="15%" align="center" >生活水泵数</td>
					<td width="35%"  align="left" ><%=rs.getString(0,"unit_tot_pumps") %>
					</td>
			    	</tr>
			    	<tr>
						<td width="15%" align="center">水箱数</td>
					    <td width="35%" align="left" ><%=rs.getString(0,"unit_tot_tanks") %>
						</td>
						<td width="15%" align="center" >消防栓数</td>
						<td width="35%" align="left" ><%=rs.getString(0,"unit_tot_fire_hydrants") %>
						</td>
			    	</tr>
                    <tr>
                    	<td width="15%" align="center">消防通道数</td>
                    	 <td width="35%"  align="left"><%=rs.getString(0,"unit_tot_fire_engine_access") %>
						</td>
						<td width="15%" align="center">竣工日期</td>
					<td width="35%" align="left"><%=DateUtil.formatFromDB(rs.getString(0,"unit_finish_date")) %>
						</td>
                    </tr>
                    <tr>
                    <td width="15%" align="center" >房屋状态</td>
					<td width="35%"  align="left" ><%=InfoStatus.getValue(rs.getString(0,"unit_status")) %>
					</td>
					<td colspan=2 width="50%"></td>
					</tr>
			</table>	
		</td>
	</tr>
</table>
</form>
</body>
</html> 


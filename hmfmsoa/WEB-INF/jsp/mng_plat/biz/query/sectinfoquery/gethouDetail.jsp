<%@page import="hmfms.util.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*,fd.commons.jdbc.Result" %>
<%@ include file="/jsp/commons/taglibs.jsp" %>
<%
	Result rs = (Result)request.getAttribute("rs");
%>

<%@page import="hmfms.services.codes.HouseKind"%>
<%@page import="hmfms.util.StringUtil"%>
<%@page import="hmfms.services.codes.HouseUseType"%>
<%@page import="hmfms.services.codes.HouSrc"%>
<%@page import="hmfms.services.codes.InfoStatus"%><html>
<%@ include file="/jsp/commons/meta.jsp" %>
<script type="text/javascript">

/* 本页面的初始处理。 本函数必须有，如果没有初始处理，则函数中不需要写任何代码。*/
$(document).ready(function(){	
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_condition 三个样式名称不能变之外其他都可以改变)
	var g=$('.grid1').datagrid({toolbar:bar,trselect:false});//或执行代码 g[0].grid.setBar(bar);	也可以
	
});	

</script>
<body>
<form name="form1" method="post" action="">
<table border="0" cellpadding="0" cellspacing="0" width="960" style="margin:0 auto">
		<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>小区信息查询→分户详细信息</div></td></tr>
	<tr><td height="<%=_PAGEBLOCK_HEIGHT%>"></td></tr>
	<tr>
		<td width="100%">
			<div class="btn_toolbar" >
				<button class="btn" type="button" value="2" onclick="window.history.back()" force>返回</button>
			</div>
		</td>
	</tr>
	<tr><td height="<%=_PAGEBLOCK_HEIGHT %>"></td></tr>
	<tr>
		<td width="100%">
			<table width="100%"  class="grid1">
				<thead>
			    	<tr>
			            <th colspan="4">分户详细信息</th>
			        </tr>
			    </thead>
			    <tr>
					 <td width="20%" align="center" >室号</td>
					 <td> <%=rs.getString(0,"hou_no")%></td>
					 <td width="20%" align="center" >房屋性质</td>
					 <td width="30%" align="left" ><%=HouseKind.getValue(rs.getString(0,"hou_kind"))%>
					</td>
				</tr>
				 <tr>
					 <td width="20%" align="center" >建筑面积</td>
					  <td width="30%" align="left" ><%=StringUtil.format2Decimal(rs.getString(0,"hou_area"))%></td>
					<td width="20%" align="center">房屋类型</td>
					<td width="30%" align="left" ><%=HouseUseType.getValue(rs.getString(0,"hou_type"))%>
					</td>
				</tr>
				<tr>
					 <td align="center" >数据来源</td>
					 <td  align="left" >
							<%=HouSrc.getValue(rs.getString(0,"hou_create_by"))%>
						</td>
					<td align="center" >房屋状态</td>
						<td  align="left" ><%=InfoStatus.getValue(rs.getString(0,"HOU_CURR_STAT"))%>
						</td>
				</tr>
					<tr>
				     <td align="center">居住面积</td>
				     <td><%=StringUtil.format2Decimal(rs.getString(0,"hou_live_area"))%>
					</td>
					 <td align="center">计租面积</td>
				     <td ><%=StringUtil.format2Decimal(rs.getString(0,"hou_rent_area"))%>
					</td>
				</tr>	
			</table>
		</td>
	</tr>
</table>
</form>
</body>
</html> 


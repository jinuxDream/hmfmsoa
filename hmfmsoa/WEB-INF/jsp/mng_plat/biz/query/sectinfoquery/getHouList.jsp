<%@page import="hmfms.util.ObjectUtil"%>
<%@page import="hmfms.web.commons.SelectBoxHtml"%>
<%@page import="hmfms.util.BeanUtil"%>
<%@page import="hmfms.web.User"%>
<%@page import="hmfms.util.StringUtil"%>
<%@page import="fd.exception.BusinessException"%>
<%@page import="hmfms.util.ActionUtil"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,fd.commons.jdbc.Result" %>
<%@ include file="/jsp/commons/taglibs.jsp" %>
<%
	User user =	ActionUtil.getUser(request);
    Result rs=(Result)request.getAttribute("rs");
%>
<%@page import="hmfms.services.codes.LiftFlag"%>
<%@page import="hmfms.services.codes.InfoStatus"%>
<%@page import="hmfms.services.codes.HouseKind"%>
<%@page import="hmfms.services.codes.HouseUseType"%><html>
<%@ include file="/jsp/commons/meta.jsp" %>
<script type="text/javascript">

/* 本页面的初始处理。 本函数必须有，如果没有初始处理，则函数中不需要写任何代码。*/
$(document).ready(function(){	
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_condition 三个样式名称不能变之外其他都可以改变)
	var g=$('.grid1').datagrid({toolbar:bar});//或执行代码 g[0].grid.setBar(bar);
});	
function gotopage(hou_id){

	$.webUtil.submit("gethouDetail.do?hou_id="+hou_id);
	return false;
}

//查询
function query(){
	//去左右空格
	$("input[type='text']").each(function(){
		$(this).val($.trim($(this).val()));
	});
	$.webUtil.submit("getHouList.do");
}
</script>
<body>
<form name="form1" method="post" action="">
<input type="hidden" name="unit_id_detail" id="unit_id_detail" value="<%=request.getParameter("unit_id_detail")%>" />
<input type="hidden" name="sect_id_detail" id="sect_id_detail" value="<%=request.getParameter("sect_id_detail")%>" /> 
<input type="hidden" name="unit_addr" id="unit_addr" value="<%=ObjectUtil.isEmpty(StringUtil.getString(request.getParameter("unit_addr")))?rs.getString(0, "unit_addr"):StringUtil.getString(request.getParameter("unit_addr"))%>" /> 
<input type="hidden" name="st_name_frst" id="st_name_frst" value="<%=ObjectUtil.isEmpty(StringUtil.getString(request.getParameter("st_name_frst")))?rs.getString(0, "st_name_frst"):StringUtil.getString(request.getParameter("st_name_frst"))%>" /> 
<input type="hidden" name="st_addr_frst" id="st_addr_frst" value="<%=ObjectUtil.isEmpty(StringUtil.getString(request.getParameter("st_addr_frst")))?rs.getString(0, "st_addr_frst"):StringUtil.getString(request.getParameter("st_addr_frst"))%>" /> 

<table border="0" cellpadding="0" cellspacing="0" width="960" style="margin:0 auto">
	<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>小区信息查询→分户列表</div></td></tr>
	<tr><td height="<%=_PAGEBLOCK_HEIGHT %>"></td></tr>
	<tr><td class="subup">门牌地址：<%=ObjectUtil.isEmpty(StringUtil.getString(request.getParameter("unit_addr")))?rs.getString(0, "unit_addr"):StringUtil.getString(request.getParameter("unit_addr"))%>
	&nbsp;&nbsp;小区名称：<%=ObjectUtil.isEmpty(StringUtil.getString(request.getParameter("st_name_frst")))?rs.getString(0, "st_name_frst"):StringUtil.getString(request.getParameter("st_name_frst"))%>&nbsp;&nbsp;
	小区地址：<%=ObjectUtil.isEmpty(StringUtil.getString(request.getParameter("st_addr_frst")))?rs.getString(0, "st_addr_frst"):StringUtil.getString(request.getParameter("st_addr_frst"))%></td></tr>
	<tr><td height="<%=_PAGEBLOCK_HEIGHT %>"></td></tr>
	<tr><td>
	<div class="wrap-paramarea">
							<div class="fl mr5 mt2">
							分户地址：<input type="text" id="hou_addr" name="hou_addr" size="20" maxlength="200" value = "<%=StringUtil.getString(request.getParameter("hou_addr")) %>" />
							&nbsp;
							房屋状态：
							<select   name="unit_status" id="unit_status">
								<option value="all" >-全部-</option>
								<% 
									List listUnit_status = new ArrayList();
									listUnit_status.add(InfoStatus.ZhuXiao.toString());
								%>
								<%=SelectBoxHtml.genOptionsWithDefault(InfoStatus.getCodeList(),StringUtil.getString(request.getParameter("unit_status")),listUnit_status) %>
							</select>
							&nbsp;
							<button class="btn" type="button" onClick="query();return false;" mask>重新筛选</button> 
					</div>
					<div>
						<div class="clear"></div>
					</div>
				</div>
	</td></tr>
	<tr>
		<td width="100%">
			<div class="btn_toolbar" >
				<button class="btn" type="button" value="1" onclick="window.history.back()" force>返回</button>
			</div>
		</td>
	</tr>			
	
	<tr>
	    <td>
				<table class="grid1"  width="100%" >
	                 <tr align="center" height="40px">
							<td width="10%">序号</td>
							<td sortable filterable  width="8%">室号</td>
							<td sortable filterable width="35%">分户地址</td>
							<td sortable filterable width="10%">分户面积</td>
							<td sortable filterable width="10%">房屋性质</td>
							<td sortable filterable width="10%">房屋类型</td>
							<td sortable filterable width="10%">电梯标志</td>
						    <td sortable filterable>房屋状态</td>
					</tr>
				  	<%
						for(int i=0;i<rs.getRowCount();i++){
					%>
				  	<tr id="clos" height="24")">
                   	 	<td align="center" ><%=i+1 %></td>
                   	 	<td align="center" ><%=rs.getString(i,"hou_no")%></td>
                   	 	<td align="left" ><a href="#" onclick="gotopage('<%=rs.getString(i,"hou_id") %>');return false;"><%=rs.getString(i,"hou_addr") %></a></td>
                    	<td align="right" ><%=StringUtil.format2Decimal(rs.getString(i,"hou_area")) %></td>
                    	<td align="center" ><%=HouseKind.getValue(rs.getString(i,"hou_kind")) %></td>
                    	<td align="center" ><%=HouseUseType.getValue(rs.getString(i,"hou_type")) %></td>
                    	<td align="center" ><%=LiftFlag.getValue(rs.getString(i,"hou_lift_flag"))%></td>    
                    	<td align="center" ><%=InfoStatus.getValue(rs.getString(i,"HOU_CURR_STAT"))%></td>          
				  	</tr>
				  	<%} %> 
				</table>
  		</td>
   	</tr>
   	<tr>
   		<td align="right">
   				<table border="0" cellspacing="0" cellpadding="0">
              		<tr>
                		<td>
							<%=rs.getPage()!=null? rs.getPage().getPageHtml("form1","getHouList.do"):""%>
						</td>
              		</tr>
				</table>
   		</td>
   	</tr>
</table>
</form>
</body>
</html> 


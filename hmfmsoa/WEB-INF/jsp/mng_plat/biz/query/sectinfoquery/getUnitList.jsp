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
<%@page import="hmfms.web.commons.SelectBoxHtml"%>
<%@page import="hmfms.services.codes.UnitKind"%>
<%@page import="hmfms.services.codes.LiftFlag"%>
<%@page import="hmfms.services.codes.InfoStatus"%><html>
<%@ include file="/jsp/commons/meta.jsp" %>
<script type="text/javascript">

/* 本页面的初始处理。 本函数必须有，如果没有初始处理，则函数中不需要写任何代码。*/
$(document).ready(function(){	
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_condition 三个样式名称不能变之外其他都可以改变)
	var g=$('.grid1').datagrid({toolbar:bar});//或执行代码 g[0].grid.setBar(bar);
});	
function onSelectRow(index,val){
	$("#unit_id_detail").val(val);
}
function gotoQuery()
{
	
	$.webUtil.submit("getUnitList.do?sect_id_detail="+$("#sect_id_detail").val());
}
function gotopage(unit_id){

	$.webUtil.submit("getunitDetail.do?unit_id="+unit_id);
	return false;
}
</script>
<body>
<form name="form1" method="post" action="">
<input type="hidden" name="unit_id_detail" id="unit_id_detail" value="">
<input type="hidden" name="sect_id_detail" id="sect_id_detail" value="<%=request.getParameter("sect_id_detail")%>" /> 
<table border="0" cellpadding="0" cellspacing="0" width="960" style="margin:0 auto">
	<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>小区信息查询→门牌列表</div></td></tr>
	<tr><td height="<%=_PAGEBLOCK_HEIGHT %>"></td></tr>
	<tr><td class="subup">小区名称：<%=rs.getString(0,"st_name_frst")%>&nbsp;&nbsp;小区地址：<%=rs.getString(0,"st_addr_frst")%></td></tr>
	<tr><td height="<%=_PAGEBLOCK_HEIGHT %>"></td></tr>
	<tr>
		<td width="100%" class="frameblue">
			<div class="wrap-paramarea">
				<div class="fl mr5 mt2">
				门牌地址：<input type="text" id="unit_addr" name="unit_addr" size="20" maxlength="200" value = "<%=StringUtil.getString(request.getParameter("unit_addr"))%>" />&nbsp;&nbsp;
				属性：<select id="unit_kind" name="unit_kind" v_empty="1" style="width: 70px">
				            <option value="">-请选择-</option>
							<%=SelectBoxHtml.genOptionsWithDefault(UnitKind.getCodeList(),request.getParameter("unit_kind")) %></select>
				电梯：<select id="unit_flag" name="unit_flag" v_empty="1" style="width: 70px">
				      <option value="">-请选择-</option>
				      <%=SelectBoxHtml.genOptionsWithDefault(LiftFlag.getCodeList(),request.getParameter("unit_flag")) %></select>
				房屋状态：<select v_empty="1" name="unit_status" id="unit_status">
				<option value="all" <%=request.getAttribute("unit_status").equals("all")?"selected":"" %>>全部</option>
				<option value="10" <%=(request.getAttribute("unit_status").equals("10")||request.getAttribute("unit_status")==null)?"selected":"" %> >正常	</option>
				<option value="11"  <%=request.getAttribute("unit_status").toString().equals("11")?"selected":""%>>冗余</option>
				<option value="12"  <%=request.getAttribute("unit_status").toString().equals("12")?"selected":""%>>已拆迁</option>
				<option value="13"  <%=request.getAttribute("unit_status").toString().equals("13")?"selected":""%>>待拆迁</option>
				<option value="14"  <%=request.getAttribute("unit_status").toString().equals("14")?"selected":"" %>>其他</option>
			  </select>
				</div>
				<button class="btn" type="button" onClick="gotoQuery()" mask>重新筛选</button> 
				<div>
					<div class="clear"></div>
				</div>
			</div>
		</td>
	</tr>
	<tr><td height="<%=_PAGEBLOCK_HEIGHT%>"></td></tr>
	<tr>
		<td width="100%">
			<div class="btn_toolbar" >
				<%if(rs.getRowCount()>0){%>
					<button class="btn" type="button" value="1" btn_href="getHouList.do"  force>查看分户</button>
				<%} %>
				<button class="btn" type="button" value="1" onclick="window.history.back()" force>返回</button>
			</div>
		</td>
	</tr>			
	
	<tr>
	    <td>
				<table class="grid1"  width="100%" >
	                 <tr align="center" height="40px">
							<td width="10%">序号</td>
							<td sortable filterable width="10%">门牌编码</td>
							<td sortable filterable width="40%">门牌地址</td>
							<td sortable filterable width="10%">门牌属性</td>
							<td sortable filterable width="10%">电梯标志</td>
							<td sortable filterable width="10%">总户数</td>
						    <td sortable filterable >门牌状态</td>
					</tr>
				  	<%
						for(int i=0;i<rs.getRowCount();i++){
					%>
				  	<tr id="clos" height="24" onClick="onSelectRow(<%=i%>,<%=rs.getString(i,"unit_id") %>)">
                   	 	<td align="center" ><%=i+1 %></td>
                   	 	<td align="center" ><%=rs.getString(i,"unit_code")%></td>
                   	 	<td align="left" ><a href="#" onclick="gotopage('<%=rs.getString(i,"unit_id") %>');return false;"><%=rs.getString(i,"unit_addr") %></a></td>
                    	<td align="center" ><%=UnitKind.getValue(rs.getString(i,"unit_kind")) %></td>
                    	<td align="center" ><%=LiftFlag.getValue(rs.getString(i,"unit_flag")) %></td>
                    	<td align="right" ><%=rs.getString(i,"unit_tot_unit") %></td>    
                    	<td align="center" ><%=InfoStatus.getValue(rs.getString(i,"unit_status"))%></td>          
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
							<%=rs.getPage()!=null? rs.getPage().getPageHtml("form1","getUnitList.do"):""%>
						</td>
              		</tr>
				</table>
   		</td>
   	</tr>
</table>
</form>
</body>
</html> 


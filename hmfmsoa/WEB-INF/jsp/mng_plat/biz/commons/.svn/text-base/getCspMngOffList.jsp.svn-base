<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="fd.commons.jdbc.Result"%>
<%@page import="hmfms.util.ObjectUtil"%>
<%@page import="hmfms.util.StringUtil"%>
<%@page import="hmfms.web.User"%>
<%@page import="hmfms.util.ActionUtil"%>
<%@ include file="/jsp/commons/tag.jsp" %>
<%
Page pagec = new PageCounter(request);//分页
User user = ActionUtil.getUser(request);
String org_code=request.getParameter("org_code_off_query");
String org_name=request.getParameter("org_name_off_query");
String isTrade = request.getParameter("isTrade");
String isPage = request.getParameter("isPage");
String cspId = request.getParameter("cspId");

String batch_no = StringUtil.getString(request.getParameter("batch_no"));
Result rs = null;
CommonsManager manager=new CommonsManager();
if(!ObjectUtil.isEmpty(batch_no)){//正式数据和操作数据
	rs = manager.getCspMngOfflistNotTrade(user,org_name,org_code,cspId,batch_no,isTrade,pagec);	
}else{//只有正式数据
	rs = manager.getSectMngOffList(user,"",org_name,org_code,cspId,"",pagec);
}
%>

<%@page import="hmfms.services.codes.StaffType"%>
<%@page import="fd.commons.jdbc.Page"%>
<%@page import="mng_plat.biz.baseinfo.choice.ChoiceManager"%>
<%@page import="hmfms.web.commons.PageCounter"%>
<%@page import="mng_plat.biz.commons.CommonsManager"%>
<%@page import="hmfms.services.codes.OperDataType"%><html>
<head>
<script type="text/javascript">
$(document).ready(function(){
	$('.btn').PicButton();
	$('.grid_fixhead').datagrid({onSelect:onSelectRow});
});
function onSelectRow(index,el){
	var tr=$(el);
	var org_id = $("input[name='org_id_arr']",tr).val();
	$('#org_id').attr("value",org_id);
	var org_code = $("input[name='org_code_arr']",tr).val();
	$('#org_code').attr("value",org_code);
	var org_name = $("input[name='org_name_arr']",tr).val();
	$('#org_name').attr("value",org_name);
	var org_addr = $("input[name='org_addr_arr']",tr).val();
	$('#org_addr').attr("value",org_addr);
	var contacter = $("input[name='contacter_arr']",tr).val();
	$('#contacter').attr("value",contacter);
	var tel = $("input[name='tel_arr']",tr).val();
	$('#tel').attr("value",tel);
	var day_tel = $("input[name='day_tel_arr']",tr).val();
	$('#day_tel').attr("value",day_tel);
	var night_tel = $("input[name='night_tel_arr']",tr).val();
	$('#night_tel').attr("value",night_tel);
}
</script>
</head>
<body>
<input type="hidden" id="org_id" name="org_id" />
<input type="hidden" id="org_code" name="org_code" />
<input type="hidden" id="org_name" name="org_name" />
<input type="hidden" id="org_addr" name="org_addr" />
<input type="hidden" id="contacter" name="contacter" />
<input type="hidden" id="tel" name="tel" />
<input type="hidden" id="day_tel" name="day_tel" />
<input type="hidden" id="night_tel" name="night_tel" />
<table border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
	<tr>
		<td>
			<table class="grid_fixhead" width="100%">			
				<tr>
					<td>序号</td>
					<td>管理处名称</td>
					<td >管理处编码</td>
					<td>小区管理处地址</td>
					<td>联系人</td>
					<td>联系电话</td>
					<td>日间保修电话</td>
					<td>夜间保修电话</td>
					<td>状态</td>
				</tr>
				<%
				if(rs.getRowCount()>0){
				   for(int i=0;i<rs.getRowCount();i++){
				%>
					<tr>
						<td height="24" align="center" btn_condition="<%=rs.getString(i,"data_type") %>-0"><%=pagec.getPageSize()*(pagec.getCurrPage()-1)+i+1 %>
							<input type="hidden" name="org_id_arr" value="<%=rs.getString(i,"org_id")%>">
							<input type="hidden" name="org_code_arr" value="<%=rs.getString(i,"org_code")%>">
							<input type="hidden" name="org_name_arr" value="<%=rs.getString(i,"ORG_name")%>">
							<input type="hidden" name="org_addr_arr" value="<%=rs.getString(i,"ORG_ADDR")%>">
							<input type="hidden" name="contacter_arr" value="<%=rs.getString(i,"contacter")%>">
							<input type="hidden" name="tel_arr" value="<%=rs.getString(i,"tel")%>">
							<input type="hidden" name="day_tel_arr" value="<%=rs.getString(i,"day_tel")%>">
							<input type="hidden" name="night_tel_arr" value="<%=rs.getString(i,"night_tel")%>">
						</td>
						<td title="<%=rs.getString(i,"ORG_name") %>">
							<a href="<c:out value="${ctx}"/>/mng_plat/biz/commons/getCspMngOffInfo.do?org_id=<%=rs.getString(i,"org_id") %>&batch_no=<%=rs.getString(i,"batch_no") %>"><%=rs.getString(i,"ORG_name")%></a>
						</td>
						<td  align="center"  title="<%=rs.getString(i,"org_code") %>"><%=rs.getString(i,"org_code")%></td>
						<td title="<%=rs.getString(i,"ORG_ADDR") %>"><%=rs.getString(i,"ORG_ADDR") %></td>
						<td title="<%=rs.getString(i,"contacter") %>"><%=rs.getString(i,"contacter") %></td>
						<td title="<%=rs.getString(i,"tel") %>"><%=rs.getString(i,"tel") %></td>
						<td title="<%=rs.getString(i,"day_tel") %>"><%=rs.getString(i,"day_tel") %></td>
						<td title="<%=rs.getString(i,"night_tel") %>"><%=rs.getString(i,"night_tel") %></td>
						<%
						if(OperDataType.ZhuXiaoShuJu.toString().equals(rs.getString(i,"data_type"))){%>
						<td align="center" title="<%=rs.getString(i,"isAudit") %>">删除未审核</td>
						<%}else if(OperDataType.ZhuCeShuJu.toString().equals(rs.getString(i,"data_type"))){%>
						<td align="center" title="<%=rs.getString(i,"isAudit") %>">新增未审核</td>
						<%}else if(OperDataType.GengGaiHouShuJu.toString().equals(rs.getString(i,"data_type"))){%>
						<td align="center" title="<%=rs.getString(i,"isAudit") %>">变更未审核</td>
						<%}else{ %>
						<td align="center" title="<%=rs.getString(i,"isAudit") %>"><%=rs.getString(i,"isAudit") %></td>
						<%} %>
					</tr>
				<%}}%>
			</table>
		</td>
	</tr>
	<tr>
		<td align="right">
			<table border="0" cellspacing="0" cellpadding="0" class="wrap-page mt5">
				<tr>
					<td align="right" colspan="2">
						<%=rs.getPage()!=null? rs.getPage().getPageHtml("form1",isPage+".do"):""%>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>

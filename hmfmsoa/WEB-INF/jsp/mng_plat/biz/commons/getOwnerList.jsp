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
String org_code=request.getParameter("ywh_org_code");
String org_name=request.getParameter("ywh_org_name");
String isPage = request.getParameter("isPage");
CommonsManager manager=new CommonsManager();
Result rs = manager.getOwnersCongress(user,"",org_name,org_code,pagec);	
%>

<%@page import="hmfms.services.codes.StaffType"%>
<%@page import="fd.commons.jdbc.Page"%>
<%@page import="mng_plat.biz.commons.CommonsManager"%>
<%@page import="hmfms.web.commons.PageCounter"%><html>
<script type="text/javascript">
$(document).ready(function(){
	$('.btn').PicButton();
	$('.grid_fixhead').datagrid({onSelect:onSelectRow});
	$('.grid2').datagrid({hashead:false,trselect:false});

	function onSelectRow(index,el){
		var tr=$(el);
		var org_id = $("input[name='org_id_arr']",tr).val();
		$('#org_id').attr("value",org_id);
	}
});
</script>
<input type="hidden" id="org_id" name="org_id" value=""/>
<table border="0" cellpadding="0" cellspacing="0" width="960" align="center">
	<tr>
		<td>
			<table class="grid_fixhead" width="100%">			
				<tr>
					<td width="10%">序号</td>
					<td>业主大会代码</td>
					<td>业主大会名称</td>
					<td>业主大会地址</td>
					<td>联系电话</td>
				</tr>
				<%for(int i=0;i<rs.getRowCount();i++){
					Result rsstaff = (Result)rs.getObject(i,"rsOrgStaff");
				%>
					<tr>
						<td height="24" align="center"><%=pagec.getPageSize()*(pagec.getCurrPage()-1)+i+1 %>
						<input type="hidden" name="org_id_arr" value="<%=rs.getString(i,"org_id")%>"/>
						<input type="hidden" name="org_name_arr" value="<%=rs.getString(i,"org_name")%>"/>
						<input type="hidden" name="org_addr_arr" value="<%=rs.getString(i,"org_addr")%>"/>
						<input type="hidden" name="post_code_arr" value="<%=rs.getString(i,"post_code")%>"/>
						<input type="hidden" name="election_date_arr" value="<%=rs.getString(i,"election_date")%>"/>
						<input type="hidden" name="comm_valid_date_arr" value="<%=rs.getString(i,"comm_valid_date")%>"/>
						<input type="hidden" name="term_arr" value="<%=rs.getString(i,"term")%>"/>
						<input type="hidden" name="hoc_record_date_arr" value="<%=rs.getString(i,"hoc_record_date")%>"/>
						</td>
						<td title="<%=rs.getString(i,"org_code") %>">
							<a href="<c:out value="${ctx}"/>/mng_plat/biz/commons/getOwnerInfo.do?org_id=<%=rs.getString(i,"org_id") %>"><%=rs.getString(i,"org_code")%></a>
						</td>
						<td title="<%=rs.getString(i,"org_name") %>"><%=rs.getString(i,"org_name")%></td>
						<td title="<%=rs.getString(i,"org_addr") %>"><%=rs.getString(i,"org_addr") %></td>
						<td title="<%=rs.getString(i,"org_tel") %>"><%=rs.getString(i,"org_tel") %></td>

					</tr>
				<%}%>
			</table>
		</td>
	</tr>
	<tr>
		<td align="right">
			<table border="0" cellspacing="0" cellpadding="0" class="wrap-page mt5">
				<tr>
					<td><%=rs.getPage()!=null? rs.getPage().getPageHtml("form1",isPage+".do"):""%></td>
				</tr>
			</table>
		</td>
	</tr>
</table>

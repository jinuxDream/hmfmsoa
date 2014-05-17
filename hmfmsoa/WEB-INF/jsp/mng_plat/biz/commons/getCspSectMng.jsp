<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="fd.commons.jdbc.Result"%>
<%@page import="hmfms.util.ObjectUtil"%>
<%@page import="hmfms.util.StringUtil"%>
<%@page import="hmfms.web.User"%>
<%@page import="hmfms.util.ActionUtil"%>
<%@ include file="/jsp/commons/taglibs.jsp" %>
<%
Page pagec = new PageCounter(request);//分页
User user = ActionUtil.getUser(request);
String org_id_query = StringUtil.getString(request.getParameter("org_id_query"));
String staff_name_query = StringUtil.getString(request.getParameter("staff_name_query"));
String staff_cert_code_query = StringUtil.getString(request.getParameter("staff_cert_code_query"));
String staff_phone_query = StringUtil.getString(request.getParameter("staff_phone_query"));

String batch_no = StringUtil.getString(request.getParameter("batch_no"));

CommonsManager manager = new CommonsManager();
String isTrade = request.getParameter("isTrade");
String isPage = request.getParameter("isPage");
Result rs = null;
if(!ObjectUtil.isEmpty(batch_no)){//正式数据和操作数据
	rs = manager.getCspSectMangerOrOper(user, org_id_query, staff_name_query, staff_cert_code_query,staff_phone_query,pagec,batch_no,isTrade);
}else{//只有正式数据
	rs = manager.getCspSectManger(user, org_id_query, staff_name_query, staff_cert_code_query,staff_phone_query,pagec);
}
%>

<%@page import="hmfms.services.codes.StaffType"%>
<%@page import="fd.commons.jdbc.Page"%>
<%@page import="mng_plat.biz.baseinfo.choice.ChoiceManager"%>
<%@page import="hmfms.web.commons.PageCounter"%>
<%@page import="mng_plat.biz.commons.CommonsManager"%>
<%@page import="hmfms.services.codes.CertificateType"%>
<%@page import="hmfms.services.codes.SexFlag"%>
<%@page import="hmfms.services.codes.OperDataType"%><html>
<script type="text/javascript">
$(document).ready(function(){
	$('.btn').PicButton();
});
function onSelectRow(index,el){
	var tr=$(el);
	var person_id = $("input[name='person_id_arr']",tr).val();
	$('#person_id').attr("value",person_id);

	var org_id = $("input[name='org_id_arr']",tr).val();
	$('#org_id').attr("value",org_id);
	
	
	var staff_name = $("input[name='staff_name_arr']",tr).val();
	$('#staff_name').attr("value",staff_name);
	var staff_phone = $("input[name='staff_phone_arr']",tr).val();
	$('#staff_phone').attr("value",staff_phone);
	var staff_cert_code = $("input[name='staff_cert_code_arr']",tr).val();
	$('#staff_cert_code').attr("value",staff_cert_code);
	var certificate_code = $("input[name='certificate_code_arr']",tr).val();
	$('#certificate_code').attr("value",certificate_code);
	var staff_issuedorg = $("input[name='staff_issuedorg_arr']",tr).val();
	$('#staff_issuedorg').attr("value",staff_issuedorg);
	var certificate_code = $("input[name='certificate_code_arr']",tr).val();
	$('#certificate_code').attr("value",certificate_code);		
}
</script>
<input type="hidden" id="person_id" name="person_id" value=""/>
<input type="hidden" id="org_id" name="org_id" value=""/>
<input type="hidden" id="staff_name" name="staff_name" value=""/>
<input type="hidden" id="staff_phone" name="staff_phone" value=""/>
<input type="hidden" id="staff_cert_code" name="staff_cert_code" value=""/>
<input type="hidden" id="staff_issuedorg" name="staff_issuedorg" value=""/>
<input type="hidden" id="certificate_code" name="certificate_code" value=""/>
	
<table border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
	<tr>
		<td>
			<table class="grid_fixhead" width="100%" style="display: none;">			
				<tr>
					<td width="10%">序号</td>
					<td>经理名称</td>
					<td>证件号码</td>
					<td>上岗证号</td>
					<td>联系电话</td>
					<td>手机号码</td>
					<td>状态</td>
				</tr>
				<%
				if(rs.getRowCount()>0){
				   for(int i=0;i<rs.getRowCount();i++){
					Result rsstaff = (Result)rs.getObject(i,"rsCertificate");
					String sgzh = "";
					for(int j=0;j<rsstaff.getRowCount();j++){
						if(CertificateType.ShangGangZheng.toString().equals(rsstaff.getString(j,"certificate_type"))){
							sgzh = rsstaff.getString(j,"certificate_code");
						}
					}
				%>
					<tr>
						<td height="24" align="center" btn_condition="<%=rs.getString(i,"data_type") %>-0"><%=pagec.getPageSize()*(pagec.getCurrPage()-1)+i+1 %>
						<input type="hidden" name="person_id_arr" value="<%=rs.getString(i,"person_id")%>"/>
						<input type="hidden" name="org_id_arr" value="<%=rs.getString(i,"org_id")%>"/>
						<input type="hidden" name="staff_name_arr" value="<%=rs.getString(i,"staff_name")%>"/>
						<input type="hidden" name="staff_phone_arr" value="<%=rs.getString(i,"staff_phone")%>"/>
						<input type="hidden" name="staff_cert_code_arr" value="<%=rs.getString(i,"staff_cert_code")%>"/>
						<input type="hidden" name="certificate_code_arr" value="<%=sgzh %>">
						<input type="hidden" name="staff_issuedorg_arr" value="<%=rs.getString(i,"staff_issuedorg")%>"/>
						</td>
						<td align="center" title="<%=rs.getString(i,"staff_name") %>">
							<a href="<c:out value="${ctx}"/>/mng_plat/biz/commons/getCspSectMngInfo.do?person_id=<%=rs.getString(i,"person_id") %>&org_id=<%=rs.getString(i,"org_id") %>&batch_no=<%=rs.getString(i,"batch_no") %>"><%=rs.getString(i,"staff_name") %></a>
						</td>
						<td align="center" title="<%=rs.getString(i,"staff_cert_code") %>"><%=rs.getString(i,"staff_cert_code") %></td>
						<td align="center" title="<%=sgzh %>"><%=sgzh %></td>
						<td align="center" title="<%=rs.getString(i,"landline") %>"><%=rs.getString(i,"landline") %></td>
						<td align="center" title="<%=rs.getString(i,"staff_phone") %>"><%=rs.getString(i,"staff_phone") %></td>
						<%
						if(OperDataType.ZhuXiaoShuJu.toString().equals(rs.getString(i,"data_type"))){%>
						<td align="center" title="<%=rs.getString(i,"isAudit") %>">离职未审核</td>
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

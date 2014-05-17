<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="fd.commons.jdbc.Result"%>
<%@page import="hmfms.util.ObjectUtil"%>
<%@page import="hmfms.util.StringUtil"%>
<%@page import="hmfms.web.User"%>
<%@page import="hmfms.util.ActionUtil"%>
<%@ include file="/jsp/commons/taglibs.jsp" %>
<%
	Result rsOwnerinfo = (Result)request.getAttribute("rsOwnerinfo");
	Result rsstaff = (Result)rsOwnerinfo.getObject(0,"rsOrgStaff");
%>

<%@page import="hmfms.services.codes.StaffType"%>
<%@page import="fd.commons.jdbc.Page"%>
<%@page import="mng_plat.biz.baseinfo.choice.ChoiceManager"%>
<%@page import="hmfms.web.commons.PageCounter"%>
<%@page import="hmfms.util.DateUtil"%>
<%@page import="hmfms.services.codes.OrgNature"%>
<%@page import="hmfms.services.codes.QualificationLevel"%>
<%@page import="hmfms.services.codes.PoliticsStatus"%>
<%@page import="hmfms.services.codes.CertType"%>
<%@page import="hmfms.services.codes.SexFlag"%><html>
<head>
<%@ include file="/jsp/commons/meta.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	$('.btn').PicButton();
	$('.grid_cspInfo').datagrid({defaultSel:false, trselect:false});//固定表头
	$('.grid_saff').datagrid({defaultSel:false, trselect:false});//固定表头
});
//子页面传值到父页面
function confm(){
	var opener = window.opener.document;
	
	var org_id = $("#org_id").val();
	$("input[name='ywh_org_id']",opener).attr("value",org_id);
	var org_code = $("#org_code").val();
	$("input[name='ywh_org_code']",opener).attr("value",org_code);
	var org_name = $("#org_name").val();
	$("input[name='ywh_org_name']",opener).attr("value",org_name);
	var org_addr = $("#org_addr").val();
	$("input[name='ywh_org_addr']",opener).attr("value",org_addr);
	var post_code = $("#post_code").val();
	$("input[name='ywh_post_code']",opener).attr("value",post_code);
	var election_date = $("#election_date").val();
	$("input[name='ywh_election_date']",opener).attr("value",election_date);
	var con_start_date = $("#con_start_date").val();
	$("input[name='ywh_con_start_date']",opener).attr("value",con_start_date);
	var con_end_data = $("#con_end_data").val();
	$("input[name='ywh_con_end_data']",opener).attr("value",con_end_data);
	var term = $("#term").val();
	$("input[name='ywh_term']",opener).attr("value",term);

	<%for(int i=0;i<rsstaff.getRowCount();i++){%>
	var sms_name_<%=i%> = $("#sms_name_<%=i%>").val();
	$("input[name='ywh_sms_name_<%=i%>']",opener).attr("value",sms_name_<%=i%>);

	var sms_sex_<%=i%> = $("#sms_sex_<%=i%>").val();
	$("input[name='ywh_sms_sex_<%=i%>']",opener).attr("value",sms_sex_<%=i%>);

	var sms_age_<%=i%> = $("#sms_age_<%=i%>").val();
	$("input[name='ywh_sms_age_<%=i%>']",opener).attr("value",sms_age_<%=i%>);

	var politics_status_<%=i%> = $("#politics_status_<%=i%>").val();
	$("input[name='ywh_politics_status_<%=i%>']",opener).attr("value",politics_status_<%=i%>);

	var sms_phone_<%=i%> = $("#sms_phone_<%=i%>").val();
	$("input[name='ywh_sms_phone_<%=i%>']",opener).attr("value",sms_phone_<%=i%>);

	var cert_type_<%=i%> = $("#sms_cert_type_<%=i%>").val();
	$("input[name='ywh_sms_cert_type_<%=i%>']",opener).attr("value",cert_type_<%=i%>);

	var sms_cert_code_<%=i%> = $("#sms_cert_code_<%=i%>").val();
	$("input[name='ywh_sms_cert_code_<%=i%>']",opener).attr("value",sms_cert_code_<%=i%>);
	<%}%>
	window.close();	
}
</script>
</head>
<body>
<form name="form1" method="post" action="">
<table border="0" cellpadding="0" cellspacing="0" width="680" align="center">
	<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>业主大会详细信息</div></td></tr>
	<tr><td height="<%=_PAGEBLOCK_HEIGHT %>"></td></tr><!--间距 不可删除-->
	<tr>
		<td width="100%">
			<button class="btn" type="button" onClick="confm();">确定</button>&nbsp;
			<button class="btn" type="button" onclick="javascript:history.back(); return false;" >返回</button>
		</td>
	</tr>
	<tr><td height="<%=_PAGEBLOCK_HEIGHT %>"></td></tr><!--间距 不可删除-->
	<tr>
		<td>
			<table class="grid_cspInfo" width="100%">			
				<thead>
					<tr>
						<td width="10%" colspan="4">业主大会基本信息</td>
					</tr>
				</thead>
				<tr>
					<td align="right" width="20%">业主大会名称:</td>
					<td width="25%">&nbsp;&nbsp;
						<%=rsOwnerinfo.getString(0,"org_name") %>
						<input type="hidden" name="org_id" id="org_id" value="<%=rsOwnerinfo.getString(0,"org_id") %>"/>
						<input type="hidden" name="org_name" id="org_name" value="<%=rsOwnerinfo.getString(0,"org_name") %>"/>
					</td>
					<td align="right" width="20%">业主大会代码:</td>
					<td>&nbsp;&nbsp;
						<%=rsOwnerinfo.getString(0,"org_code") %>
						<input type="hidden" name="org_code" id="org_code" value="<%=rsOwnerinfo.getString(0,"org_code") %>"/>
					</td>
				</tr>
				<tr>
					
					<td align="right" width="20%">业主大会地址:</td>
					<td width="25%" colspan="3">&nbsp;&nbsp;
						<%=rsOwnerinfo.getString(0,"org_addr") %>
						<input type="hidden" name="org_addr" id="org_addr" value="<%=rsOwnerinfo.getString(0,"org_addr") %>"/>
					</td>
				</tr>
				<tr>
					<td align="right" width="20%">电话:</td>
					<td width="30%">&nbsp;&nbsp;
						<%=rsOwnerinfo.getString(0,"org_tel") %>
						<input type="hidden" name="org_tel" id="org_tel" value="<%=rsOwnerinfo.getString(0,"org_tel") %>"/>
					</td>
					<td align="right" width="20%">选举时间:</td>
					<td width="25%">&nbsp;&nbsp;
						<%=DateUtil.formatFromDB(rsOwnerinfo.getString(0,"election_date")) %>
						<input type="hidden" name="election_date" id="election_date"  value="<%=DateUtil.formatFromDB(rsOwnerinfo.getString(0,"election_date")) %>"/>
					</td>
				</tr>
				<tr>
					<td align="right" width="20%">邮编:</td>
					<td width="30%">&nbsp;&nbsp;
						<%=rsOwnerinfo.getString(0,"post_code") %>
						<input type="hidden" name="post_code" id="post_code" value="<%=rsOwnerinfo.getString(0,"post_code") %>"/>
					</td>
					<td align="right" width="20%">第几届 :</td>
					<td width="25%">&nbsp;&nbsp;
						<%=rsOwnerinfo.getString(0,"term") %>
						<input type="hidden" name="term" id="term" value="<%=rsOwnerinfo.getString(0,"term") %>"/>
					</td>
					
				</tr>
				<tr>
					<td align="right" width="20%">任期结束时间:</td>
					<td width="30%">&nbsp;&nbsp;
						<%=DateUtil.formatFromDB(rsOwnerinfo.getString(0,"con_start_date")) %>
						<input type="hidden" name="con_start_date" id="con_start_date" value="<%=DateUtil.formatFromDB(rsOwnerinfo.getString(0,"con_start_date")) %>"/>
					</td>
					<td align="right" width="20%">任期结束时间:</td>
					<td width="30%">&nbsp;&nbsp;
						<%=DateUtil.formatFromDB(rsOwnerinfo.getString(0,"con_end_data")) %>
						<input type="hidden" name="con_end_data" id="con_end_data" value="<%=DateUtil.formatFromDB(rsOwnerinfo.getString(0,"con_end_data")) %>"/>
					</td>
				</tr>
			</table>
				<%
				if(rsOwnerinfo.getRowCount()>0){
				for(int i=0;i<rsstaff.getRowCount();i++){
				%>
			<table class="grid_saff" width="100%">			
				<thead>
					<tr>
						<td width="10%" colspan="4">人员情况:<%=rsstaff.getString(i,"sms_name") %></td>
					</tr>
				</thead>
					<tr>
						<td align="right" width="20%">名称:</td>
						<td width="30%">&nbsp;&nbsp;
							<%=rsstaff.getString(i,"sms_name") %>
							<input type="hidden" name="sms_name" id="sms_name_<%=i %>" value="<%=rsstaff.getString(i,"sms_name") %>"/>
						</td>
						<td align="right" width="20%">性别:</td>
						<td>&nbsp;&nbsp;
							<%=SexFlag.getValue(rsstaff.getString(i,"sms_sex")) %>
							<input type="hidden" name="sms_sex" id="sms_sex_<%=i %>" value="<%=SexFlag.getValue(rsstaff.getString(i,"sms_sex")) %>"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">政治面貌:</td>
						<td width="30%">&nbsp;&nbsp;
							<%=PoliticsStatus.getValue(rsstaff.getString(i,"politics_status")) %>
							<input type="hidden" name="politics_status"  id="politics_status_<%=i %>" value="<%=PoliticsStatus.getValue(rsstaff.getString(i,"politics_status")) %>"/>
						</td>
						<td align="right" width="20%">年龄:</td>
						<td>&nbsp;&nbsp;
							<%=rsstaff.getString(i,"sms_age") %>
							<input type="hidden" name="sms_age" id="sms_age_<%=i %>" value="<%=rsstaff.getString(i,"sms_age") %>"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">职务:</td>
						<td width="30%">&nbsp;&nbsp;
							<%=StaffType.getValue(rsstaff.getString(i,"staff_type")) %>
							<input type="hidden" name="staff_type" id="staff_type_<%=i %>" value="<%=StaffType.getValue(rsstaff.getString(i,"staff_type")) %>"/>
						</td>
						<td align="right" width="20%">联系电话:</td>
						<td>&nbsp;&nbsp;
							<%=rsstaff.getString(i,"sms_phone") %>
							<input type="hidden" name="sms_phone" id="sms_phone_<%=i %>" value="<%=rsstaff.getString(i,"sms_phone") %>"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">证件类型:</td>
						<td width="30%">&nbsp;&nbsp;
							<%=CertType.getValue(rsstaff.getString(i,"sms_cert_type")) %>
							<input type="hidden" name="sms_cert_type" id="sms_cert_type_<%=i %>" value="<%=CertType.getValue(rsstaff.getString(i,"sms_cert_type")) %>"/>
						</td>
						<td align="right" width="20%">证件编码:</td>
						<td>&nbsp;&nbsp;
							<%=rsstaff.getString(i,"sms_cert_code") %>
							<input type="hidden" name="sms_cert_code" id="sms_cert_code_<%=i %>" value="<%=rsstaff.getString(i,"sms_cert_code") %>"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">邮政地址:</td>
						<td colspan="3">&nbsp;&nbsp;
							<%=rsstaff.getString(i,"sms_post_addr") %>
							<input type="hidden" name="sms_post_addr" id="sms_post_addr_<%=i %>" value="<%=rsstaff.getString(i,"sms_post_addr") %>"/>
						</td>
					</tr>
			</table>
			<%}}%>
</table>
</form>
</body>
</html>

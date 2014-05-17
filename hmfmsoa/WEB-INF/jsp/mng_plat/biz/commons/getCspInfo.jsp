<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="fd.commons.jdbc.Result"%>
<%@page import="hmfms.util.ObjectUtil"%>
<%@page import="hmfms.util.StringUtil"%>
<%@page import="hmfms.web.User"%>
<%@page import="hmfms.util.ActionUtil"%>
<%@ include file="/jsp/commons/taglibs.jsp" %>
<%
	Result rsCspinfo = (Result)request.getAttribute("rsCspinfo");
%>

<%@page import="hmfms.services.codes.StaffType"%>
<%@page import="fd.commons.jdbc.Page"%>
<%@page import="mng_plat.biz.baseinfo.choice.ChoiceManager"%>
<%@page import="hmfms.web.commons.PageCounter"%>
<%@page import="hmfms.util.DateUtil"%>
<%@page import="hmfms.services.codes.OrgNature"%>
<%@page import="hmfms.services.codes.QualificationLevel"%><html>
<head>
<%@ include file="/jsp/commons/meta.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	$('.btn').PicButton();
	$('.grid_cspInfo').datagrid({defaultSel:false, trselect:false});//�̶���ͷ
});
</script>
</head>
<body>
<form name="form1" method="post" action="">
<table border="0" cellpadding="0" cellspacing="0" width="890" align="center">
	<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>��ҵ��˾��ϸ��Ϣ</div></td></tr>
	<tr><td height="<%=_PAGEBLOCK_HEIGHT %>"></td></tr><!--��� ����ɾ��-->
	<tr>
		<td width="100%">
			<button class="btn" type="button" onclick="javascript:history.back(); return false;" >����</button>
		</td>
	</tr>
	<tr><td height="<%=_PAGEBLOCK_HEIGHT %>"></td></tr><!--��� ����ɾ��-->
	<tr>
		<td>
			<table class="grid_cspInfo" width="100%">			
				<thead>
					<tr>
						<td width="10%" colspan="4">��ҵ��˾������Ϣ</td>
					</tr>
				</thead>
				<tr>
					<td align="right" width="20%">��ҵ��˾����:</td>
					<td width="25%" colspan="3">&nbsp;&nbsp;<%=rsCspinfo.getString(0,"org_name") %></td>
				</tr>
				<tr>
					<td align="right" width="20%">��֯��������:</td>
					<td width="30%">&nbsp;&nbsp;<%=rsCspinfo.getString(0,"dept_code") %></td>
					<td align="right" width="20%">��֯��������֤��Ч��:</td>
					<td width="25%">&nbsp;&nbsp;<%=DateUtil.formatFromDB(rsCspinfo.getString(0,"dept_valid_date")) %></td>
				</tr>
				<tr>
					<td align="right" width="20%">Ӫҵִ�ձ��:</td>
					<td width="30%">&nbsp;&nbsp;<%=rsCspinfo.getString(0,"licence_code") %></td>
					<td align="right" width="20%">Ӫҵִ����Ч��:</td>
					<td width="25%">&nbsp;&nbsp;<%=DateUtil.formatFromDB(rsCspinfo.getString(0,"licence_valid_date")) %></td>
				</tr>
				<tr>
					<td align="right" width="20%">ע������:</td>
					<td width="30%">&nbsp;&nbsp;<%=rsCspinfo.getString(0,"super_name") %></td>
					<td align="right" width="20%">��ҵ��Ӫ����:</td>
					<td width="30%">&nbsp;&nbsp;<%=OrgNature.getValue(rsCspinfo.getString(0,"org_nature")) %></td>
				</tr>
				<tr>
					<td align="right" width="20%">��        ַ :</td>
					<td width="25%" colspan="3">&nbsp;&nbsp;<%=rsCspinfo.getString(0,"org_addr") %></td>
				</tr>
				<%
				String fr = "";
				String lxr = "";
				String frdh = "";
				String lxrdh = "";
				String frstaff_postcode = "";
				String frstaff_postaddr = "";
				String lxrstaff_postcode = "";
				String lxrstaff_postaddr = "";
				String frperson_id = "";
				String lxrperson_id = "";
				Result rsstaff = (Result)rsCspinfo.getObject(0,"rsOrgStaff");
				if(rsCspinfo.getRowCount()>0){
					for(int j=0;j<rsstaff.getRowCount();j++){ 
						if(StaffType.QiYeFaRen.toString().equals(rsstaff.getString(j,"staff_type"))){
							frperson_id = rsstaff.getString(j,"person_id");
							fr = rsstaff.getString(j,"staff_name");
							frdh = rsstaff.getString(j,"staff_phone");
							frstaff_postcode = rsstaff.getString(j,"staff_postcode");
							frstaff_postaddr = rsstaff.getString(j,"staff_postaddr");
						}if(StaffType.QiYeLianXiRen.toString().equals(rsstaff.getString(j,"staff_type"))){
							lxrperson_id = rsstaff.getString(j,"person_id");
							lxr = rsstaff.getString(j,"staff_name");
							lxrdh = rsstaff.getString(j,"staff_phone");
							lxrstaff_postcode = rsstaff.getString(j,"staff_postcode");
							lxrstaff_postaddr = rsstaff.getString(j,"staff_postaddr");
						}
					}
				}
				%>
				<tr>
					<td align="right" >��ϵ��:</td>
					<td align="left">&nbsp;&nbsp;<%=lxr %></td>
					<td align="right" >����:</td>
					<td align="left">&nbsp;&nbsp;<%=fr %></td>
				</tr>
				<tr>
					<td align="right" >��ϵ�绰:</td> 
					<td><%=lxrdh %></td>
					<td align="right" >���˵绰:</td>
					<td><%=frdh %></td>
				</tr>
				<tr>
					<td align="right">��ϵ�������ʱ�:</td>
					<td><%=lxrstaff_postcode %></td>
					<td align="right">���������ʱ�:</td>
					<td><%=frstaff_postcode %></td>
				</tr>
				<tr>
					<td align="right">��ϵ��������ַ:</td>
					<td><%=lxrstaff_postaddr %></td>
					<td align="right">����������ַ:</td>
					<td><%=frstaff_postaddr %></td>
				</tr>
				<tr>
					<td align="right" width="20%">��  ��:</td>
					<td width="30%">&nbsp;&nbsp;<%=rsCspinfo.getString(0,"org_fax") %></td>
					<td align="right" width="20%">����֤����:</td>
					<td width="30%">&nbsp;&nbsp;<%=rsCspinfo.getString(0,"letter_code") %></td>
				</tr>
				<tr>
					<td align="right" width="20%">����֤����Ч����:</td>
					<td width="30%">&nbsp;&nbsp;<%=DateUtil.formatFromDB(rsCspinfo.getString(0,"letter_valid_date")) %></td>
					<td align="right" width="20%">���ʵȼ�:</td>
					<td width="30%">&nbsp;&nbsp;<%=QualificationLevel.getValue(rsCspinfo.getString(0,"qualification_level")) %></td>
				</tr>
				<tr>
					<td align="right" width="20%">��Ӫҵ��Ӫҵ��Χ:</td>
					<td width="30%" colspan="3">&nbsp;&nbsp;<%=rsCspinfo.getString(0,"main_bus_scope") %></td>
				</tr>
				<tr>
					<td align="right" width="20%">����ҵ��Ӫ:</td>
					<td width="30%" colspan="3">&nbsp;&nbsp;<%=rsCspinfo.getString(0,"other_bus_scope") %></td>
				</tr>
				<tr>
					<td align="right" width="20%">��ҵ�ſ�:</td>
					<td width="30%" colspan="3">&nbsp;&nbsp;<%=rsCspinfo.getString(0,"company_profile") %></td>
				</tr>
				<tr>
					<td align="right" width="20%">��  ע:</td>
					<td width="30%" colspan="3">&nbsp;&nbsp;<%=rsCspinfo.getString(0,"org_remark") %></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>

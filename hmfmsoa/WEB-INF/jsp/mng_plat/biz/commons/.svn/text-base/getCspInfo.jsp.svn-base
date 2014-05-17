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
	$('.grid_cspInfo').datagrid({defaultSel:false, trselect:false});//固定表头
});
</script>
</head>
<body>
<form name="form1" method="post" action="">
<table border="0" cellpadding="0" cellspacing="0" width="890" align="center">
	<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>物业公司详细信息</div></td></tr>
	<tr><td height="<%=_PAGEBLOCK_HEIGHT %>"></td></tr><!--间距 不可删除-->
	<tr>
		<td width="100%">
			<button class="btn" type="button" onclick="javascript:history.back(); return false;" >返回</button>
		</td>
	</tr>
	<tr><td height="<%=_PAGEBLOCK_HEIGHT %>"></td></tr><!--间距 不可删除-->
	<tr>
		<td>
			<table class="grid_cspInfo" width="100%">			
				<thead>
					<tr>
						<td width="10%" colspan="4">物业公司基本信息</td>
					</tr>
				</thead>
				<tr>
					<td align="right" width="20%">物业公司名称:</td>
					<td width="25%" colspan="3">&nbsp;&nbsp;<%=rsCspinfo.getString(0,"org_name") %></td>
				</tr>
				<tr>
					<td align="right" width="20%">组织机构代码:</td>
					<td width="30%">&nbsp;&nbsp;<%=rsCspinfo.getString(0,"dept_code") %></td>
					<td align="right" width="20%">组织机构代码证有效期:</td>
					<td width="25%">&nbsp;&nbsp;<%=DateUtil.formatFromDB(rsCspinfo.getString(0,"dept_valid_date")) %></td>
				</tr>
				<tr>
					<td align="right" width="20%">营业执照编号:</td>
					<td width="30%">&nbsp;&nbsp;<%=rsCspinfo.getString(0,"licence_code") %></td>
					<td align="right" width="20%">营业执照有效期:</td>
					<td width="25%">&nbsp;&nbsp;<%=DateUtil.formatFromDB(rsCspinfo.getString(0,"licence_valid_date")) %></td>
				</tr>
				<tr>
					<td align="right" width="20%">注册区县:</td>
					<td width="30%">&nbsp;&nbsp;<%=rsCspinfo.getString(0,"super_name") %></td>
					<td align="right" width="20%">企业经营性质:</td>
					<td width="30%">&nbsp;&nbsp;<%=OrgNature.getValue(rsCspinfo.getString(0,"org_nature")) %></td>
				</tr>
				<tr>
					<td align="right" width="20%">地        址 :</td>
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
					<td align="right" >联系人:</td>
					<td align="left">&nbsp;&nbsp;<%=lxr %></td>
					<td align="right" >法人:</td>
					<td align="left">&nbsp;&nbsp;<%=fr %></td>
				</tr>
				<tr>
					<td align="right" >联系电话:</td> 
					<td><%=lxrdh %></td>
					<td align="right" >法人电话:</td>
					<td><%=frdh %></td>
				</tr>
				<tr>
					<td align="right">联系人邮政邮编:</td>
					<td><%=lxrstaff_postcode %></td>
					<td align="right">法人邮政邮编:</td>
					<td><%=frstaff_postcode %></td>
				</tr>
				<tr>
					<td align="right">联系人邮政地址:</td>
					<td><%=lxrstaff_postaddr %></td>
					<td align="right">法人邮政地址:</td>
					<td><%=frstaff_postaddr %></td>
				</tr>
				<tr>
					<td align="right" width="20%">传  真:</td>
					<td width="30%">&nbsp;&nbsp;<%=rsCspinfo.getString(0,"org_fax") %></td>
					<td align="right" width="20%">资质证书编号:</td>
					<td width="30%">&nbsp;&nbsp;<%=rsCspinfo.getString(0,"letter_code") %></td>
				</tr>
				<tr>
					<td align="right" width="20%">资质证书有效日期:</td>
					<td width="30%">&nbsp;&nbsp;<%=DateUtil.formatFromDB(rsCspinfo.getString(0,"letter_valid_date")) %></td>
					<td align="right" width="20%">资质等级:</td>
					<td width="30%">&nbsp;&nbsp;<%=QualificationLevel.getValue(rsCspinfo.getString(0,"qualification_level")) %></td>
				</tr>
				<tr>
					<td align="right" width="20%">主营业务营业范围:</td>
					<td width="30%" colspan="3">&nbsp;&nbsp;<%=rsCspinfo.getString(0,"main_bus_scope") %></td>
				</tr>
				<tr>
					<td align="right" width="20%">其他业务经营:</td>
					<td width="30%" colspan="3">&nbsp;&nbsp;<%=rsCspinfo.getString(0,"other_bus_scope") %></td>
				</tr>
				<tr>
					<td align="right" width="20%">企业概况:</td>
					<td width="30%" colspan="3">&nbsp;&nbsp;<%=rsCspinfo.getString(0,"company_profile") %></td>
				</tr>
				<tr>
					<td align="right" width="20%">备  注:</td>
					<td width="30%" colspan="3">&nbsp;&nbsp;<%=rsCspinfo.getString(0,"org_remark") %></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>

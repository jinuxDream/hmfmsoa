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
String org_code=request.getParameter("org_code_query");
String org_name=request.getParameter("org_name_query");
String isTrade = request.getParameter("isTrade");
String isPage = request.getParameter("isPage");
Result rs = null;
if("true".equals(isTrade)){
	CommonsManager manager=new CommonsManager();
	rs = manager.getCsplistNotTrade(user,org_name,org_code,pagec);	
}else{
	CommonsManager manager=new CommonsManager();
	rs = manager.getCsplist(user,org_name,org_code,pagec);
}
%>

<%@page import="hmfms.services.codes.StaffType"%>
<%@page import="fd.commons.jdbc.Page"%>
<%@page import="mng_plat.biz.baseinfo.choice.ChoiceManager"%>
<%@page import="hmfms.web.commons.PageCounter"%>
<%@page import="mng_plat.biz.commons.CommonsManager"%><html>
<head>
<script type="text/javascript">
$(document).ready(function(){
	$('.btn').PicButton();
	$('.grid_fixhead').datagrid({onSelect:onSelectRow});
	$('.grid2').datagrid({hashead:false,trselect:false});

	function onSelectRow(index,el){
		var tr=$(el);
		var org_id = $("input[name='org_id_arr']",tr).val();
		$('#org_id').attr("value",org_id);
		$('#org_id_query').attr("value",org_id);
		$('#cspId').attr("value",org_id);
		var org_code = $("input[name='org_code_arr']",tr).val();
		$('#org_code').attr("value",org_code);
		var org_name = $("input[name='org_name_arr']",tr).val();
		$('#org_name').attr("value",org_name);
		var org_addr = $("input[name='org_addr_arr']",tr).val();
		$('#org_addr').attr("value",org_addr);
		var licence_code = $("input[name='licence_code_arr']",tr).val();
		$('#licence_code').attr("value",licence_code);
		var dept_valid_date = $("input[name='dept_valid_date_arr']",tr).val();
		$('#dept_valid_date').attr("value",dept_valid_date);
		var licence_valid_date = $("input[name='licence_valid_date_arr']",tr).val();
		$('#licence_valid_date').attr("value",licence_valid_date);
		var register_address = $("input[name='register_address_arr']",tr).val();
		$('#register_address').attr("value",register_address);
		var lxr = $("input[name='lxr_arr']",tr).val();
		$('#lxr').attr("value",lxr);
		var fr = $("input[name='fr_arr']",tr).val();
		$('#fr').attr("value",fr);
		var frdh = $("input[name='frdh_arr']",tr).val();
		$('#frdh').attr("value",frdh);
		var lxrdh = $("input[name='lxrdh_arr']",tr).val();
		$('#lxrdh').attr("value",lxrdh);
		var start_date = $("input[name='start_date_arr']",tr).val();
		$('#start_date').attr("value",start_date);
		var end_date = $("input[name='end_date_arr']",tr).val();
		$('#end_date').attr("value",end_date);
		var dept_code = $("input[name='dept_code_arr']",tr).val();
		$('#dept_code').attr("value",dept_code);
		var org_contacter = $("input[name='org_contacter_arr']",tr).val();
		$('#org_contacter').attr("value",org_contacter);
		var org_tel = $("input[name='org_tel_arr']",tr).val();
		$('#org_tel').attr("value",org_tel);
		var org_fax = $("input[name='org_fax_arr']",tr).val();
		$('#org_fax').attr("value",org_fax);
		var qualification_level = $("input[name='qualification_level_arr']",tr).val();
		$('#qualification_level').attr("value",qualification_level);
		var letter_code = $("input[name='letter_code_arr']",tr).val();
		$('#letter_code').attr("value",letter_code);
		var letter_valid_date = $("input[name='letter_valid_date_arr']",tr).val();
		$('#letter_valid_date').attr("value",letter_valid_date);
	}
});
</script>
</head>
<body>
<input type="hidden" id="org_id_query" name="org_id_query" />
<input type="hidden" id="org_id" name="org_id" />
<input type="hidden" id="cspId" name="cspId" />
<input type="hidden"  id="org_code" name="org_code" />
<input type="hidden"  id="org_name" name="org_name"/>
<input type="hidden"  id="org_addr" name="org_addr"/>
<input type="hidden"  id="licence_code" name="licence_code"/>
<input type="hidden"  id="dept_valid_date" name="dept_valid_date"/>
<input type="hidden"  id="licence_valid_date" name="licence_valid_date"/>
<input type="hidden"  id="register_address" name="register_address"/>
<input type="hidden"  id="lxr" name="lxr" />
<input type="hidden" id="fr" name="fr"/>
<input type="hidden" id="frdh" name="frdh"/>
<input type="hidden" id="lxrdh" name="lxrdh"/>
<input type="hidden" id="start_date" name="start_date" value=""/>
<input type="hidden" id="end_date"  name="end_date" value=""/>
<input type="hidden" id="dept_code" name="dept_code" value=""/>
<input type="hidden" id="org_contacter" name="org_contacter" value=""/>
<input type="hidden" id="org_tel" name="org_tel" value=""/>
<input type="hidden" id="org_fax" name="org_fax" value=""/>
<input type="hidden" id="qualification_level" name="qualification_level" value=""/>
<input type="hidden" id="letter_code" name="letter_code" value=""/>
<input type="hidden" id="letter_valid_date" name="letter_valid_date" value=""/>
<input type="hidden" id="licence_code" name="licence_code" value=""/>
<input type="hidden" id="licence_valid_date" name="licence_valid_date" value=""/>
	
<table border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
	<tr>
		<td>
			<table class="grid_fixhead" width="100%">			
				<tr>
					<td width="10%">序号</td>
					<td>组织机构代码</td>
					<td>物业名称</td>
					<td>物业地址</td>
					<td>企业法人</td>
					<td>企业联系人</td>
				</tr>
				<%
				if(rs.getRowCount()>0){
				   for(int i=0;i<rs.getRowCount();i++){
					Result rsstaff = (Result)rs.getObject(i,"rsOrgStaff");
				%>
					<tr>
						<td height="24" align="center"><%=i+1%>
						<input type="hidden" name="org_id_arr" value="<%=rs.getString(i,"org_id")%>"/>
						<input type="hidden" name="org_code_arr" value="<%=rs.getString(i,"org_code")%>"/>
						<input type="hidden" name="org_name_arr" value="<%=rs.getString(i,"org_name")%>"/>
						<input type="hidden" name="org_addr_arr" value="<%=rs.getString(i,"org_addr")%>"/>
						<input type="hidden" name="licence_code_arr" value="<%=rs.getString(i,"licence_code")%>"/>
						<input type="hidden" name="dept_valid_date_arr" value="<%=rs.getString(i,"dept_valid_date")%>"/>
						<input type="hidden" name="licence_valid_date_arr" value="<%=rs.getString(i,"licence_valid_date")%>"/>
						<input type="hidden" name="register_address" value="<%=rs.getString(i,"register_address")%>"/>
						<input type="hidden" name="start_date_arr" value="<%=rs.getString(i,"start_date")%>"/>
						<input type="hidden" name="end_date_arr" value="<%=rs.getString(i,"end_date")%>"/>
						<input type="hidden" name="dept_code_arr" value="<%=rs.getString(i,"dept_code")%>"/>
						<input type="hidden" name="org_contacter_arr" value="<%=rs.getString(i,"org_contacter")%>"/>
						<input type="hidden" name="org_tel_arr" value="<%=rs.getString(i,"org_tel")%>"/>
						<input type="hidden" name="org_fax_arr" value="<%=rs.getString(i,"org_fax")%>"/>
						<input type="hidden" name="qualification_level_arr" value="<%=rs.getString(i,"qualification_level")%>"/>
						<input type="hidden" name="letter_code_arr" value="<%=rs.getString(i,"letter_code")%>"/>
						<input type="hidden" name="letter_valid_date_arr" value="<%=rs.getString(i,"letter_valid_date")%>"/>
						</td>
						<td align="center" title="<%=rs.getString(i,"org_code") %>"><%=rs.getString(i,"dept_code")%></td>
						<td title="<%=rs.getString(i,"org_name") %>"><a href="<c:out value="${ctx}"/>/mng_plat/biz/commons/getCspInfo.do?org_id=<%=rs.getString(i,"org_id") %>"><%=rs.getString(i,"org_name")%></a></td>
						<td title="<%=rs.getString(i,"org_addr") %>"><%=rs.getString(i,"org_addr") %></td>
						<%
						String fr = "";
						String lxr = "";
						String frdh = "";
						String lxrdh = "";
						if(rsstaff.getRowCount()>0){
						for(int j=0;j<rsstaff.getRowCount();j++){ 
							if(StaffType.QiYeFaRen.toString().equals(rsstaff.getString(j,"staff_type"))){
								fr = rsstaff.getString(j,"staff_name");
								frdh = rsstaff.getString(j,"staff_phone");
							}if(StaffType.QiYeLianXiRen.toString().equals(rsstaff.getString(j,"staff_type"))){
								lxr = rsstaff.getString(j,"staff_name");
								lxrdh = rsstaff.getString(j,"staff_phone");
							}
						%>
							
						<%}}%>
						<td title="<%=fr %>"><%=fr %></td>
						<td title="<%=lxr %>"><%=lxr %>
						<input type="hidden" name="lxr_arr" value="<%=lxr%>"/>
						<input type="hidden" name="fr_arr" value="<%=fr%>"/>
						<input type="hidden" name="frdh_arr" value="<%=frdh%>"/>
						<input type="hidden" name="lxrdh_arr" value="<%=lxrdh%>"/>
						</td>
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

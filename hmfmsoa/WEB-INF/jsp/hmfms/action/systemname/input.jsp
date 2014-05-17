<%@page import="hmfms.util.StringUtil"%>
<%@page import="hmfms.util.DateUtil"%>
<%@page import="hmfms.util.ActionUtil"%>
<%@page import="hmfms.web.User"%>
<%@page import="fd.commons.jdbc.Result"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/jsp/commons/taglibs.jsp"%>

<%
	Result rs = (Result) request.getAttribute("rs");
    User user = ActionUtil.getUser(request);
%>

<html>
<head>
<%@ include file="/jsp/commons/meta.jsp" %> 
		<link href="<c:out value="${ctx}"/>/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<script src="<c:out value="${ctx}"/>/ligerUI/js/ligerui.min.js" type="text/javascript"></script>

<script type="text/javascript">

$(document).ready(function(){
	$(".btn").PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();
	$('.grid_fixhead').datagrid({hashead:false,fixhead:false,toolbar:bar,trselect:0});
	$('#dt_stat_date').webDatepicker();
	$('#dt_end_date').webDatepicker();
	$('#dt_date').webDatepicker();

	function onSelectRow(inx,v1){
		var tr=$(v1);
		var dt_no_arr = $("input[name='dt_no_arr']",tr).val();
		$('#dt_no').val(dt_no_arr);
	}
});

function editCheckOk(){
	$.webUtil.submit("editCheckOk.do");
}

//�鿴
function contView(){
	$.webUtil.submit("contView.do");
}
</script>
</head>
	<body>
		<form id="form1" name="form1" method="post" action="">
			<input type="hidden" name="dt_no" value="<%=rs.getString(0,"dt_no")%>">
			<table align="center" border="0" cellpadding="0" cellspacing="0" width="760" style="margin:0 auto">
				<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>�û�������١��༭</div></td></tr>
				<!--���ܰ�ť�� end-->
				<tr><td height="<%=_PAGEBLOCK_HEIGHT %>"></td></tr><!--��� ����ɾ��-->
				<tr>
					<td>
						<button class="btn" type="button" value="2" onclick="editCheckOk()">����</button>
						<button class="btn" type="button" value="2" btn_href="index.do" issubmit="0" istip="0">����</button>
					</td>
				</tr>
					<tr>
						<td width="100%">
							<table class="grid_fixhead" width="100%">
								<tr>
									<td align="right" width="20%">�����/�ˣ�<span><font color="red">(����)</font></span></td>
									<td align="left" width="80%"><input name="dt_prop_name" id="dt_prop_name" type="text"  value=<%=rs.getString(0,"dt_prop_name") %> v_empty="1" size="20"/></td>
								</tr>
								<tr>
									<td align="right" >�����������۷�/�ˣ�<span><font color="red">(����)</font></span></td>
									<td align="left"><input name="dt_invol_demand" id="dt_invol_demand" type="text"  value=<%=rs.getString(0,"dt_invol_demand") %> v_empty="1" size="20"/></td>
								</tr>
								<tr>
									<td align="right" >��ʼ���ڣ�<span><font color="red"></font></span></td>
									<td align="left"><input name="dt_stat_date" id="dt_stat_date" type="text"  v_type="date" value=<%=rs.getString(0,"dt_stat_date")%> v_empty="1" size="20"/><span>���Ϊ��,��Ĭ��Ϊ����</span></td>
								</tr>
								<tr>
									<td align="right" >�������ڣ�<span><font color="red"></font></span></td>
									<td align="left"><input name="dt_end_date" id="dt_end_date" type="text"  v_type="date" value=<%=rs.getString(0,"dt_end_date")%> v_empty="1" size="20"/><span>���Ϊ��,��Ĭ��Ϊ������</span></td>
								</tr>
								<tr>
									<td align="right" >������ڣ�<span><font color="red"></font></span></td>
									<td align="left"><input name="dt_date" id="dt_date" type="text"  v_type="date" value=<%=rs.getString(0,"dt_date")%> v_empty="1" size="20"/><span>���Ϊ��,��Ĭ��Ϊ������</span></td>
								</tr>
								<tr>
									<td align="right" >���������ܣ���<span><font color="red"></font></span></td>
									<td align="left"><input name="dt_work" id="dt_work" type="text"  value=<%=rs.getString(0,"dt_work")%> v_empty="1" size="20"/></td>
								</tr>
								
								<tr>
									<td align="right" >ʵʩ��<span><font color="red"></font></span></td>
									<td align="left"><input name="dt_implement" id="dt_implement" type="text" value=<%=rs.getString(0,"dt_implement")%>  v_empty="1" size="20"/></td>
								</tr>
								<tr>
									<td align="right" >ʵʩ�ˣ�<span><font color="red"></font></span></td>
									<td align="left"><input name="dt_impl_pers" id="dt_impl_pers" type="text" value=<%=rs.getString(0,"dt_impl_pers")%>  v_empty="1" size="20"/></td>
								</tr>
								<tr>
									<td align="right" >����ͨ����<span><font color="red"></font></span></td>
									<td align="left"><input name="dt_test_pas" id="dt_test_pas" type="text" value=<%=rs.getString(0,"dt_test_pas")%>  v_empty="1" size="20"/></td>
								</tr>
								<tr>
									<td align="right"  >����������<span><font color="red"></font></span></td>
									<td align="left"><textarea rows="8" cols="120" name="dt_assess" id="dt_assess" type="text" ><%=rs.getString(0,"dt_assess")%></textarea></td>
								</tr>
								<tr>
									<td align="right"  >������ݣ�<span><font color="red">(����)</font></span></td>
									<td align="left">
										<textarea name="dt_content" id="dt_content" rows="8" cols="120" style="resize:none" ><%=rs.getString(0,"dt_content")%></textarea>
									</td>
								</tr>
								<tr>
									<td align="right"  >��ע��<span><font color="red"></font></span></td>
									<td align="left"><textarea rows="8" cols="120" name="dt_remark" id="dt_remark" ><%=rs.getString(0,"dt_remark")%></textarea></td>
								</tr>
							</table>
						</td>
					</tr>
			  </table>
		  </form>
	</body>
</html>

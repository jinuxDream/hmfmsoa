
<%@page import="hmfms.util.DateUtil"%>
<%@page import="fd.commons.jdbc.Result"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/jsp/commons/taglibs.jsp"%>
<%
	Result rs = (Result) request.getAttribute("rs");
   
%>

<%@page import="hmfms.services.codes.CspQualifyLevel"%><html>
<%@ include file="/jsp/commons/meta.jsp"%>
<script type="text/javascript">
/* ��ҳ��ĳ�ʼ���� �����������У����û�г�ʼ���������в���Ҫд�κδ��롣*/
$(document).ready(function(){	
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();//��ʼ����ť������(��һ��button��ɵ�div,������htmlʵ����.btn_toolbar .btn .btn_condition ������ʽ���Ʋ��ܱ�֮�����������Ըı�)
	var g=$('.grid1').datagrid({toolbar:bar,trselect:false});//��ִ�д��� g[0].grid.setBar(bar);	Ҳ����
});	
function onSelectRow(index,val){
		//$("#app_id_detail").val(val);
	}
	

function gotoView(csp_id)
{
	$.webUtil.submit("getHistoryList.do?csp_id="+csp_id);
}
function gotoView1(csp_name)
{
	$.webUtil.submit("<c:out value="${ctx}"/>/mng_plat/biz/query/sectinfoquery/index.do?csp_name="+csp_name);
}
</script>
<body>
<div class="parent-wrap">
<form id="form1" name="form1" method="post" action="">
<input id="sect_id" type="hidden" value="" />
<input type="hidden" name="csp_id"  value="<%=rs.getString(0, "csp_id")%>" />
   <div class="sub-bg">
		<img src="<c:out value="${ctx}"/>/images/home_title_leftarrow.gif"/>
		<strong>��ҵ��ҵ��Ϣ</strong>
	</div>
 	<div class="wrap-btnarea">
       <button type="button" class="btn"  btn_href="index.do" issubmit="0" istip="0" >����</button>
	</div>
	<div class="wrap-content">
		<table  width="100%" class="grid1">
					<thead>
						<tr>
							<th colspan="4">��ҵ��ҵ��ϸ��Ϣ</th>
						</tr>
					</thead>
					<tr>
						<td align="center" width="20%">��ҵ����</td>
						<td align="left" nowrap>&nbsp;<%=rs.getString(0,"csp_name") %></td>
					</tr>
					<tr>
						<td align="center">��֯��������</td>
						<td align="left" nowrap>&nbsp;<%=rs.getString(0,"csp_org_code") %></td>
					</tr>
					<tr>
						<td align="center">��ַ</td>
						<td align="left" nowrap>&nbsp;<%=rs.getString(0,"csp_addr") %></td>
					</tr>
					<tr>
						<td align="center">ע�����ڵ�</td>
						<td align="left" nowrap>&nbsp;<%=rs.getString(0,"hp_name") %></td>
					</tr>
					<tr>
						<td align="center">��ҵ��ҵ��ҵ����</td>
						<td align="left" nowrap>&nbsp;<%=CspQualifyLevel.getValue(rs.getString(0, "csp_ent_qualification"))%></td>
					</tr>
					<tr>
						<td align="center">Ӫҵִ����Ч��</td>
						<td align="left" nowrap>&nbsp;<%=DateUtil.formatFromDB(rs.getString(0,"csp_biz_license_valid_date"))%></td>
					</tr>
					<tr>
						<td align="center">����֤����Ч��</td>
						<td align="left" nowrap>&nbsp;<%=DateUtil.formatFromDB(rs.getString(0,"csp_qualification_valid_date"))%></td>
					</tr>
					<tr>
						<td align="center">�������</td>
						<td align="left">
					<a href="#" onclick="gotoView('<%=rs.getString(0,"csp_id")%>');return false;">&nbsp;��ҵ��Ϣ�����ʷ</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" onclick="gotoView1('<%=rs.getString(0,"csp_name")%>');return false;">&nbsp;С�������Ϣ��ѯ</a></td>
					</tr>
						
						</table>
						  </div>
</form>
</div>
</body>
</html>
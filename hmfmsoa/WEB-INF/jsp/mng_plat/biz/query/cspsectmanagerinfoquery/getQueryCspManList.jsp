<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="fd.commons.jdbc.Result"%>
<%@ include file="/jsp/commons/taglibs.jsp" %>

<% 
	Result rsCspMan = (Result)request.getAttribute("rsCspMan");
%>


<%@page import="hmfms.services.codes.CertType"%><html>
<%@ include file="/jsp/commons/meta.jsp" %>
<script type="text/javascript">

/* ��ҳ��ĳ�ʼ���� �����������У����û�г�ʼ���������в���Ҫд�κδ��롣*/
$(document).ready(function(){	
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();//��ʼ����ť������(��һ��button��ɵ�div,������htmlʵ����.btn_toolbar .btn .btn_condition ������ʽ���Ʋ��ܱ�֮�����������Ըı�)
	var g=$('.grid1').datagrid({toolbar:bar,trselect:false});//��ִ�д��� g[0].grid.setBar(bar);	Ҳ����
});	
//��ѯС��������Ϣ�����ʷ
function gotoCspManModiHistory()
{
	$.webUtil.submit("getQueryCspManModiHistory.do");
}

</script>
<body>
<form name="form1" method="post" action="">
<!-- ��������С������ID -->
<input type="hidden" id="csm_id" name="csm_id" value="<%=rsCspMan.getString(0,"csm_id") %>" /> 

<table border="0" cellpadding="0" cellspacing="0" width="960" align="center">
<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>С��������ϸ��Ϣ</div></td></tr>
	<tr><td height="<%=_PAGEBLOCK_HEIGHT%>"></td></tr>
	<!--��� ����ɾ��--><!--���ܰ�ť�� start--> 
	<tr>
		<td width="100%">
			<button class="btn" type="button" value="2" onclick="history.back()"  force >����</button>
		</td>
	</tr>
	<!--���ܰ�ť�� end-->
	<tr><td height="<%=_PAGEBLOCK_HEIGHT %>"></td></tr><!--��� ����ɾ��-->
	<tr>
		<td width="100%"><!--������ start-->
			<table width="100%"  class="grid1">
				<thead >
					<tr>
						<th colspan="2">С��������ϸ��Ϣ</th>
					</tr>
				</thead>
					<tr>
						<td align="center">��������</td>
						<td align="left"><%=rsCspMan.getString(0,"csm_name") %></td>
					</tr>
					<tr>
						<td align="center">֤������</td>
						<td align="left" ><%=CertType.getValue(rsCspMan.getString(0,"csm_cert_type")) %></td>
					</tr>
					<tr>
						<td align="center">֤������</td>
						<td align="left"><%=rsCspMan.getString(0,"csm_cert_code") %></td>
					</tr>
					<tr>
						<td align="center">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;</td>
						<td align="left"><%=rsCspMan.getString(0,"csm_phone") %></td>
					</tr>
					<tr>
						<td align="center">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;</td>
						<td align="left"><%=rsCspMan.getString(0,"csm_tel") %></td>
					</tr>
					<tr>
						<td align="center">�ϸ�֤��</td>
						<td align="left"><%=rsCspMan.getString(0,"csm_job_code") %></td>
					</tr>
					<tr>
						<td nowrap width="20%" align="center">��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ע&nbsp;</td>
						<td nowrap width="80%" align="left"><%=rsCspMan.getString(0,"csm_remak") %></td>
					</tr>
					<tr>
						<td nowrap width="20%" align="center">�������</td>
						<td nowrap width="80%" align="left"><a href="#" onclick="gotoCspManModiHistory()">С��������Ϣ�����ʷ</a></td>
					</tr>
			</table>
			<!--������ end-->
		</td>
	</tr>
</table>
</body>
</html>


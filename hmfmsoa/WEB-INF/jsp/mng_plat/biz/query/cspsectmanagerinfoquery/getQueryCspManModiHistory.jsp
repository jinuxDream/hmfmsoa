<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="fd.commons.jdbc.Result"%>
<%@page import="hmfms.util.DateUtil"%>
<%@ include file="/jsp/commons/taglibs.jsp"%>

<%
	Result rsCspManMoHis = (Result)request.getAttribute("rsCspManMoHis");
%>

<html>
<head>
<%@ include file="/jsp/commons/meta.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();
	var g=$('.grid1').datagrid({toolbar:bar, trselect:true});//��ִ�д��� g[0].grid.setBar(bar);	Ҳ����
});

</script>
<body>
<form id="form1" name="form1" method="post" action="">
    <!-- ������ -->
	<input type="hidden" id="" name="" value="" />
<table border="0" cellpadding="0" cellspacing="0" width="960" align="center">
<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>�鿴С��������Ϣ�����ʷ</div></td></tr>
	<tr><td height="<%=_PAGEBLOCK_HEIGHT%>"></td></tr>
	<tr>	<td width="100%">
			<button class="btn" type="button" value="2" onclick="history.back()"  force >����</button>
		</td>
	</tr>	
<tr><td height="<%=_PAGEBLOCK_HEIGHT %>"></td></tr><!--��� ����ɾ��-->
	<tr>
		<td width="100%"><!--������ start-->
			<table width="100%"  class="grid1">
			<tr>
				<td width="10%">���</td>
				<td width="20%">��������</td>
				<td width="20%">�ϸ�֤��</td>
				<td width="25%">�������</td>	
				<td width="25%">���ʱ��</td>			
			</tr>
			<!--JSP��forѭ�������濪ʼд-->
			<%
			for(int i=0; i<rsCspManMoHis.getRowCount(); i++){						
			//TODO ���濪ʼд�Լ��Ĵ�����롣
			%>
			<tr>
				<td height="24" align="center" btn_condition='<%=i%2%>'><%=(i+1) %>
				</td>
				<td  align="left">&nbsp;<%=rsCspManMoHis.getString(i,"csm_name")%></td>
				<td align="center"><%=rsCspManMoHis.getString(i,"csm_job_code")%></td>
				<td align="center"><%=DateUtil.formatFromDB(rsCspManMoHis.getString(i,"csm_creat_date"))%></td>		
				<td align="center"><%=DateUtil.formatTimeFromDB(rsCspManMoHis.getString(i,"csm_creat_time"))%></td>
			</tr>
			<%} %><!--JSP��forѭ������--> 
		</table>
			</td>
	</tr>
<tr>
		<td align = "right">
			<table >
				<tr>
					<td class = "wrap-page">
		<%=rsCspManMoHis.getPage() != null ? rsCspManMoHis.getPage().getPageHtml("form1","getQuerySectCspHistoryList.do"):"" %>
		</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</div>
</body>
</html>

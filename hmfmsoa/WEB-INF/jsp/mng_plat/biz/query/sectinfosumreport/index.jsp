<%@page import="hmfms.util.ActionUtil"%>
<%@page import="hmfms.web.User"%>
<%@page import="hmfms.util.StringUtil"%>
<%@page import="hmfms.util.ObjectUtil"%>
<%@page import="fd.exception.BusinessException"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*,fd.commons.jdbc.Result"%>
<%@ include file="/jsp/commons/taglibs.jsp"%>
<%
	User user =	ActionUtil.getUser(request);
	Result list_rs = (Result)request.getAttribute("list_rs");
	//	��ѯ����
	String st_name_frst = (String)request.getParameter("st_name_frst");
	if(st_name_frst == null) st_name_frst = "";
	String st_addr_frst= (String)request.getParameter("st_addr_frst");
	if(st_addr_frst == null) st_addr_frst = "";
	Result rsHpb = (Result)request.getAttribute("rsHpb");
%> 

<%@page import="hmfms.services.codes.DeptType"%>
<%@page import="hmfms.web.commons.SelectBoxHtml"%><html>
	<%@ include file="/jsp/commons/meta.jsp"%>
	<script type="text/javascript">
		/* ��ҳ��ĳ�ʼ���� �����������У����û�г�ʼ���������в���Ҫд�κδ��롣*/
		$(document).ready(function(){	
			$('.btn').PicButton();
			var bar=$('.btn_toolbar').BtnToolBar();//��ʼ����ť������(��һ��button��ɵ�div,������htmlʵ����.btn_toolbar .btn .btn_condition ������ʽ���Ʋ��ܱ�֮�����������Ըı�)
			var g=$('.grid1').datagrid({toolbar:bar});//��ִ�д��� g[0].grid.setBar(bar);	Ҳ����
		});	
		
		//��ѯ�ֵ�
		function gotoQuery()
		{
			//����ǰ��ո�
			$("input[type=text]").each(function(){
				$(this).val($.trim($(this).val()));
			});
			$("#ho_name").val("");
			
			$.webUtil.submit("querySect.do?flag=1");
		}
		
		//��ѯ����
		function gotoQueryInfo()
		{
			//����ǰ��ո�
			$("input[type=text]").each(function(){
				$(this).val($.trim($(this).val()));
			});
			$("#str_name").val("");
			$.webUtil.submit("querySect.do?flag=2");
		}
	</script> 
	<body>
		<form name="form1" method="post" action="">
			<input type="hidden" name="sect_id" id="sect_id" value="" /> 
			<table border="0" cellpadding="0" cellspacing="0" width="960" style="margin:0 auto">
			<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>С������������ܱ�</div></td></tr>
				<tr>
					<td height="<%=_PAGEBLOCK_HEIGHT%>"></td>
				</tr>
				<!--��� ����ɾ��--><!--��ѯ������ start--> 
				<tr>
					<td width="100%" class="frameblue">
						<div class="wrap-paramarea">
							<div class="fl mr5 mt2">
							<% if(DeptType.ShiJu.equals(user.getDeptType())){%>
							���أ�<select  id="hpb_id" name = "hpb_id" v_empty="0" v_min="1" v_max="20">
									<%=SelectBoxHtml.genOptionsHpbHtml(true,StringUtil.getString(request.getParameter("hpb_id"))) %>
									</select>
							<%} %>
							�ֵ����ƣ�<input type="text" id="str_name" name="str_name" size="25" maxlength="200" value = "<%=StringUtil.getString(request.getParameter("str_name"))%>" />&nbsp;
									<button class="btn" type="button" onClick="gotoQuery()" mask>�ֵ���ѯ</button> 
							</div>
							
							<div class="fl mr5 mt2">
							���ܰ죺<input type="text" id="ho_name" name="ho_name" size="25" maxlength="200" value = "<%=StringUtil.getString(request.getParameter("ho_name"))%>" />&nbsp;
									<button class="btn" type="button" onClick="gotoQueryInfo()" mask>�����ѯ</button> 
							</div>
							
							<div>
								<div class="clear"></div>
							</div>
						</div>
					</td>
				</tr>
				<!--��ѯ������ end--> 
				<tr>
					<td height="<%=_PAGEBLOCK_HEIGHT%>"></td>
				</tr>
			</table>
		</form>
	</body>
</html>

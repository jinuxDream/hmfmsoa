<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/jsp/commons/taglibs.jsp"%>
<%@page import="hmfms.base.ActionResultHmfms"%>
<%@page import="hmfms.util.ObjectUtil"%>

<%
/**˵������ҳ��֧��7�����:1��ҳ����⣬2����ʾ��Ϣ��3����Ϣ��ϸ��Ϣ��
4����һ�������URL ,5������URL,6��������ť��,7���������Ҫ�ӳɹ�ҳ�洫�ݵ�����ҳ���������
һ��Ĭ�����������Ҫ�����κβ�����������Ĭ��ֵΪ��
	1��Ĭ��Ϊ��һ���ύҳ��ı��⡣2����ʾ�������ɹ�����3����ϸ��ϢΪ�մ�
	4����һ������ҳ���URL��5����ǰ����index.do��6��ֻ��һ�����ذ�ť��û�������κΰ�ť��7��û���κ������򴫵ݡ�
�������õ������õ�7���6���ϸ�μ�����Ա������д����ActionResultHmfms.toPageOkWithParam()��ActionResultHmfms.toPageOk()
*/
//User user = (User)request.getSession().getAttribute(hmfms.base.BaseAction.USER_SESSION);
//��Ϣҳ�����
String pageok_tilte = (String)request.getParameter(ActionResultHmfms.MESSAGE_PAGETITLE); // ���ܵ�����Ϣ�����磺�ƶ�ά�޾���->����
if (ObjectUtil.isEmpty(pageok_tilte)) pageok_tilte="�����ɹ�"; 
//��Ϣ����
String msg_content = (String)request.getParameter(ActionResultHmfms.MESSAGE_CONTENT);
if (ObjectUtil.isEmpty(msg_content)) msg_content="�����ɹ�!";
//��Ϣ����ϸ��Ϣ�򸽼���Ϣ���������ŵ�
String message_detail = (String)request.getParameter(ActionResultHmfms.MESSAGE_DETAIL);
if(ObjectUtil.isEmpty(message_detail)) message_detail=" ";


%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<%@ include file="/jsp/commons/meta.jsp" %>
<title>ѡ����Ŀ</title>
<script language="javascript">
$(document).ready(function(){
	$('.btn').PicButton();
	var g=$('.grid1').datagrid({hashead:true,trselect:false});
});	
</SCRIPT>
</head>
<body>
<form name="form1" method="post" action="">
<%=ActionResultHmfms.convertParams2Html(request)%>
<table border="0" cellpadding="0" align="center" cellspacing="0" width="680"  style="margin:0 auto">
	<tr><td><div class="headline"><div class="headarrow"> &nbsp; &nbsp; &nbsp;</div><%=pageok_tilte%></div></td></tr>
	<tr>
		<td height="20" ></td>
	</tr>
	<tr><td>
	<table class="grid1" width="100%" align="center" >
		<thead><tr><td><div align="center">��ѡ����Ϣ!</div></td></tr></thead>
		<tr>
			<td align="center" style="padding:20px">
			<table border="0" cellpadding="0" cellspacing="0" width="80%">
			<tr>
				<td width="100%" align="center" style="padding:30px;">
					<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr><td width="100%" align="center" style="border:none">
							<div   class="success_icon"><br><br><br><br><br><br></div>	 <br /></td>
							</tr>
							<tr height="25">
								<td width="100%" align="center" style="border:none">��ѡ����Ŀ:</td>
							</tr>
							<tr height="30">
								<td align="center" style="border:none">
								</td>
							</tr>
							<tr height="30">
								<td align="center" style="border:none">
								<a href="<c:out value="${ctx}"/>/hmfms/biz/sysmng/init/section.do">����˴�ѡ����Ŀ</a>
								</td>
							</tr>
							 
							<tr height="30">
								<td align="center" height="30" style="border:none">
							</td>
						</tr>
		
					</table>		
				</td>
			</tr>
			</table>
			</td>
		</tr>
		</table>	
	</td></tr>
</table>
</form>
</body>
</html>

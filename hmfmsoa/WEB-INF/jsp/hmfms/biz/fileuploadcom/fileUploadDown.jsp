<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/jsp/commons/taglibs.jsp"%>
<%@page import="hmfms.util.StringUtil"%>
<%
String tips = "�ļ��ϴ��ɹ���";
String fail_message = (String)request.getAttribute("fail_message");
String tran_type = StringUtil.getString(request.getAttribute("tran_type"));
String index = StringUtil.getString(request.getAttribute("index"));
if(hmfms.util.ObjectUtil.isEmpty(fail_message)) fail_message = "�޷���λ����";
if(!"null".equals(fail_message))
	tips = fail_message;
%>

<%@page import="hmfms.util.ObjectUtil"%><html>
<head>
<%@ include file="/jsp/commons/meta.jsp" %>
</head>
<body>
<SCRIPT LANGUAGE="JavaScript">
<!--
alert('<%=tips%>');
<%if("null".equals(fail_message)){//�ϴ��ɹ�������ϴ��ĸ�����Ϣ��д����ҳ��
	String filename = (String)request.getAttribute("filename");				//�û�������֣�����ҳ����ʾ
	String upld_filename = (String)request.getAttribute("upld_filename");	//�ϴ����ļ��Ĵ��·��
	String orgn_filename = (String)request.getAttribute("orgn_filename");	//�ϴ����ļ���ԭʼ�ļ���
	//String fileName = orgn_filename.split("\\.")[0];
	//String lf_name = fileName.split("&")[0];//��÷�������
	//String lf_fileno = fileName.split("&")[1];//��÷����ĺ�
	//��Ϻ󣬴���edit.jsp��hidden�У���edit.jsp����ʱ����Action���Ա㱣�����
	String hidden = filename + hmfms.biz.fileuploadcom.FileUploadComAction.FILEINFO_SPLITCHAR
			+ upld_filename +hmfms.biz.fileuploadcom.FileUploadComAction.FILEINFO_SPLITCHAR + orgn_filename;

	String uri_filename = upld_filename.substring(upld_filename.lastIndexOf("/")+1);//�ϴ����ļ�����ʱĿ¼�ڵ�����
	String uriRoot = hmfms.util.Property.getString("webFileTemp","");//�ϴ�����ʱ�ļ���ŵĸ�Ŀ¼

	String fileid = "tfid"+System.currentTimeMillis();%>
	var debug = false;
	//alert("info=" + parent.document);
	//alert("info=" + parent.document.getElementById("uploadfilesDiv"));
	
<%if(ObjectUtil.isEmpty(index)||"null".equals(index)){%>	
	var uploadfilesDiv = parent.document.getElementById("uploadfilesDiv");//edit.jsp����ʾ�ϴ��ļ���div
	var line = "<a href='<c:out value="${ctx}"/><%=(uriRoot+uri_filename)%>'"
	+" target='_blank'><%=filename%></a>&nbsp;&nbsp;<a href=\"#\""
	+" onclick=\"deltmpfile('<%=fileid%>');return false;\" title='ɾ������'>ɾ��</a>"
	+"<input type='hidden' name=tmpfilesid value='<%=fileid%>' disabled>";
	line += "<input type='hidden' name='uploadfiles' value=\"<%=hidden%>\">";
	line += "<input type='hidden' name='uploadfilesname' value=\"<%=filename%>\"><br>";
	
	uploadfilesDiv.innerHTML += line;
	parent.document.getElementById("recall").value += line;
<%}else{	%>
	//var uploadfilesDiv = parent.document.getElementById("uploadfilesDiv<%=index%>");
	var uploadfilesDiv = $("#uploadfilesDiv<%=index%>", window.parent.document);
	var line = "<span><a href='<c:out value="${ctx}"/><%=(uriRoot+uri_filename)%>'"
	+" target='_blank'><%=filename%></a>&nbsp;&nbsp;<a href=\"#\""
	+" onclick=\"delFile(this);return false;\" title='ɾ������'>ɾ��</a>";
	line += "<input type='hidden' name='uploadfiles' value=\"<%=hidden%>\">";
	line += "<input type='hidden' name='uploadfilesname' value=\"<%=filename%>\"><br>";
	line += "<input type=\"hidden\" name=\"index\" value=\"<%=index%>\"/>"
	line += "</span>";
	//uploadfilesDiv.innerHTML += line;
	uploadfilesDiv.html(uploadfilesDiv.html()+line);
	
<%}%>

<%
}
%>

window.location.href="<c:out value="${ctx}"/>/jsp/hmfms/biz/fileuploadcom/fileUpload.do?tran_type=0&index="+<%=index%>;

//-->
</SCRIPT>
</body>
</html>
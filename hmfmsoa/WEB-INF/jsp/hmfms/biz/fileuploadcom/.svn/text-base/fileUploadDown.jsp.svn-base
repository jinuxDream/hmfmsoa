<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/jsp/commons/taglibs.jsp"%>
<%@page import="hmfms.util.StringUtil"%>
<%
String tips = "文件上传成功！";
String fail_message = (String)request.getAttribute("fail_message");
String tran_type = StringUtil.getString(request.getAttribute("tran_type"));
String index = StringUtil.getString(request.getAttribute("index"));
if(hmfms.util.ObjectUtil.isEmpty(fail_message)) fail_message = "无法定位错误！";
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
<%if("null".equals(fail_message)){//上传成功，则把上传的各种信息回写到父页面
	String filename = (String)request.getAttribute("filename");				//用户起的名字，用于页面显示
	String upld_filename = (String)request.getAttribute("upld_filename");	//上传的文件的存放路径
	String orgn_filename = (String)request.getAttribute("orgn_filename");	//上传的文件的原始文件名
	//String fileName = orgn_filename.split("\\.")[0];
	//String lf_name = fileName.split("&")[0];//获得法规名称
	//String lf_fileno = fileName.split("&")[1];//获得法规文号
	//组合后，存入edit.jsp的hidden中，在edit.jsp保存时传入Action，以便保存入库
	String hidden = filename + hmfms.biz.fileuploadcom.FileUploadComAction.FILEINFO_SPLITCHAR
			+ upld_filename +hmfms.biz.fileuploadcom.FileUploadComAction.FILEINFO_SPLITCHAR + orgn_filename;

	String uri_filename = upld_filename.substring(upld_filename.lastIndexOf("/")+1);//上传的文件在临时目录内的名字
	String uriRoot = hmfms.util.Property.getString("webFileTemp","");//上传的临时文件存放的根目录

	String fileid = "tfid"+System.currentTimeMillis();%>
	var debug = false;
	//alert("info=" + parent.document);
	//alert("info=" + parent.document.getElementById("uploadfilesDiv"));
	
<%if(ObjectUtil.isEmpty(index)||"null".equals(index)){%>	
	var uploadfilesDiv = parent.document.getElementById("uploadfilesDiv");//edit.jsp中显示上传文件的div
	var line = "<a href='<c:out value="${ctx}"/><%=(uriRoot+uri_filename)%>'"
	+" target='_blank'><%=filename%></a>&nbsp;&nbsp;<a href=\"#\""
	+" onclick=\"deltmpfile('<%=fileid%>');return false;\" title='删除附件'>删除</a>"
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
	+" onclick=\"delFile(this);return false;\" title='删除附件'>删除</a>";
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
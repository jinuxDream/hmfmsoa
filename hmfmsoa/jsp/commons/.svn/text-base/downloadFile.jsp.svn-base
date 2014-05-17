<%@page import="hmfms.util.ObjectUtil"%>
<%@page import="java.io.File"%>
<%@page import="hmfms.util.FileDownloadUtil"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<%
		String filePath = (String)request.getAttribute("filePath");
		if (ObjectUtil.isEmpty(filePath)) filePath = request.getParameter("filePath");
		String fileName = (String)request.getAttribute("fileName");
		if (ObjectUtil.isEmpty(fileName)) fileName = request.getParameter("fileName");
		Boolean isResource = (Boolean)request.getAttribute("isResource");
		//isResource =TRUE 表示下载位于项目内的资源文件
		//isResource =FALSE 下载不是位于项目内的资源文件，而是用绝对路径表示的文件
		Boolean deleteOnExit = (Boolean)request.getAttribute("deleteOnExit");
		/* 下载文件 */
		FileDownloadUtil.downloadFile(request, response, filePath, fileName,isResource);
		if(Boolean.TRUE.equals(deleteOnExit)&&(Boolean.FALSE.equals(isResource)))
			new File(filePath).delete();
	    out.clear();
	    out = pageContext.pushBody(); 		
%>
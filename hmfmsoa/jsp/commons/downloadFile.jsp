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
		//isResource =TRUE ��ʾ����λ����Ŀ�ڵ���Դ�ļ�
		//isResource =FALSE ���ز���λ����Ŀ�ڵ���Դ�ļ��������þ���·����ʾ���ļ�
		Boolean deleteOnExit = (Boolean)request.getAttribute("deleteOnExit");
		/* �����ļ� */
		FileDownloadUtil.downloadFile(request, response, filePath, fileName,isResource);
		if(Boolean.TRUE.equals(deleteOnExit)&&(Boolean.FALSE.equals(isResource)))
			new File(filePath).delete();
	    out.clear();
	    out = pageContext.pushBody(); 		
%>
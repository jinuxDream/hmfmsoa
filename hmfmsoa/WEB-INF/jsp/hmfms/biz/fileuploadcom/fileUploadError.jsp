<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/jsp/commons/taglibs.jsp"%>
<%@page import="hmfms.util.StringUtil"%>

<html>
<head>

</head>
<body>
<script language="javascript" type="text/javascript">
	alert('<%=StringUtil.getString(request.getAttribute("fail_message")) %>');
<%
	String tran_type = StringUtil.getString(request.getAttribute("tran_type"));
%>
	window.location.href="<c:out value="${ctx}"/>/jsp/hmfms/biz/fileuploadcom/fileUpload.do?tran_type=<%=tran_type%>";
</script>
</body>
</html>


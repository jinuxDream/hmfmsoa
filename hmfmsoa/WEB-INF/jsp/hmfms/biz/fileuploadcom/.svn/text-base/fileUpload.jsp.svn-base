<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/jsp/commons/taglibs.jsp"%>
<%
String msg = request.getParameter("msg");
%>
<html>
<head>
<title> new document </title>
<%@ include file="/jsp/commons/meta.jsp"%>
<link rel="stylesheet" href="<c:out value="${ctx}"/>/style/style.css" type="text/css">
<script type="text/javascript" src="<c:out value="${ctx}"/>/js/fdutil.js"></script>
<style>
.buttons {
	FONT-SIZE: 12px;
	FONT-FAMILY: "宋体", "Arial";
	height: 21px;
	padding-top: 1px;
	border-color: #FFFFFF #000000 #000000 #FFFFFF;
	border-style: outset;
	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px
}
</style>
<SCRIPT LANGUAGE="JavaScript">
<!--

function upload()
{
	if( form1.reportFile.value=="" ){
		alert("必须选择文件！");
		return false;
	}
	$.webUtil.submit("<c:out value="${ctx}"/>/jsp/hmfms/biz/fileuploadcom/fileUploadDown.do");

}

function getDataByAjax()
{
	alert('123');
	//var filePath = form1.reportFile.value;
	var filePath = "filePath=" + "'D:/houimp.xls'";
	if(filePath.value!=""){
	
		var args = filePath;
		var ajax = new Ajax("getDataSDO.do", {onComplete: accrual, data: args});
		ajax.request();
		
	}
}
function accrual(txt)
{
	var jsonResult;
	try{
		jsonResult = Json.evaluate(txt);
		if(jsonResult.flag != "true")
		{
			alert('上传文件容量必须小于10M！');
			form1.reportFile.value = "";
		}
	}
	catch(err) {  
		alert(err);
	}
	
}

//-->
</SCRIPT>
</head>

<body>
<form name="form1" method="post" action="" enctype="multipart/form-data">
<input type="hidden" name="tran_type" value="<%=request.getAttribute("tran_type")%>">
<input type="hidden" name="index" value="<%=request.getAttribute("index")%>">
<table border="0" cellspacing="0" cellpadding="0">
<tr>
<td><input type="file" name="reportFile" size="40" class="buttons"></td>
<td>&nbsp;<input type="button" value="上 传" class="buttons" onclick="upload();return false;"></td></tr>
</table>
</form>
</body>
</html>

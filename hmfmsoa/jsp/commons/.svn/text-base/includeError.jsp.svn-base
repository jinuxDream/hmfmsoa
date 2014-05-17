<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %> 
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ page language="java" contentType="text/html; charset=GBK"  pageEncoding="GBK" isErrorPage="true" %>
<%@ include file="/jsp/commons/taglibs.jsp"%> 
<head>
<%@ include file="/jsp/commons/meta.jsp" %>
<SCRIPT LANGUAGE="JavaScript">

$(document).ready(function(){
	$('.btn').PicButton();
	
});

</SCRIPT> 
</head>
<html:html>
<!-- includeError.jsp -->
<body  >
	
		<table border="0" cellpadding="0" cellspacing="0" width="760" align="center"  style="margin:0 auto">
			<tr>
				<td ><div class="headline"  ><div class="headarrow"> &nbsp; &nbsp; &nbsp;</div>²Ù×÷Ê§°Ü</div> </td>
			</tr>
			
			
			<tr>
				<td height="<%=_PAGEBLOCK_HEIGHT %>"></td>
			</tr>
			<tr>
				<td align="center">
					<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolor="#9BB3D3">
						<tr>
							<td height="28" background="<c:out value="${ctx}"/>/images/title_innel_bg.gif" class="title">²Ù×÷Ê§°Ü</td>
						</tr>
						<tr>
							<td width="100%" align="center"><img border="0" src="<c:out value="${ctx}"/>/images/symbol_error.gif" /><br /> </td>
						</tr>
						<tr>
							<td align="center">
								<%exception.printStackTrace(); %>
								<%=exception.getMessage()%>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	
</body>
</html:html> 

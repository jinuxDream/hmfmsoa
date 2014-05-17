<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/jsp/commons/taglibs.jsp"%>
<%@page import="hmfms.util.ObjectUtil"%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<%@ include file="/jsp/commons/meta.jsp" %>
<title></title>
<script language="javascript">
$(document).ready(function(){
	$('.btn').PicButton();
	var g=$('.grid1').datagrid({hashead:true,trselect:false});

});	
</SCRIPT>
</head>
<body  >
<html:html>
<table border="0" cellpadding="0" align="center" cellspacing="0" width="680"  style="margin:0 auto">
	<tr><td><div class="headline"><div class="headarrow"> &nbsp; &nbsp; &nbsp;</div>404-无法找到页面</div></td></tr>
	<tr>
		<td height="20" ></td>
	</tr>
	<tr><td align="center"  >
	<table align="center"   class="grid1"  align="center"  border="0" cellpadding="0" cellspacing="0" width="80%" >
		    	<thead><tr><td><div align="center">页面不存在!</div></td></tr></thead>
		        <tr>
					<td align="center"   style="padding:20px">
						<table  align="center" border="0" cellpadding="0" cellspacing="0" width="300">
								
						<tr>
							<td width="100%" align="center">
							<div   class="error_icon"><br><br><br><br><br><br></div>										
							<br>您访问的页面系统无法找到！<br><br>
							</td>
							</tr>
							<tr>
							<td align="center">
		                    <button class="btn" onclick="window.history.back();return false;">返回</button>  
		                    </td>
		                </tr>
		              </table>
		            </td>
		        </tr>
		      </table>
	</td>
	</tr>
</table>
</html:html>
</body>

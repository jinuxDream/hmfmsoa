<%@ page language="java" contentType="text/html; charset=GBK"  pageEncoding="GBK"%>
<%@ include file="/jsp/commons/taglibs.jsp"%> 
<head>
<title><%=_PAGE_TITLE %></title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<link href="<c:out value="${ctx}"/>/style/applicationsystem.css" rel="stylesheet" type="text/css">
<link href="<c:out value="${ctx}"/>/style/style.css" rel="stylesheet" type="text/css">

</head>
<script language="javascript" src="<c:out value="${ctx}"/>/js/key.js"></script>

<body <%=_BODY_STUCT %> <%=_BODY_STYLE %> >

<html:html>
<center>
<table border="0" cellpadding="0" cellspacing="0" width="760"  style="margin:0 auto">

<tr>
		<td width="100%" class="bigbold" height="30"
			background="<c:out value="${ctx}"/>/images/sub_bg.gif"><img border="0"
			src="<c:out value="${ctx}"/>/images/home_title_leftarrow.gif" alt="">操作失败</td>
</tr>
<tr>
		<td height="<%=_PAGEBLOCK_HEIGHT %>"></td>
	</tr>
<tr>
    <td width="100%">
      <table border="1" cellpadding="0" cellspacing="0" width="100%"
			bordercolor="#9BB3D3">
			<tr>
				<td height="28" background="<c:out value="${ctx}"/>/images/title_innel_bg.gif"
					class="title">无权访问</td>
			</tr>
		      <tr>
				<td class="frameblue" align="center" style="padding:20px">
		    	<table border="0" cellpadding="0" cellspacing="0">
		      
		        <tr>
					<td align="center" class="frameblue" style="padding:20px">
						<table border="0" cellpadding="0" cellspacing="0" width="300">
								
						<tr>
							<td width="100%" align="center"><img border="0" src="<c:out value="${ctx}"/>/images/symbol_error.gif" alt=""><br>
							</td>
						</tr>
								
		                <tr>
		                  <td align="center" style="line-height:200%; font-size:14px">
							<br>您没有权限访问的该页面！</td>
							</tr>
							<tr>
							<td align="center">
		                    <input type="button" name="button" value="返回" onclick="window.history.back();return false;" class="btn"></td>
		                </tr>
		              </table>
		            </td>
		        </tr>
		      </table>
		     </td>
		  </tr>
</table></td>
  </tr>
</table>
</center>
</body>
</html:html>

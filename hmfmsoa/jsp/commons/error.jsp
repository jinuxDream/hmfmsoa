<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean" %> 
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ page language="java" contentType="text/html; charset=GBK"  pageEncoding="GBK"%>
<%@ include file="/jsp/commons/taglibs.jsp"%> 
<head>
	<title><%=_PAGE_TITLE %></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<link href="<c:out value="${ctx}"/>/style/applicationsystem.css" rel="stylesheet" type="text/css" />
	<link href="<c:out value="${ctx}"/>/style/style.css" rel="stylesheet" type="text/css" />
	<SCRIPT LANGUAGE="JavaScript">
		<!--
		var send_error_over = false;
		function send_error()
		{
			if(send_error_over){
				alert("您已经发送成功了！");
				return;
			}
			send_error_over = true;
			frames.ifm_senderror.document.form1.txt_error.value=document.getElementById("error_msg").innerText;
			frames.ifm_senderror.document.form1.error_from_where.value=document.getElementById("error_from_where").value;
			frames.ifm_senderror.document.form1.submit();
		}
		//-->
	</SCRIPT> 
</head>
<script language="javascript" src="<c:out value="${ctx}"/>/js/key.js"></script> 
<script language="javascript" src="<c:out value="${ctx}"/>/js/key.js"></script> 
<html:html>
<body <%=_BODY_STUCT %> <%=_BODY_STYLE %> >
	
		<table border="0" cellpadding="0" cellspacing="0" width="760" align="center"  style="margin:0 auto">
			<tr>
				<td width="100%" class="bigbold" height="30" background="<c:out value="${ctx}"/>/images/sub_bg.gif"><img border="0" src="<c:out value="${ctx}"/>/images/home_title_leftarrow.gif" alt="" /> 操作失败</td>
			</tr>
			<tr>
				<td height="<%=_PAGEBLOCK_HEIGHT %>"></td>
			</tr>
			<tr>
				<td align="center">
					<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolor="#9BB3D3">
						<tr>
							<td height="28" background="<c:out value="${ctx}"/>/images/title_innel_bg.gif" class="title">操作失败</td>
						</tr>
						<tr>
							<td width="100%" align="center"><img border="0" src="<c:out value="${ctx}"/>/images/symbol_error.gif" /><br /> </td>
						</tr>
						<tr>
							<td align="center">
								<%
									String ERROR_FROM_WHERE = (String)request.getAttribute(hmfms.base.OrgnBaseAction.ERROR_FROM_WHERE);
									if(hmfms.util.ObjectUtil.isEmpty(ERROR_FROM_WHERE))
										ERROR_FROM_WHERE = "not_from_action_forward";
									System.out.println("-->[error_jsp] " + ERROR_FROM_WHERE);
									int wloc = ERROR_FROM_WHERE.indexOf("?");
									if(wloc<0) wloc = ERROR_FROM_WHERE.length();
									ERROR_FROM_WHERE = ERROR_FROM_WHERE.substring(0, wloc);
									
									String msg = (String)request.getAttribute("msg_error");
									if(msg==null||msg.trim().length()<1) msg="";
									//out.println("uri=" + ERROR_FROM_WHERE);
									%> <input type="hidden" name="error_from_where" value="<%=ERROR_FROM_WHERE%>" /> 
								<div id="error_msg">
									<%=msg%><br /> 
									<html:errors />
								</div>
							</td>
						</tr>
						<tr>
							<td align="center"><input type="button" name="button" value="返回" onclick="window.history.back();return false;" class="btn"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	
</body>
</html:html> 

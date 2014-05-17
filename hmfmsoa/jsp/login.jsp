<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK" %>
<%@ include file="/jsp/commons/taglibs.jsp" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic" %>
<%
	session.setMaxInactiveInterval(8*60*60); // 1小时
	String isNeedLock=CfgSysPara.getParaValueByName("NEED_LOCK","true");	
%>

<%@page import="mng_plat.service.cfg.CfgSysPara"%><html>
	<%@ include file="/jsp/commons/meta.jsp" %>
	<script language="javascript">
function gotoCheckPass()
{
	if(event.keyCode == 13)
	{
		gotoPage();
	}
	else if(event.keyCode == 37)
	{
		$("#userName").attr("focus", true);

	}
}

function gotoCheck()
{
	if(event.keyCode == 13)
	{
		gotoPage();
	}
	else if(event.keyCode == 39)
	{
		$("#userPass").attr("focus", true);
	}
}
function unLock()
{
	url="<c:out value="${ctx}"/>/jsp/to_unLock.do";
    window.open(url, "Popup", "width=350,height=230,top=250,left=380");
}

function gotoPage()
{
	$.webUtil.submit("<c:out value="${ctx}"/>/jsp/login.do");	
}
function cancel()
{   
	$("#userPass").val("");
	$("#userName").val("");
	$("#userName").focus();

}
	</script>
<script type="text/javascript">
$(document).ready(function(){
	$(".btn").PicButton();
	$(":text:eq(0)").focus();	
});
</script>
<base target="_top">
<body text="#000000" topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" bgcolor="#E8EFF8">
<div style="margin-top:100px;" >
	<table border="0" cellpadding="0" cellspacing="0" width="100%" height="100%" align="center">
		<tr>
			<td width="100%" align="center">
				<table border="0" cellpadding="0" cellspacing="0" align="center" height="100%">
					<tr>
						<td width="100%" align="center">
							<table border="1" cellpadding="0" cellspacing="0" width="1000" align="center">
								<tr>
									<td height="428" width="100%" align="center" background="<c:out value="${ctx}"/>/images/login/homebg.jpg">
										<table border="0" cellpadding="0" cellspacing="0" width="780" align="center">
											<tr>
												<td height="17" colspan="2"></td>
											</tr>
											<tr>
												<td height="185" align="center" colspan="2">
													<table border="0" cellpadding="0" cellspacing="0" width="770" align="center">
														<tr>
															<td rowspan="2" valign="top"></td>
															<td valign="top" width="558" height="74"></td>
														</tr>
														<tr>
															<td height="101" valign="top" align="right">
																<table border="0" cellpadding="0" cellspacing="1" width="500" align="right">
																	<tr>
																		<td style="padding:0 0 10px" align="right"">
																			<form name="form1" action="" method="post">
																			<table border="0" cellpadding="2" cellspacing="2" width="454" align="center">
																				<tr><td height="20" width="100%" colspan="5"><html:errors /></td></tr>
																				<tr>
																					<td height="50" width="30" align="right" class="pr5">用户 </td>
																					<td width="70" align="left"><input name="userName" id="userName" type="text" size="10" maxlength="10" title="用户" value="" onkeydown="gotoCheck()" tabindex="1" class="text"></td>
																					<td width="30" align="right" class="pr5">密码 </td>
																					<td width="90" align="left"><input name="userPass" id="userPass" size="10" type="password" class="text" maxlength="12" title="密码" value="" tabindex="2" onkeydown="gotoCheckPass()"></td>
																					<td>
																						<button class="btn" type="button" onclick="gotoPage();return false;">&nbsp;登录&nbsp;</button>	
																						<button class="btn" type="button" onclick="cancel();return false;">&nbsp;取消&nbsp;</button>	
																						<%if("true".equals(isNeedLock)){%>
																						<button class="btn" type="button" onclick="unLock();return false;">自我解锁</button>	
																						<%}%>
																					</td>
																				</tr>																				
																			</table>
																			</form>
																			<table border="0" cellpadding="0" cellspacing="0" width="100%">
																				<tr>
																					<td width="100%" align="left"><font color="#E8EFF8">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;推荐使用IE(6.0以上版本)，分辨率1024X768，请不要使用弹出窗口的插件！ <br></td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</td>
											</tr>
											<tr>
												<td colspan="2" style="padding-left: 5px; padding-right: 5px; padding-bottom: 5px" align="center">
													<table border="0" cellpadding="0" cellspacing="0" width="100%" align="center"">
														<tr>
															<td width="100%" style="padding: 5px" align="center">
																<div style="padding: 10; width: 100%; height: 20; border-left: 0 white solid; border-right: 0 outset; border-bottom: 0 outset"><span id="span1"></span></div>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>
</body>
</html>

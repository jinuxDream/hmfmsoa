<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="fmt" %>

<%@page import="hmfms.base.ActionResultHmfms"%><c:set  var="ctx" value="${pageContext.request.contextPath}"/>
<%
String _PAGEBLOCK_HEIGHT = "5";
String _PAGE_TITLE = "\u4e0a\u6d77\u5e02\u7269\u4e1a\u76d1\u7ba1\u5e73\u53f0\uff08\u4e8c\u671f\uff09";
String back_url = request.getRequestURI();
String back_url_home = back_url.substring(back_url.indexOf("/WEB-INF/jsp")+12);
back_url_home = back_url_home.substring(0,back_url_home.lastIndexOf("/"))+"/index.do";
%>
<script>
function putback()
{
	var back_url_home = "<c:out value="${ctx}"/><%=back_url_home%>";
	var batch_no = $('input[name=batch_no]').val();
	var back_name = "<%=ActionResultHmfms.BACK_HOME_URL%>";
	var url="<c:out value="${ctx}"/>/mng_plat/biz/tradelog/reviewBack.do?batch_no="+batch_no+"&"+back_name+"="+back_url_home+"";
	$.webUtil.openWindow({title:"\u5ba1\u6838\u9000\u56de\u539f\u56e0",url:url,ismodel:true,width:750,height:450});
}
function goBackLog(batch_no,tran_status){
	var url = "<c:out value="${ctx}"/>/mng_plat/biz/tradelog/listGoBackLog.do?batch_no="+batch_no+"&tran_status="+tran_status+"";
	$.webUtil.openWindow({title:"\u5ba1\u6838\u9000\u56de\u4fe1\u606f",url:url,ismodel:true,width:600,height:400});
}

</script>
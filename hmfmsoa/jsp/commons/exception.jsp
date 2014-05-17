<%@page import="fd.commons.web.WebCoreAction"%>
<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%
//System.out.println("exception ........................");
String AccessType = (String)request.getAttribute(WebCoreAction.ACCESS_TYPE_SNID);
if(WebCoreAction.ACCESS_TYPE_AJAX.equals(AccessType)){
	//System.out.println("****************** AJAX ******************");
	String expmsg = (String)request.getAttribute(WebCoreAction.AJAX_EXCEPTION_MSGID);
	System.out.println("expmsg=["+expmsg+"]");
	response.setContentType("text/xml;charset=GBK");
	response.getWriter().write(expmsg);
	//response.getWriter().flush();
	return;
}
else if(WebCoreAction.ACCESS_TYPE_HTTP.equals(AccessType)){
	//System.out.println("Access Type wrong111!");
	request.getRequestDispatcher("/jsp/commons/exp_http.jsp").forward(request, response);
	return;
}
else{
	//System.out.println("Access Type wrong222!");
	request.getRequestDispatcher("/jsp/commons/exp_http.jsp").forward(request, response);
	return;
}
%>
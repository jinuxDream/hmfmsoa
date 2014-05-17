<%
	String toClose="true".equals(request.getParameter("toclose"))?"?toclose=true":"";
	String sUrl = request.getContextPath()+"/jsp/login.jsp"+toClose;
	session.removeAttribute(hmfms.base.BaseAction.USER_SESSION);
	response.sendRedirect(sUrl);
%>

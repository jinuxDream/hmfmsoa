package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;
import fd.commons.web.WebCoreAction;

public class exception_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    javax.servlet.jsp.PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html; charset=GBK");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");

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

    } catch (Throwable t) {
      out = _jspx_out;
      if (out != null && out.getBufferSize() != 0)
        out.clearBuffer();
      if (pageContext != null) pageContext.handlePageException(t);
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(pageContext);
    }
  }
}

package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;
import hmfms.base.ActionResultHmfms;

public class leftImage_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  static {
    _jspx_includes = new java.util.Vector(1);
    _jspx_includes.add("/jsp/commons/taglibs.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_set_var_value;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_out_value;

  public leftImage_jsp() {
    _jspx_tagPool_c_set_var_value = new org.apache.jasper.runtime.TagHandlerPool();
    _jspx_tagPool_c_out_value = new org.apache.jasper.runtime.TagHandlerPool();
  }

  public java.util.List getIncludes() {
    return _jspx_includes;
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_set_var_value.release();
    _jspx_tagPool_c_out_value.release();
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

      out.write("\n");
      out.write("<html>\r\n");
      out.write("<head>\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\r\n");
      out.write("\r\n");
      out.write("\r\n\r\n");
      if (_jspx_meth_c_set_0(pageContext))
        return;
      out.write("\r\n");

String _PAGEBLOCK_HEIGHT = "5";
String _PAGE_TITLE = "\u4e0a\u6d77\u5e02\u7269\u4e1a\u76d1\u7ba1\u5e73\u53f0\uff08\u4e8c\u671f\uff09";
String back_url = request.getRequestURI();
String back_url_home = back_url.substring(back_url.indexOf("/WEB-INF/jsp")+12);
back_url_home = back_url_home.substring(0,back_url_home.lastIndexOf("/"))+"/index.do";

      out.write("\r\n");
      out.write("<script>\r\nfunction putback()\r\n{\r\n\tvar back_url_home = \"");
      if (_jspx_meth_c_out_0(pageContext))
        return;
      out.print(back_url_home);
      out.write("\";\r\n\tvar batch_no = $('input[name=batch_no]').val();\r\n\tvar back_name = \"");
      out.print(ActionResultHmfms.BACK_HOME_URL);
      out.write("\";\r\n\tvar url=\"");
      if (_jspx_meth_c_out_1(pageContext))
        return;
      out.write("/mng_plat/biz/tradelog/reviewBack.do?batch_no=\"+batch_no+\"&\"+back_name+\"=\"+back_url_home+\"\";\r\n\t$.webUtil.openWindow({title:\"\\u5ba1\\u6838\\u9000\\u56de\\u539f\\u56e0\",url:url,ismodel:true,width:750,height:450});\r\n}\r\nfunction goBackLog(batch_no,tran_status){\r\n\tvar url = \"");
      if (_jspx_meth_c_out_2(pageContext))
        return;
      out.write("/mng_plat/biz/tradelog/listGoBackLog.do?batch_no=\"+batch_no+\"&tran_status=\"+tran_status+\"\";\r\n\t$.webUtil.openWindow({title:\"\\u5ba1\\u6838\\u9000\\u56de\\u4fe1\\u606f\",url:url,ismodel:true,width:600,height:400});\r\n}\r\n\r\n");
      out.write("</script>");
      out.write("\n");
      out.write("</head>\n");
      out.write("<body style=\"background-attachment: fixed; background-image: url('");
      if (_jspx_meth_c_out_3(pageContext))
        return;
      out.write("/images/main/menu_left.gif');background-attachment: fixed; background-repeat: repeat; background-position: right top\">\n");
      out.write("</body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      out = _jspx_out;
      if (out != null && out.getBufferSize() != 0)
        out.clearBuffer();
      if (pageContext != null) pageContext.handlePageException(t);
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(pageContext);
    }
  }

  private boolean _jspx_meth_c_set_0(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:set ---- */
    org.apache.taglibs.standard.tag.el.core.SetTag _jspx_th_c_set_0 = (org.apache.taglibs.standard.tag.el.core.SetTag) _jspx_tagPool_c_set_var_value.get(org.apache.taglibs.standard.tag.el.core.SetTag.class);
    _jspx_th_c_set_0.setPageContext(pageContext);
    _jspx_th_c_set_0.setParent(null);
    _jspx_th_c_set_0.setVar("ctx");
    _jspx_th_c_set_0.setValue("${pageContext.request.contextPath}");
    int _jspx_eval_c_set_0 = _jspx_th_c_set_0.doStartTag();
    if (_jspx_th_c_set_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_set_var_value.reuse(_jspx_th_c_set_0);
    return false;
  }

  private boolean _jspx_meth_c_out_0(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_0 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_0.setPageContext(pageContext);
    _jspx_th_c_out_0.setParent(null);
    _jspx_th_c_out_0.setValue("${ctx}");
    int _jspx_eval_c_out_0 = _jspx_th_c_out_0.doStartTag();
    if (_jspx_th_c_out_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_0);
    return false;
  }

  private boolean _jspx_meth_c_out_1(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_1 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_1.setPageContext(pageContext);
    _jspx_th_c_out_1.setParent(null);
    _jspx_th_c_out_1.setValue("${ctx}");
    int _jspx_eval_c_out_1 = _jspx_th_c_out_1.doStartTag();
    if (_jspx_th_c_out_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_1);
    return false;
  }

  private boolean _jspx_meth_c_out_2(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_2 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_2.setPageContext(pageContext);
    _jspx_th_c_out_2.setParent(null);
    _jspx_th_c_out_2.setValue("${ctx}");
    int _jspx_eval_c_out_2 = _jspx_th_c_out_2.doStartTag();
    if (_jspx_th_c_out_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_2);
    return false;
  }

  private boolean _jspx_meth_c_out_3(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_3 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_3.setPageContext(pageContext);
    _jspx_th_c_out_3.setParent(null);
    _jspx_th_c_out_3.setValue("${ctx}");
    int _jspx_eval_c_out_3 = _jspx_th_c_out_3.doStartTag();
    if (_jspx_th_c_out_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_3);
    return false;
  }
}

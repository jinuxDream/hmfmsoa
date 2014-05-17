package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;
import mng_plat.biz.sysmng.init.TaskTipDTO;
import hmfms.base.ActionResultHmfms;
import hmfms.util.*;
import mng_plat.service.workgroup.WorkGroup;
import hmfms.web.User;
import java.util.Map;
import fd.commons.jdbc.Result;
import fd.exception.BusinessException;

public class workspace_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  static {
    _jspx_includes = new java.util.Vector(2);
    _jspx_includes.add("/jsp/commons/taglibs.jsp");
    _jspx_includes.add("/jsp/commons/meta.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_set_var_value;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_out_value;

  public workspace_jsp() {
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

      out.write("\r\n");
      out.write("\r\n");
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
      out.write("\r\n");
      out.write("\r\n");

	User user = ActionUtil.getUser(request);
	Map<String,Result> map = (Map<String,Result>)request.getAttribute("map");
	Result rsRole = map.get("rsRole");
	Result rsGroup = map.get("rsGroup");
	String htmlMenu = (String)request.getAttribute("htmlMenu");
	
	 

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<HTML>\r\n");
      out.write("<HEAD>\r\n");
      out.write("</HEAD>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html;charset=GBK\">\r\n");
      out.write("<meta http-equiv=\"Cache-Control\" content=\"no-store\"/>\r\n");
      out.write("<meta http-equiv=\"Pragma\" content=\"no-cache\"/>\r\n");
      out.write("<meta http-equiv=\"Expires\" content=\"0\"/>\r\n");
      out.write("<title>");
      out.print(_PAGE_TITLE );
      out.write("</title>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      if (_jspx_meth_c_out_3(pageContext))
        return;
      out.write("/style/style.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      if (_jspx_meth_c_out_4(pageContext))
        return;
      out.write("/style/menu.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      if (_jspx_meth_c_out_5(pageContext))
        return;
      out.write("/style/printstyle.css\" />\r\n\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      if (_jspx_meth_c_out_6(pageContext))
        return;
      out.write("/vcol/style/web-ui.css\" />\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      if (_jspx_meth_c_out_7(pageContext))
        return;
      out.write("/vcol/style/your-custom.css\" />\r\n");
      out.write("<script language=\"javascript\" type=\"text/javascript\" src=\"");
      if (_jspx_meth_c_out_8(pageContext))
        return;
      out.write("/vcol/js/jquery-1.7.js\">");
      out.write("</script>\r\n");
      out.write("<script language=\"javascript\" type=\"text/javascript\" src=\"");
      if (_jspx_meth_c_out_9(pageContext))
        return;
      out.write("/vcol/js/web-ui.js\">");
      out.write("</script>\r\n");
      out.write("<script language=\"javascript\" type=\"text/javascript\" src=\"");
      if (_jspx_meth_c_out_10(pageContext))
        return;
      out.write("/vcol/js/web-commons.js\">");
      out.write("</script>\r\n");
      out.write("<script language=\"javascript\" type=\"text/javascript\" src=\"");
      if (_jspx_meth_c_out_11(pageContext))
        return;
      out.write("/vcol/js/web-ui_config.js\">");
      out.write("</script>\r\n");
      out.write("<script>$.webUtil.setting.msgboxtype='custom';");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<link href=\"");
      if (_jspx_meth_c_out_12(pageContext))
        return;
      out.write("/ligerUI/skins/Aqua/css/ligerui-all.css\" rel=\"stylesheet\" type=\"text/css\" /> \r\n");
      out.write("<script src=\"");
      if (_jspx_meth_c_out_13(pageContext))
        return;
      out.write("/ligerUI/js/ligerui.min.js\" type=\"text/javascript\">");
      out.write("</script> \r\n");
      out.write("<script language=\"javascript\">\r\nwindow.parent.htmlMenu(\"");
      out.print(htmlMenu);
      out.write("\");\r\n$(document).ready(function(){\r\n\t$('.grid2').datagrid({trselect:false});\r\n\t");
if(ObjectUtil.isEmpty(htmlMenu)){
      out.write("\r\n\t\tselectMenu();\r\n\t");
}
      out.write("\r\n});\r\nfunction selectMenu(){\r\n\t$.webUtil.openWindow(\r\n\t\t{title:\"选择用户或组\",applyTo:$(\".grid1\"),width:300,height:400,buttons:[\r\n\t\t\t{btn:'");
      out.write("<button>确定");
      out.write("</button>',onclick:function(e){\r\n\t\t\t\t$.webUtil.submit(\"workspace.do\");\r\n\t\t\t\t$(e.data.target).closeWebDialog();\r\n\t\t\t}\r\n\t\t\t},\r\n\t\t\t{btn:'");
      out.write("<button>关闭");
      out.write("</button>',onclick:function(e){$(e.data.target).closeWebDialog();}}\r\n\t\t]});\r\n\t$('.btn').PicButton();\r\n\t$('.grid1').datagrid({hashead:false,trselect:false});\r\n}\r\n//小区维护信息提示\r\nfunction gotoBuLu(sect_id){\r\n\t$.webUtil.submit(\"");
      if (_jspx_meth_c_out_14(pageContext))
        return;
      out.write("/cspplatinfo/baseinfo/sectmodify/addModifySectView.do?sect_id=\"+sect_id);\r\n}\r\nfunction gototran(url){\r\n\t$.webUtil.submit(\"");
      if (_jspx_meth_c_out_15(pageContext))
        return;
      out.write("/\"+url);\r\n}\r\n//小区整改情况信息提示\r\nfunction gotoSectCorrect(){\r\n\t$.webUtil.submit(\"");
      if (_jspx_meth_c_out_16(pageContext))
        return;
      out.write("/fourcheckmng/specialcheck/sectspecialcheck/correctaudit/index.do\");\r\n}\r\n");
      out.write("</script>\r\n");
      out.write("<script type=\"text/javascript\">$(function (){$(\"#accordion1\").ligerAccordion({height: 420});});");
      out.write("</script>\r\n\r\n");
      out.write("<style type=\"text/css\">\r\nbody{ padding:20px; margin:0}\r\n#accordion1{ width:250px;overflow:hidden;}\r\n");
      out.write("</style>\r\n\r\n");
      out.write("<body >\r\n");
      out.write("<form name=\"form1\" action=\"\" method=\"post\">\r\n ");
      out.write("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n\t");
      out.write("<tr>\r\n\t\t");
      out.write("<td>\r\n\t\t\t");
      out.write("<table width=\"760\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\" height=\"100%\">\r\n        \t\t");
      out.write("<tr>\r\n\t\t        \t");
      out.write("<td width=\"90\"   rowspan=\"2\"  align=\"center\" >&nbsp;");
      out.write("</td>\r\n\t\t\t\t\t");
      out.write("<td  colspan=\"2\"  valign=\"top\">\r\n\t\t\t\t\t");
      out.write("<div >\r\n\t\t\t\t\t\t \t");
      out.write("<div title=\"程序上线提醒\">\r\n\t\t\t\t\t\t  \t\t \r\n\t\t\t\t\t\t  \t");
      out.write("</div>\r\n\t\t\t\t\t\t  ");
      out.write("</div>\r\n\t\t\t\t\t");
      out.write("</td>\r\n        \t\t");
      out.write("</tr>\r\n        \t\t");
      out.write("<tr>\r\n          \t\t\t");
      out.write("<td  height=\"10\">&nbsp;");
      out.write("<font color=\"red\">");
      out.write("<b>");
      out.write("</b>");
      out.write("</font>");
      out.write("</td>\r\n        \t\t");
      out.write("</tr>\r\n      \t\t");
      out.write("</table>\r\n\t\t");
      out.write("</td>\r\n\t\t");
      out.write("<td width=\"150\">&nbsp;");
      out.write("</td>\r\n\t\t");
      out.write("<td>\r\n\t\t \r\n\t\t\t \t \r\n\t\t");
      out.write("</td>\r\n\t\t\r\n\t");
      out.write("</tr>\r\n");
      out.write("</table>\r\n");
      out.write("<table class=\"grid1\" width=\"80%\" style=\"display:none\" >\r\n\t");
      out.write("<tr>\r\n\t\t");
      out.write("<td align=\"center\">角色");
      out.write("</td>\r\n\t");
      out.write("</tr>\r\n\t");
      out.write("<tr>\r\n\t\t");
      out.write("<td>\r\n\t\t");
for(int i=0;i<rsRole.getRowCount();i++){ 
      out.write("\r\n\t\t\t");
      out.write("<input type=\"checkbox\" name=\"roled_id\" value=\"");
      out.print(rsRole.getString(i,"ro_roleid") );
      out.write("\"/>");
      out.print(rsRole.getString(i,"ro_name") );
      out.write("\r\n\t\t");
} 
      out.write("\r\n\t\t");
      out.write("</td>\r\n\t");
      out.write("</tr>\r\n\t\r\n\t");
      out.write("<tr>\r\n\t\t");
      out.write("<td align=\"center\">组");
      out.write("</td>\r\n\t");
      out.write("</tr>\r\n\t");
      out.write("<tr>\r\n\t\t");
      out.write("<td>\r\n\t\t");
for(int i=0;i<rsGroup.getRowCount();i++){ 
      out.write("\r\n\t\t\t");
      out.write("<input type=\"checkbox\" name=\"group_id\" value=\"");
      out.print(rsGroup.getString(i,"group_id") );
      out.write("\"/>");
      out.print(rsGroup.getString(i,"group_name") );
      out.write("\r\n\t\t");
} 
      out.write("\r\n\t\t");
      out.write("</td>\r\n\t");
      out.write("</tr>\r\n");
      out.write("</table>\r\n");
      out.write("</form>\r\n");
      out.write("</BODY>\r\n");
      out.write("</HTML>\r\n");
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

  private boolean _jspx_meth_c_out_4(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_4 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_4.setPageContext(pageContext);
    _jspx_th_c_out_4.setParent(null);
    _jspx_th_c_out_4.setValue("${ctx}");
    int _jspx_eval_c_out_4 = _jspx_th_c_out_4.doStartTag();
    if (_jspx_th_c_out_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_4);
    return false;
  }

  private boolean _jspx_meth_c_out_5(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_5 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_5.setPageContext(pageContext);
    _jspx_th_c_out_5.setParent(null);
    _jspx_th_c_out_5.setValue("${ctx}");
    int _jspx_eval_c_out_5 = _jspx_th_c_out_5.doStartTag();
    if (_jspx_th_c_out_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_5);
    return false;
  }

  private boolean _jspx_meth_c_out_6(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_6 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_6.setPageContext(pageContext);
    _jspx_th_c_out_6.setParent(null);
    _jspx_th_c_out_6.setValue("${ctx}");
    int _jspx_eval_c_out_6 = _jspx_th_c_out_6.doStartTag();
    if (_jspx_th_c_out_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_6);
    return false;
  }

  private boolean _jspx_meth_c_out_7(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_7 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_7.setPageContext(pageContext);
    _jspx_th_c_out_7.setParent(null);
    _jspx_th_c_out_7.setValue("${ctx}");
    int _jspx_eval_c_out_7 = _jspx_th_c_out_7.doStartTag();
    if (_jspx_th_c_out_7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_7);
    return false;
  }

  private boolean _jspx_meth_c_out_8(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_8 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_8.setPageContext(pageContext);
    _jspx_th_c_out_8.setParent(null);
    _jspx_th_c_out_8.setValue("${ctx}");
    int _jspx_eval_c_out_8 = _jspx_th_c_out_8.doStartTag();
    if (_jspx_th_c_out_8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_8);
    return false;
  }

  private boolean _jspx_meth_c_out_9(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_9 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_9.setPageContext(pageContext);
    _jspx_th_c_out_9.setParent(null);
    _jspx_th_c_out_9.setValue("${ctx}");
    int _jspx_eval_c_out_9 = _jspx_th_c_out_9.doStartTag();
    if (_jspx_th_c_out_9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_9);
    return false;
  }

  private boolean _jspx_meth_c_out_10(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_10 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_10.setPageContext(pageContext);
    _jspx_th_c_out_10.setParent(null);
    _jspx_th_c_out_10.setValue("${ctx}");
    int _jspx_eval_c_out_10 = _jspx_th_c_out_10.doStartTag();
    if (_jspx_th_c_out_10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_10);
    return false;
  }

  private boolean _jspx_meth_c_out_11(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_11 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_11.setPageContext(pageContext);
    _jspx_th_c_out_11.setParent(null);
    _jspx_th_c_out_11.setValue("${ctx}");
    int _jspx_eval_c_out_11 = _jspx_th_c_out_11.doStartTag();
    if (_jspx_th_c_out_11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_11);
    return false;
  }

  private boolean _jspx_meth_c_out_12(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_12 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_12.setPageContext(pageContext);
    _jspx_th_c_out_12.setParent(null);
    _jspx_th_c_out_12.setValue("${ctx}");
    int _jspx_eval_c_out_12 = _jspx_th_c_out_12.doStartTag();
    if (_jspx_th_c_out_12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_12);
    return false;
  }

  private boolean _jspx_meth_c_out_13(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_13 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_13.setPageContext(pageContext);
    _jspx_th_c_out_13.setParent(null);
    _jspx_th_c_out_13.setValue("${ctx}");
    int _jspx_eval_c_out_13 = _jspx_th_c_out_13.doStartTag();
    if (_jspx_th_c_out_13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_13);
    return false;
  }

  private boolean _jspx_meth_c_out_14(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_14 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_14.setPageContext(pageContext);
    _jspx_th_c_out_14.setParent(null);
    _jspx_th_c_out_14.setValue("${ctx}");
    int _jspx_eval_c_out_14 = _jspx_th_c_out_14.doStartTag();
    if (_jspx_th_c_out_14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_14);
    return false;
  }

  private boolean _jspx_meth_c_out_15(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_15 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_15.setPageContext(pageContext);
    _jspx_th_c_out_15.setParent(null);
    _jspx_th_c_out_15.setValue("${ctx}");
    int _jspx_eval_c_out_15 = _jspx_th_c_out_15.doStartTag();
    if (_jspx_th_c_out_15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_15);
    return false;
  }

  private boolean _jspx_meth_c_out_16(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_16 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_16.setPageContext(pageContext);
    _jspx_th_c_out_16.setParent(null);
    _jspx_th_c_out_16.setValue("${ctx}");
    int _jspx_eval_c_out_16 = _jspx_th_c_out_16.doStartTag();
    if (_jspx_th_c_out_16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_16);
    return false;
  }
}

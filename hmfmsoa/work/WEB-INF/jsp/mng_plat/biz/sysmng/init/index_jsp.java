package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.apache.jasper.runtime.*;
import hmfms.util.ActionUtil;
import hmfms.web.User;
import hmfms.base.ActionResultHmfms;
import hmfms.util.DateUtil;
import fd.commons.jdbc.Result;
import mng_plat.biz.sysmng.init.LoginMng;

public class index_jsp extends HttpJspBase {


  private static java.util.Vector _jspx_includes;

  static {
    _jspx_includes = new java.util.Vector(2);
    _jspx_includes.add("/jsp/commons/taglibs.jsp");
    _jspx_includes.add("/jsp/commons/meta.jsp");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_set_var_value;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_out_value;

  public index_jsp() {
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
      out.write("\r\n");

User user=ActionUtil.getUser(request);
String strDeptType = user.getDeptType();
String strTellName = user.getTellName();
String strDeptName = user.getDeptName();

String htmlMenu = (String)request.getAttribute("htmlMenu");

      out.write("\r\n\r\n\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n  ");
      out.write("<!--[if !(IE)]>");
      out.write("<!-->\r\n  ");
      out.write("<!--");
      out.write("<![endif]-->\r\n  ");
      out.write("<!--[if IE 8]>\r\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=8\" />\r\n  ");
      out.write("<![endif]--> \r\n  ");
      out.write("<!--[if IE 7]>\r\n  ");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=7\" />\r\n  ");
      out.write("<![endif]--> \r\n  ");
      out.write("<!--[if lte IE 6]>\r\n  ");
      out.write("<![endif]--> \r\n");
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
      out.write("<script language=\"javascript\" type=\"text/javascript\"> \r\nfunction toExit1(){\r\n\twindow.open(\"");
      if (_jspx_meth_c_out_12(pageContext))
        return;
      out.write("/hmfms/biz/init/exitSys.do\",\"_parent\",\"width=600,height=600,resu=no,scrollbars=yes\");\r\n}\r\nfunction toExit(){\r\n\t//alert(\"ddd\");\r\n\tvar req = new Request(\r\n\t{\r\n\t\tmethod: 'post',async: false,url: \"");
      if (_jspx_meth_c_out_13(pageContext))
        return;
      out.write("/hmfms/biz/init/exitSys.do?toclose=true\",\r\n\t\tevalScripts: false,\r\n\t\ttimeout: 3000,headers: {'Pragma': 'no-cache'},\r\n\t\tonRequest: function(item) {\r\n\t\t //alert(item);\r\n\t\t},\r\n\t\tonSuccess: function(html) {\r\n\t\t //alert(html);\r\n\t\t},\r\n\t\tonFailure: function(item) {\r\n\t\t// alert(item.responseText);\r\n\t\t},\r\n\t\tonTimeout: function(item) {\r\n\t\t\talert(\"不能正常退出，下次登陆请解锁!\");\r\n\t\t}\r\n\t}).send();\r\n}\r\nfunction window.onbeforeunload()\r\n{\r\nvar b = window.event.clientX > document.body.clientWidth - 20;\r\n// window.topframe 里的 topframe 与框架里的横向框架页 topframe 对应\r\n  \tvar statusb = \"fullscreen=0,toolbar=0,location=0,scrollbars=1,status=1,resizable=0\"; \r\n\tif(b &&event.clientY");
      out.write("<0||event.altKey){\r\n\t\t//alert(\"begin\");\r\n\t\ttoExit();\r\n\t}else{\r\n\t}\r\n}\r\n");
      out.write("</script>\r\n\r\n");
      out.write("<style type=\"text/css\">\r\n*{font-family:\"微软雅黑\";}\r\na:hover{text-decoration:none;}\r\nul{ padding:0; margin:0;}\r\n\r\n.top-button{\r\n\tfont-size:14px;\r\n\tcolor:#5578A2;\r\n}\r\n.top-button:hover{\r\n\tcolor:#F37B03;\r\n}\r\n\r\n/* \r\n\tLEVEL ONE \r\n*/\r\nul.dropdown{ \r\n\tposition: relative;\t\r\n\tmargin-top:2px;\t\r\n\tmin-width:500px;\r\n}\r\nul.dropdown li{\r\n\tmin-width:120px;\r\n\tfont-weight: normal; \r\n\tfloat: left; \r\n\tzoom: 1;\r\n\tborder:1px solid #275985;\r\n\tbackground:#2F8ECC;\r\n\tcursor:pointer;\r\n}\r\nul.dropdown a:hover{ \r\n\tcolor: #fff; \r\n}\r\nul.dropdown a:active{ \r\n\tcolor: #333; \r\n}\r\nul.dropdown li a{ \r\n\tdisplay: block; \r\n\tpadding: 4px 20px;\t\r\n\tborder-right: 1px solid #333;\r\n\tcolor: #fff;\r\n}\r\nul.dropdown li:last-child a{ \r\n\tborder-right: none; \r\n}\r\nul.dropdown li.hover,ul.dropdown li:hover{ \r\n\tbackground: #F37B03; \r\n\tcolor: #fff; \r\n\tposition: relative; \r\n}\r\nul.dropdown li:hover > a{ \r\n\tcolor: #fff; \r\n}\r\n\r\n/* \r\n\tLEVEL TWO\r\n*/\r\nul.dropdown ul{\t\r\n\tvisibility: hidden; \r\n\tposition: absolute; \r\n\ttop: 100%; \r\n\tleft: 0; \r\n}\r\nul.dropdown ul li{ \t\r\n\tfont-weight: normal; \r\n");
      out.write("\tbackground: #D2E6F6; \r\n\tcolor: #333; \r\n\tborder: 1px solid #FFF; \r\n\tborder-bottom: 1px solid #ccc;\r\n\tborder-right: 1px solid #ccc;\r\n\tfloat: none; \r\n\tpadding: 2px 0 ;\r\n}\t\t\t\t\t\t\t\t\t  \r\nul.dropdown ul li a{ \r\n\tborder-right: none; \r\n\twidth:80%;\r\n\tdisplay: inline-block;\r\n\tcolor: #333;\r\n} \r\n\r\n/* \r\n\tLEVEL THREE\r\n*/\r\nul.dropdown ul ul{ \r\n\tleft: 100%; \r\n\ttop: 0; \r\n\t_width:220px;\t\r\n}\r\nul.dropdown ul ul li {\r\n\tbackground:#FFEFDD;\r\n\tcolor: #333; \r\n\tborder: 1px solid #FFF; \r\n\tborder-bottom: 1px solid #ccc;\r\n\tborder-right: 1px solid #ccc;\r\n}\r\nul.dropdown ul ul li a{\r\n\twhite-space:nowrap;\r\n}\r\nul.dropdown li:hover > ul{ \r\n\tvisibility: visible;\r\n}\r\n\r\n/* FOR IE*/\r\n*ul.dropdown ul li{ \r\n\tdisplay: inline; \r\n\twidth: 100%;\r\n}\r\n");
      out.write("</style>\r\n\r\n");
      out.write("<script type=\"text/javascript\">\r\nfunction modiPass()\r\n{\r\n\turl=\"changePwd.do\";\r\n\twindow.open(url, \"Popup\", \"width=650,height=350,top=200,left=380\");\r\n}\r\n");
      out.write("</script>\r\n");
      out.write("<script type=\"text/javascript\">\r\nfunction htmlMenu(htmlMenu){\r\n  \t$(\".dropdown\").html(htmlMenu);\r\n  \t $(\"ul.dropdown li\").hover(function(){        \t\r\n         $(this).addClass(\"hover\");\r\n         $('ul:first',this).css('visibility', 'visible');\r\n \t\t\r\n     }, function(){    \r\n         $(this).removeClass(\"hover\");\r\n         $('ul:first',this).css('visibility', 'hidden');    \r\n     });\r\n     \r\n     $(\"ul.dropdown ul li:has(ul)\").find(\"a:first\").append($(\"");
      out.write("<img style='position:absolute;right:5px;margin-top:5px' src='");
      if (_jspx_meth_c_out_14(pageContext))
        return;
      out.write("/images/menu/menu_arrow.gif'/>\"));\r\n     \r\n     /*单击菜单后关闭下拉菜单*/\r\n     $(\"ul.dropdown ul ul li a\").click(function(){\r\n     \t$(this).parents(\"li\").removeClass(\"hover\");\r\n         $('ul:first',$(this).parents(\"li\")).css('visibility', 'hidden');\r\n     });\r\n\r\n \t/*横向菜单浏览器兼容性修正，菜单按钮长度随菜单内容变化*/\r\n     if($.browser.msie && jQuery.browser.version==\"6.0\"){return; } //for IE6\r\n     $(\"ul.dropdown li:has(.sub_menu)\").each(function(){\r\n     \tvar maxLiLength=0;\r\n     \t$(this).find(\"li:has(ul)\").each(function(){    \t\t\r\n     \t\tif($(this).width()>maxLiLength) maxLiLength=$(this).width();\r\n     \t\tvar maxLiLength2=0;\r\n     \t\t$(this).find(\"li\").each(function(){\r\n     \t\t\tif($(this).width()>maxLiLength2) maxLiLength2=$(this).width();\r\n     \t\t});\r\n     \t\t$(this).find(\".sonsub_menu\").width(maxLiLength2);   \t\t   \t\t\r\n     \t});\r\n     \t$(this).find(\".sub_menu\").width(maxLiLength);\r\n     });\r\n}\r\n$(document).ready(function(){\r\n\thtmlMenu(\"");
      out.print(htmlMenu);
      out.write("\");\r\n});\r\nfunction selectMenu(){\r\n\tmainframe.window.selectMenu(); \r\n}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body scroll=\"no\">\r\n");
      out.write("<form name=\"form1\" target=\"mainframe\" method=\"post\">\r\n");
      out.write("<div align=\"center\">\r\n\t");
      out.write("<div id=\"top\">\r\n\t\t");
      out.write("<table id=\"topTab\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n\t\t\t");
      out.write("<tr> \r\n\t\t\t\t");
      out.write("<td width=\"20%\" background=\"");
      if (_jspx_meth_c_out_15(pageContext))
        return;
      out.write("/images/main/top_bg.gif\" valign=\"top\" align=\"left\">");
      out.write("<img border=\"0\" src=\"");
      if (_jspx_meth_c_out_16(pageContext))
        return;
      out.write("/images/main/top_name.jpg\" width=\"702\" height=\"80\">");
      out.write("</td>\r\n\t\t\t\t");
      out.write("<td width=\"20%\" background=\"");
      if (_jspx_meth_c_out_17(pageContext))
        return;
      out.write("/images/main/top_bg.gif\">");
      out.write("</td>\r\n\t\t\t\t");
      out.write("<td valign=\"top\" align=\"right\" background=\"");
      if (_jspx_meth_c_out_18(pageContext))
        return;
      out.write("/images/main/top_bg.gif\">\r\n\t\t\t\t\t");
      out.write("<table style=\"position: absolute; right:20px; top: 2px; width: 220px;\">\r\n\t\t\t\t\t\t");
      out.write("<tr>\r\n\t\t\t\t\t\t");
      out.write("<td width=\"100\" align=\"right\">");
      out.write("<a class=\"nowrap top-button\" href=\"");
      if (_jspx_meth_c_out_19(pageContext))
        return;
      out.write("/mng_plat/biz/sysmng/init/workspace.do\" target=\"mainframe\">首页--");
      out.write("</a>");
      out.write("</td>\r\n\t\t\t\t\t\t");
      out.write("<td width=\"100\" align=\"right\">");
      out.write("<a class=\"nowrap top-button\" onclick=\"modiPass();return false;\" href=\"");
      if (_jspx_meth_c_out_20(pageContext))
        return;
      out.write("/mng_plat/biz/sysmng/init/top.do\">修改密码--");
      out.write("</a>");
      out.write("</td>\r\n                        ");
      out.write("<td width=\"100\" align=\"right\">");
      out.write("<a class=\"nowrap top-button\" href=\"");
      if (_jspx_meth_c_out_21(pageContext))
        return;
      out.write("/mng_plat/biz/sysmng/init/logout.do\" target=\"_parent\">退出系统--");
      out.write("</a>");
      out.write("</td>\r\n\t\t\t\t\t\t");
      out.write("<td width=\"100\" align=\"right\">");
      out.write("<a class=\"nowrap top-button\" href=\"#\" onclick=\"selectMenu();\">");
      out.write("<font color=\"red\">重新选择");
      out.write("</font>");
      out.write("</a>");
      out.write("</td>  \r\n\t\t\t\t\t\t");
      out.write("<td width=\"200\" align=\"right\">&nbsp;");
      out.write("</td> \r\n                        ");
      out.write("</tr>\r\n                        ");
      out.write("<tr>");
      out.write("<td colspan=\"10\" align=\"right\">\r\n\t                    \t");
      out.write("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\">\r\n\t                        \t");
      out.write("<tbody>\r\n\t                            \t");
      out.write("<tr>                                                   \r\n\t                                \t");
      out.write("<td width=\"26\">");
      out.write("<img border=\"0\" src=\"");
      if (_jspx_meth_c_out_22(pageContext))
        return;
      out.write("/images/main/title_user.gif\" width=\"26\" height=\"33\" />");
      out.write("</td>\r\n\t                                    ");
      out.write("<td width=\"12%\" class=\"whiteW nowrap\"  align=\"left\">");
      out.print(strTellName);
      out.write("</td>\r\n\t                                    ");
      out.write("<td width=\"26\">");
      out.write("<img border=\"0\" src=\"");
      if (_jspx_meth_c_out_23(pageContext))
        return;
      out.write("/images/main/title_department.gif\" width=\"49\" height=\"33\" />");
      out.write("</td>\r\n\t                                    ");
      out.write("<td width=\"12%\" class=\"whiteW nowrap\" align=\"left\">");
      out.print(strDeptName);
      out.write("</td>\r\n\t                                ");
      out.write("</tr>\r\n\t                           \t");
      out.write("</tbody>\r\n\t                       \t");
      out.write("</table>                                                           \t\r\n                        ");
      out.write("</td>");
      out.write("</tr>\r\n                        ");
      out.write("<tr>");
      out.write("<td colspan=\"10\" align=\"right\">\r\n                       \t\t ");
      out.write("<span class=\"whiteW nowrap pl20\">系统日期：");
      out.print(DateUtil.formatFromDB(DateUtil.getSysDate()) );
      out.write("</span>\r\n                        ");
      out.write("</td>");
      out.write("</tr>\r\n\t\t\t\t\t");
      out.write("</table>\r\n\t\t\t\t\t");
      out.write("<img border=\"0\" src=\"");
      if (_jspx_meth_c_out_24(pageContext))
        return;
      out.write("/images/main/top_right.gif\" width=\"20\" height=\"80\">\r\n\t\t\t\t");
      out.write("</td>\r\n\t  \t\t");
      out.write("</tr>\r\n  \t\t\t");
      out.write("<tr>                \t\r\n\t\t\t\t");
      out.write("<td colspan=\"3\">");
      out.write("<div align=\"left\">\r\n\t\t\t\t");
      out.write("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" background=\"");
      if (_jspx_meth_c_out_25(pageContext))
        return;
      out.write("/images/main/title_bg.gif\">\r\n\t\t          \t");
      out.write("<tbody>\r\n\t\t             \t");
      out.write("<tr>                  \t\r\n\t\t\t            \t");
      out.write("<td width=\"17\" align=\"left\" valign=\"top\" height=\"33\">");
      out.write("<img border=\"0\" src=\"");
      if (_jspx_meth_c_out_26(pageContext))
        return;
      out.write("/images/main/title_left.gif\" width=\"17\">");
      out.write("</td>                                \t\r\n\t\t\t                ");
      out.write("<td align=\"left\" valign=\"top\" height=\"33\">\r\n\t\t\t                    \t");
      out.write("<!-- 菜单 begin -->\r\n\t\t\t\t\t\t\t\t\t");
      out.write("<ul class=\"dropdown\">\r\n\t\t\t\t\t\t\t\t\t\t\r\n\t\t\t                        ");
      out.write("</ul>\r\n\t\t\t                        ");
      out.write("<div class=\"clear\">");
      out.write("</div>\r\n\t\t\t                        ");
      out.write("<!-- 菜单 end -->                                        \r\n\t\t\t               \t");
      out.write("</td>\r\n\t\t\t                ");
      out.write("<td width=\"14\" align=\"right\">");
      out.write("<img border=\"0\" src=\"");
      if (_jspx_meth_c_out_27(pageContext))
        return;
      out.write("/images/main/title_right.gif\" width=\"14\" height=\"33\">");
      out.write("</td>\r\n\t\t\t           \t\t");
      out.write("</tr>\r\n\t\t               \t");
      out.write("</tbody>\r\n\t\t           ");
      out.write("</table>");
      out.write("</div>\r\n    \t\t\t");
      out.write("</td>\r\n \t\t\t");
      out.write("</tr>    \r\n\t\t");
      out.write("</table>\r\n\t");
      out.write("</div>\r\n\t");
      out.write("<div id=\"middle\" class=\"nowrap\" style=\"min-width:880px;\">\r\n\t\t");
      out.write("<div id=\"middle_left\" style=\"width:8px;\" class=\"fl\">\r\n\t\t\t");
      out.write("<iframe src=\"leftImage.do\" id=\"leftFrame\" name=\"leftFrame\" frameborder=\"0\" scrolling=\"no\" title=\"leftFrame\" marginwidth=\"0\" marginheight=\"0\" style=\"width: 100%\">");
      out.write("</iframe>\t\t\r\n\t\t");
      out.write("</div>\t\t\r\n\t\t");
      out.write("<div id=\"middle_right\" style=\"width:9px;\" class=\"fr\">\r\n\t\t\t");
      out.write("<iframe src=\"rightImage.do\" id=\"rightFrame\" name=\"rightFrame\" frameborder=\"0\" scrolling=\"no\"  title=\"rightFrame\" marginwidth=\"0\" marginheight=\"0\" style=\"width: 100%\">");
      out.write("</iframe>\r\n\t\t");
      out.write("</div>\r\n\t\t");
      out.write("<div id=\"middle_main\" style=\"width:98%;border:0px solid red\" class=\"fr\">\r\n\t\t\t");
      out.write("<iframe src=\"workspace.do\" id=\"mainframe\" name=\"mainframe\" frameborder=\"0\" scrolling=\"auto\" marginwidth=\"0\" marginheight=\"0\" style=\"width: 100%;\">");
      out.write("</iframe>\r\n\t\t");
      out.write("</div>\r\n\t\t");
      out.write("<div class=\"clear\">");
      out.write("</div>\r\n\t");
      out.write("</div>\r\n\t");
      out.write("<div id=\"foot\" style=\"min-width: 880px;\">\r\n\t\t");
      out.write("<iframe src=\"foot.do\" id=\"bottomframe\"\tname=\"bottomframe\" \tframeborder=\"0\" scrolling=\"no\" marginwidth=\"0\" marginheight=\"0\" style=\"width:100%;height:50px\">");
      out.write("</iframe>\r\n\t");
      out.write("</div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n\t \t$(\"body\").css({\"overflow\":\"hidden\"}).attr(\"scroll\",\"no\");\r\n\t\tvar topFrameHeight = $(\"#top\").outerHeight();\r\n\t\tvar bottomframeHeight = $(\"#bottomframe\").outerHeight();\r\n\t\tvar screenHeight = $(document).height();\r\n\t\tvar mainFrameHeight = screenHeight-topFrameHeight-bottomframeHeight;\r\n\t\t$(\"#middle iframe\").height(mainFrameHeight).css(\"min-height\",\"450px\");\r\n\t\t\r\n\t\twindow.onresize=function(){\r\n\t\t\tvar screenHeight = $(window).height();\r\n\t\t\t//var screenWidth = $(document).width();\r\n\t\t\tvar mainFrameHeight = screenHeight-topFrameHeight-bottomframeHeight;\r\n\t\t\t$(\"#middle iframe\").height(mainFrameHeight);\t\t\t\t\t\t\r\n\t\t\t//$(\"#middle_main\").width(screenWidth-27);\r\n\t\t}\r\n\t");
      out.write("</script>\r\n");
      out.write("</div>\r\n");
      out.write("</form>\r\n");
      out.write("</body>\r\n");
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

  private boolean _jspx_meth_c_out_17(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_17 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_17.setPageContext(pageContext);
    _jspx_th_c_out_17.setParent(null);
    _jspx_th_c_out_17.setValue("${ctx}");
    int _jspx_eval_c_out_17 = _jspx_th_c_out_17.doStartTag();
    if (_jspx_th_c_out_17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_17);
    return false;
  }

  private boolean _jspx_meth_c_out_18(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_18 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_18.setPageContext(pageContext);
    _jspx_th_c_out_18.setParent(null);
    _jspx_th_c_out_18.setValue("${ctx}");
    int _jspx_eval_c_out_18 = _jspx_th_c_out_18.doStartTag();
    if (_jspx_th_c_out_18.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_18);
    return false;
  }

  private boolean _jspx_meth_c_out_19(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_19 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_19.setPageContext(pageContext);
    _jspx_th_c_out_19.setParent(null);
    _jspx_th_c_out_19.setValue("${ctx}");
    int _jspx_eval_c_out_19 = _jspx_th_c_out_19.doStartTag();
    if (_jspx_th_c_out_19.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_19);
    return false;
  }

  private boolean _jspx_meth_c_out_20(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_20 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_20.setPageContext(pageContext);
    _jspx_th_c_out_20.setParent(null);
    _jspx_th_c_out_20.setValue("${ctx}");
    int _jspx_eval_c_out_20 = _jspx_th_c_out_20.doStartTag();
    if (_jspx_th_c_out_20.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_20);
    return false;
  }

  private boolean _jspx_meth_c_out_21(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_21 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_21.setPageContext(pageContext);
    _jspx_th_c_out_21.setParent(null);
    _jspx_th_c_out_21.setValue("${ctx}");
    int _jspx_eval_c_out_21 = _jspx_th_c_out_21.doStartTag();
    if (_jspx_th_c_out_21.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_21);
    return false;
  }

  private boolean _jspx_meth_c_out_22(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_22 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_22.setPageContext(pageContext);
    _jspx_th_c_out_22.setParent(null);
    _jspx_th_c_out_22.setValue("${ctx}");
    int _jspx_eval_c_out_22 = _jspx_th_c_out_22.doStartTag();
    if (_jspx_th_c_out_22.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_22);
    return false;
  }

  private boolean _jspx_meth_c_out_23(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_23 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_23.setPageContext(pageContext);
    _jspx_th_c_out_23.setParent(null);
    _jspx_th_c_out_23.setValue("${ctx}");
    int _jspx_eval_c_out_23 = _jspx_th_c_out_23.doStartTag();
    if (_jspx_th_c_out_23.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_23);
    return false;
  }

  private boolean _jspx_meth_c_out_24(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_24 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_24.setPageContext(pageContext);
    _jspx_th_c_out_24.setParent(null);
    _jspx_th_c_out_24.setValue("${ctx}");
    int _jspx_eval_c_out_24 = _jspx_th_c_out_24.doStartTag();
    if (_jspx_th_c_out_24.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_24);
    return false;
  }

  private boolean _jspx_meth_c_out_25(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_25 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_25.setPageContext(pageContext);
    _jspx_th_c_out_25.setParent(null);
    _jspx_th_c_out_25.setValue("${ctx}");
    int _jspx_eval_c_out_25 = _jspx_th_c_out_25.doStartTag();
    if (_jspx_th_c_out_25.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_25);
    return false;
  }

  private boolean _jspx_meth_c_out_26(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_26 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_26.setPageContext(pageContext);
    _jspx_th_c_out_26.setParent(null);
    _jspx_th_c_out_26.setValue("${ctx}");
    int _jspx_eval_c_out_26 = _jspx_th_c_out_26.doStartTag();
    if (_jspx_th_c_out_26.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_26);
    return false;
  }

  private boolean _jspx_meth_c_out_27(javax.servlet.jsp.PageContext pageContext)
          throws Throwable {
    JspWriter out = pageContext.getOut();
    /* ----  c:out ---- */
    org.apache.taglibs.standard.tag.el.core.OutTag _jspx_th_c_out_27 = (org.apache.taglibs.standard.tag.el.core.OutTag) _jspx_tagPool_c_out_value.get(org.apache.taglibs.standard.tag.el.core.OutTag.class);
    _jspx_th_c_out_27.setPageContext(pageContext);
    _jspx_th_c_out_27.setParent(null);
    _jspx_th_c_out_27.setValue("${ctx}");
    int _jspx_eval_c_out_27 = _jspx_th_c_out_27.doStartTag();
    if (_jspx_th_c_out_27.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
      return true;
    _jspx_tagPool_c_out_value.reuse(_jspx_th_c_out_27);
    return false;
  }
}

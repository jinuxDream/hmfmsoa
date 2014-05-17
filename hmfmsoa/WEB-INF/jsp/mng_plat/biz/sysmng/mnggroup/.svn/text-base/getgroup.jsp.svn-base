<%@ page language="java" contentType="text/html; charset=GBK"
  pageEncoding="GBK"%>
<%@page import="fd.commons.jdbc.Result"%>  
<%@ include file="/jsp/commons/taglibs.jsp"%>
<%
  Result rsGroup = (Result)request.getAttribute("rsGroup");
  %>
<html>
  <head>
    <%@ include file="/jsp/commons/meta.jsp" %>
    <script language="javascript">
    /* 本页面的初始处理。 本函数必须有，如果没有初始处理，则函数中不需要写任何代码。*/
    $(document).ready(function(){	
    	$('.btn').PicButton();
    	var bar=$('.btn_toolbar').BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_condition 三个样式名称不能变之外其他都可以改变)
    	var g=$('.grid1').datagrid({toolbar:bar,trselect:false});//或执行代码 g[0].grid.setBar(bar);	也可以
    });	
    </script>
  </head>
  <body>
    <form name="form1" method="post" action="">
      <table width="680" align="center" border="0" cellpadding="0" cellspacing="1">
        <tr><td><div class="headline"><div class="headarrow">&nbsp;</div>工作组管理→详细信息</div></td></tr>
        <tr>
          <td valign="top">
                  <table width="100%"  class="grid1">
                  <thead>
			    	<tr>
			          <th colspan="2">工作组详细信息</th>
			        </tr>
			    </thead>
			    <tr>
                      <td width="20%" align="left" nowrap>
                        工作组编码
                      </td>
                      <td width="80%" height="23" align="left" nowrap>
                        <%=rsGroup.getString(0, "group_id")%>
                    </tr>
                    <tr>
                      <td width="20%" align="left" nowrap>
                        工作组名称
                      </td>
                      <td width="80%" height="23" align="left" nowrap>
                        <%=rsGroup.getString(0, "group_NAME")%>
                      </td>
                    </tr>
                    <tr>
                      <td nowrap width="20%" align="left">
                        描 述
                      </td>
                      <td height="23" align="left" nowrap><%=rsGroup.getString(0, "remark")%></td>
                    </tr>
                  </table>
          </td>
        </tr>
        <tr>
           <td align="center"><button class="btn" type="button" value="4" btn_href="index.do" >返回</button>
           </td>
         </tr>
      </table>
    </form>
  </body>
</html>

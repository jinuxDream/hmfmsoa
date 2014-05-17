<%@page import="hmfms.util.ObjectUtil"%>
<%@page import="hmfms.util.DateUtil"%>
<%@page import="hmfms.web.User"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<%@ include file="/jsp/commons/taglibs.jsp" %>
<%
  Result retMp = (Result) request.getAttribute("retMp");
  User user = ActionUtil.getUser(request);
  %>

<%@page import="fd.commons.jdbc.Result"%>
<%@page import="hmfms.util.ActionUtil"%><html>
<%@ include file="/jsp/commons/meta.jsp" %>

<script language="javascript">
$(document).ready(function(){	
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_condition 三个样式名称不能变之外其他都可以改变)
	var g=$('.grid1').datagrid({toolbar:bar,trselect:false});//或执行代码 g[0].grid.setBar(bar);	也可以
});	
function onSelectRow(index,val){}
      window.print();
      </script>
      </head>
      <body>
      <form action="" method="post" name="form1">
        <center>
      <TABLE cellSpacing="0" cellPadding="0" width="70%" border="0">
        <TBODY>
		  <tr>
            <td colspan="2" height="40">             
            </td>
          </tr>

          <TR>
            <TD class="bigbold" align="middle" colSpan="2">
              操作员基本资料表
            </TD>
          </TR>
          <tr>
            <TD width="57%" background="<c:out value="${ctx}"/>/images/yulan_bg.gif" class="ziheightfx">
              &nbsp;
            </TD>
            <TD align="right" background="<c:out value="${ctx}"/>/images/yulan_bg.gif" class="ziheightfx">
              打印日期:&nbsp;<%=DateUtil.getCNDate(DateUtil.getSysDate())%>
            </TD>
          </tr>
          <tr>
            <TD colSpan="2" valign="top">
              <DIV align="center">
                <TABLE id="list" borderColor="#000000" cellSpacing="0" cellPadding="0" width="100%" border="1">
                  <TBODY>
                    <tr>
                      <td width="25%" height="25" align="center" class="ziheightfx">
                        用户号                      </td>
                      <td width="75%" height="25" align="left" class="ziheightfx">
                       &nbsp;<%=retMp.getString(0,"te_operid")%>
                      </td>
                    </tr>
                    <tr>
                      <td height="25" align="center" class="ziheightfx">
                       操作员姓名                      </td>
                      <td height="25" align="left" class="ziheightfx">
                        &nbsp;<%=retMp.getString(0,"te_name")%>
                      </td>
                    </tr>
                    <tr>
                      <td height="25" align="center" class="ziheightfx">
                        工作密码                      </td>
                      <td height="25" align="left" class="ziheightfx">
                         &nbsp;<%=retMp.getString(0,"te_passwd")%>
                      </td>
                    </tr>
                    <tr>
                      <td height="25" align="center" class="ziheightfx">
                        解锁密码                      </td>
                      <td height="25" align="left" class="ziheightfx">
                        &nbsp;<%=retMp.getString(0,"te_unlock")%>
                      </td>
                    </tr>
                    <tr>
                      <td height="25" align="center" class="ziheightfx">
                        角 色                      </td>
                      <td height="25" align="left" class="ziheightfx">
                        &nbsp;<%=retMp.getString(0,"ro_name")%>
                      </td>
                    </tr>
                    <tr>
                      <td height="25" align="center" class="ziheightfx">
                        备 注                      </td>
                      <td height="25" align="left" class="ziheightfx" style='table-layout:fixed;word-break:break-all;'>
                        &nbsp;<%=retMp.getString(0,"te_remark")%>
                      </td>
                    </tr>
                </TABLE><br>
                <TABLE cellSpacing="0" cellPadding="0" width="100%" border="0">
                  <TR>
                    <TD width="57%" class="ziheightfx">
                      经办人：
                      <u>
                        <%=ObjectUtil.isEmpty(user.getTellName()) ? "" : user.getTellName()%>
                      </u>
                    </TD>
                    <TD width="43%" class="ziheightfx">
                      单位(盖章)_______________
                    </TD>
                  </tr>
                  <tr>
                    <td colspan="2">
                      注：密码只需要6位，请取得本单据后立即登录系统修改密码。
                    </td>
                  </tr>
                </table>
              </DIV>
            </TD>
          </tr>
           <tr>
            <td colspan="2" height="120">&nbsp;
              
            </td>
          </tr>
          <tr>
            <td colspan="2">
              <p style="margin:0.2cm">
              </p>
              <img src="<c:out value="${ctx}"/>/images/line2.gif" width="100%" height="15" />
              <p style="margin:0.4cm">
              </p>
            </td>
          </tr>
           <tr>
            <td colspan="2" height="80">&nbsp;
              
            </td>
          </tr>
          <TR>
            <TD class="bigbold" align="middle" colSpan="2">
              操作员基本资料表
            </TD>
          </TR>
          <tr>
            <TD width="57%" background="<c:out value="${ctx}"/>/images/yulan_bg.gif" class="ziheightfx">
              &nbsp;
            </TD>
            <TD align="right" background="<c:out value="${ctx}"/>/images/yulan_bg.gif" class="ziheightfx">
              打印日期:&nbsp;<%=DateUtil.getCNDate(DateUtil.getSysDate())%>
            </TD>
          </tr>
          <tr>
            <TD colSpan="2" valign="top">
              <DIV align="center">
                <TABLE id="list" borderColor="#000000" cellSpacing="0" cellPadding="0" width="100%" border="1">
                  <TBODY>
                    <tr>
                      <td width="25%" height="25" align="center" class="ziheightfx">
                        用户号                      </td>
                      <td width="75%" height="25" align="left" class="ziheightfx">
                      &nbsp;<%=retMp.getString(0,"te_operid")%>                      </td>
                    </tr>
                    <tr>
                      <td height="25" align="center" class="ziheightfx">
                        操作员姓名                      </td>
                      <td height="25" align="left" class="ziheightfx">
                      &nbsp;<%=retMp.getString(0,"te_name")%>                      </td>
                    </tr>
                    <tr>
                      <td height="25" align="center" class="ziheightfx">
                        工作密码                      </td>
                      <td height="25" align="left" class="ziheightfx">
                      &nbsp;<%=retMp.getString(0,"te_passwd")%>                      </td>
                    </tr>
                    <tr>
                      <td height="25" align="center" class="ziheightfx">
                        解锁密码                      </td>
                      <td height="25" align="left" class="ziheightfx">
                      &nbsp;<%=retMp.getString(0,"te_unlock")%>                      </td>
                    </tr>
                    <tr>
                      <td height="25" align="center" class="ziheightfx">
                        角 色                      </td>
                      <td height="25" align="left" class="ziheightfx">
                      &nbsp;<%=retMp.getString(0,"ro_name")%>                      </td>
                    </tr>
                    <tr>
                      <td height="25" align="center" class="ziheightfx">
                        备 注                      </td>
                      <td height="25" align="left" class="ziheightfx" style='table-layout:fixed;word-break:break-all;'>
                      &nbsp;<%=retMp.getString(0,"te_remark")%>                      </td>
                    </tr>
                </TABLE>
                <br>
                <TABLE cellSpacing="0" cellPadding="0" width="100%" border="0">
                  <TR>
                    <TD width="57%" class="ziheightfx">
                      经办人：
                      <u>
                        <%=ObjectUtil.isEmpty(user.getTellName()) ? "" : user.getTellName()%>
                      </u>
                    </TD>
                    <TD width="43%" class="ziheightfx">
                      单位(盖章)_________________
                    </TD>
                  </tr>
                  <tr>
                    <td colspan="2">
                      注：密码只需要6位，请取得本单据后立即登录系统修改密码。
                    </td>
                  </tr>
                </table>
              </DIV>
            </TD>
          </tr>
          <TR>
            <TD colSpan="2" height="10">
            </TD>
          </TR>
        </TBODY>
      </TABLE>
    </center>
  </BODY>
</HTML>

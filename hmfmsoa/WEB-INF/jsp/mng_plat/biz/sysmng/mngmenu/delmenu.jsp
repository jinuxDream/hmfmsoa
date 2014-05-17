<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<%@ include file="/jsp/commons/taglibs.jsp" %>
<%@page import="fd.commons.jdbc.Result"%>
<%
  Result rsMenu = (Result) request.getAttribute("rsMenu");
  Result rsSubMenu = (Result) request.getAttribute("rsSubMenu");
  String level = rsMenu.getString(0, "menu_level");
  boolean isFact = MenuFunLevel.JuTiJiaoYi.toString().equals(level);
  %>

<%@page import="hmfms.services.codes.MenuFunLevel"%><html>
<%@ include file="/jsp/commons/meta.jsp" %>

<script language="javascript">
$(document).ready(function(){	
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_condition 三个样式名称不能变之外其他都可以改变)
	var g=$('.grid1').datagrid({toolbar:bar,trselect:false});//或执行代码 g[0].grid.setBar(bar);	也可以
});	
function onSelectRow(index,val){}
function gotoindex()
{
  cleanData();
  $.webUtil.submit('index.do');
}
function cleanData()
{
  var inptArray = document.getElementsByTagName("input");
  var retVal = "";
  for (var i = 0; i < inptArray.length; i++)
  {
    inptArray[i].value=retVal;
  }
}
   </script>

<body>
    <form name="form1" method="post" action="">
    <input type="hidden" name="menu_id" value="<%=rsMenu.getString(0,"menu_id")%>" />

      <table width="960" style="margin:0 auto" border="0" cellpadding="0" cellspacing="0">
		<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>菜单管理→删除</div></td></tr>        
       <tr>
          <td>
             <table class="grid1" width="100%" >
             	<thead>
             		<tr>
             			<th colspan="2">菜单功能列表</th>
             		</tr>
             	</thead>
            
              <tr>
                <td width="20%" height="23" align="left" nowrap>
                  菜单编码
                </td>
                <td width="80%" height="23" align="left" nowrap>
                  <%=rsMenu.getString(0, "menu_id")%>
                </td>
              </tr>
              <tr>
                <td width="20%" height="23" align="left" nowrap>
                  菜单名称
                </td>
                <td width="80%" height="23" align="left" nowrap>
                  <%=rsMenu.getString(0, "menu_name")%>
                </td>
              </tr>
              <tr>
                <td width="20%" height="23" align="left" nowrap>
                  功能级别
                </td>
                <td width="80%" height="23" align="left" nowrap>
                  <%=MenuFunLevel.getValue(level)%>
                </td>
              </tr>
              <%
                if (isFact)//具体交易
                {
                	String thisParent = rsMenu.getString(0, "parent_id");//当前所属父菜单
                %>
              <tr>
                <td width="20%" height="23" align="left" nowrap>
                  父菜单编码
                </td>
                <td height="23" align="left" nowrap>
                  <%
                    for (int i = 0; i < rsSubMenu.getRowCount(); i++)
                    {
                    	String parent = rsSubMenu.getString(i, "menu_id");
                    	String name = rsSubMenu.getString(i, "menu_name");
                    	if (thisParent.equals(parent))
                    	{
                    %> <%=name%> <%
                    }
                    }
                    %>
                </td>
              </tr>
              <tr>
                <td width="20%" height="23" align="left" nowrap>
                  入口网页
                </td>
                <td width="80%" height="23" align="left" nowrap>
                  <%=rsMenu.getString(0, "url_link")%>
                </td>
              </tr>
              <%
                }
                %>
              <tr>
                <td nowrap height="23" width="20%" align="left">
                  描 述
                </td>
                <td nowrap height="23" width="80%" align="left">
                  <%=rsMenu.getString(0, "menu_desc")%>
                </td>
              </tr>
            </table>
            <table width="100%" border="0" cellspacing="0" cellpadding="5">
              <tr>
                <td align="center">
                <div>
                <button  type="button" class="btn" value="1"  btn_href="delmenuok.do" istip="1" mask>删除</button>
                <button  type="button" class="btn" value="2" onClick="gotoindex()" mask>返回</button>
                </div>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
  </form>
</body>
  
</html>

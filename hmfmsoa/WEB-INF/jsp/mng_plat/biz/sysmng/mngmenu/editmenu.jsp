<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<%@ include file="/jsp/commons/taglibs.jsp" %>
<%@page import="fd.commons.jdbc.Result"%>
<%
  Result rsMenu = (Result) request.getAttribute("rsMenu");
  Result rsSubMenu = (Result) request.getAttribute("rsSubMenu");
  String level = rsMenu.getString(0, "menu_level");
  boolean isFact = MenuFunLevel.JuTiJiaoYi.toString().equals(level);
  //取菜单级别,如果菜单级别为具体交易的则可以修改其上级菜单归属关系,如果不为具体交易,则不允许变更菜单级别,只能变更菜单编码、菜单名称、描述
  %>

<%@page import="hmfms.services.codes.MenuFunLevel"%><html>
<%@ include file="/jsp/commons/meta.jsp" %>

<script type="text/javascript">
$(document).ready(function(){	
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_condition 三个样式名称不能变之外其他都可以改变)
	var g=$('.grid1').datagrid({toolbar:bar,trselect:false});//或执行代码 g[0].grid.setBar(bar);	也可以
});	
function onSelectRow(index,val){}
function gotoindex()
{
  cleanData();
  $.webUtil.submit("index.do");
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
<input type="hidden" id="selectedUserid" value="" /> 
       <table width="960" style="margin:0 auto" border="0" cellpadding="0" cellspacing="1">
        <tr><td><div class="headline"><div class="headarrow">&nbsp;</div>菜单管理→编辑</div></td></tr> 
              <tr>
                <td colspan="2">
                   <table class="grid1" width="100%" >
               	<thead>
             		<tr>
             			<th colspan="2">菜单功能列表</th>
             		</tr>
             	</thead>
                </td>
              </tr>
              <tr>
                <td width="20%" height="23" align="left" nowrap>
                  菜单编码
                </td>
                <td width="80%" height="23" align="left" nowrap>
                  <input type="text" class="text" size="20" name="menu_id" maxlength="20" value="<%=rsMenu.getString(0,"menu_id") %>" readonly title="菜单编码"/>
                </td>
              </tr>
              <tr>
                <td width="20%" height="23" align="left" nowrap>
                  菜单名称
                </td>
                <td width="80%" height="23" align="left" nowrap>
                  <input type="text" class="text" size="20" name="menu_name" maxlength="20" value="<%=rsMenu.getString(0,"menu_name") %>" title="菜单名称"/>
                  <span>
                    <font color="red">
                      *
                    </font>
                  </span>
                </td>
              </tr>
              <tr>
                <td width="20%" height="23" align="left" nowrap>
                  功能级别
                </td>
                <td width="80%" height="23" align="left" nowrap>
                  <select name="menu_level" disabled v_empty="1">
                    <option value="<%=level%>" selected>
                      <%=MenuFunLevel.getValue(level)%>
                    </option>
                  </select>
                </td>
              </tr>
              <tr>
                <td width="20%" height="23" align="left" nowrap>
                  菜单位置序号
                </td>
                <td width="80%" height="23" align="left" nowrap>
                  <input type="text" class="emptytext" size="10" name="pos_code" maxlength="10" value="<%=rsMenu.getString(0, "pos_code") %>" title="菜单位置序号"/>
                  用于菜单排序
                </td>
              </tr>
              <%
                if (isFact)//具体交易
                {
                	String thisParent = rsMenu.getString(0, "parent_id");//当前所属父菜单
                %>
              <tr>
                <td width="20%" height="23" align="left" nowrap>
                  父菜单名称
                </td>
                <td height="23" align="left" nowrap>
                  <select name="parent_id">
                    <%
                      for (int i = 0; i < rsSubMenu.getRowCount(); i++)
                      {
                      	String parent = rsSubMenu.getString(i, "menu_id");
                      	String name = rsSubMenu.getString(i,"menu_name");
                      	if (thisParent.equals(parent))
                      	{
                      %>
                    <option value="<%=parent%>" selected>
                      <%=name%>
                    </option>
                    <%
                      } else
                      {
                      	%>
                    <option value="<%=parent%>">
                      <%=name%>
                    </option>
                    <%
                      }
                      }
                      %>
                  </select>
                </td>
              </tr>
              <tr>
                <td width="20%" height="23" align="left" nowrap>
                  入口网页
                </td>
                <td width="80%" height="23" align="left" nowrap>
                  <input type="text" class="emptytext" size="40" name="url_link" maxlength="128" value="<%=rsMenu.getString(0,"url_link")%>" title="入口网页"/>
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
                  <input type="text" class="emptytext" size="40" name="menu_desc" maxlength="512" value="<%=rsMenu.getString(0,"menu_desc")%>" title="描述"/>
                </td>
              </tr>
            </table>
              <table width="100%" border="0" cellspacing="0" cellpadding="5">
                <tr>
		 <td align="center">
			<div  >
				<button type="button" class="btn"  value="1" btn_href="editmenuok.do" istip="1" mask >保存</button>
				<button type="button" class="btn"  value="2" onclick="gotoindex()"  mask >返回</button>
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

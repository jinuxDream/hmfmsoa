<%@page import="fd.commons.jdbc.Result"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/jsp/commons/taglibs.jsp" %>
<%
  Result rsPriMenu = (Result) request.getAttribute("rsPriMenu");
  Result rsSubMenu = (Result) request.getAttribute("rsSubMenu");
  %>

<%@page import="hmfms.services.codes.MenuFunLevel"%>
<%@page import="hmfms.web.commons.SelectBoxHtml"%><html>
  <head>
   <%@ include file="/jsp/commons/meta.jsp" %>
<script type="text/javascript" language="javascript">
$(document).ready(function(){	
  	$(".btn").PicButton();
  	var bar=$(".btn_toolbar").BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_condition 三个样式名称不能变之外其他都可以改变)
  	var g=$(".grid1").datagrid({toolbar:bar,trselect:false});//或执行代码 g[0].grid.setBar(bar);	也可以
  });	
function getState()
{
  var lev = document.form1.menu_level.value;
  document.form1.pos_code.value = "00";
  if ( lev == <%= MenuFunLevel.YiJiCaiDan.toString()%> )
  {
    document.form1.primenu.value = 0;
    document.form1.primenu.disabled = true;
    document.all.item( 'pri_menu' ).style.display = "none";
    document.form1.submenu.value = 0;
    document.form1.submenu.disabled = true;
    document.all.item( 'sub_menu' ).style.display = "none";
    document.form1.parent_id.value = 0;
    document.all.item( 'entrance' ).style.display = "none";
    document.form1.url_link.value = "";
    document.form1.pos_code.value = "0<%=rsPriMenu.getRowCount()%>";
    return ;
  }
  if ( lev == <%= MenuFunLevel.ErJiCaiDan.toString()%> )
  {
    document.form1.primenu.value = 0;
    document.form1.primenu.disabled = false;
    document.all.item( 'pri_menu' ).style.display = "block";
    document.form1.submenu.value = 0;
    document.form1.submenu.disabled = true;
    document.all.item( 'sub_menu' ).style.display = "none";
    document.all.item( 'entrance' ).style.display = "none";
    document.form1.url_link.value = "";
    return ;
  }
  if ( lev == <%= MenuFunLevel.JuTiJiaoYi.toString()%> )
  {
    document.form1.primenu.value = 0;
    document.form1.primenu.disabled = false;
    document.all.item( 'pri_menu' ).style.display = "block";
    document.form1.submenu.value = 0;
    document.form1.submenu.disabled = false;
    document.all.item( 'sub_menu' ).style.display = "block";
    document.all.item( 'entrance' ).style.display = "block";
    return ;
  }
}

function gotosave()
{
  var lev = document.form1.menu_level.value;
  if ( lev == <%= MenuFunLevel.YiJiCaiDan.toString()%> )
  {
    document.form1.parent_id.value = document.form1.menu_id.value; //一级菜单的父菜单为本身
  }
  if ( lev == <%= MenuFunLevel.ErJiCaiDan.toString()%> )
  {
    var array = document.getElementsByName( "primenucode" );
    var max = array.length;
    var tmpvalue = "";
    for ( var i = 0; i<max; i ++ )
    {
      tmpvalue = array[ document.form1.primenu.selectedIndex ].value; //取一级菜单
    }
    document.form1.parent_id.value = tmpvalue; //二级菜单的父菜单为一级菜单
  }
  if ( lev == <%= MenuFunLevel.JuTiJiaoYi.toString()%> )
  {
    document.form1.parent_id.value = document.form1.submenu.value; //具体交易的父菜单为二级菜单
  }
  if ( document.form1.parent_id.value == 0||document.form1.parent_id.value == "" )
  {
    alert( "请指定上级菜单后,再保存" );
    return ;
  }
    if ( confirm( "确定要进行保存吗?" ) )
    {
    	$.webUtil.submit('addmenuok.do');
    }
}
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
var code1;
var code2;
var sel_index1;
function SetSubMenu( optionIndex )
{
  var arrayLength = document.form1.primenu.options.length;
  var arrayInstance = new Array( arrayLength );
  code1 = new Array( arrayLength );
  code1[ 0 ] = "";
  code2 = new Array( arrayLength );
  for ( i = 0; i<arrayLength; i ++ )
    arrayInstance[ i ] = new Array();
  arrayInstance[ 0][ 0 ] = new Option( "--请选择一级菜单--", "0" );
  <%for ( int k = 0; k<rsPriMenu.getRowCount(); k ++ )
  {
    int j =  - 1;
    %>code1[<%=(k+1)%>] = "<%=rsPriMenu.getString(k," pos_code ")%>";
    code2[<%=(k)%>] = new Array();
    <%String s_code = rsPriMenu.getString( k, "menu_id" );
    for ( int l = 0; l<rsSubMenu.getRowCount(); l ++ )
    {
      if ( rsPriMenu.getString( k, "menu_id" ).equals( rsSubMenu.getString( l, "parent_id" ) ) )
      {
        out.println( "code2[" + k + "][code2[" + k + "].length]=" + rsSubMenu.getString( l, "isum" ) + ";" );
      }
      String p_code = rsSubMenu.getString( l, "parent_id" );
      if ( p_code.equals( s_code ) )
      {
        %>arrayInstance[<%=(k+1)%>][<%=(j=j+1)%>] = new Option("<%=rsSubMenu.getString(l,"menu_name")%>", "<%=rsSubMenu.getString(l,"menu_id")%>" );
        <%
      }
    }
  }
  %>var Column = document.form1.submenu;
  for ( roleArrayLength = Column.options.length - 1; roleArrayLength>0; roleArrayLength -- )
    Column.options[ roleArrayLength ] = null;
  for ( j = 0; j<arrayInstance[ optionIndex ].length; j ++ )
  {
    Column.options[j] = new Option( arrayInstance[optionIndex][j].text, arrayInstance[optionIndex][j].value )
  }
  Column.options[0].selected = true;
  if ( document.form1.menu_level.value == '<%= MenuFunLevel.ErJiCaiDan.toString() %>' )
  {
    document.form1.pos_code.value = code2[ optionIndex - 1 ].length + 1;
  }
  //  document.getElementById("pos1").innerHTML=code1[optionIndex];
  sel_index1 = optionIndex;
  SetSubMenu2( 0 );
}
function SetSubMenu2( optionIndex )
{
	//alert("optionIndex=="+optionIndex);
  document.form1.pos_code.value = code2[sel_index1-1][optionIndex] + 1;
  var len = document.form1.pos_code.value.length;
  if ( len == 3 )
  {
    document.form1.pos_code.value = "00";
    return ;
  }
  if ( len<2 )
    document.form1.pos_code.value = "0" + document.form1.pos_code.value;
}
</script>
<body>
<form name="form1" method="post" action="">
<input type="hidden" id="selectedUserid" value="" /> 
    <input type="hidden" name="parent_id" value="" />
      <table border="0" cellpadding="0" cellspacing="0" width="960" style="margin:0 auto">
      	<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>菜单管理→新增</div></td></tr>   
        <tr>
          <td>
            <table width="100%" class="grid1">
              <tr >
                <th width="100%" align="center" colspan="2" >
                  菜单功能列表
                </th>
              </tr>
              <tr>
                <td width="20%" height="23" align="left" nowrap>
                  菜单编码
                </td>
                <td width="80%" height="23" align="left" nowrap>
                  <input type="text" class="text" size="30" name="menu_id" maxlength="40" title="菜单编码"/><font color="red">*</font>
                </td>
              </tr>
              <tr>
                <td width="20%" height="23" align="left" nowrap>
                  菜单名称
                </td>
                <td width="80%" height="23" align="left" nowrap>
                  <input type="text" class="text" size="30" name="menu_name" maxlength="20" title="菜单名称"/><font color="red">*</font>
                </td>
              </tr>
              <tr>
                <td width="20%" height="23" align="left" nowrap>
                  菜单级别
                </td>
                <td width="80%" height="23" align="left" nowrap>
                  <select name="menu_level" v_empty="1" onChange="getState()" style="width:100px">
                    <%=SelectBoxHtml.genOptions4QuanBu(MenuFunLevel.getCodeList(), MenuFunLevel.JuTiJiaoYi.toString())%>
                  </select>
                  <span>
                    <font color="red">
                      *
                    </font>
                  </span>
                </td>
              </tr>
              <tr id="pri_menu" style="display:block">
                <td height="23" align="left" nowrap>
                  一级菜单
                </td>
                <td height="23" align="left" nowrap>
                  <select name="primenu" v_empty="1" onchange="SetSubMenu(options.selectedIndex)" style="width:140px">
                    <option value="0">
                      --请选择一级菜单--
                    </option>
                    <%
                      for (int i = 0; i < rsPriMenu.getRowCount(); i++)
                      {
                      %>
                    <option value="<%=i+1%>">
                      <%=rsPriMenu.getString(i, "menu_NAME")%>
                    </option>
                    <%
                      }
                      %>
                  </select>
                  <input type="hidden" name="primenucode" value="0" />
                  <%
                    for (int i = 0; i < rsPriMenu.getRowCount(); i++)
                    {
                    %> 
                  <input type="hidden" name="primenucode" value="<%=rsPriMenu.getString(i, "menu_id")%>" />
                  <%
                    }
                    %>
                </td>
              </tr>
              <tr id="sub_menu" style="display:block">
                <td height="23" align="left" nowrap>
                  二级菜单
                </td>
                <td height="23" align="left" nowrap>
                  <select name="submenu" v_empty="1" onchange="SetSubMenu2(options.selectedIndex)" style="width:140px" v_empty="1">
                    <option value="0">
                      --请选择一级菜单--
                    </option>
                  </select>
                </td>
              </tr>
              <tr id="entrance" style="display:block">
                <td width="20%" height="23" align="left" nowrap>
                  入口网页
                </td>
                <td width="80%" height="23" align="left" nowrap>
                  <input type="text" class="emptytext" size="40"  name="url_link" maxlength="128" value=""  title="入口网页"/>
                </td>
              </tr>
              <tr style="display:block">
                <td width="20%" height="23" align="left" nowrap>
                  菜单位置序号
                </td>
                <td width="80%" height="23" align="left" nowrap>
                  <input type="text" class="emptytext" size="10" name="pos_code" maxlength="10" value=""  title="菜单位置序号"/>
                  用于菜单排序，可以手工修改，系统默认取本级菜单数+1
                </td>
              </tr>
              <tr>
                <td nowrap height="23" width="20%" align="left">
                  描&nbsp;述
                </td>
                <td nowrap height="23" width="80%" align="left">
                  <input type="text" class="emptytext" size="40" name="menu_desc" maxlength="512" value="" title="描述"/>
                </td>
              </tr>
              <tr>
                <td align="center" colspan="2"><button type="button" class="btn" onClick="gotosave();return false;">保存</button>
                <button type="button" class="btn" onClick="gotoindex();return false;">返回</button>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
  </form>
</body>
  
</html>

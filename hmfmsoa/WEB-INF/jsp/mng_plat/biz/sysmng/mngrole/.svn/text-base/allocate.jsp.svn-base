<%@ page language="java" contentType="text/html; charset=GBK"
  pageEncoding="GBK"%>
<%@ page import="fd.commons.jdbc.Result"%>
<%@page import="java.util.Map"%>
<%@page import="hmfms.util.StringUtil"%>
<%@ include file="/jsp/commons/taglibs.jsp"%>
<%
String roleid = request.getParameter("roleid");
String role_name = request.getParameter("role_name");
Result rsMenu = (Result)request.getAttribute("rsMenu");
%>
<html>
  <head>
    <%@ include file="/jsp/commons/meta.jsp" %>
<script type="text/javascript">
/* 本页面的初始处理。 本函数必须有，如果没有初始处理，则函数中不需要写任何代码。*/
$(document).ready(function(){	
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_condition 三个样式名称不能变之外其他都可以改变)
	var g=$('.grid1').datagrid({toolbar:bar,trselect:false});//或执行代码 g[0].grid.setBar(bar);	也可以
});	
function gotoindex()
{
  cleanData();
  $.webUtil.submit("index.do");
}

function cleanData()
{
  var inptArray = document.getElementsByTagName("input");
  var retVal = "";
  for (var i = 0; i<inptArray.length; i++)
  {
    inptArray[i].value = retVal;
  }
}

function chang_all()
{
  var all_td = document.getElementById("all_td");
  var inputArray = document.getElementsByTagName("tr");
  var len = inputArray.length;
  for (var i = 0; i<len; i++)
  {
    if (inputArray[i].id.indexOf("rowMain")>=0 || inputArray[i].id.indexOf("rowSub")>=0)
    {
      inputArray[i].style.display = (inputArray[i].style.display=="none") ? "inline" : "none";
    }
  }
  if (all_td.innerHTML.indexOf("d_down")>0)
  {
    all_td.innerHTML = "<img  id='all_img' border='0'  src='<c:out value="${ctx}"/>/images/menu/d_up.gif' />收缩";
  }
  else
  {
    all_td.innerHTML = "<img  id='all_img' border='0'  src='<c:out value="${ctx}"/>/images/menu/d_down.gif' />展开";
  }
}

function changimg(img)
{
  if (img.src.indexOf("d_down")>0)
  img.src = "<c:out value="${ctx}"/>/images/menu/d_up.gif";
  else
  img.src = "<c:out value="${ctx}"/>/images/menu/d_down.gif";
}
function gotosave()
{
  genMenu();
  
  if (confirm("确认要保存吗?") )
  {
    $.webUtil.submit("allocateok.do");
  }
}
function genMenu()
{
  var inputArray = document.getElementsByTagName("input");
  var len = inputArray.length;
  var renValue = "";
  for (var i = 0; i<len; i++)
  {
    if (inputArray[i].checked && inputArray[i].value != "")
    {
      renValue += inputArray[i].value + "|";
    }
  }
  form1.menu.value = renValue.substring(0, renValue.length-1);
}
function hide_display1(sign, event)
{
  var rowMain;
  rowMain = document.getElementsByName("rowMain" + sign);
  if (rowMain==null || rowMain.length==0)
  {
    return ;
  }
  if (rowMain[0].style.display=="none")
  {
    for (var i = 0; i<rowMain.length; i++)
    {
      rowMain[i].style.display = "";
    }
  }
  else
  {
    for (var i = 0; i<rowMain.length; i++)
    {
      rowMain[i].style.display = "none";
    }
  }
}
function hide_display(sign, e)
{
  e = window.event || e;
  var srcElement = e.srcElement || e.target;
  var rowSub = document.getElementsByName("rowSub");
  var value = document.getElementsByName("repValue");
  var srcid = srcElement.id;
  if ( (srcid * 1-1)==value[0].value)
  {
    if (rowSub[srcid * 1-1].style.display=="none")
    {
      rowSub[srcid * 1-1].style.display = "";
    }
    else
    {
      rowSub[srcid * 1-1].style.display = "none";
    }
  }
  else
  {
    rowSub[srcid * 1-1].style.display = "";
    rowSub[value[0].value].style.display = "none";
    value[0].value = srcid * 1-1;
  }
}

</script>
<script language="javascript" defer>
var n = document.getElementsByTagName("input");
var CheckBoxNum = n.length;
var SubNodeCheckSome, SameNodeCheckSome, tf, SearchNodeName;
var SearchParentNodeName, SameNodeNum, SubNodeCheckedNum;
var SameNodeCheckedNum, SubNodeNum, SubNodeCheckedTF;
function FindParentNode(SubNodeName) //获取上级结点名，并判断是否被选中
{
  tf = false; //初始化选中状态
  SearchNodeName = SubNodeName;
  t = SubNodeName.lastIndexOf("_"); //判断是否存在上级结点
  if (t !=  - 1)
    SearchNodeName = SubNodeName.substring(0, t);
  //如果存在上级结点，取得上级结点名
  if (document.all(SubNodeName).checked)
    tf = true;
  //判断结点是否被选中
  return SearchNodeName;
}

function CheckSubNode(NodeName) //获取结点名，并判断子结点是否选中
{
  SubNodeCheckedTF = false; //初始化子结点选中状态
  SubNodeNum = 0; //初始化子结点数目
  SameNodeNum = 0; //初始化同级结点数目
  SubNodeCheckedNum = 0; //初始化子结点被选中的数目
  SameNodeCheckedNum = 0; //初始化同级结点被选中的数目
  SubNodeCheckSome = 0; //初始化子结点半选数目
  SameNodeCheckSome = 0; //初始化同级结点半选数目
  ParentNodeName = FindParentNode(NodeName); //取得上级结点名
  SearchParentNodeName = NodeName; //当前结点名
  d = NodeName.lastIndexOf("_"); //判断是否存在上级结点
  if (d !=  - 1)
    SearchParentNodeName = SearchParentNodeName.substring(0, d);
  //如果存在上级结点，取得上级结点名
  for (i = 0; i < CheckBoxNum; i++)
  {
    if (n[i].name.length == NodeName.length && ParentNodeName == FindParentNode(n[i].name))
    {
      SameNodeNum += 1; //同级结点数目加一
      if (n[i].checked)
        SameNodeCheckedNum += 1;
      //同级结点被选中的数目加一
      if (n[i].indeterminate)
        SameNodeCheckSome += 1;
      //同级结点半选数目加一
    }
    if (n[i].name.substring(0, NodeName.length) == NodeName && n[i].name != NodeName && n[i].type == "checkbox")
    {
      SubNodeNum += 1; //子结点数数目加一
      if (n[i].checked)
        SubNodeCheckedNum += 1;
      //子结点被选中的数目加一
      if (n[i].indeterminate)
        SubNodeCheckSome += 1;
      //子结点半选数目加一
    }
  }
  if ((SameNodeNum == 1 || SameNodeCheckedNum == 0) && (SubNodeCheckedNum == 0) && !document.all(NodeName).checked)
    SubNodeCheckedTF = true;
  //判断子结点是否被选中
  if ((SameNodeNum >= 0 && SameNodeCheckedNum < SameNodeNum) || SameNodeCheckSome > 0 || SubNodeCheckSome > 0)
	 document.all(SearchParentNodeName).indeterminate = true;
  //将上级选中状态改为半选
  if ((SameNodeCheckedNum == SameNodeNum || SameNodeCheckedNum == 0) && SubNodeCheckSome == 0 && SameNodeCheckSome == 0)
    document.all(SearchParentNodeName).indeterminate = false;
  //取消上级半选状态
  return SearchParentNodeName;
}

function CheckAll(BoxName)
{
  SearchNodeName = BoxName;
  SearchParentNodeName = BoxName;
  SubNodeLength = BoxName.split("_").length;
  for (i = 0; i < CheckBoxNum; i++)
  {
    if (n[i].name.substring(0, BoxName.length) == BoxName && n[i].name != BoxName && n[i].type == "checkbox")
    {
      n[i].indeterminate = false; //取消半选状态
      n[i].checked = document.all(BoxName).checked ? true : false; //选中所有子结点
    }
  }
  for (j = 1; j < SubNodeLength; j++)
  {
	var obj = CheckSubNode(SearchParentNodeName);
	var checkboxObj = document.all(obj);
    checkboxObj.checked = SubNodeCheckedTF ? false : true; 
	//如果有子结点被选中，则选中上级结点，返之取消
  }
}

document.body.onclick = function()
{
  if (event.srcElement.type == "checkbox")
    CheckAll(event.srcElement.name);
}
</script>
<style type="text/css">
.menuori{font-size:11px;;font-family:tahoma;color:#FFFFFF;border:1px solid #275985;background-color:#2F8ECC}
.submenuori1{font-size:11px;;font-family:tahoma;color:#FFFFFF;border:1px solid #FFFFFF;background-color:#464787}
</style>
  </head>
  <body>
    <form name="form1" method="post" action="">
      <input type="hidden" name="menu" value="" />
      <input type="hidden" name="ro_roleid" value="<%=roleid%>" />
      <input type="hidden" name="repValue" value="0" />
      <input type="checkbox" name="menuid" value="" style="display:none">
      <INPUT TYPE="hidden" name="<%=hmfms.util.Constants.CHECK_EPASS_HIDDEN_NAME %>" value="false" />
      <INPUT TYPE="hidden" name="<%=hmfms.util.Constants.EPASS_ENCY_CODE_NAME %>" />
      <INPUT TYPE="hidden" name="<%=hmfms.util.Constants.EPASS_ENCY_CODE_NAME %>" />
      <table border="0" cellpadding="0" cellspacing="1" width="680" align="center">
        <tr><td><div class="headline"><div class="headarrow">&nbsp;</div> 角色管理→权限分配</div></td></tr>
        <tr>
   
        <td ><a id="all_td" href="#" onclick="chang_all();">
         <img  id="all_img" border="0"  src="<c:out value="${ctx}"/>/images/menu/d_down.gif" />展开
		</a>
        &nbsp;&nbsp;
		<strong><%=role_name %>的权限列表</strong>
		</td>
        </tr>
        <tr>
          <td>
     <table align="center" border="0" cellpadding="0" cellspacing="1" width="100%">
      <%
        hmfms.web.User user = (hmfms.web.User)request.getSession().getAttribute(hmfms.base.BaseAction.USER_SESSION);
        //List vInfo = (List)dist.CompMenu(user.getRoleID(), user.getTellID(),session.getId());
        user.setUserSessionID(session.getId());
        
        int iMainValue=0;
        int iSubValue=0;
        
        for(int i=0;i<rsMenu.getRowCount();i++){
		String str_i=  	StringUtil.fillString(Integer.toString(i), '0', 3);
        	
                %>
      <tr width="100%">
        <td  align="left" width="100%" height="25" id="rowC" class="menuori"    >         
          <img name="img1" onmousedown="hide_display1(<%=i+1%>,event);changimg(this);" style="cursor:hand" border="0" src="<c:out value="${ctx}"/>/images/menu/d_down.gif" 
          width="30" height="13" />
           <input type="checkbox" name="menuid_<%=str_i%>" value="<%=rsMenu.getString(i,"menu_id")%>"
		  <%=rsMenu.getString(i,"rcode").equals(rsMenu.getString(i,"mcode"))?"checked":""%> >
          <%=rsMenu.getString(i,"menu_name")%>
        </td>
      </tr>
      <tr id="temp1" style="display:none">
        <td>
        </td>
      </tr>
      <%
        Result rs1=(Result)rsMenu.getObject(i,"submenu");
        for(int j =0;j<rs1.getRowCount();j++){
        	iMainValue++;
        	String str_j=  	StringUtil.fillString(Integer.toString(j), '0', 3);
        %>
      <tr id="rowMain<%=i+1%>" name ="rowMain<%=i+1%>" style="display:none">
        <td>
          <table border="0" cellpadding="0" cellspacing="1" width="100%">
            <tr>
              <td height="25" class="submenuori1" id="<%=iMainValue%>" >
			  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			  <img id="<%=iMainValue%>" name="img<%=iMainValue%>"  onmousedown="hide_display('<%=iMainValue%>',event);changimg(this)" style="cursor:hand" border="0" 
			  src="<c:out value="${ctx}"/>/images/menu/d_down.gif" 
			  width="30" height="13" />
            <input type="checkbox" name="menuid_<%=str_i%>_<%=str_j%>"  value="<%=rs1.getString(j,"menu_id")%>"
		     <%=rs1.getString(j,"rcode").equals(rs1.getString(j,"mcode"))?"checked":"" %> >
                <%=rs1.getString(j,"menu_name")%>
              </td>
            </tr>
            <tr id="rowSub" name="rowSub" style="display:none">
              <td>
               <table border="0" cellpadding="0" cellspacing="0" id="list" width="100%" bordercolor="#9BB3D3">
                <%
                  Result rs2=(Result)rs1.getObject(j,"submenu");                  		
                  for(int k=0;k<rs2.getRowCount();k++){
                	  String str_k=  	StringUtil.fillString(Integer.toString(k), '0', 3);
                  	String menu_id=rs2.getString(k,"menu_id");					
                  	String strName=rs2.getString(k,"menu_name");				
                    	%>
                    <tr width="100%" >
                    
                    <td width="100%" height="20">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img border="0" src="<c:out value="${ctx}"/>/images/menu/d_up.gif" width="30" height="13" />
           				<input type="checkbox" name="menuid_<%=str_i%>_<%=str_j%>_<%=str_k%>" value="<%=rs2.getString(k,"menu_id")%>"
		     				<%=rs2.getString(k,"rcode").equals(rs2.getString(k,"mcode"))?"checked":"" %> >
                		<%=rs2.getString(k,"menu_name")%>
                    </td>
                  </tr>
                
                <%iSubValue++;}%>
                </table>
              </td>
            </tr>
          </table>
        </td>
      </tr>
      <%} %>
      <%}%>
    </table>     
          </td>
        </tr>
        <tr>
          <td align="center"><button class="btn" type="button" value="4" onclick="gotosave();return false;" >保存</button><button class="btn" type="button" value="4" onclick="gotoindex();return false;" >返回</button>
          </td>
        </tr>
      </table>
    </form>
  </body>
</html>

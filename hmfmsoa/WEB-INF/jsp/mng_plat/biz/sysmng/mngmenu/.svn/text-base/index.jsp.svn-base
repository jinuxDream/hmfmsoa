<%@page import="fd.commons.jdbc.Result"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/jsp/commons/taglibs.jsp" %>
<%
	Result rsMenu = (Result)request.getAttribute( "rsMenu" );
	String menucode = request.getParameter("ms_code1");
	if(menucode==null)menucode="";
%>

<%@page import="hmfms.services.codes.MenuFunLevel"%><html>
  <head>
<%@ include file="/jsp/commons/meta.jsp" %>
  <script type="text/javascript" language="javascript">
  /* 本页面的初始处理。 本函数必须有，如果没有初始处理，则函数中不需要写任何代码。*/
  $(document).ready(function(){	
  	$(".btn").PicButton();
  	var bar=$(".btn_toolbar").BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_condition 三个样式名称不能变之外其他都可以改变)
  	var g=$(".grid1").datagrid({toolbar:bar,onSelect:onSelectRow});//或执行代码 g[0].grid.setBar(bar);	也可以

  	function onSelectRow(index,el){
		var tr=$(el);
		var menu_id = $("input[name='menu_id_arr']",tr).val();
		$('#menu_id').attr("value",menu_id);
	}
  	
  });	
  function onSelectRow(index,val){
  		$("#ms_code").val(val);
  	}
    </script>
  </head>
<body >
<form name="form1" method="post" action="">
<input type="hidden" name="menu_id" id="menu_id" value="" />
	<table width="960" style="margin:0 auto" border="0" cellpadding="0" cellspacing="1">
      	<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>菜单管理</div></td></tr>     
		<tr><td height="<%=_PAGEBLOCK_HEIGHT%>"></td></tr>
        <tr>
          <td width="100%" align="left" class="frameblue">
          <div class="wrap-paramarea">
          	<div  style="float: left;">菜单编码:&nbsp;<input name="ms_code1" type="text" class="input" value="<%=menucode %>"/>&nbsp;&nbsp;</div>
          	<div><button class="btn" type="button" value="3" btn_href="index.do">重新筛选</button></div>
          	<div class="clear"></div>
          	</div>		
          </td>
        </tr>
         <tr>
          <td align="left" height="<%=_PAGEBLOCK_HEIGHT%>"> </td>
        </tr>
        <tr>
          <td width="100%">
          <div class="btn_toolbar" >
			<button class="btn" type="button" value="1" btn_href="addmenu.do" mask>新增 </button>
			<button class="btn" type="button" value="2" btn_href="editmenu.do" mask>编辑 </button>
			<button class="btn" type="button" value="3" btn_href="delmenu.do"  mask>删除</button>
				<div class="btn_condition" >
					<div id='condition_null'>新增</div>
					<div id='0'>新增,编辑,删除</div>
				</div> 
			</div> 
          </td>
        </tr>
        <tr>
          <td align="left" height="<%=_PAGEBLOCK_HEIGHT%>"> </td>
        </tr>
        <tr>
          <td width="100%" >
            <table width="100%" class="grid1">
              <tr>
                <td width="40" align="center" >&nbsp;</td>
                <td width="80" align="center" >菜单编码</td>
                <td width="150" align="center" >菜单名称 </td>
                <td width="80" align="center" >功能级别</td>
                <td width="120" align="center" >父菜单名称</td>
                <td width="90" align="center" >位置编码</td>
              </tr>
              <%String rowClass = "";
				for(int i = 0; i < rsMenu.getRowCount(); i++)
				{
                String ms_code = rsMenu.getString( i, "menu_id" );
                String ms_parent = rsMenu.getString( i, "menu_parent" );
                boolean isEqual = ms_code.equals( ms_parent ); 
                String parent = rsMenu.getString( i, "ms_parent_name" );
                if ( isEqual ){
                  parent = "&nbsp;&nbsp;";
                }
                %>
              <tr onclick="onSelectRow(<%=i%>,'<%=ms_code%>')">
                <td height="24" align="center" btn_condition='0'><%=i+1%>
                <input name="menu_id_arr" type="hidden" value="<%=ms_code %>">
                </td>
                <td align="left">&nbsp;&nbsp;<%=ms_code%></td>
                <td align="left">&nbsp;&nbsp;<%=rsMenu.getString(i, "menu_name")%></td>
                <td align="center"><%=MenuFunLevel.getValue(rsMenu.getString(i, "menu_level"))%></td>
                <td align="center"><%=parent%></td>
                <td align="center"><%=rsMenu.getString(i, "pos_code")%></td>
              </tr>
              <%}
                %>
            </table>
          </td>
        </tr>
      </table>
    </form>
  </body>
</html>

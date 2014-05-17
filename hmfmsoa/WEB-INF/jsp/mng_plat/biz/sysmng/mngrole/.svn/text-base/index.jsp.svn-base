<%@ page language="java" contentType="text/html; charset=GBK"
  pageEncoding="GBK"%>
<%@ include file="/jsp/commons/taglibs.jsp"%>
<%@page import="fd.commons.jdbc.Result"%>
<%
  Result rsRole = (Result) request.getAttribute("rsRole");
	String rolecode = request.getParameter("rolecode");
	if(rolecode==null)
		rolecode="";
  %>
<html>
  <head>
    <%@ include file="/jsp/commons/meta.jsp" %>
    <script language="javascript">
    /* 本页面的初始处理。 本函数必须有，如果没有初始处理，则函数中不需要写任何代码。*/
    $(document).ready(function(){	
    	$(".btn").PicButton();
    	var bar=$(".btn_toolbar").BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_condition 三个样式名称不能变之外其他都可以改变)
    	var g=$(".grid1").datagrid({toolbar:bar});//或执行代码 g[0].grid.setBar(bar);	也可以
    });	
    function onSelectRow(index,val,name){
    		$("#roleid").val(val);
     		$("#role_name").val(name);
    	}

    </script>
  </head>
  <body>
    <form name="form1" method="post" action="">
      <input type="hidden" value="" name="roleid" id="roleid" />
      <input type="hidden" value="0" name="sign" />
       <input type="hidden" name="role_name" id="role_name" value="" />
      <table width="960" style="margin:0 auto" border="0" cellpadding="0" cellspacing="1">
       	<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>角色管理</div></td></tr>
        <tr><td height="<%=_PAGEBLOCK_HEIGHT%>"></td></tr>
        <tr>
          <td width="100%" height="30" align="left" class="frameblue">
            <table border="0" cellpadding="0" cellspacing="0" width="100%">
             <tr>
          <td width="100%" align="left" class="frameblue">
          <div class="wrap-paramarea">
                  <div class="fl mr5 mt2">角色编码:&nbsp;
                  <input name="rolecode" type="text" class="input" value="<%=rolecode %>" /></div>
                  <div><button class="btn" type="button" value="6" btn_href="index.do"  mask>重新筛选 </button></div>
               	<div class="clear"></div>
          	</div>	
                </td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td width="100%"><div class="btn_toolbar" >
			<button class="btn" type="button" value="1" btn_href="addrole.do"  mask>新增 </button>
			<button class="btn" type="button" value="2" btn_href="getrole.do" mask>查看 </button>
			<button class="btn" type="button" value="3" btn_href="editrole.do" mask>编辑 </button>
			<button class="btn" type="button" value="4" btn_href="delrole.do"  mask>删除</button>
			<button class="btn" type="button" value="5" btn_href="allocate.do"  mask>分配权限</button>
				<div class="btn_condition" >
					<div id='condition_null'>新增</div>
					<div id='0'>新增,查看,编辑,删除,分配权限</div>
				</div> 
			</div> 
        </tr>
        <tr>
          <td width="100%">
            <table width="100%" class="grid1">
              <tr height="24" align="center">
                <td width="5%" >选择</td>
                <td width="25%" align="center">角色编码</td>
                <td align="center" >角色名称</td>
              </tr>

                <%	String rowClass = "";
					for(int i = 0; i < rsRole.getRowCount(); i++)
					{
                  	String strName = rsRole.getString(i, "RO_NAME").trim();
                  	String roleid = rsRole.getString(i, "RO_ROLEID").trim();
                  %>
              <tr onclick="onSelectRow(<%=i%>,'<%=roleid%>','<%=strName %>')">
                <td width="30" height="24" align="center" btn_condition='0'><%=i+1%></td>
                  <td width="15%" height="23" align="center">
                    <%=roleid%>
                  </td>
                  <td width="32%" align="left">
                    &nbsp;&nbsp;<%=strName%>
                  </td>
                </tr>
                <%
                  }
                  %>
              </table>
          </td>
        </tr>
      </table>
    </form>
  </body>
</html>

      
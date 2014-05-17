<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="fd.commons.jdbc.Result"%>
<%@ include file="/jsp/commons/taglibs.jsp" %>
<%
  Result rsGroup = (Result) request.getAttribute("rsGroup");
  %>
<html>
<%@ include file="/jsp/commons/meta.jsp" %>

<script language="javascript">
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
  for (var i = 0; i < inptArray.length; i++)
  {
    inptArray[i].value=retVal;
  }
}
</script>
<body>
<form name="form1" method="post" action="">
      <input type="hidden" name="group_id" value="<%=rsGroup.getString(0, "group_id")%>" />
      <table width="680" align="center" border="0" cellpadding="0" cellspacing="1">
      	<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>工作组管理→删除</div></td></tr>
        <tr><td height="<%=_PAGEBLOCK_HEIGHT%>"></td></tr>
        <tr>
          <td>
             <table class="grid1" width="100%" >
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
                        <%=rsGroup.getString(0, "group_name")%>
                      </td>
                    </tr>
                    <tr>
                      <td nowrap width="20%" align="left">
                        描 述
                      </td>
                      <td height="23" align="left" nowrap>
                        &nbsp;<%=rsGroup.getString(0, "remark")%>
                      </td>
                    </tr>
                  </table>
               
       
              <tr>
                <td align="center">
                	<div>
				  <button  type="button" class="btn" value="1" btn_href="delgroupok.do" istip="1" mask>删除</button>
				  <button  type="button" class="btn" value="2" onClick="gotoindex()" mask>返回</button>
				  </div>
                </td>
              </tr>
                </table>
    </form>
  </body>
</html>

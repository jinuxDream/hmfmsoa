
<%@page import="fd.commons.jdbc.Result"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<%@ include file="/jsp/commons/taglibs.jsp" %>
<%
  Result rsGroup = (Result) request.getAttribute("rsGroup");
  %>
<html>
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
      <input type="hidden" name="group_id" value="<%=rsGroup.getString(0, "group_id")%>" />
      <table width="680" align="center" border="0" cellpadding="0" cellspacing="1">
     	<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>角色管理→编辑</div></td></tr>
         <tr>
          <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
              <tr>
                <td colspan="2">
                   <table class="grid1" width="100%" >
             	<thead>
             		<tr>
             			<th colspan="2">修改角色</th>
             		</tr>
             	</thead>
                          <tr>
                            <td width="20%" height="23" align="left" nowrap>
                              角色编码
                            </td>
                            <td width="80%" height="23" align="left" nowrap>
                              <input type="text" class="text" size="20" value="<%=rsGroup.getString(0, "group_id")%>" name="tCode" maxlength="4" disabled />
                            </td>
                          </tr>
                          <tr>
                            <td width="20%" height="23" align="left" nowrap>
                              角色名称
                            </td>
                            <td width="80%" height="23" align="left" nowrap>
                              <input type="text" class="text" value="<%=rsGroup.getString(0, "group_name")%>" title="角色名称" size="20" name="group_name" maxlength="40" />
                            <span>
                          <font color="red">
                            *
                          </font>
                        </span></td>
                          </tr>
                          <tr>
                            <td nowrap height="23" width="20%" align="left">
                              描 述
                            </td>
                            <td nowrap height="23" width="80%" align="left">
                              <textarea cols="60"  maxlength="256" class="emptytextarea" rows="2" name="remark"><%=rsGroup.getString(0, "remark")%></textarea>
                            </td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                  </table>
                  <table width="100%" border="0" cellspacing="0" cellpadding="5">
                    <tr>
                      <td align="center">
                      <div  >
						<button  type="button" class="btn" value="1" btn_href="editgroupok.do" istip="1" mask >保存</button>
						<button  type="button" class="btn" value="2" onClick="gotoindex()" mask >返回</button>
							</div>
                      </td>
                    </tr>
                  </table>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </form>
  </body>
</html>

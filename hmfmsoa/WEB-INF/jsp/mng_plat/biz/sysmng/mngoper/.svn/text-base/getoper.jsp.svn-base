<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<%@ include file="/jsp/commons/taglibs.jsp" %>
<%@page import="hmfms.util.DateUtil"%>
<%@page import="java.util.Map"%>
<%@page import="hmfms.util.ObjectUtil"%>
<%
Result retMp = (Result) request.getAttribute("retMp");
User user = ActionUtil.getUser(request);
%>

<%@page import="fd.commons.jdbc.Result"%>
<%@page import="hmfms.util.ActionUtil"%>
<%@page import="hmfms.web.User"%>
<%@page import="hmfms.services.codes.DeptType"%><html>
<%@ include file="/jsp/commons/meta.jsp" %>

<script language="javascript">
$(document).ready(function(){	
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_condition 三个样式名称不能变之外其他都可以改变)
	var g=$('.grid1').datagrid({toolbar:bar,trselect:false});//或执行代码 g[0].grid.setBar(bar);	也可以
});	
function onSelectRow(index,val){}
function gotoPrint()
{
  if ( confirm( "确认要打印操作员资料吗？" ) )
  {
    url = "printoper.do?operatorid=" + form1.operatorid.value;
    $.webUtil.open(url);
  }
}

/* 本页面的初始处理。 本函数必须有，如果没有初始处理，则函数中不需要写任何代码。*/
function pageInit()
{
}
    </script>
  </head>
  <body>
  <form action="" method="post" name="form1">
  <input type="hidden" name="operatorid" value="<%=retMp.getString(0,"te_operid")%>" />
    <table width="960" style="margin:0 auto" border="0" cellpadding="0" cellspacing="1">
    <tr><td><div class="headline"><div class="headarrow">&nbsp;</div>操作员管理→详细信息</div></td></tr>
      <tr>
      	 <td>
            <div>
            	<%if("del".equals(request.getParameter("flag"))){%>
            		<button  type="button" class="btn" value="1" btn_href="deloperok.do" istip="1">删除</button>
            	<%}else{ %>
					<button  type="button" class="btn" value="1" onClick="gotoPrint()" mask>打印</button>
				<%}%>
				<button  type="button" class="btn" value="2" onClick="window.history.back()" mask>返回</button>
			</div>
         </td>
      </tr>            
            <tr>
                <td width="100%">
                  		   <table class="grid1" width="100%" >
             	<thead>
             		<tr>
             			<th colspan="2">操作员详细信息</th>
             		</tr>
             	</thead>
                          <tr class="contentwhite">
                            <td width="25%" height="23" align="center">
                              用户号
                            </td>
                            <td width="75%" align="left">
                              &nbsp;<%=retMp.getString(0,"te_operid")%>
                            </td>
                          </tr>
                          <tr class="contentblue">
                            <td align="center"  height="23" >
                              操作员姓名
                            </td>
                            <td align="left">
                              &nbsp;<%=retMp.getString(0,"te_name")%>
                            </td>
                          </tr>
                          <tr class="contentwhite">
                            <td align="center" height="23" >
                              工作密码
                            </td>
                            <td align="left">
                              &nbsp;<%=retMp.getString(0,"te_passwd")%>
                            </td>
                          </tr>
                          <tr class="contentblue">
                            <td align="center" height="23" >
                              解锁密码
                            </td>
                            <td align="left">
                              &nbsp;<%=retMp.getString(0,"te_unlock")%>
                            </td>
                          </tr>
                          <tr class="contentwhite">
                            <td align="center" height="23" >
                              单位类别
                            </td>
                            <td align="left">
                              &nbsp;<%=DeptType.getValue(retMp.getString(0,"te_dept_type"))%>
                            </td>
                          </tr>
                          <tr class="contentblue">
                            <td align="center" height="23" >
                              备 注
                            </td>
                            <td align="left" style='table-layout:fixed;word-break:break-all;'>
                              &nbsp;<%=retMp.getString(0,"te_remark")%>
                            </td>
                          </tr>
                        </table>
                    </tr>

                  </table>
    </form>
  </body>
</html>

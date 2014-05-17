<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/jsp/commons/taglibs.jsp" %>
<%@page import="fd.commons.jdbc.Result"%>
<%@page import="hmfms.web.User"%>
<%
	User user = ActionUtil.getUser(request);
%>
<%@page import="hmfms.util.ActionUtil"%>

<%@page import="hmfms.services.codes.DeptType"%><html>
<%@ include file="/jsp/commons/meta.jsp" %>
<script type="text/javascript">
$(document).ready(function(){	
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_condition 三个样式名称不能变之外其他都可以改变)
	var g=$('.grid1').datagrid({toolbar:bar,trselect:false});//或执行代码 g[0].grid.setBar(bar);	也可以
});	
function onSelectRow(index,val){}

function gotoSearch()
{
  if ( form1.te_dept_type.value == "" )
  {
    alert( "请选择合适的单位类别!" );
    return false;
  }
  var vType = form1.te_dept_type.value;
  url = "getdept.do?te_dept_type=" + vType;
   $.webUtil.open(url);
}
</script>
<body>
<form name="form1" method="post" action="">
<input type="hidden" id="selectedUserid" value="" /> 
    <table width="960" style="margin:0 auto" border="0" cellpadding="0" cellspacing="1">
      	<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>操作员管理→新增操作员</div></td></tr>
      <tr>
        <td>
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td valign="top" align="center">
                  <input type="hidden" name="org_id" value="" />
              </td>    
            </tr>
            <tr>
              <td>
              	<button  type="button" class="btn" value="1"  btn_href="addoperok.do" istip="1" mask>保存</button>
				<button  type="button" class="btn" value="2" btn_href="index.do" issubmit="0">返回</button>
              </td>
            </tr>         
			<tr><td height="<%=_PAGEBLOCK_HEIGHT%>"></td></tr>        
			<tr>
			<td>      
            <table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
              <tr>
                <td colspan="2">
                 <table class="grid1" width="100%" >
             	<thead>
             		<tr>
             			<th colspan="2">操作员信息添加</th>
             		</tr>
             	</thead>
                    <tr>
                      <td width="20%" height="23" align="center" nowrap>操作员姓名</td>
                      <td width="80%" height="23" align="left" nowrap>
                        <input type="text" class="text" v_empty="0"  v_min="1" v_max="20" size="20" name="te_name" maxlength="20" title="操作员姓名"/>
                        <span>
                          <font color="red">
                            *
                          </font>
                        </span>
                      </td>
                    </tr>
                    <tr>
                      <td width="20%" height="23" align="center" nowrap>单位类别</td>
                      <td width="80%" height="23" align="left" nowrap>
                        <select name="te_dept_type" v_empty="0"  v_min="1" style="width:180px">
                          <option value="">
                            ----请先选择单位类别----
                          </option>
                          	<%if(DeptType.ShiJu.equals(user.getDeptType())){	//市局白蚁专管	%>	
				            	<option value='<%=DeptType.ShiJu%>'><%=DeptType.getValue(DeptType.ShiJu)%></option>
				            	<option value='<%=DeptType.QuJu%>'><%=DeptType.getValue(DeptType.QuJu)%></option>
				            	<option value='<%=DeptType.WuYeGongSi%>'><%=DeptType.getValue(DeptType.WuYeGongSi)%></option>
				            	<option value='<%=DeptType.FangGuanBan%>'><%=DeptType.getValue(DeptType.FangGuanBan)%></option>
				            	<option value='<%=DeptType.XiaoQuGuanLiChu%>'><%=DeptType.getValue(DeptType.XiaoQuGuanLiChu)%></option>
				            <%} %>
                          	<%if(DeptType.QuJu.equals(user.getDeptType())){%>
				            	<option value='<%=DeptType.WuYeGongSi%>'><%=DeptType.getValue(DeptType.WuYeGongSi)%></option>
				            	<option value='<%=DeptType.FangGuanBan%>'><%=DeptType.getValue(DeptType.FangGuanBan)%></option>
				            	<option value='<%=DeptType.XiaoQuGuanLiChu%>'><%=DeptType.getValue(DeptType.XiaoQuGuanLiChu)%></option>
				            <%} %>
				            <%if(DeptType.WuYeGongSi.equals(user.getDeptType())){%>
				            	<option value='<%=DeptType.XiaoQuGuanLiChu%>'><%=DeptType.getValue(DeptType.XiaoQuGuanLiChu)%></option>
				            	<option value='<%=DeptType.YeWeiHui%>'><%=DeptType.getValue(DeptType.YeWeiHui)%></option>
				            <%} %>
                        </select>
                        <span>
                          <font color="red">
                            *
                          </font>
                        </span>
                      </td>
                    </tr>
                    <tr>
                      <td width="20%" height="23" align="center" nowrap>单位名称</td>
                      <td width="80%" height="23" align="left" nowrap>
                        <input type="text" class="text" size="40" v_empty="0"  v_min="1" v_max="40" value="" name="depart_name" readonly="true" title="单位名称"/>
                        <span>
                          <font color="red">
                            *
                          </font>
                        </span>
                        <a href="#" onClick="gotoSearch()">选择</a>
                      </td>
                    </tr>
                    <tr>
                      <td nowrap height="23" width="20%" align="center">备 注</td>
                      <td nowrap height="23" width="80%" align="left">
                        <input type="text" class="emptytext" size="40" v_min="1" v_max="255" v_empty="1" value="" name="te_remark" maxlength="512" title="备注"/>
                      </td>
                    </tr>
                  </table>
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


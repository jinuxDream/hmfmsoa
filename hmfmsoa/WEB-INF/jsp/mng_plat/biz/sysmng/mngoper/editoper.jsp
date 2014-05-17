<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="hmfms.web.User"%>
<%@page import="fd.commons.jdbc.Result,hmfms.util.ObjectUtil"%>
<%@page import="java.util.Map"%>

<%@ include file="/jsp/commons/taglibs.jsp"%>

<%
Result retMp = (Result) request.getAttribute("retMp");
User user = ActionUtil.getUser(request);
%>

<%@page import="hmfms.util.ActionUtil"%>
<%@page import="hmfms.services.codes.OperStatus"%>
<%@page import="hmfms.services.codes.DeptType"%><html>
<%@ include file="/jsp/commons/meta.jsp" %>

<script type="text/javascript">
$(document).ready(function(){	
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_condition 三个样式名称不能变之外其他都可以改变)
	var g=$('.grid1').datagrid({toolbar:bar,trselect:false});//或执行代码 g[0].grid.setBar(bar);	也可以
});	
</script>
<body>
<form name="form1" method="post" action="">
<input type="hidden" name="te_operid" id="te_operid" value="<%=retMp.getString(0,"te_operid")%>" />
	<table align="center" border="0" cellpadding="0" cellspacing="0" width="960" style="margin:0 auto">
		<tr><td align="left" ><div class="headline"><div class="headarrow">&nbsp;</div>操作员管理→编辑</div></td></tr>
		<tr>
			<td width="100%" align="left">
				<div class="">
					<button class="btn" type="button"  btn_href="editoperok.do" istip="1" >保存</button>
					<button class="btn" type="button"  onClick="javascript:history.back()";>返回</button>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<table class="grid1" width="100%">
				<tr>
             		<th colspan="2">操作员信息修改</th>
             	</tr>
				 <tr>
					<td width="25%" align="center"> 用户号</td>
					<td width="80%" align="left">&nbsp;<input type="text" class="text" size="20"  value="<%=retMp.getString(0,"te_operid")%>" name="operid1" maxlength="6" readonly /><span class="redW">*</span>
					</td>
				</tr>
				 <tr>
					 <td width="25%" align="center">操作员姓名</td>
                     <td width="80%" height="23" align="left" nowrap>
                     &nbsp;<input type="text" class="text" size="20" v_empty="0" v_min="1" v_max="20" value="<%=retMp.getString(0,"te_name")%>" name="te_name" maxlength="20" title="操作员姓名"/><span><font color="red">*</font></span>
                     </td>
				</tr>
				 <tr>
					 <td width="25%" align="center">操作员状态</td>
                     <td width="80%" align="left">
                     	<input type="hidden" name="te_state" value="<%=retMp.getString(0,"te_state") %>">
                        &nbsp;<%=OperStatus.getValue(retMp.getString(0,"te_state")) %>
                     </td>
				</tr>
                <tr>
                    <td width="25%" align="center">单位类别</td>
					<td width="80%" align="left">&nbsp;<%=DeptType.getValue(retMp.getString(0,"te_dept_type"))%>
				</tr>
				<tr>
                    <td width="25%" align="center">  备 注</td>
                    <td>&nbsp;<input type="text" class="emptytext" size="40" v_min="1" v_max="255" v_empty="1" value="<%=retMp.getString(0,"te_remark")%>" name="te_remark" maxlength="256" title="备注"/>
                    </td>
				</tr>
				</table>
			</td>
		</tr>
	</table>
</form>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	Map<String,Result> map = (Map<String,Result>)request.getAttribute("map");
	Result rsRole = map.get("rsRole");
	Result rsOrg = map.get("rsOrg");
	Result rsWorkGroup = map.get("rsWorkGroup");
%>
<%@page import="hmfms.web.commons.SelectBoxHtml"%>
<%@page import="hmfms.services.codes.TranType"%>
<%@page import="java.util.Map"%>
<%@page import="fd.commons.jdbc.Result"%>
<%@page import="hmfms.services.codes.TranRole"%>
<%@page import="hmfms.services.codes.TranStatus"%>
<%@page import="hmfms.services.codes.TranOper"%><html>
<head>
<%@ include file="/jsp/commons/taglibs.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<%@ include file="/jsp/commons/meta.jsp" %>
<script type="text/javascript">
$(document).ready(function(){	
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_conditon 三个样式名称不能变之外其他都可以改变)
	var g=$('.grid1').datagrid({toolbar:bar});//或执行代码 g[0].grid.setBar(bar); 也可以
	$('.grid2').datagrid({trselect:false});
	$('.grid3').datagrid({trselect:false,idCol:1,idStart:1});
});	
</script>
</head>
<body>
<form name="form1" id="form1" method="post" action="">
	<table border="0" cellpadding="0" cellspacing="0" width="980" align="center">
		<tr><td><div class="headline"><div class="headarrow"> &nbsp; &nbsp; &nbsp;</div>流程管理→流程管理</div></td></tr>
		<tr>
		<td width="100%">
			<table>
			<tr>
				<td>
					<button id="save" class="btn" type="button" btn_href="save.do" value="1" istip="1">保存</button>
					<button class="btn" type="button" value="2" btn_href="index.do" issubmit="0">返回</button>
				</td>
			</tr>
			</table>			
		</td>
	</tr>
	<tr>
		<td>
			<table id="houlist" width="100%"  class="grid2">
				<tr>
					<td colspan="2">流程使用范围</td>
				</tr>
				<tr>
					<td align="right">交易类型：</td>
					<td align="left">
						<select name="flow_scope.tran_type">
							<option value="">--请选择--</option>
							<%=SelectBoxHtml.genOptions(TranType.getCodeList()) %>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">选择使用范围：</td>
					<td align="left">
						<select name="flow_scope.flow_scope">
							<option value="">--请选择--</option>
							<%=SelectBoxHtml.genOptions(rsOrg) %>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">选择角色：</td>
					<td align="left">
						<select name="tran_role.ro_roleid">
							<option value="">--请选择--</option>
							<%=SelectBoxHtml.genOptions(rsRole) %>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">选择流程组：</td>
					<td align="left">
						<select name="tran_role.group_id">
							<option value="">--请选择--</option>
							<%=SelectBoxHtml.genOptions(rsWorkGroup) %>
						</select>
						&nbsp;&nbsp;&nbsp;&nbsp;<a href="#">新增流程组</a>
					</td>
				</tr>
				<tr>
					<td align="right">选择交易角色：</td>
					<td align="left">
						<select name="tran_role.tran_role">
							<option value="">--请选择--</option>
							<%=SelectBoxHtml.genOptions(TranRole.getCodeList()) %>
						</select>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td><button class="btn" type="button" onClick="$('.grid3').addrow();">添加</button><button type="button" class="btn" onClick="$('.grid3').delrow();">删除</button></td>
	</tr>
	<tr>
		<td>
			<table width="100%"  class="grid3">
				<tr>
					<td>序号</td>
					<td>下一个状态</td>
					<td>交易操作按钮名称</td>
					<td>当前状态</td>
				</tr>
				<tr>
					<td align="center">1</td>
					<td align="center">
						<select name="tran_flow.next_status">
							<option value="">--请选择--</option>
							<%=SelectBoxHtml.genOptions(TranStatus.getCodeList()) %>
						</select>
					</td>
					<td align="center">
						<select name="tran_flow.tran_oper">
							<option value="">--请选择--</option>
							<%=SelectBoxHtml.genOptions(TranOper.getCodeList()) %>
						</select>
					</td>
					<td align="center">
						<select name="tran_flow.curr_status">
							<option value="">--请选择--</option>
							<%=SelectBoxHtml.genOptions(TranStatus.getCodeList()) %>
						</select>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	</table>
</form>
</body>
</html>
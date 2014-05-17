<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*,fd.commons.jdbc.Result"%>
<%@page import="hmfms.util.ActionUtil"%>
<%@page import="hmfms.web.User"%>
<%@page import="hmfms.util.StringUtil"%>
<%@page import="hmfms.util.ObjectUtil"%>
<%@page import="fd.exception.BusinessException"%>
<%@ include file="/jsp/commons/taglibs.jsp" %>
<%
	User user =	ActionUtil.getUser(request);
	Result rsDept = (Result)request.getAttribute("rsDept");
	String te_dept_type = request.getParameter( "te_dept_type" );
%> 
<%@page import="fd.commons.jdbc.ResultRow"%>
<%@page import="hmfms.services.codes.*"%>
<%@page import="hmfms.web.commons.SelectBoxHtml"%>
<html>
<%@ include file="/jsp/commons/meta.jsp" %>
<script type="text/javascript">
/* 本页面的初始处理。 本函数必须有，如果没有初始处理，则函数中不需要写任何代码。*/
$(document).ready(function(){	
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_condition 三个样式名称不能变之外其他都可以改变)
	var g=$('.grid1').datagrid({toolbar:bar,trselect:false});//或执行代码 g[0].grid.setBar(bar);
	$('.grid2').datagrid({hashead:false,trselect:false});
});
function gotosave(){
	var val = $(':radio[name="radiobutton"]:checked').val();
	if (val=="" || undefined == val){
		alert("请先选择所属单位!");
		return ;
	}
	if (confirm("确认选择完成吗?") ){
		var opener = window.opener.document;
		var valName = val.split(",");
		$("input[name='depart_name']",opener).attr("value",valName[1]);
		$("input[name='org_id']",opener).attr("value",valName[0]);
		window.close();
 	}
}
</script> 
<body>
<form name="form1" method="post" action="">
<input id="te_operid" name="te_operid" type="hidden" value="">
	<table align="center" border="0" cellpadding="0" cellspacing="0" width="700" style="margin:0 auto">
		<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>操作员管理</div></td></tr>
		<!--间距 不可删除--><!--查询条件区 start--> 
		<!--查询条件区 end--> 
		<tr>
			<td>
				<table class="grid2" width="700">
					<tr>
						<input type="hidden" name="te_dept_type" value="<%=te_dept_type%>" />
						<td>单位编号：<input type="text" id="org_code" name="org_code" size="15" maxlength="200" value="<%=ObjectUtil.isEmpty(request.getParameter("org_code"))?"":request.getParameter("org_code") %>" /></td>
						<td>单位名称：<input type="text" id="org_name" name="org_name" size="15" maxlength="200" value="<%=ObjectUtil.isEmpty(request.getParameter("org_name"))?"":request.getParameter("org_name") %>" /></td>
						 <td>单位类别:&nbsp;<strong><%=DeptType.getValue(te_dept_type)%></strong></td>
						<td><button class="btn" type="button" btn_href="getdept.do">重新筛选</button> </td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
         	 <td>
				<button  type="button" class="btn" value="1" id="btnAdd" onclick="gotosave()" force>确定</button>
				<button  type="button" class="btn" value="2" onClick="window.close()"  >返回</button>
          	</td>
        </tr>
		<tr>
			<td>
				<table class="grid1" width="700">
					<tr height="24" align="center">
		                <td width="5%" align="center" >选择</td>
		                <td width="10%" align="center">序号</td>
		                <td width="20%" align="center" >单位编号</td>
						<td width="40%" align="center" >单位名称</td>
		                <td align="center" >单位类型</td>
					</tr>
					<%for(int i=0;i<rsDept.getRowCount();i++){%> 
					<tr>
						<td align="center">
							<input type="radio" name="radiobutton" value="<%=rsDept.getString(i,"org_id")%>,<%=rsDept.getString(i, "org_name")%>" />
						</td>
						<td align="center"><%=i+1%></td>
						<td align="center" width="10%"><%=rsDept.getString(i,"org_id")%></td>
						<td align="center"><%=rsDept.getString(i,"org_name")%></td>
						<td align="center"><%=DeptType.getValue(rsDept.getString(i,"dept_type"))%></td>
					</tr>
					<%}%> 
				</table>
			</td>
		</tr>
		<tr>
			<td align="right">
				<table border="0" cellspacing="0" cellpadding="0" class="wrap-page mt5">
					<tr>
						<td><%=rsDept.getPage()!=null? rsDept.getPage().getPageHtml("form1","getdept.do"):""%></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>
</body>
</html>

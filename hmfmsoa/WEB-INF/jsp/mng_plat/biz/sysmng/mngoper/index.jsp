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
	Result rsOper = (Result)request.getAttribute("rsOper");
	String roleid = StringUtil.getString(request.getParameter("ro_roleid_ind"));
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
			var g=$('.grid1').datagrid({toolbar:bar,onSelect:onSelectRow});//或执行代码 g[0].grid.setBar(bar);
			$('.grid2').datagrid({hashead:false,trselect:false});
			function onSelectRow(index,el){
				var tr=$(el);
				var org_id = $("input[name='te_operid_arr']",tr).val();
				$('#te_operid').attr("value",org_id);
			}
		});

</script> 
<body>
<form name="form1" method="post" action="">
<input id="te_operid" name="te_operid" type="hidden" value="">
	<table align="center" border="0" cellpadding="0" cellspacing="0" width="960" style="margin:0 auto">
		<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>操作员管理</div></td></tr>
		<!--间距 不可删除--><!--查询条件区 start--> 
		<!--查询条件区 end--> 
		<tr>
			<td>
				<table class="grid2" width="960">
					<tr>
						<td>操作员编号：<input type="text" id="te_id_ind" name="te_id_ind" size="15" maxlength="200" value="<%=ObjectUtil.isEmpty(request.getParameter("te_id_ind"))?"":request.getParameter("te_id_ind") %>" /></td>
						<td>操作员名称：<input type="text" id="te_name_ind" name="te_name_ind" size="15" maxlength="200" value="<%=ObjectUtil.isEmpty(request.getParameter("te_name_ind"))?"":request.getParameter("te_name_ind") %>" /></td>
						<td>
							操作员状态：
							<select name="te_state_ind">
								<option value="">--全部--</option>
								<%=SelectBoxHtml.genOptionsWithDefault(OperStatus.getCodeList(),request.getParameter("te_state_ind")) %>
							</select>
						</td>
						<td width="20%" >用户类别：
				            <select name="ro_roleid_ind" style="width: 150">
				              <option value="" selected >---全部---</option>
				             <%if(DeptType.ShiJu.equals(user.getDeptType())){%>
				            	<option value='<%=DeptType.ShiJu%>' <%if(roleid.equals(DeptType.ShiJu.toString())) {%>selected<%} %>><%=DeptType.getValue(DeptType.ShiJu)%></option>
				            	<option value='<%=DeptType.QuJu%>' <%if(roleid.equals(DeptType.QuJu.toString())) {%>selected<%} %>><%=DeptType.getValue(DeptType.QuJu)%></option>
				            	<option value='<%=DeptType.WuYeGongSi%>' <%if(roleid.equals(DeptType.WuYeGongSi.toString())) {%>selected<%} %>><%=DeptType.getValue(DeptType.WuYeGongSi)%></option>
				            	<option value='<%=DeptType.FangGuanBan%>' <%if(roleid.equals(DeptType.FangGuanBan.toString())) {%>selected<%} %>><%=DeptType.getValue(DeptType.FangGuanBan)%></option>
				            	<option value='<%=DeptType.XiaoQuGuanLiChu%>' <%if(roleid.equals(DeptType.XiaoQuGuanLiChu.toString())) {%>selected<%} %>><%=DeptType.getValue(DeptType.XiaoQuGuanLiChu)%></option>
				                <option value='<%=DeptType.YeWeiHui%>' <%if(roleid.equals(DeptType.YeWeiHui.toString())) {%>selected<%} %>><%=DeptType.getValue(DeptType.YeWeiHui)%></option>
				            <%} %>
				             <%if(DeptType.QuJu.equals(user.getDeptType())){%>
				            	<option value='<%=DeptType.WuYeGongSi%>' <%if(roleid.equals(DeptType.WuYeGongSi.toString())) {%>selected<%} %>><%=DeptType.getValue(DeptType.WuYeGongSi)%></option>
				            	<option value='<%=DeptType.FangGuanBan%>' <%if(roleid.equals(DeptType.FangGuanBan.toString())) {%>selected<%} %>><%=DeptType.getValue(DeptType.FangGuanBan)%></option>
				            	<option value='<%=DeptType.XiaoQuGuanLiChu%>' <%if(roleid.equals(DeptType.XiaoQuGuanLiChu.toString())) {%>selected<%} %>><%=DeptType.getValue(DeptType.XiaoQuGuanLiChu)%></option>
				            <%} %>
				            <%if(DeptType.WuYeGongSi.equals(user.getDeptType())){%>
				            	<option value='<%=DeptType.XiaoQuGuanLiChu%>' <%if(roleid.equals(DeptType.XiaoQuGuanLiChu.toString())) {%>selected<%} %>><%=DeptType.getValue(DeptType.XiaoQuGuanLiChu)%></option>
								<option value='<%=DeptType.YeWeiHui%>' <%if(roleid.equals(DeptType.YeWeiHui.toString())) {%>selected<%} %>><%=DeptType.getValue(DeptType.YeWeiHui)%></option>
				            <%} %>              
				            </select>
						</td>
						<td><button class="btn" type="button" btn_href="index.do">重新筛选</button> </td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
         	 <td>
	          	<div class="btn_toolbar">
					<button  type="button" class="btn" value="1" id="btnAdd" btn_href="addoper.do" force>新增</button>
					<button  type="button" class="btn" value="2" btn_href="getoper.do">查看</button>
					<button  type="button" class="btn" value="3" btn_href="editoper.do" >修改</button>
					<button type="button" class="btn" value="4" btn_href="getoper.do?flag=del" >删除</button>
					<button  type="button" class="btn" value="6" btn_href="resetPassword.do"  istip="1">重置密码</button>
					<button  type="button" class="btn" value="7" btn_href="upedateTe_state.do?flag=0" istip="1">启用</button>
					<button  type="button" class="btn" value="8" btn_href="upedateTe_state.do?flag=1" istip="1">禁用</button>
					<div class="btn_condition" >
						<div id='condition_null'>新增</div>
						<div id='<%=OperStatus.ZhengZaiShiYong.toString() %>'>查看</div>
						<div id='<%=OperStatus.XinJiaCaoZuoYuan.toString() %>'>查看,修改,删除,重置密码,启用</div>
						<div id='<%=OperStatus.ZanTingShiYong.toString() %>'>查看,修改,删除,重置密码,启用</div>
						<div id='<%=OperStatus.ZhengChangShiYong.toString() %>'>查看,修改,删除,重置密码,禁用</div>
					</div>
				</div>
          	</td>
        </tr>
		<tr>
			<td>
				<table class="grid1" width="960">
					<tr height="24" align="center">
		                <td width="5%" align="center" >序号</td>
		                <td width="10%" align="center">用户号</td>
		                <td width="20%" align="center" >操作员姓名</td>
						<td width="40%" align="center" >所属单位名称</td>
		                <td width="15%" align="center" >用户类别</td>
		                <td align="center" >状态</td>
					</tr>
					<%for(int i=0;i<rsOper.getRowCount();i++){%> 
					<tr>
						<td align="center" btn_condition='<%=rsOper.getString(i,"te_state") %>'><%=i+1%>
						<input name="te_operid_arr" type="hidden" value="<%=rsOper.getString(i,"te_operid") %>">
						</td>
						<td align="center" width="10%"><%=rsOper.getString(i,"te_operid")%></td>
						<td align="left"><%=rsOper.getString(i,"te_name")%></td>
						<td align="left"><%=rsOper.getString(i,"org_name")%></td>
						<td align="center"><%=DeptType.getValue(rsOper.getString(i,"te_dept_type"))%></td>
						<td align="center"><%=OperStatus.getValue(rsOper.getString(i,"te_state"))%></td>
					</tr>
					<%}%> 
				</table>
			</td>
		</tr>
		<tr>
			<td align="right">
				<table border="0" cellspacing="0" cellpadding="0" class="wrap-page mt5">
					<tr>
						<td><%=rsOper.getPage()!=null? rsOper.getPage().getPageHtml("form1","index.do"):""%></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>
</body>
</html>

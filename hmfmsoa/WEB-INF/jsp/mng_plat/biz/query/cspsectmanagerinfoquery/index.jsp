<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="fd.commons.jdbc.Result"%>
<%@page import="hmfms.util.DateUtil"%>
<%@ include file="/jsp/commons/taglibs.jsp" %>

<%
	Result rsCspMan = (Result)request.getAttribute("rsCspMan");
	//查询条件
	String csm_name = (String)request.getParameter("csm_name");
	if(csm_name == null) csm_name = "";
	String csm_job_code= (String)request.getParameter("csm_job_code");
	if(csm_job_code == null) csm_job_code = "";
%>

<html>
<%@ include file="/jsp/commons/meta.jsp" %>
<script type="text/javascript">
/* 本页面的初始处理。 本函数必须有，如果没有初始处理，则函数中不需要写任何代码。*/
$(document).ready(function(){	
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_condition 三个样式名称不能变之外其他都可以改变)
	var g=$('.grid1').datagrid({toolbar:bar, trselect:true});//或执行代码 g[0].grid.setBar(bar);	也可以
});
//选中行
function onSelectRow(index,val)
{
	$("#csm_id_detail").val(val);//“val”为“value”,“#”为当前页面
}
//重新筛选
function gotoQuery()
{
	//处理前后空格
	$("input[type=text]").each(function(){
		$(this).val($.trim($(this).val()));
	});
	$.webUtil.submit("index.do");
}

</script>
<body>
<form name="form1" method="post" action="">
<!-- 隐藏域存放小区经理ID -->
<input type="hidden" id="csm_id_detail" name="csm_id_detail" value="" /> 

<table border="0" cellpadding="0" cellspacing="0" width="960" style="margin:0 auto">
<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>小区经理信息查询</div></td></tr>
	<tr><td height="<%=_PAGEBLOCK_HEIGHT %>"></td></tr><!--间距 不可删除-->
	<!--查询条件区 start-->
	<tr>
	  <td width="100%" class="frameblue">
	    <div class="wrap-paramarea">
	      <div class="fl mr5 mt2">
	                           经理名称：<input type="text" id="csm_name" name="csm_name" size="15" maxlength="10" value = "<%=csm_name %>"/>&nbsp;
	                           上岗证号：<input type="text" id="csm_job_code" name="csm_job_code" size="15" maxlength="10" value = "<%=csm_job_code%>" />&nbsp;
	      </div>
	      <div><button class="btn" type="button" onClick="gotoQuery()"  mask>重新筛选</button></div>
	      <div class="clear"></div>
	    </div>
	  </td>
	</tr>
	<!--查询条件区 end-->
	<tr><td height="<%=_PAGEBLOCK_HEIGHT%>"></td></tr>
	<!--间距 不可删除--><!--功能按钮区 start-->
	<tr>
		<td width="100%">
			<div class="btn_toolbar" >
				<button class="btn" type="button" value="1" btn_href="getQueryCspManList.do" issubmit="1" istip="0">查看</button>
				<div class="btn_condition" >
					<div id='0'>查看</div>
				</div>
			</div>
		</td>
	</tr>
	<!--功能按钮区 end-->
	<tr><td height="<%=_PAGEBLOCK_HEIGHT %>"></td></tr><!--间距 不可删除-->
	<tr>
		<td width="100%"><!--内容区 start-->
			<table width="100%"  class="grid1">
				<tr>
					<td width="10%">序号</td>
					<td width="20%">经理名称</td>
					<td width="25%">上岗证号</td>
					<td width="25%">联系电话</td>
					<td width="">创建日期</td>
				</tr>
              	<!--JSP的for循环在下面开始写-->
              		<%
						for(int i=0; i<rsCspMan.getRowCount(); i++){
					%>
             	<tr onclick="onSelectRow(<%=i%>,'<%=rsCspMan.getString(i,"csm_id")%>')">
                	<td align="center" height="24" btn_condition='0'><%=(i+1) %></td>
					<td align="center"><%=rsCspMan.getString(i,"csm_name") %></td>
					<td align="center"><%=rsCspMan.getString(i,"csm_job_code") %></td>
					<td align="center"><%=rsCspMan.getString(i,"csm_tel") %></td>
					<td align="center"><%=DateUtil.formatFromDB(rsCspMan.getString(i,"csm_creat_date")) %></td>
             	 </tr>
              		<%} %><!--JSP的for循环结束--> 
			</table>
			<!--内容区 end-->
		</td>
	</tr>
	<tr>
		<td align = "right">
			<table >
				<tr>
					<td class = "wrap-page">
						<%=rsCspMan.getPage() != null ? rsCspMan.getPage().getPageHtml("form1","index.do"):"" %>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>

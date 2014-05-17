
<%@page import="hmfms.util.DateUtil"%>
<%@page import="fd.commons.jdbc.Result"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/jsp/commons/taglibs.jsp"%>
<%
	Result rs = (Result) request.getAttribute("rs");
   
%>

<%@page import="hmfms.services.codes.CspQualifyLevel"%><html>
<%@ include file="/jsp/commons/meta.jsp"%>
<script type="text/javascript">
/* 本页面的初始处理。 本函数必须有，如果没有初始处理，则函数中不需要写任何代码。*/
$(document).ready(function(){	
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_condition 三个样式名称不能变之外其他都可以改变)
	var g=$('.grid1').datagrid({toolbar:bar,trselect:false});//或执行代码 g[0].grid.setBar(bar);	也可以
});	
function onSelectRow(index,val){
		//$("#app_id_detail").val(val);
	}
	

function gotoView(csp_id)
{
	$.webUtil.submit("getHistoryList.do?csp_id="+csp_id);
}
function gotoView1(csp_name)
{
	$.webUtil.submit("<c:out value="${ctx}"/>/mng_plat/biz/query/sectinfoquery/index.do?csp_name="+csp_name);
}
</script>
<body>
<div class="parent-wrap">
<form id="form1" name="form1" method="post" action="">
<input id="sect_id" type="hidden" value="" />
<input type="hidden" name="csp_id"  value="<%=rs.getString(0, "csp_id")%>" />
   <div class="sub-bg">
		<img src="<c:out value="${ctx}"/>/images/home_title_leftarrow.gif"/>
		<strong>物业企业信息</strong>
	</div>
 	<div class="wrap-btnarea">
       <button type="button" class="btn"  btn_href="index.do" issubmit="0" istip="0" >返回</button>
	</div>
	<div class="wrap-content">
		<table  width="100%" class="grid1">
					<thead>
						<tr>
							<th colspan="4">物业企业详细信息</th>
						</tr>
					</thead>
					<tr>
						<td align="center" width="20%">物业名称</td>
						<td align="left" nowrap>&nbsp;<%=rs.getString(0,"csp_name") %></td>
					</tr>
					<tr>
						<td align="center">组织机构代码</td>
						<td align="left" nowrap>&nbsp;<%=rs.getString(0,"csp_org_code") %></td>
					</tr>
					<tr>
						<td align="center">地址</td>
						<td align="left" nowrap>&nbsp;<%=rs.getString(0,"csp_addr") %></td>
					</tr>
					<tr>
						<td align="center">注册所在地</td>
						<td align="left" nowrap>&nbsp;<%=rs.getString(0,"hp_name") %></td>
					</tr>
					<tr>
						<td align="center">物业企业从业资质</td>
						<td align="left" nowrap>&nbsp;<%=CspQualifyLevel.getValue(rs.getString(0, "csp_ent_qualification"))%></td>
					</tr>
					<tr>
						<td align="center">营业执照有效期</td>
						<td align="left" nowrap>&nbsp;<%=DateUtil.formatFromDB(rs.getString(0,"csp_biz_license_valid_date"))%></td>
					</tr>
					<tr>
						<td align="center">资质证书有效期</td>
						<td align="left" nowrap>&nbsp;<%=DateUtil.formatFromDB(rs.getString(0,"csp_qualification_valid_date"))%></td>
					</tr>
					<tr>
						<td align="center">相关链接</td>
						<td align="left">
					<a href="#" onclick="gotoView('<%=rs.getString(0,"csp_id")%>');return false;">&nbsp;物业信息变更历史</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="#" onclick="gotoView1('<%=rs.getString(0,"csp_name")%>');return false;">&nbsp;小区相关信息查询</a></td>
					</tr>
						
						</table>
						  </div>
</form>
</div>
</body>
</html>
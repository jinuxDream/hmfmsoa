<%@page import="hmfms.util.ActionUtil"%>
<%@page import="hmfms.web.User"%>
<%@page import="hmfms.util.StringUtil"%>
<%@page import="hmfms.util.ObjectUtil"%>
<%@page import="fd.exception.BusinessException"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*,fd.commons.jdbc.Result"%>
<%@ include file="/jsp/commons/taglibs.jsp"%>
<%
	User user =	ActionUtil.getUser(request);
	Result rs=(Result)request.getAttribute("rs");
	request.setAttribute("rs", rs);
	//	查询参数
	String st_name_frst = (String)request.getParameter("st_name_frst");
	if(st_name_frst == null) st_name_frst = "";
	String st_addr_frst= (String)request.getParameter("st_addr_frst");
	if(st_addr_frst == null) st_addr_frst = "";
%> 

<%@page import="hmfms.web.commons.SelectBoxHtml"%>
<%@page import="hmfms.services.codes.SectType"%>
<%@page import="hmfms.services.codes.SectKind"%>
<%@page import="hmfms.services.codes.DeptType"%>
<%@page import="hmfms.services.codes.InfoStatus"%><html>
	<%@ include file="/jsp/commons/meta.jsp"%>
	<script type="text/javascript">
		/* 本页面的初始处理。 本函数必须有，如果没有初始处理，则函数中不需要写任何代码。*/
		$(document).ready(function(){	
			$('.btn').PicButton();
			var bar=$('.btn_toolbar').BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_condition 三个样式名称不能变之外其他都可以改变)
			var g=$('.grid1').datagrid({toolbar:bar});//或执行代码 g[0].grid.setBar(bar);	也可以
			<% if(user.getDeptType().equals(DeptType.QuJu.toString())){ %>
			//当是区局时则会去加载区县下所有房办信息
			ajaxGetHpb_off();
			<% }else{ %>
			ajaxGetHpb_off('<%=request.getParameter("hpb_id") %>');
			<% } %>
		});	
		function onSelectRow(index,val){
			$("#sect_id_detail").val(val);
		}
		
		//查询
		function gotoQuery()
		{
			//处理前后空格
			$("input[type=text]").each(function(){
				$(this).val($.trim($(this).val()));
			});
			$.webUtil.submit("index.do");
		}
		//通过ajax获得房办
		function ajaxGetHpb_off(opt){
			if(opt==""){
				$("#ho_name").html("<option value=''>-全部-</option>");
			}else{
				$.ajax({ 
					url: '<c:out value="${ctx}"/>/cspplatinfo/commons/getHpb_offOptionSDO.do', 
					dataType: "text",
					data:{hpb_id:opt},
					success: onSaveok
				});
			}
		}
		function onSaveok(data){
			$("#ho_name").html("<option value=''>-全部-</option>"+data);
			var selOpt = "<%=ObjectUtil.isEmpty(request.getParameter("ho_name"))?"":request.getParameter("ho_name") %>";
			$("#ho_name option[value='"+selOpt+"']").attr("selected", true);
		}
		function onSaveError(){
			alert("网络出现问题！");
		}
		function gotopage(sect_id){

			$.webUtil.submit("getsectDetail.do?sect_id="+sect_id);
			return false;
		}
	</script> 
	<body>
		<form name="form1" method="post" action="">
			<input type="hidden" name="sect_id_detail" id="sect_id_detail" value="" /> 
			<table border="0" cellpadding="0" cellspacing="0" width="960" style="margin:0 auto">
			<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>小区信息查询</div></td></tr>
				<tr>
					<td height="<%=_PAGEBLOCK_HEIGHT%>"></td>
				</tr>
				<!--间距 不可删除--><!--查询条件区 start--> 
				<tr>
					<td width="100%" class="frameblue">
						<div class="wrap-paramarea">
							<div class="fl mr5 mt2">
							小区名称:<input type="text" id="st_name_frst" name="st_name_frst" size="15" maxlength="200" value="<%=ObjectUtil.isEmpty(request.getParameter("st_name_frst"))?"":request.getParameter("st_name_frst") %>" />&nbsp;
							小区地址:<input type="text" id="st_addr_frst" name="st_addr_frst" size="15" maxlength="200" value = "<%=ObjectUtil.isEmpty(request.getParameter("st_addr_frst"))?"":request.getParameter("st_addr_frst") %>" />&nbsp;
							门牌地址:<input type="text" id="unitaddr" name="unitaddr" size="20" maxlength="200" value = "<%=ObjectUtil.isEmpty(request.getParameter("unitaddr"))?"":request.getParameter("unitaddr") %>" />&nbsp;
							小区类型:<select name="sect_type" id="sect_type">
										<option value="all">-全部-</option>
										<%=SelectBoxHtml.genOptionsWithDefault(SectType.getCodeList(),ObjectUtil.isEmpty(request.getParameter("sect_type"))?SectType.ZhuZhai.toString():request.getParameter("sect_type")) %>
									</select>&nbsp;
							小区性质:<select name="st_kind" id="st_kind">
										<option value="all">-全部-</option>
										<%=SelectBoxHtml.genOptionsWithDefault(SectKind.getCodeList(),ObjectUtil.isEmpty(request.getParameter("st_kind"))?"":request.getParameter("st_kind") ) %>
									</select>
							<br/>
							<% if(!user.getDeptType().equals(DeptType.QuJu.toString())&&!user.getDeptType().equals(DeptType.FangGuanBan.toString())){ %>
								区县名称：<select name="hpb_id" id="hpb_id" onchange="ajaxGetHpb_off(this.value);return false;" style="width:80px;"><%=SelectBoxHtml.genOptionsHpbHtml(true,StringUtil.getString(request.getParameter("hpb_id"))) %></select>&nbsp;
							<%}
							if(!user.getDeptType().equals(DeptType.FangGuanBan.toString())){ %>
							房办名称:<select  id="ho_name" name="ho_name" style="width:100px;"><option value="">-全部-</option></select>
							<%}%>
							街道名称:<input type="text"  id="str_name" name="str_name" value="<%=ObjectUtil.isEmpty(request.getParameter("str_name"))?"":request.getParameter("str_name")%>">
							房屋状态:<select  id="sect_curr_stat" name = "sect_curr_stat" >
										<option value="all">-全部-</option>
										<% List<String> list=new ArrayList<String>();
											list.add(InfoStatus.ZhuXiao.toString());
											%>
										<%=SelectBoxHtml.genOptionsWithDefault(InfoStatus.getCodeList(),ObjectUtil.isEmpty(request.getParameter("sect_curr_stat"))?InfoStatus.ZhengChang.toString():request.getParameter("sect_curr_stat"),list) %>
									</select>&nbsp;
							<button class="btn" type="button" btn_href="index.do" mask>重新筛选</button> 
					</div>
					<div>
						<div class="clear"></div>
					</div>
						</div>
					</td>
				</tr>
					<tr>
					<td width="100%">
						<div class="btn_toolbar">
						<%if(!rs.isEmpty()){ %>
						<button class="btn" type="button" value="1" btn_href="querySect.do"   force>导出</button>
						<button class="btn" type="button" value="1" btn_href="getUnitList.do"   force>查看门牌</button>
						<%}%>
						<button class="btn" type="button" value="2" onclick="javascript:history.back();" force>返回</button>
					</div>
					</td>
				</tr>
				<tr>
					<td>
						<table border="1" cellpadding="0" cellspacing="0" width="100%" bordercolor="#000000" class="grid1">
							<tr align="center" height="40px">
								<td width="3%">序号</td>
								<td width="10%">小区编码</td>
								<td width="15%">小区名称</td>
								<td width="15%">小区地址</td>
								<td width="15%">物业公司名称</td>
								<td width="15%">小区管理处</td>
								<td width="10%">房办</td>
								<td>归属街道</td>
								<td width="6%">小区类型</td>
							</tr>
							<%for(int i=0;i<rs.getRowCount();i++){%>
								<tr onclick="onSelectRow(<%=i%>,'<%=rs.getString(i,"sect_id")%>')" >
									<td align="center"><%=i+1 %><input type="hidden" name="sect_id" value="<%=rs.getString(i, "sect_id")%>" /></td>
									<td align="center"><%=rs.getString(i,"st_code") %></td>
									<td align="left"><a href="#" onclick="gotopage('<%=rs.getString(i,"sect_id") %>');return false;"><%=rs.getString(i, "st_name_frst") %></a></td>
									<td align="left"><%=rs.getString(i, "st_addr_frst") %></td>
									<td align="left"><%=rs.getString(i, "csp_name") %></td>
									<td align="left"><%=rs.getString(i, "cs_name") %></td>
									<td align="left"><%=rs.getString(i, "ho_name") %></td>
									<td align="left"><%=rs.getString(i, "str_name") %></td>
									<td align="center"><%=SectType.getValue(rs.getString(i, "sect_type")) %></td>
								</tr>
							<%} %>
						</table>
					</td>
				</tr>
				<tr>
<td>
<table border="0" cellpadding="0" cellspacing="0" width="100%" align="center">
<div  style="float: right;" width="760" >
		<%=rs.getPage()!=null? rs.getPage().getPageHtml("form1","index.do"):""%></div>   
		</table>
		</td>
		</tr>
			
				
			</table>
		</form>
	</body>
</html>

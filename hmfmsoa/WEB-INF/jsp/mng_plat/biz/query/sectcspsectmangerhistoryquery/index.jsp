<%@page import="hmfms.util.DateUtil"%>
<%@page import="fd.commons.jdbc.Result"%>
<%@page import="hmfms.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/jsp/commons/taglibs.jsp"%>
<%
	Result rs = (Result) request.getAttribute("rs");
%>
<html>
<%@ include file="/jsp/commons/meta.jsp" %>
<script type="text/javascript">
$(document).ready(function(){	
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();//��ʼ����ť������(��һ��button��ɵ�div,������htmlʵ����.btn_toolbar .btn .btn_condition ������ʽ���Ʋ��ܱ�֮�����������Ըı�)
	var g=$('.grid1').datagrid({toolbar:bar,onSelect:onSelectRow});//��ִ�д��� g[0].grid.setBar(bar);	Ҳ����
});	

function onSelectRow(index,elm){
	var csp_id = $(elm).find("input[name=csp_id_arr]").val();
	var sect_id = $(elm).find("input[name=sect_id_arr]").val();
	$("#csp_id").val(csp_id);
	$("#sect_id").val(sect_id);
}

/*��ϸ��Ϣ*/
function gotohref(sect_id)
{
	$.webUtil.submit("getDetailList.do?sect_id="+sect_id);
}
</script>
<body>
<div class="parent-wrap">
<form id="form1" name="form1" method="post" action="">

<input type="hidden" name="csp_id" id="csp_id" value="" />
<input type="hidden" name="sect_id" id="sect_id" value="" />
<div class="sub-bg">
		<img src="<c:out value="${ctx}"/>/images/home_title_leftarrow.gif"/>
		<strong>С�����������ʷ��ѯ</strong>
	</div>
	<div class="wrap-paramarea">
		<div class="fl mr5 mt2">
		С�����ƣ� <input name="st_name_frst" type="text"  value="<%=StringUtil.getString(request.getParameter("st_name_frst"))%>" /> 
		��ҵ���ƣ�<input name="csp_name" type="text"  value="<%=StringUtil.getString(request.getParameter("csp_name"))%>" />
		С�������������ƣ� <input name="hp_name" type="text"  value="<%=StringUtil.getString(request.getParameter("hp_name"))%>" /></div>
		<button id="searchBtn" type="button" class="btn"  btn_href="index.do"  value="" issubmit="1" istip="0">��ѯ</button>
		<div class="clear"></div>
		</div>
		 				<div class="wrap-btnarea">
		                <div class="btn_toolbar" >
						<button type="button" class="btn"   btn_href="getDetailList.do" sisubmit="1" istip="0" >�鿴</button>
						<div class="btn_condition" >
				        <div id='0'>�鿴</div>
						</div>
						</div>
	</div>
        <div class="wrap-content">
			<table width="100%"  class="grid1">
			<tr>
					<td width="3%" >���</td>
					<td width="10%" >С������</td>
					<td width="15%" >С������</td>
					<td width="20%">С����ַ</td>
					<td width="19%" >��ҵ����</td>
					<td width="6%" >С������</td>
					<td width="8%" >��������</td>
					<td width="10%" >��ҵ�����ͬ<br>��ʼ����</td>
					<td width="" >��ҵ�����ͬ<br>��������</td>
				</tr>
				
			<%
				for (int i = 0; i < rs.getRowCount(); i++) {
			%>
			     
			 <tr>
			        <td align="center" height="50" btn_condition='0'><%=(i+1) %>
			        <input type="hidden" name="sect_id" id="sect_id" value="" />
					<input type="hidden" name="csp_id" value="<%=rs.getString(i, "csp_id")%>" />
					<input type="hidden" name="sect_id_arr" value="<%=rs.getString(i, "sect_id")%>" /></td>
				    <td   height="24" align="center"><a href="#" onclick="gotohref(<%=rs.getString(i, "sect_id")%>)">&nbsp;<%=rs.getString(i, "st_code")%></a></td>
				    <td   height="24" align="left">&nbsp;<%=rs.getString(i, "st_name_frst")%></td>
				    <td   height="24" align="left">&nbsp;<%=rs.getString(i, "st_addr_frst")%></td>
				    <td   height="24" align="left">&nbsp;<%=rs.getString(i, "csp_name")%></td>
				    <td   height="24" align="center"><%=rs.getString(i, "csm_name")%></td>
				    <td   height="20" align="center"><%=rs.getString(i, "hp_name")%></td>
				    <td   height="20" align="center"><%=DateUtil.formatFromDB(rs.getString(i, "st_csp_contract_start_date"))%></td>
				    <td   height="20" align="center"><%=DateUtil.formatFromDB(rs.getString(i, "st_csp_contract_end_date"))%></td>
					</tr>
					<%
				}
			%>
					</table>
        </div>
        	  <div style="float: right;"><%=rs.getPage()!=null? rs.getPage().getPageHtml("form1","index.do"):""%></div>
		  	
	<!-- �������� end -->
</form>
</div>
</body>
</html>
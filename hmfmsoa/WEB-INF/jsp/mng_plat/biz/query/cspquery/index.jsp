<%@page import="hmfms.util.DateUtil"%>
<%@page import="fd.commons.jdbc.Result"%>
<%@page import="hmfms.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/jsp/commons/taglibs.jsp"%>
<%
	Result rs = (Result) request.getAttribute("rs");
   
%>

<%@page import="hmfms.services.codes.CspQualifyLevel"%><html>
<%@ include file="/jsp/commons/meta.jsp" %>
<script type="text/javascript">
$(document).ready(function(){	
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();//��ʼ����ť������(��һ��button��ɵ�div,������htmlʵ����.btn_toolbar .btn .btn_condition ������ʽ���Ʋ��ܱ�֮�����������Ըı�)
	var g=$('.grid1').datagrid({toolbar:bar});//��ִ�д��� g[0].grid.setBar(bar);	Ҳ����
});	
function onSelectRow(index,val){
	//var csp_id = $(elm).find("input[name=csp_id_arr]").val();
	$("#csp_id_detail").val(val);
}

</script>
<body>
<div class="parent-wrap">
<form id="form1" name="form1" method="post" action="">
<input type="hidden" id="csp_id_detail" name="csp_id_detail" value="" /> 
<div class="sub-bg">
		<img src="<c:out value="${ctx}"/>/images/home_title_leftarrow.gif"/>
		<strong>��ҵ��Ϣ��ѯ</strong>
	</div>
	<div class="wrap-paramarea">
		<div class="fl mr5 mt2">
		��ҵ��˾���ƣ� <input name="csp_name" size="35%" type="text"  value="<%=StringUtil.getString(request.getParameter("csp_name"))%>" /> 
		��֯�������룺<input name="csp_org_code" size="35%" type="text"  value="<%=StringUtil.getString(request.getParameter("csp_org_code"))%>" /></div>
		<button id="searchBtn" type="button" class="btn"  btn_href="index.do"  value="" issubmit="1" istip="0">��ѯ</button>
		<div class="clear"></div>
		</div>
		<div class="wrap-btnarea">
          <div class="btn_toolbar" >
		     <button type="button" class="btn" btn_href="getDetailList.do" issubmit="1" istip="0">�鿴</button>
		     <div class="btn_condition" >
               <div id='0'>�鿴</div>
		     </div>
		  </div>
	    </div>
        <div class="wrap-content">
			<table width="100%"  class="grid1">
			<tr>
					<td width="5%" >���</td>
					<td width="20%" >��֯��������</td>
					<td width="30%" >��ҵ��˾����</td>
					<td width="20%" >���ʵȼ�</td>
					<td width="10%" >ע�����ڵ�</td>
					<td width="" >��������</td>
				</tr>
				
			<%
				for (int i = 0; i < rs.getRowCount(); i++) {
			%>
			     
			 <tr onclick="onSelectRow(<%=i%>,'<%=rs.getString(i,"csp_id")%>')">
			        <td align="center" height="50" btn_condition='<%=0%>'><%=(i+1) %>
					<input type="hidden" name="csp_id" value="<%=rs.getString(i, "csp_id")%>" />
					</td>
				    <td   height="24" align="center"><%=rs.getString(i, "csp_org_code")%></td>
				    <td   height="24" align="left">&nbsp;<%=rs.getString(i, "csp_name")%></td>
				    <td   height="24" align="center"><%=CspQualifyLevel.getValue(rs.getString(i, "csp_ent_qualification"))%></td>
				    <td   height="24" align="center"><%=rs.getString(i, "hp_name")%></td>
				    <td   height="20" align="center"><%=DateUtil.formatFromDB(rs.getString(i, "csp_creat_date"))%></td>
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
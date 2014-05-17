<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/jsp/commons/taglibs.jsp"%>
<%@page import="hmfms.base.ActionResultHmfms"%>
<%
Result rs = (Result)request.getAttribute( "redoList" );
%>
<%@page import="fd.commons.jdbc.Result"%>
<%@page import="hmfms.util.DateUtil"%><html>
<head>
<%@ include file="/jsp/commons/meta.jsp" %>
<script language="javascript">
$(document).ready(function(){
	$('.btn').PicButton();
	var g=$('.table1').datagrid({trselect:false});
});

</SCRIPT>
</head>
<body>
<form name="form1" method="post" action="" >
<table border="0" cellpadding="0" cellspacing="0" width="500" align="center">
<tr><td height="5"></td></tr>
<tr>
    <td width="100%">
		  <table  width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
			<tr>
    			<td width="100%">
					<table class="table1" align="center" width="100%">
					<tr>
					  <td width="10%"  align="center"><strong>序号</strong></td>
					  <td width="30%" align="center"><strong>退回时间</strong></td>
					  <td width="25%"  align="center"><strong>审核操作员</strong></td>
				      <td width="" align="center" ><strong>退回原因</strong></td>
			        </tr>
			        <% for(int i =0;(rs!=null) && i<rs.getRowCount();i++){ %>
					<tr>
					  <td  align="center"><%=(i+1)%></td>
					  <td  align="center"><%=DateUtil.formatFromDB(rs.getString(i,"oper_datetime").substring(0,8))%>&nbsp;<%=DateUtil.formatTimeFromDB(rs.getString(i,"oper_datetime").substring(8))%></td>
					  <td  align="center"><%=rs.getString(i,"te_name")%></td>
				      <td  align= "left" ><%=rs.getString(i,"remark")%></td>
			        </tr>
			        <%} %>
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

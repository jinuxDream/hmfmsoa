<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/jsp/commons/taglibs.jsp"%>
<%@page import="hmfms.base.ActionResultHmfms"%>
<%
String ws_id = request.getParameter("ws_id" );
String batch_no = request.getParameter("batch_no" );
String tran_status = request.getParameter("tran_status");
String back_home_url = request.getParameter(ActionResultHmfms.BACK_HOME_URL);
%>

<html>
<head>
<%@ include file="/jsp/commons/meta.jsp" %>
<script language="javascript">
$(document).ready(function(){
	$('.btn').PicButton();
	var g=$('.table1').datagrid({trselect:false});
});
function repealAudit()
{
	if (confirm("确定要退回审核吗?") )
  	{	
		 $.webUtil.submit('<c:out value="${ctx}"/>/mng_plat/biz/tradelog/setTradeGoBack.do'); 
  	}
}

</SCRIPT>
</head>
<body>
<form name="form1" method="post" action="" >
<input type="hidden" name="ws_id" value="<%=ws_id%>" />
<input type="hidden" name="batch_no" value="<%=batch_no%>" />
<input type="hidden" name="tran_status" value="<%=tran_status%>" />
<input type="hidden" name="<%=ActionResultHmfms.BACK_HOME_URL %>" value="<%=back_home_url%>" />
<table border="0" cellpadding="0" cellspacing="0" width="500" align="center">
<tr>
	<td height="21" align="left">
    	<button class="btn" type="button" value="" istip="0" onClick="repealAudit();return false;">确定</button>&nbsp;
	</td>
</tr>
<tr><td height="5"></td></tr>
<tr>
    <td width="100%">
		  <table  width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
			<tr>
			  <td>
				 <table class="table1" width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="#9BB3D3">
					<thead>
					<tr>
					  <td colspan="5"><div align="center" class="style1">退回信息</div></td>
					</tr>
					</thead>
					<tr>
					  <td width="18%"  align="center">退回原因</td>
					  <td width="82%"  align="left"><textarea name="remark" cols="60" rows="20"></textarea></td>
			        </tr>
				  </table>
			  </td>
		    </tr>
			<tr>
    			<td width="100%">
					<table  width="100%" border="0" cellspacing="0" cellpadding="0" align="center">
						<tr>
						  <td>&nbsp;</td>
						</tr>
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

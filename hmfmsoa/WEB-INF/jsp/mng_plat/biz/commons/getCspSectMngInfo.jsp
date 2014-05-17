<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="fd.commons.jdbc.Result"%>
<%@page import="hmfms.util.ObjectUtil"%>
<%@page import="hmfms.util.StringUtil"%>
<%@page import="hmfms.web.User"%>
<%@page import="hmfms.util.ActionUtil"%>
<%@ include file="/jsp/commons/taglibs.jsp" %>
<%
Result rsCspSectMng = (Result)request.getAttribute("rsCspinfo");
String batch_no = StringUtil.getString(request.getParameter("batch_no"));
String data_type = StringUtil.getString(rsCspSectMng.getString(0,"data_type"));
%>

<%@page import="hmfms.services.codes.StaffType"%>
<%@page import="fd.commons.jdbc.Page"%>
<%@page import="mng_plat.biz.baseinfo.choice.ChoiceManager"%>
<%@page import="hmfms.web.commons.PageCounter"%>
<%@page import="mng_plat.biz.commons.CommonsManager"%>
<%@page import="hmfms.services.codes.CertificateType"%>
<%@page import="hmfms.services.codes.SexFlag"%>
<%@page import="hmfms.services.codes.CertType"%>
<%@page import="hmfms.services.codes.PoliticsStatus"%>
<%@page import="hmfms.services.codes.DegreeLevel"%><html>
<head>
<%@ include file="/jsp/commons/meta.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	$('.btn').PicButton();
	$('.grid_cspsectMng').datagrid({defaultSel:false, trselect:false});//固定表头
});
</script>
</head>
<body>
<form name="form1" method="post" action="">
<table border="0" cellpadding="0" cellspacing="0" width="890" align="center">
	<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>小区经理详细信息</div></td></tr>
	<tr><td height="<%=_PAGEBLOCK_HEIGHT %>"></td></tr><!--间距 不可删除-->
	<tr>
		<td width="100%">
			<%if("true".equals(request.getParameter("del"))){ %>
				<button class="btn" istip="1" type="button" btn_href="<c:out value="${ctx}"/>/mng_plat/biz/baseinfo/cspmng_add/delCspMng.do?person_id=<%=rsCspSectMng.getString(0,"person_id") %>&org_id=<%=rsCspSectMng.getString(0,"org_id") %>&batch_no=<%=batch_no %>&data_type=<%=data_type %>">删除</button>
			<%} %>
			<button class="btn" type="button" onclick="javascript:history.back(); return false;" >返回</button>
		</td>
	</tr>
	<tr><td height="<%=_PAGEBLOCK_HEIGHT %>"></td></tr><!--间距 不可删除-->
	<tr>
		<td>
			<table class="grid_cspsectMng" width="100%">			
				<thead>
					<tr>
						<td width="10%" colspan="4">小区经理详细信息</td>
					</tr>
				</thead>
				<%
					Result rsstaff = (Result)rsCspSectMng.getObject(0,"rsCertificate");
					String sgzh = "";
					for(int j=0;j<rsstaff.getRowCount();j++){
						if(CertificateType.ShangGangZheng.toString().equals(rsstaff.getString(j,"certificate_type"))){
							sgzh = rsstaff.getString(j,"certificate_code");
						}
					}
				%>
				<tr>
					<td align="center" width="20%">经理名称:</td>
					<td colspan="3">&nbsp;&nbsp;<%=rsCspSectMng.getString(0,"STAFF_NAME") %></td>
				</tr>
				<tr>
					<td align="center" width="20%">证件类型:</td>
					<td width="30%">&nbsp;&nbsp;<%=CertType.getValue(rsCspSectMng.getString(0,"staff_cert_type")) %></td>
					<td align="center" width="10%">证件号码:</td>
					<td>&nbsp;&nbsp;<%=rsCspSectMng.getString(0,"staff_cert_code") %></td>
				</tr>
				<tr>
					<td align="center" width="20%">上岗证编号:</td>
					<td colspan="3">&nbsp;&nbsp;<%=sgzh %></td>
				</tr>
				<tr>
					<td align="center" width="20%">政治面貌:</td>
					<td>&nbsp;&nbsp;<%=PoliticsStatus.getValue(rsCspSectMng.getString(0,"politics_status")) %></td>
					<td align="center">性别:</td>
					<td>&nbsp;&nbsp;<%=SexFlag.getValue(rsCspSectMng.getString(0,"staff_sex")) %></td>
				</tr>
				<tr>
					<td align="center" width="20%">年龄:</td>
					<td>&nbsp;&nbsp;<%=rsCspSectMng.getString(0,"staff_age") %></td>
					<td align="center">最高学历:</td>
					<td>&nbsp;&nbsp;<%=DegreeLevel.getValue(rsCspSectMng.getString(0,"staff_diploma")) %></td>
				</tr>
				<tR>
					<td align="center" width="20%">手机:</td>
					<td>&nbsp;&nbsp;<%=rsCspSectMng.getString(0,"staff_phone") %></td>
					<td align="center">座机:</td>
					<td>&nbsp;&nbsp;<%=rsCspSectMng.getString(0,"landline") %></td>
				</tR>
				<tr>
					<td align="center" width="20%">邮政编码:</td>
					<td>&nbsp;&nbsp;<%=rsCspSectMng.getString(0,"staff_postcode") %></td>
					<td align="center">邮政地址:</td>
					<td>&nbsp;&nbsp;<%=rsCspSectMng.getString(0,"staff_postaddr") %></td>
				</tr>
				<tr>
					<td align="center" width="20%">备注:</td>
					<td colspan="3">&nbsp;&nbsp;<%=rsCspSectMng.getString(0,"remk") %></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>

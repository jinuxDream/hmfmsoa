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
	$('.grid_cspsectMng').datagrid({defaultSel:false, trselect:false});//�̶���ͷ
});
</script>
</head>
<body>
<form name="form1" method="post" action="">
<table border="0" cellpadding="0" cellspacing="0" width="890" align="center">
	<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>С��������ϸ��Ϣ</div></td></tr>
	<tr><td height="<%=_PAGEBLOCK_HEIGHT %>"></td></tr><!--��� ����ɾ��-->
	<tr>
		<td width="100%">
			<%if("true".equals(request.getParameter("del"))){ %>
				<button class="btn" istip="1" type="button" btn_href="<c:out value="${ctx}"/>/mng_plat/biz/baseinfo/cspmng_add/delCspMng.do?person_id=<%=rsCspSectMng.getString(0,"person_id") %>&org_id=<%=rsCspSectMng.getString(0,"org_id") %>&batch_no=<%=batch_no %>&data_type=<%=data_type %>">ɾ��</button>
			<%} %>
			<button class="btn" type="button" onclick="javascript:history.back(); return false;" >����</button>
		</td>
	</tr>
	<tr><td height="<%=_PAGEBLOCK_HEIGHT %>"></td></tr><!--��� ����ɾ��-->
	<tr>
		<td>
			<table class="grid_cspsectMng" width="100%">			
				<thead>
					<tr>
						<td width="10%" colspan="4">С��������ϸ��Ϣ</td>
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
					<td align="center" width="20%">��������:</td>
					<td colspan="3">&nbsp;&nbsp;<%=rsCspSectMng.getString(0,"STAFF_NAME") %></td>
				</tr>
				<tr>
					<td align="center" width="20%">֤������:</td>
					<td width="30%">&nbsp;&nbsp;<%=CertType.getValue(rsCspSectMng.getString(0,"staff_cert_type")) %></td>
					<td align="center" width="10%">֤������:</td>
					<td>&nbsp;&nbsp;<%=rsCspSectMng.getString(0,"staff_cert_code") %></td>
				</tr>
				<tr>
					<td align="center" width="20%">�ϸ�֤���:</td>
					<td colspan="3">&nbsp;&nbsp;<%=sgzh %></td>
				</tr>
				<tr>
					<td align="center" width="20%">������ò:</td>
					<td>&nbsp;&nbsp;<%=PoliticsStatus.getValue(rsCspSectMng.getString(0,"politics_status")) %></td>
					<td align="center">�Ա�:</td>
					<td>&nbsp;&nbsp;<%=SexFlag.getValue(rsCspSectMng.getString(0,"staff_sex")) %></td>
				</tr>
				<tr>
					<td align="center" width="20%">����:</td>
					<td>&nbsp;&nbsp;<%=rsCspSectMng.getString(0,"staff_age") %></td>
					<td align="center">���ѧ��:</td>
					<td>&nbsp;&nbsp;<%=DegreeLevel.getValue(rsCspSectMng.getString(0,"staff_diploma")) %></td>
				</tr>
				<tR>
					<td align="center" width="20%">�ֻ�:</td>
					<td>&nbsp;&nbsp;<%=rsCspSectMng.getString(0,"staff_phone") %></td>
					<td align="center">����:</td>
					<td>&nbsp;&nbsp;<%=rsCspSectMng.getString(0,"landline") %></td>
				</tR>
				<tr>
					<td align="center" width="20%">��������:</td>
					<td>&nbsp;&nbsp;<%=rsCspSectMng.getString(0,"staff_postcode") %></td>
					<td align="center">������ַ:</td>
					<td>&nbsp;&nbsp;<%=rsCspSectMng.getString(0,"staff_postaddr") %></td>
				</tr>
				<tr>
					<td align="center" width="20%">��ע:</td>
					<td colspan="3">&nbsp;&nbsp;<%=rsCspSectMng.getString(0,"remk") %></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>

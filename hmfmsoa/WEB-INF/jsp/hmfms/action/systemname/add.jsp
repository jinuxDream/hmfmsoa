<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*,fd.commons.jdbc.Result" %>
<%@ include file="/jsp/commons/taglibs.jsp"%>
<%@page import="hmfms.web.commons.SelectBoxHtml"%>
<%@page import="hmfms.services.codes.*"%>
<%@page import="hmfms.util.StringUtil"%>

<html>
	<head>
		<%@ include file="/jsp/commons/meta.jsp" %> 
		<link href="<c:out value="${ctx}"/>/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<script src="<c:out value="${ctx}"/>/ligerUI/js/ligerui.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			var tree=null;
			$(document).ready(function(){
				var bar=$('.btn_toolbar').BtnToolBar();
				$('.btn').PicButton();
				$('.grid1').datagrid({fixhead:false,toolbar:bar,trselect:false});
				$('.grid_fixhead').datagrid({hashead:false,trselect:false});
				$('#dt_stat_date').webDatepicker();
				$('#dt_end_date').webDatepicker();
				$('#dt_date').webDatepicker();
			});
		</script> 
	</head>
	<body>
		<form name="form1" method="post" action="">
			<input type="hidden" name="selected_check_quota_seq" id="selected_check_quota_seq" value="" />
			<table align="center" border="0" cellpadding="0" cellspacing="0" width="960" style="margin:0 auto">
			<tr><td align="left"><div class="headline"><div class="headarrow">&nbsp;</div>�û�ϵͳ������</div></td></tr>	
			<tr><td align="left">
				<button class="btn" type="button" value="1" btn_href="save.do">����</button>
				<button class="btn" type="button" value="2" btn_href="index.do" issubmit="0" istip="0">����</button>
				</td>
			</tr>
			<tr>
				<td align="left">
					<table class="grid1" width="100%">
						 <thead>
							<tr>
								<td align="center">�û�ϵͳ����</td>
							</tr>
						</thead>
						
						<tr>
							<td align="left" width="100%">
								<table class="grid_fixhead" width="100%" border="0">
									<tr>
										<td align="right" width="150">ϵͳ��ţ�<span><font color="red">(����)</font></span></td>
										<td align="left"><input name="sn_id" id="sn_id" type="text"   v_empty="1" size="20"/></td>
									</tr>
									<tr>
										<td align="right" width="150">ϵͳ���ƣ�<span><font color="red">(����)</font></span></td>
										<td align="left"><input name="sn_name" id="sn_name" type="text"   v_empty="1" size="20"/></td>
									</tr>
								</table>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>				
	</body>
</html>
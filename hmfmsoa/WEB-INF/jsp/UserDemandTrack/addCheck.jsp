<%@page import="hmfms.services.codes.*"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*,fd.commons.jdbc.Result" %>
<%@ include file="/jsp/commons/taglibs.jsp"%>
<%@page import="hmfms.web.commons.SelectBoxHtml"%>
<%@page import="hmfms.util.StringUtil"%>
<%
	String indexdata = (String)request.getAttribute("jsonArrStr");
%> 
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
			<tr><td align="left"><div class="headline"><div class="headarrow">&nbsp;</div>�û�������١�����</div></td></tr>	
			<tr><td align="left">
				<button class="btn" type="button" value="1" btn_href="addCheckOk.do">����</button>
				<button class="btn" type="button" value="2" btn_href="index.do" issubmit="0" istip="0">����</button>
				</td>
			</tr>
			
			
			<tr>
				<td align="left">
					<table class="grid1" width="100%">
						 <thead>
							<tr>
								<td align="center">�û����������Ϣ</td>
							</tr>
						</thead>
						<tr>
							<td width="" class="frameblue">
								
									<div class="fl mr5 mt2">ϵͳ���ƣ�
										<select name="dt_sys" id="dt_sys">
											<option value="">--ȫ��--</option>
											<%=SelectBoxHtml.genOptionsWithDefault(systemname.getCodeList(),request.getParameter("dt_sys")) %>
										</select>
									</div>
									<div class="fl mr5 mt2">���״̬��
										<select name="dt_state" id="dt_state" onchange="ajaxGetDemand_track(this.value);">
											<option value="">--ȫ��--</option>
											<%=SelectBoxHtml.genOptionsWithDefault(ChangeStat.getCodeList(),request.getParameter("dt_state")) %>
										</select>
									</div>
								
							</td>
						</tr>
						
						<tr>
							<td align="left" width="100%">
								<table class="grid_fixhead" width="100%" border="0">
									
									<tr>
										<td align="right" width="150">�����/�ˣ�<span><font color="red">(����)</font></span></td>
										<td align="left"><input name="dt_prop_name" id="dt_prop_name" type="text"   v_empty="1" size="20"/></td>
									</tr>
									<tr>
										<td align="right" width="150">�����������۷�/�ˣ�<span><font color="red">(����)</font></span></td>
										<td align="left"><input name="dt_invol_demand" id="dt_invol_demand" type="text"   v_empty="1" size="20"/></td>
									</tr>
									<tr>
										<td align="right" width="150">��ʼ���ڣ�<span><font color="red"></font></span></td>
										<td align="left"><input name="dt_stat_date" id="dt_stat_date" type="text"  v_type="date"  v_empty="1" size="20"/><span>���Ϊ��,��Ĭ��Ϊ����</span></td>
									</tr>
									<tr>
										<td align="right" width="150">�������ڣ�<span><font color="red"></font></span></td>
										<td align="left"><input name="dt_end_date" id="dt_end_date" type="text"  v_type="date"  v_empty="1" size="20"/><span>���Ϊ��,��Ĭ��Ϊ������</span></td>
									</tr>
									<tr>
										<td align="right" width="150">������ڣ�<span><font color="red"></font></span></td>
										<td align="left"><input name="dt_date" id="dt_date" type="text"  v_type="date"  v_empty="1" size="20"/><span>���Ϊ��,��Ĭ��Ϊ������</span></td>
									</tr>
									<tr>
										<td align="right" width="150">���������ܣ���<span><font color="red"></font></span></td>
										<td align="left"><input name="dt_work" id="dt_work" type="text"   v_empty="1" size="20"/></td>
									</tr>
									
									<tr>
										<td align="right" width="150">ʵʩ��<span><font color="red"></font></span></td>
										<td align="left"><input name="dt_implement" id="dt_implement" type="text"   v_empty="1" size="20"/></td>
									</tr>
									<tr>
										<td align="right" width="150">ʵʩ�ˣ�<span><font color="red"></font></span></td>
										<td align="left"><input name="dt_impl_pers" id="dt_impl_pers" type="text"   v_empty="1" size="20"/></td>
									</tr>
									<tr>
										<td align="right" width="150">����ͨ����<span><font color="red"></font></span></td>
										<td align="left"><input name="dt_test_pas" id="dt_test_pas" type="text"   v_empty="1" size="20"/></td>
									</tr>
									<tr>
										<td align="right" width="150">����������<span><font color="red"></font></span></td>
										<td align="left"><textarea rows="8" cols="157" name="dt_assess" id="dt_assess" type="text"></textarea></td>
									</tr>
									<tr>
										<td align="right" width="150">������ݣ�<span><font color="red">(����)</font></span></td>
										<td align="left">
											<textarea name="dt_content" id="dt_content" rows="8" cols="157" style="width:500;overflow-x:visible;overflow-y:visible;" style="resize:none"></textarea>
										</td>
									</tr>
									<tr>
										<td align="right" width="150">��ע��<span><font color="red"></font></span></td>
										<td align="left"><textarea rows="8" cols="157" name="dt_remark" id="dt_remark"></textarea></td>
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
<%@page import="mng_plat.service.baseinfo.BaseInfoQuery"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="java.util.*,fd.commons.jdbc.Result"%>
<%@page import="hmfms.util.ActionUtil"%>
<%@page import="hmfms.web.User"%>
<%@page import="hmfms.util.StringUtil"%>
<%@page import="hmfms.util.ObjectUtil"%>
<%@page import="fd.exception.BusinessException"%>
<%@ include file="/jsp/commons/taglibs.jsp" %>
<%
	User user =	ActionUtil.getUser(request);
	Map<String,Object> mapList = (Map<String,Object>)request.getAttribute("map");
	Result rsList = (Result)mapList.get("rsList");//��Ŀ��Ϣ
	Result rscsp=(Result)mapList.get("rscsp");//��ҵ��˾��Ϣ
	Result rscsp_sect=(Result)mapList.get("rscsp_sect");//С��������Ϣ
	Result rsjwh=(Result)mapList.get("rsjwh");//��ί��
	Result rsywh=(Result)mapList.get("rsywh");//ҵί��
	Result rsxmjl=(Result)mapList.get("rsxmjl");//С������
	
	String[] checkbox=rsList.getString(0, "sect_hoc_reason").split(",");
	int year=Integer.parseInt(DateUtil.getYear(DateUtil.getSysDate()))-1;
%> 
<%@page import="hmfms.web.commons.SelectBoxHtml"%>
<%@page import="hmfms.services.codes.*"%>
<%@page import="hmfms.util.DateUtil"%>
<%@page import="fd.commons.jdbc.ResultRow"%>
<%@page import="java.util.Map"%><html>
<%@ include file="/jsp/commons/meta.jsp" %>
<script type="text/javascript">
	/**��ҳ��ĳ�ʼ���� �����������У����û�г�ʼ���������в���Ҫд�κδ��롣*/
	$(document).ready(function(){	
		$('.btn').PicButton();
		var bar=$('.btn_toolbar').BtnToolBar();
		var g=$('.grid1').datagrid({toolbar:bar,trselect:false});
		
		//��ʾ��ѡ��
		<%for(int i=0;i<checkbox.length;i++){%>
		$("input[name='sect_hoc_reason']").each(function(){
			 if($(this).val()=="<%=checkbox[i]%>"){
				$(this).attr("checked",true)
			}    
		})
		<%}%>
		//��ʾ��ѡ��-end
	});	
</script> 
<body>
<form name="form1" id="form1" method="post" action="">
<input type="hidden" name="batch_no" value="<%=rsList.getString(0, "batch_no") %>">
<table align="center" border="0" cellpadding="0" cellspacing="0" width="960" style="margin:0 auto">
	<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>С����Ϣ��ѯ����ϸ��Ϣ</div></td></tr>
	<tr><td height="5"></td></tr><!--��� ����ɾ��-->
	<tr><td ><button class="btn" type="button"  onClick="javascript:history.back()">����</button></td></tr>
	<tr><td height="5"></td></tr><!--��� ����ɾ��-->
<tr>
		<td>
			<table class="grid1" width="100%">
				<thead>
				<tr>
					<th colspan="6">С��������Ϣ</th>
				</tr>
				</thead>
				<tr>
					<td align="right" width="18%">С������</td>
					<td align="left" colspan="2">&nbsp;&nbsp;<%=rsList.getString(0,"st_code") %></td>
					<td align="right" width="18%">�������� </td>
					<td align="left" colspan="2">&nbsp;&nbsp;<%=DateUtil.formatFromDB(rsList.getString(0,"sect_finish_date"))%></td>
				</tr>
				<tr>
					<td align="right" width="15%">С������</td>
					<td align="left" colspan="2" width="35%">&nbsp;&nbsp;<%=rsList.getString(0,"st_name_frst") %></td>
					<td align="right">С����ַ</td>
					<td align="left"colspan="2">&nbsp;&nbsp;<%=rsList.getString(0,"st_addr_frst") %></td>
				</tr>
				<tr>
					<td align="right" >����</td>
					<td align="left"  colspan="2" >&nbsp;&nbsp;<%=rsList.getString(0,"hp_name") %></td>
					<td align="right" >���ܰ�</td>
					<td align="left"  colspan="2" >&nbsp;&nbsp; <%=rsList.getString(0,"ho_name") %> </td>
				</tr>
				<tr>
					<td align="right" >����</td>
					<td  colspan="2">&nbsp;&nbsp;<%=rsList.getString(0,"lp_name") %></td>
					<td align="right">�ֵ�</td>
					<td  colspan="2" >&nbsp;&nbsp;<%=rsList.getString(0,"str_name") %></td>
				</tr>
				
				<tr>
					<td align="right">С��������</td>
					<td colspan="5">
						<%if(IsFlag.Shi.toString().equals(rsList.getString(0,"st_isopen"))){ %> ���ڷ��ʽ <%} %>
						<%if(IsFlag.Shi.toString().equals(rsList.getString(0,"st_isopen"))){ %>�������&nbsp;&nbsp;<%=rsList.getString(0,"st_gateway") %>�������л����������&nbsp;&nbsp;<%=rsList.getString(0,"st_vehicle_gateway") %>��<%} %>
						<%if(IsFlag.Fou.toString().equals(rsList.getString(0,"st_isopen"))){ %> �����ڷ��ʽ <%} %> 
					</td>
				</tr>
				<tr>
					<td align="right" rowspan="2">С��������Χ</td>
					<td colspan="2">����&nbsp;&nbsp;<%=rsList.getString(0,"st_csp_east") %></td>
					<td >�ϣ�&nbsp;&nbsp;<%=rsList.getString(0,"st_csp_south") %></td>
					<td rowspan="2" colspan="2">��������&nbsp;&nbsp;<%=rsList.getString(0,"st_notcsparea") %></td>
				</tr>
				<tr>
					<td colspan="2">����&nbsp;&nbsp;<%=rsList.getString(0,"st_csp_west") %></td>
					<td>����&nbsp;&nbsp;<%=rsList.getString(0,"st_csp_north") %></td>
				</tr>
				<tr>
					<td align="right">С������</td>
					<td align="left" colspan="2">&nbsp;&nbsp;<%=SectKind.getValue(rsList.getString(0,"st_kind")) %></td>
					<td align="right" >��ҵ��������</td>
				   <td colspan="2"><%=CspServiceType.getValue(rsList.getString(0,"st_csp_servcie_type")) %>
				   				<br>�Ƿ��й�:&nbsp;&nbsp;<%=IsFlag.getValue(rsList.getString(0,"st_is_managed")) %></td>
				</tr>
				<tr>
					<td align="right">�ܽ��������ƽ���ף�</td>
					<td align="left" colspan="2">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_cnst_area")) %></td>
					<td align="right">ռ�������ƽ���ף�</td>
					<td align="left" colspan="2">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_land_area")) %></td>
				</tr>
				<tr>
					<td align="right">С��״̬</td>
					<td colspan="2">&nbsp;&nbsp;<%=InfoStatus.getValue(rsList.getString(0,"sect_curr_stat")) %></td>
					<td align="right">С������</td>
					<td colspan="2" align="left">&nbsp;&nbsp;<%= SectType.getValue(rsList.getString(0,"sect_type")) %> </td>
				</tr>
				<tr>
					<td align="right" rowspan="5">���ݷ��������<br>��ƽ���ף�</td>
					<td align="right">��Ʒ��</td>
					<td align="left">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_condo_hous_area")) %></td>
					<td align="right">�ۺ�</td>
					<td align="left" colspan="2" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_postmarket_hous_area")) %></td>
				</tr>
				<tr>
					<td align="right">δ�۹���</td>
					<td align="left" colspan="4">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_public_hous_area")) %>
						�����в����׷��� &nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_unsold_notcount")) %>ƽ���ף�
					</td>
				</tr>
				<tr>
					<td align="right">�������</td>
					<td align="left" colspan="4">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_other_hous_area"))%></td>
				</tr>
				<tr>
					<td align="right">������</td>
					<td align="left">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_multilayer_area")) %></td>
					<td align="right">�߲����</td>
					<td align="left" colspan="2">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_high_area")) %></td>
				</tr>
				<tr>
					<td align="right">�������</td>
					<td align="left">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_villa_area")) %></td>
					<td align="right">��סլ���</td>
					<td align="left" colspan="2">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_non_residential_area")) %></td>
				</tr>
				<tr>
					<td align="right">С������</td>
					<td align="left">&nbsp;&nbsp;<%=SectHonor.getValue(rsList.getString(0,"st_sect_honor"))%>
					</td>
					<td colspan="4"><% if(rsList.getString(0,"st_sect_honor").equals(SectHonor.QiTa)){ %>���У�(&nbsp;&nbsp;<%=rsList.getString(0,"st_other_honor") %>)<%} %></td>
				</tr>
			</table>
	</td>
	</tr>
	<tr>
		<td>
			<table class="grid1" width="100%">
				<thead>
				<tr>
					<th colspan="6">��ʩ�豸��Ϣ</th>
				</tr>
				</thead>
				 <tr >
				    <td align="right" width="15%">����ˮ�ø���</td>
				    <td width="15%">&nbsp;&nbsp;<%=rsList.getString(0,"st_fire_pump_num") %></td>
				    <td width="15%" align="right">��ˮ�ظ���</td>
				    <td width="15%">&nbsp;&nbsp;<%=rsList.getString(0,"st_tot_reservoirs")%></td>
				    <td width="15%" align="right">ˮ�����</td>
				    <td width="15%">&nbsp;&nbsp;<%=rsList.getString(0,"st_tot_tanks") %></td>
				  </tr>
				  <tr >
				    <td align="right">���������λ��</td>
				    <td>&nbsp;&nbsp;<%=rsList.getString(0,"st_tot_ong_parks") %></td>
				    <td align="right">���»�����λ��</td>
				    <td>&nbsp;&nbsp;<%=rsList.getString(0,"st_tot_ung_parks") %></td>
				    <td align="right">С��ʵ�л�������</td>
				    <td>&nbsp;&nbsp;<%=rsList.getString(0,"st_tot_car") %></td>
				  </tr>
				  <tr >
				    <td align="right">�ǻ�����λ<br /> ����ͣ�����</td>
				    <td>&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_non_vtehicle_tot_ong_parks")) %></td>
				    <td align="right">�ǻ�����λ<br />  ����ͣ�����</td>
				    <td>&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_non_vtehicle_tot_ung_parks")) %></td>
				    <td align="right">�̻����</td>
				    <td>&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_afforested_area")) %></td>
				  </tr>
				  <tr >
				    <td align="right">����ˮ����</td>
				    <td>&nbsp;&nbsp;<%=rsList.getString(0,"st_tot_pumps") %></td>
				    <td align="right">����˨����</td>
				    <td>&nbsp;&nbsp;<%=rsList.getString(0,"st_tot_fire_hydrants") %></td>
				    <td align="right">���в���������˨��</td>
				    <td>&nbsp;&nbsp;<%=rsList.getString(0,"st_no_flre_pump_nmu") %></td>
				  </tr>
				  <tr >
				    <td align="right">�Ѱ�װ���̽ͷ����</td>
				    <td>&nbsp;&nbsp;<%=rsList.getString(0,"st_tot_monitors") %>��</td>
				    <td  colspan="2" >����̽ͷ��&nbsp;&nbsp;<%=rsList.getString(0,"st_ground_tot_monitors") %>��(�����޷�����ʹ��&nbsp;&nbsp;<%=rsList.getString(0,"st_no_ground_tot_monitors") %>)</td>
				    <td  colspan="2" >������̽ͷ��&nbsp;&nbsp;<%=rsList.getString(0,"st_tot_lift_monitors") %>��(���в�������&nbsp;&nbsp;<%=rsList.getString(0,"st_no_tot_lift_monitors") %>)</td>
				  </tr>
				  
				  <tr >
				 	<td align="right">����ͨ������</td>
				    <td colspan="2">&nbsp;&nbsp;<%=rsList.getString(0,"st_tot_fire_engine_access") %></td>
				    <td align="right">�����÷���ַ</td>
				    <td colspan="2">&nbsp;&nbsp;<%=rsList.getString(0,"st_csp_use_addr") %></td>
				  </tr>
				  <tr >
					<td align="right">�����÷�����</td>
					<td colspan="2">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_csp_area")) %>ƽ����</td>
				    <td align="right">ҵί���÷�</td>
				    <td colspan="2">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_committee_area")) %>ƽ����</td>
				  </tr>
				  
				  <tr >
				    <td align="right">С���ڻ������</td>
				    <td colspan="2">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_club_area")) %></td>
				    <td align="right">С�����Ƿ����ܽ籨��</td>
				    <td colspan="2">&nbsp;&nbsp;<%=IsFlag.getValue(rsList.getString(0,"st_perimeter_alarm")) %></td>
				  </tr>
				  <tr >
				    <td align="right" >�Ƿ�װ�������Χ��</td>
				    <td colspan="2" >&nbsp;&nbsp;<%=IsFlag.getValue(rsList.getString(0,"st_pulse_fence")) %></td>
	
				    <td align="right">С�����Ƿ��е��Ӽ����</td>
				    <td colspan="2" >&nbsp;&nbsp;<%=IsFlag.getValue(rsList.getString(0,"st_electronic_room")) %></td>
				 </tr>
				 <tr >
				    <td align="right"  >��������̨������ϸ��</td>
				    <td colspan="2" >&nbsp;&nbsp;<%=rsList.getString(0,"st_tot_lift") %></td>
				    <td align="right">�������а�ȫ������������̨��</td>
				    <td colspan="2" >&nbsp;&nbsp;<%=rsList.getString(0,"st_tot_trouble_lift") %></td>
				  </tr>
		</table>
	</td>
</tr>
<tr>
	<td>
		<table class="grid1" width="100%">
			<thead>				  
			  <tr >
				   <td colspan="6" >��ҵ����λ��Ϣ</td>
			  </tr>
			  </thead>
			  <tr>
			    <td align="right" width="15%">��ҵ������ҵ����</td>
			    <td width="35%" colspan="2" >&nbsp;&nbsp;<%=rscsp.getString(0,"csp_name") %></td>
			    <td align="right" width="15%">ע������</td>
			    <td width="35%"  colspan="2"  >&nbsp;&nbsp;<%=rscsp.getString(0,"hp_name") %></td>
			 </tr>
			 <tr>
			    <td align="right" >��ҵ�����ͬ��ʼʱ��</td>
			    <td colspan="2">&nbsp;&nbsp;<%=DateUtil.formatFromDB(rsList.getString(0,"st_csp_contract_start_date")) %></td>
			    <td align="right" >��ҵ�����ͬ����ʱ��</td>
			    <td colspan="2">&nbsp;&nbsp;<%=DateUtil.formatFromDB(rsList.getString(0,"st_csp_contract_end_date")) %></td>
			 </tr>
			 <tr >
			    <td align="right" >��֯��������</td>
			    <td colspan="2">&nbsp;&nbsp;<%=rscsp.getString(0,"csp_org_code") %></td>
			    <td align="right">��֯����������Ч��</td>
			    <td colspan="2">&nbsp;&nbsp;<%=DateUtil.formatFromDB(rscsp.getString(0,"csp_code_valid_date")) %></td>
			  </tr>
			  <tr>
			    <td align="right" >��ҵ��ҵע���ַ</td>
			    <td colspan="2">&nbsp;&nbsp;<%=rscsp.getString(0,"csp_register_address") %></td>
			    <td align="right" >��ҵ��ҵ�칫��ַ</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rscsp.getString(0,"csp_addr") %></td>
			  </tr>
			  <tr >
			    <td align="right">��ҵ���˴���</td>
			    <td colspan="2">&nbsp;&nbsp;<%=rscsp.getString(0,"csp_legal_rep_name") %></td>
			    <td align="right">��ҵ�����ֻ�����</td>
			    <td colspan="2">&nbsp;&nbsp;<%=rscsp.getString(0,"csp_legal_rep_phone") %></td>
			  </tr>
			  <tr >
			    <td align="right">��ҵ��ϵ��</td>
			    <td colspan="2">&nbsp;&nbsp;<%=rscsp.getString(0,"csp_contacter") %></td>
			    <td  align="right">��ҵ��ϵ�绰</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rscsp.getString(0,"csp_tel") %></td>
			  </tr>
			  <tr >
			    <td align="right" >��ҵ����</td>
			    <td colspan="2">&nbsp;&nbsp;<%=rscsp.getString(0,"csp_fax") %></td>
			    <td align="right" >��ҵ��ҵ���ʵȼ�</td>
			    <td colspan="2">&nbsp;&nbsp;<%=CspQualifyLevel.getValue(rscsp.getString(0,"csp_ent_qualification")) %></td>
			  </tr>
			  <tr >
			    <td align="right" >����֤����</td>
			    <td colspan="2">&nbsp;&nbsp;<%=rscsp.getString(0,"csp_qualification") %></td>
			    <td align="right">����֤���֤����</td>
			    <td colspan="2">&nbsp;&nbsp;<%=DateUtil.formatFromDB(rscsp.getString(0,"csp_qualification_valid_date")) %></td>
			  </tr>
			  <tr >
			    <td align="right" >Ӫҵִ�պ�</td>
			    <td colspan="2">&nbsp;&nbsp;<%=rscsp.getString(0,"csp_biz_license_code") %></td>
			    <td align="right" >Ӫҵִ����Ч��</td>
			    <td colspan="2">&nbsp;&nbsp;<%=DateUtil.formatFromDB(rscsp.getString(0,"csp_biz_license_valid_date")) %></td>
			  </tr>
	   </table>
	 </td>
 </tr>
<tr>
	<td>
		<table class="grid1" width="100%">
			<thead>		
			  <tr >
			    <td colspan="6" >С��������Ϣ</td>
			  </tr>
			  </thead>
			  <tr>
			    <td align="right" width="15%">С����������</td>
			    <td width="35%" colspan="2" >&nbsp;&nbsp;<%=rscsp_sect.getString(0,"cs_name") %></td>
			    <td align="right" width="15%">С�������칫��ַ</td>
			    <td width="35%" colspan="2" >&nbsp;&nbsp;<%=rscsp_sect.getString(0,"cs_addr") %></td>
			  </tr>
			  <tr >
			    <td align="right">С��������ϵ��</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rscsp_sect.getString(0,"cs_contacter") %></td>
			    <td align="right" >��ϵ����ϵ�绰</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rscsp_sect.getString(0,"cs_tel") %></td>
			 </tr>
			 <tr>
			    <td align="right" >С���ռ䱨�޵绰</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rscsp_sect.getString(0,"cs_day_tel") %></td>
			    <td align="right">С��ҹ�䱨�޵绰</td>
			    <td colspan="2"  >&nbsp;&nbsp;<%=rscsp_sect.getString(0,"cs_night_tel") %></td>
			 </tr>
			 <tr>
			    <td align="right" >��ҵͶ�ߵ绰</td>
			    <td colspan="2">&nbsp;&nbsp;<%=rscsp_sect.getString(0,"cs_complaint_tel") %></td>
			    <td align="right" >С����������</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rscsp_sect.getString(0,"cs_fax") %></td>
			  </tr>
			  <tr >
			    <td align="right" >С�������ʱ�</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rsxmjl.getString(0,"cs_post_code")%></td>
			    <td align="right" >С����������</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rsxmjl.getString(0,"csm_name")%></td>
			 </tr>
			  <tr >
			    <td align="right" >С������̶��绰��</td>
			    <td colspan="2">&nbsp;&nbsp;<%=rsxmjl.getString(0,"csm_tel")%></td>
			    <td align="right" >С�������ֻ���</td>
			    <td colspan="2">&nbsp;&nbsp;<%=rsxmjl.getString(0,"csm_phone")%></td>
			  </tr>
			  <tr >
			    <td align="right" >С���������֤����</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rsxmjl.getString(0,"csm_cert_code")%></td>
			    <td align="right" >С�������ϸ�֤��</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rsxmjl.getString(0,"csm_job_code")%></td>
			  </tr>
			  <tr >
			    <td align="right" >С�������ʸ�֤������</td>
			    <td colspan="2"  >&nbsp;&nbsp;<%=rsxmjl.getString(0,"csm_job_Qualification")%></td>
			    <td align="right" >֤����</td>
			    <td colspan="2"  >&nbsp;&nbsp;<%=rsxmjl.getString(0,"csm_job_log")%></td>
			  </tr>
		</table>
	</td>
 </tr>
<tr>
	<td>
		<table class="grid1" width="100%">
			<thead>					  
			  <tr >
			    <td colspan="10" >ҵ������ҵ��ίԱ����Ϣ���ǰ����ҵί�ӻṵͨ��</td>
			  </tr>
			  </thead>
			  <tr>
			    <td align="right" width="15%" >δ����ҵ�����ԭ�� <p>���Ѿ������������</p></td>
			    <td colspan="9"><input type="checkbox" disabled name="sect_hoc_reason" value="<%=NotSetUpHoc.Wsbjfsydjzmjwdd50ys.toString() %>">���۲�����ʹ�õĽ������δ�ﵽ50%���ϣ�
							    <input type="checkbox" disabled name="sect_hoc_reason" value="<%=NotSetUpHoc.Stfwcsbjfsywm2n.toString() %>">���׷��ݳ��۲�����ʹ��δ��2�� &nbsp;
							    <input type="checkbox" disabled name="sect_hoc_reason" value="<%=NotSetUpHoc.Yzjsjdbcl.toString() %>">ҵ�����پ�����������&nbsp;
							    <input type="checkbox" disabled name="sect_hoc_reason" value="<%=NotSetUpHoc.Jwhswcl.toString() %>">��ί����δ����&nbsp;&nbsp;&nbsp;���ɶ�ѡ��</td>
			  </tr>
			  <tr >
			     <td align="right"  width="15%">ҵ���������</td>
			    <td colspan="4" width="35%" >&nbsp;&nbsp;<%=rsywh.getString(0,"hoc_name") %></td>
			    <td align="right"  width="15%">ҵ�����칫��ַ</td>
			    <td colspan="4" width="35%" >&nbsp;&nbsp;<%=rsywh.getString(0,"hoc_addr") %></td>
			  </tr>
			  <tr >
			    <td  align="right">ҵί����ϵ�绰</td>
			    <td colspan="4">&nbsp;&nbsp;<%=rsywh.getString(0,"hoc_tel") %></td>
			    <td  align="right">ҵί����</td>
			    <td colspan="4" >&nbsp;&nbsp;<%=rsywh.getString(0,"hoc_term") %></td>
			 </tr>
			  <tr >
			    <td  align="right">ҵί��������ʼʱ��</td>
			    <td colspan="4" >&nbsp;&nbsp;<%=DateUtil.formatFromDB(rsywh.getString(0,"hoc_con_start_date")) %></td>
			    <td  align="right" >ҵί�����ڽ���ʱ��</td>
			    <td colspan="4">&nbsp;&nbsp;<%=DateUtil.formatFromDB(rsywh.getString(0,"hoc_con_end_data"))%></td>
			 </tr>
			  <tr >
			    <td  align="right">ѡ��ʱ��</td>
			    <td colspan="4">&nbsp;&nbsp;<%=DateUtil.formatFromDB(rsywh.getString(0,"hoc_election_date")) %></td>
			    <td align="right" width="15%">ҵ��������</td>
			    <td colspan="4"  width="35%">&nbsp;&nbsp;<%=rsywh.getString(0,"hoc_code") %></td>
			 </tr>
			  <tr >
				  <td align="right">ְ��</td>
				  <td   colspan="2"> <%=HocDutyType.getValue(rsywh.getString(0,"dir_duty")) %>  </td>
				  <td align="right">����</td>
			      <td colspan="2">&nbsp;&nbsp;<%=rsywh.getString(0,"dir_name") %></td>
			      <td align="right" >�Ա�</td>
			      <td colspan="3">&nbsp;&nbsp;<%=SexFlag.getValue(rsywh.getString(0,"dir_sex")) %></td>
			 </tr>
			 <tr>
			    <td align="right" width="15%">����</td>
			    <td colspan="2"  width="20%">&nbsp;&nbsp;<%=rsywh.getString(0,"dir_age") %></td>
			    <td align="right" width="15%">������ò</td>
			    <td colspan="2" width="25%">&nbsp;&nbsp;<%=PoliticsStatus.getValue(rsywh.getString(0,"dir_politics_status")) %></td>
			    <td align="right" width="10%">��ϵ�绰</td>
			    <td colspan="3" width="15%">&nbsp;&nbsp;<%=rsywh.getString(0,"dir_tel") %></td>
			 </tr>
			 <tr >
			    <td align="right">֤������</td>
			    <td colspan="5">&nbsp;&nbsp;<%=CertType.getValue(rsywh.getString(0,"dir_cert_type")) %></td>
			    <td  align="right">֤�����</td>
			    <td colspan="3">&nbsp;&nbsp;<%=rsywh.getString(0,"dir_cert_code") %></td>
			 </tr>
			 <tr >
				  <td align="right">ְ��</td>
				  <td  colspan="2"> <%=HocDutyType.getValue(rsywh.getString(1,"dir_duty")) %>  </td>
				  <td align="right">����</td>
			      <td colspan="2">&nbsp;&nbsp;<%=rsywh.getString(1,"dir_name") %></td>
			      <td align="right" >�Ա�</td>
			      <td colspan="3">&nbsp;&nbsp;<%=SexFlag.getValue(rsywh.getString(1,"dir_sex")) %></td>
			 </tr>
			 <tr >
			    <td align="right" >����</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rsywh.getString(1,"dir_age") %></td>
			    <td align="right" >������ò</td>
			    <td colspan="2">&nbsp;&nbsp;<%=PoliticsStatus.getValue(rsywh.getString(1,"dir_politics_status")) %></td>
			    <td align="right" >��ϵ�绰</td>
			    <td colspan="3">&nbsp;&nbsp;<%=rsywh.getString(1,"dir_tel") %></td>
			 </tr>
			 <tr >
			    <td align="right">֤������</td>
			    <td colspan="5">&nbsp;&nbsp;<%=CertType.getValue(rsywh.getString(1,"dir_cert_type")) %></td>
			    <td  align="right">֤�����</td>
			    <td colspan="3">&nbsp;&nbsp;<%=rsywh.getString(1,"dir_cert_code") %></td>
			 </tr>
			 <tr >
				  <td align="right">ְ��</td>
				  <td   colspan="2"> <%=HocDutyType.getValue(rsywh.getString(2,"dir_duty")) %>  </td>
				  <td align="right">����</td>
			      <td colspan="2">&nbsp;&nbsp;<%=rsywh.getString(2,"dir_name") %></td>
			      <td align="right" >�Ա�</td>
			      <td colspan="3">&nbsp;&nbsp;<%=SexFlag.getValue(rsywh.getString(2,"dir_sex")) %></td>
			 </tr>
			 <tr >
			    <td align="right" >����</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rsywh.getString(0,"dir_age") %></td>
			    <td align="right" >������ò</td>
			    <td colspan="2">&nbsp;&nbsp;<%=PoliticsStatus.getValue(rsywh.getString(2,"dir_politics_status")) %></td>
			    <td align="right" >��ϵ�绰</td>
			    <td colspan="3">&nbsp;&nbsp;<%=rsywh.getString(2,"dir_tel") %></td>
			 </tr>
			 <tr >
			    <td align="right">֤������</td>
			    <td colspan="5">&nbsp;&nbsp;<%=CertType.getValue(rsywh.getString(2,"dir_cert_type")) %></td>
			    <td  align="right">֤�����</td>
			    <td colspan="3">&nbsp;&nbsp;<%=rsywh.getString(2,"dir_cert_code") %></td>
			 </tr>
			 <tr >
			    <td align="right">����������</td>
			    <td align="left" colspan="9" >&nbsp;&nbsp;<%=OnwersTypeOper.getValue(rsywh.getString(0,"hoc_onwers_typeoper")) %></td>
			  </tr>
			  <tr>
			    <td align="right">����������</td>
			    <td align="left" colspan="9" >&nbsp;&nbsp;<%=OwnersTransition.getValue(rsywh.getString(0,"hoc_owners_transition")) %> </td>
			  </tr>
	 </table>
	</td>
</tr>
<tr>
	<td>
		<table class="grid1" width="100%">
			<thead>		
			  <tr >
			    <td colspan="6" >����ίԱ����Ϣ</td>
			  </tr>
			  </thead>
			  <tr >
			    <td align="right" width="15%">��������ίԱ������</td>
			    <td colspan="2" width="35%" >&nbsp;&nbsp;<%=rsjwh.getString(0,"cmt_name")%></td>
			    <td align="right" width="15%" >��ί��칫��ַ</td>
			    <td colspan="2" width="35%" >&nbsp;&nbsp;<%=rsjwh.getString(0,"cmt_addr") %></td>
			  </tr>
			  <tr >
			    <td align="right"  >��ί���</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rsjwh.getString(0,"cmt_secretary_name") %></td>
			    <td align="right" >��ϵ�绰</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rsjwh.getString(0,"cmt_secretary_tel")%></td>
			  </tr>
			  <tr >
			    <td align="right" >��ί����</td>
			    <td colspan="2">&nbsp;&nbsp;<%=rsjwh.getString(0,"cmt_director_name") %></td>
			    <td align="right"  >��ϵ�绰</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rsjwh.getString(0,"cmt_director_tel") %></td>
			  </tr>
		 </table>
	</td>
</tr>
<tr>
	<td>
		<table class="grid1" width="100%">
			<thead>		
		    <tr>
		      <td  colspan="10" >��Ӫ������Ϣ</td>
		    </tr>
		    </thead>
		    <tr>
		      <td  colspan="2" align="right" width="21%">������Ա��</td>
		      <td  colspan="3" align="left">&nbsp;&nbsp;<%=rsList.getString(0,"st_manager_num") %></td>
		      <td  colspan="2"  align="right" width="21%">ά����Ա��</td>
		      <td  colspan="3" align="left" >&nbsp;&nbsp;<%=rsList.getString(0,"st_repairs_num") %></td>
		    </tr>
		    <tr >
		      <td colspan="2" align="right" >������Ա��</td>
		      <td colspan="3" align="left">&nbsp;&nbsp;<%=rsList.getString(0,"st_clean_num") %></td>
		      <td colspan="2" width="8%" align="right" >������Ա��</td>
		      <td colspan="3" align="left" >&nbsp;&nbsp;<%=rsList.getString(0,"st_security_num") %></td>
		    </tr>
		    <tr >
		      <td colspan="2" align="right" >������Ա��</td>
		      <td colspan="3" align="left" >&nbsp;&nbsp;<%=rsList.getString(0,"st_greening_num") %></td>
		      <td  colspan="2"align="right" >��ҵ������շ��ƶ�</td>
		      <td  colspan="3" align="left" >&nbsp;&nbsp;<%=ChargeMethods.getValue(rsList.getString(0,"st_charge_methods")) %> </td>
		    </tr>
		    <tr >
		      <td rowspan="4" align="right" width="11%">�շѱ�׼&nbsp;Ԫ/ƽ����<p>[�����豸���з�]</p> </td>
		      <td width="10%" align="right" >��㣨��Ʒ����</td>
		      <td  width="17%" colspan="2" align="left" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_multilayer_standard")) %></td>
		      <td colspan="2" rowspan="4" width="17%" align="right" >ȫ��Ӧ����ҵ���ܶԪ��<p>��������ʩ�豸���зѣ�</p></td>
		      <td colspan="2" rowspan="4" width="15%" align="left" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_year_charge_total")) %></td>
		      <td rowspan="4" align="right"  ><%=year%>����ҵ��ʵ�գ�Ԫ��</td>
		      <td rowspan="4" align="left" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_yearwyfss_2012")) %></td>
		    </tr>
		    <tr >
		      <td align="right"  >�߲㣨��Ʒ����</td>
		      <td width="89"  colspan="2" align="left"  >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_top_standard")) %></td>
		    </tr>
		    <tr >
		      <td align="right"  >��������Ʒ����</td>
		      <td colspan="2"  width="89"  align="left" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_villa_standard")) %></td>
		    </tr>
		    <tr >
		      <td align="right"   >�ۺ�(Ԫ/����)</td>
		      <td width="89" colspan="2" align="left"  >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_after_standard")) %></td>
		    </tr>
			<tr >
			      <td colspan="2" align="right"><%=year%>������ܳɱ���Ԫ��</td>
			      <td colspan="2" align="left" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_yearfwzcb_2012")) %></td>
			      <td colspan="2"  align="right" >��λ�������ɱ�(Ԫ/ƽ����)<br>(�ܳɱ�/���շ����)</td>
			      <td colspan="4" align="left"  >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_unit_service_cost")) %></td>
			 </tr>
			 <tr >
			      <td colspan="2" align="right"   ><%=year%>����ҵ��֧��Ĺ������棨Ԫ��</td>
			      <td colspan="8" align="left"  >��&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_wykzpdggsyze"))%>Ԫ������ͣ������ֳ�&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_wykzpdggsyqztcftc")) %>Ԫ��
			         ��������&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_wykzpdggsy")) %>Ԫ��</td>
			 </tr>

		    <tr>
		      <td colspan="2" align="right" >������С����겹�������ƽ���ף�</td>
		      <td  colspan="2" align="left" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_xslxqdbbtmj"))%></td>
		      <td colspan="4" rowspan="2" align="center" >ȫ�����ܲ�����Ԫ��<p>(��ÿ��ÿƽ����0.27Ԫ����)</p> </td>
		      <td colspan="2" rowspan="2"  align="left" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_qnxsbtje")) %></td>
		    </tr>
		    <tr >
		      <td colspan="2" align="right" >�������ڶ�Ǩ�����������ƽ���ף�</td>
		      <td align="left" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_xszqdqfbtmj")) %></td>
		    </tr>
		    <tr >
		      <td colspan="2" align="right"  ><%=year%>�걾С��ӯ����+/-Ԫ��</td>
		      <td colspan="8" align="left" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_yearxqyk_2012")) %></td>
		    </tr>
		  </table>
	 </td>
 </tr>
<tr>
	<td>
		<table class="grid1" width="100%">
			<thead>		
		    <tr>
		      <td colspan="7">����ά���ʽ���Ϣ</td>
		    </tr>
		    </thead>
		    <tr >
		      <td align="right" rowspan="2" >��Ʒ��</td>
		      <td align="right" >���ڹ鼯���</td>
		      <td align="left" width="10%">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_spfsqgjje")) %></td>
		      <td align="right" >��ʹ���ʽ��</td>
		      <td align="left"  width="10%" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_spfysyzje")) %></td>
		      <td align="right"  >�ʽ������</td>
		      <td align="left"  width="10%" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_spfzjzye")) %></td>
		    </tr>
		    <tr >
		      <td align="right" >�Ƿ�������������</td>
		      <td align="left" ><%=IsFlag.getValue(rsList.getString(0,"st_spfsfmzxctj")) %> </td>
		      <td align="right" >��������</td>
		      <td align="left">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_spfyxcje"))%></td>
		      <td align="left"></td>
		      <td align="left"></td>
		    </tr>
		    <tr >
		      <td rowspan="2" align="right">�ۺ� <p>����</p> <p>����</p> </td>
		      <td align="right" >��ʼ����ά�޻���</td>
		      <td align="left" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_shfcsfwwxjj")) %></td>
		      <td align="right" >��ʼ�ַ�����</td>
		      <td align="left" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_shfcsjfjj")) %></td>
		      <td align="right" >��ʼ����ˮ�û���</td>
		      <td align="left" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_shfcsdtsbjj")) %></td>
		    </tr>
		    <tr >
		      <td align="right" >����ά�޻������</td>
		      <td align="left">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_shffwwxjjye")) %></td>
		      <td align="right" >�ַ��������</td>
		      <td align="left" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_shfjfjjye")) %></td>
		      <td align="right" >����ˮ�û������</td>
		      <td align="left">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_shfdtsbjjye")) %></td>
		    </tr>
		    <tr >
		      <td align="right" >��ע</td>
		      <td  colspan="6" align="left" >&nbsp;&nbsp;<%=rsList.getString(0,"st_remk") %></td>
		    </tr>
		  </table>
	 </td>
</tr>
 </table>
</form>
</body>
</html>

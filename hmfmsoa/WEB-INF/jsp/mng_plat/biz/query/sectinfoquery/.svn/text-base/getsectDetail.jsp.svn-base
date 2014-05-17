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
	Result rsList = (Result)mapList.get("rsList");//项目信息
	Result rscsp=(Result)mapList.get("rscsp");//物业公司信息
	Result rscsp_sect=(Result)mapList.get("rscsp_sect");//小区管理处信息
	Result rsjwh=(Result)mapList.get("rsjwh");//居委会
	Result rsywh=(Result)mapList.get("rsywh");//业委会
	Result rsxmjl=(Result)mapList.get("rsxmjl");//小区经理
	
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
	/**本页面的初始处理。 本函数必须有，如果没有初始处理，则函数中不需要写任何代码。*/
	$(document).ready(function(){	
		$('.btn').PicButton();
		var bar=$('.btn_toolbar').BtnToolBar();
		var g=$('.grid1').datagrid({toolbar:bar,trselect:false});
		
		//显示多选框
		<%for(int i=0;i<checkbox.length;i++){%>
		$("input[name='sect_hoc_reason']").each(function(){
			 if($(this).val()=="<%=checkbox[i]%>"){
				$(this).attr("checked",true)
			}    
		})
		<%}%>
		//显示多选框-end
	});	
</script> 
<body>
<form name="form1" id="form1" method="post" action="">
<input type="hidden" name="batch_no" value="<%=rsList.getString(0, "batch_no") %>">
<table align="center" border="0" cellpadding="0" cellspacing="0" width="960" style="margin:0 auto">
	<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>小区信息查询→详细信息</div></td></tr>
	<tr><td height="5"></td></tr><!--间距 不可删除-->
	<tr><td ><button class="btn" type="button"  onClick="javascript:history.back()">返回</button></td></tr>
	<tr><td height="5"></td></tr><!--间距 不可删除-->
<tr>
		<td>
			<table class="grid1" width="100%">
				<thead>
				<tr>
					<th colspan="6">小区基本信息</th>
				</tr>
				</thead>
				<tr>
					<td align="right" width="18%">小区代码</td>
					<td align="left" colspan="2">&nbsp;&nbsp;<%=rsList.getString(0,"st_code") %></td>
					<td align="right" width="18%">竣工日期 </td>
					<td align="left" colspan="2">&nbsp;&nbsp;<%=DateUtil.formatFromDB(rsList.getString(0,"sect_finish_date"))%></td>
				</tr>
				<tr>
					<td align="right" width="15%">小区名称</td>
					<td align="left" colspan="2" width="35%">&nbsp;&nbsp;<%=rsList.getString(0,"st_name_frst") %></td>
					<td align="right">小区地址</td>
					<td align="left"colspan="2">&nbsp;&nbsp;<%=rsList.getString(0,"st_addr_frst") %></td>
				</tr>
				<tr>
					<td align="right" >区县</td>
					<td align="left"  colspan="2" >&nbsp;&nbsp;<%=rsList.getString(0,"hp_name") %></td>
					<td align="right" >房管办</td>
					<td align="left"  colspan="2" >&nbsp;&nbsp; <%=rsList.getString(0,"ho_name") %> </td>
				</tr>
				<tr>
					<td align="right" >环线</td>
					<td  colspan="2">&nbsp;&nbsp;<%=rsList.getString(0,"lp_name") %></td>
					<td align="right">街道</td>
					<td  colspan="2" >&nbsp;&nbsp;<%=rsList.getString(0,"str_name") %></td>
				</tr>
				
				<tr>
					<td align="right">小区封闭与否</td>
					<td colspan="5">
						<%if(IsFlag.Shi.toString().equals(rsList.getString(0,"st_isopen"))){ %> 属于封闭式 <%} %>
						<%if(IsFlag.Shi.toString().equals(rsList.getString(0,"st_isopen"))){ %>（出入口&nbsp;&nbsp;<%=rsList.getString(0,"st_gateway") %>个，其中机动车出入口&nbsp;&nbsp;<%=rsList.getString(0,"st_vehicle_gateway") %>）<%} %>
						<%if(IsFlag.Fou.toString().equals(rsList.getString(0,"st_isopen"))){ %> 不属于封闭式 <%} %> 
					</td>
				</tr>
				<tr>
					<td align="right" rowspan="2">小区四至范围</td>
					<td colspan="2">东：&nbsp;&nbsp;<%=rsList.getString(0,"st_csp_east") %></td>
					<td >南：&nbsp;&nbsp;<%=rsList.getString(0,"st_csp_south") %></td>
					<td rowspan="2" colspan="2">不包括：&nbsp;&nbsp;<%=rsList.getString(0,"st_notcsparea") %></td>
				</tr>
				<tr>
					<td colspan="2">西：&nbsp;&nbsp;<%=rsList.getString(0,"st_csp_west") %></td>
					<td>北：&nbsp;&nbsp;<%=rsList.getString(0,"st_csp_north") %></td>
				</tr>
				<tr>
					<td align="right">小区性质</td>
					<td align="left" colspan="2">&nbsp;&nbsp;<%=SectKind.getValue(rsList.getString(0,"st_kind")) %></td>
					<td align="right" >物业服务类型</td>
				   <td colspan="2"><%=CspServiceType.getValue(rsList.getString(0,"st_csp_servcie_type")) %>
				   				<br>是否托管:&nbsp;&nbsp;<%=IsFlag.getValue(rsList.getString(0,"st_is_managed")) %></td>
				</tr>
				<tr>
					<td align="right">总建筑面积（平方米）</td>
					<td align="left" colspan="2">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_cnst_area")) %></td>
					<td align="right">占地面积（平方米）</td>
					<td align="left" colspan="2">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_land_area")) %></td>
				</tr>
				<tr>
					<td align="right">小区状态</td>
					<td colspan="2">&nbsp;&nbsp;<%=InfoStatus.getValue(rsList.getString(0,"sect_curr_stat")) %></td>
					<td align="right">小区类型</td>
					<td colspan="2" align="left">&nbsp;&nbsp;<%= SectType.getValue(rsList.getString(0,"sect_type")) %> </td>
				</tr>
				<tr>
					<td align="right" rowspan="5">房屋分类总面积<br>（平方米）</td>
					<td align="right">商品房</td>
					<td align="left">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_condo_hous_area")) %></td>
					<td align="right">售后房</td>
					<td align="left" colspan="2" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_postmarket_hous_area")) %></td>
				</tr>
				<tr>
					<td align="right">未售公房</td>
					<td align="left" colspan="4">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_public_hous_area")) %>
						（其中不成套房屋 &nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_unsold_notcount")) %>平方米）
					</td>
				</tr>
				<tr>
					<td align="right">其他面积</td>
					<td align="left" colspan="4">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_other_hous_area"))%></td>
				</tr>
				<tr>
					<td align="right">多层面积</td>
					<td align="left">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_multilayer_area")) %></td>
					<td align="right">高层面积</td>
					<td align="left" colspan="2">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_high_area")) %></td>
				</tr>
				<tr>
					<td align="right">别墅面积</td>
					<td align="left">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_villa_area")) %></td>
					<td align="right">非住宅面积</td>
					<td align="left" colspan="2">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_non_residential_area")) %></td>
				</tr>
				<tr>
					<td align="right">小区荣誉</td>
					<td align="left">&nbsp;&nbsp;<%=SectHonor.getValue(rsList.getString(0,"st_sect_honor"))%>
					</td>
					<td colspan="4"><% if(rsList.getString(0,"st_sect_honor").equals(SectHonor.QiTa)){ %>其中：(&nbsp;&nbsp;<%=rsList.getString(0,"st_other_honor") %>)<%} %></td>
				</tr>
			</table>
	</td>
	</tr>
	<tr>
		<td>
			<table class="grid1" width="100%">
				<thead>
				<tr>
					<th colspan="6">设施设备信息</th>
				</tr>
				</thead>
				 <tr >
				    <td align="right" width="15%">消防水泵个数</td>
				    <td width="15%">&nbsp;&nbsp;<%=rsList.getString(0,"st_fire_pump_num") %></td>
				    <td width="15%" align="right">蓄水池个数</td>
				    <td width="15%">&nbsp;&nbsp;<%=rsList.getString(0,"st_tot_reservoirs")%></td>
				    <td width="15%" align="right">水箱个数</td>
				    <td width="15%">&nbsp;&nbsp;<%=rsList.getString(0,"st_tot_tanks") %></td>
				  </tr>
				  <tr >
				    <td align="right">地面机动车位数</td>
				    <td>&nbsp;&nbsp;<%=rsList.getString(0,"st_tot_ong_parks") %></td>
				    <td align="right">地下机动车位数</td>
				    <td>&nbsp;&nbsp;<%=rsList.getString(0,"st_tot_ung_parks") %></td>
				    <td align="right">小区实有机动车数</td>
				    <td>&nbsp;&nbsp;<%=rsList.getString(0,"st_tot_car") %></td>
				  </tr>
				  <tr >
				    <td align="right">非机动车位<br /> 地上停车面积</td>
				    <td>&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_non_vtehicle_tot_ong_parks")) %></td>
				    <td align="right">非机动车位<br />  地下停车面积</td>
				    <td>&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_non_vtehicle_tot_ung_parks")) %></td>
				    <td align="right">绿化面积</td>
				    <td>&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_afforested_area")) %></td>
				  </tr>
				  <tr >
				    <td align="right">生活水泵数</td>
				    <td>&nbsp;&nbsp;<%=rsList.getString(0,"st_tot_pumps") %></td>
				    <td align="right">消防栓总数</td>
				    <td>&nbsp;&nbsp;<%=rsList.getString(0,"st_tot_fire_hydrants") %></td>
				    <td align="right">其中不可用消防栓数</td>
				    <td>&nbsp;&nbsp;<%=rsList.getString(0,"st_no_flre_pump_nmu") %></td>
				  </tr>
				  <tr >
				    <td align="right">已安装监控探头总数</td>
				    <td>&nbsp;&nbsp;<%=rsList.getString(0,"st_tot_monitors") %>个</td>
				    <td  colspan="2" >场地探头数&nbsp;&nbsp;<%=rsList.getString(0,"st_ground_tot_monitors") %>个(其中无法正常使用&nbsp;&nbsp;<%=rsList.getString(0,"st_no_ground_tot_monitors") %>)</td>
				    <td  colspan="2" >电梯内探头数&nbsp;&nbsp;<%=rsList.getString(0,"st_tot_lift_monitors") %>个(其中不可用数&nbsp;&nbsp;<%=rsList.getString(0,"st_no_tot_lift_monitors") %>)</td>
				  </tr>
				  
				  <tr >
				 	<td align="right">消防通道总数</td>
				    <td colspan="2">&nbsp;&nbsp;<%=rsList.getString(0,"st_tot_fire_engine_access") %></td>
				    <td align="right">物企用房地址</td>
				    <td colspan="2">&nbsp;&nbsp;<%=rsList.getString(0,"st_csp_use_addr") %></td>
				  </tr>
				  <tr >
					<td align="right">物企用房建面</td>
					<td colspan="2">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_csp_area")) %>平方米</td>
				    <td align="right">业委会用房</td>
				    <td colspan="2">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_committee_area")) %>平方米</td>
				  </tr>
				  
				  <tr >
				    <td align="right">小区内会所面积</td>
				    <td colspan="2">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_club_area")) %></td>
				    <td align="right">小区内是否有周界报警</td>
				    <td colspan="2">&nbsp;&nbsp;<%=IsFlag.getValue(rsList.getString(0,"st_perimeter_alarm")) %></td>
				  </tr>
				  <tr >
				    <td align="right" >是否安装脉冲电子围栏</td>
				    <td colspan="2" >&nbsp;&nbsp;<%=IsFlag.getValue(rsList.getString(0,"st_pulse_fence")) %></td>
	
				    <td align="right">小区内是否有电子监控室</td>
				    <td colspan="2" >&nbsp;&nbsp;<%=IsFlag.getValue(rsList.getString(0,"st_electronic_room")) %></td>
				 </tr>
				 <tr >
				    <td align="right"  >电梯数（台）（有细表）</td>
				    <td colspan="2" >&nbsp;&nbsp;<%=rsList.getString(0,"st_tot_lift") %></td>
				    <td align="right">经评估有安全隐患电梯数（台）</td>
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
				   <td colspan="6" >物业管理单位信息</td>
			  </tr>
			  </thead>
			  <tr>
			    <td align="right" width="15%">物业服务企业名称</td>
			    <td width="35%" colspan="2" >&nbsp;&nbsp;<%=rscsp.getString(0,"csp_name") %></td>
			    <td align="right" width="15%">注册区县</td>
			    <td width="35%"  colspan="2"  >&nbsp;&nbsp;<%=rscsp.getString(0,"hp_name") %></td>
			 </tr>
			 <tr>
			    <td align="right" >物业服务合同开始时间</td>
			    <td colspan="2">&nbsp;&nbsp;<%=DateUtil.formatFromDB(rsList.getString(0,"st_csp_contract_start_date")) %></td>
			    <td align="right" >物业服务合同结束时间</td>
			    <td colspan="2">&nbsp;&nbsp;<%=DateUtil.formatFromDB(rsList.getString(0,"st_csp_contract_end_date")) %></td>
			 </tr>
			 <tr >
			    <td align="right" >组织机构代码</td>
			    <td colspan="2">&nbsp;&nbsp;<%=rscsp.getString(0,"csp_org_code") %></td>
			    <td align="right">组织机构代码有效期</td>
			    <td colspan="2">&nbsp;&nbsp;<%=DateUtil.formatFromDB(rscsp.getString(0,"csp_code_valid_date")) %></td>
			  </tr>
			  <tr>
			    <td align="right" >物业企业注册地址</td>
			    <td colspan="2">&nbsp;&nbsp;<%=rscsp.getString(0,"csp_register_address") %></td>
			    <td align="right" >物业企业办公地址</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rscsp.getString(0,"csp_addr") %></td>
			  </tr>
			  <tr >
			    <td align="right">企业法人代表</td>
			    <td colspan="2">&nbsp;&nbsp;<%=rscsp.getString(0,"csp_legal_rep_name") %></td>
			    <td align="right">企业法人手机号码</td>
			    <td colspan="2">&nbsp;&nbsp;<%=rscsp.getString(0,"csp_legal_rep_phone") %></td>
			  </tr>
			  <tr >
			    <td align="right">企业联系人</td>
			    <td colspan="2">&nbsp;&nbsp;<%=rscsp.getString(0,"csp_contacter") %></td>
			    <td  align="right">企业联系电话</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rscsp.getString(0,"csp_tel") %></td>
			  </tr>
			  <tr >
			    <td align="right" >企业传真</td>
			    <td colspan="2">&nbsp;&nbsp;<%=rscsp.getString(0,"csp_fax") %></td>
			    <td align="right" >物业企业资质等级</td>
			    <td colspan="2">&nbsp;&nbsp;<%=CspQualifyLevel.getValue(rscsp.getString(0,"csp_ent_qualification")) %></td>
			  </tr>
			  <tr >
			    <td align="right" >资质证书编号</td>
			    <td colspan="2">&nbsp;&nbsp;<%=rscsp.getString(0,"csp_qualification") %></td>
			    <td align="right">资质证书颁证日期</td>
			    <td colspan="2">&nbsp;&nbsp;<%=DateUtil.formatFromDB(rscsp.getString(0,"csp_qualification_valid_date")) %></td>
			  </tr>
			  <tr >
			    <td align="right" >营业执照号</td>
			    <td colspan="2">&nbsp;&nbsp;<%=rscsp.getString(0,"csp_biz_license_code") %></td>
			    <td align="right" >营业执照有效期</td>
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
			    <td colspan="6" >小区管理处信息</td>
			  </tr>
			  </thead>
			  <tr>
			    <td align="right" width="15%">小区管理处名称</td>
			    <td width="35%" colspan="2" >&nbsp;&nbsp;<%=rscsp_sect.getString(0,"cs_name") %></td>
			    <td align="right" width="15%">小区管理处办公地址</td>
			    <td width="35%" colspan="2" >&nbsp;&nbsp;<%=rscsp_sect.getString(0,"cs_addr") %></td>
			  </tr>
			  <tr >
			    <td align="right">小区管理处联系人</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rscsp_sect.getString(0,"cs_contacter") %></td>
			    <td align="right" >联系人联系电话</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rscsp_sect.getString(0,"cs_tel") %></td>
			 </tr>
			 <tr>
			    <td align="right" >小区日间报修电话</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rscsp_sect.getString(0,"cs_day_tel") %></td>
			    <td align="right">小区夜间报修电话</td>
			    <td colspan="2"  >&nbsp;&nbsp;<%=rscsp_sect.getString(0,"cs_night_tel") %></td>
			 </tr>
			 <tr>
			    <td align="right" >物业投诉电话</td>
			    <td colspan="2">&nbsp;&nbsp;<%=rscsp_sect.getString(0,"cs_complaint_tel") %></td>
			    <td align="right" >小区管理处传真</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rscsp_sect.getString(0,"cs_fax") %></td>
			  </tr>
			  <tr >
			    <td align="right" >小区管理处邮编</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rsxmjl.getString(0,"cs_post_code")%></td>
			    <td align="right" >小区经理姓名</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rsxmjl.getString(0,"csm_name")%></td>
			 </tr>
			  <tr >
			    <td align="right" >小区经理固定电话号</td>
			    <td colspan="2">&nbsp;&nbsp;<%=rsxmjl.getString(0,"csm_tel")%></td>
			    <td align="right" >小区经理手机号</td>
			    <td colspan="2">&nbsp;&nbsp;<%=rsxmjl.getString(0,"csm_phone")%></td>
			  </tr>
			  <tr >
			    <td align="right" >小区经理身份证号码</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rsxmjl.getString(0,"csm_cert_code")%></td>
			    <td align="right" >小区经理上岗证号</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rsxmjl.getString(0,"csm_job_code")%></td>
			  </tr>
			  <tr >
			    <td align="right" >小区经理资格证书名称</td>
			    <td colspan="2"  >&nbsp;&nbsp;<%=rsxmjl.getString(0,"csm_job_Qualification")%></td>
			    <td align="right" >证书编号</td>
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
			    <td colspan="10" >业主大会和业主委员会信息（填报前请与业委居会沟通）</td>
			  </tr>
			  </thead>
			  <tr>
			    <td align="right" width="15%" >未成立业主大会原因 <p>（已经成立的无需填）</p></td>
			    <td colspan="9"><input type="checkbox" disabled name="sect_hoc_reason" value="<%=NotSetUpHoc.Wsbjfsydjzmjwdd50ys.toString() %>">出售并交付使用的建筑面积未达到50%以上；
							    <input type="checkbox" disabled name="sect_hoc_reason" value="<%=NotSetUpHoc.Stfwcsbjfsywm2n.toString() %>">首套房屋出售并交付使用未满2年 &nbsp;
							    <input type="checkbox" disabled name="sect_hoc_reason" value="<%=NotSetUpHoc.Yzjsjdbcl.toString() %>">业主较少决定不成立；&nbsp;
							    <input type="checkbox" disabled name="sect_hoc_reason" value="<%=NotSetUpHoc.Jwhswcl.toString() %>">居委会尚未成立&nbsp;&nbsp;&nbsp;（可多选）</td>
			  </tr>
			  <tr >
			     <td align="right"  width="15%">业主大会名称</td>
			    <td colspan="4" width="35%" >&nbsp;&nbsp;<%=rsywh.getString(0,"hoc_name") %></td>
			    <td align="right"  width="15%">业主大会办公地址</td>
			    <td colspan="4" width="35%" >&nbsp;&nbsp;<%=rsywh.getString(0,"hoc_addr") %></td>
			  </tr>
			  <tr >
			    <td  align="right">业委会联系电话</td>
			    <td colspan="4">&nbsp;&nbsp;<%=rsywh.getString(0,"hoc_tel") %></td>
			    <td  align="right">业委会届别</td>
			    <td colspan="4" >&nbsp;&nbsp;<%=rsywh.getString(0,"hoc_term") %></td>
			 </tr>
			  <tr >
			    <td  align="right">业委会任期起始时间</td>
			    <td colspan="4" >&nbsp;&nbsp;<%=DateUtil.formatFromDB(rsywh.getString(0,"hoc_con_start_date")) %></td>
			    <td  align="right" >业委会任期结束时间</td>
			    <td colspan="4">&nbsp;&nbsp;<%=DateUtil.formatFromDB(rsywh.getString(0,"hoc_con_end_data"))%></td>
			 </tr>
			  <tr >
			    <td  align="right">选举时间</td>
			    <td colspan="4">&nbsp;&nbsp;<%=DateUtil.formatFromDB(rsywh.getString(0,"hoc_election_date")) %></td>
			    <td align="right" width="15%">业主大会代码</td>
			    <td colspan="4"  width="35%">&nbsp;&nbsp;<%=rsywh.getString(0,"hoc_code") %></td>
			 </tr>
			  <tr >
				  <td align="right">职务</td>
				  <td   colspan="2"> <%=HocDutyType.getValue(rsywh.getString(0,"dir_duty")) %>  </td>
				  <td align="right">姓名</td>
			      <td colspan="2">&nbsp;&nbsp;<%=rsywh.getString(0,"dir_name") %></td>
			      <td align="right" >性别</td>
			      <td colspan="3">&nbsp;&nbsp;<%=SexFlag.getValue(rsywh.getString(0,"dir_sex")) %></td>
			 </tr>
			 <tr>
			    <td align="right" width="15%">年龄</td>
			    <td colspan="2"  width="20%">&nbsp;&nbsp;<%=rsywh.getString(0,"dir_age") %></td>
			    <td align="right" width="15%">政治面貌</td>
			    <td colspan="2" width="25%">&nbsp;&nbsp;<%=PoliticsStatus.getValue(rsywh.getString(0,"dir_politics_status")) %></td>
			    <td align="right" width="10%">联系电话</td>
			    <td colspan="3" width="15%">&nbsp;&nbsp;<%=rsywh.getString(0,"dir_tel") %></td>
			 </tr>
			 <tr >
			    <td align="right">证件类型</td>
			    <td colspan="5">&nbsp;&nbsp;<%=CertType.getValue(rsywh.getString(0,"dir_cert_type")) %></td>
			    <td  align="right">证件编号</td>
			    <td colspan="3">&nbsp;&nbsp;<%=rsywh.getString(0,"dir_cert_code") %></td>
			 </tr>
			 <tr >
				  <td align="right">职务</td>
				  <td  colspan="2"> <%=HocDutyType.getValue(rsywh.getString(1,"dir_duty")) %>  </td>
				  <td align="right">姓名</td>
			      <td colspan="2">&nbsp;&nbsp;<%=rsywh.getString(1,"dir_name") %></td>
			      <td align="right" >性别</td>
			      <td colspan="3">&nbsp;&nbsp;<%=SexFlag.getValue(rsywh.getString(1,"dir_sex")) %></td>
			 </tr>
			 <tr >
			    <td align="right" >年龄</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rsywh.getString(1,"dir_age") %></td>
			    <td align="right" >政治面貌</td>
			    <td colspan="2">&nbsp;&nbsp;<%=PoliticsStatus.getValue(rsywh.getString(1,"dir_politics_status")) %></td>
			    <td align="right" >联系电话</td>
			    <td colspan="3">&nbsp;&nbsp;<%=rsywh.getString(1,"dir_tel") %></td>
			 </tr>
			 <tr >
			    <td align="right">证件类型</td>
			    <td colspan="5">&nbsp;&nbsp;<%=CertType.getValue(rsywh.getString(1,"dir_cert_type")) %></td>
			    <td  align="right">证件编号</td>
			    <td colspan="3">&nbsp;&nbsp;<%=rsywh.getString(1,"dir_cert_code") %></td>
			 </tr>
			 <tr >
				  <td align="right">职务</td>
				  <td   colspan="2"> <%=HocDutyType.getValue(rsywh.getString(2,"dir_duty")) %>  </td>
				  <td align="right">姓名</td>
			      <td colspan="2">&nbsp;&nbsp;<%=rsywh.getString(2,"dir_name") %></td>
			      <td align="right" >性别</td>
			      <td colspan="3">&nbsp;&nbsp;<%=SexFlag.getValue(rsywh.getString(2,"dir_sex")) %></td>
			 </tr>
			 <tr >
			    <td align="right" >年龄</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rsywh.getString(0,"dir_age") %></td>
			    <td align="right" >政治面貌</td>
			    <td colspan="2">&nbsp;&nbsp;<%=PoliticsStatus.getValue(rsywh.getString(2,"dir_politics_status")) %></td>
			    <td align="right" >联系电话</td>
			    <td colspan="3">&nbsp;&nbsp;<%=rsywh.getString(2,"dir_tel") %></td>
			 </tr>
			 <tr >
			    <td align="right">证件类型</td>
			    <td colspan="5">&nbsp;&nbsp;<%=CertType.getValue(rsywh.getString(2,"dir_cert_type")) %></td>
			    <td  align="right">证件编号</td>
			    <td colspan="3">&nbsp;&nbsp;<%=rsywh.getString(2,"dir_cert_code") %></td>
			 </tr>
			 <tr >
			    <td align="right">运作情况类别</td>
			    <td align="left" colspan="9" >&nbsp;&nbsp;<%=OnwersTypeOper.getValue(rsywh.getString(0,"hoc_onwers_typeoper")) %></td>
			  </tr>
			  <tr>
			    <td align="right">换届情况类别</td>
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
			    <td colspan="6" >居民委员会信息</td>
			  </tr>
			  </thead>
			  <tr >
			    <td align="right" width="15%">所属居民委员会名称</td>
			    <td colspan="2" width="35%" >&nbsp;&nbsp;<%=rsjwh.getString(0,"cmt_name")%></td>
			    <td align="right" width="15%" >居委会办公地址</td>
			    <td colspan="2" width="35%" >&nbsp;&nbsp;<%=rsjwh.getString(0,"cmt_addr") %></td>
			  </tr>
			  <tr >
			    <td align="right"  >居委书记</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rsjwh.getString(0,"cmt_secretary_name") %></td>
			    <td align="right" >联系电话</td>
			    <td colspan="2" >&nbsp;&nbsp;<%=rsjwh.getString(0,"cmt_secretary_tel")%></td>
			  </tr>
			  <tr >
			    <td align="right" >居委主任</td>
			    <td colspan="2">&nbsp;&nbsp;<%=rsjwh.getString(0,"cmt_director_name") %></td>
			    <td align="right"  >联系电话</td>
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
		      <td  colspan="10" >经营管理信息</td>
		    </tr>
		    </thead>
		    <tr>
		      <td  colspan="2" align="right" width="21%">管理人员数</td>
		      <td  colspan="3" align="left">&nbsp;&nbsp;<%=rsList.getString(0,"st_manager_num") %></td>
		      <td  colspan="2"  align="right" width="21%">维修人员数</td>
		      <td  colspan="3" align="left" >&nbsp;&nbsp;<%=rsList.getString(0,"st_repairs_num") %></td>
		    </tr>
		    <tr >
		      <td colspan="2" align="right" >保洁人员数</td>
		      <td colspan="3" align="left">&nbsp;&nbsp;<%=rsList.getString(0,"st_clean_num") %></td>
		      <td colspan="2" width="8%" align="right" >安保人员数</td>
		      <td colspan="3" align="left" >&nbsp;&nbsp;<%=rsList.getString(0,"st_security_num") %></td>
		    </tr>
		    <tr >
		      <td colspan="2" align="right" >保绿人员数</td>
		      <td colspan="3" align="left" >&nbsp;&nbsp;<%=rsList.getString(0,"st_greening_num") %></td>
		      <td  colspan="2"align="right" >物业管理费收费制度</td>
		      <td  colspan="3" align="left" >&nbsp;&nbsp;<%=ChargeMethods.getValue(rsList.getString(0,"st_charge_methods")) %> </td>
		    </tr>
		    <tr >
		      <td rowspan="4" align="right" width="11%">收费标准&nbsp;元/平米月<p>[不含设备运行费]</p> </td>
		      <td width="10%" align="right" >多层（商品房）</td>
		      <td  width="17%" colspan="2" align="left" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_multilayer_standard")) %></td>
		      <td colspan="2" rowspan="4" width="17%" align="right" >全年应收物业费总额（元）<p>（不含设施设备运行费）</p></td>
		      <td colspan="2" rowspan="4" width="15%" align="left" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_year_charge_total")) %></td>
		      <td rowspan="4" align="right"  ><%=year%>年物业费实收（元）</td>
		      <td rowspan="4" align="left" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_yearwyfss_2012")) %></td>
		    </tr>
		    <tr >
		      <td align="right"  >高层（商品房）</td>
		      <td width="89"  colspan="2" align="left"  >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_top_standard")) %></td>
		    </tr>
		    <tr >
		      <td align="right"  >别墅（商品房）</td>
		      <td colspan="2"  width="89"  align="left" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_villa_standard")) %></td>
		    </tr>
		    <tr >
		      <td align="right"   >售后(元/户月)</td>
		      <td width="89" colspan="2" align="left"  >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_after_standard")) %></td>
		    </tr>
			<tr >
			      <td colspan="2" align="right"><%=year%>年服务总成本（元）</td>
			      <td colspan="2" align="left" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_yearfwzcb_2012")) %></td>
			      <td colspan="2"  align="right" >单位面积服务成本(元/平方米)<br>(总成本/可收费面积)</td>
			      <td colspan="4" align="left"  >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_unit_service_cost")) %></td>
			 </tr>
			 <tr >
			      <td colspan="2" align="right"   ><%=year%>年物业可支配的公共收益（元）</td>
			      <td colspan="8" align="left"  >总&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_wykzpdggsyze"))%>元（其中停车费提分成&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_wykzpdggsyqztcftc")) %>元，
			         其他收益&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_wykzpdggsy")) %>元）</td>
			 </tr>

		    <tr>
		      <td colspan="2" align="right" >享受老小区达标补贴面积（平方米）</td>
		      <td  colspan="2" align="left" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_xslxqdbbtmj"))%></td>
		      <td colspan="4" rowspan="2" align="center" >全年享受补贴金额（元）<p>(按每月每平方米0.27元计算)</p> </td>
		      <td colspan="2" rowspan="2"  align="left" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_qnxsbtje")) %></td>
		    </tr>
		    <tr >
		      <td colspan="2" align="right" >享受早期动迁房补贴面积（平方米）</td>
		      <td align="left" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_xszqdqfbtmj")) %></td>
		    </tr>
		    <tr >
		      <td colspan="2" align="right"  ><%=year%>年本小区盈亏（+/-元）</td>
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
		      <td colspan="7">房屋维修资金信息</td>
		    </tr>
		    </thead>
		    <tr >
		      <td align="right" rowspan="2" >商品房</td>
		      <td align="right" >首期归集金额</td>
		      <td align="left" width="10%">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_spfsqgjje")) %></td>
		      <td align="right" >已使用资金额</td>
		      <td align="left"  width="10%" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_spfysyzje")) %></td>
		      <td align="right"  >资金总余额</td>
		      <td align="left"  width="10%" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_spfzjzye")) %></td>
		    </tr>
		    <tr >
		      <td align="right" >是否满足续筹条件</td>
		      <td align="left" ><%=IsFlag.getValue(rsList.getString(0,"st_spfsfmzxctj")) %> </td>
		      <td align="right" >已续筹金额</td>
		      <td align="left">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_spfyxcje"))%></td>
		      <td align="left"></td>
		      <td align="left"></td>
		    </tr>
		    <tr >
		      <td rowspan="2" align="right">售后房 <p>三项</p> <p>基金</p> </td>
		      <td align="right" >初始房屋维修基金</td>
		      <td align="left" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_shfcsfwwxjj")) %></td>
		      <td align="right" >初始街坊基金</td>
		      <td align="left" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_shfcsjfjj")) %></td>
		      <td align="right" >初始电梯水泵基金</td>
		      <td align="left" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_shfcsdtsbjj")) %></td>
		    </tr>
		    <tr >
		      <td align="right" >房屋维修基金余额</td>
		      <td align="left">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_shffwwxjjye")) %></td>
		      <td align="right" >街坊基金余额</td>
		      <td align="left" >&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_shfjfjjye")) %></td>
		      <td align="right" >电梯水泵基金余额</td>
		      <td align="left">&nbsp;&nbsp;<%=StringUtil.format2Decimal(rsList.getString(0,"st_shfdtsbjjye")) %></td>
		    </tr>
		    <tr >
		      <td align="right" >备注</td>
		      <td  colspan="6" align="left" >&nbsp;&nbsp;<%=rsList.getString(0,"st_remk") %></td>
		    </tr>
		  </table>
	 </td>
</tr>
 </table>
</form>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/jsp/commons/taglibs.jsp" %>    
<html>
<%	
	hmfms.web.User user = (hmfms.web.User)request.getSession().getAttribute(hmfms.base.BaseAction.USER_SESSION);
	String strName = user.getTellID();
%>
<head>
<title><%=_PAGE_TITLE %></title>
<link rel="stylesheet" href="<c:out value="${ctx}"/>/style/style.css" type="text/css">
<%@ include file="/jsp/commons/meta.jsp" %>
<SCRIPT LANGUAGE="JavaScript">
$(document).ready(function(){	
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();//��ʼ����ť������(��һ��button��ɵ�div,������htmlʵ����.btn_toolbar .btn .btn_condition ������ʽ���Ʋ��ܱ�֮�����������Ըı�)
	var g=$('.grid1').datagrid({toolbar:bar,trselect:false});//��ִ�д��� g[0].grid.setBar(bar);	Ҳ����
});	
function modiPass()
{
	if(form1.oldpass.value == null || form1.oldpass.value == ""){
		alert("������ԭ���룡");
		return false;
	}
	if(form1.newpass.value == null || form1.newpass.value == ""){
		alert("�����������룡");
		return false;
	}
	if(form1.newpass1.value == null || form1.newpass1.value == ""){
		alert("������ȷ�������룡");
		return false;
	}
	if(form1.oldunlock.value == null || form1.oldunlock.value == ""){
		alert("������ԭ�������룡");
		return false;
	}
	if(form1.newunlock.value == null || form1.newunlock.value == ""){
		alert("�������½������룡");
		return false;
	}
	if(form1.newunlock1.value == null || form1.newunlock1.value == ""){
		alert("������ȷ���½������룡");
		return false;
	}		
		var newpass = form1.newpass.value;
		var newpass1 = form1.newpass1.value
		if(newpass != newpass1)
		{
			alert("ȷ��������������벻һ�£�");
			return false;
		}
		var newunlock = form1.newunlock.value;
		var newunlock1 = form1.newunlock1.value
		if(newunlock != newunlock1)
		{
			alert("�½��������ȷ���½������벻һ��");
			return false;
		}
		var opid = form1.op_id.value;
		var oldunlock = form1.oldunlock.value;
		var oldpass = form1.oldpass.value;
		$.webUtil.submit("changePwdOK.do?opid=" + opid + "&oldunlock=" + oldunlock+"&oldpass="+oldpass+"&newunlock=" + newunlock+"&newpass="+newpass);
}	
</SCRIPT>
</head>
<body text="#000000" bgcolor="#FFFFFF">
 <table border="0" align="center" cellpadding="0" cellspacing="0" width="620">
  <tr>
    <td width="100%" class="bigbold" height="30" background="<c:out value="${ctx}"/>/images/sub_bg.gif"><img border="0" src="<c:out value="${ctx}"/>/images/home_title_leftarrow.gif">�޸�����</td>
  </tr>
  <tr>
    <td width="100%" height="10"><html:errors/>
	</td>
  </tr>
  <tr>
    <td width="100%">
    <form name="form1" method="post" action="" target="_parent" >
	  <table  width="95%"  border="0" cellspacing="0" cellpadding="0" align="center">
		            <tr>
		              <td align="center">
		              <div>
		              	<button  type="button" class="btn" value="1" onClick="modiPass()"  >����</button>
						<button  type="button" class="btn" value="2" onClick="window.close()" >�ر�</button>
							</div>
		              </td>
		            </tr>   
		            <tr><td height="<%=_PAGEBLOCK_HEIGHT%>"></td></tr> 	  
		<tr>
		  <td>
             <div align="center">
              <center>
            
				 <table id="list_hashead" class="grid1" width="100%" border="0" cellspacing="0" cellpadding="0" bordercolor="#9BB3D3" align="center">
					<thead>
             		<tr>
             			<th colspan="2">����Ա������Ϣ</th>
             		</tr>
             		</thead>
             		
					<tr>
					  <td  width="30%" align="center">�û���<input type="hidden" name="op_id" value="<%=strName%>"></td>
					  <td  width="70%" align="left"><%=strName%></td>
					</tr>
					<tr>
					  <td align="center" >ԭ����</td>
					  <td align="left"><input type="password" size = "20" maxlength="12" name="oldpass" title="ԭ����" class="text"></td>
				    </tr>
					<tr>
					  <td align="center" >������</td>
					  <td align="left"><input type="password" size = "20" maxlength="12" name="newpass" title="������" class="text"></td>
				    </tr>
					<tr>
					  <td align="center" >ȷ��������</td>
					  <td align="left"><input type="password" size = "20" maxlength="12" name="newpass1" title="ȷ��������" class="text"></td>
				    </tr>
					<tr>
					  <td align="center" >ԭ��������</td>
					  <td align="left"><input type="password" size = "20" maxlength="12" name="oldunlock" title="ԭ��������" class="text"></td>
				    </tr>
					<tr>
					  <td align="center" >�½�������</td>
					  <td width="70%" align="left"><input type="password" size = "20" maxlength="12" name="newunlock" title="�½�������" class="text"></td>
				    </tr>
					<tr>
					  <td  align="center">ȷ���½�������</td>
					  <td  align="left"><input type="password" size = "20" name="newunlock1" title="ȷ���½�������" class="text"></td>
				    </tr>
				  </table>
                </center>
                 </div>
			  </td>
		    </tr>
		<tr>
			<td height="20" align="center">&nbsp;
			</td>
		</tr>
	    </table>
      </form>
    </td>
  </tr>
  <tr>
    <td width="100%"></td>
  </tr>
</table>
</body>
</html>
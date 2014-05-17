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
	var bar=$('.btn_toolbar').BtnToolBar();//初始化按钮工具条(有一组button组成的div,见下面html实例，.btn_toolbar .btn .btn_condition 三个样式名称不能变之外其他都可以改变)
	var g=$('.grid1').datagrid({toolbar:bar,trselect:false});//或执行代码 g[0].grid.setBar(bar);	也可以
});	
function modiPass()
{
	if(form1.oldpass.value == null || form1.oldpass.value == ""){
		alert("请输入原密码！");
		return false;
	}
	if(form1.newpass.value == null || form1.newpass.value == ""){
		alert("请输入新密码！");
		return false;
	}
	if(form1.newpass1.value == null || form1.newpass1.value == ""){
		alert("请输入确认新密码！");
		return false;
	}
	if(form1.oldunlock.value == null || form1.oldunlock.value == ""){
		alert("请输入原解锁密码！");
		return false;
	}
	if(form1.newunlock.value == null || form1.newunlock.value == ""){
		alert("请输入新解锁密码！");
		return false;
	}
	if(form1.newunlock1.value == null || form1.newunlock1.value == ""){
		alert("请输入确认新解锁密码！");
		return false;
	}		
		var newpass = form1.newpass.value;
		var newpass1 = form1.newpass1.value
		if(newpass != newpass1)
		{
			alert("确认新密码和新密码不一致！");
			return false;
		}
		var newunlock = form1.newunlock.value;
		var newunlock1 = form1.newunlock1.value
		if(newunlock != newunlock1)
		{
			alert("新解锁密码和确认新解锁密码不一致");
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
    <td width="100%" class="bigbold" height="30" background="<c:out value="${ctx}"/>/images/sub_bg.gif"><img border="0" src="<c:out value="${ctx}"/>/images/home_title_leftarrow.gif">修改密码</td>
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
		              	<button  type="button" class="btn" value="1" onClick="modiPass()"  >保存</button>
						<button  type="button" class="btn" value="2" onClick="window.close()" >关闭</button>
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
             			<th colspan="2">操作员密码信息</th>
             		</tr>
             		</thead>
             		
					<tr>
					  <td  width="30%" align="center">用户号<input type="hidden" name="op_id" value="<%=strName%>"></td>
					  <td  width="70%" align="left"><%=strName%></td>
					</tr>
					<tr>
					  <td align="center" >原密码</td>
					  <td align="left"><input type="password" size = "20" maxlength="12" name="oldpass" title="原密码" class="text"></td>
				    </tr>
					<tr>
					  <td align="center" >新密码</td>
					  <td align="left"><input type="password" size = "20" maxlength="12" name="newpass" title="新密码" class="text"></td>
				    </tr>
					<tr>
					  <td align="center" >确认新密码</td>
					  <td align="left"><input type="password" size = "20" maxlength="12" name="newpass1" title="确认新密码" class="text"></td>
				    </tr>
					<tr>
					  <td align="center" >原解锁密码</td>
					  <td align="left"><input type="password" size = "20" maxlength="12" name="oldunlock" title="原解锁密码" class="text"></td>
				    </tr>
					<tr>
					  <td align="center" >新解锁密码</td>
					  <td width="70%" align="left"><input type="password" size = "20" maxlength="12" name="newunlock" title="新解锁密码" class="text"></td>
				    </tr>
					<tr>
					  <td  align="center">确认新解锁密码</td>
					  <td  align="left"><input type="password" size = "20" name="newunlock1" title="确认新解锁密码" class="text"></td>
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
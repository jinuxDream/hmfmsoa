<%@page import="hmfms.util.ActionUtil"%>
<%@page import="hmfms.web.User"%>
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ include file="/jsp/commons/taglibs.jsp" %>
<%@page import="hmfms.util.DateUtil"%>
<%@page import="fd.commons.jdbc.Result"%>
<%
User user=ActionUtil.getUser(request);
String strDeptType = user.getDeptType();
String strTellName = user.getTellName();
String strDeptName = user.getDeptName();

String htmlMenu = (String)request.getAttribute("htmlMenu");
%>


<%@page import="mng_plat.biz.sysmng.init.LoginMng"%><html>
<head>
  <!--[if !(IE)]><!-->
  <!--<![endif]-->
  <!--[if IE 8]>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
  <![endif]--> 
  <!--[if IE 7]>
  <meta http-equiv="X-UA-Compatible" content="IE=7" />
  <![endif]--> 
  <!--[if lte IE 6]>
  <![endif]--> 
<%@ include file="/jsp/commons/meta.jsp" %>
<script language="javascript" type="text/javascript"> 
function toExit1(){
	window.open("<c:out value="${ctx}"/>/hmfms/biz/init/exitSys.do","_parent","width=600,height=600,resu=no,scrollbars=yes");
}
function toExit(){
	//alert("ddd");
	var req = new Request(
	{
		method: 'post',async: false,url: "<c:out value="${ctx}"/>/hmfms/biz/init/exitSys.do?toclose=true",
		evalScripts: false,
		timeout: 3000,headers: {'Pragma': 'no-cache'},
		onRequest: function(item) {
		 //alert(item);
		},
		onSuccess: function(html) {
		 //alert(html);
		},
		onFailure: function(item) {
		// alert(item.responseText);
		},
		onTimeout: function(item) {
			alert("不能正常退出，下次登陆请解锁!");
		}
	}).send();
}
function window.onbeforeunload()
{
var b = window.event.clientX > document.body.clientWidth - 20;
// window.topframe 里的 topframe 与框架里的横向框架页 topframe 对应
  	var statusb = "fullscreen=0,toolbar=0,location=0,scrollbars=1,status=1,resizable=0"; 
	if(b &&event.clientY<0||event.altKey){
		//alert("begin");
		toExit();
	}else{
	}
}
</script>

<style type="text/css">
*{font-family:"微软雅黑";}
a:hover{text-decoration:none;}
ul{ padding:0; margin:0;}

.top-button{
	font-size:14px;
	color:#5578A2;
}
.top-button:hover{
	color:#F37B03;
}

/* 
	LEVEL ONE 
*/
ul.dropdown{ 
	position: relative;	
	margin-top:2px;	
	min-width:500px;
}
ul.dropdown li{
	min-width:120px;
	font-weight: normal; 
	float: left; 
	zoom: 1;
	border:1px solid #275985;
	background:#2F8ECC;
	cursor:pointer;
}
ul.dropdown a:hover{ 
	color: #fff; 
}
ul.dropdown a:active{ 
	color: #333; 
}
ul.dropdown li a{ 
	display: block; 
	padding: 4px 20px;	
	border-right: 1px solid #333;
	color: #fff;
}
ul.dropdown li:last-child a{ 
	border-right: none; 
}
ul.dropdown li.hover,ul.dropdown li:hover{ 
	background: #F37B03; 
	color: #fff; 
	position: relative; 
}
ul.dropdown li:hover > a{ 
	color: #fff; 
}

/* 
	LEVEL TWO
*/
ul.dropdown ul{	
	visibility: hidden; 
	position: absolute; 
	top: 100%; 
	left: 0; 
}
ul.dropdown ul li{ 	
	font-weight: normal; 
	background: #D2E6F6; 
	color: #333; 
	border: 1px solid #FFF; 
	border-bottom: 1px solid #ccc;
	border-right: 1px solid #ccc;
	float: none; 
	padding: 2px 0 ;
}									  
ul.dropdown ul li a{ 
	border-right: none; 
	width:80%;
	display: inline-block;
	color: #333;
} 

/* 
	LEVEL THREE
*/
ul.dropdown ul ul{ 
	left: 100%; 
	top: 0; 
	_width:220px;	
}
ul.dropdown ul ul li {
	background:#FFEFDD;
	color: #333; 
	border: 1px solid #FFF; 
	border-bottom: 1px solid #ccc;
	border-right: 1px solid #ccc;
}
ul.dropdown ul ul li a{
	white-space:nowrap;
}
ul.dropdown li:hover > ul{ 
	visibility: visible;
}

/* FOR IE*/
*ul.dropdown ul li{ 
	display: inline; 
	width: 100%;
}
</style>

<script type="text/javascript">
function modiPass()
{
	url="changePwd.do";
	window.open(url, "Popup", "width=650,height=350,top=200,left=380");
}
</script>
<script type="text/javascript">
function htmlMenu(htmlMenu){
  	$(".dropdown").html(htmlMenu);
  	 $("ul.dropdown li").hover(function(){        	
         $(this).addClass("hover");
         $('ul:first',this).css('visibility', 'visible');
 		
     }, function(){    
         $(this).removeClass("hover");
         $('ul:first',this).css('visibility', 'hidden');    
     });
     
     $("ul.dropdown ul li:has(ul)").find("a:first").append($("<img style='position:absolute;right:5px;margin-top:5px' src='<c:out value="${ctx}"/>/images/menu/menu_arrow.gif'/>"));
     
     /*单击菜单后关闭下拉菜单*/
     $("ul.dropdown ul ul li a").click(function(){
     	$(this).parents("li").removeClass("hover");
         $('ul:first',$(this).parents("li")).css('visibility', 'hidden');
     });

 	/*横向菜单浏览器兼容性修正，菜单按钮长度随菜单内容变化*/
     if($.browser.msie && jQuery.browser.version=="6.0"){return; } //for IE6
     $("ul.dropdown li:has(.sub_menu)").each(function(){
     	var maxLiLength=0;
     	$(this).find("li:has(ul)").each(function(){    		
     		if($(this).width()>maxLiLength) maxLiLength=$(this).width();
     		var maxLiLength2=0;
     		$(this).find("li").each(function(){
     			if($(this).width()>maxLiLength2) maxLiLength2=$(this).width();
     		});
     		$(this).find(".sonsub_menu").width(maxLiLength2);   		   		
     	});
     	$(this).find(".sub_menu").width(maxLiLength);
     });
}
$(document).ready(function(){
	htmlMenu("<%=htmlMenu%>");
});
function selectMenu(){
	mainframe.window.selectMenu(); 
}
</script>
</head>
<body scroll="no">
<form name="form1" target="mainframe" method="post">
<div align="center">
	<div id="top">
		<table id="topTab" border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr> 
				<td width="20%" background="<c:out value="${ctx}"/>/images/main/top_bg.gif" valign="top" align="left"><img border="0" src="<c:out value="${ctx}"/>/images/main/top_name.jpg" width="702" height="80"></td>
				<td width="20%" background="<c:out value="${ctx}"/>/images/main/top_bg.gif"></td>
				<td valign="top" align="right" background="<c:out value="${ctx}"/>/images/main/top_bg.gif">
					<table style="position: absolute; right:20px; top: 2px; width: 220px;">
						<tr>
						<td width="100" align="right"><a class="nowrap top-button" href="<c:out value="${ctx}"/>/mng_plat/biz/sysmng/init/workspace.do" target="mainframe">首页--</a></td>
						<td width="100" align="right"><a class="nowrap top-button" onclick="modiPass();return false;" href="<c:out value="${ctx}"/>/mng_plat/biz/sysmng/init/top.do">修改密码--</a></td>
                        <td width="100" align="right"><a class="nowrap top-button" href="<c:out value="${ctx}"/>/mng_plat/biz/sysmng/init/logout.do" target="_parent">退出系统--</a></td>
						<td width="100" align="right"><a class="nowrap top-button" href="#" onclick="selectMenu();"><font color="red">重新选择</font></a></td>  
						<td width="200" align="right">&nbsp;</td> 
                        </tr>
                        <tr><td colspan="10" align="right">
	                    	<table border="0" cellspacing="0" cellpadding="0" width="100%">
	                        	<tbody>
	                            	<tr>                                                   
	                                	<td width="26"><img border="0" src="<c:out value="${ctx}"/>/images/main/title_user.gif" width="26" height="33" /></td>
	                                    <td width="12%" class="whiteW nowrap"  align="left"><%=strTellName%></td>
	                                    <td width="26"><img border="0" src="<c:out value="${ctx}"/>/images/main/title_department.gif" width="49" height="33" /></td>
	                                    <td width="12%" class="whiteW nowrap" align="left"><%=strDeptName%></td>
	                                </tr>
	                           	</tbody>
	                       	</table>                                                           	
                        </td></tr>
                        <tr><td colspan="10" align="right">
                       		 <span class="whiteW nowrap pl20">系统日期：<%=DateUtil.formatFromDB(DateUtil.getSysDate()) %></span>
                        </td></tr>
					</table>
					<img border="0" src="<c:out value="${ctx}"/>/images/main/top_right.gif" width="20" height="80">
				</td>
	  		</tr>
  			<tr>                	
				<td colspan="3"><div align="left">
				<table border="0" cellspacing="0" cellpadding="0" width="100%" background="<c:out value="${ctx}"/>/images/main/title_bg.gif">
		          	<tbody>
		             	<tr>                  	
			            	<td width="17" align="left" valign="top" height="33"><img border="0" src="<c:out value="${ctx}"/>/images/main/title_left.gif" width="17"></td>                                	
			                <td align="left" valign="top" height="33">
			                    	<!-- 菜单 begin -->
									<ul class="dropdown">
										
			                        </ul>
			                        <div class="clear"></div>
			                        <!-- 菜单 end -->                                        
			               	</td>
			                <td width="14" align="right"><img border="0" src="<c:out value="${ctx}"/>/images/main/title_right.gif" width="14" height="33"></td>
			           		</tr>
		               	</tbody>
		           </table></div>
    			</td>
 			</tr>    
		</table>
	</div>
	<div id="middle" class="nowrap" style="min-width:880px;">
		<div id="middle_left" style="width:8px;" class="fl">
			<iframe src="leftImage.do" id="leftFrame" name="leftFrame" frameborder="0" scrolling="no" title="leftFrame" marginwidth="0" marginheight="0" style="width: 100%"></iframe>		
		</div>		
		<div id="middle_right" style="width:9px;" class="fr">
			<iframe src="rightImage.do" id="rightFrame" name="rightFrame" frameborder="0" scrolling="no"  title="rightFrame" marginwidth="0" marginheight="0" style="width: 100%"></iframe>
		</div>
		<div id="middle_main" style="width:98%;border:0px solid red" class="fr">
			<iframe src="workspace.do" id="mainframe" name="mainframe" frameborder="0" scrolling="auto" marginwidth="0" marginheight="0" style="width: 100%;"></iframe>
		</div>
		<div class="clear"></div>
	</div>
	<div id="foot" style="min-width: 880px;">
		<iframe src="foot.do" id="bottomframe"	name="bottomframe" 	frameborder="0" scrolling="no" marginwidth="0" marginheight="0" style="width:100%;height:50px"></iframe>
	</div>
<script type="text/javascript">
	 	$("body").css({"overflow":"hidden"}).attr("scroll","no");
		var topFrameHeight = $("#top").outerHeight();
		var bottomframeHeight = $("#bottomframe").outerHeight();
		var screenHeight = $(document).height();
		var mainFrameHeight = screenHeight-topFrameHeight-bottomframeHeight;
		$("#middle iframe").height(mainFrameHeight).css("min-height","450px");
		
		window.onresize=function(){
			var screenHeight = $(window).height();
			//var screenWidth = $(document).width();
			var mainFrameHeight = screenHeight-topFrameHeight-bottomframeHeight;
			$("#middle iframe").height(mainFrameHeight);						
			//$("#middle_main").width(screenWidth-27);
		}
	</script>
</div>
</form>
</body>
</html>
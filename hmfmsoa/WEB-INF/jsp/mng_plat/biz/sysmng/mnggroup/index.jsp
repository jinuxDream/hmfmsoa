<%@ page language="java" contentType="text/html; charset=GBK"
  pageEncoding="GBK"%>
<%@ include file="/jsp/commons/taglibs.jsp"%>
<%@page import="fd.commons.jdbc.Result"%>
<%
  Result rsGroup = (Result) request.getAttribute("rsGroup");
	String groupcode = request.getParameter("groupcode");
	if(groupcode==null)
		groupcode="";
  %>
<html>
  <head>
    <%@ include file="/jsp/commons/meta.jsp" %>
    <script language="javascript">
    /* ��ҳ��ĳ�ʼ���� �����������У����û�г�ʼ���������в���Ҫд�κδ��롣*/
    $(document).ready(function(){	
    	$(".btn").PicButton();
    	var bar=$(".btn_toolbar").BtnToolBar();//��ʼ����ť������(��һ��button��ɵ�div,������htmlʵ����.btn_toolbar .btn .btn_condition ������ʽ���Ʋ��ܱ�֮�����������Ըı�)
    	var g=$(".grid1").datagrid({toolbar:bar});//��ִ�д��� g[0].grid.setBar(bar);	Ҳ����
    });	
    function onSelectRow(index,val,name){
    		$("#group_id").val(val);
    		$("#group_name").val(name);
    		
    	}

    </script>
  </head>
  <body>
    <form name="form1" method="post" action="">
      <input type="hidden" value="" name="group_id" id="group_id" />
      <input type="hidden" value="0" name="sign" />
       <input type="hidden" name="group_name" id="group_name"/>
      <table width="960" style="margin:0 auto" border="0" cellpadding="0" cellspacing="1">
       	<tr><td><div class="headline"><div class="headarrow">&nbsp;</div>���������</div></td></tr>
        <tr><td height="<%=_PAGEBLOCK_HEIGHT%>"></td></tr>
        <tr>
          <td width="100%" height="30" align="left" class="frameblue">
            <table border="0" cellpadding="0" cellspacing="0" width="100%">
             <tr>
          <td width="100%" align="left" class="frameblue">
          <div class="wrap-paramarea">
                  <div class="fl mr5 mt2">���������:&nbsp;
                  <input name="groupcode" type="text" class="input" value="<%=groupcode %>" /></div>
                  <div><button class="btn" type="button" value="6" btn_href="index.do"  mask>����ɸѡ </button></div>
               	<div class="clear"></div>
          	</div>	
                </td>
              </tr>
            </table>
          </td>
        </tr>
        <tr>
          <td width="100%"><div class="btn_toolbar" >
			<button class="btn" type="button" value="1" btn_href="addgroup.do"  mask>���� </button>
			<button class="btn" type="button" value="2" btn_href="getgroup.do" mask>�鿴 </button>
			<button class="btn" type="button" value="3" btn_href="editgroup.do" mask>�༭ </button>
			<button class="btn" type="button" value="4" btn_href="delgroup.do"  mask>ɾ��</button>
			<button class="btn" type="button" value="5" btn_href="allocate.do"  mask>����Ȩ��</button>
				<div class="btn_condition" >
					<div id='condition_null'>����</div>
					<div id='0'>����,�鿴,�༭,ɾ��,����Ȩ��</div>
				</div> 
			</div> 
        </tr>
        <tr>
          <td width="100%">
            <table width="100%" class="grid1">
              <tr height="24" align="center">
                <td width="5%">ѡ��</td>
                <td width="25%" align="center">���������</td>
                <td align="center">����������</td>
              </tr>

                <%	String rowClass = "";
					for(int i = 0; i < rsGroup.getRowCount(); i++)
					{
                  	String strName = rsGroup.getString(i, "group_name").trim();
                  	String group_id = rsGroup.getString(i, "group_id").trim();
                  %>
              <tr onclick="onSelectRow(<%=i%>,'<%=group_id%>','<%=strName %>')">
                <td width="30" height="24" align="center" btn_condition='0'><%=i+1%></td>
                  <td width="15%" height="23" align="center">
                    <%=group_id%>
                  </td>
                  <td width="32%" align="left">
                    &nbsp;&nbsp;<%=strName%>
                  </td>
                </tr>
                <%
                  }
                  %>
              </table>
          </td>
        </tr>
      </table>
    </form>
  </body>
</html>

      
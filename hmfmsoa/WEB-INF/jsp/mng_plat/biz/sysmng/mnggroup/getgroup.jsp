<%@ page language="java" contentType="text/html; charset=GBK"
  pageEncoding="GBK"%>
<%@page import="fd.commons.jdbc.Result"%>  
<%@ include file="/jsp/commons/taglibs.jsp"%>
<%
  Result rsGroup = (Result)request.getAttribute("rsGroup");
  %>
<html>
  <head>
    <%@ include file="/jsp/commons/meta.jsp" %>
    <script language="javascript">
    /* ��ҳ��ĳ�ʼ���� �����������У����û�г�ʼ���������в���Ҫд�κδ��롣*/
    $(document).ready(function(){	
    	$('.btn').PicButton();
    	var bar=$('.btn_toolbar').BtnToolBar();//��ʼ����ť������(��һ��button��ɵ�div,������htmlʵ����.btn_toolbar .btn .btn_condition ������ʽ���Ʋ��ܱ�֮�����������Ըı�)
    	var g=$('.grid1').datagrid({toolbar:bar,trselect:false});//��ִ�д��� g[0].grid.setBar(bar);	Ҳ����
    });	
    </script>
  </head>
  <body>
    <form name="form1" method="post" action="">
      <table width="680" align="center" border="0" cellpadding="0" cellspacing="1">
        <tr><td><div class="headline"><div class="headarrow">&nbsp;</div>������������ϸ��Ϣ</div></td></tr>
        <tr>
          <td valign="top">
                  <table width="100%"  class="grid1">
                  <thead>
			    	<tr>
			          <th colspan="2">��������ϸ��Ϣ</th>
			        </tr>
			    </thead>
			    <tr>
                      <td width="20%" align="left" nowrap>
                        ���������
                      </td>
                      <td width="80%" height="23" align="left" nowrap>
                        <%=rsGroup.getString(0, "group_id")%>
                    </tr>
                    <tr>
                      <td width="20%" align="left" nowrap>
                        ����������
                      </td>
                      <td width="80%" height="23" align="left" nowrap>
                        <%=rsGroup.getString(0, "group_NAME")%>
                      </td>
                    </tr>
                    <tr>
                      <td nowrap width="20%" align="left">
                        �� ��
                      </td>
                      <td height="23" align="left" nowrap><%=rsGroup.getString(0, "remark")%></td>
                    </tr>
                  </table>
          </td>
        </tr>
        <tr>
           <td align="center"><button class="btn" type="button" value="4" btn_href="index.do" >����</button>
           </td>
         </tr>
      </table>
    </form>
  </body>
</html>

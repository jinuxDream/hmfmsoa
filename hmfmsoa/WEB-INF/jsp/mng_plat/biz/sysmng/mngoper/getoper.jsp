<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<%@ include file="/jsp/commons/taglibs.jsp" %>
<%@page import="hmfms.util.DateUtil"%>
<%@page import="java.util.Map"%>
<%@page import="hmfms.util.ObjectUtil"%>
<%
Result retMp = (Result) request.getAttribute("retMp");
User user = ActionUtil.getUser(request);
%>

<%@page import="fd.commons.jdbc.Result"%>
<%@page import="hmfms.util.ActionUtil"%>
<%@page import="hmfms.web.User"%>
<%@page import="hmfms.services.codes.DeptType"%><html>
<%@ include file="/jsp/commons/meta.jsp" %>

<script language="javascript">
$(document).ready(function(){	
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();//��ʼ����ť������(��һ��button��ɵ�div,������htmlʵ����.btn_toolbar .btn .btn_condition ������ʽ���Ʋ��ܱ�֮�����������Ըı�)
	var g=$('.grid1').datagrid({toolbar:bar,trselect:false});//��ִ�д��� g[0].grid.setBar(bar);	Ҳ����
});	
function onSelectRow(index,val){}
function gotoPrint()
{
  if ( confirm( "ȷ��Ҫ��ӡ����Ա������" ) )
  {
    url = "printoper.do?operatorid=" + form1.operatorid.value;
    $.webUtil.open(url);
  }
}

/* ��ҳ��ĳ�ʼ���� �����������У����û�г�ʼ���������в���Ҫд�κδ��롣*/
function pageInit()
{
}
    </script>
  </head>
  <body>
  <form action="" method="post" name="form1">
  <input type="hidden" name="operatorid" value="<%=retMp.getString(0,"te_operid")%>" />
    <table width="960" style="margin:0 auto" border="0" cellpadding="0" cellspacing="1">
    <tr><td><div class="headline"><div class="headarrow">&nbsp;</div>����Ա�������ϸ��Ϣ</div></td></tr>
      <tr>
      	 <td>
            <div>
            	<%if("del".equals(request.getParameter("flag"))){%>
            		<button  type="button" class="btn" value="1" btn_href="deloperok.do" istip="1">ɾ��</button>
            	<%}else{ %>
					<button  type="button" class="btn" value="1" onClick="gotoPrint()" mask>��ӡ</button>
				<%}%>
				<button  type="button" class="btn" value="2" onClick="window.history.back()" mask>����</button>
			</div>
         </td>
      </tr>            
            <tr>
                <td width="100%">
                  		   <table class="grid1" width="100%" >
             	<thead>
             		<tr>
             			<th colspan="2">����Ա��ϸ��Ϣ</th>
             		</tr>
             	</thead>
                          <tr class="contentwhite">
                            <td width="25%" height="23" align="center">
                              �û���
                            </td>
                            <td width="75%" align="left">
                              &nbsp;<%=retMp.getString(0,"te_operid")%>
                            </td>
                          </tr>
                          <tr class="contentblue">
                            <td align="center"  height="23" >
                              ����Ա����
                            </td>
                            <td align="left">
                              &nbsp;<%=retMp.getString(0,"te_name")%>
                            </td>
                          </tr>
                          <tr class="contentwhite">
                            <td align="center" height="23" >
                              ��������
                            </td>
                            <td align="left">
                              &nbsp;<%=retMp.getString(0,"te_passwd")%>
                            </td>
                          </tr>
                          <tr class="contentblue">
                            <td align="center" height="23" >
                              ��������
                            </td>
                            <td align="left">
                              &nbsp;<%=retMp.getString(0,"te_unlock")%>
                            </td>
                          </tr>
                          <tr class="contentwhite">
                            <td align="center" height="23" >
                              ��λ���
                            </td>
                            <td align="left">
                              &nbsp;<%=DeptType.getValue(retMp.getString(0,"te_dept_type"))%>
                            </td>
                          </tr>
                          <tr class="contentblue">
                            <td align="center" height="23" >
                              �� ע
                            </td>
                            <td align="left" style='table-layout:fixed;word-break:break-all;'>
                              &nbsp;<%=retMp.getString(0,"te_remark")%>
                            </td>
                          </tr>
                        </table>
                    </tr>

                  </table>
    </form>
  </body>
</html>

<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>

<%@ include file="/jsp/commons/taglibs.jsp" %>
<%@page import="fd.commons.jdbc.Result"%>
<%
  Result rsMenu = (Result) request.getAttribute("rsMenu");
  Result rsSubMenu = (Result) request.getAttribute("rsSubMenu");
  String level = rsMenu.getString(0, "menu_level");
  boolean isFact = MenuFunLevel.JuTiJiaoYi.toString().equals(level);
  //ȡ�˵�����,����˵�����Ϊ���彻�׵�������޸����ϼ��˵�������ϵ,�����Ϊ���彻��,���������˵�����,ֻ�ܱ���˵����롢�˵����ơ�����
  %>

<%@page import="hmfms.services.codes.MenuFunLevel"%><html>
<%@ include file="/jsp/commons/meta.jsp" %>

<script type="text/javascript">
$(document).ready(function(){	
	$('.btn').PicButton();
	var bar=$('.btn_toolbar').BtnToolBar();//��ʼ����ť������(��һ��button��ɵ�div,������htmlʵ����.btn_toolbar .btn .btn_condition ������ʽ���Ʋ��ܱ�֮�����������Ըı�)
	var g=$('.grid1').datagrid({toolbar:bar,trselect:false});//��ִ�д��� g[0].grid.setBar(bar);	Ҳ����
});	
function onSelectRow(index,val){}
function gotoindex()
{
  cleanData();
  $.webUtil.submit("index.do");
}
function cleanData()
{
  var inptArray = document.getElementsByTagName("input");
  var retVal = "";
  for (var i = 0; i < inptArray.length; i++)
  {
    inptArray[i].value=retVal;
  }
}
</script>
<body>
<form name="form1" method="post" action="">
<input type="hidden" id="selectedUserid" value="" /> 
       <table width="960" style="margin:0 auto" border="0" cellpadding="0" cellspacing="1">
        <tr><td><div class="headline"><div class="headarrow">&nbsp;</div>�˵�������༭</div></td></tr> 
              <tr>
                <td colspan="2">
                   <table class="grid1" width="100%" >
               	<thead>
             		<tr>
             			<th colspan="2">�˵������б�</th>
             		</tr>
             	</thead>
                </td>
              </tr>
              <tr>
                <td width="20%" height="23" align="left" nowrap>
                  �˵�����
                </td>
                <td width="80%" height="23" align="left" nowrap>
                  <input type="text" class="text" size="20" name="menu_id" maxlength="20" value="<%=rsMenu.getString(0,"menu_id") %>" readonly title="�˵�����"/>
                </td>
              </tr>
              <tr>
                <td width="20%" height="23" align="left" nowrap>
                  �˵�����
                </td>
                <td width="80%" height="23" align="left" nowrap>
                  <input type="text" class="text" size="20" name="menu_name" maxlength="20" value="<%=rsMenu.getString(0,"menu_name") %>" title="�˵�����"/>
                  <span>
                    <font color="red">
                      *
                    </font>
                  </span>
                </td>
              </tr>
              <tr>
                <td width="20%" height="23" align="left" nowrap>
                  ���ܼ���
                </td>
                <td width="80%" height="23" align="left" nowrap>
                  <select name="menu_level" disabled v_empty="1">
                    <option value="<%=level%>" selected>
                      <%=MenuFunLevel.getValue(level)%>
                    </option>
                  </select>
                </td>
              </tr>
              <tr>
                <td width="20%" height="23" align="left" nowrap>
                  �˵�λ�����
                </td>
                <td width="80%" height="23" align="left" nowrap>
                  <input type="text" class="emptytext" size="10" name="pos_code" maxlength="10" value="<%=rsMenu.getString(0, "pos_code") %>" title="�˵�λ�����"/>
                  ���ڲ˵�����
                </td>
              </tr>
              <%
                if (isFact)//���彻��
                {
                	String thisParent = rsMenu.getString(0, "parent_id");//��ǰ�������˵�
                %>
              <tr>
                <td width="20%" height="23" align="left" nowrap>
                  ���˵�����
                </td>
                <td height="23" align="left" nowrap>
                  <select name="parent_id">
                    <%
                      for (int i = 0; i < rsSubMenu.getRowCount(); i++)
                      {
                      	String parent = rsSubMenu.getString(i, "menu_id");
                      	String name = rsSubMenu.getString(i,"menu_name");
                      	if (thisParent.equals(parent))
                      	{
                      %>
                    <option value="<%=parent%>" selected>
                      <%=name%>
                    </option>
                    <%
                      } else
                      {
                      	%>
                    <option value="<%=parent%>">
                      <%=name%>
                    </option>
                    <%
                      }
                      }
                      %>
                  </select>
                </td>
              </tr>
              <tr>
                <td width="20%" height="23" align="left" nowrap>
                  �����ҳ
                </td>
                <td width="80%" height="23" align="left" nowrap>
                  <input type="text" class="emptytext" size="40" name="url_link" maxlength="128" value="<%=rsMenu.getString(0,"url_link")%>" title="�����ҳ"/>
                </td>
              </tr>
              <%
                }
                %>
              <tr>
                <td nowrap height="23" width="20%" align="left">
                  �� ��
                </td>
                <td nowrap height="23" width="80%" align="left">
                  <input type="text" class="emptytext" size="40" name="menu_desc" maxlength="512" value="<%=rsMenu.getString(0,"menu_desc")%>" title="����"/>
                </td>
              </tr>
            </table>
              <table width="100%" border="0" cellspacing="0" cellpadding="5">
                <tr>
		 <td align="center">
			<div  >
				<button type="button" class="btn"  value="1" btn_href="editmenuok.do" istip="1" mask >����</button>
				<button type="button" class="btn"  value="2" onclick="gotoindex()"  mask >����</button>
				</div>
			
		</td>
	</tr>
            </table>
          </td>
        </tr>
      </table>				
  </form>
</body>
  
</html>

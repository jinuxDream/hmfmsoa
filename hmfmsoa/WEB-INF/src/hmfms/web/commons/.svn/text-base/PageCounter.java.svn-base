package hmfms.web.commons;

import javax.servlet.http.HttpServletRequest;

import fd.commons.jdbc.PageData;

/**
 * 分页管理
 * @author 徐超
 * 2007-3-1
 */
public class PageCounter extends PageData
{
	public static String HTML_CURR_PAGE_NAME="HTML_CURR_PAGE_NAME";
	public static String HTML_TOTAL_SIZE_NAME="HTML_TOTAL_SIZE_NAME";
	public static String HTML_PAGE_SIZE_NAME="HTML_PAGE_SIZE_NAME";
	public static String HTML_PAGE_FLIP_NAME="HTML_PAGE_FLIP_FLAG";
	public static String HTML_PAGE_JUMPER_NAME="HTML_PAGE_JUMPER_NAME";
	public static String HTML_PAGE_GOTO_FUNCNAME="HTML_PAGE_GOTO_PAGE";
	private boolean isFlipPage=false; 
	/**
	 * 在客户端提交后，可以在action类中使用此构造函数生成一个PageCounter实例，并使用此实例为参数，到后台分页查询数据库
	 * @param request
	 */
	public PageCounter(HttpServletRequest request){
		String strCurrPage = request.getParameter(HTML_CURR_PAGE_NAME);
		String strTotalSize = request.getParameter(HTML_TOTAL_SIZE_NAME);
		String strPageSize = request.getParameter(HTML_PAGE_SIZE_NAME);
		String strFlipFlag = request.getParameter(HTML_PAGE_FLIP_NAME);
		if(strCurrPage == null|| strCurrPage.trim()=="") strCurrPage = "1";	
		if(strTotalSize == null|| strTotalSize.trim()=="") strTotalSize ="0";	
		if(strPageSize == null|| strPageSize.trim()=="") strPageSize =Integer.toString(DEFAULT_PAGE_SIZE);	
		if(HTML_PAGE_FLIP_NAME.equals(strFlipFlag)) {
			currPage=Integer.parseInt(strCurrPage);
			isFlipPage=true;
		}else {
			currPage=1;
			isFlipPage=false;
		}
		totalSize=Integer.parseInt(strTotalSize);
		pageSize=Integer.parseInt(strPageSize);
	}
	/**
	 * 判断是否是翻页动作请求
	 * @return
	 */
	public boolean isFlipPage(){
		return isFlipPage;
	}
	public String getPageSummaryInfo(){
		StringBuffer summrayInfo=new StringBuffer();
		summrayInfo.append("第").append(getPageCount()==0?0:currPage).append("页/共").append(getPageCount()).append("页&nbsp;&nbsp;");
		summrayInfo.append("总计").append(totalSize).append("条记录&nbsp;&nbsp;");
		return summrayInfo.toString();
	}
	
	/**
	 * 取得控制分页用的html代码字符串。包括三部分
	 * (1)分页概要信息：第i页/共n页  总共m条记录
	 * (2)分页控制信息：首页  上一页  下一页  末页 　跳至＿＿页
	 * @param formName　翻页时，翻页动作要提交查询时，提交的form名称,例如使用"form1"
	 * @param Url　翻页时，翻页动作要提交查询时，提交form的action的URL，例如使用"/jsp/list.do?param=value"
	 * @return
	 */
		public String getPageHtml(String formName,String Url){
			StringBuffer strHtml = new StringBuffer();
			StringBuffer hrefF=new StringBuffer();
			StringBuffer hrefP=new StringBuffer();
			StringBuffer hrefN=new StringBuffer();
			StringBuffer hrefE=new StringBuffer();
			StringBuffer hrefJ=new StringBuffer();
			//设置参数的隐藏域参数。
			StringBuffer hiddenParam=new StringBuffer();
			hiddenParam.append("<input type='hidden' name='").append(HTML_CURR_PAGE_NAME).append("' value='").append(currPage).append("' />\n");
			hiddenParam.append("<input type='hidden' name='").append(HTML_TOTAL_SIZE_NAME).append("' value='").append(totalSize).append("' />\n");
			hiddenParam.append("<input type='hidden' name='").append(HTML_PAGE_SIZE_NAME).append("' value='").append(pageSize).append("' />\n");
			hiddenParam.append("<input type='hidden' name='").append(HTML_PAGE_FLIP_NAME).append("' value='' disabled />\n");
			strHtml.append(hiddenParam.toString());
			
//			设置翻页超链执行的javascript函数
			StringBuffer scrptFun=new StringBuffer();
			scrptFun.append("<script language=\"javascript\">\n");
			scrptFun.append(" function ").append(HTML_PAGE_GOTO_FUNCNAME).append(" (toPage) {\n");
			scrptFun.append(" var  numPar=/^\\d+$/ \n");
			scrptFun.append(" if(!numPar.test(toPage) || toPage<1||toPage>").append(getPageCount()).append(" ) { alert('输入的跳转页码不存在!');return false;}\n");
			scrptFun.append(formName).append(".elements[\"").append(HTML_CURR_PAGE_NAME).append("\"].value =toPage;\n");
			scrptFun.append(formName).append(".elements[\"").append(HTML_PAGE_FLIP_NAME).append("\"].disabled =false;\n");
			scrptFun.append(formName).append(".elements[\"").append(HTML_PAGE_FLIP_NAME).append("\"].value =\"").append(HTML_PAGE_FLIP_NAME).append("\";\n");
			//scrptFun.append(formName).append(".action =\"").append(Url).append("\";\n");
//			scrptFun.append(formName).append(".submit();\n");
			scrptFun.append("$.webUtil.submit(\"").append(Url).append("\", {ischeck:false});\n");
			scrptFun.append("}</script>\n");
			strHtml.append(scrptFun);
			
			
//			设置显示分页概要信息的htmlcode
			strHtml.append(getPageSummaryInfo());
//			设置翻页超链文本
			String firstTitle="首页";String prevTitle="上一页";String nextTitle="下一页";String endTitle="末页";String jumperTitle="跳至:";
			hrefF.append("<a href='#' onClick='").append(HTML_PAGE_GOTO_FUNCNAME).append("(1);return false'></a>");
			hrefP.append("<a href='#' onClick='").append(HTML_PAGE_GOTO_FUNCNAME).append("(").append(currPage<=1?1:currPage-1).append(");return false'></a>");		
			hrefN.append("<a href='#' onClick='").append(HTML_PAGE_GOTO_FUNCNAME).append("(").append(currPage>=getPageCount()?getPageCount():currPage+1).append(");return false'></a>");		
			hrefE.append("<a href='#' onClick='").append(HTML_PAGE_GOTO_FUNCNAME).append("(").append(getPageCount()>0?getPageCount():1).append(");return false'></a>");		
			strHtml.append("&nbsp;&nbsp;").append(currPage<=1?firstTitle:hrefF.insert(hrefF.indexOf("</a>"),firstTitle).toString());
			strHtml.append("&nbsp;&nbsp;").append(currPage<=1?prevTitle:hrefP.insert(hrefP.indexOf("</a>"),prevTitle).toString());
			strHtml.append("&nbsp;&nbsp;").append(currPage>=getPageCount()?nextTitle:hrefN.insert(hrefN.indexOf("</a>"),nextTitle).toString());
			strHtml.append("&nbsp;&nbsp;").append(currPage>=getPageCount()?endTitle:hrefE.insert(hrefE.indexOf("</a>"),endTitle).toString());
			StringBuffer jumper=new StringBuffer();
			//设置直接跳转用的html代码
			hrefJ.append("<a href='#' onClick='javascript:").append(HTML_PAGE_GOTO_FUNCNAME).append("(").append(formName).append(".").append(HTML_PAGE_JUMPER_NAME).append(".value);return false'></a>");		
			strHtml.append("\n&nbsp;&nbsp;").append(getPageCount()<2?jumperTitle:hrefJ.insert(hrefJ.indexOf("</a>"),jumperTitle).toString());
			jumper.append("<input type='text' name='").append(HTML_PAGE_JUMPER_NAME).append("' size='4' class='emptytext_R' value='' ");
			jumper.append(" onkeydown='javascript: if(event.keyCode==13){").append(HTML_PAGE_GOTO_FUNCNAME).append("(this.value);return false;}'>");
			strHtml.append(jumper);

			return strHtml.toString();
			
		}
}

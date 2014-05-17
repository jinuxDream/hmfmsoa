/**
 * 
 */
package hmfms.base;

import hmfms.util.ObjectUtil;
import hmfms.util.RequestUtil;
import hmfms.util.StringUtil;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import fd.commons.web.ActionResult;
import fd.exception.BusinessException;

/**
 * @author zhangcs
 *
 */
public class ActionResultHmfms extends ActionResult {
	
	private static String GLOB_PAGE_OK="/jsp/commons/pageok.jsp";
	private static String LAST_REQUEST_URL="last_request_url";
	public static String GOBACK_URL="goback_url";
	public static String TOOL_BUTTONS="tool_buttons";
	public static String MESSAGE_PAGETITLE="message_pagetitle";
	public static String MESSAGE_CONTENT="message_content";
	public static String MESSAGE_DETAIL="message_detail";
	public static String IS_CLOSE="is_close";	//"0"表示显示"返回"按钮而不显示"关闭"按钮,"1"表是关闭弹窗，并刷新打开窗口。"2"表示返回首页
	public static String BACK_HOME_URL="back_home_url";	//审核退回公共action传进来的功能模块首页返回路径，如果去goback_url会去找公共action
	public static ActionResultHmfms  toPageOkWithParam(HttpServletRequest request, Map<Object, Object> mpParam,boolean isForward){
		ActionResultHmfms action=new ActionResultHmfms();
//		则需要设置pageok需要的参数信息。pageok一共需要5个参数：页面标题，提示消息，消息详细信息，上一个请求的URL ,返回URL,其他按钮组
		//1、页面标题
		String message_pagetilte=request.getAttribute(MESSAGE_PAGETITLE)==null?"":request.getAttribute(MESSAGE_PAGETITLE).toString();
		if(ObjectUtil.isEmpty(message_pagetilte)) message_pagetilte=request.getParameter(MESSAGE_PAGETITLE);
		if(ObjectUtil.isEmpty(message_pagetilte)) message_pagetilte="操作成功";
		//2、提示消息
		String message_content=request.getAttribute(MESSAGE_CONTENT)==null?"":request.getAttribute(MESSAGE_CONTENT).toString();
		if(ObjectUtil.isEmpty(message_content)) message_content=request.getParameter(MESSAGE_CONTENT);
		if(ObjectUtil.isEmpty(message_content)) message_content="操作成功！";
		//3、消息详细信息
		String message_detail=request.getAttribute(MESSAGE_DETAIL)==null?"":request.getAttribute(MESSAGE_DETAIL).toString();
		if(ObjectUtil.isEmpty(message_detail)) message_detail=request.getParameter(MESSAGE_DETAIL);
		if(ObjectUtil.isEmpty(message_detail)) message_detail=" ";
		//4、封装返回按钮//取得需要返回的URL，如果没有设置时，缺省为请求模块的index.do
		String lastRequestURL=request.getRequestURI();
		String urlPrefix=lastRequestURL.substring(0,lastRequestURL.lastIndexOf("/")+1);
		String goback_url=request.getAttribute(GOBACK_URL)==null?"":request.getAttribute(GOBACK_URL).toString();
		if(ObjectUtil.isEmpty(goback_url)) goback_url=request.getParameter(GOBACK_URL);
		if(ObjectUtil.isEmpty(goback_url)) goback_url=urlPrefix+"index.do";
		String tool_buttons="返回,"+goback_url;
		
		/*2012-07-12 HuYM start*/
		//封装关闭按钮 关闭弹出窗口，并使父窗口页面跳转到该模块首页
		String is_close = request.getAttribute(IS_CLOSE)==null?"":request.getAttribute(IS_CLOSE).toString();
		if(ObjectUtil.isEmpty(is_close)) is_close = request.getParameter(IS_CLOSE);
		if(ObjectUtil.isEmpty(is_close)) is_close = "0";
		
		String back_home_url = "";
		if ("2".equals(is_close)) {
			back_home_url = request.getAttribute(BACK_HOME_URL)==null?"":request.getAttribute(BACK_HOME_URL).toString();
			if(ObjectUtil.isEmpty(back_home_url)) back_home_url = request.getParameter(BACK_HOME_URL);
		}
		/*如果是3，强制涮洗某个页面*/
		if("3".equals(is_close)) {
			back_home_url = request.getAttribute(BACK_HOME_URL)==null?"":urlPrefix+request.getAttribute(BACK_HOME_URL).toString();
			if(ObjectUtil.isEmpty(back_home_url)) back_home_url = urlPrefix+request.getParameter(BACK_HOME_URL);
		}
//		back_home_url = back_home_url.substring(0, back_home_url.lastIndexOf("/")+1);
//		back_home_url = back_home_url+"index.do";
		if("2".equals(is_close)&&ObjectUtil.isEmpty(back_home_url)) throw new BusinessException("未找到审核退回后跳转的页面。"); 
		/*end*/
		
		//5、封装其他按钮组（初返回以外的其他按钮） 
		String other_buttons=request.getAttribute(TOOL_BUTTONS)==null?"":request.getAttribute(TOOL_BUTTONS).toString();
		if(ObjectUtil.isEmpty(other_buttons)) other_buttons=request.getParameter(TOOL_BUTTONS);
		if(ObjectUtil.isEmpty(other_buttons)) other_buttons="";
		
		//6、组装所有的按钮
		tool_buttons= tool_buttons+(ObjectUtil.isEmpty(other_buttons)?"":"|"+other_buttons);
		//7、将所需的参数全部封装到URL中。
		StringBuffer paramURL = new StringBuffer();//
		if(isForward==false || ObjectUtil.isEmpty(request.getParameter(MESSAGE_PAGETITLE))){
			paramURL.append(MESSAGE_PAGETITLE).append("=").append(message_pagetilte).append("&");
		}
		if(isForward==false || ObjectUtil.isEmpty(request.getParameter(MESSAGE_CONTENT))){
			paramURL.append(MESSAGE_CONTENT).append("=").append(message_content).append("&");
		}
		if(isForward==false || ObjectUtil.isEmpty(request.getParameter(MESSAGE_DETAIL))){
			paramURL.append(MESSAGE_DETAIL).append("=").append(message_detail).append("&");
		}
		if(isForward==false || ObjectUtil.isEmpty(request.getParameter(IS_CLOSE))) {
			paramURL.append(IS_CLOSE).append("=").append(is_close).append("&");
		}
		if ("2".equals(is_close)) {
			if(isForward==false || ObjectUtil.isEmpty(request.getParameter(BACK_HOME_URL))){
				paramURL.append(BACK_HOME_URL).append("=").append(back_home_url).append("&");
			}
		}
		if ("3".equals(is_close)) {
			if(isForward==false || ObjectUtil.isEmpty(request.getParameter(BACK_HOME_URL))){
				paramURL.append(BACK_HOME_URL).append("=").append(back_home_url).append("&");
			}
		}
		if(isForward==false || ObjectUtil.isEmpty(request.getParameter(LAST_REQUEST_URL))){
			paramURL.append(LAST_REQUEST_URL).append("=").append(lastRequestURL).append("&");
		}
		
		if(isForward==false ){
			paramURL.append(TOOL_BUTTONS).append("=").append(tool_buttons).append("&");
		} else if(ObjectUtil.isEmpty(request.getParameter(TOOL_BUTTONS))){
			paramURL.append(TOOL_BUTTONS).append("=").append(tool_buttons).append("&");
		}else  paramURL.append(GOBACK_URL).append("=").append(goback_url).append("&");
		//封装map中的参数
		if(mpParam!=null&&  isForward==false ){
			Iterator<Object> it =mpParam.keySet().iterator();
			while (it.hasNext()){
				String key=(String)it.next();
				Object value=mpParam.get(key);
				paramURL.append(key).append("=").append(value!=null?value:" ").append("&");
			}
		}
		
		if(paramURL.length()>0) paramURL.deleteCharAt(paramURL.length()-1);
		String toURL=GLOB_PAGE_OK+RequestUtil.encodeFullURL(paramURL.toString());
		action.setForwardUri(toURL , isForward);
		return action;		
	}
	public static ActionResultHmfms  toPageOk(HttpServletRequest request,boolean isForward){
		return ActionResultHmfms.toPageOkWithParam(request, null, isForward);
	}
	@SuppressWarnings("unchecked")
	public  static String  convertParams2Html(HttpServletRequest request){
		StringBuffer html=new StringBuffer();
		Enumeration<String>  en= request.getParameterNames();
		while( en.hasMoreElements()){
			String key=en.nextElement().toString();
			if(MESSAGE_PAGETITLE.equals(key)||MESSAGE_CONTENT.equals(key)||MESSAGE_DETAIL.equals(key)||TOOL_BUTTONS.equals(key)) continue;
			String [] values=request.getParameterValues(key);
			for (int i = 0;i<values.length; i ++){
				html.append("<input type='hidden' name='").append(key).append("'");
				html.append(" value='").append(values[i]).append("'").append(">\r\n");
			}
		}
		return html.toString();
	}
	/**
	 * 将request对象中的按钮信息，格式化成按钮html
	 * @param request
	 * @return
	 */
	public  static String  convertButton2Html(HttpServletRequest request ){
		//上一个请求的URL
		StringBuffer html=new StringBuffer();

		//上一个请求的URL
		String last_request_url=request.getParameter(ActionResultHmfms.LAST_REQUEST_URL);
		if(ObjectUtil.isEmpty(last_request_url)) throw new BusinessException("未取到上次请求的URL");
		String urlPrefix=last_request_url.substring(0,last_request_url.lastIndexOf("/")+1);
		//要返回的URL
		String goback_url=request.getParameter(ActionResultHmfms.GOBACK_URL);
		if(ObjectUtil.isEmpty(goback_url)) goback_url=urlPrefix+"index.do";
		String tool_buttons = (String)request.getParameter(ActionResultHmfms.TOOL_BUTTONS);
		//if(ObjectUtil.isEmpty(tool_buttons)) tool_buttons="返回,"+urlPrefix+"index.do";
		//else if(tool_buttons.indexOf("返回")<0) tool_buttons="返回,"+urlPrefix+"index.do|"+tool_buttons;
		if(ObjectUtil.isEmpty(tool_buttons)) tool_buttons="返回,"+urlPrefix+goback_url;
		else if(tool_buttons.indexOf("返回")<0) tool_buttons="返回,"+urlPrefix+goback_url+"|"+tool_buttons;
		if(!ObjectUtil.isEmpty(tool_buttons)){
			String btnArry[] = StringUtil.split(tool_buttons,"|");
			for(int i=0;i<btnArry.length;i++){
				System.out.println(tool_buttons);
				String btnNameOrUrl[] = StringUtil.split(btnArry[i],",");
				if(btnNameOrUrl.length!=2) throw new BusinessException("按钮信息不完整，请用【名称,URL|名称,URL】格式提供按钮信息"+btnArry[i]);
				String btnName=btnNameOrUrl[0];
				String btnUrlOpen=btnNameOrUrl[1];
				String btnUrlIsOpen[] = StringUtil.split(btnUrlOpen, "-");
				String btnUrl="";
				if(btnUrlIsOpen.length==2){
					btnUrl = btnUrlIsOpen[1];
				}else{
					btnUrl = btnUrlOpen;
				}

				if(btnUrl.indexOf(request.getContextPath())<0){
					if(btnUrl.startsWith("/jsp")){
						btnUrl=request.getContextPath()+btnUrl;
					}else 
						btnUrl=urlPrefix+btnUrl;
				}
				if(btnUrlIsOpen.length==2){
					html.append("<button class='btn' type='button' value='' istip='0' popup='1' issubmit='1'  btn_href='");
					html.append(btnUrl).append("'>").append(btnName).append("</button>&nbsp");
				}else{
					html.append("<button class='btn' type='button' value='' istip='0' btn_href='");
					html.append(btnUrl).append("'>").append(btnName).append("</button>&nbsp");
				}
			}
		}
		return html.toString();
	}
}

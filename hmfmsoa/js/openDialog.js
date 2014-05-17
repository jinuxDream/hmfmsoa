/*
 * <p>标    题: jQuery WEB-UI-openDialog</p>
 * <p>描    述: </p>
 * <p>版    权: Copyright (c) 2011</p>
 * <p>公    司: 上海泓智信息科技有限公司</p>
 * <p>创建时间: 2011-09-01 上午10:47:52</p>
 * @author Emile Yi
 * @version 1.2
 * 更新openWindow，打开一个ECMAScript核心的窗口（浏览器自带），适用于窗体传值，子窗体不用考虑
 * 父窗体的DOM，只需要将参数传回给父窗体。
 */
(function($){	
    $.OpenDialogs={		
		dialogs:{},					//实体集合
		params:{},
				
		//选项设置
		entity:{
			$dialog:"",				//jQuery对象
			setting:{
				id:"",				//ID
				type:"text",		//dialog类型
				title:"我的标题",	//标题
				content:"我的内容",	//内容
				width:300,			//宽
				height:150,			//高				
				isOpen:false,		//是否开启
				model:true,			//模式窗体
				animate:null,		//设置动画
				appendTo:"body", 	//插入位置
				callback:function(){}
			}
		},
		
		/*构造函数*/
		constructor:function(setting,callback){			
			var _setting = this.entity.setting;
			if(!$.isEmptyObject(setting)){
				_setting = $.extend(false,_setting,setting);
			}
			
			//模式窗体
			if(_setting.model && this.dialogs[_setting.id]){return;}					

			//设置窗体已打开
			_setting.isOpen =true;

			//取得dialog ID
			if(!setting || !setting.id){
				var ind = $(".modalDialog").length-1;
				if(ind>=0){												
					var id = (parseInt($(".modalDialog:eq("+ind+")").parent().attr("id"))+1)+"_dialog";
					_setting.id = id;						
				}else{
					_setting.id="0_dialog";
				}					
			}

			var temp_div="";
			temp_div+="<div>";
			if(_setting.model){
				temp_div+="<div id=\"modalDialogBg\" class=\"modalDialogBg\" style=\"height:"+$(document).height()+"px;filter:alpha(opacity=30);opacity:0.3;\"></div>";
			}
			temp_div+="<div id=\"modalDialog\" class=\"modalDialog\">";
			temp_div+="<div class=\"title\"><h4></h4><span title=\"关闭\"></span></div>";
			temp_div+="<div class=\"content\"></div>";
			temp_div+="</div>";
			temp_div+="</div>";
			var _dialog = $(temp_div).attr("id",_setting.id);
						
			//设置CSS
			_dialog.find("#modalDialog").css({
					width:_setting.width,
					left:($(window).width()-parseInt(_setting.width))/2+"px",
					top:($(window).height()-parseInt(_setting.height))/3+"px"
				});			
			_dialog.find("#modalDialog .content").css({height:_setting.height});
			
			//设置动作
			if(_setting.animate=="top"){		
				_dialog.find("#modalDialogBg").animate({opacity:"0.5"},"normal");
				_dialog.find("#modalDialog").css({top:($(document).scrollTop()-parseInt(_setting.height))+"px"})
					.animate({top:(($(document).height()-(parseInt(_setting.height)))/4)+"px"},"normal");
			}else if(_setting.animate=="left"){
				_dialog.find("#modalDialogBg").animate({opacity:"0.5"},"normal");
				_dialog.find("#modalDialog").css({left:(0-parseInt(_setting.height))+"px"})
					.animate({left:(($(document).width()-(parseInt(_setting.width)))/2)+"px"},"normal");
			}else if(!$.isEmptyObject(_setting.animate)&&$.type(_setting.animate)=="object"){
				_dialog.find("#modalDialog").animate(_setting.animate);
			}
			
			//设置title
			_dialog.find(".title h4").text(_setting.title);
			
			//处理内容
			switch(_setting.type){			
			case "text":
				_dialog.find(".content").html(_setting.content);
				break;
			case "id":				
				_dialog.find(".content").html($("#"+_setting.content+"").html());
				$("#"+_setting.content+"").hide();
				break;
			case "iframe":
				_dialog.find(".content").html("<iframe src=\""+_setting.content+"?frameid="+_setting.id+"\" width=\"100%\" height=\""+(parseInt(_setting.height)-30)+"px"+"\" scrolling=\"auto\" frameborder=\"0\" marginheight=\"0\" marginwidth=\"0\"></iframe>");
				break;
			}

			//拖动事件
			_dialog.find(".modalDialog").simpleDrag({obj:".title",maxTop:true,alpha:0.8});	
			
			//绑定关闭事件
			_dialog.find("#modalDialog .title span").click(function(){			
				$.OpenDialogs.close(_setting);
			});
			
			//保存到集合
			_entity={$dialog:_dialog,setting:_setting};			
			this.dialogs[_setting.id]=_entity;			
			
			$(_setting.appendTo).append(_dialog);

			callback(_dialog);			
		},

		/*打开窗体*/
		open:function(setting){
			$.OpenDialogs.constructor(setting,function(dialog){
				dialog.find("#modalDialogBg").show();
				dialog.find("#modalDialog").show();				
			});			
		},

		/*关闭窗体*/
		close:function(setting){			
			var _setting = setting || {};
			var dialog = $("#"+_setting.id);
					
			if(_setting.animate=="top"){		
				dialog.find("#modalDialogBg").animate({opacity:"0.5"},"normal",function(){$(this).hide();});
				dialog.find("#modalDialog").animate({top:($(document).scrollTop()-parseInt(_setting.height))+"px"},"fast",function(){$(this).hide();dialog.remove();});
			}else if(_setting.animate=="left"){
				dialog.find("#modalDialogBg").animate({opacity:"0.5"},"normal",function(){$(this).hide();});
				dialog.find("#modalDialog").animate({left:(0-parseInt(_setting.height))+"px"},"fast",function(){$(this).hide();dialog.remove();});
			}else if(!$.isEmptyObject(_setting.animate)&&$.type(_setting.animate)=="object"){
				dialog.find("#modalDialogBg").animate({opacity:"0.5"},"normal",function(){$(this).hide();});
				dialog.find("#modalDialog").animate({"opacity":"hide"},function(){$(this).hide();dialog.remove();});
			}else{
				dialog.find("#modalDialogBg").hide();
				dialog.find("#modalDialog").hide();
				dialog.remove();  //不合理 需改进 原因 回调函数异步调用 
			}
			this.destroy(_setting.id);
			
			_setting.callback(this.params);
		},

		/*销毁*/
		destroy:function(id){			
			delete $.OpenDialogs.dialogs[id];			
		},
		
		callback:function(params){
			var frameid = frameElement.src.substring(frameElement.src.lastIndexOf("frameid")+8);
			window.parent.$.OpenDialogs.params=params;
			$(frameElement).parents("#"+frameid).find("span").click();
		}				
	};
	
	//开启浏览器窗口
	$.OpenWindow={
		win:null,
		params:{},
		open:function(setting){
			var _setting =  $.extend(false,{					
					content:"",			//url路径
					width:700,			//宽
					height:500,			//高
					top:50,				//顶边位置
					left:0,			//左边位置					
					autoClose:false,	//true:每次打开窗口时关闭前一次打开的窗口，这里只允许打开一个窗口。IE6以下不支持同名窗口，所以IE6以下默认true。									
					callback:function(){}
				},setting || {});			
			if($.support.leadingWhitespace && jQuery.browser.version=="6.0"){
				_setting.autoClose=true;
			}
			if(_setting.autoClose){				
				win.close();				
			}
			
			_setting.left=parseInt(($(window).width()-parseInt(_setting.width))/2)+150;		
							
			var sFeatures = "height="+_setting.height+","+"width="+_setting.width+","+"top="+_setting.top+","+"left="+_setting.left+",toolbar=no, menubar=no, scrollbars=yes, resizable=no, location=no, status=no";
			win = open(_setting.content,"newwin",sFeatures);
			$(win).focus();
			
			$("#opWin").remove();
			$(document.body).append($("<span id='opWin' style=\"display:none;\"></span>").click(function(){$.OpenWindow.close(_setting);$(this).remove();}));
		},
	
		close:function(_setting){	
			if(win){
				win.close();
				win=null;
				_setting.callback(this.params);				
			}			
		},

		callback:function(params){
			var opener = window.opener;			
			if(opener){				
				opener.$.OpenWindow.params=params;				
				opener.$("#opWin").click();
			}
		}		
	};

	/*调用开启窗体函数*/
	$.openDialog=function(setting,callback){				
		if(callback && $.type(callback)=="function"){
			setting.callback=callback;
		}
		if(setting.type=="window" || !setting.type){
			$.OpenWindow.open(setting);
		}else{
			$.OpenDialogs.open(setting);
		}		
	};

	/*关闭窗体函数*/
	$.closeDialog=function(params){
		$.OpenWindow.callback(params);
	};
})(jQuery);

/*
 * 拖动组件
 */
(function($) {
	$.fn.simpleDrag = function(setting){
		var dragObject = this;
		var dragObjectSelector = $(dragObject).selector;	//当前选择器
		var dragIndex = 0;
		var _dragObj = dragObject;	//拖动区域

		setting = jQuery.extend({
			obj: "self",	//拖动区域 默认本身
		   	alpha: 1 ,		//拖动时的透明度,默认不透明.
		   	maxTop:false,	//当前拖动对象停止拖动后z-index是否为最大，即在所有对象上面！ false | true
			fixed:true		//是否固定
	  	},setting);
		
		function drag(){
			//兼容IE6.0 7.0 8.0 ff		
			if(setting.fixed && !($.browser.msie && $.browser.version=="6.0")){
				$(dragObject).css({position:"fixed"});
			}else{
				$(dragObject).css({position:"absolute"});
			}
			if(!parseInt($(dragObject).css("left"))){$(dragObject).css("left","0");}
			if(!parseInt($(dragObject).css("top"))){$(dragObject).css("top","0");}
			
			//当选择器不是自身时获得拖动区域对象
			if(setting.obj != "self"){
				_dragObj = $(dragObject).children(setting.obj);
			}
			
			var _move=false;
			var _x,_y;
			var docTop=0;//显示区域top
			var docLeft=0;//显示区域left
			var docWidth=$(window).width();//显示区域宽 body	for IE
			var docHeight=$(window).height();//显示区域高
			var dragObjWidth,dragObjHeight;//拖动对象宽高
			
			$(_dragObj).click(function(){})
			.mousedown(function(e){				
				if(setting.obj == "self"){
					eventObj = $(this);
				}else{
					eventObj = $(this).parent(dragObjectSelector);
				}	
				dragIndex = $(dragObjectSelector).index(eventObj);
					
				dragObjWidth = $(dragObjectSelector+":eq("+dragIndex+")").outerWidth(true);
				dragObjHeight = $(dragObjectSelector+":eq("+dragIndex+")").outerHeight(true);								

				_move=true;
				_x=e.pageX-parseInt($(dragObjectSelector+":eq("+dragIndex+")").css("left"));
				_y=e.pageY-parseInt($(dragObjectSelector+":eq("+dragIndex+")").css("top"));
				$(dragObjectSelector+":eq("+dragIndex+")").fadeTo(20, setting.alpha);
				if(setting.maxTop){$(dragObjectSelector+":eq("+dragIndex+")").css("z-index",getMaxZIndex()+1)}
			});
			
			$(document).mousemove(function(e){
				if(_move){
					var x=e.pageX-_x;
					var y=e.pageY-_y;
					
					//锁定拖动范围
					if(y<=docTop){ y=0;}
					else if(y>=docHeight-dragObjHeight){y=docHeight-dragObjHeight;}
					
					if(x<=docLeft){ x=0;}
					else if(x>=docWidth-dragObjWidth){x=docWidth-dragObjWidth;}

					$(dragObjectSelector+":eq("+dragIndex+")").css({top:y,left:x});
				}
			})
			.mouseup(function(){
				_move=false;
				$(dragObjectSelector+":eq("+dragIndex+")").fadeTo("fast", 1);
			});			
		};	
		
		//获取最大z-index
		function getMaxZIndex(){
			var maxZ = 0;
			$("*").each(function(){
				if( maxZ < parseInt($(this).css("z-index"))){
					maxZ = parseInt($(this).css("z-index"));
				}
			})	
			return maxZ;
		};
	
		return drag();
	};
 })(jQuery);
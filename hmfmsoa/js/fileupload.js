/*
删除已经上传并存库的文件
参数：已经上传并存库的文件：附件序号
*/
function delfile(fileid)
{
	var curfilesid_arr = document.getElementsByName("curfilesid");//checkbox，存删除选择
	var size = curfilesid_arr.length;
	var xiansFile = "";
	
	var uploadfilesDiv = document.getElementById("dataUploadfilesDiv");

	var dd = uploadfilesDiv.innerHTML;
	var selectD = dd.split("<BR>");
	var deloperfiles = document.form1.deloperfiles.value;//记录需要删除的文件id，逗号分隔
	//下个循环是找到需要删除行的checkbox并打勾标记
	for(var i=0; i<size; i++)
	{
		
		var checkbox = curfilesid_arr[i];
		if(fileid == checkbox.value){
			checkbox.checked = !checkbox.checked;//如果选中过，则取消，即：点一次删除，点两次取消
		}
		if(checkbox.checked){//alert("i="+i);
			deloperfiles += checkbox.value+",";
			selectD[i]="";
		}else{
			xiansFile+=selectD[i]+"<BR>"
		}
	}
	
	//alert("deltmpfileindex_full="+deltmpfileindex_full.length);
	//var deltmpfileindex = document.form1.deloperfiles;
	uploadfilesDiv.innerHTML=xiansFile;
	//alert(xiansFile);
	recall.value=xiansFile;
	document.form1.deloperfiles.value = deloperfiles;
}

/*
删除刚刚上传的文件
参数：已经上传但未存库的文件：临时序号
*/
function deltmpfile(tmpfileid)
{
	var uploadfilesDiv = document.getElementById("uploadfilesDiv");
	var recall = document.getElementById("recall");
	var tmpfilesid_arr = document.getElementsByName("tmpfilesid");//checkbox，存删除选择
	var size = tmpfilesid_arr.length;
	var dd = uploadfilesDiv.innerHTML;
	var selectD = dd.split("<br>");
	var deltmpfileindex_full = "";
	var xiansFile = "";
	//下个循环是找到需要删除行的checkbox并打勾标记
	for(var i=0; i<size; i++)
	{
		var checkbox = tmpfilesid_arr[i];
		if(tmpfileid==checkbox.value){
			checkbox.checked = !checkbox.checked;//如果选中过，则取消，即：点一次删除，点两次取消
		}
		if(checkbox.checked){//alert("i="+i);
			//deltmpfileindex_full = deltmpfileindex_full+i+",";
			//alert(dd[i]);
			selectD[i]="";
			//alert(dd[i]);
		}else{
			xiansFile+=selectD[i]+"<br>"
		}
	}
	//dd = dd.substring(0, dd.length - 1);
	uploadfilesDiv.innerHTML=xiansFile;
	recall.value=xiansFile;
	//alert("deltmpfileindex_full="+deltmpfileindex_full.length);
	var deltmpfileindex = document.form1.deltmpfileindex;
	deltmpfileindex.value = deltmpfileindex_full.substring(0, deltmpfileindex_full.length-1);
}
// 修改081102 文件上传 结束
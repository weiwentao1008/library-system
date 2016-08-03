function showInfoForm() {
	var contentbox = document.getElementById("content-box");
	var contentboxcontent = document.createElement("div");
	var contentboxheader = document.createElement("div");
	contentbox.innerHTML = "";
	contentboxheader.setAttribute("class", "content-box-header");
	contentboxcontent.setAttribute("class", "content-box-content");
	contentboxheader.innerHTML = '<h3>设置</h3>'
			+ '<ul class="content-box-tabs">'
			+ ' <li><a href="#tab1" class="default-tab">个人信息</a></li>'
			+ '</ul>' + '<div class="clear">';
	contentboxcontent.innerHTML = ' <div class="tab-content default-tab" id="tab1" align="center">'
			+ '<form class="showform" id="showform" action="" method="post" onsubmit="return false;">'
			+ ' <fieldset>'
			+ '<!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->'
			+ '<p>'
			+ ' <label>账号</label>'
			+ ' <input class="text-input small-input" type="text" id="idshow" name="idshow" disabled="disabled" />'
			+ ' <!--<span class="input-notification error png_bg">Error message</span>--> '
			+ '</p>'
			+ ' <br />'
			+ '<p>'
			+ ' <label>姓名</label>'
			+ ' <input class="text-input small-input" type="text" id="nameshow" name="nameshow" />'
			+ ' <!--<span class="input-notification error png_bg">Error message</span>--> '
			+ '</p>'
			+ ' <br />'
			+ '<p>'
			+ '<label>性别</label>'
			+ ' <select id="sexshow" name="sexshow" class="small-input" >'
			+ '<option id="sexshow0" value="0">男</option>'
			+ '<option id="sexshow1" value="1">女</option>'
			+ ' </select>'
			+ '<br />'
			+ '</p>'
			+ '<p>'
			+ ' <label>年级</label>'
			+ ' <input class="text-input small-input" type="text" id="gradeshow" name="gradeshow"  disabled="disabled" />'
			+ ' <!--<span class="input-notification error png_bg">Error message</span>--> '
			+ '</p>'
			+ ' <br />'
			+ '<p>'
			+ ' <label>可借阅时间</label>'
			+ ' <input class="text-input small-input" type="text" id="timeshow" name="timeshow"  disabled="disabled" />'
			+ ' <!--<span class="input-notification error png_bg">Error message</span>--> '
			+ '</p>'
			+ ' <br />'
			+ '<p>'
			+ ' <label>可借阅本数</label>'
			+ ' <input class="text-input small-input" type="text" id="countshow" name="countshow"  disabled="disabled" />'
			+ ' <!--<span class="input-notification error png_bg">Error message</span>--> '
			+ '</p>'
			+ ' <br />'
			+ ' <p>'
			+ '<label>类型</label>'
			+ '  <select id="modeshow" name="modeshow" class="small-input" value="2">'
			+ ' <option id="modeshow0" value="0">读者</option>'
			+ '<option id="modeshow1" value="1" >图书管理员</option>'
			+ ' <option id="modeshow2" value="2">管理员</option>'
			+ ' </select>'
			+ ' <br />'
			+ ' </p>'
			+ ' <p>'
			+ '  <input class="button" onclick="changeinfo()" id="showsubmit" type="button" value="Submit" />'
			+ ' </p>' + '</fieldset>' + '  </form>' + ' </div>';
	contentbox.appendChild(contentboxheader);
	contentbox.appendChild(contentboxcontent);

}

function getInfo() {
				jQuery.ajax({
					url : 'user/myInfo',
					type : 'get',
					dataType : 'json',
					success : function(data) {
							showInfoForm();
							document.getElementById("idshow").setAttribute(
									"value", data.user._id);
							document.getElementById("nameshow").setAttribute(
									"value", data.user.name);
							document.getElementById("sexshow" + data.user.gender)
									.setAttribute("selected", "selected");
							document.getElementById("gradeshow").setAttribute(
									"value", data.user.grade + "级");
							document.getElementById("timeshow").setAttribute(
									"value", data.user.borrowtime + "天");
							document.getElementById("countshow").setAttribute(
									"value", data.user.count + "本");
							document.getElementById("modeshow" + data.user.type)
									.setAttribute("selected", "selected");
							$.tooltip('获取信息成功', 2000, true);
					},
					error : function(data) {
						$.tooltip('获取信息失败,检查是否登录', 2000, false);
					}
				});

}

function changeinfo() {
	var jsoninfo = $.toJSON($('.showform').serializeObject());  //
	 jQuery.ajax( {  
      url:'user/changeInfo',  
      type:'put',  
      contentType : 'application/json',  
      data: jsoninfo,  
      dataType:'json',
      success:function(data) {
    		  	$.tooltip('个人信息修改成功',2000,true);
			},  
      error: function(data) {  
         $.tooltip('个人信息修改失败,请重试',2000,false);
      }
  }); 
}

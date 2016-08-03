


$(function(){
    
	 
	
	 function getCookie(name)
     {
		 alert(document.cookie);
         cookie=document.cookie.split(';');
         alert(cookie);
         for(i=0;i<cookie.length;i++)
         {
             str=cookie[i].split('=');
             alert(str[0]);
             if(str[0]==name)
            {
            	 alert(str[1]);
                 return str[1];
            }
         }
         return null;
     }

	
	
	function CheckIsLogin()
			{
			var flag="false";
		 jQuery.ajax( {  
             url:'user/isLogin',
             type:'post',
             dataType:'json',
             success:function(data) {
           	  if(data.isLogin=="true")
				 flag="true";
			  else
                 $.tooltip('请先登录',2000,true);
				},  
             error: function(data) {  
                 alert("error")  ;
             }
         }); 
		 return flag;
			}
	
	/*设置已经登录的用户名*/
	
	/*if(CheckIsLogin()=="true")
	{
		var name= document.getElementById('readername');
		name
	}*/
	
            
            
    
    $('.dialog').click(
    		function()
    		{
    			var $el=$('.dialog');
    		    $el.hDialog();
    		});
    //提交并验证表单
    $('.subButton').click(function() {
        var $username = $('.username');
        var $password = $('.password'); 
        var $mode = $('.mode');
        if($username.val() == ''){
            $.tooltip('账号还没填呢...'); $username.focus();
        }else if($password.val() == ''){
            $.tooltip('密码还没填呢...'); $password.focus(); 
        }else{
            var jsonuserinfo = $.toJSON($('.loginform').serializeObject());  //
            jQuery.ajax( {  
                url:'user/login',  
                type:'post',  
                contentType : 'application/json',  
                data: jsonuserinfo,
                dataType:'json',
                success:function(data) {
                      document.getElementById("readername").innerHTML="";
                      document.getElementById("readername").innerHTML=data.loginname;
                      var borrowbookdiv=document.getElementById("borrowbookdiv");
                      var bookmanagediv=document.getElementById("bookmanagediv");
                      var setdiv=document.getElementById("setdiv");
                      borrowbookdiv.style.display="block";
                      setdiv.style.display="block";
                    if($mode.val()=="1")
                        bookmanagediv.style.display="block";
                        $.tooltip('登录成功，2秒后自动关闭',1000,true);
                        setTimeout(function(){ 
                            //$el.hDialog('close',{box:'#HBox'},'http://smwell.sinaapp.com/');  //也可以加跳转链接哦~
                        	$('.dialog').hDialog('close',{box:'#HBox'});
                        },1000);
                },  
                error: function(data) {  
                    $.tooltip('登录失败',2000,false);
                }
            }); 
        }
    });
            
	$('.loginout').click(function()
			{
		 jQuery.ajax( {  
            url:'user',
            type:'delete',
            dataType:'json',
            success:function(data) {
						$.tooltip('已注销',2000,true);
						document.getElementById("readername").innerHTML="";
	              		 document.getElementById("readername").innerHTML="游客";
	              		var borrowbookdiv=document.getElementById("borrowbookdiv");
	              		  var bookmanagediv=document.getElementById("bookmanagediv");
	              		  var setdiv=document.getElementById("setdiv");
	              		borrowbookdiv.style.display="none";
	              		bookmanagediv.style.display="none";
	              		setdiv.style.display="none";
	              		document.getElementById("content-box").innerHTML="";
				},  
            error: function(data) {  
            	 $.tooltip('请先登录',2000,false);
            }
        }); 
            });
	
			
			
			

	
	 $('.changepassword').hDialog({box: '#changepwdBox'});
	$('.changepwdsub').click(function()
			{
			var jsoninfo = $.toJSON($('.changeform').serializeObject()); 
		 jQuery.ajax( {  
           url:'user/changePwd',  
           type:'put',
           contentType : 'application/json',
           data: jsoninfo,  
           dataType:'json',
           success:function(data) {
         		  		$.tooltip('密码修改成功',2000,true);
         		  		setTimeout(function(){ 
							//$el.hDialog('close',{box:'#HBox'},'http://smwell.sinaapp.com/');  //也可以加跳转链接哦~
							$('.changepassword').hDialog('close',{box:'#changepwdBox'});
						},500);
				},  
           error: function(data) {
                $.tooltip('密码修改失败,请重试',2000,false);
           }
       }); 
			});
	/*$(".loginform").hDialog({
	               width: 500,
	               height: 400,
	               closeHide: false,
	               resetForm: false,
	               modalHide: false,
	           });*/
	

	
	
	
	 $('.Reg').hDialog({box: '#reg'}); //自定义弹框容器ID/Class；
	$('.RegButton').click(function() {
		var $username = $('.regusername');
		var $password = $('.regpassword'); 
		var $twopassword = $('.twopassword');
		var $mode = $('.regmode');
		if($username.val() == ''){
			$.tooltip('昵称还没填呢...'); $username.focus();
		}else if($password.val() == ''){
			$.tooltip('密码还没填呢...'); $password.focus();
		}else if($twopassword.val()!=$password.val()){
				$.tooltip('两次密码不匹配'); $twopassword.focus();
		}else{
			var jsonuserinfo = $.toJSON($('.regform').serializeObject());  //
            jQuery.ajax( {  
                url:'user',  
                type:'post',  
                contentType : 'application/json',  
                data: jsonuserinfo,  
                dataType:'json',
                success:function(data) {
						$.tooltip('注册成功,您的账号为:'+data.reguserId,10000,true);
						setTimeout(function(){ 
							$('.Reg').hDialog('close',{box:'#reg'});
						},500);
				},  
                error: function(data) {  
                     $.tooltip('注册失败,请重试');
                }
            }); 
		}
	});
	
	
	
});

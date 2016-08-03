<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>读者页面</title>
<!--                       CSS                       -->
<!-- Reset Stylesheet -->
<link rel="stylesheet" href="resources/css/reset.css" type="text/css" media="screen" />
<!-- Main Stylesheet -->
<link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen" />
<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
<link rel="stylesheet" href="resources/css/invalid.css" type="text/css" media="screen" />
<link rel="stylesheet" href="resources/css/common.css"/><!-- 基本样式 -->
<link rel="stylesheet" href="resources/css/animate.min.css"/> <!-- 动画效果 -->
<link href='http://fonts.useso.com/css?family=Raleway:100,700,800' rel='stylesheet' type='text/css'/>
<link rel="stylesheet" type="text/css" href="resources/fonts/font-awesome-4.2.0/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css" href="resources/css/normalize.css" />
<link rel="stylesheet" type="text/css" href="resources/css/demo.css" />
<link rel="stylesheet" type="text/css" href="resources/css/component.css" />
<link rel="stylesheet" href="resources/css/jq22.css" media="screen" />
<link rel="stylesheet" href="resources/css/css3_3d.css" media="screen" />

<!--                       Javascripts                       -->
<!-- jQuery -->
<script type="text/javascript" src="http://libs.baidu.com/jquery/1.10.0/jquery.min.js"></script>
<!-- jQuery Configuration -->
<script type="text/javascript" src="resources/scripts/simpla.jquery.configuration.js"></script>
<!-- Facebox jQuery Plugin -->
<script type="text/javascript" src="resources/scripts/facebox.js"></script>
<!-- jQuery WYSIWYG Plugin -->
<script type="text/javascript" src="resources/scripts/jquery.wysiwyg.js"></script>
<!-- jQuery Datepicker Plugin -->
<script type="text/javascript" src="resources/scripts/jquery.hDialog.js"></script>

<script type="text/javascript" src="resources/scripts/classie.js"></script>

<script type="text/javascript" src="resources/scripts/modernizr.js"></script>

<script type="text/javascript" src="js/jquery.json-2.2.min.js"></script>
<script type="text/javascript" src="js/serializeObject.js"></script>
<script type="text/javascript" src="js/login.js"></script> 
<script type="text/javascript" src="js/MyInfo.js"></script>
<script type="text/javascript" src="js/AddandChangeBook.js"></script>
<script type="text/javascript" src="js/BorrowAndBackBook.js"></script>


</head>

<body>
 <!--左侧菜单栏START-->
 <div id="body-wrapper">
<div id="sidebar">
    <div id="sidebar-wrapper">
      <!-- Sidebar with logo and menu -->
      <h1 id="sidebar-title"><a href="#">Simpla Admin</a></h1>
      <!-- Logo (221px wide) -->
      <a href="#"><img id="logo" src="resources/images/libraryLogo.png" alt="Simpla Admin logo" /></a>
      <!-- Sidebar Profile links -->
      <div id="profile-links"> Hello, <a id="readername" href="#" title="My Name"></a><br/>
        <br />
        <a href="javascript:;" class="bounceIn dialog" title="login">登录</a>
 | <a href="#" title="Sign Out" class="loginout" >注销</a> </div>
      <ul id="main-nav">
        <!-- Accordion Menu -->
        <div id="borrowbookdiv"  style="display:none" >
        <li> <a id=hasborrowparent class="nav-top-item" > 借阅图书查询 </a>
          <ul>
            <li><a id="hasborrow" href="#" onclick="gethasBorrowInfo()">已借阅图书</a></li>
          </ul>
        </li>
        </div>
       
         <div id="bookmanagediv" style="display:none">
        <li> <a id="bookmanageparent" href="#" class="nav-top-item">书库管理 </a>
          <ul>
            <li><a class="addbook" id="addbook" onclick="showAddBookFrame()" href="#">添加图书</a></li>
            <li><a class="changebook"  id="changebook" onclick="showGetIsbnForm()" href="#">编辑图书</a></li>
            <li><a class="borrowbook" id="borrowbook" onclick="showBorrowBookForm()" href="#">借阅图书</a></li>
            <li><a class="backbook" id="backbook"  onclick="showbackBookForm()" href="#">归还图书</a></li>
          </ul>
        </li>
        </div>
       
        <li> <a href="#" id="searchBook" class="nav-top-item"> 检索图书 </a>
        </li>
         <div id="setdiv" style="display:none">
        <li> <a id="setparent" href="#" class="nav-top-item">设置 </a>
          <ul> 
            <li><a class="changepassword" id="changepassword" href="#">密码修改</a></li>
            <li><a class="changeInfo" onclick="getInfo()"  id="changeInfo" href="#">个人信息修改</a></li>
          </ul>
        </li>
        </div>
        
      </ul>
    </div>
  </div>
  <!--左侧菜单栏END-->
   </div>
 <div id="main-content" class="main" >
    
    <div id="content-box" class="content-box">
    
    
    <!--  <div class="content-box-header">
        <h3>Content box</h3>
      </div>
      End .content-box-header
      <div class="content-box-content">
        <div class="tab-content default-tab" id="tab1">
          This is the target div. id must match the href of this div's tab
          <div class="notification attention png_bg"> <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>
            <div> This is a Content Box. You can put whatever you want in it. By the way, you can close this notification with the top-right cross. </div>
          </div>
          <table>
            <thead>
              <tr>
                <th><input class="check-all" type="checkbox" />
                </th>
                <th>Column 1</th>
                <th>Column 2</th>
                <th>Column 3</th>
                <th>Column 4</th>
                <th>Column 5</th>
              </tr>
            </thead>
           
            <tbody>
              <tr>
                <td>
                  <input type="checkbox" />
                </td>
                <td>Lorem ipsum dolor</td>
                <td><a href="#" title="title">Sit amet</a></td>
                <td>Consectetur adipiscing</td>
                <td>Donec tortor diam</td>
                <td>
                  Icons
                  <a href="#" title="Edit"><img src="resources/images/icons/pencil.png" alt="Edit" /></a> <a href="#" title="Delete"><img src="resources/images/icons/cross.png" alt="Delete" /></a> <a href="#" title="Edit Meta"><img src="resources/images/icons/hammer_screwdriver.png" alt="Edit Meta" /></a> </td>
              </tr>
            </tbody>
          </table>
        </div>
        
        
        </div> -->
        
        
    </div>
    
    
<div id="morphsearch" class="morphsearch">
  <form class="morphsearch-form" method="post" onsubmit="return false;">
					<input class="morphsearch-input" type="search" placeholder="Search..."/>
					<button class="morphsearch-submit" onclick="search()" type="submit">Search</button>
			</form>
			<div id="morphsearch-content" class="morphsearch-content">
				
		    </div><!-- /morphsearch-content -->
			<span class="morphsearch-close"></span>
		</div><!-- /morphsearch -->
    
<script type="text/javascript" src="js/searchBook.js"></script> 
    </div>
    
    
    
  
 <div id="HBox"  align="center">
    	<h3>登录</h3>
			<form class="loginform" action="" method="post" onsubmit="return false;">
				账号:<input type="text" class="username" name="username"/>
            	<br/>
            	密码:<input type="password" class="password" name="password" />
         	 	<br/>
              	<select name="mode" class="mode">
                      <option value="0">读者</option>
                      <option value="1">图书管理员</option>
                      <option value="2">管理员</option>
                    </select>
                    <br/>
					<input type="submit" value="登录"  class="subButton" />
					<input type="button" value="注册" class="Reg"/>
			</form>
		</div>
		<div id="reg"  align="center">
    	<h3>注册</h3>
			<form class="regform" action="" method="post" onsubmit="return false;">
				用户名:<input type="text" class="regusername" name="username"/>
            	<br/>
            	登录密码:<input type="password" class="regpassword" name="password" />
         	 	<br/>
         	 	再次输入:<input type="password" class="twopassword" name="twopassword" />
         	 	<br/>
         	 	账号类型:
              	<select name="mode" class="regmode">
                      <option value="0">读者</option>
                      <option value="1">图书管理员</option>
                      <option value="2">管理员</option>
                 </select>
                    <br/>
                 	性别:
              	<select name="sex" class="sex">
                      <option value="0">男</option>
                      <option value="1">女</option>
                 </select>
             	年级:
             	<select name="grader" class="grader">
                      <option value="2010">2010</option>
                      <option value="2011">2011</option>
                      <option value="2012">2012</option>
                      <option value="2013">2013</option>
                      <option value="2014">2014</option>
                      <option value="2015">2015</option>
                 </select>
					<input type="button" value="注册" class="RegButton"/>
			</form>
			<p id=regInfo></p>
		</div>
		
		<div id="changepwdBox"  align="center">
    	<h3>修改密码</h3>
			<form class="changeform" action="" method="post" onsubmit="return false;">
				原密码:<input type="text" class="oldpassword" name="oldpassword"/>
            	<br/>
            	新密码:<input type="password" class="newpassword" name="newpassword" />
         	 	<br/>
         	 	新密码:<input type="password" class="newtwopassword" name="newtwopassword" />
         	 	<br/>
					<input type="submit" value="提交"  class="changepwdsub" />
					
			</form>
		</div>
		
	<ul id="information">
    <li id="info">
    </li></ul>
		
		
		
</body>
</html>

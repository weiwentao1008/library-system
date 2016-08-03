
function getCookie(name)
     {
         
         cookie=document.cookie.split(';');
      
         for(i=0;i<cookie.length;i++)
         {
             str=cookie[i].split('=');
            
             if(str[0]==name)
            {
                
                 return str[1];
            }
         }
         return null;
     }



function showBorrowBookForm() {
	var contentbox = document.getElementById("content-box");
	contentbox.innerHTML = "";
	var contentboxcontent = document.createElement("div");
	var contentboxheader = document.createElement("div");
	contentboxheader.setAttribute("class", "content-box-header");
	contentboxcontent.setAttribute("class", "content-box-content");
	contentboxheader.innerHTML = '<h3>借书</h3>';
	contentboxcontent.innerHTML = '<div class="tab-content default-tab" id="tab1" align="center">'
			+ '<form class="borrowbookform" id="borrowbookform" action="" method="post" onsubmit="return false;">'
			+ ' <fieldset>'
			+ '<p>'
			+ ' <label>读者账号</label>'
			+ ' <input class="text-input small-input" type="text" id="readerid" name="readerid" />'
			+ '</p>'
			+ ' <br />'
			+ '<p>'
			+ ' <label>ISBN</label>'
			+ ' <input class="text-input small-input" type="text" id="bookisbn" name="bookisbn" />'
			+ '</p>'
			+ ' <br />'
			+ ' <p>'
			+ '  <input class="button" onclick="borrowBook()" id="showsubmit" type="button" value="借阅" />'
			+ ' </p>' + '</fieldset>' + '  </form>' + ' </div>';
	contentbox.appendChild(contentboxheader);
	contentbox.appendChild(contentboxcontent);
}

function borrowBook() {

	jQuery.ajax({
		url : 'borrow/borrowBook?bookisbn=' + $('#bookisbn').val()
				+ '&readerid=' + $('#readerid').val(),
		type : 'post',
		dataType : 'json',
		success : function(data) {
		
				$.tooltip(data.state, 2000, true);
				showBorrowBookForm();	
		},
		error : function(data) {
			$.tooltip('借阅失败', 2000, false);
		}
	});
}

function getBorrowInfo(url) {
	jQuery
			.ajax({
				url : url,
				type : 'get',
				dataType : 'json',
				success : function(data) {
						$.tooltip("获取成功", 2000, true);
						var contentbox = document.getElementById("content-box");
						contentbox.innerHTML = "";
						var contentboxcontent = document.createElement("div");
						var contentboxheader = document.createElement("div");
						contentbox.appendChild(contentboxheader);
						contentbox.appendChild(contentboxcontent);
						contentboxheader.setAttribute("class",
								"content-box-header");
						contentboxcontent.setAttribute("class",
								"content-box-content");
						contentboxheader.innerHTML = '<h3>还书</h3>';
						contentboxcontent.innerHTML = '<div class="tab-content default-tab" id="tab1">'
								+ '<div class="notification attention png_bg"> <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>'
								+ '<div id="showbackinfo"> </div>'
								+ '</div>'
								+ '<table>'
								+ '<thead>'
								+ '<tr>'
								+ ' <th>id</th>'
								+ ' <th>书名</th>'
								+ ' <th>借阅时间</th>'
								+ '<th>到期时间</th>'
								+ '<th>操作</th>'
								+ ' </tr>'
								+ '</thead>'
								+ '<tbody id="tablebody">';
						var n = 1;
						var tablebody = document.getElementById('tablebody');
						tablebody.innerHTML = "";
						$
								.each(
										data.borrowList,
										function(k, v) {
											tablebody.innerHTML += '<tr>'
													+ '<td>'
													+ (n++)
													+ '</td>'
													+ '<td><a href="#" title="title">'
													+ v.bookname
													+ '</a></td>'
													+ '<td>'
													+ v.borrowtime
													+ '</td>'
													+ '<td>'
													+ v.backtime
													+ '</td>'
													+ '<td><a id="'
													+ v.borrowid
													+ '" onclick="backBook(this)" href="#" title="归还"><img src="resources/images/icons/cross.png" alt="Delete" /></a></td>'
													+ '</tr>';
										});
						contentboxcontent.innerHTML += '</tbody></table></div>';
						if (data.moretime != 0)
							document.getElementById("showbackinfo").innerHTML = '您已有'
									+ data.moretime + '本书过期,请尽快归还······';
						else
							document.getElementById("showbackinfo").innerHTML = '记得归还您的图书······';
						
				},
				error : function(data) {
                    $.tooltip("还没借书哦·····", 2000, false);
					
				}
			});
}

function gethasBorrowInfo() {
    
		jQuery.ajax({
					url : 'borrow/hasborrowBookinfo?logincode='+getCookie('logincode'),
					type : 'get',
					dataType : 'json',
					success : function(data) {
					$.tooltip("获取成功", 2000, true);
					var contentbox = document.getElementById("content-box");
					contentbox.innerHTML = "";
					var contentboxcontent = document.createElement("div");
					var contentboxheader = document.createElement("div");
					contentbox.appendChild(contentboxheader);
					contentbox.appendChild(contentboxcontent);
					contentboxheader.setAttribute("class","content-box-header");
					contentboxcontent.setAttribute("class","content-box-content");
					contentboxheader.innerHTML = '<h3>还书</h3>';
					contentboxcontent.innerHTML = '<div class="tab-content default-tab" id="tab1">'
													+ '<div class="notification attention png_bg"> <a href="#" class="close"><img src="resources/images/icons/cross_grey_small.png" title="Close this notification" alt="close" /></a>'
													+ '<div id="showbackinfo"> </div>'
													+ '</div>'
													+ '<table>'
													+ '<thead>'
													+ '<tr>'
													+ ' <th>id</th>'
													+ ' <th>书名</th>'
													+ ' <th>借阅时间</th>'
													+ '<th>到期时间</th>'
													+ ' </tr>'
													+ '</thead>'
													+ '<tbody id="tablebody">';
											var n = 1;
											var tablebody = document
													.getElementById('tablebody');
											tablebody.innerHTML = "";
											$.each(data.borrowList,
															function(k, v) {
																tablebody.innerHTML += '<tr>'
																		+ '<td>'
																		+ (n++)
																		+ '</td>'
																		+ '<td><a href="#" title="title">'
																		+ v.bookname
																		+ '</a></td>'
																		+ '<td>'
																		+ v.borrowtime
																		+ '</td>'
																		+ '<td>'
																		+ v.backtime
																		+ '</td>'
																		+ '</tr>';
															});
											contentboxcontent.innerHTML += '</tbody></table></div>';
											if (data.moretime != 0)
												document
														.getElementById("showbackinfo").innerHTML = '您已有'
														+ data.moretime
														+ '本书过期,请尽快归还······';
											else
												document
														.getElementById("showbackinfo").innerHTML = '记得归还您的图书······';
									},
									error : function(data) {
										$.tooltip("获取失败", 2000, false);
									}
								});
                }

function backBook(click) {
	jQuery.ajax({
		url : 'borrow/backBook?borrowid=' + click.id,
		type : 'put',
		dataType : 'json',
		success : function(data) {
				$.tooltip("还书成功,欢迎下次借阅", 2000, true);
				showbackBookForm();
		},
		error : function(data) {
		      $.tooltip("还书失败,请重试···", 2000, false);
		}
	});
}

function showbackBookForm() {

	var contentbox = document.getElementById("content-box");
	contentbox.innerHTML = "";
	var contentboxcontent = document.createElement("div");
	var contentboxheader = document.createElement("div");
	contentboxheader.setAttribute("class", "content-box-header");
	contentboxcontent.setAttribute("class", "content-box-content");
	contentboxheader.innerHTML = '<h3>还书</h3>';
	contentboxcontent.innerHTML = '<div class="tab-content default-tab" id="tab1" align="center">'
			+ '<form class="backbookform" id="backbookform" action="" method="post" onsubmit="return false;">'
			+ ' <fieldset>'
			+ '<p>'
			+ ' <label>读者账号</label>'
			+ ' <input class="text-input small-input" type="text" id="readerid" name="readerid" />'
			+ '</p>'
			+ ' <br />'
			+ ' <p>'
			+ '  <input class="button" onclick="getBorrowInfo(\'borrow/borrowBookinfo?logincode='+getCookie('logincode')+'&readerid=\' + $(\'#readerid\').val())" id="showsubmit" type="button" value="获取" />'
			+ ' </p>' + '</fieldset>' + '  </form>' + ' </div>';
	contentbox.appendChild(contentboxheader);
	contentbox.appendChild(contentboxcontent);

}

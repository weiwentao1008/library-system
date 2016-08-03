function showAddBookFrame() {
	var contentbox = document.getElementById("content-box");
	contentbox.innerHTML = "";
	var contentboxcontent = document.createElement("div");
	var contentboxheader = document.createElement("div");
	contentboxheader.setAttribute("class", "content-box-header");
	contentboxcontent.setAttribute("class", "content-box-content");
	contentboxheader.innerHTML = '<h3>添加书入库</h3>';
	contentboxcontent.innerHTML = ' <div class="tab-content default-tab" id="tab1" align="center">'
			+ '<form class="addbookform" id="addbookform" action="" method="post" onsubmit="return false;">'
			+ ' <fieldset>'
			+ '<p>'
			+ ' <label>书名</label>'
			+ ' <input class="text-input small-input" type="text" id="bookname" name="bookname" />'
			+ '</p>'
			+ ' <br />'
			+ '<p>'
			+ ' <label>作者</label>'
			+ ' <input class="text-input small-input" type="text" id="author" name="author" />'
			+ '</p>'
			+ ' <br />'
			+ '<p>'
			+ '<label>ISBN</label>'
			+ ' <input class="text-input small-input" type="text" id="isbn" name="isbn" />'
			+ '<br />'
			+ '</p>'
			+ '<p>'
			+ ' <label>价格</label>'
			+ ' <input class="text-input small-input" type="text" id="price" name="price"  />'
			+ '</p>'
			+ ' <br />'
			+ '<p>'
			+ ' <label>类型</label>'
			+ ' <input class="text-input small-input" type="text" id="type" name="type"  />'
			+ '</p>'
			+ ' <br />'
			+ '<p>'
			+ ' <label>可借阅本数</label>'
			+ ' <input class="text-input small-input" type="text" id="count" name="count"  />'
			+ '</p>'
			+ ' <br />'
			+ '<p>'
			+ '<label>位置</label>'
			+ ' <input class="text-input small-input" type="text" id="point" name="point" />'
			+ '<br />'
			+ '</p>'
			+ ' <p>'
			+ '  <input class="button" onclick="addBook()" id="showsubmit" type="button" value="保存" />'
			+ ' </p>' + '</fieldset>' + '  </form>' + ' </div>';
	contentbox.appendChild(contentboxheader);
	contentbox.appendChild(contentboxcontent);
}

function showGetIsbnForm()
{
	var contentbox = document.getElementById("content-box");
	contentbox.innerHTML = "";
	var contentboxcontent = document.createElement("div");
	var contentboxheader = document.createElement("div");
	contentboxheader.setAttribute("class", "content-box-header");
	contentboxcontent.setAttribute("class", "content-box-content");
	contentboxheader.innerHTML = '<h3>编辑图书信息</h3>';
	contentboxcontent.innerHTML = ' <div class="tab-content default-tab" id="tab1" align="center">'
			+ '<form class="getIsbnForm" id="getIsbnForm" action="" method="post" onsubmit="return false;">'
			+ ' <fieldset>'
			+ '<p>'
			+ ' <label>isbn</label>'
			+ ' <input class="text-input small-input" type="text" id="getisbn" name="getisbn" />'
			+ '</p>'
			+ ' <br />'
			+ ' <p>'
			+ '  <input class="button" onclick="getBookInfoForChange()" id="showsubmit" type="button" value="获取" />'
			+ ' </p>' + '</fieldset>' + '  </form>' + ' </div>';
	contentbox.appendChild(contentboxheader);
	contentbox.appendChild(contentboxcontent);
}

function getBookInfoForChange() {
	jQuery.ajax({
        url:'book/getbookinfoForchange?bookisbn='+$('#getisbn').val(),
        type:'get',
        dataType:'json',
        success:function(data) {
        	var contentbox = document.getElementById("content-box");
        	contentbox.innerHTML = "";
        	var contentboxcontent = document.createElement("div");
        	var contentboxheader = document.createElement("div");
        	contentboxheader.setAttribute("class", "content-box-header");
        	contentboxcontent.setAttribute("class", "content-box-content");
        	contentboxheader.innerHTML = '<h3>编辑图书信息</h3>';
        	contentboxcontent.innerHTML = ' <div class="tab-content default-tab" id="tab1" align="center">'
        			+ '<form class="changebookform" id="changebookform" action="" method="post" onsubmit="return false;">'
        			+ ' <fieldset>'
        			+ '<p>'
        			+ ' <label>书名</label>'
        			+ ' <input class="text-input small-input" type="text" id="bookname" name="bookname" value="'+data.bookinfo.name+'" />'
        			+ '</p>'
        			+ ' <br />'
        			+ '<p>'
        			+ ' <label>作者</label>'
        			+ ' <input class="text-input small-input" type="text" id="author" name="author" value="'+data.bookinfo.author+'" />'
        			+ '</p>'
        			+ ' <br />'
        			+ '<p>'
        			+ '<label>ISBN</label>'
        			+ ' <input class="text-input small-input" type="text" id="isbn" name="isbn" value="'+data.bookinfo.isbn+'" />'
        			+ '<br />'
        			+ '</p>'
        			+ '<p>'
        			+ ' <label>价格</label>'
        			+ ' <input class="text-input small-input" type="text" id="price" name="price" value= "'+data.bookinfo.price+'" />'
        			+ '</p>'
        			+ ' <br />'
        			+ '<p>'
        			+ ' <label>类型</label>'
        			+ ' <input class="text-input small-input" type="text" id="type" name="type"  value="'+data.bookinfo.type+'"/>'
        			+ '</p>'
        			+ ' <br />'
        			+ '<p>'
        			+ ' <label>可借阅本数</label>'
        			+ ' <input class="text-input small-input" type="text" id="count" name="count" value= "'+data.bookinfo.count+'"/>'
        			+ '</p>'
        			+ ' <br />'
        			+ '<p>'
        			+ '<label>位置</label>'
        			+ ' <input class="text-input small-input" type="text" id="point" name="point" value="'+data.bookinfo.point+'" />'
        			+ '<br />'
        			+ '</p>'
        			+ ' <p>'
        			+ '  <input class="button" onclick="changeBook()" id="showsubmit" type="button" value="保存" />'
        			+ ' </p>' + '</fieldset>' + '  </form>' + ' </div>';
        	contentbox.appendChild(contentboxheader);
        	contentbox.appendChild(contentboxcontent);
        	$.tooltip('获取书信息成功', 2000, true);
			},  
        error: function(data) { 
            $.tooltip('获取书信息失败', 2000, false);
        }
    }); 
}

function addBook() {
	var jsoninfo = $.toJSON($('.addbookform').serializeObject()); //
	jQuery.ajax({
		url : 'book/addBook',
		type : 'post',
		contentType : 'application/json',
		data : jsoninfo,
		dataType : 'json',
		success : function(data) {
				$.tooltip('添加书入库成功', 2000, true);
				showAddBookFrame();
		},
		error : function(data) {
			$.tooltip('添加书入库失败,请重试', 2000, false);
		}
	});
}

function changeBook() {
	var jsoninfo = $.toJSON($('.changebookform').serializeObject()); //
	jQuery.ajax({
		url : 'book/changebook',
		type : 'put',
		contentType : 'application/json',
		data : jsoninfo,
		dataType : 'json',
		success : function(data) {
				$.tooltip('修改书成功', 2000, true);
				showGetIsbnForm();
		},
		error : function(data) {
			$.tooltip('修改书失败,请重试', 2000, false);
		}
	});
}

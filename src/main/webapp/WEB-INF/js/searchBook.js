(function() {
				var morphSearch = document.getElementById( 'morphsearch' ),
					input = morphSearch.querySelector( 'input.morphsearch-input' ),
					ctrlClose = morphSearch.querySelector( 'span.morphsearch-close' ),
					searchBook=document.getElementById("searchBook");
					isOpen = isAnimating = true;
					classie.add( morphSearch, 'open' );
					searchBook.setAttribute("class","nav-top-item current");
					var pageWidth = window.innerWidth;
					$(".morphsearch.open").css("width",pageWidth-250);
					// show/hide search area
					toggleSearch = function(evt) {
						// return if open and the input gets focused
						if( evt.type.toLowerCase() === 'focus' && isOpen ) return false;

						var offsets = morphsearch.getBoundingClientRect();
						if( isOpen ) {
							classie.remove( morphSearch, 'open' );
							searchBook.setAttribute("class","nav-top-item");
							$(".morphsearch").css("width",200);
							if( input.value !== '' ) {
								setTimeout(function() {
									classie.add( morphSearch, 'hideInput' );
									setTimeout(function() {
										classie.remove( morphSearch, 'hideInput' );
										input.value = '';
									}, 300 );
								}, 500);
							}
							
							input.blur();
						}
						else {
							classie.add( morphSearch, 'open' );
							searchBook.setAttribute("class","nav-top-item current");
							var pageWidth = window.innerWidth;
							//$('.morphsearch.open').style.width=pageWidth-240;
							$(".morphsearch.open").css("width",pageWidth-250);
						}
						isOpen = !isOpen;

					};
				// events
				$('#bookinfo').click(function(){document.getElementById("bookinfo").addEventListener("click",toggleSearch);});
				searchBook.addEventListener('click', toggleSearch);
				input.addEventListener( 'focus', toggleSearch );
				ctrlClose.addEventListener( 'click', toggleSearch );
				// esc key closes search overlay
				// keyboard navigation events
				document.addEventListener( 'keydown', function( ev ) {
					var keyCode = ev.keyCode || ev.which;
					if( keyCode === 27 && isOpen ) {
						toggleSearch(ev);
					}
				} );

				
				/***** for demo purposes only: don't allow to submit the form *****/
				morphSearch.querySelector( 'button[type="submit"]' ).addEventListener( 'click', function(ev) { ev.preventDefault(); } );
			})();


function search()
{ 
	var morphsearchcontent=document.getElementById("morphsearch-content");
	 jQuery.ajax({
         url:'book/findbook?bookname='+$('.morphsearch-input').val(),
         type:'get',
         dataType:'json',
         success:function(data) {
               var bookListHtml='<div class=\"dummy-column\"><h2>图书查询</h2>';
               $.each(data.booklist, function(k, v) {
									bookListHtml += '<a name=' +v._id +' id=\"bookinfo\" class=\"dummy-media-object\" href=\"#info\" onclick=\"getbookinfo(this)\">'
								                       + '<h3>'+v.name +'</h3></a>';
                                });
                bookListHtml+='</div>';
        	 morphsearchcontent.innerHTML="";
        	 morphsearchcontent.innerHTML=bookListHtml;
            },  
         error: function(data) { 
                $.tooltip("查询图书失败",2000,false);
         }
     }); 
}

function getbookinfo(sub){
	jQuery.ajax({
        url:'book/getBookInfo?bookid='+sub.name,
        type:'get',
        dataType:'json',
        success:function(data) {
        	document.getElementById("info").innerHTML='<div>'
     	       	+ '<h3>'+data.bookinfo.name+'</h3>'
     	        +'<p>位置:'+data.bookinfo.point+'</p>'
     	        +'<p>作者:'+data.bookinfo.author+'</p>'
     	        +'<p>馆藏总数:'+data.bookinfo.count+'</p>'
     	        +'<p>当前可借数:'+data.bookinfo.hascount+'</p>'
     	        +'<p>类型:'+data.bookinfo.type+'</p>'
     	        +'<p>价格:'+data.bookinfo.price+'</p>'
     	        +'<p>ISBN:'+data.bookinfo.isbn+'</p>'
     	        +'<a href="#" class="close">x</a> </div>'
     	        +' <span class="backface"></span>';
                
			},  
        error: function(data) { 
            $.tooltip('获取图书信息失败',2000,false);
        }
    }); 
	
	
	}




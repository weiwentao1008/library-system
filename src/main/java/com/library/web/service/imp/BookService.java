package com.library.web.service.imp;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.library.web.bean.Book;
import com.library.web.dao.imp.BookImp;

public class BookService {
	private static BookImp bookImp;
	static {
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("/applicationContext.xml");
		bookImp=context.getBean(BookImp.class);
	}
	public static Book findBookByIsbn(String isbn)
	{
		return bookImp.findForbookisbn(isbn);
	}
	public static boolean bookChange(Book book){
		return bookImp.changebook(book);
	}
	public static boolean bookAdd(Book book){
		return bookImp.addbook(book);
	}
	public static boolean bookDelete(int bookid){
		return bookImp.deletebook(bookid);
		
	}
	public static List<Book> bookQuery(String bookname){
		List<Book> bookList=bookImp.findOnebook(bookname);
		/*String html="<div class=\"dummy-column\"><h2>图书查询</h2>";
		for (int i = 0; i < book.size(); i++) {
				html = html + "<a name="+book.get(i).get_id()+" id=\"bookinfo\" class=\"dummy-media-object\" href=\"#info\" onclick=\"getbookinfo(this)\">"
						+ "<h3>"+book.get(i).getName()+"</h3></a>";
			}	
		html=html+"</div>";*/
		return bookList;
	}
	/*public static String bookAll(){
		List<Book> book=BookImp.getInstance().findAllbook();
		String html="<div class=\"dummy-column\"><h2>图书查询</h2>";
		for (int i = 0; i < book.size(); i++) {
			for (int j = 0; j < 3; j++) {
				html = html + "<a class=\"dummy-media-object\" href=\"#\">"
						+ "<h3>+"+book.get(i).getName()+"</h3></a>";
			}
		}
		html=html+"</div>";
		return html;
	}*/
	public static Book getBookInfo(Integer bookid)
	{
		return bookImp.findForbookid(bookid);
	}
}

package com.library.web.service.imp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.library.web.bean.Book;
import com.library.web.bean.Borrow;
import com.library.web.bean.User;
import com.library.web.dao.imp.BookImp;
import com.library.web.dao.imp.BorrowImp;
import com.library.web.dao.imp.UserImp;

public class BorrowService {
	/*public static String findAllborrow() {
		List<Borrow> borrow = BorrowImp.getInstance().findAll();
		String html = "<div class=\"dummy-column\"><h2>全部借书列表 </h2>";
		for (int i = 0; i < borrow.size(); i++) {
			for (int j = 0; j < 3; j++) {
				html = html+"<a class=\"dummy-media-object\" href=\"#\">"
						+ "<h3>+"+borrow.get(i).get_id()+"</h3></a>";
			}	
		}
		html=html+"</div>";
		return html;
	}*/
	private static UserImp userImp;
	private static BookImp bookImp;
	private static BorrowImp borrowImp;
	static {
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("/applicationContext.xml");
		bookImp=context.getBean(BookImp.class);
		borrowImp=context.getBean(BorrowImp.class);
		userImp=context.getBean(UserImp.class);
	}
	public static List<Borrow> findForuserid(int userid) {
		List<Borrow> borrow=borrowImp.finnUserborrow(userid);
		
		return borrow;
	}

	public static int borrowBook(String isbn, int userid) {
		Book book = bookImp.findForbookisbn(isbn);
		User user = userImp.findById(userid);
		
		if(book==null||user==null)
			return 2;//找不到isbn或用户
		if(user.getHascount()+1>user.getCount())
			return 1;//超过可借阅数
		if(book.getHascount()-1<0)
			return 5;//图书不够
		Borrow borrow = new Borrow();
		borrow.setIsback(0);
		borrow.setUserid(userid);
		borrow.setBookid(book.get_id());
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		borrow.setBorrow_time(sf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, user.getBorrowtime());
		borrow.setBack_time(sf.format(cal.getTime()));
		if(borrowImp.add(borrow))
		{
			book.setHascount(book.getHascount()-1);
			user.setHascount(user.getHascount()+1);
			bookImp.changebook(book);
			userImp.changeUser(user);
			return 3;//成功
		}
		else
			return 4;//失败
	}

	public static boolean BackBook(Integer borrowid) {
		
		Borrow bo=borrowImp.findBorrowById(borrowid);
		User user=userImp.findById(bo.getUserid());
		user.setHascount(user.getHascount()-1);
		userImp.changeUser(user);
		bo.setIsback(1);
		return borrowImp.change(bo);
	}

}

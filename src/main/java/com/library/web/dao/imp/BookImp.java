package com.library.web.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.library.web.bean.Book;
import com.library.web.dao.BookDao;
import com.library.web.sessionFactory.HibernateSessionFactory;

public class BookImp implements BookDao {

/*	private static Session session=HibernateSessionFactory.getSession();  
	private static BookImp bookimp=new BookImp();
	public static synchronized BookImp getInstance(){
		return bookimp;
	}*/
	private Session session=HibernateSessionFactory.getSession();
	
	public void disposeBookImp()
	{
		HibernateSessionFactory.closeSession();
	}
	public boolean addbook(Book book) {
		Transaction tr=session.beginTransaction();
		session.save(book);
		tr.commit();
		return tr.wasCommitted();
	}

	public List<Book> findAllbook() {
		String sql="from Book";
		Query query=session.createQuery(sql);
		return query.list();
	}

	
	public List<Book> findOnebook(String bookname) {
		String sql="from Book where name like ?";
		Query query=session.createQuery(sql);
		query.setString(0,"%"+bookname+"%");
		return query.list();
	}
	
	public Book findForbookid(int bookid){
		String sql="from Book where _id=?";
		Query query=session.createQuery(sql);
		query.setString(0, String.valueOf(bookid));
		return (Book)query.uniqueResult();
	}
	public Book findForbookisbn(String bookisbn){
		String sql="from Book where isbn=?";
		Query query=session.createQuery(sql);
		query.setString(0, bookisbn);
		return (Book)query.uniqueResult();
	}
	public boolean changebook(Book book) {
		Transaction tr=session.beginTransaction();
		session.update(book);
		tr.commit();
		return tr.wasCommitted();
	}

	
	public boolean deletebook(int bookid) {
		String sql="delete Book where _id=?";
		Query query=session.createQuery(sql);
		query.setString(0, String.valueOf(bookid));
		return query.executeUpdate()>0;
	}

}

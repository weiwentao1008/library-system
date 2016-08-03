package com.library.web.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.library.web.bean.Borrow;
import com.library.web.dao.BorrowDao;
import com.library.web.sessionFactory.HibernateSessionFactory;

public class BorrowImp implements BorrowDao {
	
	/*private static Session session=HibernateSessionFactory.getSession();  
	private static BorrowImp borrowimp=new BorrowImp();
	public static synchronized BorrowImp getInstance(){
		return borrowimp;
	}*/
	private Session session=HibernateSessionFactory.getSession();
	public void disposeBorrowImp()
	{
		HibernateSessionFactory.closeSession();
	}
	public List<Borrow> findAll() {
		String sql="from Borrow";
		Query query=session.createQuery(sql);
		return query.list();
	}

	public Borrow findBorrowById(Integer id)
	{
		String sql="from Borrow where _id=?";
		Query query=session.createQuery(sql);
		query.setInteger(0,id);
		return (Borrow) query.uniqueResult();
	}
	
	public List<Borrow> finnUserborrow(int userid) {
		String sql="from Borrow where userid=? and isback=0";
		Query query=session.createQuery(sql);
		query.setString(0, String.valueOf(userid));
		return query.list();
	}
	
	
	public boolean delete(int borrowid) {
		String sql="delete Borrow where _id";
		Query query=session.createQuery(sql);
		query.setString(0, String.valueOf(borrowid));
		return query.executeUpdate()>0;
	}

	
	public boolean add(Borrow borrow) {
		Transaction tr=session.beginTransaction();
		session.save(borrow);
		tr.commit();
		return tr.wasCommitted();
	}
	
	public boolean change(Borrow borrow) {
		Transaction tr=session.beginTransaction();
		session.update(borrow);
		tr.commit();
		return tr.wasCommitted();
	}

}

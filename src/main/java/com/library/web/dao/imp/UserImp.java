package com.library.web.dao.imp;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.library.web.bean.User;
import com.library.web.dao.UserDao;
import com.library.web.sessionFactory.HibernateSessionFactory;

public class UserImp implements UserDao {

	/*private Session session=HibernateSessionFactory.getSession();
	private static UserImp userimp=new UserImp();
	public static synchronized UserImp getInstance(){
		return userimp;
	}*/
	private Session session=HibernateSessionFactory.getSession();
	public void disposeUserImp()
	{
		HibernateSessionFactory.closeSession();
	}
	public User findById(int userid) {
		String sql="from User where _id=?";
		Query query=session.createQuery(sql);
		query.setString(0, String.valueOf(userid));
		return (User)query.uniqueResult();
	}

	public List<User> findAll() {
		String sql="from User";
		Query query=session.createQuery(sql);
		return query.list();
	}
	
	public boolean changeUser(User user) {
		Transaction tr= session.beginTransaction();
		session.update(user);
		tr.commit();
		return tr.wasCommitted();
	}
	
	public boolean addUser(User user) {
		Transaction tr= session.beginTransaction();
		session.save(user);
		tr.commit();
		return tr.wasCommitted();
	}
	
	public boolean deleteUser(int userid) {
		String sql="delete User where _id=?";
		Query query=session.createQuery(sql);
		query.setString(0, String.valueOf(userid));
		return query.executeUpdate()>0;
	}

}

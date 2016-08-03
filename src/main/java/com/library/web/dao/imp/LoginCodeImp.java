package com.library.web.dao.imp;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.library.web.bean.LoginCode;
import com.library.web.sessionFactory.HibernateSessionFactory;

public class LoginCodeImp {

	/*private static Session session=HibernateSessionFactory.getSession();  
	private static LoginCodeImp loginCodeImp=new LoginCodeImp();
	public static synchronized LoginCodeImp getInstance(){
		return loginCodeImp;
	}*/
	private Session session=HibernateSessionFactory.getSession();
	public void disposeLoginCodeImp()
	{
		HibernateSessionFactory.closeSession();
	}
	
	public boolean add(LoginCode loginCode) {
		Transaction tr=session.beginTransaction();
		session.save(loginCode);
		tr.commit();
		return tr.wasCommitted();
	}
	public LoginCode hasLive(String logincode)
	{
		String sql="from LoginCode where usercode=?";
		Query query=session.createQuery(sql);
		query.setString(0, logincode);
		return (LoginCode)query.uniqueResult();
	}
	public boolean deleteLoginCode(String logincode)
	{
		Transaction tr=session.beginTransaction();
		System.out.println(logincode);
		LoginCode lc=hasLive(logincode);
		if(lc!=null)
		{
			session.delete(lc);
			tr.commit();
			return true;
		}
		else
			return false;
	}
}

package com.library.web.service.imp;

import java.util.Calendar;
import java.util.Date;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.library.web.bean.LoginCode;
import com.library.web.dao.imp.BorrowImp;
import com.library.web.dao.imp.LoginCodeImp;

public class LoginCodeService {

	private static LoginCodeImp loginCodeImp;
	static {
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("/applicationContext.xml");
		loginCodeImp=context.getBean(LoginCodeImp.class);
	}
	public static String CreateLoginCode(Integer userid)
	{
		
		LoginCode lc=new LoginCode();
		lc.setUserid(userid);
		lc.setLogintime(new java.sql.Date(new Date().getTime()));
		Calendar now=Calendar.getInstance();
		now.add(Calendar.MINUTE,20);
		lc.setEndtime(new java.sql.Date(now.getTime().getTime()));
		String code=String.valueOf((""+userid+now.getTime().getTime()).hashCode());
		lc.setUsercode(code);
		loginCodeImp.add(lc);
		return code;
	}
	
	public static boolean  hasLive(String logincode)
	{
		LoginCode lc = loginCodeImp.hasLive(logincode);
		if(lc==null||lc.getEndtime().before(new Date()))
			return false;
		else
			return true;
	}
	public static boolean deleteLoginCode(String logincode)
	{
		return loginCodeImp.deleteLoginCode(logincode);
	}
	public static LoginCode getIdByLogincode(String logincode)
	{
		return loginCodeImp.hasLive(logincode);
	}
}

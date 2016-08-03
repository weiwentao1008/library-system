package com.library.web.service.imp;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.library.web.bean.User;
import com.library.web.dao.imp.UserImp;
import com.library.web.dto.AddUserDTO;
import com.library.web.dto.ChangeMyInfoDTO;
import com.library.web.dto.ChangePwdDTO;
import com.library.web.dto.LoginUserDTO;

public class UserService {

	private static UserImp userImp;
	static {
		AbstractApplicationContext context=new ClassPathXmlApplicationContext("/applicationContext.xml");
		userImp=context.getBean(UserImp.class);
	}
	public static String LoginCheck(LoginUserDTO loginuser) {
		User user = userImp.findById(loginuser.getUsername());
		if (user != null && user.getPassword().equals(loginuser.getPassword())&&loginuser.getMode()==user.getType())
			return user.getName();
		else
			return null;
	}

	public static Integer adduser(AddUserDTO adduser) {
		User user = new User();
		user.setBorrowtime(5);
		user.setCount(5);
		user.setGender(adduser.getSex());
		user.setGrade(adduser.getGrader());
		user.setName(adduser.getUsername());
		user.setPassword(adduser.getPassword());
		user.setType(adduser.getMode());
		// user.set_id(10);
		System.currentTimeMillis();
		if (userImp.addUser(user)) {
			System.out.println(user.get_id());
			return user.get_id();
		}
		return null;
	}

	public static boolean changePassword(ChangePwdDTO changepwd, Integer id) {
		User user = userImp.findById(id);
		if (user != null
				&& user.getPassword().equals(changepwd.getOldpassword())) {
			user.setPassword(changepwd.getNewpassword());
			return userImp.changeUser(user);
		} else
			return false;
	}

	public static User getInfo(Integer id) {
		return userImp.findById(id);
	}

	public static boolean changeMyInfoDTO(ChangeMyInfoDTO info,Integer id) {
		User user = userImp.findById(id);
		if (user != null) {
			user.setName(info.getNameshow());
			user.setGender(info.getSexshow());
			user.setType(info.getModeshow());
			return userImp.changeUser(user);
		}
		return false;

	}
	
	public void removeSession(String code){
		if(code == null){
			throw new RuntimeException("code not exist..");
		}
	}
}

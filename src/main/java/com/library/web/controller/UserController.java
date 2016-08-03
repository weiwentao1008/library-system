package com.library.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.web.bean.LoginCode;
import com.library.web.bean.User;
import com.library.web.dto.AddUserDTO;
import com.library.web.dto.ChangeMyInfoDTO;
import com.library.web.dto.ChangePwdDTO;
import com.library.web.dto.LoginUserDTO;
import com.library.web.exception.WebException;
import com.library.web.service.imp.LoginCodeService;
import com.library.web.service.imp.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {


	@RequestMapping(method=RequestMethod.GET)
	public String show() {
		return "main";
	}

	@ResponseBody
	@RequestMapping(value = "/isLogin", method = RequestMethod.POST)
	public Map<String,Object> isLoginCheck(@CookieValue("logincode") String logincode) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if(LoginCodeService.hasLive(logincode))
			modelMap.put("isLogin", "true");
		else
			modelMap.put("isLogin", "false");
		return modelMap;
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.DELETE)
	public Map<String, Object> loginout(@CookieValue("logincode")String code,HttpServletResponse response) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if(LoginCodeService.deleteLoginCode(code))
		{
		Cookie c=new Cookie("logincode","");
		c.setMaxAge(0);
		response.addCookie(c);
		c=new Cookie("loginid","");
		c.setMaxAge(0);
		response.addCookie(c);
		c=new Cookie("loginname","");
		c.setMaxAge(0);
		response.addCookie(c);
		}
		else
			throw new WebException("注销失败");
		return modelMap;
	}

	@ResponseBody
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public Map<String, Object> LoginCheck(@RequestBody LoginUserDTO loginUserDTO,HttpServletResponse response)throws WebException {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String username = UserService.LoginCheck(loginUserDTO);
		if(username!=null)
		{
			String code=LoginCodeService.CreateLoginCode(loginUserDTO.getUsername());
			Cookie c=new Cookie("logincode", code);
			c.setMaxAge(20*60);
			response.addCookie(c);
			c=new Cookie("loginid",String.valueOf( loginUserDTO.getUsername()));
			c.setMaxAge(20*60);
			response.addCookie(c);
			/*c=new Cookie("loginname",username);
			c.setMaxAge(20*60);
			response.addCookie(c);*/
			modelMap.put("loginname", username);
			return modelMap;
		}
		else
			throw new WebException("登录失败");
			//modelMap.put("loginState", "error");
		
	}
	
	

	/**
	 * 
	 * @param changepwd
	 * @param printwriter
	 * @param session
	 */
	@ResponseBody
	@RequestMapping(value = "/changePwd",method = RequestMethod.PUT)
	public Map<String, Object> changePassword( @CookieValue("logincode") String logincode,@RequestBody ChangePwdDTO changepwd
			) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		/*System.out.println(changepwd.getOldpassword() + ";"
				+ changepwd.getNewpassword() + ";"
				+ changepwd.getNewtwopassword());*/
		LoginCode lc = LoginCodeService.getIdByLogincode(logincode);
		
		if (lc!=null&&UserService.changePassword(changepwd,lc.getUserid()))
			return modelMap;
		else
			throw new WebException("密码修改失败");
		

	}


	/**
	 * http://localhost:8080/user
	 * method post 增加一个资源
	 * @param adduser
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> createUser(@RequestBody AddUserDTO adduser) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Integer regid = UserService.adduser(adduser);
		if (regid != null) {
			modelMap.put("reguserId", regid);
			return modelMap;
		} else
			throw new WebException("用户创建失败");
		
	}

	@ResponseBody
	@RequestMapping(value="/myInfo",method = RequestMethod.GET)
	public Map<String, Object> getInfo(@CookieValue("logincode") String code) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String userid = getUserIdByCode(code);
		User user = UserService.getInfo(new Integer(userid));
		if(user==null)
			throw new WebException("用户信息获取失败");
		else
		{
			modelMap.put("user", user);
			return modelMap;
		}
		
	}

	@ResponseBody
	@RequestMapping(value="/changeInfo",method = RequestMethod.PUT)
	public Map<String, Object> changeMyInfoDTO(@RequestBody ChangeMyInfoDTO info, @CookieValue("logincode")String code) {
		String userid = getUserIdByCode(code);
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (UserService.changeMyInfoDTO(info,Integer.valueOf(userid)))
		{
			modelMap.put("state", "success");
			return modelMap;
		}
		else
			throw new WebException("用户信息修改失败");
		
	}

}

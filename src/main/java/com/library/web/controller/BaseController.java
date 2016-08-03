package com.library.web.controller;

import com.library.web.service.imp.LoginCodeService;

public class BaseController {

	
	static String getUserIdByCode(String code){
		
		return String.valueOf(LoginCodeService.getIdByLogincode(code).getUserid());
	}
}

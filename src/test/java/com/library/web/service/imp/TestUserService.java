package com.library.web.service.imp;

import org.junit.Assert;
import org.junit.Test;

import com.library.web.bean.User;
import com.library.web.dto.AddUserDTO;
import com.library.web.dto.ChangeMyInfoDTO;
import com.library.web.dto.ChangePwdDTO;
import com.library.web.dto.LoginUserDTO;

public class TestUserService {

	
	@Test
	public void testLoginCheck()
	{
		LoginUserDTO login=new LoginUserDTO();
		login.setUsername(1);
		login.setPassword("123456");
		login.setMode(1);
		UserService.LoginCheck(login);
		Assert.assertNotNull(UserService.LoginCheck(login));
	}
	@Test
	public void testAddUser()
	{
		AddUserDTO addUserDTO=new AddUserDTO();
		addUserDTO.setPassword("123");
		addUserDTO.setSex(0);
		addUserDTO.setGrader("2012");
		addUserDTO.setUsername("wwy");
		addUserDTO.setMode(0);
		addUserDTO.setTwopassword("123");
		Assert.assertNotNull(UserService.adduser(addUserDTO));
	}
	
	@Test
	public void testChangePassword()
	{
		ChangePwdDTO changeDto=new ChangePwdDTO();
		changeDto.setOldpassword("1234");
		changeDto.setNewpassword("123");
		changeDto.setNewtwopassword("123");
		Assert.assertTrue(UserService.changePassword(changeDto, 2));
	}
	@Test
	public void testGetInfo()
	{
		Assert.assertNotNull(UserService.getInfo(1));
	}
	@Test
	public void testChangeMyInfoDTO()
	{
		ChangeMyInfoDTO change=new ChangeMyInfoDTO();
		change.setSexshow(1);
		change.setModeshow(1);
		change.setNameshow("weweiwe");
		Assert.assertTrue(UserService.changeMyInfoDTO(change, 3));
	}
	

}

package com.library.web.service.imp;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class LoginCodeServiceTest {

	private String code;
	@Test
	public void testCreateLoginCode() {
		code=LoginCodeService.CreateLoginCode(2);
		Assert.assertNotNull(code);
	}

	@Test
	public void testHasLive() {
		Assert.assertTrue(LoginCodeService.hasLive(code));
	}

	@Test
	public void testGetIdByLogincode() {
		Assert.assertNotNull(LoginCodeService.getIdByLogincode(code));
	}
	@Test
	public void testDeleteLoginCode() {
		Assert.assertTrue(LoginCodeService.deleteLoginCode(code));
	}

	

}

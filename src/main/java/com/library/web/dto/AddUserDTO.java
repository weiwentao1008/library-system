package com.library.web.dto;

public class AddUserDTO {

	private String username;
	private String password;
	private String twopassword;
	private Integer mode;
	private Integer sex;
	private String grader;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTwopassword() {
		return twopassword;
	}
	public void setTwopassword(String twopassword) {
		this.twopassword = twopassword;
	}
	public Integer getMode() {
		return mode;
	}
	public void setMode(Integer mode) {
		this.mode = mode;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getGrader() {
		return grader;
	}
	public void setGrader(String grader) {
		this.grader = grader;
	}
}

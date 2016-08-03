package com.library.web.dto;

public class ChangePwdDTO {

	private String oldpassword;
	private String newpassword;
	private String newtwopassword;
	public String getOldpassword() {
		return oldpassword;
	}
	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getNewtwopassword() {
		return newtwopassword;
	}
	public void setNewtwopassword(String newtwopassword) {
		this.newtwopassword = newtwopassword;
	}

}

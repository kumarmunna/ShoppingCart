package com.shoppingcart.Model;

public class LoginBean {

	private String username;
	private String password;
	private boolean exist = true;
	private boolean notexist = false;
	
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
	public boolean isExist() {
		return exist;
	}
	public void setExist(boolean exist) {
		this.exist = exist;
	}
	public boolean isNotexist() {
		return notexist;
	}
	public void setNotexist(boolean notexist) {
		this.notexist = notexist;
	}
		
	
}

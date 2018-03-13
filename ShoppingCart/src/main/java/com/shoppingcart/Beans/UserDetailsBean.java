package com.shoppingcart.Beans;


public class UserDetailsBean {

	private String username;
	private String password;
	private String userrole;
	private boolean active;
	private String name;
	private String address;
	private String phone;
	private String email;
	private String flowType;

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

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFlowType() {
		return flowType;
	}

	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}

	@Override
	public String toString() {
		return "UserDetailsBean [username=" + username + ", password="
				+ password + ", userrole=" + userrole + ", active=" + active
				+ ", name=" + name + ", address=" + address + ", phone="
				+ phone + ", email=" + email + ", flowType=" + flowType + "]";
	}

}

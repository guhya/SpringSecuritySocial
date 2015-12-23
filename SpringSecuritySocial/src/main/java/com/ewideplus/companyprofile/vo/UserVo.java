package com.ewideplus.companyprofile.vo;

import org.springframework.stereotype.Component;

@Component
public class UserVo extends CommonVo{

	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String enabled;
	
	private String roleString;
	
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getRoleString() {
		return roleString;
	}
	public void setRoleString(String roleString) {
		this.roleString = roleString;
	}
	
	@Override
	public String toString() {
		return "UserVo [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", enabled=" + enabled + ", getSeq()=" + getSeq() + ", getRegId()=" + getRegId()
				+ ", getRegIp()=" + getRegIp() + ", getRegDate()=" + getRegDate() + ", getModId()=" + getModId()
				+ ", getModIp()=" + getModIp() + ", getModDate()=" + getModDate() + ", getDelYn()=" + getDelYn() + "]";
	}
	

}

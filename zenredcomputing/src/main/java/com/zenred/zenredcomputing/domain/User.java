package com.zenred.zenredcomputing.domain;

public class User {
	
	private Integer User_id;
	private UserStatus user_Status;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String password;
	
	public Integer getUser_id() {
		return User_id;
	}
	public void setUser_id(Integer User_id) {
		this.User_id = User_id;
	}
	public UserStatus getUser_Status() {
		return user_Status;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setUser_Status(UserStatus user_Status) {
		this.user_Status = user_Status;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [User_id=" + User_id + ", user_Status=" + user_Status
				+ ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailAddress=" + emailAddress + ", password=" + password
				+ "]";
	}
}

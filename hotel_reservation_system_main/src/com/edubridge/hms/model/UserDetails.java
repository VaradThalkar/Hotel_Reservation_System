package com.edubridge.hms.model;

public class UserDetails {
	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", userName=" + userName + ", Password=" + Password + ", name=" + name
				+ ", contactNumber=" + contactNumber + ", adhaarNumber=" + adhaarNumber + "]";
	}
	int userId;
	String userName;
	String Password;
	String name;
	long contactNumber;
	long adhaarNumber;
	public UserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}
	public long getAdhaarNumber() {
		return adhaarNumber;
	}
	public void setAdhaarNumber(long adhaarNumber) {
		this.adhaarNumber = adhaarNumber;
	}
}

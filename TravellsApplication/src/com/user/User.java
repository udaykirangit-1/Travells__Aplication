package com.user;

public class User {
	
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String gender;
	private String mail;
	private String password;
	private int failedCount;
	private String accountStatus;
	private String loggeString;
	
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
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getFailedCount() {
		return failedCount;
	}
	
	public void setFailedCount(int failedCount) {
		this.failedCount = failedCount;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	
	
	
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", mobileNumber=" + mobileNumber
				+ ", gender=" + gender + ", mail=" + mail + ", password=" + password + ", failedCount=" + failedCount
				+ ", accountStatus=" + accountStatus + ", loggeString=" + loggeString + "]";
	}
	public User() {
		
	}
	
	public User(String firstName, String lastName, String mobileNumber, String gender, String mail, String password,
			int failedCount, String accountStatus, String loggeString) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.gender = gender;
		this.mail = mail;
		this.password = password;
		this.failedCount = failedCount;
		this.accountStatus = accountStatus;
		this.loggeString = loggeString;
	}
	public String getLoggeString() {
		return loggeString;
	}
	public void setLoggeString(String loggeString) {
		this.loggeString = loggeString;
	}

}

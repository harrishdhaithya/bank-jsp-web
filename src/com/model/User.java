package com.model;

import java.util.Random;
import org.apache.commons.codec.digest.DigestUtils;

public class User {
	// private UUID accno;
	private String accno;
	private String fname;
	private String lname;
	private String phone;
	private String email;
	private double balance;
	private String passwordHash;
	public User(String fname, String lname, String phone, String email, String password, double balance) {
		// this.accno = UUID.randomUUID();
		this.accno = createAccNo();
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
		this.email = email;
//		this.password = password;
		this.balance = balance;
		this.passwordHash = getHash(password);
	}
	public User(String accno, String fname, String lname, String phone, String email,String passwordHash, double balance) {
		this.accno = accno;
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
		this.email = email;
		this.balance = balance;
		this.passwordHash = passwordHash;
		
	}
	private static String getHash(String password) {
		String hash = DigestUtils.sha256Hex(password);
		return hash;
	}
	private static String createAccNo(){
		String accno = "";
		Random rand = new Random();
		for(int i=0;i<10;i++){
			accno+=rand.nextInt(10);
		}
		return accno;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
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
	public String getAccno() {
		return accno;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public boolean evalPassword(String password) {
		return this.passwordHash.equals(getHash(password));
	}
	@Override
	public String toString() {
		return "Account Number: "+accno+"\n"+
				"Name: "+fname+" "+lname+"\n"+
				"Email: "+email+"\n"+
				"Phone Number: "+phone+"\n"+
				"Balance: "+balance
				;
	}
}

package com.model;

import java.util.Random;
import org.apache.commons.codec.digest.DigestUtils;

public class Admin {
	// private UUID empid;
	private String empid;
	private String name;
	private String phone;
	private String email;
//	private String password;
	private String passwordHash;
	public Admin(String name, String phone, String email, String password) {
		this.empid = createAccNo();
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.passwordHash=getHash(password);
	}
	public Admin(String empid, String name, String phone, String email, String passwordHash) {
		this.empid = empid;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.passwordHash = passwordHash;
	}
	private static String createAccNo(){
		String accno = "";
		Random rand = new Random();
		for(int i=0;i<10;i++){
			accno+=rand.nextInt(10);
		}
		return accno;
	}
	public static String getHash(String password) {
		String hash = DigestUtils.sha256Hex(password);
		return hash;
	}
	public String getEmpid() {
		return empid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	public boolean evalPassword(String password) {
		return this.passwordHash.equals(getHash(password));
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	@Override
	public String toString() {
		return "Employee Id: "+empid+"\n"+
				"Name: "+name+"\n"+
				"Phone: "+phone+"\n"+
				"Email: "+email
				; 
	}
	
}

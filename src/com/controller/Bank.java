package com.controller;

import com.Singleton.Singleton;
import com.dao.TransactionDao;
import com.dao.UserDao;
import com.dao.UserSecretDao;
import com.model.Admin;
import com.model.User;
import com.model.UserSecret;


public class Bank {
	public static boolean signup(String fname, String lname, String phone, String email, String password,double balance) {
		if(phone.length()!=10) {
			return false;
		}
		User user = null;
		UserSecret us = null;
		UserDao u = Singleton.getUserDao();
		UserSecretDao usdao = Singleton.getUserSecretDao();
		String secret = TwoFAAuth.generateSecretKey();
		user = new User(fname,lname,phone,email,password,balance);
		us = new UserSecret(user.getAccno(),secret);
		boolean success = u.saveUser(user);
		if(!success){
			return false;
		}
		System.out.println("Your Secret for Google Authenticator is "+secret);
		return success&&usdao.saveSecret(us);
	}
	public static boolean deposit(String accno,double amount) {
		UserDao u = Singleton.getUserDao();
		User user = u.getUserByAccno(accno);
		user.setBalance(user.getBalance()+amount);
		return u.saveUser(user);
	}
	public static boolean withdraw(String accno,double amount) {
		UserDao u = Singleton.getUserDao();
		User user = u.getUserByAccno(accno);
		if(user.getBalance()<amount){
			return false;
		}
		user.setBalance(user.getBalance()-amount);
		return u.saveUser(user);
	}
	public static boolean makeTransaction(String srcacc,String to,double amount) {
		UserDao u = Singleton.getUserDao();
		if(srcacc.equals(to)){
			return false;
		}
		User src = u.getUserByAccno(srcacc);
		User dest = u.getUserByAccno(to);
		if(src==null||dest==null){
			return false;
		}
		TransactionDao tdao = Singleton.getTransactionDao();
		try{
			return tdao.performTransaction(src, dest, amount);
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
	}
}

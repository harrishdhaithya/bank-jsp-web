package com.controller;

import java.util.Properties;
import java.util.Random;
import java.util.Scanner;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Otp {
	public static String generateOTP() {
		Random rand = new Random();
		String otp = "";
		for(int i=0;i<4;i++) {
			otp = otp+rand.nextInt(10);
		}
		return otp;
	}
	public static boolean evalOtp(String reciptant) {
		String mailid = System.getenv("email");
		String password = System.getenv("password");
		String host = "smtp.gmail.com";
		Properties prop = System.getProperties();
		prop.setProperty("mail.smtp.host", host);
		prop.setProperty("mail.smtp.port", "465");
		prop.setProperty("mail.smtp.ssl.enable", "true");
		prop.setProperty("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.required", "true");
		prop.put("mail.smtp.ssl.protocols", "TLSv1.2");
		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailid, password);
			}
		});
		String otp = generateOTP();
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mailid));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(reciptant));
			message.setSubject("Your One Time Password");
			message.setText("OTP: "+otp);
			Transport.send(message);
		}catch(MessagingException e) {
			System.out.println("Could not Send OTP");
			System.out.println(e);
			return false;
		}
		Scanner in = new Scanner(System.in);
		System.out.println("Enter Your 4 Digit OTP: ");
		String enteredOtp = in.nextLine();
//		System.out.println(enteredOtp);
		if(otp.equals(enteredOtp)) {
			return true;
		}
		return false;
	}
}

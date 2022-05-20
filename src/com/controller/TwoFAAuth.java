package com.controller;

import java.security.SecureRandom;
import java.util.Scanner;
import com.Singleton.Singleton;
import com.dao.AdminSecretDao;
import com.dao.UserSecretDao;
import com.model.Admin;
import com.model.User;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;
import de.taimos.totp.TOTP;

public class TwoFAAuth {
	public static String getTOTPCode(String secretKey) {
	    Base32 base32 = new Base32();
	    byte[] bytes = base32.decode(secretKey);
//	    System.out.println(bytes.toString());
	    String hexKey = Hex.encodeHexString(bytes);
	    return TOTP.getOTP(hexKey);
	}
	public static String generateSecretKey() {
	    SecureRandom random = new SecureRandom();
	    byte[] bytes = new byte[20];
	    random.nextBytes(bytes);
	    Base32 base32 = new Base32();
	    return base32.encodeToString(bytes);
	}
	public static boolean twoFA(User user) {
//		String secret = Bank.userSecrets.get(user);
		UserSecretDao usdao = Singleton.getUserSecretDao();
		String secret = usdao.getSecret(user.getAccno());
		if(secret==null){
			return false;
		}
//		System.out.println(secret);
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the 6 digit key: ");
		String code = in.nextLine();
		if (code.equals(getTOTPCode(secret))) {
		    return true;
		} else {
		    System.out.println("Invalid 2FA Code");
		}
		return false;
	}
	public static boolean twoFA(Admin admin) {
//		String secret = Bank.adminSecrets.get(admin);
		AdminSecretDao asdao = Singleton.getAdminSecretDao();
		String secret = asdao.getSecret(admin.getEmpid());
		if(secret==null){
			System.out.println("No Secrets found...");
			return false;
		}
//		System.out.println(secret);
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the 6 digit key: ");
		String code = in.nextLine();
		if (code.equals(getTOTPCode(secret))) {
			System.out.println("Login Success ");
		    return true;
		} else {
		    System.out.println("Invalid 2FA Code");
		}
		return false;
	}
}

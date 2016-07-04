package com.klaus.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class SecurityCoder {

	public static final String KEY_SHA = "SHA";

	/**
	 * SHAº”√‹
	 * 
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encryptSHA(String coderDate) {
		
		if(coderDate==null){
			return null;
		}
		
		try{
			
		/*	byte[] data=coderDate.getBytes();

			MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
			sha.update(data);

			return sha.digest().toString();
			*/
			
			
			
			byte[] data=coderDate.getBytes();

			MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
			sha.update(data);

			
			
			BigInteger shal = new BigInteger(sha.digest());
			
			
			return shal.toString(32).substring(1);
			
			
		}catch(Exception e){}
		
		return null;

	}
	
}

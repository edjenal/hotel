package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
public class ConvertPasswordToMD5 {
 
    public static String convertPasswordToMD5(String password) {
        MessageDigest md;
		
        try {
			md = MessageDigest.getInstance("MD5");
			BigInteger hash = new BigInteger(1, md.digest(password.getBytes()));
			 
	        return String.format("%32x", hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
 
        return null;
    }
 
}

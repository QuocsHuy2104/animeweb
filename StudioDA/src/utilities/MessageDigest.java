package utilities;

import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class MessageDigest {

	public static String getMD5(String password) throws NoSuchAlgorithmException {
		java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		md.update(password.getBytes());

		byte[] digest = md.digest();
		String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		return myHash;

	}

	public static boolean verify(String inputPassword, String hashPassWord) throws NoSuchAlgorithmException {
		java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		md.update(inputPassword.getBytes());
		byte[] digest = md.digest();
		String myChecksum = DatatypeConverter.printHexBinary(digest).toUpperCase();
		return hashPassWord.equals(myChecksum);
	}
}

package com.paralucent.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncrypSHA256 {

    public String byte2hex(byte[] b) {
	String hs = "";
	String stmp = "";
	for (int n = 0; n < b.length; n++) {
	    stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
	    if (stmp.length() == 1)
		hs = hs + "0" + stmp;
	    else
		hs = hs + stmp;
	}
	return hs.toUpperCase();
    }

    public String genEncrypSHA256(String message) {
	MessageDigest digest = null;
	try {
	    digest = MessageDigest.getInstance("SHA-256");
	} catch (NoSuchAlgorithmException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	digest.update(message.getBytes());
	return byte2hex(digest.digest());
    }
}

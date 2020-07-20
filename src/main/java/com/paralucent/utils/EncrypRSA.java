package com.paralucent.utils;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class EncrypRSA {

    /**
     * 加密
     * 
     * @param publicKey
     * @param srcBytes
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public byte[] encrypt(RSAPublicKey publicKey, byte[] srcBytes)
	    throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
	if (publicKey == null) {
	    return null;
	}
	// Cipher 負責完成加密或解密工作，基於 RSA
	Cipher cipher = Cipher.getInstance("RSA");
	// 根據公鑰，對 Cipher 物件進行初始化
	cipher.init(Cipher.ENCRYPT_MODE, publicKey);
	byte[] resultBytes = cipher.doFinal(srcBytes);
	return resultBytes;
    }

    /**
     * 解密
     * 
     * @param privateKey
     * @param srcBytes
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public byte[] decrypt(RSAPrivateKey privateKey, byte[] srcBytes)
	    throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
	if (privateKey == null) {
	    return null;
	}
	// Cipher負責完成加密或解密工作，基於RSA
	Cipher cipher = Cipher.getInstance("RSA");
	// 根據公鑰，對 Cipher 物件進行初始化
	cipher.init(Cipher.DECRYPT_MODE, privateKey);
	byte[] resultBytes = cipher.doFinal(srcBytes);
	return resultBytes;
    }

    public void genEncypRSA()
	    throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
	EncrypRSA rsa = new EncrypRSA();
	String msg = "郭XX-精品相聲";
	// KeyPairGenerator 類用於生成公鑰和私鑰對，基於 RSA 演算法生成物件
	KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
	// 初始化密鑰對生成器，密鑰大小為 1024 位
	keyPairGen.initialize(1024);
	// 生成一個密鑰對，保存在 keyPair 中
	KeyPair keyPair = keyPairGen.generateKeyPair();
	// 得到私鑰
	RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
	// 得到公鑰
	RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
	// 用公鑰加密
	byte[] srcBytes = msg.getBytes();
	byte[] resultBytes = rsa.encrypt(publicKey, srcBytes);
	// 用私鑰解密
	byte[] decBytes = rsa.decrypt(privateKey, resultBytes);
	System.out.println("明文是:" + msg);
	System.out.println("加密後是:" + new String(resultBytes));
	System.out.println("解密後是:" + new String(decBytes));
    }
}

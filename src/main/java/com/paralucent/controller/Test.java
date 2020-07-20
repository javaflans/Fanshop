package com.paralucent.controller;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.crypto.NoSuchPaddingException;

public class Test {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException {
	System.out.println(UUID.randomUUID().toString().replace("-", "").toUpperCase().length());
    }
}

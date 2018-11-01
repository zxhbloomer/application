package com.main;

import java.util.Random;

import static com.main.RSAEncrypt.*;
import static java.lang.Math.abs;

public class MainApplication {

	/**
	 * 非对称加密
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		String filepath="E:/";

		StringBuilder sb = new StringBuilder();

		for(int i=0;i<1000;i++){ sb.append(abs(new Random().nextLong()));}

		String  json = sb.toString();

		//公钥加密过程
		byte[] cipherData=encrypt(loadPublicKeyByStr(loadPublicKeyByFile(filepath)),json.getBytes());

		String cipher= Base64One.encode(cipherData);

		//私钥解密过程
		byte[] res=RSAEncrypt.decrypt(loadPrivateKeyByStr(loadPrivateKeyByFile(filepath)), Base64One.decode(cipher));

		String origin=new String(res);

		System.out.println("原文："+json);
		System.out.println("加密："+cipher);
		System.out.println("解密："+origin);

	}

}

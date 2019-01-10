package com.nsu.util.encrypt;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;


public class Main {
	public static void main(String[] args) throws Exception {

		KeyPair keyPair = RSAUtil.getKeyPair();
		
		RSAPublicKey pubKey = (RSAPublicKey) keyPair.getPublic();

		System.out.println("-");
		System.out.println(pubKey.getPublicExponent().toString());
		System.out.println("-");

		RSAPrivateKey priKey = (RSAPrivateKey) keyPair.getPrivate();


		System.out.println("-");
		System.out.println(priKey.getPrivateExponent().toString());
		System.out.println("-");



		String result =RSAUtil.encryptStringByJs(pubKey, "123456");

		System.out.println(result);

		System.out.println(Config.result);

		System.out.println(result.equals(Config.result));

		RSAPublicKey defaultPublicKey = RSAUtil.getDefaultPublicKey();

		RSAPrivateKey defaultPrivateKey = RSAUtil.getDefaultPrivateKey();

		String encryptStringByJs = RSAUtil.encryptStringByJs("123456");

		String result1=RSAUtil.decryptStringByJs(encryptStringByJs);

		System.out.println(new String(result1));
	}
}

package com.chaksiva.testclasses;

import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {

	public static void main(String[] args) {
		String x = null;
		System.out.println(String.valueOf(x).trim().isEmpty());
		String password = "admin@123";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);

		 System.out.println(hashedPassword);

		try {
			String enc = SimpleCrypto.encrypt("123456");
			String dec = SimpleCrypto.decrypt(enc);
			System.out.println(enc + "<->" + dec);

			// String ciphertext = encrypt(key, "1234567890123456");
			// System.out.println(ciphertext);
			// System.out.println("decrypted value:" + (decrypt(key,
			// ciphertext)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String encrypt(String key, String value)
			throws GeneralSecurityException {

		byte[] raw = key.getBytes(Charset.forName("US-ASCII"));
		if (raw.length != 16) {
			throw new IllegalArgumentException("Invalid key size.");
		}

		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(
				new byte[16]));
		byte[] encryptedString = cipher.doFinal(value.getBytes(Charset
				.forName("US-ASCII")));
		System.out.println(encryptedString);
		return new String(encryptedString);
	}

	public static String decrypt(String key, String encrypted)
			throws GeneralSecurityException {

		byte[] raw = key.getBytes(Charset.forName("US-ASCII"));
		if (raw.length != 16) {
			throw new IllegalArgumentException("Invalid key size.");
		}
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(
				new byte[16]));
		byte[] original = cipher.doFinal(encrypted.getBytes());

		return new String(original, Charset.forName("US-ASCII"));
	}

}

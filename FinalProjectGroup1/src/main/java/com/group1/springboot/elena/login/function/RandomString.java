package com.group1.springboot.elena.login.function;


import java.util.Random;

public class RandomString {
	
	public RandomString() {
	}
	
	public String generateTempPassword(int pwlength) {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String randompassword = "";
		int length = pwlength;
		Random rand = new Random();
		
		char[] temtext = new char[length];
		
		for(int i=0; i<length; i++) {
			temtext[i]=characters.charAt(rand.nextInt(26));
		}
		
		for(int i=0; i<length; i++) {
			randompassword += temtext[i];
		}
		
		return randompassword;
		
		
	
		
		
		
	}

}

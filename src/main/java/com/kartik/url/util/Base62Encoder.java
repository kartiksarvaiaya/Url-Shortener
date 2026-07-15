package com.kartik.url.util;

public final class Base62Encoder {
	private static final String CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final int BASE = 62;
	private Base62Encoder() {
		
	}
	
	public static String encode(long number) {
		 if(number == 0)
			 return "0";
		 StringBuilder sb = new StringBuilder();
		 while(number > 0) {
			 int remainder = (int) number % BASE;
			 sb.append(CHARACTERS.charAt(remainder));
			 number /= BASE;
		 }
		 return sb.reverse().toString();
	 }
	 
	 public static long decode(String shortCode) {
		 long number = 0;
		 for(char c : shortCode.toCharArray())
		 {
			 number = number * BASE + CHARACTERS.indexOf(c);
		 }
		 return number;
	 }
}

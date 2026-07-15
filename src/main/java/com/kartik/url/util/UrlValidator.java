package com.kartik.url.util;
import java.net.URI;
public class UrlValidator {
	private UrlValidator() {
		
	}
	
	public static boolean isValid(String url) {
		try {
			URI uri = new URI(url);
			return uri.getScheme() != null
					&& uri.getHost() != null;
 		}
		catch (Exception e) {
			return false;
		}
	}
}

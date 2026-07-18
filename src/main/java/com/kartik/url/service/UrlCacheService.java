package com.kartik.url.service;

public interface UrlCacheService {
	String get(String shortCode);
	void put(String shortCode, String originalUrl);
	void delete(String shortCode);
}

package com.kartik.url.service;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.kartik.url.cache.CacheConstants;

@Service
public class UrlCacheServiceImpl implements UrlCacheService{
	private final RedisTemplate<String ,String> redisTemplate;
	private static final Duration TTL = Duration.ofDays(30);
	public UrlCacheServiceImpl(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	@Override
	public String get(String shortCode) {
		return redisTemplate.opsForValue()
				.get(CacheConstants.SHORT_URL_PREFIX + shortCode);
	}
	
	@Override
	public void put(String shortCode, String originalUrl) {
		redisTemplate.opsForValue().set(CacheConstants.SHORT_URL_PREFIX + shortCode, originalUrl, TTL);
	}
	
	@Override
	public void delete(String shortCode) {
		redisTemplate.delete(CacheConstants.SHORT_URL_PREFIX + shortCode);
	}
}

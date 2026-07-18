package com.kartik.url.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kartik.url.entity.Url;
import com.kartik.url.exception.ResourceNotFoundException;
import com.kartik.url.exception.UrlExpiredException;
import com.kartik.url.repository.UrlRepository;

@Service
public class UrlReadServiceImpl implements UrlReadService{
	private final UrlRepository urlRepository;
	private final UrlCacheService cacheService;
	public UrlReadServiceImpl(UrlRepository urlRepository, UrlCacheService cacheService) {
		this.urlRepository = urlRepository;
		this.cacheService = cacheService;
	}
	
	@Override
	@Transactional
	public String getOriginalUrl(String shortCode) {
		// Search in cache
		String originalUrl = cacheService.get(shortCode);
		if(originalUrl != null) {
			System.out.println("Cache Hit");
			return originalUrl;
		}
		System.out.println("Cache Miss");
		// Load from Database
        Optional<Url> optionalUrl = urlRepository.findByShortCode(shortCode);
        if(optionalUrl.isEmpty()) throw new RuntimeException("ShortUrl not found");
        Url url = optionalUrl.get();
        cacheService.put(shortCode, url.getOriginalUrl());
        return url.getOriginalUrl();
	}
}

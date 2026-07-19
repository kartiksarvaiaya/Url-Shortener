package com.kartik.url.service;

import org.springframework.stereotype.Service;

import com.kartik.url.dto.UrlAnalyticsResponse;
import com.kartik.url.entity.Url;
import com.kartik.url.exception.ResourceNotFoundException;
import com.kartik.url.repository.UrlRepository;

@Service
public class UrlAnalyticsServiceImpl implements UrlAnalyticsService {
    private final UrlRepository urlRepository;
    public UrlAnalyticsServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }
    @Override
    public UrlAnalyticsResponse getAnalytics(String shortCode) {
    	Url url = urlRepository.findByShortCode(shortCode)
    			.orElseThrow(() -> new ResourceNotFoundException("Short Url not found"));
    	return new UrlAnalyticsResponse(
    			url.getOriginalUrl(),
    			url.getShortCode(),
    			url.getClickCount(),
    			url.getCreatedAt(),
    			url.getLastAccessedAt(),
    			url.getExpiresAt()
    		);
    }
}

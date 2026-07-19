package com.kartik.url.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kartik.url.dto.CreateShortUrlRequest;
import com.kartik.url.dto.ShortUrlResponse;
import com.kartik.url.dto.UrlAnalyticsResponse;
import com.kartik.url.service.UrlAnalyticsService;
import com.kartik.url.service.UrlReadService;
import com.kartik.url.service.UrlWriteService;

import jakarta.validation.Valid;

@RestController
public class UrlController {
	private final UrlReadService urlReadService;
	private final UrlWriteService urlWriteService;
	private final UrlAnalyticsService urlAnalyticsService;
	public UrlController(UrlReadService urlReadService, UrlWriteService urlWriteService, UrlAnalyticsService urlAnalyticsService) {
		this.urlReadService = urlReadService;
		this.urlWriteService = urlWriteService;
		this.urlAnalyticsService = urlAnalyticsService;
	}
	
	@PostMapping("/urls")
	public ResponseEntity<ShortUrlResponse> createShortUrl(@Valid @RequestBody CreateShortUrlRequest request){
			ShortUrlResponse response = urlWriteService.createShortUrl(request);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(response);
	}
	
	@GetMapping("/{shortCode}")
	public ResponseEntity<Void> redirect (@PathVariable String shortCode)
	{
		String originalUrl = urlReadService.getOriginalUrl(shortCode);
		return ResponseEntity.status(HttpStatus.FOUND)
				.location(URI.create(originalUrl))
				.build();
	}
	
	@GetMapping("/urls/{shortCode}/analytics")
	public ResponseEntity<UrlAnalyticsResponse> getAnalytics(@PathVariable String shortCode){
		return ResponseEntity.ok(urlAnalyticsService.getAnalytics(shortCode));
	}
}

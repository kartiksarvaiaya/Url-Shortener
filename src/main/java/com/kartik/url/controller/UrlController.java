package com.kartik.url.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kartik.url.dto.CreateShortUrlRequest;
import com.kartik.url.dto.ShortUrlResponse;
import com.kartik.url.service.UrlReadService;
import com.kartik.url.service.UrlWriteService;

import jakarta.validation.Valid;

@RestController
public class UrlController {
	private final UrlReadService urlReadService;
	private final UrlWriteService urlWriteService;
	public UrlController(UrlReadService urlReadService, UrlWriteService urlWriteService) {
		this.urlReadService = urlReadService;
		this.urlWriteService = urlWriteService;
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
				.build();
	}
}

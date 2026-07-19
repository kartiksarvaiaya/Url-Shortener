package com.kartik.url.dto;

import java.time.LocalDateTime;

public class UrlAnalyticsResponse {

    private String originalUrl;
    private String shortCode;
    private Long clickCount;
    private LocalDateTime createdAt;
    private LocalDateTime lastAccessedAt;
    private LocalDateTime expiresAt;
	public String getOriginalUrl() {
		return originalUrl;
	}
	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}
	public String getShortCode() {
		return shortCode;
	}
	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}
	public Long getClickCount() {
		return clickCount;
	}
	public void setClickCount(Long clickCount) {
		this.clickCount = clickCount;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getLastAccessedAt() {
		return lastAccessedAt;
	}
	public void setLastAccessedAt(LocalDateTime lastAccessedAt) {
		this.lastAccessedAt = lastAccessedAt;
	}
	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}
	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt;
	}
	public UrlAnalyticsResponse(String originalUrl, String shortCode, Long clickCount, LocalDateTime createdAt,
			LocalDateTime lastAccessedAt, LocalDateTime expiresAt) {
		super();
		this.originalUrl = originalUrl;
		this.shortCode = shortCode;
		this.clickCount = clickCount;
		this.createdAt = createdAt;
		this.lastAccessedAt = lastAccessedAt;
		this.expiresAt = expiresAt;
	}

}
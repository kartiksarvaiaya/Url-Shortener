package com.kartik.url.service;

import com.kartik.url.dto.CreateShortUrlRequest;
import com.kartik.url.dto.ShortUrlResponse;

public interface UrlWriteService {
	ShortUrlResponse createShortUrl(CreateShortUrlRequest request);
}

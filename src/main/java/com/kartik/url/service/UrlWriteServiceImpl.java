package com.kartik.url.service;

import org.hibernate.cache.CacheException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.InvalidUrlException;

import com.kartik.url.dto.CreateShortUrlRequest;
import com.kartik.url.dto.ShortUrlResponse;
import com.kartik.url.entity.Url;
import com.kartik.url.repository.UrlRepository;
import com.kartik.url.util.Base62Encoder;
import com.kartik.url.util.UrlValidator;

@Service
public class UrlWriteServiceImpl implements UrlWriteService{
	private final UrlRepository urlRepository;
	private final UrlCacheService cacheService;
	@Value("${app.base-url}")
	private String baseUrl;
    public UrlWriteServiceImpl(UrlRepository urlRepository, UrlCacheService cacheService) {
        this.urlRepository = urlRepository;
        this.cacheService = cacheService;
    }
    @Override
    @Transactional
    public ShortUrlResponse createShortUrl(CreateShortUrlRequest request) {

        if (!UrlValidator.isValid(request.getOriginalUrl())) {
            throw new InvalidUrlException("Invalid Url");
        }

        // Check if URL already exists
        Url existingUrl = urlRepository
                .findByOriginalUrl(request.getOriginalUrl())
                .orElse(null);

        if (existingUrl != null) {

            try {
                cacheService.put(
                        existingUrl.getShortCode(),
                        existingUrl.getOriginalUrl());
            } catch (Exception e) {
                // Ignore cache failure
            }

            ShortUrlResponse response = new ShortUrlResponse();
            response.setOriginalUrl(existingUrl.getOriginalUrl());
            response.setShortUrl(baseUrl + "/" + existingUrl.getShortCode());

            return response;
        }

        // Create new record
        Url url = new Url();
        url.setOriginalUrl(request.getOriginalUrl());

        url = urlRepository.save(url);

        String shortCode = Base62Encoder.encode(url.getId());

        url.setShortCode(shortCode);

        url = urlRepository.save(url);

        try {
            cacheService.put(
                    url.getShortCode(),
                    url.getOriginalUrl());
        } catch (Exception e) {
            // Ignore cache failure
        }

        ShortUrlResponse response = new ShortUrlResponse();
        response.setOriginalUrl(url.getOriginalUrl());
        response.setShortUrl(baseUrl + "/" + url.getShortCode());

        return response;
    }
}

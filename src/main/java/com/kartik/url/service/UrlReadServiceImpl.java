package com.kartik.url.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kartik.url.entity.Url;
import com.kartik.url.exception.ResourceNotFoundException;
import com.kartik.url.exception.UrlExpiredException;
import com.kartik.url.repository.UrlRepository;

@Service
public class UrlReadServiceImpl implements UrlReadService{
	private final UrlRepository urlRepository;
	public UrlReadServiceImpl(UrlRepository urlRepository) {
		this.urlRepository = urlRepository;
	}
	
	@Override
	@Transactional
	public String getOriginalUrl(String shortCode) {
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Short URL not found"));

        if (url.getExpiresAt() != null &&
                url.getExpiresAt().isBefore(LocalDateTime.now())) {

            throw new UrlExpiredException("Short URL has expired");
        }

        url.setClickCount(url.getClickCount() + 1);

        return url.getOriginalUrl();
	}
}

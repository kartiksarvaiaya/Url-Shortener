package com.kartik.url.service;

import com.kartik.url.dto.UrlAnalyticsResponse;

public interface UrlAnalyticsService {

    UrlAnalyticsResponse getAnalytics(String shortCode);

}
package com.project.ecommerce.ratelimit;

import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RateLimitConfig {

    public RateLimitRule loginRule() {
        return new RateLimitRule(5, Duration.ofMinutes(1));
    }

    public RateLimitRule registerRule() {
        return new RateLimitRule(3, Duration.ofMinutes(10));
    }

    public RateLimitRule userRule() {
        return new RateLimitRule(60, Duration.ofMinutes(1));
    }
}

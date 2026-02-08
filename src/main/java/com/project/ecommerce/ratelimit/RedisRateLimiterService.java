package com.project.ecommerce.ratelimit;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisRateLimiterService {

    private final StringRedisTemplate redisTemplate;
    private final RateLimitConfig config;

    public RedisRateLimiterService(StringRedisTemplate redisTemplate,
                                   RateLimitConfig config) {
        this.redisTemplate = redisTemplate;
        this.config = config;
    }

    public boolean allowRequest(String key, String path) {

        RateLimitRule rule = resolveRule(path);

        Long count = redisTemplate.opsForValue().increment(key);

        if (count != null && count == 1) {
            redisTemplate.expire(key, rule.window());
        }

        return count != null && count <= rule.limit();
    }

    private RateLimitRule resolveRule(String path) {

        if (path.contains("/login")) {
            return config.loginRule();
        }

        if (path.contains("/register")) {
            return config.registerRule();
        }

        return config.userRule();
    }
}

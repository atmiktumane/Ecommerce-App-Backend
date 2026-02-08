package com.project.ecommerce.ratelimit;

import java.time.Duration;

public record RateLimitRule(int limit, Duration window) {
}

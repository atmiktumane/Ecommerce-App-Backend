package com.project.ecommerce.ratelimit;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private final RedisRateLimiterService rateLimiter;

    public RateLimitFilter(RedisRateLimiterService rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // Apply only to auth APIs
        if (!path.startsWith("/api/v1/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        String ip = IpUtil.getClientIp(request);
        String key = "rate:" + path + ":" + ip;

        boolean allowed = rateLimiter.allowRequest(key, path);

        if (!allowed) {
            response.setStatus(429);
            response.getWriter().write("Too many requests");
            return;
        }

        filterChain.doFilter(request, response);
    }
}

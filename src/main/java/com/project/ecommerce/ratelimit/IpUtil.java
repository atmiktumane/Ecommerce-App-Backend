package com.project.ecommerce.ratelimit;

import jakarta.servlet.http.HttpServletRequest;

public class IpUtil {

    public static String getClientIp(HttpServletRequest request) {

        String xfHeader = request.getHeader("X-Forwarded-For");

        if (xfHeader != null && !xfHeader.isEmpty()) {
            return xfHeader.split(",")[0].trim();
        }

        return request.getRemoteAddr();
    }
}

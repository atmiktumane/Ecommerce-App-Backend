package com.project.ecommerce.ratelimit;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<RateLimitFilter> rateLimitFilterRegistration(
            RateLimitFilter filter) {

        FilterRegistrationBean<RateLimitFilter> bean =
                new FilterRegistrationBean<>();

        bean.setFilter(filter);
        bean.addUrlPatterns("/*");
        bean.setOrder(1);

        return bean;
    }
}

package com.project.ecommerce.config;

import com.project.ecommerce.dto.UserDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;

@Configuration
public class RedisConfig {

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory factory) {

        // 1. Store JSON in Redis
        // 2. Serialization + Deserialization
        // 3. TTL (Time To Live) -> Data Expiry Time

        ObjectMapper mapper = new ObjectMapper();

        Jackson2JsonRedisSerializer<UserDTO> serializer =
                new Jackson2JsonRedisSerializer<>(UserDTO.class);

        serializer.setObjectMapper(mapper);

        RedisCacheConfiguration config =
                RedisCacheConfiguration.defaultCacheConfig()
                        .serializeValuesWith(
                                RedisSerializationContext.SerializationPair
                                        .fromSerializer(serializer))
                        .entryTtl(Duration.ofMinutes(10));

        return RedisCacheManager.builder(factory)
                .cacheDefaults(config)
                .build();
    }
}

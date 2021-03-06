package com.ludensdomain.config;

import com.ludensdomain.util.RedisCacheKeyConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.ConfigureRedisAction;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static com.ludensdomain.util.RedisCacheKeyConstants.GAME_LIST;

@Configuration
public class RedisSessionConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {

        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisHost, redisPort));
    }

    @Bean
    public static ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }

    /**
     * String 자료형 위주로 데이터를 받기 위해 RedisTemplate StringRedisSerializer 사용.
     * GenericJackson2JsonRedisSerializer은 객체를 JSON 방식으로 변환
     *
     * @return redisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        return redisTemplate;
    }

    /**.
     * RedisCacheManager : Spring에서 Redis를 통한 cache 기능을 기존 스프링 코드를 최대한 유지시키며 안전하게 적용하기 위해
     * 사용되는 기능이다. 데이터 생존 주기, 캐시 데이터 타입 등 캐시의 여러 설정을 부여할 수 있다.
     * disableCachingNullValues : null값이 들어가지 않도록 설정
     * Serialization : 기존 Redis의 byte array 타입을 특정 데이터 타입으로 받기 위해 설정
     * GenericJackson2JsonRedisSerializer은 모든 Object를 JSON 형태로 변환해주지만 Class Type을 같이 저장시켜서
     * 동일한 Class Type(DTO)만으로 조회가 가능하다는 특징이 있다.
     * cacheConfigurationMap : 캐시 별로 유효시간을 정하기 위해 HashMap 데이터 타입으로 설정
     * Duration : 캐시의 수명 기간을 정의. 디폴트로 1시간 동안 캐시를 보유하도록 설정
     */
    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration
                .defaultCacheConfig()
                .disableCachingNullValues()
                .serializeKeysWith(RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(new GenericJackson2JsonRedisSerializer()))
                .entryTtl(Duration.ofHours(1));

        Map<String, RedisCacheConfiguration> cacheConfiguration = new HashMap<>();
        cacheConfiguration.put(GAME_LIST, redisCacheConfiguration.entryTtl(Duration.ofMinutes(10)));

        return RedisCacheManager
                .RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .build();
    }

}

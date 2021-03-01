package com.ludensdomain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
@EnableCaching
@EnableAspectJAutoProxy
public class LudensdomainApplication {

    public static void main(String[] args) {

        SpringApplication.run(LudensdomainApplication.class, args);
    }
}

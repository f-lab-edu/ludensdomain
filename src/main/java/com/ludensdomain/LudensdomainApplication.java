package com.ludensdomain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**.
 *
 * @EnableCaching Spring 어노테이션 기반 캐시 기능 적용
 * @EnableRedisHttpSession 세션을 통한 Redis 기능 적용(서버 확장에 의한 사용자 정보 통일성을 위함)
 * @EnableAspectJAutoProxy AOP를 이용하기 위한 프록시 기능 적용
 */

@SpringBootApplication
@EnableRedisHttpSession
@EnableCaching
@EnableAspectJAutoProxy
public class LudensdomainApplication {

    public static void main(String[] args) {

        SpringApplication.run(LudensdomainApplication.class, args);
    }
}

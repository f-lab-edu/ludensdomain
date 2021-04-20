package com.ludensdomain;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**.
 *
 * @EnableAspectJAutoProxy AOP를 이용하기 위한 프록시 기능 적용
 * @EnableCaching Spring 어노테이션 기반 캐시 기능 적용
 * @EnableEncryptableProperties Jasypt 사용에 필요한 암호화 기능 적용
 * @EnableRedisHttpSession 세션을 통한 Redis 기능 적용(서버 확장에 의한 사용자 정보 통일성을 위함)
 */

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableCaching
@EnableEncryptableProperties
@EnableRedisHttpSession
@Log4j2
public class LudensdomainApplication {

    public static void main(String[] args) {

        SpringApplication.run(LudensdomainApplication.class, args);
    }
}

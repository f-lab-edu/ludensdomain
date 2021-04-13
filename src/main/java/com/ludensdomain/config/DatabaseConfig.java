package com.ludensdomain.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public HikariDataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * DB 연결을 위한 sqlSessionFactoryBean 생성.
     *
     * @return factoryBean.getObject() 생성한 factoryBean 반환
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setTypeAliasesPackage("com.ludensdomain.dto");
        factoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/**/*.xml"));

        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}

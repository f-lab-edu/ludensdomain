package com.ludensdomain.config;

import com.ludensdomain.util.RoutingDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static com.ludensdomain.util.ReplicationKeyConstants.MASTER;
import static com.ludensdomain.util.ReplicationKeyConstants.SLAVE;

/*
 * @EnableTransactionManagement : 어노테이션 기반 트랜잭션 관리 기능
 * @Configuration과 같이 사용하면 기본적인 트랜잭션 또는 반응형 트랜잭션을 configure할 수 있다.
 * XML configuration으로는 <tx:annotation-driven/>와 같은 방법이다.
 * 차이점이라면 TransactionManger의 빈 이름은 transactionManager로 고정이 되나, 어노테이션 방식의 빈 이름은 사용자 임의로 정할 수 있다.
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
    /*
     * DataSource는 JDBC 사용 시 connection pool을 지원하는 인터페이스다.
     * replication을 적용했기에 각 서버에 쓰일 2개의 DataSource를 생성해야 한다.
     * @ConfigurationProperties는 properties 파일에서 prefix에 해당되는 키값을 바인딩(binding)함
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    /*
     * RoutingDataSource에서 반환된 키값(readonly인 경우 slave 키를, 아닌 경우 master 키)을 타겟 DataSource로 지정
     * @Qualifier : 같은 객체가 2개 이상 있을 때 사용할 의존 객체를 선택
     */
    @Bean
    public DataSource routingDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                        @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        RoutingDataSource routingDataSource = new RoutingDataSource();

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(MASTER, masterDataSource);
        dataSourceMap.put(SLAVE, slaveDataSource);
        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(masterDataSource);

        return routingDataSource;
    }

    /*
     * LazyConnectionDataSourceProxy : 쿼리문이 생성되는 순간 DataSource를 선택해 Proxy로 감싸서 전달
     * 원래 Spring에서는 Transaction이 시작될 때 이미 어떤 DataSource를 사용할지 결정되며, Transaction이 끝날 때까지 변경이 안 된다.
     * 또한 애플리케이션이 처음 가동될 때 Connection 객체를 무조건 호출해 가지고 있다.
     * 따라서 기본 설정에서는 Master 서버만 선택되며 데이터 엑세스가 없어도 Connection을 유지하면서 리소스를 사용한다.
     * 가동 시 제일 먼저 만들어지도록 @Primary를 추가함
     */
    @Primary
    @Bean
    public DataSource dataSource(@Qualifier("routingDataSource") DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }

    /*
     * MyBatis 연결을 위한 sqlSessionFactoryBean 객체 생성
     * DTO 클래스와 mapper xml 위치 정보를 담은 factoryBean을 getObject() 메서드로 SqlSessionTemplate에 전달
     * SqlSessionFactoryBean은 Spring의 FactoryBean 인터페이스를 추상화한 것임
     * PathMatchingResourcePatternResolver : 특정 위치에 있는 resource를 가져올 때 사용
     * @return factoryBean.getObject() 생성한 factoryBean 반환
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource(routingDataSource(masterDataSource(), slaveDataSource())));
        factoryBean.setTypeAliasesPackage("com.ludensdomain.dto");
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/**/*.xml"));

        return factoryBean.getObject();
    }

    /*
     * SqlSessionFactory에서 만들어진 sqlSession을 사용
     */
    @Bean
    public SqlSessionTemplate sqlSession() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }

    /*
     * PlatformTransactionManager는 트랜잭션 관리 객체로 트랜잭션 종료 시 commit() 또는 rollback() 메서드 호출
     * DataSourceTransactionManager : JDBC 기반의 트랜잭션 설정 때 사용
     */
    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);

        return transactionManager;
    }
}
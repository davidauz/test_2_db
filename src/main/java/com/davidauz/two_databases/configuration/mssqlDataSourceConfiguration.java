package com.davidauz.two_databases.configuration;

import com.zaxxer.hikari.HikariDataSource;
import com.davidauz.two_databases.entity.mssqlentity.mssqlTable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.davidauz.two_databases.repository.mssqlRepos",
        entityManagerFactoryRef = "MssqlTableEntityManagerFactory",
        transactionManagerRef= "MssqlTableTransactionManager"
)
@Slf4j
public class mssqlDataSourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.mssql")
    public DataSourceProperties MssqlTableDataSourceProperties() {
        log.info("001");
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.mssql.configuration")
    public DataSource MssqlTableDataSource() {
        log.info("002");
        return MssqlTableDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }


//    It’s mandatory to annotate at least one data source with the @Primary annotation.
//    Configure multiple data sources can’t anymore specify the JPA properties like dialect and ddl.auto
//    in the properties file, those should be included in the configuration classes separately
//    for each data source.
    @Primary
    @Bean(name = "MssqlTableEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean MssqlTableEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        log.info("003");
        Map<String, String> mssqlJpaProperties = new HashMap<>();
        mssqlJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        mssqlJpaProperties.put("hibernate.hbm2ddl.auto", "none");
        mssqlJpaProperties.put("jpa.hibernate.ddl-auto", "none");
        return builder
                .dataSource(MssqlTableDataSource())
                .packages(mssqlTable.class)
                .properties(mssqlJpaProperties)
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager MssqlTableTransactionManager(
            final @Qualifier("MssqlTableEntityManagerFactory") LocalContainerEntityManagerFactoryBean MssqlTableEntityManagerFactory) {
        log.info("004");
        return new JpaTransactionManager(MssqlTableEntityManagerFactory.getObject());
    }

}

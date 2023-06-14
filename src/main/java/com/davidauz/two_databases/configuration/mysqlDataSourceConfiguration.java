package com.davidauz.two_databases.configuration;

import com.davidauz.two_databases.entity.mysqlentity.mysqlTable;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@EnableJpaRepositories(basePackages = "com.davidauz.two_databases.repository.mysqlRepos",
        entityManagerFactoryRef = "mysqlTableEntityManagerFactory",
        transactionManagerRef= "mysqlTableTransactionManager")
public class mysqlDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("app.datasource.mysql")
    public DataSourceProperties mysqlTableDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("app.datasource.mysql.configuration")
    public DataSource mysqlTableDataSource() {
        return mysqlTableDataSourceProperties().initializeDataSourceBuilder()
                .type(HikariDataSource.class).build();
    }

    @Bean(name = "mysqlTableEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mysqlTableEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {

        Map<String, String> mysqlJPAProperties = new HashMap<>();
        mysqlJPAProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        mysqlJPAProperties.put("hibernate.hbm2ddl.auto", "none");
        mysqlJPAProperties.put("jpa.hibernate.ddl-auto", "none");


        return builder
                .dataSource(mysqlTableDataSource())
                .packages(mysqlTable.class)
                .properties(mysqlJPAProperties)
                .build();
    }

    @Bean
    public PlatformTransactionManager mysqlTableTransactionManager(
            final @Qualifier("mysqlTableEntityManagerFactory") LocalContainerEntityManagerFactoryBean mysqlTableEntityManagerFactory) {
        return new JpaTransactionManager(mysqlTableEntityManagerFactory.getObject());
    }
}

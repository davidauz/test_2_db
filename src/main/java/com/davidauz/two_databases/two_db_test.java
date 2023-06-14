package com.davidauz.two_databases;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages={"com.davidauz.two_databases"})
@EntityScan(basePackages={"com.davidauz.two_databases"})
public class two_db_test {

    public static void main(String[] args) {
        SpringApplication.run(two_db_test.class, args);
    }

}

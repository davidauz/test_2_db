package com.davidauz.two_databases;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages={"com.davidauz.two_databases"})
@EntityScan(basePackages={"com.davidauz.two_databases"})
public class MultipledatasourcesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipledatasourcesApplication.class, args);
    }

}

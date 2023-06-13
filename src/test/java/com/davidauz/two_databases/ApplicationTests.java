package com.davidauz.two_databases;

import com.davidauz.two_databases.entity.mssqlentity.mssqlTable;
import com.davidauz.two_databases.entity.mysqlentity.mysqlTable;
import com.davidauz.two_databases.repository.mssqlRepos.mssqlTableRepo;
import com.davidauz.two_databases.repository.mysqlRepos.mysqlTableRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan("com.davidauz.two_databases.repository")
@EntityScan(basePackages={"com.davidauz.two_databases.repository"})
@Slf4j
public class ApplicationTests {

    @Autowired
    private mysqlTableRepo mysqlTableRepo;

    @Autowired
    private mssqlTableRepo poporh1Repository;

    private mssqlTable mssqlT;
    private mysqlTable mysqlT;


    @Test
    public void testMysql() {
        Optional<mysqlTable> qchFromDb= mysqlTableRepo.findById(4635L);
        assertTrue(qchFromDb.isPresent());
    }

    @Test
    public void testMSSQL() {
        Optional<mssqlTable> poFromDb= poporh1Repository.findById(2558578L);
        assertTrue(poFromDb.isPresent());
        log.info(poFromDb.toString());
    }
}

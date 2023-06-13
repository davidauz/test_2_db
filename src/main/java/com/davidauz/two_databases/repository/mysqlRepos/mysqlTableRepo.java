package com.davidauz.two_databases.repository.mysqlRepos;

import com.davidauz.two_databases.entity.mysqlentity.mysqlTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface mysqlTableRepo extends JpaRepository<mysqlTable, Long> {
}

package com.davidauz.two_databases.repository.mssqlRepos;

import com.davidauz.two_databases.entity.mssqlentity.mssqlTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface mssqlTableRepo extends JpaRepository<mssqlTable, Long> {
}


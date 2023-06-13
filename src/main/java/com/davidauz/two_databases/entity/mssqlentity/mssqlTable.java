package com.davidauz.two_databases.entity.mssqlentity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "MSSQL_TABLE")
public class mssqlTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "VARCHAR_COLUMN")
    private String columnVarchar;
}

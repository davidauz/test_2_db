package com.davidauz.two_databases.entity.mysqlentity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "mysqlTable")
public class mysqlTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name="column_long")
    private Long longColumn;

    @Column(name="column_date")
    private Timestamp dateColumn;

    @Column(name="column_string")
    private String stringColumn;
}


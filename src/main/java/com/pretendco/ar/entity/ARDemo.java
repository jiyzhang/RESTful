package com.pretendco.ar.entity;

import lombok.Data;

import java.sql.Timestamp;
import javax.persistence.*;

@Table(name = "ardemo")
@Entity
@Data
public class ARDemo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "store_id", nullable = false)
    private long store_id;

    @Column(name = "demotime", nullable = false)
    private Timestamp demotime;

    @Column(name = "comment", length = 100)
    private String comment;
}

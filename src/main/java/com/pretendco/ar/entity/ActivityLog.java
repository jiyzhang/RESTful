package com.pretendco.ar.entity;

import lombok.Data;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "ActivityLog") //, indexes = {
//        @Index(name = "idx_serial_number", columnList = "SerialNumber"),
//        @Index(name = "idx_sessionid", columnList = "SessionID")
//})
@Entity
@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "SerialNumber", nullable = true, length = 12)
    private String serialNumber;

    @Column(name = "SessionID", nullable = true, length=36)
    private String sessionID;

    @Column(name = "SessionStartTime")
    private Timestamp sessionStartTime;

    @Column(name = "SessionTerminationTime")
    private Timestamp sessionTerminationTime;
}
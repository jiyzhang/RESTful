package com.pretendco.ar.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "ErrorLog" , indexes = {
        @Index(name = "idx_serial_number", columnList = "SerialNumber")
})
public class ErrorLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @JsonIgnore
    private long id;

    @Column(name = "SerialNumber", nullable = true, length = 12)
    private String serialNumber;

    @Column(name = "SessionID", nullable = true, length=36)
    private String sessionID;

    @Column(name = "EventTime", nullable = true)
    private Timestamp eventTime;

    @Column(name = "ErrorMessage", length = 100)
    private String errorMessage;

}
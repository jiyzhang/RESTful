package com.pretendco.ar.entity;

import lombok.Data;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
import lombok.Builder;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "ActivityLog" , indexes = {
        @Index(name = "idx_serial_number_sessionid", columnList = "SerialNumber, SessionID")
//        @Index(name = "idx_sessionid", columnList = "SessionID")
})
@Entity
@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @JsonIgnore
    private long id;

    @Column(name = "SerialNumber", nullable = true, length = 12)
//    @JsonProperty("SerialNumber")
    private String serialNumber;

    @Column(name = "SessionID", nullable = true, length=36)
//    @JsonProperty("SessionID")
    private String sessionID;


    @Column(name = "SessionStartTime")
//    @JsonProperty("SessionStartTime")
    private Timestamp sessionStartTime;

    @Column(name = "SessionTerminationTime")
//    @JsonProperty("SessionTerminationTime")
    private Timestamp sessionTerminationTime;
}
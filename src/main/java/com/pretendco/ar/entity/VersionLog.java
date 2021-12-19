package com.pretendco.ar.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.sql.Timestamp;
import javax.persistence.*;

@Table(name="VersionLog")
@Entity
@Data
public class VersionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @JsonIgnore
    private long id;

    @Column(name = "SerialNumber", nullable = true, length = 12)
//    @JsonProperty("SerialNumber")
    private String serialNumber;


    @Column(name = "LogTime")
//        @JsonProperty("LogTime")
    private Timestamp logTime;

    @Column(name = "VersionNumber", length = 20)
//    @JsonProperty("VersionNumber")
    private String versionNumber;

}

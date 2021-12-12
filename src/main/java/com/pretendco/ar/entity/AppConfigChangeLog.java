package com.pretendco.ar.entity;

import javax.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Table(name = "AppConfigChangeLog", indexes = {
        @Index(name = "idx_serial_number", columnList = "SerialNumber")
})
@Entity
@Data
public class AppConfigChangeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SerialNumber", nullable = true, length = 12)
    private String serialNumber;

    @Column(name = "SessionID", nullable = true, length=36)
    private String sessionID;

    @Column(name = "ParameterChangeTime", nullable = true)
    private Timestamp parameterChangeTime;

    @Column(name = "ParameterToBeChanged", length = 20)
    private String parameterToBeChanged;

    @Column(name = "ParameterValueBeforeChange", length = 20)
    private String parameterValueBeforeChange;

    @Column(name = "ParameterValueAfterChange", length = 20)
    private String parameterValueAfterChange;

}
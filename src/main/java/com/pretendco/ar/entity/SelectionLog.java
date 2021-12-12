package com.pretendco.ar.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Data
@Table(name = "SelectionLog" , indexes = {
        @Index(name = "idx_serial_number_sessionid", columnList = "SerialNumber, SessionID")
})
public class SelectionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @JsonIgnore
    private long id;

    @Column(name = "SerialNumber", nullable = true, length = 12)
    private String serialNumber;
    @Column(name = "SessionID", nullable = true, length=36)
    private String sessionID;

    @Column(name = "SelectedWatchSeries", length = 20)
    private String selectedWatchSeries;

    @Column(name = "SelectedWatchSize", length = 10)
    private String selectedWatchSize;

    @Column(name = "SelectedWatchCase", length = 20)
    private String selectedWatchCase;

    @Column(name = "SelectedWatchBand", length = 30)
    private String selectedWatchBand;
}
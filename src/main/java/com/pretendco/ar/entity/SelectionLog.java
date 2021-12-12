package com.pretendco.ar.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class SelectionLog {
    @EmbeddedId
    private SelectionLogId id;

    @Column(name = "SelectedWatchSeries", length = 20)
    private String selectedWatchSeries;

    @Column(name = "SelectedWatchSize", length = 10)
    private String selectedWatchSize;

    @Column(name = "SelectedWatchCase", length = 20)
    private String selectedWatchCase;

    @Column(name = "SelectedWatchBand", length = 30)
    private String selectedWatchBand;
}
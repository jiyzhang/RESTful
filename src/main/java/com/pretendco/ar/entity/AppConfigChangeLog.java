package com.pretendco.ar.entity;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.Data;

@Table(name = "AppConfigChangeLog")
@Entity
@Data
public class AppConfigChangeLog {
    @EmbeddedId
    private AppConfigChangeLogId id;

    @Column(name = "ParameterToBeChanged", length = 20)
    private String parameterToBeChanged;

    @Column(name = "ParameterValueBeforeChange", length = 20)
    private String parameterValueBeforeChange;

    @Column(name = "ParameterValueAfterChange", length = 20)
    private String parameterValueAfterChange;

}
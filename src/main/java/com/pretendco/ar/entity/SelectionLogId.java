package com.pretendco.ar.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;


@Embeddable
@Data
@EqualsAndHashCode
public class SelectionLogId implements Serializable {
    private static final long serialVersionUID = 8535886524227547747L;
    @Column(name = "SerialNumber", nullable = false, length = 12)
    private String serialNumber;
    @Column(name = "SessionID", nullable = false, length=36)
    private String sessionID;
}
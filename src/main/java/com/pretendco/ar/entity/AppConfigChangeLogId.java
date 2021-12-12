package com.pretendco.ar.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.sql.Timestamp;

@Embeddable
@Data
@EqualsAndHashCode
public class AppConfigChangeLogId implements Serializable {
    private static final long serialVersionUID = -1610900610314787859L;
    @Column(name = "SerialNumber", nullable = false, length = 12)
    private String serialNumber;
    @Column(name = "SessionID", nullable = false, length=36)
    private String sessionId;
    @Column(name = "ParameterChangeTime", nullable = false)
    private Timestamp parameterChangeTime;

//    public Timestamp getParameterChangeTime() {
//        return parameterChangeTime;
//    }
//
//    public void setParameterChangeTime(Timestamp parameterChangeTime) {
//        this.parameterChangeTime = parameterChangeTime;
//    }
//
//    public Integer getSessionNumber() {
//        return sessionNumber;
//    }
//
//    public void setSessionNumber(Integer sessionNumber) {
//        this.sessionNumber = sessionNumber;
//    }
//
//    public String getSerialNumber() {
//        return serialNumber;
//    }
//
//    public void setSerialNumber(String serialNumber) {
//        this.serialNumber = serialNumber;
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(parameterChangeTime, serialNumber, sessionNumber);
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        AppConfigChangeLogId entity = (AppConfigChangeLogId) o;
//        return Objects.equals(this.parameterChangeTime, entity.parameterChangeTime) &&
//                Objects.equals(this.serialNumber, entity.serialNumber) &&
//                Objects.equals(this.sessionNumber, entity.sessionNumber);
//    }
}
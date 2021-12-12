package com.pretendco.ar.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import java.io.Serializable;
import java.sql.Timestamp;

@Embeddable
@Data
@EqualsAndHashCode
public class ErrorLogId implements Serializable {
    private static final long serialVersionUID = -6870604430756353463L;
    @Column(name = "SerialNumber", nullable = false, length = 12)
    private String serialNumber;
    @Column(name = "SessionID", nullable = false, length=36)
    private String sessionID;
    @Column(name = "EventTime", nullable = false)
    private Timestamp eventTime;

//    public Instant getEventTime() {
//        return eventTime;
//    }
//
//    public void setEventTime(Instant eventTime) {
//        this.eventTime = eventTime;
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
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(serialNumber, sessionNumber, eventTime);
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
//        ErrorLogId entity = (ErrorLogId) o;
//        return Objects.equals(this.serialNumber, entity.serialNumber) &&
//                Objects.equals(this.sessionNumber, entity.sessionNumber) &&
//                Objects.equals(this.eventTime, entity.eventTime);
//    }
}
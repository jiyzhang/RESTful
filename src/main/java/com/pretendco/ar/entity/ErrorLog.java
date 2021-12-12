package com.pretendco.ar.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
@Data
public class ErrorLog {
    @EmbeddedId
    private ErrorLogId id;

    @Column(name = "ErrorMessage", length = 100)
    private String errorMessage;

//    public String getErrorMessage() {
//        return errorMessage;
//    }
//
//    public void setErrorMessage(String errorMessage) {
//        this.errorMessage = errorMessage;
//    }
//
//    public ErrorLogId getId() {
//        return id;
//    }
//
//    public void setId(ErrorLogId id) {
//        this.id = id;
//    }
}
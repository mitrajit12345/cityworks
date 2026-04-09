package com.cityworks.model;

import jakarta.persistence.*;

@Entity
@Table(name = "servicerequest")
public class ServiceRequest {

    @Id
    @Column(name = "requestid")
    private Long requestID;

    @Column(name = "status")
    private String status;

    public Long getRequestID() {
        return requestID;
    }

    public void setRequestID(Long requestID) {
        this.requestID = requestID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
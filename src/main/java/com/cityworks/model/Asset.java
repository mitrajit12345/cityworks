package com.cityworks.model;

import jakarta.persistence.*;

@Entity
@Table(name = "asset")
public class Asset {

    @Id
    @Column(name = "assetid")
    private Long assetID;

    @Column(name = "status")
    private String status; // Active, Inactive

    public Long getAssetID() {
        return assetID;
    }

    public void setAssetID(Long assetID) {
        this.assetID = assetID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package com.example.cineplanet.domain;

public class PeliculaDomain {
    String status;
    String src;

    public PeliculaDomain(String status, String src) {
        this.status = status;
        this.src = src;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}

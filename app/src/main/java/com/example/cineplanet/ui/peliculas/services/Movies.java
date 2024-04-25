package com.example.cineplanet.ui.peliculas.services;

public class Movies {
    int id;
    String src;
    String status;

    public Movies(int id, String src, String status) {
        this.id = id;
        this.src = src;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

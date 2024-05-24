package com.example.cineplanet.ui.cines.domain;

import java.util.List;

public class CineDomain {
    private String name;
    private String distance;
    private String address;
    private List<String> avaliable;
    private String id;

    public CineDomain(String name, String distance, String address, List<String> avaliable, String id) {
        this.name = name;
        this.distance = distance;
        this.address = address;
        this.avaliable = avaliable;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getAvaliable() {
        return avaliable;
    }

    public void setAvaliable(List<String> avaliable) {
        this.avaliable = avaliable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

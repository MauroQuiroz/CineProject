package com.example.cineplanet.domain;

public class CineDomain {
    int id;
    String name;
    String distance;
    String address;
    String [] avaliable;

    public CineDomain(int id, String name, String distance, String address, String[] avaliable) {
        this.id = id;
        this.name = name;
        this.distance = distance;
        this.address = address;
        this.avaliable = avaliable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String[] getAvaliable() {
        return avaliable;
    }

    public void setAvaliable(String[] avaliable) {
        this.avaliable = avaliable;
    }
}

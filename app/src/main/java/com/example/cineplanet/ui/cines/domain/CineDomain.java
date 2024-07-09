package com.example.cineplanet.ui.cines.domain;

import java.util.List;

public class CineDomain {
    private String name;
    private String distance;
    private String address;
    private List<String> avaliable;
    private String id;
    private double latitude;
    private double longitude;
    private float distancia;
    public CineDomain(String name, String distance, String address, List<String> avaliable, String id) {
        this.name = name;
        this.distance = distance;
        this.address = address;
        this.avaliable = avaliable;
        this.id = id;
    }

    public CineDomain(String name, String distance, String address, List<String> avaliable, String id, double latitude, double longitude) {
        this.name = name;
        this.distance = distance;
        this.address = address;
        this.avaliable = avaliable;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public float getDistancia() {
        return distancia;
    }

    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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

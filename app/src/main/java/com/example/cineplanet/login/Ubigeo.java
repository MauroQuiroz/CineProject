package com.example.cineplanet.login;

import java.util.List;

public class Ubigeo {

    private String name;
    private List<Province> provinces;
    private String id;

    public Ubigeo(String name, List<Province> provinces, String id) {
        this.name = name;
        this.provinces = provinces;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static class Province {
        private String name;
        private List<String> districts;

        public Province(String name, List<String> districts) {
            this.name = name;
            this.districts = districts;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getDistricts() {
            return districts;
        }

        public void setDistricts(List<String> districts) {
            this.districts = districts;
        }
    }
}

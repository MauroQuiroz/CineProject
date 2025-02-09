package com.example.cineplanet.domain;

public class PeliculaDetailDomain {
    String name;
    String gender;
    String duration;
    String age;
    String sinopsis;
    String director;
    String[] language;
    String[] avaliable;
    int id;

    String urlmini;
    String url;

    public PeliculaDetailDomain(String name, String gender, String duration, String age, String sinopsis, String director, String[] language, String[] avaliable, int id, String urlmini, String url) {
        this.name = name;
        this.gender = gender;
        this.duration = duration;
        this.age = age;
        this.sinopsis = sinopsis;
        this.director = director;
        this.language = language;
        this.avaliable = avaliable;
        this.id = id;
        this.urlmini = urlmini;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String[] getLanguage() {
        return language;
    }

    public void setLanguage(String[] language) {
        this.language = language;
    }

    public String[] getAvaliable() {
        return avaliable;
    }

    public void setAvaliable(String[] avaliable) {
        this.avaliable = avaliable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlmini() {
        return urlmini;
    }

    public void setUrlmini(String urlmini) {
        this.urlmini = urlmini;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

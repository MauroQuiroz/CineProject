package com.example.cineplanet.ui.peliculas.services;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

@Entity(tableName = "movie")
@TypeConverters({ListConverter.class, ListListConverter.class})
public class Movie {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String src;
    private String srcLocal = "";
    private String status;
    private String name;
    private String gender;
    private String duration;
    private String age;
    private String sinopsis;
    private String director;
    private List<String> language;
    private List<String> avaliable;
    private String urlmini;
    private String urlminiLocal = "";
    private String url;
    private String urlLocal = "";
    private List<String> idsCinemas;
    private List<List<String>> hoursCinemas;
    private byte[] datosImagen;
    private byte[] datosImagenMini;

    public Movie(int id, String src, String srcLocal, String status, String name, String gender, String duration, String age, String sinopsis, String director, List<String> language, List<String> avaliable, String urlmini, String urlminiLocal, String url, String urlLocal, List<String> idsCinemas, List<List<String>> hoursCinemas) {
        this.id = id;
        this.src = src;
        this.srcLocal = srcLocal;
        this.status = status;
        this.name = name;
        this.gender = gender;
        this.duration = duration;
        this.age = age;
        this.sinopsis = sinopsis;
        this.director = director;
        this.language = language;
        this.avaliable = avaliable;
        this.urlmini = urlmini;
        this.urlminiLocal = urlminiLocal;
        this.url = url;
        this.urlLocal = urlLocal;
        this.idsCinemas = idsCinemas;
        this.hoursCinemas = hoursCinemas;
    }
    @Ignore
    public Movie(int id, String src, String srcLocal, String status, String name, String gender, String duration, String age, String sinopsis, String director, List<String> language, List<String> avaliable, String urlmini, String url, List<String> idsCinemas, List<List<String>> hoursCinemas) {
        this.id = id;
        this.src = src;
        this.srcLocal = srcLocal;
        this.status = status;
        this.name = name;
        this.gender = gender;
        this.duration = duration;
        this.age = age;
        this.sinopsis = sinopsis;
        this.director = director;
        this.language = language;
        this.avaliable = avaliable;
        this.urlmini = urlmini;
        this.url = url;
        this.idsCinemas = idsCinemas;
        this.hoursCinemas = hoursCinemas;
    }

    public byte[] getDatosImagenMini() {
        return datosImagenMini;
    }

    public void setDatosImagenMini(byte[] datosImagenMini) {
        this.datosImagenMini = datosImagenMini;
    }

    public byte[] getDatosImagen() {
        return datosImagen;
    }

    public void setDatosImagen(byte[] datosImagen) {
        this.datosImagen = datosImagen;
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

    public String getSrcLocal() {
        return srcLocal;
    }

    public void setSrcLocal(String srcLocal) {
        this.srcLocal = srcLocal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public List<String> getLanguage() {
        return language;
    }

    public void setLanguage(List<String> language) {
        this.language = language;
    }

    public List<String> getAvaliable() {
        return avaliable;
    }

    public void setAvaliable(List<String> avaliable) {
        this.avaliable = avaliable;
    }

    public String getUrlmini() {
        return urlmini;
    }

    public void setUrlmini(String urlmini) {
        this.urlmini = urlmini;
    }

    public String getUrlminiLocal() {
        return urlminiLocal;
    }

    public void setUrlminiLocal(String urlminiLocal) {
        this.urlminiLocal = urlminiLocal;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlLocal() {
        return urlLocal;
    }

    public void setUrlLocal(String urlLocal) {
        this.urlLocal = urlLocal;
    }

    public List<String> getIdsCinemas() {
        return idsCinemas;
    }

    public void setIdsCinemas(List<String> idsCinemas) {
        this.idsCinemas = idsCinemas;
    }

    public List<List<String>> getHoursCinemas() {
        return hoursCinemas;
    }

    public void setHoursCinemas(List<List<String>> hoursCinemas) {
        this.hoursCinemas = hoursCinemas;
    }
}

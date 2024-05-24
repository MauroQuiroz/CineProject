package com.example.cineplanet.ui.cines.domain;

import java.util.List;

public class CiudadesDomain {
    private String name;
    private List<Integer> idMovies;
    private List<Integer> idCinemas;
    private int id;

    public CiudadesDomain(String name, List<Integer> idMovies, List<Integer> idCinemas, int id) {
        this.name = name;
        this.idMovies = idMovies;
        this.idCinemas = idCinemas;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getIdMovies() {
        return idMovies;
    }

    public void setIdMovies(List<Integer> idMovies) {
        this.idMovies = idMovies;
    }

    public List<Integer> getIdCinemas() {
        return idCinemas;
    }

    public void setIdCinemas(List<Integer> idCinemas) {
        this.idCinemas = idCinemas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

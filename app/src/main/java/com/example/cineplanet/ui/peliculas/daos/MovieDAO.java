package com.example.cineplanet.ui.peliculas.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cineplanet.ui.peliculas.services.Movie;

import java.util.List;
@Dao
public interface MovieDAO {
    @Query("SELECT * FROM movie")
    List<Movie> getAll();

    @Insert
    void insert(Movie movie);
    @Query("DELETE FROM movie")
    void deleteAll();
    @Query("SELECT * FROM movie WHERE id = :movieId")
    Movie getMovieById(int movieId);
    @Update
    void update(Movie movie);
    @Query("SELECT COUNT(*) FROM movie WHERE id = :id")
    int countMovieById(int id);
    @Query("SELECT * FROM movie WHERE status = 'Cartelera'")
    List<Movie> getMoviesEnCartelera();
    @Query("SELECT * FROM movie WHERE status = 'Estrenos'")
    List<Movie> getMoviesEnEstreno();
    @Query("SELECT * FROM movie WHERE status = 'Festival'")
    List<Movie> getMoviesEnFestival();
}

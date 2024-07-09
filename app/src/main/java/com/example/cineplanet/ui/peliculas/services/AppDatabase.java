package com.example.cineplanet.ui.peliculas.services;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.cineplanet.ui.peliculas.daos.MovieDAO;

@Database(entities = {Movie.class}, version = 1)
@TypeConverters({ListConverter.class, ListListConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieDAO movieDAO();
    private static AppDatabase instance;
    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "movieDB")
                            .allowMainThreadQueries() // Solo para fines de demostración; considera usar AsyncTask o LiveData para consultas en el hilo principal
                            .build();
                }
            }
        }
        return instance;
    }
}

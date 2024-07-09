package com.example.cineplanet.ui.peliculas.services;

import androidx.room.TypeConverter;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ListListConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static List<List<String>> fromString(String value) {
        if (value == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<List<String>>>() {}.getType();
        return gson.fromJson(value, listType);
    }

    @TypeConverter
    public static String fromListList(List<List<String>> list) {
        return gson.toJson(list);
    }
}

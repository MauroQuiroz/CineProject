package com.example.cineplanet.ui.cines.domain;

import android.location.Location;
import android.os.AsyncTask;

import com.example.cineplanet.ui.cines.adapter.CineAdapter;

import java.util.List;

public class CalculateDistanceTask extends AsyncTask<Void, Void, Void> {
    private List<CineDomain> cines;
    private Location currentLocation;
    private CineAdapter adapter;

    public CalculateDistanceTask(List<CineDomain> cines, Location currentLocation, CineAdapter adapter) {
        this.cines = cines;
        this.currentLocation = currentLocation;
        this.adapter = adapter;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        for (CineDomain cinema : cines) {
            Location cinemaLocation = new Location("");
            cinemaLocation.setLatitude(cinema.getLatitude());
            cinemaLocation.setLongitude(cinema.getLongitude());

            float distance = currentLocation.distanceTo(cinemaLocation);
            cinema.setDistancia(distance);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        adapter.notifyDataSetChanged();
    }
}
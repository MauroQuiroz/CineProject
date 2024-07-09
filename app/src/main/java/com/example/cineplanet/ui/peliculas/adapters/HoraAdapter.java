package com.example.cineplanet.ui.peliculas.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cineplanet.R;
import com.example.cineplanet.ui.butaca.Butaca;
import com.example.cineplanet.ui.peliculas.services.CinePelicula;
import com.example.cineplanet.ui.peliculas.services.Movie;
import com.google.gson.Gson;

import java.util.List;

public class HoraAdapter extends RecyclerView.Adapter<HoraAdapter.Viewholder> {

    List<String> items;
    Context context;
    Movie movie;
    CinePelicula cinePelicula;

    public HoraAdapter(List<String> items, Movie movie,CinePelicula cinePelicula) {
        this.items = items;
        this.movie = movie;
        this.cinePelicula = cinePelicula;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View infalter = LayoutInflater.from(parent.getContext()).inflate(R.layout.hour_item,parent,false);
        context  = parent.getContext();
        return  new HoraAdapter.Viewholder(infalter );
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.txt.setText(items.get(position));
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Butaca.class );
                String sData = new Gson().toJson(movie);
                intent.putExtra("idM",String.valueOf(movie.getId()));

                String sData2 = new Gson().toJson(cinePelicula);
                intent.putExtra("cine",sData2);

                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    public class  Viewholder extends RecyclerView.ViewHolder{
        TextView txt;
        LinearLayout container;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.hour);
            container =  itemView.findViewById(R.id.hour_container);
        }
    }
}

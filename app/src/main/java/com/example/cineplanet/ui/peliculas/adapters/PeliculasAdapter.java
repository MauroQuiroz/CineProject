package com.example.cineplanet.ui.peliculas.adapters;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cineplanet.R;
import com.example.cineplanet.domain.PeliculaDomain;

import java.util.ArrayList;
import java.util.List;

import com.example.cineplanet.ui.peliculas.services.Movies;
import com.squareup.picasso.Picasso;

public class PeliculasAdapter extends RecyclerView.Adapter<PeliculasAdapter.Viewholder> {

    List<Movies> items;
    Context context;

    int interador = 0;

    public PeliculasAdapter(List<Movies> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View infalter = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_movie,parent,false);
        context  = parent.getContext();
        return  new Viewholder(infalter );
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.txt .setText(items.get(position).getStatus());
        Picasso.get().load(items.get(position).getSrc()).into(holder.image);
        /*int posActual = position+interador;


        holder.txt .setText(items.get(posActual).getStatus());
        Picasso.get().load(items.get(posActual).getSrc()).into(holder.image);

        holder.txt2.setText(items.get(posActual+1).getStatus());
        Picasso.get().load(items.get(posActual+1).getSrc()).into(holder.image2);

        interador++;*/

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends  RecyclerView.ViewHolder{

        TextView txt;
        ImageView image;
        TextView txt2;
        ImageView image2;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txt_show_status);
            image = itemView.findViewById(R.id.image_show);
            txt2 = itemView.findViewById(R.id.txt_show_status2);
            image2 = itemView.findViewById(R.id.image_show2);
        }
    }

}

package com.example.cineplanet.ui.peliculas.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cineplanet.R;
import com.example.cineplanet.domain.PeliculaDomain;

import java.util.ArrayList;
import java.util.List;

import com.example.cineplanet.ui.peliculas.PeliculaShow;
import com.example.cineplanet.ui.peliculas.services.Movies;
import com.squareup.picasso.Picasso;

public class PeliculasAdapter extends RecyclerView.Adapter<PeliculasAdapter.Viewholder> {

    List<Movies> items;
    Context context;



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
        holder.txt.setText(items.get(position).getStatus());
        Picasso.get().load(items.get(position).getSrc()).into(holder.image);


        //Event CLick
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PeliculaShow.class);
                intent.putExtra("idPelicula",items.get(position).getId());
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends  RecyclerView.ViewHolder{

        TextView txt;
        ImageView image;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txt_show_status);
            image = itemView.findViewById(R.id.image_show);

        }
    }

}

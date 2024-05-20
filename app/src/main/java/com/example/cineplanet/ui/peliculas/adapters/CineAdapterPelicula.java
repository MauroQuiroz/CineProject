package com.example.cineplanet.ui.peliculas.adapters;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cineplanet.R;
import com.example.cineplanet.ui.peliculas.services.CinePelicula;
import com.example.cineplanet.ui.peliculas.services.Movie;

import java.util.List;

public class CineAdapterPelicula extends RecyclerView.Adapter<CineAdapterPelicula.Viewholder> {

    List<CinePelicula> items;
    String[][] horas;
    Context context;
    Movie movie;

    RecyclerView.Adapter adapter;
    RecyclerView recyclerView;

    public CineAdapterPelicula(List<CinePelicula> items, Movie movie) {
        this.items = items;
        this.horas = movie.getHoursCinemas();
        this.movie = movie;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View infalter = LayoutInflater.from(parent.getContext()).inflate(R.layout.cine_item,parent,false);
        context  = parent.getContext();
        return  new CineAdapterPelicula.Viewholder(infalter );
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        if(position%2==0){
            holder.container.setBackgroundColor(Color.parseColor("#F2F3F6"));

        }else{
            holder.container.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        holder.title.setText(items.get(position).getName());
        holder.descripcion.setText(items.get(position).getAddress());
        String av = "";
        for (String str : items.get(position).getAvaliable()){
            av+=str+",";
        }
        holder.avaliable.setText(av);
        holder.desplegable.setVisibility(View.GONE);
        holder.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toggleImageVisibility(holder.desplegable,holder.close);

            }
        });


        recyclerView = holder.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        adapter  = new HoraAdapter(horas[position],movie,items.get(position));
        recyclerView.setAdapter(adapter);

    }

    private void toggleImageVisibility(LinearLayout linearLayout,ImageView image) {
        if (linearLayout.getVisibility() == View.VISIBLE) {
            linearLayout.setVisibility(View.GONE);
            image.setImageResource(R.drawable.ic_plus);

        } else {
            linearLayout.setVisibility(View.VISIBLE);
            image.setImageResource(R.drawable.ic_minius);
        }
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        TextView title;
        TextView descripcion;
        TextView avaliable;
        ImageView close;
        LinearLayout desplegable;
        RecyclerView recyclerView;
        ConstraintLayout container;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt1);
            descripcion = itemView.findViewById(R.id.descripcion);
            avaliable = itemView.findViewById(R.id.available);
            close = itemView.findViewById(R.id.icon2);
            desplegable  = itemView.findViewById(R.id.desplegable);
            recyclerView = itemView.findViewById(R.id.Rv_hours);
            container = itemView.findViewById(R.id.container_item_cine);
        }
    }
}

package com.example.cineplanet.search.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cineplanet.R;
import com.example.cineplanet.ui.peliculas.PeliculaShow;
import com.example.cineplanet.ui.peliculas.adapters.CineAdapterPelicula;
import com.example.cineplanet.ui.peliculas.services.Movie;
import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BuscadorMainAdapter extends RecyclerView.Adapter<BuscadorMainAdapter.Viewholder> {
    public List<Movie> getItems() {
        return items;
    }

    public void setItems(List<Movie> items) {
        this.items = items;
    }

    public List<Movie> getResultados() {
        return resultados;
    }

    public void setResultados(List<Movie> resultados) {
        this.resultados = resultados;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    List<Movie> items;
    List<Movie> resultados;
    Context context;

    public BuscadorMainAdapter(List<Movie> items) {
        this.items = items;
        resultados = new ArrayList<>();
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View infalter = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pelicula_search,parent,false);
        context  = parent.getContext();
        return  new BuscadorMainAdapter.Viewholder(infalter );
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {


        Picasso.get().load(resultados.get(position).getUrlmini()).into(holder.image);

        holder.title.setText(resultados.get(position).getName());
        holder.genero.setText(resultados.get(position).getGender());

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PeliculaShow.class);
                intent.putExtra("id" ,String.valueOf(resultados.get(position).getId()) );
                intent.putExtra("cxt", "si");
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return resultados.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        LinearLayout container;
        ImageView image;
        TextView title;
        TextView genero;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            image= itemView.findViewById(R.id.img_search);
            title = itemView.findViewById(R.id.title_busqueda);
            genero = itemView.findViewById(R.id.genero_busqueda);
            container = itemView.findViewById(R.id.resultado_container);
        }
    }


}

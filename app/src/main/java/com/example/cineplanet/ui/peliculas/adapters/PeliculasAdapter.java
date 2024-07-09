package com.example.cineplanet.ui.peliculas.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cineplanet.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.example.cineplanet.ui.cines.domain.CineDomain;
import com.example.cineplanet.ui.peliculas.PeliculaShow;
import com.example.cineplanet.ui.peliculas.services.Movie;
import com.google.gson.Gson;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class PeliculasAdapter extends RecyclerView.Adapter<PeliculasAdapter.Viewholder> {

    List<Movie> items;
    Context context;
    String conexion;
    List<Movie> resultados;

    public PeliculasAdapter(List<Movie> items,String conexion) {

        this.conexion = conexion;
        this.items = new ArrayList<>(items);
        this.resultados = new ArrayList<>(items);
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View infalter = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_movie,parent,false);
        context  = parent.getContext();
        return  new Viewholder(infalter );
    }
    public List<Movie> getResultados() {
        return resultados;
    }
    public List<Movie> getItems() {
        return items;
    }
    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        if (conexion.equals("no")) {
            holder.txt.setText(resultados.get(position).getStatus());

            byte[] datosImagen = resultados.get(position).getDatosImagen();
            if (datosImagen != null && datosImagen.length > 0) {
                Log.i("jhuleeeei", "Datos de imagen recibidos: " + datosImagen.length + " bytes");
                Bitmap bitmap = BitmapFactory.decodeByteArray(datosImagen, 0, datosImagen.length);
                if (bitmap != null) {
                    holder.image.setImageBitmap(bitmap);
                } else {

                     holder.image.setImageResource(R.drawable.no_internet);
                }
            } else {

                 holder.image.setImageResource(R.drawable.no_internet);
            }

            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = holder.itemView.getContext();
                    Intent intent = new Intent(context, PeliculaShow.class);

                    intent.putExtra("id", String.valueOf(items.get(position).getId()) );
                    intent.putExtra("cxt", "no");

                    context.startActivity(intent);
                }
            });
        } else {


            holder.txt.setText(resultados.get(position).getStatus());
            Picasso.get().load(resultados.get(position).getSrc()).into(holder.image);
            holder.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = holder.itemView.getContext();
                    Intent intent = new Intent(context, PeliculaShow.class);
                    String dataA = new Gson().toJson(resultados.get(position));
                    intent.putExtra("data", dataA);
                    intent.putExtra("cxt", "si");
                    intent.putExtra("id", String.valueOf(resultados.get(position).getId()) );
                    Log.i("jhuleeeei", "Se hizo clic en la imagen en la posición: " + position);
                    context.startActivity(intent);
                }
            });
        }


    }

    public static Bitmap base64ToBitmap(String base64String) {
        byte[] decodedByteArray = Base64.decode(base64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.length);
    }
    @Override
    public int getItemCount() {
        return resultados.size();
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

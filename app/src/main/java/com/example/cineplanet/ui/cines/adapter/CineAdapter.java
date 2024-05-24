package com.example.cineplanet.ui.cines.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cineplanet.R;
import com.example.cineplanet.ui.cines.domain.CineDomain;
import com.example.cineplanet.ui.peliculas.adapters.CineAdapterPelicula;

import java.util.ArrayList;
import java.util.List;

public class CineAdapter extends RecyclerView.Adapter<CineAdapter.Viewholder> {
    List<CineDomain> items;
    List<CineDomain> resultados;
    Context context;

    public List<CineDomain> getItems() {
        return items;
    }

    public void setItems(List<CineDomain> items) {
        this.items = items;
    }

    public List<CineDomain> getResultados() {
        return resultados;
    }

    public void setResultados(List<CineDomain> resultados) {
        this.resultados = resultados;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public CineAdapter(List<CineDomain> items) {

        this.items = new ArrayList<>(items);
        this.resultados = new ArrayList<>(items);
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View infalter = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cine_main,parent,false);
        context  = parent.getContext();
        return  new CineAdapter.Viewholder(infalter );
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.name.setText(resultados.get(position).getName());
        holder.address.setText(resultados.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return resultados.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        TextView name;
        TextView address;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_cine);
            address  =itemView.findViewById(R.id.txt_direction_cine);
        }
    }
}

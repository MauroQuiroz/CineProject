package com.example.cineplanet.ui.cines.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cineplanet.R;
import com.example.cineplanet.ui.cines.IClickFiltroCiudad;
import com.example.cineplanet.ui.cines.domain.CiudadesDomain;

import java.util.List;

public class FiltroCiudadCineAdpater extends RecyclerView.Adapter<FiltroCiudadCineAdpater.Viewholder> {
    List<CiudadesDomain> items;
    Context context;
    IClickFiltroCiudad iClickFiltroCiudad;

    public FiltroCiudadCineAdpater(List<CiudadesDomain> items,IClickFiltroCiudad iClickFiltroCiudad) {
        this.items = items;
        this.iClickFiltroCiudad = iClickFiltroCiudad;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View infalter = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filtro_ciudad,parent,false);
        context  = parent.getContext();
        return  new FiltroCiudadCineAdpater.Viewholder(infalter );

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.name.setText(items.get(position).getName());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickFiltroCiudad.clickFiltroCiudad(items.get(position).getIdCinemas());
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView name;
        LinearLayout container;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_name_ciudad_filtro);
            container = itemView.findViewById(R.id.filtro_ciudad_container);
        }
    }
}

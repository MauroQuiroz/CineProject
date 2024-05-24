package com.example.cineplanet.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cineplanet.R;
import com.example.cineplanet.domain.CineDomain;

import java.util.List;

import retrofit2.Callback;

public class CineFavoritoAdapter extends RecyclerView.Adapter<CineFavoritoAdapter.Viewholder> {
    List<CineDomain> cineDomains;
    Context context;
    IRecyFromActivity listener;

    public CineFavoritoAdapter(List<CineDomain> cineDomains, IRecyFromActivity listener) {

        this.cineDomains = cineDomains;
        this.listener = listener;
    }



    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View infalter = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cine_favorito,parent,false);
        context  = parent.getContext();
        return  new CineFavoritoAdapter.Viewholder(infalter );
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.textView.setText(cineDomains.get(position).getName());
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(cineDomains.get(position).getName(),cineDomains.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cineDomains.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView textView;
        LinearLayout container;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txt_fav);
            container = itemView.findViewById(R.id.container_item_cine_favorito);
        }
    }
}

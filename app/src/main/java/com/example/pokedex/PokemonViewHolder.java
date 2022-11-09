package com.example.pokedex;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PokemonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    PokeActivity pokeActivity;
    ImageView imageView;
    TextView nameView;
    TextView typeView;

    public PokemonViewHolder(@NonNull View itemView, PokeActivity pokeActivity) {
        super(itemView);
        this.pokeActivity = pokeActivity;
        this.imageView = itemView.findViewById(R.id.imgView);
        this.nameView = itemView.findViewById(R.id.tvName);
        this.typeView = itemView.findViewById(R.id.tvType);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        PokeActivity.pokemonDetail = PokeActivity.pokedex.get(super.getAdapterPosition());
        Intent i = new Intent(this.pokeActivity, FormActivity.class);
        //pasar informacion a un activity
        i.putExtra("activity", "pokedex");
        this.pokeActivity.startActivity(i);
    }
}

package com.example.pokedex;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewPokemonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    NewPokeActivity newPokeActivity;
    ImageView imageView;
    TextView nameView;

    public NewPokemonViewHolder(@NonNull View itemView, NewPokeActivity newPokeActivity) {
        super(itemView);
        this.newPokeActivity = newPokeActivity;
        this.imageView = itemView.findViewById(R.id.imgView);
        this.nameView = itemView.findViewById(R.id.tvName);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        PokeActivity.pokemonDetail = PokeActivity.toFind.get(super.getAdapterPosition());
        Intent i = new Intent(this.newPokeActivity, FormActivity.class);
        i.putExtra("activity", "find");
        this.newPokeActivity.startActivity(i);
    }
}

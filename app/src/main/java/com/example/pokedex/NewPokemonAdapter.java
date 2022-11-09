package com.example.pokedex;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

public class NewPokemonAdapter extends RecyclerView.Adapter<NewPokemonViewHolder>{
    private ArrayList<Pokemon> pokemonModelList;
    private NewPokeActivity newPokeActivity;

    public NewPokemonAdapter(ArrayList<Pokemon> pokemonModelList, NewPokeActivity newPokeActivity) {
        this.pokemonModelList = pokemonModelList;
        this.newPokeActivity = newPokeActivity;
    }

    //este metodo se ejecuta por la cantidad de
    //elementos que entren en la pantalla
    @NonNull
    @Override
    public NewPokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //item_persona = card
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_new_pokemon, parent, false);

        return new NewPokemonViewHolder(v, this.newPokeActivity);
    }

    //se ejecuta a partir de cuanto haga scroll
    //tanto para arriba y para abajo
    @Override
    public void onBindViewHolder(@NonNull NewPokemonViewHolder holder, int position) {
        Pokemon p = PokeActivity.toFind.get(position);
        holder.nameView.setText(p.getName());

        Glide.with(this.newPokeActivity)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + p.getNumber() + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
    }
    //ver el tamaño de la lista
    @Override
    public int getItemCount() {
        return PokeActivity.toFind.size();
    }
}

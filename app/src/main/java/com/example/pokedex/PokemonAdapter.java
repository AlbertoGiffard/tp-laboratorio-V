package com.example.pokedex;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder>{
    private ArrayList<Pokemon> pokemonModelList;
    private PokeActivity pokeActivity;

    public PokemonAdapter(ArrayList<Pokemon> pokemonModelList, PokeActivity pokeActivity) {
        this.pokemonModelList = pokemonModelList;
        this.pokeActivity = pokeActivity;
    }

    //este metodo se ejecuta por la cantidad de
    //elementos que entren en la pantalla
    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //item_persona = card
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);

        return new PokemonViewHolder(v, this.pokeActivity);
    }

    //se ejecuta a partir de cuanto haga scroll
    //tanto para arriba y para abajo
    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Pokemon p = PokeActivity.pokedex.get(position);
        holder.nameView.setText(p.getName());
        holder.typeView.setText(p.getType());

        Glide.with(this.pokeActivity)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + p.getNumber() + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
    }
    //ver el tamaño de la lista
    @Override
    public int getItemCount() {
        return PokeActivity.pokedex.size();
    }

    public void addPokemonList(ArrayList<Pokemon> pokemonList) {
        //this.pokemonModelList.addAll(pokemonList);
        this.divideList(pokemonList);
        notifyDataSetChanged();
    }

    public void divideList(ArrayList<Pokemon> pokemonList) {
        int counter = 0;
        String[] types = {"Fire", "Water", "Grass", "Normal", "Psychic", "Flying"};
        int random = 0;

        for (Pokemon p: pokemonList) {
            random = new Random().nextInt(types.length);
            p.setType(types[random]);

            if (counter < 6) {
                PokeActivity.pokedex.add(p);
            } else {
                PokeActivity.toFind.add(p);
            }
            counter++;
        }
    }
}

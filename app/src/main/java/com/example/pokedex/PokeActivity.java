package com.example.pokedex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokeActivity extends AppCompatActivity {
    private static final String TAG = "Pokedex";
    private ArrayList<Pokemon> list;
    private PokemonAdapter adapter;
    private Retrofit retrofit;

    public static ArrayList<Pokemon> pokedex;
    public static ArrayList<Pokemon> toFind;
    public static Pokemon pokemonDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke);

        if(PokeActivity.pokedex != null) {
            //ejecucion del recycle view
            this.adapter = new PokemonAdapter(PokeActivity.pokedex, this);
            RecyclerView rv = super.findViewById(R.id.rvPokemon);
            GridLayoutManager linear = new GridLayoutManager(this, 2);
            rv.setLayoutManager(linear);
            rv.setAdapter(this.adapter);

            PokeController pokeController = new PokeController(this);
            PokeView pokeView = new PokeView(this, pokeController);
        } else {
            PokeActivity.pokedex = new ArrayList<Pokemon>();
            PokeActivity.toFind = new ArrayList<Pokemon>();
            PokeActivity.pokemonDetail = new Pokemon();

            this.list = new ArrayList<Pokemon>();
            //ejecucion del recycle view
            this.adapter = new PokemonAdapter(list, this);
            RecyclerView rv = super.findViewById(R.id.rvPokemon);
            GridLayoutManager linear = new GridLayoutManager(this, 2);
            rv.setLayoutManager(linear);
            rv.setAdapter(this.adapter);
            //conexion con API
            this.retrofit = new Retrofit.Builder()
                    .baseUrl("https://pokeapi.co/api/v2/")
                    .addConverterFactory(GsonConverterFactory.create(
                            new GsonBuilder().serializeNulls().create()
                    ))
                    .build();

            PokeController pokeController = new PokeController(this);
            PokeView pokeView = new PokeView(this, pokeController);

            this.getData();
        }
    }

    public void getData(){
        PokemonAPIService pokemonApiService = this.retrofit.create(PokemonAPIService.class);
        Call<ResponsePokemon> call = pokemonApiService.getPokemons();

        call.enqueue(new Callback<ResponsePokemon>() {
            @Override
            public void onResponse(Call<ResponsePokemon> call, Response<ResponsePokemon> response) {
                if (response.isSuccessful()){
                    ResponsePokemon responsePokemon = response.body();
                    ArrayList<Pokemon> pokemonList =  responsePokemon.getResults();
                    adapter.addPokemonList(pokemonList);
                } else {
                    Log.d("Error", "Something happened");
                    return;
                }
            }

            @Override
            public void onFailure(Call<ResponsePokemon> call, Throwable t) {
                Log.d("Error", t.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.poke_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.itemHelp) {
            Card card = new Card();
            card.show(getSupportFragmentManager(), "card");
        }
        return super.onOptionsItemSelected(item);
    }
}
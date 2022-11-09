package com.example.pokedex;

import com.example.pokedex.PokemonFetchResults;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Query;

public interface PokemonAPIService {
    @GET("pokemon/?limit=20")
    Call<ResponsePokemon> getPokemons();
}

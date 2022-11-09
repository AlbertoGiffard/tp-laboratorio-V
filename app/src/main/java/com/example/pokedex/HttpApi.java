package com.example.pokedex;

import android.util.Log;

import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpApi {
    private static final String TAG = "Pokedex";
    private List<Pokemon> pokemonList;
    private PokemonAdapter adapter;
    private Retrofit retrofit;

    public void getDataApi2(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create(
                        new GsonBuilder().serializeNulls().create()
                ))
                .build();

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

    public String getDataApi(String endpoint){
        String result = null;
        try {
            URL url = new URL(endpoint);
            //agregar el cast
            //agregar el segundo exception
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //le digo de que tipo sera
            connection.setRequestMethod("GET");
            Log.d("respuesta", "entre3");
            connection.connect();
            Log.d("respuesta", "entre4");
            //obtener el codigo de respuesta (200,400,500)
            int responseCode = connection.getResponseCode();

            if (responseCode == 200){
                //no es la respuesta como tal, es un enlace a la respuesta que aun está en el servidor
                InputStream is = connection.getInputStream();
                //la lectura del response
                result = this.readStream(is);

            } else {
                //no es necesario que sea una exception
                throw new IOException();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  result;
    }

    public String readStream(InputStream is){
        String response = null;

        try {
            //sacamos con un output
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int readCount = 0;
            //cuando devuelva -1 es que no hay mas datos por leer
            //debe estar esto dentro del while
            while ((readCount = is.read(buffer)) != -1){
                baos.write(buffer, 0, readCount);
            }
            //para cerrar la conexion
            is.close();
            response = new String(baos.toByteArray(), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    public byte[] getImgApi(String endpoint){
        byte[] result = null;
        try {
            URL url = new URL(endpoint);
            //agregar el cast
            //agregar el segundo exception
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //le digo de que tipo sera
            connection.setRequestMethod("GET");
            connection.connect();
            //obtener el codigo de respuesta (200,400,500)
            int responseCode = connection.getResponseCode();

            if (responseCode == 200){
                //no es la respuesta como tal, es un enlace a la respuesta que aun está en el servidor
                InputStream is = connection.getInputStream();
                //sacamos con un output
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int countRead = 0;

                //cuando devuelva -1 es que no hay mas datos por leer
                //debe estar esto dentro del while
                while ((countRead = is.read(buffer)) != -1){
                    baos.write(buffer, 0, countRead);
                }
                //para cerrar la conexion
                is.close();

                return baos.toByteArray();
            } else {
                //no es necesario que sea una exception
                throw new IOException();
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  result;
    }
}

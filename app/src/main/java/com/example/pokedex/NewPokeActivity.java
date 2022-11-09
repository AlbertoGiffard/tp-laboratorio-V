package com.example.pokedex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class NewPokeActivity extends AppCompatActivity {
    private NewPokemonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_poke);
        ///////////////ACTION BAR
        ActionBar actionBar = super.getSupportActionBar();
        actionBar.setTitle(R.string.app_new_poke_name);
        //que aparezca la flecha de back
        actionBar.setDisplayHomeAsUpEnabled(true);

        //ejecucion del recycle view
        this.adapter = new NewPokemonAdapter(PokeActivity.toFind, this);
        RecyclerView rv = super.findViewById(R.id.rvNewPokemon);
        GridLayoutManager linear = new GridLayoutManager(this, 3);
        rv.setLayoutManager(linear);
        rv.setAdapter(this.adapter);

        NewPokeController newPokeController = new NewPokeController(this);
        NewPokeView pokeView = new NewPokeView(this, newPokeController);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            Log.d("click", "en home");
            //para finalizar esta activity y regrese a la anterior
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
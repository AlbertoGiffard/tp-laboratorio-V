package com.example.pokedex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class FormActivity extends AppCompatActivity {
    private String comeFrom;
    private FormController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        ///////////////ACTION BAR
        ActionBar actionBar = super.getSupportActionBar();
        actionBar.setTitle("Pokemon: " + PokeActivity.pokemonDetail.getPresentation());
        //que aparezca la flecha de back
        actionBar.setDisplayHomeAsUpEnabled(true);
        ///////////////
        this.controller = new FormController(this);
        FormView view = new FormView(this, this.controller);
        view.setfieldsPokemon();

        //para obtener los datos del actitivyt anterior
        Bundle bundle = super.getIntent().getExtras();
        comeFrom = (String)bundle.getSerializable("activity");
        //dependiendo de este comeFrom agrega a la lista o remueve
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

    public String getComeFrom(){
        return this.comeFrom;
    }
}
package com.example.pokedex;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class NewPokeController implements View.OnClickListener{
    private NewPokeActivity newPokeActivity;

    public NewPokeController(NewPokeActivity newPokeActivity) {
        this.newPokeActivity = newPokeActivity;
    }

    @Override
    public void onClick(View view) {
    }
}

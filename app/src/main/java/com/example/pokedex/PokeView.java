package com.example.pokedex;

import android.widget.Button;

public class PokeView {
    private Button btnNew;
    private PokeActivity pokeActivity;

    public PokeView(PokeActivity pokeActivity, PokeController pokeController) {
        this.pokeActivity = pokeActivity;
        this.btnNew = pokeActivity.findViewById(R.id.btnNew);

        this.btnNew.setOnClickListener(pokeController);
    }

}

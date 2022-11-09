package com.example.pokedex;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class FormController implements View.OnClickListener{
    private FormActivity formActivity;
    private EditText editText;

    public FormController(FormActivity formActivity) {
        this.formActivity = formActivity;
        this.editText = formActivity.findViewById(R.id.etFormDescription);
    }

    @Override
    public void onClick(View view) {
        //viaja al siguiente activity
        Intent i;

        Log.d("come", this.formActivity.getComeFrom());

        if (this.formActivity.getComeFrom().equals("pokedex")) {
            PokeActivity.pokemonDetail.setDescription(this.editText.getText().toString());
            this.updateAPokemonDescription(PokeActivity.pokedex, PokeActivity.pokemonDetail, this.editText.getText().toString());
        } else {
            PokeActivity.pokemonDetail.setDescription(this.editText.getText().toString());
            PokeActivity.pokedex.add(PokeActivity.pokemonDetail);
            this.removePokemonFromToFind();
        }

        i = new Intent(this.formActivity, PokeActivity.class);
        this.formActivity.startActivity(i);
    }

    public void removePokemonFromToFind() {
        for (int i = 0; i < PokeActivity.toFind.size(); i++){
            Pokemon p = PokeActivity.toFind.get(i);
            Log.d("pokemon", p.getPresentation());

            if (p.getName().equals(PokeActivity.pokemonDetail.getName())) {
                PokeActivity.toFind.remove(i);
                break;
            }
        }
    }

    public void updateAPokemonDescription(ArrayList<Pokemon> list, Pokemon pokemon, String description) {
        for (int i = 0; i > list.size(); i++){
            Pokemon p = list.get(i);

            if (p.getName() == pokemon.getName()) {
                p.setDescription(description);
                break;
            }
        }
    }
}

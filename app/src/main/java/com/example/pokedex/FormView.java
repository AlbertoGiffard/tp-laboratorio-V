package com.example.pokedex;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class FormView {
    private FormActivity formActivity;
    private AppCompatButton btnSave;

    public FormView(FormActivity formActivity, FormController controller) {
        this.formActivity = formActivity;
        this.btnSave = formActivity.findViewById(R.id.btnSaveForm);
        this.btnSave.setOnClickListener(controller);
    }

    public void setfieldsPokemon(){
        ImageView imgView = this.formActivity.findViewById(R.id.imgForm);
        TextView name = this.formActivity.findViewById(R.id.tvFormName);
        TextView type = this.formActivity.findViewById(R.id.tvFormType);
        EditText description = this.formActivity.findViewById(R.id.etFormDescription);

        name.setText(PokeActivity.pokemonDetail.getName());
        type.setText(PokeActivity.pokemonDetail.getType());
        description.setText(PokeActivity.pokemonDetail.getDescription());

        Glide.with(this.formActivity)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + PokeActivity.pokemonDetail.getNumber() + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgView);
    }
}

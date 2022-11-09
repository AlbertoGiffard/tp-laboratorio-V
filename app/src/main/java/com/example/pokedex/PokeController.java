package com.example.pokedex;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class PokeController implements View.OnClickListener{
    private PokeActivity pokeActivity;
    private Button btnNew;

    public PokeController(PokeActivity pokeActivity) {
        this.pokeActivity = pokeActivity;
    }

    @Override
    public void onClick(View view) {
        //viaja al siguiente activity
        Intent i = new Intent(this.pokeActivity, NewPokeActivity.class);
        this.pokeActivity.startActivity(i);
    }
}

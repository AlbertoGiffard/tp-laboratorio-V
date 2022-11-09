package com.example.pokedex;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

public class IntroductionController implements View.OnClickListener {
    private VideoView video;
    private MainActivity mainActivity;
    private Button btnIn;

    public IntroductionController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        //obtener ID del xml
        this.video = (VideoView) this.mainActivity.findViewById(R.id.introductionVideo);
        //obtener el path del video
        int id = this.mainActivity.getResources().getIdentifier("intro", "raw", this.mainActivity.getPackageName());
        final String path = "android.resource://" + this.mainActivity.getPackageName() + "/" + id;
        //setear el video
        this.video.setVideoURI(Uri.parse(path));
        //reproducir
        this.video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });
        this.video.start();
    }

    @Override
    public void onClick(View view) {
        //viaja al siguiente activity
        Intent i = new Intent(this.mainActivity, PokeActivity.class);
        this.mainActivity.startActivity(i);
    }
}

package com.example.pokedex;

import android.widget.Button;

public class IntroductionView {
    private Button btnIn;

    public IntroductionView(MainActivity mainActivity, IntroductionController introController) {
        this.btnIn = mainActivity.findViewById(R.id.btnInApp);

        this.btnIn.setOnClickListener(introController);
    }
}

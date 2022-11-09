package com.example.pokedex;

import android.content.DialogInterface;
import android.util.Log;
import android.view.View;

public class CardController implements DialogInterface.OnClickListener  {
    View view;
    //ctrl O
    public CardController() {
    }
    public CardController(View view) {
        this.view = view;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == DialogInterface.BUTTON_NEGATIVE){
            Log.d("Click", "En el negativo");
        } else if(i == DialogInterface.BUTTON_NEUTRAL){
            Log.d("Click", "En el neutral");
        } else {
            Log.d("Click", "En el positivo");
        }
    }
}

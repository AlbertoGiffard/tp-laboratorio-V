package com.example.pokedex;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Card extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Titulo de la ventana");
        builder.setTitle(R.string.help_title);
        //incrusta xml en el dialog, en este casa ventana.xml
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog,null);
        builder.setView(view);
        //botones
        //con el view
        CardController clickListener = new CardController(view);
        builder.setPositiveButton("Ok", clickListener);

        return builder.create();
    }
}

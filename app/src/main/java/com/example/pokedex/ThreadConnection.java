package com.example.pokedex;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class ThreadConnection implements Runnable {
    public static final int IMG = 10;
    public static final int TEXT = 5;
    Handler handler;
    boolean img;


    public ThreadConnection(Handler handler, boolean img){
        this.handler = handler;
        this.img = img;
    }

    @Override
    public void run(){
        //no se puede conectar a internet en el hilo principal
        HttpApi http = new HttpApi();

        if (img) {
            Log.d("Respuesta", "estoy en img");
            byte[] response = http.getImgApi("https://static.wikia.nocookie.net/mario/images/e/e3/MPS_Mario.png/revision/latest?cb=20220814154953");

            Message message = new Message();
            message.obj = response;
            message.arg1 = IMG;
            handler.sendMessage(message);
        } else {
            //test: https://restcountries.com/v3.1/all
            String response = http.getDataApi("https://pokeapi.co/api/v2/pokemon?limit=10&offset=0");

            Log.d("Respuesta", response);
            Message message = new Message();
            message.obj = response;
            message.arg1 = TEXT;
            handler.sendMessage(message);
        }
    }
}

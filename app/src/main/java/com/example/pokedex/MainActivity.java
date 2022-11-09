package com.example.pokedex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements Handler.Callback  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ////////API
        //no se puede conectar a internet en el hilo principal
        //para que lea la cola de los mensajes y hay que implementar el handler
        //se dbe implementar antes que el hilo
        Handler handler = new Handler(this);
        Thread t1 = new Thread(new ThreadConnection(handler, false));
        //t1.start();
        ////////

        IntroductionController introController = new IntroductionController(this);
        IntroductionView introView = new IntroductionView(this, introController);

    }

    @Override
    public boolean handleMessage(@NonNull Message message) {
        Log.d("JSON-Pokemon", "entre");
        if (message.arg1 == ThreadConnection.TEXT) {
            try {
                JSONObject jsonArray = new JSONObject(message.obj.toString());
                Log.d("JSON-Pokemon", jsonArray.toString());
                Log.d("JSON-Pokemon", "jsonArray.toString()");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else if(message.arg1 ==ThreadConnection.IMG){
            byte[] imgByte = (byte[]) message.obj;

            //img.setImageBitmap(BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length));

        }
        return false;
    }
}
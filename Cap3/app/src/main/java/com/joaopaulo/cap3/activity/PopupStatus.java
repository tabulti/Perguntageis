package com.joaopaulo.cap3.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import com.firebase.client.ValueEventListener;
import com.joaopaulo.cap3.R;

import java.util.HashMap;

/**
 * Created by Gustavo on 03/11/2015.
 */



public class PopupStatus extends Activity {


    private ImageView aux;

    final private Firebase fbstatus = new Firebase("https://resplendent-heat-382.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.popup_status);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8),(int) (height*.47));

        apagarCorrects();

        fbstatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                HashMap<String,String> jogo= (HashMap)dataSnapshot.child("jogos").child("jogoexample").getValue();
                int leana = Integer.parseInt(jogo.get("leanA"));

                if(leana == 0){

                }else if(leana == 1){
                    aux = (ImageView) findViewById(R.id.leana1);
                    aux.setVisibility(View.VISIBLE);
                }else if (leana == 2){
                    aux = (ImageView) findViewById(R.id.leana1);
                    aux.setVisibility(View.VISIBLE);

                    aux = (ImageView) findViewById(R.id.leana2);
                    aux.setVisibility(View.VISIBLE);
                }else{
                    aux = (ImageView) findViewById(R.id.leana1);
                    aux.setVisibility(View.VISIBLE);

                    aux = (ImageView) findViewById(R.id.leana2);
                    aux.setVisibility(View.VISIBLE);

                    aux = (ImageView) findViewById(R.id.leana3);
                    aux.setVisibility(View.VISIBLE);

                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }








    public void apagarCorrects(){

        aux = (ImageView) findViewById(R.id.agilea1);
        aux.setVisibility(View.INVISIBLE);

        aux = (ImageView) findViewById(R.id.agilea2);
        aux.setVisibility(View.INVISIBLE);

        aux = (ImageView) findViewById(R.id.agilea3);
        aux.setVisibility(View.INVISIBLE);

        aux = (ImageView) findViewById(R.id.agileb1);
        aux.setVisibility(View.INVISIBLE);

        aux = (ImageView) findViewById(R.id.agileb2);
        aux.setVisibility(View.INVISIBLE);

        aux = (ImageView) findViewById(R.id.agileb3);
        aux.setVisibility(View.INVISIBLE);

        aux = (ImageView) findViewById(R.id.scruma1);
        aux.setVisibility(View.INVISIBLE);

        aux = (ImageView) findViewById(R.id.scruma2);
        aux.setVisibility(View.INVISIBLE);

        aux = (ImageView) findViewById(R.id.scruma3);
        aux.setVisibility(View.INVISIBLE);

        aux = (ImageView) findViewById(R.id.scrumb1);
        aux.setVisibility(View.INVISIBLE);

        aux = (ImageView) findViewById(R.id.scrumb2);
        aux.setVisibility(View.INVISIBLE);

        aux = (ImageView) findViewById(R.id.scrumb3);
        aux.setVisibility(View.INVISIBLE);

        aux = (ImageView) findViewById(R.id.xpa1);
        aux.setVisibility(View.INVISIBLE);

        aux = (ImageView) findViewById(R.id.xpa2);
        aux.setVisibility(View.INVISIBLE);

        aux = (ImageView) findViewById(R.id.xpa3);
        aux.setVisibility(View.INVISIBLE);

        aux = (ImageView) findViewById(R.id.xpb1);
        aux.setVisibility(View.INVISIBLE);

        aux = (ImageView) findViewById(R.id.xpb2);
        aux.setVisibility(View.INVISIBLE);

        aux = (ImageView) findViewById(R.id.xpb3);
        aux.setVisibility(View.INVISIBLE);

        aux = (ImageView) findViewById(R.id.leana1);
        aux.setVisibility(View.INVISIBLE);

        aux = (ImageView) findViewById(R.id.leana2);
        aux.setVisibility(View.INVISIBLE);

        aux = (ImageView) findViewById(R.id.leana3);
        aux.setVisibility(View.INVISIBLE);

        aux = (ImageView) findViewById(R.id.leanb1);
        aux.setVisibility(View.INVISIBLE);

        aux = (ImageView) findViewById(R.id.leanb2);
        aux.setVisibility(View.INVISIBLE);

        aux = (ImageView) findViewById(R.id.leanb3);
        aux.setVisibility(View.INVISIBLE);



    }


}

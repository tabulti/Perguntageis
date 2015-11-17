package com.joaopaulo.cap3.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
    private ImageView avatarA,avatarB;
    private String login;
    private TextView tvAvatarA,tvAvatarB;


    final private Firebase fbstatus = new Firebase("https://perguntageis.firebaseio.com/");
    //private

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.popup_status);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .75));

        apagarCorrects();
        avatarA = (ImageView) findViewById(R.id.avatarA);
        avatarB = (ImageView) findViewById(R.id.avatarB);
        Bundle args = getIntent().getExtras();
        login = args.getString("login");
        tvAvatarA = (TextView) findViewById(R.id.tvAvatarA);
        tvAvatarB = (TextView) findViewById(R.id.tvAvatarB);
        fbstatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                apagarCorrects();
                HashMap<String,String> jogo= (HashMap)dataSnapshot.child("jogos").child("jogoexample").getValue();
                HashMap<String,String> usuario1= (HashMap)dataSnapshot.child("usuarios").child("u1").getValue();
                HashMap<String,String> usuario2= (HashMap)dataSnapshot.child("usuarios").child("u2").getValue();

                if(login.equals(jogo.get("loginJogadorA"))){
                    if(login.equals(usuario1.get("email"))){
                        switch (Integer.parseInt(usuario1.get("imgcode"))){
                            case 1:
                                avatarA.setImageResource(R.drawable.escudo);
                                tvAvatarA.setText("Você");
                                break;
                            case 2:
                                avatarA.setImageResource(R.drawable.ironman);
                                tvAvatarA.setText("Você");
                                break;
                            case 3:
                                avatarA.setImageResource(R.drawable.dangerous);
                                tvAvatarA.setText("Você");
                                break;
                            case 4:
                                avatarA.setImageResource(R.drawable.marvel);
                                tvAvatarA.setText("Você");
                                break;
                            case 5:
                                avatarA.setImageResource(R.drawable.martelo);
                                tvAvatarA.setText("Você");
                                break;
                            case 6:
                                avatarA.setImageResource(R.drawable.lanterna);
                                tvAvatarA.setText("Você");
                                break;
                            default:
                                break;
                        }
                        switch (Integer.parseInt(usuario2.get("imgcode"))){
                            case 1:
                                avatarB.setImageResource(R.drawable.escudo);
                                tvAvatarB.setText("Oponente");
                                break;
                            case 2:
                                avatarB.setImageResource(R.drawable.ironman);
                                tvAvatarB.setText("Oponente");
                                break;
                            case 3:
                                avatarB.setImageResource(R.drawable.dangerous);
                                tvAvatarB.setText("Oponente");
                                break;
                            case 4:
                                avatarB.setImageResource(R.drawable.marvel);
                                tvAvatarB.setText("Oponente");
                                break;
                            case 5:
                                avatarB.setImageResource(R.drawable.martelo);
                                tvAvatarB.setText("Oponente");
                                break;
                            case 6:
                                avatarB.setImageResource(R.drawable.lanterna);
                                tvAvatarB.setText("Oponente");
                                break;
                            default:
                                break;
                        }
                    }else if(login.equals(usuario2.get("email"))){
                        switch (Integer.parseInt(usuario2.get("imgcode"))){
                            case 1:
                                avatarA.setImageResource(R.drawable.escudo);
                                tvAvatarA.setText("Você");
                                break;
                            case 2:
                                avatarA.setImageResource(R.drawable.ironman);
                                tvAvatarA.setText("Você");
                                break;
                            case 3:
                                avatarA.setImageResource(R.drawable.dangerous);
                                tvAvatarA.setText("Você");
                                break;
                            case 4:
                                avatarA.setImageResource(R.drawable.marvel);
                                tvAvatarA.setText("Você");
                                break;
                            case 5:
                                avatarA.setImageResource(R.drawable.martelo);
                                tvAvatarA.setText("Você");
                                break;
                            case 6:
                                avatarA.setImageResource(R.drawable.lanterna);
                                tvAvatarA.setText("Você");
                                break;
                            default:
                                break;
                        }
                        switch (Integer.parseInt(usuario1.get("imgcode"))){
                            case 1:
                                avatarB.setImageResource(R.drawable.escudo);
                                tvAvatarB.setText("Oponente");
                                break;
                            case 2:
                                avatarB.setImageResource(R.drawable.ironman);
                                tvAvatarB.setText("Oponente");
                                break;
                            case 3:
                                avatarB.setImageResource(R.drawable.dangerous);
                                tvAvatarB.setText("Oponente");
                                break;
                            case 4:
                                avatarB.setImageResource(R.drawable.marvel);
                                tvAvatarB.setText("Oponente");
                                break;
                            case 5:
                                avatarB.setImageResource(R.drawable.martelo);
                                tvAvatarB.setText("Oponente");
                                break;
                            case 6:
                                avatarB.setImageResource(R.drawable.lanterna);
                                tvAvatarB.setText("Oponente");
                                break;
                            default:
                                break;
                        }
                    }
                }else if(login.equals(jogo.get("loginJogadorB"))){
                    if(login.equals(usuario1.get("email"))){
                        switch (Integer.parseInt(usuario1.get("imgcode"))){
                            case 1:
                                avatarB.setImageResource(R.drawable.escudo);
                                tvAvatarB.setText("Você");
                                break;
                            case 2:
                                avatarB.setImageResource(R.drawable.ironman);
                                tvAvatarB.setText("Você");
                                break;
                            case 3:
                                avatarB.setImageResource(R.drawable.dangerous);
                                tvAvatarB.setText("Você");
                                break;
                            case 4:
                                avatarB.setImageResource(R.drawable.marvel);
                                tvAvatarB.setText("Você");
                                break;
                            case 5:
                                avatarB.setImageResource(R.drawable.martelo);
                                tvAvatarB.setText("Você");
                                break;
                            case 6:
                                avatarB.setImageResource(R.drawable.lanterna);
                                tvAvatarB.setText("Você");
                                break;
                            default:
                                break;
                        }
                        switch (Integer.parseInt(usuario2.get("imgcode"))){
                            case 1:
                                avatarA.setImageResource(R.drawable.escudo);
                                tvAvatarA.setText("Oponente");
                                break;
                            case 2:
                                avatarA.setImageResource(R.drawable.ironman);
                                tvAvatarA.setText("Oponente");
                                break;
                            case 3:
                                avatarA.setImageResource(R.drawable.dangerous);
                                tvAvatarA.setText("Oponente");
                                break;
                            case 4:
                                avatarA.setImageResource(R.drawable.marvel);
                                tvAvatarA.setText("Oponente");
                                break;
                            case 5:
                                avatarA.setImageResource(R.drawable.martelo);
                                tvAvatarA.setText("Oponente");
                                break;
                            case 6:
                                avatarA.setImageResource(R.drawable.lanterna);
                                tvAvatarA.setText("Oponente");
                                break;
                            default:
                                break;
                        }
                    }else if(login.equals(usuario2.get("email"))){
                        switch (Integer.parseInt(usuario2.get("imgcode"))){
                            case 1:
                                avatarB.setImageResource(R.drawable.escudo);
                                tvAvatarB.setText("Você");
                                break;
                            case 2:
                                avatarB.setImageResource(R.drawable.ironman);
                                tvAvatarB.setText("Você");
                                break;
                            case 3:
                                avatarB.setImageResource(R.drawable.dangerous);
                                tvAvatarB.setText("Você");
                                break;
                            case 4:
                                avatarB.setImageResource(R.drawable.marvel);
                                tvAvatarB.setText("Você");
                                break;
                            case 5:
                                avatarB.setImageResource(R.drawable.martelo);
                                tvAvatarB.setText("Você");
                                break;
                            case 6:
                                avatarB.setImageResource(R.drawable.lanterna);
                                tvAvatarB.setText("Você");
                                break;
                            default:
                                break;
                        }
                        switch (Integer.parseInt(usuario1.get("imgcode"))){
                            case 1:
                                avatarA.setImageResource(R.drawable.escudo);
                                tvAvatarA.setText("Oponente");
                                break;
                            case 2:
                                avatarA.setImageResource(R.drawable.ironman);
                                tvAvatarA.setText("Oponente");
                                break;
                            case 3:
                                avatarA.setImageResource(R.drawable.dangerous);
                                tvAvatarA.setText("Oponente");
                                break;
                            case 4:
                                avatarA.setImageResource(R.drawable.marvel);
                                tvAvatarA.setText("Oponente");
                                break;
                            case 5:
                                avatarA.setImageResource(R.drawable.martelo);
                                tvAvatarA.setText("Oponente");
                                break;
                            case 6:
                                avatarA.setImageResource(R.drawable.lanterna);
                                tvAvatarA.setText("Oponente");
                                break;
                            default:
                                break;
                        }
                    }
                }

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


                int leanb = Integer.parseInt(jogo.get("leanB"));

                if(leanb == 0){

                }else if(leanb == 1){
                    aux = (ImageView) findViewById(R.id.leanb1);
                    aux.setVisibility(View.VISIBLE);
                }else if (leanb == 2){
                    aux = (ImageView) findViewById(R.id.leanb1);
                    aux.setVisibility(View.VISIBLE);

                    aux = (ImageView) findViewById(R.id.leanb2);
                    aux.setVisibility(View.VISIBLE);
                }else{
                    aux = (ImageView) findViewById(R.id.leanb1);
                    aux.setVisibility(View.VISIBLE);

                    aux = (ImageView) findViewById(R.id.leanb2);
                    aux.setVisibility(View.VISIBLE);

                    aux = (ImageView) findViewById(R.id.leanb3);
                    aux.setVisibility(View.VISIBLE);

                }

                int agilea = Integer.parseInt(jogo.get("agileA"));

                if(agilea == 0){

                }else if(agilea == 1){
                    aux = (ImageView) findViewById(R.id.agilea1);
                    aux.setVisibility(View.VISIBLE);
                }else if (agilea == 2){
                    aux = (ImageView) findViewById(R.id.agilea1);
                    aux.setVisibility(View.VISIBLE);

                    aux = (ImageView) findViewById(R.id.agilea2);
                    aux.setVisibility(View.VISIBLE);
                }else{
                    aux = (ImageView) findViewById(R.id.agilea1);
                    aux.setVisibility(View.VISIBLE);

                    aux = (ImageView) findViewById(R.id.agilea2);
                    aux.setVisibility(View.VISIBLE);

                    aux = (ImageView) findViewById(R.id.agilea3);
                    aux.setVisibility(View.VISIBLE);

                }

                int agileb = Integer.parseInt(jogo.get("agileB"));

                if(agileb == 0){

                }else if(agileb == 1){
                    aux = (ImageView) findViewById(R.id.agileb1);
                    aux.setVisibility(View.VISIBLE);
                }else if (agileb == 2){
                    aux = (ImageView) findViewById(R.id.agileb1);
                    aux.setVisibility(View.VISIBLE);

                    aux = (ImageView) findViewById(R.id.agileb2);
                    aux.setVisibility(View.VISIBLE);
                }else{
                    aux = (ImageView) findViewById(R.id.agileb1);
                    aux.setVisibility(View.VISIBLE);

                    aux = (ImageView) findViewById(R.id.agileb2);
                    aux.setVisibility(View.VISIBLE);

                    aux = (ImageView) findViewById(R.id.agileb3);
                    aux.setVisibility(View.VISIBLE);

                }

                int scruma = Integer.parseInt(jogo.get("scrumA"));

                if(scruma == 0){

                }else if(scruma == 1){
                    aux = (ImageView) findViewById(R.id.scruma1);
                    aux.setVisibility(View.VISIBLE);
                }else if (scruma == 2){
                    aux = (ImageView) findViewById(R.id.scruma1);
                    aux.setVisibility(View.VISIBLE);

                    aux = (ImageView) findViewById(R.id.scruma2);
                    aux.setVisibility(View.VISIBLE);
                }else{
                    aux = (ImageView) findViewById(R.id.scruma1);
                    aux.setVisibility(View.VISIBLE);

                    aux = (ImageView) findViewById(R.id.scruma2);
                    aux.setVisibility(View.VISIBLE);

                    aux = (ImageView) findViewById(R.id.scruma3);
                    aux.setVisibility(View.VISIBLE);

                }

                int scrumb = Integer.parseInt(jogo.get("scrumB"));

                if(scrumb == 0){

                }else if(scrumb == 1){
                    aux = (ImageView) findViewById(R.id.scrumb1);
                    aux.setVisibility(View.VISIBLE);
                }else if (scrumb == 2){
                    aux = (ImageView) findViewById(R.id.scrumb1);
                    aux.setVisibility(View.VISIBLE);

                    aux = (ImageView) findViewById(R.id.scrumb2);
                    aux.setVisibility(View.VISIBLE);
                }else{
                    aux = (ImageView) findViewById(R.id.scrumb1);
                    aux.setVisibility(View.VISIBLE);

                    aux = (ImageView) findViewById(R.id.scrumb2);
                    aux.setVisibility(View.VISIBLE);

                    aux = (ImageView) findViewById(R.id.scrumb3);
                    aux.setVisibility(View.VISIBLE);

                }

                int xpa = Integer.parseInt(jogo.get("xpA"));

                if(xpa == 0){

                }else if(xpa == 1){
                    aux = (ImageView) findViewById(R.id.xpa1);
                    aux.setVisibility(View.VISIBLE);
                }else if (xpa == 2){
                    aux = (ImageView) findViewById(R.id.xpa1);
                    aux.setVisibility(View.VISIBLE);

                    aux = (ImageView) findViewById(R.id.xpa2);
                    aux.setVisibility(View.VISIBLE);
                }else{
                    aux = (ImageView) findViewById(R.id.xpa1);
                    aux.setVisibility(View.VISIBLE);

                    aux = (ImageView) findViewById(R.id.xpa2);
                    aux.setVisibility(View.VISIBLE);

                    aux = (ImageView) findViewById(R.id.xpa3);
                    aux.setVisibility(View.VISIBLE);

                }

                int xpb = Integer.parseInt(jogo.get("xpB"));

                if(xpb == 0){

                }else if(xpb == 1){
                    aux = (ImageView) findViewById(R.id.xpb1);
                    aux.setVisibility(View.VISIBLE);
                }else if (xpb == 2){
                    aux = (ImageView) findViewById(R.id.xpb1);
                    aux.setVisibility(View.VISIBLE);

                    aux = (ImageView) findViewById(R.id.xpb2);
                    aux.setVisibility(View.VISIBLE);
                }else{
                    aux = (ImageView) findViewById(R.id.xpb1);
                    aux.setVisibility(View.VISIBLE);

                    aux = (ImageView) findViewById(R.id.xpb2);
                    aux.setVisibility(View.VISIBLE);

                    aux = (ImageView) findViewById(R.id.xpb3);
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

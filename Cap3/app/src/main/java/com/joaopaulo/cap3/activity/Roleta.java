package com.joaopaulo.cap3.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.joaopaulo.cap3.R;

import java.util.Random;

public class Roleta extends AppCompatActivity {


    private Animation animRotate;
    private Animation animFinal;
    private ImageView roleta_background;
    private ImageView arrow;
    private MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roleta);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mp = MediaPlayer.create(getApplicationContext(), R.raw.tic2);
        roleta_background = (ImageView) findViewById(R.id.roleta);
        arrow = (ImageView) findViewById(R.id.arrow);
        arrow.bringToFront();


        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onPause(){
        super.onPause();
        roleta_background.setImageResource(R.drawable.roleta_final);
    }

    public void girarRoleta(View view){

        Random rand = new Random();
        int aux = rand.nextInt(4);

        roleta_background.setImageResource(R.drawable.roleta_final);
        animRotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        roleta_background.startAnimation(animRotate);


        if(aux == 0) {
            animFinal = AnimationUtils.loadAnimation(this, R.anim.rotate_final1);
            animRotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    mp.start();

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    roleta_background.setImageResource(R.drawable.roleta_final_amarelo);
                    roleta_background.startAnimation(animFinal);
                    finalAnimacao(animFinal,0);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });

            Toast.makeText(Roleta.this,"0 - Amarelo ",Toast.LENGTH_SHORT).show();


        }else if(aux == 1){
            animFinal = AnimationUtils.loadAnimation(this, R.anim.rotate_final1);
            animRotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    mp.start();


                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    roleta_background.setImageResource(R.drawable.roleta_final_azul);
                    roleta_background.startAnimation(animFinal);
                    finalAnimacao(animFinal,1);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            Toast.makeText(Roleta.this,"1 - Azul ",Toast.LENGTH_SHORT).show();


        }else if(aux == 2){
            animFinal = AnimationUtils.loadAnimation(this, R.anim.rotate_final1);
            animRotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    mp.start();


                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    roleta_background.setImageResource(R.drawable.roleta_final_vermelho);
                    roleta_background.startAnimation(animFinal);
                    finalAnimacao(animFinal,2);


                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            Toast.makeText(Roleta.this,"2 - Vermelho ",Toast.LENGTH_SHORT).show();


        }else{
            animFinal = AnimationUtils.loadAnimation(this, R.anim.rotate_final1);
            animRotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    mp.start();


                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    roleta_background.setImageResource(R.drawable.roleta_final_verde);
                    roleta_background.startAnimation(animFinal);
                    finalAnimacao(animFinal,3);

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            Toast.makeText(Roleta.this,"3 - Verde ",Toast.LENGTH_SHORT).show();

        }

    }

    public void popupStatus(View v){

        startActivity(new Intent(Roleta.this,PopupStatus.class));
    }

    public void finalAnimacao(Animation anim, int codigoTema){

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(Roleta.this, PerguntaActivity.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

}

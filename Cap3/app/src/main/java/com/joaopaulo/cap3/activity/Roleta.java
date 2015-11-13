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

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.joaopaulo.cap3.R;

import java.util.HashMap;
import java.util.Random;

public class Roleta extends AppCompatActivity {


    private Animation animRotate;
    private Animation animFinal;
    private ImageView roleta_background;
    private ImageView arrow,suavez;
    private MediaPlayer mp;
    private Button girar,status;
    private final Firebase ref = new Firebase("https://perguntageis.firebaseio.com/");
    private HashMap<String,String> vezNoTurno;
    private HashMap<String,String> verificarFim;
    private int scrumA,agileA,xpA,leanA,scrumB,agileB,xpB,leanB;
    private String login;
    private HashMap<String,String> usuario1,usuario2,usuario3,usuario4;
    private HashMap<String,Object> updateStatus = new HashMap<>();
    private Firebase jogoRef = ref.child("jogos").child("jogoexample");



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
        suavez = (ImageView) findViewById(R.id.SuaVez);
        suavez.setImageResource(R.drawable.aguarde);
        girar = (Button) findViewById(R.id.girar);
        girar.setClickable(false);
        Bundle args = getIntent().getExtras();
        login = args.getString("login");
        status = (Button) findViewById(R.id.status);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                vezNoTurno = (HashMap) dataSnapshot.child("jogos").child("jogoexample").getValue();
                verificarFim = (HashMap) dataSnapshot.child("jogos").child("jogoexample").getValue();
                usuario1 = (HashMap) dataSnapshot.child("usuarios").child("u1").getValue();
                usuario2 = (HashMap) dataSnapshot.child("usuarios").child("u2").getValue();

                if (vezNoTurno.get("vezNoTurno").equals("A")) {
                    if (vezNoTurno.get("loginJogadorA").equals(login)) {
                        suavez.setImageResource(R.drawable.sopq);
                        girar.setClickable(true);
                    } else {
                        suavez.setImageResource(R.drawable.aguarde);
                        girar.setClickable(false);
                    }

                } else if (vezNoTurno.get("vezNoTurno").equals("B")) {
                    if (vezNoTurno.get("loginJogadorB").equals(login)) {
                        suavez.setImageResource(R.drawable.sopq);
                        girar.setClickable(true);
                    } else {
                        suavez.setImageResource(R.drawable.aguarde);
                        girar.setClickable(false);
                    }
                } else {
                    suavez.setImageResource(R.drawable.aguarde);
                    girar.setClickable(false);
                }
                verificarFimDeJogo();


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });


        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onPause(){
        super.onPause();

    }

    @Override
    protected void onResume(){
        super.onResume();
        roleta_background.setImageResource(R.drawable.roleta_final);
        status.setClickable(true);



    }

    public void girarRoleta(View view){

        status.setClickable(false);
        girar.setClickable(false);

        final int aux = gerarCodigoTema();
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
                    finalAnimacao(animFinal, aux);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });




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
                    finalAnimacao(animFinal, 1);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });




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
                    finalAnimacao(animFinal, 2);


                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });




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
                    finalAnimacao(animFinal, 3);

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });



        }

    }

    public void popupStatus(View v){

        startActivity(new Intent(Roleta.this, PopupStatus.class));
    }

    public void finalAnimacao(Animation anim, final int codigoTema){

        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(Roleta.this, PerguntaActivity.class);
                intent.putExtra("codigotema", codigoTema);
                intent.putExtra("login", login);
                startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    public void verificarFimDeJogo(){
        //-----------------------------------VERIFICAR FIM DO JOGO ------------------------

        HashMap<String,Object> updateDadosUsuario1 = new HashMap<>();
        HashMap<String,Object> updateDadosUsuario2 = new HashMap<>();
        Firebase usuarioRef = ref.child("usuarios");
        int aux = 0;

        scrumB = Integer.parseInt(verificarFim.get("scrumB"));
        leanB = Integer.parseInt(verificarFim.get("leanB"));
        agileB = Integer.parseInt(verificarFim.get("agileB"));
        xpB = Integer.parseInt(verificarFim.get("xpB"));

        if(scrumB>=3 && leanB>=3 && agileB>=3 && xpB>=3){

            updateStatus.put("agileA",""+0);
            updateStatus.put("agileB",""+0);
            updateStatus.put("idJogo",""+1);
            updateStatus.put("leanA",""+0);
            updateStatus.put("leanB",""+0);
            updateStatus.put("loginJogadorA","teste@teste.com");
            updateStatus.put("loginJogadorB","teste2@gmail.com");
            updateStatus.put("scrumA",""+0);
            updateStatus.put("scrumB",""+0);
            updateStatus.put("vezNoTurno","A");
            updateStatus.put("xpA",""+0);
            updateStatus.put("xpB",""+0);

            jogoRef.updateChildren(updateStatus);

            if(login.equals(usuario1.get("email"))){

                aux = Integer.parseInt(usuario1.get("perguntasLean"));
                updateDadosUsuario1.put("u1/perguntasLean",""+(aux+leanA));
                aux = Integer.parseInt(usuario1.get("perguntasAgile"));
                updateDadosUsuario1.put("u1/perguntasAgile",""+(aux+agileA));
                aux = Integer.parseInt(usuario1.get("perguntasXP"));
                updateDadosUsuario1.put("u1/perguntasXP",""+(aux+xpA));
                aux = Integer.parseInt(usuario1.get("perguntasSCRUM"));
                updateDadosUsuario1.put("u1/perguntasSCRUM",""+(aux+scrumA));
                aux = Integer.parseInt(usuario1.get("vitorias"));
                updateDadosUsuario1.put("u1/vitorias", "" + (aux + 1));
                usuarioRef.updateChildren(updateDadosUsuario1);


            }else if(login.equals(usuario2.get("email"))){


                aux = Integer.parseInt(usuario2.get("perguntasLean"));
                updateDadosUsuario1.put("u2/perguntasLean",""+(aux+leanB));
                aux = Integer.parseInt(usuario2.get("perguntasAgile"));
                updateDadosUsuario1.put("u2/perguntasAgile",""+(aux+agileB));
                aux = Integer.parseInt(usuario2.get("perguntasXP"));
                updateDadosUsuario1.put("u2/perguntasXP",""+(aux+xpB));
                aux = Integer.parseInt(usuario2.get("perguntasSCRUM"));
                updateDadosUsuario1.put("u2/perguntasSCRUM",""+(aux+scrumB));
                aux = Integer.parseInt(usuario2.get("vitorias"));
                updateDadosUsuario1.put("u2/vitorias",""+(aux+1));
                usuarioRef.updateChildren(updateDadosUsuario2);

            }else{

            }


            Intent fimDeJogo = new Intent(Roleta.this,FimDeJogoActivity.class);
            fimDeJogo.putExtra("login",login);
            fimDeJogo.putExtra("vencedor",vezNoTurno.get("loginJogadorB"));
            startActivity(fimDeJogo);

        }

        scrumA = Integer.parseInt(verificarFim.get("scrumA"));
        leanA = Integer.parseInt(verificarFim.get("leanA"));
        agileA = Integer.parseInt(verificarFim.get("agileA"));
        xpA = Integer.parseInt(verificarFim.get("xpA"));

        if(scrumA>=3 && leanA>=3 && agileA>=3 && xpA>=3){

            updateStatus.put("agileA",""+0);
            updateStatus.put("agileB",""+0);
            updateStatus.put("idJogo",""+1);
            updateStatus.put("leanA",""+0);
            updateStatus.put("leanB",""+0);
            updateStatus.put("loginJogadorA","teste@teste.com");
            updateStatus.put("loginJogadorB","teste2@gmail.com");
            updateStatus.put("scrumA",""+0);
            updateStatus.put("scrumB",""+0);
            updateStatus.put("vezNoTurno","A");
            updateStatus.put("xpA",""+0);
            updateStatus.put("xpB",""+0);

            jogoRef.updateChildren(updateStatus);

            if(login.equals(usuario1.get("email"))){

                aux = Integer.parseInt(usuario1.get("perguntasLean"));
                updateDadosUsuario1.put("u1/perguntasLean",""+(aux+leanA));
                aux = Integer.parseInt(usuario1.get("perguntasAgile"));
                updateDadosUsuario1.put("u1/perguntasAgile",""+(aux+agileA));
                aux = Integer.parseInt(usuario1.get("perguntasXP"));
                updateDadosUsuario1.put("u1/perguntasXP",""+(aux+xpA));
                aux = Integer.parseInt(usuario1.get("perguntasSCRUM"));
                updateDadosUsuario1.put("u1/perguntasSCRUM",""+(aux+scrumA));
                aux = Integer.parseInt(usuario1.get("vitorias"));
                updateDadosUsuario1.put("u1/vitorias",""+(aux+1));
                usuarioRef.updateChildren(updateDadosUsuario1);

            }else if(login.equals(usuario2.get("email"))){

                aux = Integer.parseInt(usuario2.get("perguntasLean"));
                updateDadosUsuario1.put("u2/perguntasLean",""+(aux+leanB));
                aux = Integer.parseInt(usuario2.get("perguntasAgile"));
                updateDadosUsuario1.put("u2/perguntasAgile",""+(aux+agileB));
                aux = Integer.parseInt(usuario2.get("perguntasXP"));
                updateDadosUsuario1.put("u2/perguntasXP",""+(aux+xpB));
                aux = Integer.parseInt(usuario2.get("perguntasSCRUM"));
                updateDadosUsuario1.put("u2/perguntasSCRUM",""+(aux+scrumB));
                aux = Integer.parseInt(usuario2.get("vitorias"));
                updateDadosUsuario1.put("u2/vitorias",""+(aux+1));
                usuarioRef.updateChildren(updateDadosUsuario2);

            }else{

            }

            Intent fimDeJogo = new Intent(Roleta.this,FimDeJogoActivity.class);
            fimDeJogo.putExtra("login",login);
            fimDeJogo.putExtra("vencedor",vezNoTurno.get("loginJogadorA"));
            startActivity(fimDeJogo);

        }

        //------------------FIM VERIFICAR FIM DE JOGO ------------------------------------
    }

    public int gerarCodigoTema(){
        //0 - SCRUM, 1 - XP, 2 - AGILE, 3 - LEAN
        final int codigoScrum = 0, codigoXp = 1, codigoAgile = 2, codigoLean = 3;
        Random rand = new Random();
        int codigoGerado;

        if(login.equals(verificarFim.get("loginJogadorA"))){
            if(scrumA >= 3){
                if(leanA >= 3){
                    if(xpA >= 3){
                        codigoGerado = codigoAgile;
                    }else if(agileA >= 3){
                        codigoGerado = codigoXp;
                    }else{
                        if(rand.nextInt(2) == 0){
                            codigoGerado = codigoAgile;
                        }else{
                            codigoGerado = codigoXp;
                        }
                    }
                }else if(xpA >=3){
                    if(leanA >= 3){
                        codigoGerado = codigoAgile;
                    }else if(agileA >= 3){
                        codigoGerado = codigoLean;
                    }else{
                        if(rand.nextInt(2) == 0){
                            codigoGerado = codigoAgile;
                        }else{
                            codigoGerado = codigoLean;
                        }
                    }

                }else if(agileA >=3){
                    if(leanA >= 3) {
                        codigoGerado = codigoXp;
                    }else if (xpA >= 3){
                        codigoGerado = codigoLean;
                    }else{
                        if(rand.nextInt(2) == 0){
                            codigoGerado = codigoXp;
                        }else{
                            codigoGerado = codigoLean;
                        }
                    }

                }else{
                    int i = rand.nextInt(3);
                    if(i == 0){
                        codigoGerado = codigoLean;
                    }else if (i == 1){
                        codigoGerado = codigoXp;
                    }else{
                        codigoGerado = codigoAgile;
                    }
                }


            }else if(leanA >= 3){
                if(scrumA >= 3){
                    if(xpA >= 3){
                        codigoGerado = codigoAgile;
                    }else if(agileA >= 3){
                        codigoGerado = codigoXp;
                    }else{
                        if(rand.nextInt(2) == 0){
                            codigoGerado = codigoAgile;
                        }else{
                            codigoGerado = codigoXp;
                        }
                    }
                }else if(xpA >=3){
                    if(scrumA >= 3){
                        codigoGerado = codigoAgile;
                    }else if(agileA >= 3){
                        codigoGerado = codigoScrum;
                    }else{
                        if(rand.nextInt(2) == 0){
                            codigoGerado = codigoAgile;
                        }else{
                            codigoGerado = codigoScrum;
                        }
                    }

                }else if(agileA >=3){
                    if(scrumA >= 3) {
                        codigoGerado = codigoXp;
                    }else if (xpA >= 3){
                        codigoGerado = codigoScrum;
                    }else{
                        if(rand.nextInt(2) == 0){
                            codigoGerado = codigoXp;
                        }else{
                            codigoGerado = codigoScrum;
                        }
                    }

                }else{
                    int i = rand.nextInt(3);
                    if(i == 0){
                        codigoGerado = codigoScrum;
                    }else if (i == 1){
                        codigoGerado = codigoXp;
                    }else{
                        codigoGerado = codigoAgile;
                    }
                }
            }else if(xpA >= 3){
                if(scrumA >= 3){
                    if(leanA >= 3){
                        codigoGerado = codigoAgile;
                    }else if(agileA >= 3){
                        codigoGerado = codigoLean;
                    }else{
                        if(rand.nextInt(2) == 0){
                            codigoGerado = codigoAgile;
                        }else{
                            codigoGerado = codigoLean;
                        }
                    }
                }else if(leanA >=3){
                    if(scrumA >= 3){
                        codigoGerado = codigoAgile;
                    }else if(agileA >= 3){
                        codigoGerado = codigoScrum;
                    }else{
                        if(rand.nextInt(2) == 0){
                            codigoGerado = codigoAgile;
                        }else{
                            codigoGerado = codigoScrum;
                        }
                    }

                }else if(agileA >=3){
                    if(scrumA >= 3) {
                        codigoGerado = codigoLean;
                    }else if (leanA >= 3){
                        codigoGerado = codigoScrum;
                    }else{
                        if(rand.nextInt(2) == 0){
                            codigoGerado = codigoLean;
                        }else{
                            codigoGerado = codigoScrum;
                        }
                    }

                }else{
                    int i = rand.nextInt(3);
                    if(i == 0){
                        codigoGerado = codigoScrum;
                    }else if (i == 1){
                        codigoGerado = codigoLean;
                    }else{
                        codigoGerado = codigoAgile;
                    }
                }
            }else if(agileA >= 3){
                if(scrumA >= 3){
                    if(leanA >= 3){
                        codigoGerado = codigoXp;
                    }else if(xpA >= 3){
                        codigoGerado = codigoLean;
                    }else{
                        if(rand.nextInt(2) == 0){
                            codigoGerado = codigoXp;
                        }else{
                            codigoGerado = codigoLean;
                        }
                    }
                }else if(leanA >=3){
                    if(scrumA >= 3){
                        codigoGerado = codigoXp;
                    }else if(xpA >= 3){
                        codigoGerado = codigoScrum;
                    }else{
                        if(rand.nextInt(2) == 0){
                            codigoGerado = codigoXp;
                        }else{
                            codigoGerado = codigoScrum;
                        }
                    }

                }else if(xpA >=3){
                    if(scrumA >= 3) {
                        codigoGerado = codigoLean;
                    }else if (leanA >= 3){
                        codigoGerado = codigoScrum;
                    }else{
                        if(rand.nextInt(2) == 0){
                            codigoGerado = codigoLean;
                        }else{
                            codigoGerado = codigoScrum;
                        }
                    }

                }else{
                    int i = rand.nextInt(3);
                    if(i == 0){
                        codigoGerado = codigoScrum;
                    }else if (i == 1){
                        codigoGerado = codigoLean;
                    }else{
                        codigoGerado = codigoXp;
                    }
                }
            }else{
                codigoGerado = rand.nextInt(4);
            }

        }else{
            if(scrumB >= 3){
                if(leanB >= 3){
                    if(xpB >= 3){
                        codigoGerado = codigoAgile;
                    }else if(agileB >= 3){
                        codigoGerado = codigoXp;
                    }else{
                        if(rand.nextInt(2) == 0){
                            codigoGerado = codigoAgile;
                        }else{
                            codigoGerado = codigoXp;
                        }
                    }
                }else if(xpB >=3){
                    if(leanB >= 3){
                        codigoGerado = codigoAgile;
                    }else if(agileB >= 3){
                        codigoGerado = codigoLean;
                    }else{
                        if(rand.nextInt(2) == 0){
                            codigoGerado = codigoAgile;
                        }else{
                            codigoGerado = codigoLean;
                        }
                    }

                }else if(agileB >=3){
                    if(leanB >= 3) {
                        codigoGerado = codigoXp;
                    }else if (xpB >= 3){
                        codigoGerado = codigoLean;
                    }else{
                        if(rand.nextInt(2) == 0){
                            codigoGerado = codigoXp;
                        }else{
                            codigoGerado = codigoLean;
                        }
                    }

                }else{
                    int i = rand.nextInt(3);
                    if(i == 0){
                        codigoGerado = codigoLean;
                    }else if (i == 1){
                        codigoGerado = codigoXp;
                    }else{
                        codigoGerado = codigoAgile;
                    }
                }


            }else if(leanB >= 3){
                if(scrumB >= 3){
                    if(xpB >= 3){
                        codigoGerado = codigoAgile;
                    }else if(agileB >= 3){
                        codigoGerado = codigoXp;
                    }else{
                        if(rand.nextInt(2) == 0){
                            codigoGerado = codigoAgile;
                        }else{
                            codigoGerado = codigoXp;
                        }
                    }
                }else if(xpB >=3){
                    if(scrumB >= 3){
                        codigoGerado = codigoAgile;
                    }else if(agileB >= 3){
                        codigoGerado = codigoScrum;
                    }else{
                        if(rand.nextInt(2) == 0){
                            codigoGerado = codigoAgile;
                        }else{
                            codigoGerado = codigoScrum;
                        }
                    }

                }else if(agileB >=3){
                    if(scrumB >= 3) {
                        codigoGerado = codigoXp;
                    }else if (xpB >= 3){
                        codigoGerado = codigoScrum;
                    }else{
                        if(rand.nextInt(2) == 0){
                            codigoGerado = codigoXp;
                        }else{
                            codigoGerado = codigoScrum;
                        }
                    }

                }else{
                    int i = rand.nextInt(3);
                    if(i == 0){
                        codigoGerado = codigoScrum;
                    }else if (i == 1){
                        codigoGerado = codigoXp;
                    }else{
                        codigoGerado = codigoAgile;
                    }
                }
            }else if(xpB >= 3){
                if(scrumB >= 3){
                    if(leanB >= 3){
                        codigoGerado = codigoAgile;
                    }else if(agileB >= 3){
                        codigoGerado = codigoLean;
                    }else{
                        if(rand.nextInt(2) == 0){
                            codigoGerado = codigoAgile;
                        }else{
                            codigoGerado = codigoLean;
                        }
                    }
                }else if(leanB >=3){
                    if(scrumB >= 3){
                        codigoGerado = codigoAgile;
                    }else if(agileB >= 3){
                        codigoGerado = codigoScrum;
                    }else{
                        if(rand.nextInt(2) == 0){
                            codigoGerado = codigoAgile;
                        }else{
                            codigoGerado = codigoScrum;
                        }
                    }

                }else if(agileB >=3){
                    if(scrumB >= 3) {
                        codigoGerado = codigoLean;
                    }else if (leanB >= 3){
                        codigoGerado = codigoScrum;
                    }else{
                        if(rand.nextInt(2) == 0){
                            codigoGerado = codigoLean;
                        }else{
                            codigoGerado = codigoScrum;
                        }
                    }

                }else{
                    int i = rand.nextInt(3);
                    if(i == 0){
                        codigoGerado = codigoScrum;
                    }else if (i == 1){
                        codigoGerado = codigoLean;
                    }else{
                        codigoGerado = codigoAgile;
                    }
                }
            }else if(agileB >= 3){
                if(scrumB >= 3){
                    if(leanB >= 3){
                        codigoGerado = codigoXp;
                    }else if(xpB >= 3){
                        codigoGerado = codigoLean;
                    }else{
                        if(rand.nextInt(2) == 0){
                            codigoGerado = codigoXp;
                        }else{
                            codigoGerado = codigoLean;
                        }
                    }
                }else if(leanB >=3){
                    if(scrumB >= 3){
                        codigoGerado = codigoXp;
                    }else if(xpB >= 3){
                        codigoGerado = codigoScrum;
                    }else{
                        if(rand.nextInt(2) == 0){
                            codigoGerado = codigoXp;
                        }else{
                            codigoGerado = codigoScrum;
                        }
                    }

                }else if(xpB >=3){
                    if(scrumB >= 3) {
                        codigoGerado = codigoLean;
                    }else if (leanB >= 3){
                        codigoGerado = codigoScrum;
                    }else{
                        if(rand.nextInt(2) == 0){
                            codigoGerado = codigoLean;
                        }else{
                            codigoGerado = codigoScrum;
                        }
                    }

                }else{
                    int i = rand.nextInt(3);
                    if(i == 0){
                        codigoGerado = codigoScrum;
                    }else if (i == 1){
                        codigoGerado = codigoLean;
                    }else{
                        codigoGerado = codigoXp;
                    }
                }
            }else{
                codigoGerado = rand.nextInt(4);
            }
        }



        return codigoGerado;
    }

}

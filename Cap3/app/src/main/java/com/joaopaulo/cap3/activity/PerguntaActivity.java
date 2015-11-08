package com.joaopaulo.cap3.activity;


import android.app.AlertDialog;
import android.content.DialogInterface;


import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.joaopaulo.cap3.R;

import java.util.HashMap;


public class PerguntaActivity extends AppCompatActivity {
    private int jumpTime;

    private Button b1,b2,b3,b4;
    private TextView pergunta;
    private char alternativaCorreta;
    private String categoria;
    private HashMap<String,Object> vezNoTurno;
    private HashMap<String,String> jogoExample;

    private ProgressBar timer;
    final private Firebase ref = new Firebase("https://resplendent-heat-382.firebaseio.com/");
    final private Firebase jogosRef = ref.child("jogos");
    final Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta);
        timer = (ProgressBar) findViewById(R.id.progressBar);

        final int progressTime = 100;

        //TODO receber código da intent anterior, montar if else para setar categoria, modificar estrutura JSON
        categoria = "scrum";

        b1 = (Button) findViewById(R.id.btn1);
        b2 = (Button) findViewById(R.id.btn2);
        b3 = (Button) findViewById(R.id.btn3);
        b4 = (Button) findViewById(R.id.btn4);
        pergunta = (TextView) findViewById(R.id.pergunta);

        //-----------------------------------------RECUPERAR PERGUNTAS-----------------------------
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //TODO Fazer com que os botões sejam preenchidos aleatoriamente a partir de 1 numero random gerado
                HashMap<String, String> p = (HashMap) snapshot.child("perguntas").child("scrum02").getValue();
                pergunta.setText(p.get("pergunta"));
                b1.setText(p.get("alternativaA"));
                //b2.setText(p.get("alternativaCorreta"));
                b3.setText(p.get("alternativaB"));
                b4.setText(p.get("alternativaC"));
                alternativaCorreta = 'B';


                jogoExample = (HashMap) snapshot.child("jogos").child("jogoexample").getValue();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        /*
        switch(alternativaCorreta) {
            case 'A':
                b1.setBackground(getResources().getDrawable(R.drawable.button_resposta_shape_correto));
                b2.setBackground(getResources().getDrawable(R.drawable.button_resposta_shape));
                b3.setBackground(getResources().getDrawable(R.drawable.button_resposta_shape));
                b4.setBackground(getResources().getDrawable(R.drawable.button_resposta_shape));

                break;
            case 'B':
                b1.setBackground(getResources().getDrawable(R.drawable.button_resposta_shape));
                b2.setBackground(getResources().getDrawable(R.drawable.button_resposta_shape_correto));
                b3.setBackground(getResources().getDrawable(R.drawable.button_resposta_shape));
                b4.setBackground(getResources().getDrawable(R.drawable.button_resposta_shape));



                break;
            case 'C':

                b1.setBackground(getResources().getDrawable(R.drawable.button_resposta_shape));
                b2.setBackground(getResources().getDrawable(R.drawable.button_resposta_shape));
                b3.setBackground(getResources().getDrawable(R.drawable.button_resposta_shape_correto));
                b4.setBackground(getResources().getDrawable(R.drawable.button_resposta_shape));




                break;
            case 'D':
                b1.setBackground(getResources().getDrawable(R.drawable.button_resposta_shape));
                b2.setBackground(getResources().getDrawable(R.drawable.button_resposta_shape));
                b3.setBackground(getResources().getDrawable(R.drawable.button_resposta_shape));
                b4.setBackground(getResources().getDrawable(R.drawable.button_resposta_shape_correto));



                break;
            default:
                b1.setBackground(getResources().getDrawable(R.drawable.button_resposta_shape));
                b2.setBackground(getResources().getDrawable(R.drawable.button_resposta_shape));
                b3.setBackground(getResources().getDrawable(R.drawable.button_resposta_shape));
                b4.setBackground(getResources().getDrawable(R.drawable.button_resposta_shape));
                break;

        }

        /*

        ---------------------------------------TIMER---------------------------------------------------

         */

        final Thread t = new Thread(){

            @Override
            public void run(){


                jumpTime = 0;
                while(jumpTime < progressTime){
                    try {
                        sleep(150);
                        jumpTime += 1;
                        timer.setProgress(jumpTime);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }


                //TODO criar dependencia de qual usuários está logado atualmente
                vezNoTurno = new HashMap<String,Object>();
                vezNoTurno.put("jogoexample/vezNoTurno", "B");
                jogosRef.updateChildren(vezNoTurno);


                mHandler.post(new Runnable() {
                    public void run() {

                        AlertDialog.Builder builder = new AlertDialog.Builder(PerguntaActivity.this);
                        builder.setTitle("Alerta!");
                        builder.setMessage("Você excedeu o tempo limite para responder a pergunta e perdeu sua vez!");
                        builder.setIcon(R.drawable.feelsbad);
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                onBackPressed();

                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();


                    }
                });

            }

        };
        t.start();

/*

        ---------------------------------FIM DO TIMER---------------------------------------------------

         */

    }

    @Override
    protected void onPause(){
        super.onPause();
        jumpTime = 0;


    }

    public void responderA() {
        HashMap<String,Object> updateStatus = new HashMap<>();
        int aux;
        //TODO criar dependencia do usuário que está LOGADO no momento
        //TODO

        //------------------------RESPONDEU CORRETAMENTE-----------------------------
        if (alternativaCorreta == 'A') {


            switch (categoria){
                case "scrum":
                    aux = Integer.parseInt(jogoExample.get("scrumA"));
                    updateStatus.put("jogoexample/scrumA",""+(aux+1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                case "xp":
                    aux = Integer.parseInt(jogoExample.get("xpA"));
                    updateStatus.put("jogoexample/xpA",""+(aux+1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                case "agile":
                    aux = Integer.parseInt(jogoExample.get("agileA"));
                    updateStatus.put("jogoexample/agileA",""+(aux+1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                case "lean":
                    aux = Integer.parseInt(jogoExample.get("leanA"));
                    updateStatus.put("jogoexample/leanA",""+(aux+1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                default:
                    break;
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(PerguntaActivity.this);
            builder.setTitle("PARABÉNS!");
            builder.setMessage("Você respondeu corretamente!!!");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    onBackPressed();

                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();


            //----------------------------------RESPONDEU ERRADO-----------------------------------
        }else{
            vezNoTurno.put("jogoexample/vezNoTurno", "B");
            jogosRef.updateChildren(vezNoTurno);

            AlertDialog.Builder builder = new AlertDialog.Builder(PerguntaActivity.this);
            builder.setTitle(":(");
            builder.setIcon(R.drawable.feelsbad);
            builder.setMessage("Você respondeu errado!!!");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    onBackPressed();

                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        }

    }


    public void responderB() {
        HashMap<String,Object> updateStatus = new HashMap<>();
        int aux;
        //TODO criar dependencia do usuário que está LOGADO no momento
        //TODO

        //------------------------RESPONDEU CORRETAMENTE-----------------------------
        if (alternativaCorreta == 'B') {

            switch (categoria){
                case "scrum":
                    aux = Integer.parseInt(jogoExample.get("scrumA"));
                    updateStatus.put("jogoexample/scrumA",""+(aux+1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                case "xp":
                    aux = Integer.parseInt(jogoExample.get("xpA"));
                    updateStatus.put("jogoexample/xpA",""+(aux+1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                case "agile":
                    aux = Integer.parseInt(jogoExample.get("agileA"));
                    updateStatus.put("jogoexample/agileA",""+(aux+1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                case "lean":
                    aux = Integer.parseInt(jogoExample.get("leanA"));
                    updateStatus.put("jogoexample/leanA",""+(aux+1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                default:
                    break;
            }


                    AlertDialog.Builder builder = new AlertDialog.Builder(PerguntaActivity.this);
                    builder.setTitle("PARABÉNS!");
                    builder.setMessage("Você respondeu corretamente!!!");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            onBackPressed();

                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();

            //----------------------------------RESPONDEU ERRADO-----------------------------------
        }else{
            vezNoTurno.put("jogoexample/vezNoTurno", "B");
            jogosRef.updateChildren(vezNoTurno);

            AlertDialog.Builder builder = new AlertDialog.Builder(PerguntaActivity.this);
                    builder.setTitle(":(");
                    builder.setIcon(R.drawable.feelsbad);
                    builder.setMessage("Você respondeu errado!!!");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            onBackPressed();

                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();

        }

    }

    public void responderC() {
        HashMap<String,Object> updateStatus = new HashMap<>();
        int aux;
        //TODO criar dependencia do usuário que está LOGADO no momento
        //TODO

        //------------------------RESPONDEU CORRETAMENTE-----------------------------
        if (alternativaCorreta == 'C') {


            switch (categoria){
                case "scrum":
                    aux = Integer.parseInt(jogoExample.get("scrumA"));
                    updateStatus.put("jogoexample/scrumA",""+(aux+1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                case "xp":
                    aux = Integer.parseInt(jogoExample.get("xpA"));
                    updateStatus.put("jogoexample/xpA",""+(aux+1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                case "agile":
                    aux = Integer.parseInt(jogoExample.get("agileA"));
                    updateStatus.put("jogoexample/agileA",""+(aux+1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                case "lean":
                    aux = Integer.parseInt(jogoExample.get("leanA"));
                    updateStatus.put("jogoexample/leanA",""+(aux+1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                default:
                    break;
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(PerguntaActivity.this);
            builder.setTitle("PARABÉNS!");
            builder.setMessage("Você respondeu corretamente!!!");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    onBackPressed();

                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();


            //----------------------------------RESPONDEU ERRADO-----------------------------------
        }else{
            vezNoTurno.put("jogoexample/vezNoTurno", "B");
            jogosRef.updateChildren(vezNoTurno);

            AlertDialog.Builder builder = new AlertDialog.Builder(PerguntaActivity.this);
            builder.setTitle(":(");
            builder.setIcon(R.drawable.feelsbad);
            builder.setMessage("Você respondeu errado!!!");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    onBackPressed();

                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        }

    }

    public void responderD() {
        HashMap<String,Object> updateStatus = new HashMap<>();
        int aux;
        //TODO criar dependencia do usuário que está LOGADO no momento
        //TODO

        //------------------------RESPONDEU CORRETAMENTE-----------------------------
        if (alternativaCorreta == 'D') {


            switch (categoria){
                case "scrum":
                    aux = Integer.parseInt(jogoExample.get("scrumA"));
                    updateStatus.put("jogoexample/scrumA",""+(aux+1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                case "xp":
                    aux = Integer.parseInt(jogoExample.get("xpA"));
                    updateStatus.put("jogoexample/xpA",""+(aux+1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                case "agile":
                    aux = Integer.parseInt(jogoExample.get("agileA"));
                    updateStatus.put("jogoexample/agileA",""+(aux+1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                case "lean":
                    aux = Integer.parseInt(jogoExample.get("leanA"));
                    updateStatus.put("jogoexample/leanA",""+(aux+1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                default:
                    break;
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(PerguntaActivity.this);
            builder.setTitle("PARABÉNS!");
            builder.setMessage("Você respondeu corretamente!!!");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    onBackPressed();

                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();


            //----------------------------------RESPONDEU ERRADO-----------------------------------
        }else{
            vezNoTurno.put("jogoexample/vezNoTurno", "B");
            jogosRef.updateChildren(vezNoTurno);

            AlertDialog.Builder builder = new AlertDialog.Builder(PerguntaActivity.this);
            builder.setTitle(":(");
            builder.setIcon(R.drawable.feelsbad);
            builder.setMessage("Você respondeu errado!!!");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    onBackPressed();

                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();

        }

    }


}

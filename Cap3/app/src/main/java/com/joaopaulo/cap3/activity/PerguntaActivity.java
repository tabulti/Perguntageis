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

    private ProgressBar timer;
    final private Firebase ref = new Firebase("https://resplendent-heat-382.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta);
        timer = (ProgressBar) findViewById(R.id.progressBar);
        final Handler mHandler = new Handler();
        final int progressTime = 100;

        b1 = (Button) findViewById(R.id.btn1);
        b2 = (Button) findViewById(R.id.btn2);
        b3 = (Button) findViewById(R.id.btn3);
        b4 = (Button) findViewById(R.id.btn4);
        pergunta = (TextView) findViewById(R.id.pergunta);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                HashMap<String, String> p = (HashMap) snapshot.child("perguntas").child("scrum02").getValue();
                pergunta.setText(p.get("pergunta"));
                b1.setText(p.get("alternativaCorreta"));
                b2.setText(p.get("alternativaA"));
                b3.setText(p.get("alternativaB"));
                b4.setText(p.get("alternativaC"));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



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

                Firebase jogosRef = ref.child("jogos");
                HashMap<String,Object> vezNoTurno = new HashMap<String,Object>();
                vezNoTurno.put("jogoexample/vezNoTurno", "B");
                jogosRef.updateChildren(vezNoTurno);

                //Alert Cadastro bem-sucedido
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



    }

    @Override
    protected void onPause(){
        super.onPause();
        jumpTime = 0;

    }

    public void responder(){


        onBackPressed();
    }


}

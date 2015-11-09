package com.joaopaulo.cap3.activity;


import android.app.AlertDialog;
import android.content.DialogInterface;


import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
import java.util.Random;


public class PerguntaActivity extends AppCompatActivity {
    private int jumpTime;
    final int progressTime = 100;
    private Button b1, b2, b3, b4;
    private TextView pergunta;
    private char alternativaCorreta;
    private String categoria;
    private HashMap<String, Object> vezNoTurno;
    private HashMap<String, String> jogoExample;
    volatile boolean pause = false; 
    private String login;
    private int codigoTema;

    private Thread t = new Thread() {
        @Override
        public void run() {

            jumpTime = 0;
            while (jumpTime < progressTime) {
                try {
                    sleep(400);
                    jumpTime += 1;
                    timer.setProgress(jumpTime);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (pause == false) {
                //TODO criar dependencia de qual usuários está logado atualmente
                vezNoTurno = new HashMap<String, Object>();
                vezNoTurno.put("jogoexample/vezNoTurno", "B");
                jogosRef.updateChildren(vezNoTurno);


                mHandler.post(new Runnable() {
                    public void run() {
                        if (jumpTime == 100) {
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
                        } else {

                        }
                    }
                });
            } else {

            }

        }

    };
    private ProgressBar timer;
    final private Firebase ref = new Firebase("https://resplendent-heat-382.firebaseio.com/");
    final private Firebase jogosRef = ref.child("jogos");
    final Handler mHandler = new Handler();
    private int btnOrganization;
    private Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta);
        timer = (ProgressBar) findViewById(R.id.progressBar);
        Bundle args = getIntent().getExtras();
        login = args.getString("login");
        codigoTema = args.getInt("codigotema");

        if(codigoTema == 0){
            categoria = "scrum";
        }else if(codigoTema == 1){
            categoria = "xp";
        }else if(codigoTema == 2){
            categoria = "agile";
        }else {
            categoria = "lean";
        }
        btnOrganization = rand.nextInt(4);

        b1 = (Button) findViewById(R.id.btn1);
        b2 = (Button) findViewById(R.id.btn2);
        b3 = (Button) findViewById(R.id.btn3);
        b4 = (Button) findViewById(R.id.btn4);
        pergunta = (TextView) findViewById(R.id.pergunta);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btn1) {
                    pause = true;

                    responderA();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btn2) {
                    pause = true;
                    responderB();
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btn3) {
                    pause = true;
                    responderC();
                }
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btn4) {
                    pause = true;
                    responderD();
                }
            }
        });

        //-----------------------------------------RECUPERAR PERGUNTAS-----------------------------
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                HashMap<String, String> p = (HashMap) snapshot.child("perguntas").child(categoria+ "" + (rand.nextInt(6)+1)).getValue();

                switch (btnOrganization) {
                    case 0:
                        alternativaCorreta = 'A';
                        b1.setText(p.get("alternativaCorreta"));
                        b2.setText(p.get("alternativaA"));
                        b3.setText(p.get("alternativaB"));
                        b4.setText(p.get("alternativaC"));
                        break;
                    case 1:
                        alternativaCorreta = 'B';
                        b1.setText(p.get("alternativaA"));
                        b2.setText(p.get("alternativaCorreta"));
                        b3.setText(p.get("alternativaB"));
                        b4.setText(p.get("alternativaC"));
                        break;
                    case 2:
                        alternativaCorreta = 'C';
                        b1.setText(p.get("alternativaA"));
                        b2.setText(p.get("alternativaB"));
                        b3.setText(p.get("alternativaCorreta"));
                        b4.setText(p.get("alternativaC"));
                        break;
                    case 3:
                        alternativaCorreta = 'D';
                        b1.setText(p.get("alternativaA"));
                        b2.setText(p.get("alternativaB"));
                        b3.setText(p.get("alternativaC"));
                        b4.setText(p.get("alternativaCorreta"));
                        break;
                    default:
                        break;
                }
                pergunta.setText(p.get("pergunta"));

                jogoExample = (HashMap) snapshot.child("jogos").child("jogoexample").getValue();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        /*

        ---------------------------------------TIMER---------------------------------------------------

         */


        t.start();

/*

        ---------------------------------FIM DO TIMER---------------------------------------------------

         */

    }

    @Override
    protected void onPause() {
        super.onPause();
        jumpTime = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        pause = false;
        btnOrganization = rand.nextInt(4);
    }


    public void responderA() {

        HashMap<String, Object> updateStatus = new HashMap<String,Object>();
        int aux;
        //TODO criar dependencia do usuário que está LOGADO no momento
        //TODO

        //------------------------RESPONDEU CORRETAMENTE-----------------------------
        if (alternativaCorreta == 'A') {
            switch (categoria) {
                case "scrum":
                    aux = Integer.parseInt(jogoExample.get("scrumA"));
                    updateStatus.put("jogoexample/scrumA", "" + (aux + 1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                case "xp":
                    aux = Integer.parseInt(jogoExample.get("xpA"));
                    updateStatus.put("jogoexample/xpA", "" + (aux + 1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                case "agile":
                    aux = Integer.parseInt(jogoExample.get("agileA"));
                    updateStatus.put("jogoexample/agileA", "" + (aux + 1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                case "lean":
                    aux = Integer.parseInt(jogoExample.get("leanA"));
                    updateStatus.put("jogoexample/leanA", "" + (aux + 1));
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
        } else {

            vezNoTurno = new HashMap<String, Object>();
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
        HashMap<String, Object> updateStatus = new HashMap<String,Object>();
        int aux;
        //TODO criar dependencia do usuário que está LOGADO no momento
        //TODO

        //------------------------RESPONDEU CORRETAMENTE-----------------------------
        if (alternativaCorreta == 'B') {

            switch (categoria) {
                case "scrum":
                    aux = Integer.parseInt(jogoExample.get("scrumA"));
                    updateStatus.put("jogoexample/scrumA", "" + (aux + 1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                case "xp":
                    aux = Integer.parseInt(jogoExample.get("xpA"));
                    updateStatus.put("jogoexample/xpA", "" + (aux + 1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                case "agile":
                    aux = Integer.parseInt(jogoExample.get("agileA"));
                    updateStatus.put("jogoexample/agileA", "" + (aux + 1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                case "lean":
                    aux = Integer.parseInt(jogoExample.get("leanA"));
                    updateStatus.put("jogoexample/leanA", "" + (aux + 1));
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
        } else {
            vezNoTurno = new HashMap<String, Object>();
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
        HashMap<String, Object> updateStatus = new HashMap<String,Object>();
        int aux;
        //TODO criar dependencia do usuário que está LOGADO no momento
        //TODO

        //------------------------RESPONDEU CORRETAMENTE-----------------------------
        if (alternativaCorreta == 'C') {


            switch (categoria) {
                case "scrum":
                    aux = Integer.parseInt(jogoExample.get("scrumA"));
                    updateStatus.put("jogoexample/scrumA", "" + (aux + 1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                case "xp":
                    aux = Integer.parseInt(jogoExample.get("xpA"));
                    updateStatus.put("jogoexample/xpA", "" + (aux + 1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                case "agile":
                    aux = Integer.parseInt(jogoExample.get("agileA"));
                    updateStatus.put("jogoexample/agileA", "" + (aux + 1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                case "lean":
                    aux = Integer.parseInt(jogoExample.get("leanA"));
                    updateStatus.put("jogoexample/leanA", "" + (aux + 1));
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
        } else {
            vezNoTurno = new HashMap<String, Object>();
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
        HashMap<String, Object> updateStatus = new HashMap<String, Object>();
        int aux;
        //TODO criar dependencia do usuário que está LOGADO no momento
        //TODO

        //------------------------RESPONDEU CORRETAMENTE-----------------------------
        if (alternativaCorreta == 'D') {


            switch (categoria) {
                case "scrum":
                    aux = Integer.parseInt(jogoExample.get("scrumA"));
                    updateStatus.put("jogoexample/scrumA", "" + (aux + 1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                case "xp":
                    aux = Integer.parseInt(jogoExample.get("xpA"));
                    updateStatus.put("jogoexample/xpA", "" + (aux + 1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                case "agile":
                    aux = Integer.parseInt(jogoExample.get("agileA"));
                    updateStatus.put("jogoexample/agileA", "" + (aux + 1));
                    jogosRef.updateChildren(updateStatus);
                    break;
                case "lean":
                    aux = Integer.parseInt(jogoExample.get("leanA"));
                    updateStatus.put("jogoexample/leanA", "" + (aux + 1));
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
        } else {
            vezNoTurno = new HashMap<String, Object>();
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

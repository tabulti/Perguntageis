package com.joaopaulo.cap3.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.joaopaulo.cap3.R;

import java.util.HashMap;

public class FimDeJogoActivity extends AppCompatActivity {

    private final Firebase ref = new Firebase("https://perguntageis.firebaseio.com/");
    private Firebase jogoRef = ref.child("jogos").child("jogoexample");
    private HashMap<String,String> dadosJogo,usuario1,usuario2;
    private HashMap<String,Object> atualizaDados = new HashMap<>();

    private String vencedor;
    private String login;
    private ImageView resultado,rank;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim_de_jogo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //TODO MANIPULAR DADOS DO JOGO!!
        rank = (ImageView) findViewById(R.id.rank);
        Bundle args = getIntent().getExtras();
        login = args.getString("login");
        vencedor = args.getString("vencedor");
        resultado = (ImageView) findViewById(R.id.resultado);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usuario1 = (HashMap)dataSnapshot.child("usuarios").child("u1").getValue();
                usuario2 = (HashMap)dataSnapshot.child("usuarios").child("u2").getValue();

                if(login.equals(usuario1.get("email"))){
                    switch (Integer.parseInt(usuario1.get("rank"))){
                        case 25:
                            rank.setImageResource(R.drawable.rankvintecinco);
                            break;
                        case 24:
                            rank.setImageResource(R.drawable.rankvintequatro);
                            break;
                        case 23:
                            rank.setImageResource(R.drawable.rankvintetres);
                            break;
                        case 22:
                            rank.setImageResource(R.drawable.rankvintedois);
                            break;
                        case 21:
                            rank.setImageResource(R.drawable.rankvintedois);
                            break;
                        default:
                            rank.setImageResource(R.drawable.rankvintecinco);
                            break;
                    }
                }else if(login.equals(usuario2.get("email"))){
                    switch (Integer.parseInt(usuario2.get("rank"))){
                        case 25:
                            rank.setImageResource(R.drawable.rankvintecinco);
                            break;
                        case 24:
                            rank.setImageResource(R.drawable.rankvintequatro);
                            break;
                        case 23:
                            rank.setImageResource(R.drawable.rankvintetres);
                            break;
                        case 22:
                            rank.setImageResource(R.drawable.rankvintedois);
                            break;
                        case 21:
                            rank.setImageResource(R.drawable.rankvintedois);
                            break;
                        default:
                            rank.setImageResource(R.drawable.rankvintecinco);
                            break;
                    }
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });




        if(vencedor.equals(login)){
            resultado.setImageResource(R.drawable.vitoria);
        }else{
            resultado.setImageResource(R.drawable.derrota);
        }





    }

    @Override
    public void onBackPressed(){
        if(vencedor.equals(usuario1.get("email"))){
            atualizaDados.put("usuarios/u1/gold",""+(Integer.parseInt(usuario1.get("gold"))+10));
            ref.updateChildren(atualizaDados);
        }else if(vencedor.equals(usuario2.get("email"))){
            atualizaDados.put("usuarios/u2/gold",""+(Integer.parseInt(usuario2.get("gold"))+10));
            ref.updateChildren(atualizaDados);
        }else{

        }

        FimDeJogoActivity.this.finish();
        super.onBackPressed();

    }

    public void voltarHome(View view){

        onBackPressed();
    }

}

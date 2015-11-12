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

    private final Firebase ref = new Firebase("https://resplendent-heat-382.firebaseio.com/");
    private Firebase jogoRef = ref.child("jogos").child("jogoexample");
    private HashMap<String,String> dadosJogo;
    private HashMap<String,Object> updateStatus = new HashMap<>();
    private String vencedor;
    private String login;
    private ImageView resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fim_de_jogo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        jogoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dadosJogo = (HashMap)dataSnapshot.getValue();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        //TODO MANIPULAR DADOS DO JOGO!!

        Bundle args = getIntent().getExtras();
        login = args.getString("login");
        vencedor = args.getString("vencedor");
        resultado = (ImageView) findViewById(R.id.resultado);

        if(vencedor.equals(login)){
            resultado.setImageResource(R.drawable.vitoria);
        }else{
            resultado.setImageResource(R.drawable.derrota);
        }

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







    }

}

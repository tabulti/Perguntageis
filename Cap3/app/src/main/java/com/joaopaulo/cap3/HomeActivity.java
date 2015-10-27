package com.joaopaulo.cap3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Bundle args = getIntent().getExtras();
        String login = args.getString("login");

        TextView msgBoasVindas = (TextView) findViewById(R.id.tvCardPerfil);
        msgBoasVindas.setText("Bem vindo " + login + "!");
        //msgBoasVindas.setTextSize(20);

    }
}

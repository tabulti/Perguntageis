package com.joaopaulo.cap3.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.joaopaulo.cap3.R;

public class NivelActivity extends AppCompatActivity {


    private int total = 0;

    private ProgressBar progressoUsuario;
    private TextView progressoUsu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel);



        progressoUsuario = (ProgressBar) findViewById(R.id.lvlProgressBar);
        progressoUsu = (TextView) findViewById(R.id.progressoTexto);
        progressoUsu.setText("0%");

        progressoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = 10 + total;
                progressoUsuario.setProgress(total);
                progressoUsu.setText(total + "%");

                if (total == 100) {
                    total = 0;
                }
            }
        });
    }
}

package com.joaopaulo.cap3.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.joaopaulo.cap3.R;

public class CadastroActivity extends AppCompatActivity{
    Toolbar toolbarCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        toolbarCadastro = (Toolbar) findViewById(R.id.toolbar);
        toolbarCadastro.setTitle("Cadastro");
        setSupportActionBar(toolbarCadastro);

        Button btProximo = (Button) findViewById(R.id.btProximo);
        btProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText login = (EditText) findViewById(R.id.edtLoginCadastro);
                EditText senha = (EditText) findViewById(R.id.edtSenhaCadastro2);
                EditText email = (EditText) findViewById(R.id.edtEmailCadastro);

                String userLogin = login.getText().toString();
                String userSenha = senha.getText().toString();
                String userEmail = email.getText().toString();

                Bundle userParams = new Bundle();
                userParams.putString("userLogin", userLogin);
                userParams.putString("userSenha", userSenha);
                userParams.putString("userEmail", userEmail);

                Intent intent = new Intent(getBaseContext(), CadastroActivity2.class);

                intent.putExtras(userParams);
                startActivity(intent);
            }

        });
    }
}

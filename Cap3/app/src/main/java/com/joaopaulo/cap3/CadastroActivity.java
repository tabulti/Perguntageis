package com.joaopaulo.cap3;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class CadastroActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Button btProximo = (Button) findViewById(R.id.btProximo);
        btProximo.setOnClickListener(this);

    }

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

        Intent intent = new Intent(getBaseContext(),CadastroActivity2.class);

        intent.putExtras(userParams);
        startActivity(intent);
    }
}

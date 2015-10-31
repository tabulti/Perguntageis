package com.joaopaulo.cap3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.firebase.client.Firebase;
import com.joaopaulo.cap3.R;

public class MainActivity extends AppCompatActivity{

    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Insancia do facebook para login
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_main);

        loginButton = (LoginButton)findViewById(R.id.login_button);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btLogin = (Button) findViewById(R.id.btLogin);
        Button btCadastro = (Button) findViewById(R.id.btCadastro);


        //Evento do botão login pelo facebook
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                alert(
                        "User ID: "
                                + loginResult.getAccessToken().getUserId()
                                + "\n" +
                                "Auth Token: "
                                + loginResult.getAccessToken().getToken()
                );
            }

            @Override
            public void onCancel() {
                alert("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                alert("Login attempt failed.");
            }
        });


        //Evento do botão cadastro
        btCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btCadastro) {
                    Intent intent = new Intent(getBaseContext(), CadastroActivity.class);
                    startActivity(intent);
                }
            }
        });


       //Login local pré-definido
       btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btLogin) {

                    final TextView tLogin = (TextView) findViewById(R.id.tLogin);
                    TextView tSenha = (TextView) findViewById(R.id.tSenha);

                    String login = tLogin.getText().toString();
                    String senha = tSenha.getText().toString();


                    if ("tabulti".equals(login) && "123".equals(senha)) {
                        Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                        Bundle params = new Bundle();
                        params.putString("login", login);
                        intent.putExtras(params);
                        startActivity(intent);
                    } else {
                        alert("Login e senha incorretos.");
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    //Método para mostrar um toast
    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
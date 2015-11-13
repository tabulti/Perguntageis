package com.joaopaulo.cap3.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.joaopaulo.cap3.R;

public class MainActivity extends AppCompatActivity{

    private LoginButton loginButtonFacebook;
    private CallbackManager callbackManager;
    Firebase ref;
    ProgressDialog mAuthProgressDialog;
    private Firebase.AuthStateListener mAuthStateListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Firebase.setAndroidContext(this);

        //Insancia do facebook para login
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_main);

        Button btLogin = (Button) findViewById(R.id.btLogin);
        Button btCadastro = (Button) findViewById(R.id.btCadastro);




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

                    final String login = tLogin.getText().toString();
                    String senha = tSenha.getText().toString();


                    mAuthProgressDialog = new ProgressDialog(MainActivity.this);
                    mAuthProgressDialog.setTitle("Loading");
                    mAuthProgressDialog.setMessage("Authenticating with Firebase...");
                    mAuthProgressDialog.setCancelable(false);
                    mAuthProgressDialog.show();

                    ref = new Firebase("https://perguntageis.firebaseio.com/");

                    //Handler Firebase
                    Firebase.AuthResultHandler authResultHandler = new Firebase.AuthResultHandler() {
                        @Override
                        public void onAuthenticated(AuthData authData) {

                            // Authenticated successfully with payload authData
                            mAuthProgressDialog.hide();
                            alert("teste Autenticado");

                            Intent intent = new Intent(getBaseContext(), HomeActivity.class);

                            intent.putExtra("login", login);

                            startActivity(intent);

                            /**
                             * TODO: chamar HomeActivity
                             */
                        }
                        @Override
                        public void onAuthenticationError(FirebaseError firebaseError) {
                            // Authenticated failed with error firebaseError
                            alert("teste não-autenticado");
                            Log.i("TESTE", firebaseError.getMessage());
                            mAuthProgressDialog.hide();
                        }
                    };


                    ref.authWithPassword(login, senha, authResultHandler);


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
package com.joaopaulo.cap3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.FacebookSdk;

import com.facebook.appevents.AppEventsLogger;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.joaopaulo.cap3.R;
import com.joaopaulo.cap3.activity.CadastroActivity;
import com.joaopaulo.cap3.activity.HomeActivity;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());

        Firebase.setAndroidContext(this);
        final Firebase perguntageisFireBase = new Firebase("https://perguntageis.firebaseio.com/");



        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);

        Button btLogin = (Button) findViewById(R.id.btLogin);
        Button btCadastro = (Button) findViewById(R.id.btCadastro);



        btCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btCadastro) {
                    Intent intent = new Intent(getBaseContext(), CadastroActivity.class);
                    startActivity(intent);
                }
            }
        });



        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btLogin) {

                    final TextView tLogin = (TextView) findViewById(R.id.tLogin);
                    TextView tSenha = (TextView) findViewById(R.id.tSenha);

                    String login = tLogin.getText().toString();
                    String senha = tSenha.getText().toString();


                    perguntageisFireBase.child("message").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {

                        }

                        @Override
                        public void onCancelled(FirebaseError error) {
                        }
                    });

                    /**
                     * TODO
                     * implementar um laço para percorrer um
                     * json de logins e senhas para autenticação
                     * dentro do if-else.
                     */
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
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }


    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
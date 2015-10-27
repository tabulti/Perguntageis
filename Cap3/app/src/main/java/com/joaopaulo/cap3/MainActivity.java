package com.joaopaulo.cap3;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btLogin = (Button) findViewById(R.id.btLogin);
        Button btCadastro = (Button) findViewById(R.id.btCadastro);

        btLogin.setOnClickListener(this);
        btCadastro.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btLogin) {

            TextView tLogin = (TextView) findViewById(R.id.tLogin);
            TextView tSenha = (TextView) findViewById(R.id.tSenha);

            String login = tLogin.getText().toString();
            String senha = tSenha.getText().toString();

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
        } else{
            if (view.getId() == R.id.btCadastro) {
                Intent intent = new Intent(getBaseContext(), CadastroActivity.class);
                startActivity(intent);
            }
        }
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/

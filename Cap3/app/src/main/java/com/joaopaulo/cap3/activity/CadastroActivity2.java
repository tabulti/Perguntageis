package com.joaopaulo.cap3.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.joaopaulo.cap3.R;
import com.joaopaulo.cap3.adapters.ImageAdaptor;
import java.util.HashMap;
import java.util.Map;

/**
 * Activity que finaliza o cadastro de usuário, recuperando dados da
 * CadastroActivity e adicionando outros.
 *
 * TODO: implentar onActivityDestroyed() para impedir que um usuario
 * volte para esta activity ao finalizar um cadastro
 */

public class CadastroActivity2 extends AppCompatActivity {

    String userLogin, userSenha, userEmail;
    int avatarImgCode;
    Toolbar cadastro2Toolbar;
    GridView grid;
    Map<String, String> mapUser;

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_cadastro2);

        //Conexão com FireBase
        Firebase.setAndroidContext(this);
        final Firebase perguntageisFireBase = new Firebase("https://perguntageis.firebaseio.com/");

        //Setando Toolbar
        cadastro2Toolbar = (Toolbar) findViewById(R.id.toolbar);
        cadastro2Toolbar.setTitle("Cadastro");
        setSupportActionBar(cadastro2Toolbar);

        //Recuperando dados de parâmetros
        Bundle args = getIntent().getExtras();
        userLogin = args.getString("userLogin");
        userSenha = args.getString("userSenha");
        userEmail = args.getString("userEmail");

        Button btConfirmarCadastro = (Button) findViewById(R.id.btConfirmaCadastro);

        //Instanciando e inicializando gridView com seu adapter
        grid = (GridView) findViewById(R.id.gridAvatar);
        grid.setOnItemClickListener(onGridViewItemClick());
        grid.setAdapter(new ImageAdaptor(this));

        //Evento de clique do botão para confirmar o cadastro
        btConfirmarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btConfirmaCadastro) {

                    //Instanciando um novo usuário
                    mapUser = new HashMap<>();
                    mapUser.put("login", userLogin);
                    mapUser.put("senha", userSenha);
                    mapUser.put("email", userEmail);
                    mapUser.put("imageCode", String.valueOf(avatarImgCode));
                    //mapUser.put("imageLvlCode", String.valueOf(25));


                    //Handler/Callback para credenciais de usuário
                    Firebase.ResultHandler handler = new Firebase.ResultHandler() {
                        @Override
                        public void onSuccess() {
                            //Sucesso
                        }

                        @Override
                        public void onError(FirebaseError firebaseError) {
                            //Erro
                        }
                    };
                    //Criando credenciais de acesso do usuário
                    perguntageisFireBase.createUser(userEmail, userLogin, handler);

                    //Salvando um usuário no firebase
                    perguntageisFireBase.child("users").push().setValue(mapUser);

                    //Alert Cadastro bem-sucedido
                    AlertDialog.Builder builder = new AlertDialog.Builder(CadastroActivity2.this);
                    builder.setTitle("Tudo Certo!");
                    builder.setMessage("Você foi cadastrado com sucesso!");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(CadastroActivity2.this, HomeActivity.class);
                            Bundle params = new Bundle();
                            params.putString("login", userLogin);
                            intent.putExtras(params);
                            startActivity(intent);
                            return;
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }

    //Evento de click no gridView
    private AdapterView.OnItemClickListener onGridViewItemClick() {
        return new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int posicao, long id) {
                switch (posicao) {
                    case 0:
                        showToast("Capitão América selecionado");
                        avatarImgCode = 0;
                        break;
                    case 1:
                        showToast("Homem de Ferro selecionado");
                        avatarImgCode = 1;
                        break;
                    case 2:
                        showToast("Feiticeira Escalarte selecionada");
                        avatarImgCode = 2;
                        break;
                    case 3:
                        showToast("Thor selecionado");
                        avatarImgCode = 3;
                        break;
                    case 4:
                        showToast("Nick Fury selecionado");
                        avatarImgCode = 4;
                        break;
                    case 5:
                        showToast("Gavião Arqueiro selecionado");
                        avatarImgCode = 5;
                        break;
                }
            }
        };
    }
    //Método para mostrar um toast
    public void showToast(String msg) {
        Toast.makeText(CadastroActivity2.this, msg, Toast.LENGTH_SHORT).show();
    }

}

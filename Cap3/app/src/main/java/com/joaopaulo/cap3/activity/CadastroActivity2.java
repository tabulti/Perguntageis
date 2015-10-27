package com.joaopaulo.cap3.activity;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.joaopaulo.cap3.R;
import com.joaopaulo.cap3.adapters.ImageAdaptor;
import com.joaopaulo.cap3.domain.Usuario;

public class CadastroActivity2 extends AppCompatActivity{

    String userLogin, userSenha, userEmail;
    int avatarImgCode;
    Toolbar cadastro2Toolbar;
    ImageView imgSelecionada;
    GridView grid;

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_cadastro2);

        cadastro2Toolbar = (Toolbar) findViewById(R.id.toolbar);
        cadastro2Toolbar.setTitle("Cadastro");
        setSupportActionBar(cadastro2Toolbar);

        Bundle args = getIntent().getExtras();
        userLogin = args.getString("userLogin");
        userSenha = args.getString("userSenha");
        userEmail = args.getString("userEmail");

        Button btConfirmarCadastro = (Button) findViewById(R.id.btConfirmaCadastro);

        grid = (GridView) findViewById(R.id.gridAvatar);
        grid.setOnItemClickListener(onGridViewItemClick());
        grid.setAdapter(new ImageAdaptor(this));


        btConfirmarCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId() == R.id.btConfirmaCadastro) {
                    Usuario usuario = new Usuario(userLogin, userSenha, userEmail, avatarImgCode);
                    /**
                     * TODO: inserir usuario no arquivo json de cadastro
                     */

                    Toast.makeText(CadastroActivity2.this, "teste", Toast.LENGTH_SHORT).show();

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

    //Evento de click no grid
    private AdapterView.OnItemClickListener onGridViewItemClick() {
        return new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v, int posicao, long id) {
                imgSelecionada = (ImageView) v.findViewById(R.id.img);
                switch(posicao){
                    case 0:
                        showToast("Capitão América selecionado");
                        break;
                    case 1:
                        showToast("Homem de Ferro selecionado");
                        break;
                    case 2:
                        showToast("Feiticeira Escalarte selecionada");
                        break;
                    case 3:
                        showToast("Thor selecionado");
                        break;
                    case 4:
                        showToast("Nick Fury selecionado");
                        break;
                    case 5:
                        showToast("Gavião Arqueiro selecionado");
                        break;
                }
            }
        };
    }

    public void showToast(String msg){
        Toast.makeText(CadastroActivity2.this, msg, Toast.LENGTH_SHORT).show();
    }
}

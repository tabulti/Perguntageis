package com.joaopaulo.cap3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

public class CadastroActivity2 extends AppCompatActivity implements View.OnClickListener{

    String userLogin, userSenha, userEmail;

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_cadastro2);

        Bundle args = getIntent().getExtras();
        userLogin = args.getString("userLogin");
        userSenha = args.getString("userSenha");
        userEmail = args.getString("userEmail");

        Button btConfirmarCadastro = (Button) findViewById(R.id.btConfirmaCadastro);

        GridView grid = (GridView) findViewById(R.id.gridAvatar);
        grid.setOnItemClickListener(onGridViewItemClick());
        grid.setAdapter(new ImageAdaptor(this));


        //Teste
        Toast.makeText(this, userLogin, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, userSenha, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, userEmail, Toast.LENGTH_SHORT).show();

    }

    private AdapterView.OnItemClickListener onGridViewItemClick() {
        return new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v, int posicao, long id){
                Toast.makeText(CadastroActivity2.this, "Imagem selecionada: " + posicao, Toast.LENGTH_SHORT).show();
            }
        };
    }

    //onClick de btnConfirma
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btConfirmaCadastro){
            Usuario usuario = new Usuario(userLogin, userSenha, userEmail);
        }
    }
}

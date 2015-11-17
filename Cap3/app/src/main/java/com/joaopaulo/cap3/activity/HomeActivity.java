package com.joaopaulo.cap3.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.joaopaulo.cap3.R;

import java.io.ObjectStreamException;
import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private TextView textView,qtdCoracoes,qtdDiamantes,qtdGold,rank;
    private String login;
    private Button btnJogar,imgDiamante;
    private final Firebase ref = new Firebase("https://perguntageis.firebaseio.com/");
    private ImageView avatar;
    private HashMap<String,String> usuario1,usuario2;
    private HashMap<String,Object> atualizarUsuarios = new HashMap<>();

    private int total = 0;

    private ProgressBar progressoUsuario;
    private TextView progressoUsu;

    private int currentCodeImage = 25;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Recebe parametro "login" da activity anterior
        Bundle args = getIntent().getExtras();
        login = args.getString("login");
        //Define toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        avatar = (ImageView) findViewById(R.id.imgCardPerfil);
        qtdCoracoes = (TextView) findViewById(R.id.tvCoracao);
        qtdDiamantes = (TextView) findViewById(R.id.tvDiamante);
        qtdGold = (TextView) findViewById(R.id.tvMoeda);
        rank = (TextView) findViewById(R.id.rankingHome);
        imgDiamante = (Button) findViewById(R.id.imgDiamante);






        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usuario1 = (HashMap) dataSnapshot.child("usuarios").child("u1").getValue();
                usuario2 = (HashMap) dataSnapshot.child("usuarios").child("u2").getValue();

                if(login.equals(usuario1.get("email"))){
                    qtdDiamantes.setText("x" + usuario1.get("diamantes"));
                    qtdCoracoes.setText("x" + usuario1.get("coracoes"));
                    qtdGold.setText("x" + usuario1.get("gold"));
                    rank.setText("RANKING: "+usuario1.get("rank"));
                    switch (Integer.parseInt(usuario1.get("imgcode"))){
                        case 1:
                            avatar.setImageResource(R.drawable.escudo);
                            break;
                        case 2:
                            avatar.setImageResource(R.drawable.ironman);
                            break;
                        case 3:
                            avatar.setImageResource(R.drawable.lanterna);
                            break;
                        case 4:
                            avatar.setImageResource(R.drawable.martelo);
                            break;
                        case 5:
                            avatar.setImageResource(R.drawable.marvel);
                            break;
                        case 6:
                            avatar.setImageResource(R.drawable.mira);
                            break;
                        default:
                            break;
                    }

                    //Pinguins
                    switch(Integer.parseInt(usuario1.get("rank"))){
                        case 25:
                            avatar.setImageResource(R.drawable.rankvintecinco);
                            break;
                        case 24:
                            avatar.setImageResource(R.drawable.rankvintequatro);
                            break;
                        case 23:
                            avatar.setImageResource(R.drawable.rankvintetres);
                            break;
                        case 22:
                            avatar.setImageResource(R.drawable.rankvintedois);
                            break;
                        case 21:
                            avatar.setImageResource(R.drawable.rankvinteum);
                            break;
                        default:
                            break;
                    }

                }else if(login.equals(usuario2.get("email"))){
                    qtdDiamantes.setText("x" + usuario2.get("diamantes"));
                    qtdCoracoes.setText("x" + usuario2.get("coracoes"));
                    qtdGold.setText("x"+usuario2.get("gold"));
                    rank.setText("RANKING: "+usuario2.get("rank"));
                    switch (Integer.parseInt(usuario2.get("imgcode"))){
                        case 1:
                            avatar.setImageResource(R.drawable.escudo);

                            break;
                        case 2:
                            avatar.setImageResource(R.drawable.ironman);

                            break;
                        case 3:
                            avatar.setImageResource(R.drawable.dangerous);
                            break;
                        case 4:
                            avatar.setImageResource(R.drawable.marvel);
                            break;
                        case 5:
                            avatar.setImageResource(R.drawable.martelo);
                            break;
                        case 6:
                            avatar.setImageResource(R.drawable.lanterna);
                            break;
                        default:
                            break;
                    }

                    switch(Integer.parseInt(usuario2.get("rank"))){
                        case 25:
                            avatar.setImageResource(R.drawable.rankvintecinco);
                            break;
                        case 24:
                            avatar.setImageResource(R.drawable.rankvintequatro);
                            break;
                        case 23:
                            avatar.setImageResource(R.drawable.rankvintetres);
                            break;
                        case 22:
                            avatar.setImageResource(R.drawable.rankvintedois);
                            break;
                        case 21:
                            avatar.setImageResource(R.drawable.rankvinteum);
                            break;
                        default:
                            break;
                    }

                }else{

                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_dehaze_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);


        //Implementação do navigation drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) drawerLayout.findViewById(R.id.navigation_view);
        View headerView = navigationView.inflateHeaderView(R.layout.navigation_drawer_header);
        TextView tvNome = (TextView) headerView.findViewById(R.id.tvNomeNavHeader);
        tvNome.setText(login);
        if (navigationView != null) {
            setupNavigationDrawerContent(navigationView);
        }
        setupNavigationDrawerContent(navigationView);


        //Welcome msg
       /* TextView msgBoasVindas = (TextView) findViewById(R.id.tvCardPerfil);
        msgBoasVindas.setText(login);*/

        //BUTTON ROLETA
        btnJogar = (Button) findViewById(R.id.btnJogar);
        btnJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Roleta.class);
                intent.putExtra("login", login);
                startActivity(intent);
            }
        });

        progressoUsuario = (ProgressBar) findViewById(R.id.lvlProgressBar);
        /*progressoUsu = (TextView) findViewById(R.id.progressoTexto);
        progressoUsu.setText("0%");*/

        progressoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = 10 + total;
                progressoUsuario.setProgress(total);
                //progressoUsu.setText(total + "%");
                if (total == 100) {
                    total = 0;
                    currentCodeImage--;
                    if(currentCodeImage == 25){
                        avatar.setImageResource(R.drawable.rankvintecinco);
                    }else if(currentCodeImage ==24){
                        avatar.setImageResource(R.drawable.rankvintequatro);
                    }else if(currentCodeImage == 23){
                        avatar.setImageResource(R.drawable.rankvintetres);
                    }else if(currentCodeImage == 22){
                        avatar.setImageResource(R.drawable.rankvintedois);
                    }else if(currentCodeImage ==21){
                        avatar.setImageResource(R.drawable.rankvinteum);
                    }

                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    Intent intent;
                    switch (menuItem.getItemId()) {
                        case R.id.item_conquistas:
                            menuItem.setChecked(true);
                            intent = new Intent(HomeActivity.this, ConquistasActivity.class);
                            startActivity(intent);
                            break;

                        case R.id.item_oficina_de_perguntas:
                            menuItem.setChecked(true);
                            intent = new Intent(HomeActivity.this, OficinaActivity.class);
                            startActivity(intent);

                            break;

                        case R.id.item_caixa_de_entrada:
                            //menuItem.setChecked(true);
                            Toast.makeText(getApplicationContext(), "Funcionalidade não diponível", Toast.LENGTH_LONG).show();

                            break;
                        case R.id.item_ajuda:
                            //menuItem.setChecked(true);
                            Toast.makeText(getApplicationContext(), "Funcionalidade não diponível", Toast.LENGTH_LONG).show();

                            break;
                        case R.id.item_configuracoes:
                            //Toast.makeText(HomeActivity.this, "Launching " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(), "Funcionalidade não diponível", Toast.LENGTH_LONG).show();
                            break;
                        case R.id.item_logout:
                            ref.unauth();
                            break;
                    }
                    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                    drawer.closeDrawer(GravityCompat.START);
                    return true;
                }
            }
        );
    }

    public void comprarDiamante(View view){
        if(usuario1.get("email").equals(login)){
            if(Integer.parseInt(usuario1.get("gold"))>=30){
                atualizarUsuarios.put("usuarios/u1/gold",""+(Integer.parseInt(usuario1.get("gold"))-30));
                atualizarUsuarios.put("usuarios/u1/diamantes",""+(Integer.parseInt(usuario1.get("diamantes"))+1));
                ref.updateChildren(atualizarUsuarios);
            }else{

            }

        }else if(usuario2.get("email").equals(login)){
            if(Integer.parseInt(usuario2.get("gold"))>=30){
                atualizarUsuarios.put("usuarios/u2/gold",""+(Integer.parseInt(usuario2.get("gold"))-30));
                atualizarUsuarios.put("usuarios/u2/diamantes",""+(Integer.parseInt(usuario2.get("diamantes"))+1));
                ref.updateChildren(atualizarUsuarios);
            }else{

            }
        }else{

        }
    }
}

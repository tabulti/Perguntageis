package com.joaopaulo.cap3.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.joaopaulo.cap3.R;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private TextView textView,qtdCoracoes,qtdDiamantes,qtdGold;
    private String login;
    private Button btnJogar;
    private final Firebase ref = new Firebase("https://perguntageis.firebaseio.com/");
    private ImageView avatar;
    private HashMap<String,String> usuario1,usuario2;


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


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usuario1 = (HashMap) dataSnapshot.child("usuarios").child("u1").getValue();
                usuario2 = (HashMap) dataSnapshot.child("usuarios").child("u2").getValue();

                if(login.equals(usuario1.get("email"))){
                    qtdDiamantes.setText("x" + usuario1.get("diamantes"));
                    qtdCoracoes.setText("x" + usuario1.get("coracoes"));
                    qtdGold.setText("x" + usuario1.get("gold"));
                    switch (Integer.parseInt(usuario1.get("imgcode"))){
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

                }else if(login.equals(usuario2.get("email"))){
                    qtdDiamantes.setText("x" + usuario2.get("diamantes"));
                    qtdCoracoes.setText("x" + usuario2.get("coracoes"));
                    qtdGold.setText("x"+usuario2.get("gold"));
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
        TextView msgBoasVindas = (TextView) findViewById(R.id.tvCardPerfil);
        msgBoasVindas.setText(login);

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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                Toast.makeText(this, "TESTE", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
            new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.item_conquistas:
                            menuItem.setChecked(true);
                            textView.setText(menuItem.getTitle());
                            drawerLayout.closeDrawer(GravityCompat.START);
                            return true;
                        case R.id.item_oficina_de_perguntas:
                            menuItem.setChecked(true);
                            textView.setText(menuItem.getTitle());
                            drawerLayout.closeDrawer(GravityCompat.START);
                            return true;
                        case R.id.item_caixa_de_entrada:
                            menuItem.setChecked(true);
                            textView.setText(menuItem.getTitle());
                            drawerLayout.closeDrawer(GravityCompat.START);
                            return true;
                        case R.id.item_ajuda:
                            menuItem.setChecked(true);
                            textView.setText(menuItem.getTitle());
                            drawerLayout.closeDrawer(GravityCompat.START);
                            return true;
                        case R.id.item_configuracoes:
                            menuItem.setChecked(true);
                            textView.setText(menuItem.getTitle());
                            Toast.makeText(HomeActivity.this, "Launching " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                            drawerLayout.closeDrawer(GravityCompat.START);
                            return true;
                        case R.id.item_logout:

                            menuItem.setChecked(true);
                            textView.setText(menuItem.getTitle());
                            drawerLayout.closeDrawer(GravityCompat.START);
                            ref.unauth();
                            return true;
                    }
                    return true;
                }
            }
        );
    }
}

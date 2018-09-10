package com.cooperativa.ideias.ascender.ecoponto;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.cooperativa.ideias.ascender.ecoponto.Utils.ConstantsUtils;
import com.cooperativa.ideias.ascender.ecoponto.Utils.FragmentUtils;
import com.cooperativa.ideias.ascender.ecoponto.Utils.GetDataFrom;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import static android.app.PendingIntent.getActivity;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //Variaveis...

    Toolbar toolbar = null;


    //WebCustomTabs
    CustomTabsIntent customTabsIntent;
    CustomTabsIntent.Builder intentBuilder;

    //Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAuth = FirebaseAuth.getInstance();

        customTabs();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Metodo para AdMob anuncios...
        // mInterstitialAd = new InterstitialAd(this);
        // mInterstitialAd.setAdUnitId("ca-app-pub-4036318734376935/2022261408");
        //Chamando fragmento inicial...
        navigationView.setCheckedItem(R.id.nav_coleta);
        FragmentUtils.replace(this, new MapsColetasFragment());

    }
    // Metodp onBackPressed Menu DrawerNavigation...
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        new GetDataFrom().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        displayView(item.getItemId());
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
  }


    private void displayView(int itemId) {
        switch (itemId) {
            case R.id.nav_coleta:
                FragmentUtils.replace(this, new MapsColetasFragment());
                break;
            case R.id.nav_oleo:
                FragmentUtils.replace(this, new MapsActivityNsr());
                break;
                case R.id.nav_horario:
                FragmentUtils.replace(this, new ColetasFragment());
                break;
                case R.id.nav_protejapontos:
                    startActivity(new Intent(this, ProtejaOsEcoPontos.class));
                    break;case R.id.nav_sobre:
                        FragmentUtils.replace(this, new SobreFragment());
                break;
                case R.id.nav_catadores:
//                startActivity(new Intent(this, ASCItaperuna.class));
                   customTabsIntent.launchUrl(this, Uri.parse(ConstantsUtils.URL_CATADORES));
                break;

            case R.id.nav_ascender:

//                startActivity(new Intent(this, AscenderIdeias.class));
                customTabsIntent.launchUrl(this, Uri.parse(ConstantsUtils.URL_AI));
                break;

            case R.id.nav_sair:
                //dialog
                dialogSair();

                break;
            case R.id.nav_compartilar:
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                share.putExtra(Intent.EXTRA_SUBJECT, "Compartilhe nosso aplicativo...");
                share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.cooperativa.ideias.ascender.ecoponto");

                startActivity(Intent.createChooser(share, "Compartilhar link!"));
                //startActivity ( intent );

                break;

        }
    }

    //instanciando custom tabs para carregamento de paginas web
    private void customTabs(){
        intentBuilder = new CustomTabsIntent.Builder();
        intentBuilder.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        intentBuilder.setExitAnimations(this, R.anim.right_to_left_end, R.anim.left_to_right_end);
        intentBuilder.setStartAnimations(this, R.anim.left_to_right_start, R.anim.right_to_left_start);
        intentBuilder.setSecondaryToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        customTabsIntent = intentBuilder.build();
    }



    //AlertDialog de confimação de signOut
    private void dialogSair() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Desconectar?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //desconectando e passando null como parametro para redirecionamento a activityLogin
                FirebaseAuth.getInstance().signOut();
                updateUI(null);

            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onStart() {
        super.onStart();
        //verificando se há algum usuario logado
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }


    //caso usuario == null redireciona para a activity de login
    private void updateUI(FirebaseUser currentUser){
        if(currentUser!= null){

        }else {
            startActivity(new Intent( this, LoginActivity.class));
            finish();
        }
    }


}















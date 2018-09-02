package com.cooperativa.ideias.ascender.ecoponto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.cooperativa.ideias.ascender.ecoponto.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import static android.app.PendingIntent.getActivity;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    //Variaveis...
    private FragmentManager fragmentManager;
    Toolbar toolbar = null;
    private Button botaocoleta;
    private Button nav_Button2;


    //Google...
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private GoogleApiClient googleApiClient;

    // Variavel a nivel de classe para Firebase Atuh eimportacoes necessarias...
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

//Adicionado aqui, analisar...
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);


        toolbar = (Toolbar) findViewById ( R.id.toolbar );
        setSupportActionBar ( toolbar );

        DrawerLayout drawer = (DrawerLayout) findViewById ( R.id.drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle (
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener ( toggle );
        toggle.syncState ( );

        NavigationView navigationView = (NavigationView) findViewById ( R.id.nav_view );
        navigationView.setNavigationItemSelectedListener ( this );


        //Metodo para AdMob anuncios...
        // mInterstitialAd = new InterstitialAd(this);
        // mInterstitialAd.setAdUnitId("ca-app-pub-4036318734376935/2022261408");
        //Chamando fragmento inicial...
        navigationView.setCheckedItem ( R.id.nav_coleta );
        fragmentManager = getSupportFragmentManager ( );
        FragmentTransaction transaction = fragmentManager.beginTransaction ( );
        transaction.add ( R.id.ecoinicio, new MapsActivityClt (), "Inicio" );
        transaction.commitAllowingStateLoss ( );

        //Referências GoogleSignIOptionos Google...
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder ( GoogleSignInOptions.DEFAULT_SIGN_IN )
                .requestEmail ( )
                .build ( );

        googleApiClient = new GoogleApiClient.Builder ( this )
                .enableAutoManage ( this, this )
                .addApi ( Auth.GOOGLE_SIGN_IN_API, gso )
                .build ( );


        //Método para login com Google Signi...
        firebaseAuth = FirebaseAuth.getInstance ( );
        firebaseAuthListener = new FirebaseAuth.AuthStateListener ( ) {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser ( );
                if (user != null) {
                    setUserData ( user );
                } else {
                    goLogInScreen ( );
                }
            }
        };
    }
    //FIM Método para login com Google Signi...


    // Metodp onBackPressed Menu DrawerNavigation...
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById ( R.id.drawer_layout );
        if (drawer.isDrawerOpen ( GravityCompat.START )) {
            drawer.closeDrawer ( GravityCompat.START );
        } else {
            super.onBackPressed ( );
        }
    }


    public boolean onNavigationItemSelected(@NonNull MenuItem item) {//aLTERADO AQUI 3.1.1
        int id = item.getItemId ( );

        if (id == R.id.nav_coleta) {


            FragmentTransaction transaction = fragmentManager.beginTransaction ( );
            transaction.replace ( R.id.ecoinicio, new MapsActivityClt ( ), "Mapa 1" );
            transaction.addToBackStack ( null );
            transaction.commit ( );

            // Chamando Fragmento Mapa Nosso Oleo Nosso Rio....

        } else if (id == R.id.nav_oleo) {


            FragmentTransaction transaction = fragmentManager.beginTransaction ( );
            transaction.replace ( R.id.ecoinicio, new MapsActivityNsr ( ), "Mapa 2" );
            transaction.addToBackStack ( null );
            transaction.commit ( );

            //Chamando Activity Horarios de Coleta...

        } else if (id == R.id.nav_horario) {

            Intent intent = new Intent ( this, Main2Activity.class );
            startActivity ( intent );

            // Chamando Activity Proteja os Eco Pontos...

        } else if (id == R.id.nav_protejapontos) {

            Intent intent = new Intent ( this, ProtejaOsEcoPontos.class );
            startActivity ( intent );

            //Transiction Sobre...
        } else if (id == R.id.nav_sobre) {


            Intent intent = new Intent ( this, Sobre.class );
            startActivity ( intent );

            //Chamando Activity Mapa Associacao de Catadores de Itaperuna...

        } else if (id == R.id.nav_catadores) {

            Intent intent = new Intent ( this, ASCItaperuna.class );
            startActivity ( intent );


            //Chamando Activity Mapa Ascender Ideias...
        } else if (id == R.id.nav_ascender) {

            Intent intent = new Intent ( this, AscenderIdeias.class );
            startActivity ( intent );

            // Intente para accao botão de lougot...
        } else if (id == R.id.nav_sair) {
            FirebaseAuth.getInstance ( ).signOut ( );
            //startActivity ( intent );

        } else if (id == R.id.nav_compartilar) {
            Intent share = new Intent ( Intent.ACTION_SEND );
            share.setType ( "text/plain" );
            share.addFlags ( Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET );

            share.putExtra ( Intent.EXTRA_SUBJECT, "Compartilhe nosso aplicativo..." );
            share.putExtra ( Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.cooperativa.ideias.ascender.ecoponto" );

            startActivity ( Intent.createChooser ( share, "Compartilhar link!" ) );
            //startActivity ( intent );

        }

        DrawerLayout drawer = (DrawerLayout) findViewById ( R.id.drawer_layout );
        drawer.closeDrawer ( GravityCompat.START );
        return true;


    }


    // Métodos para refrências e vinculos com Login do Goole...
    @Override
    protected void onStart() {
        super.onStart ( );

        firebaseAuth.addAuthStateListener ( firebaseAuthListener );

    }

    private void goLogInScreen() {
        Intent intent = new Intent ( this, MainLoginActivity.class );
        intent.addFlags ( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK );
        startActivity ( intent );
    }

    public void logOut(View view) {
        firebaseAuth.signOut ( );

        Auth.GoogleSignInApi.signOut ( googleApiClient ).setResultCallback ( new ResultCallback <Status> ( ) {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess ( )) {
                    goLogInScreen ( );
                } else {
                    Toast.makeText ( getApplicationContext ( ), R.string.not_close_session, Toast.LENGTH_SHORT ).show ( );
                }
            }
        } );
    }

    public void revoke(View view) {
        firebaseAuth.signOut ( );

        Auth.GoogleSignInApi.revokeAccess ( googleApiClient ).setResultCallback ( new ResultCallback <Status> ( ) {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess ( )) {
                    goLogInScreen ( );
                } else {
                    Toast.makeText ( getApplicationContext ( ), R.string.not_revoke, Toast.LENGTH_SHORT ).show ( );
                }
            }
        } );
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStop() {
        super.onStop ( );

        if (firebaseAuthListener != null) {
            firebaseAuth.removeAuthStateListener ( firebaseAuthListener );
        }
    }
//Adicionado aqui, analisar...
    public void setUserData(FirebaseUser userData) {
    }
}















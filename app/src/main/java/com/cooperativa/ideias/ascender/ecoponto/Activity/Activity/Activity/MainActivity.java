package com.cooperativa.ideias.ascender.ecoponto.Activity.Activity.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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


import com.cooperativa.ideias.ascender.ecoponto.Activity.Activity.Fragment.HorariosDeColeta;
import com.cooperativa.ideias.ascender.ecoponto.R;

import static android.app.PendingIntent.getActivity;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {


    private FragmentManager fragmentManager;
    Toolbar toolbar = null;
    private Button botaocoleta;
    private Button nav_Button2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Chamando fragmento inicial...
        navigationView.setCheckedItem(R.id.nav_coleta);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.ecoinicio, new MapsActivityNsr(), "Inicio");
        transaction.commitAllowingStateLoss();


    }


    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_coleta) {

            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.ecoinicio, new MapsActivityClt(), "Mapa 1");
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.nav_oleo) {


            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.ecoinicio, new MapsActivityNsr(), "Mapa 2");
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.nav_horario) {


            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.ecoinicio, new HorariosDeColeta(), "Horario de Coleta");
            transaction.addToBackStack(null);
            transaction.commit();

            //Transiction Sobre...
        } else if (id == R.id.nav_sobre) {


            Intent intent = new Intent(this, Sobre.class);
            startActivity(intent);

        } else if (id == R.id.nav_catadores) {

            Intent intent = new Intent(this, ASCItaperuna.class);
            startActivity(intent);



        } else if (id == R.id.nav_ascender) {

            Intent intent = new Intent(this, AscenderIdeias.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void start_face_um(View view) {
        Uri uri = Uri.parse("https://www.facebook.com/ascenderideias/"); //missing 'http://' vil cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


    public void start_instagran_um(View view) {
        Uri uri = Uri.parse("https://www.instagram.com/ascenderideias/"); //missing 'http://' vil cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }


    public void start_face_dois(View view) {
        Uri uri = Uri.parse("https://www.facebook.com/Associação-De-Catadores-De-Itaperuna-514430032060871/"); //missing 'http://' vil cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);




    }
}
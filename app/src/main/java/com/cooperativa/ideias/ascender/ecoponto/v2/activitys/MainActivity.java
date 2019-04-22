package com.cooperativa.ideias.ascender.ecoponto.v2.activitys;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.cooperativa.ideias.ascender.ecoponto.R;
import com.cooperativa.ideias.ascender.ecoponto.Utils.FragmentUtils;
import com.cooperativa.ideias.ascender.ecoponto.v2.fragments.DetalhesFragment;
import com.cooperativa.ideias.ascender.ecoponto.v2.fragments.LocalidadesFragment;
import com.cooperativa.ideias.ascender.ecoponto.v2.fragments.MapaFragment;
import com.cooperativa.ideias.ascender.ecoponto.v2.fragments.SobreFragment;
import com.cooperativa.ideias.ascender.ecoponto.v2.models.Cidade;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Spinner spinner;
    private Cidade cidade;
    private BottomNavigationView navigation;
    private ArrayList<Cidade> cidades;

    private boolean Map, Loc, Sob;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        Fragment fragment = null;
        Map = false;
        Loc = false;
        Sob = false;
        switch (item.getItemId()) {
            case R.id.navigation_mapa:

                Map = true;
                toolbar.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);
                fragment = MapaFragment.newInstance(cidade);

                break;

            case R.id.navigation_localidades:
                Loc= true;
                toolbar.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.GONE);
                fragment = new LocalidadesFragment();
                break;
            case R.id.navigation_sobre:
                Sob = true;
                toolbar.setVisibility(View.GONE);
                fragment = new SobreFragment();
                break;

            default:
                break;
        }

        FragmentUtils.replace(MainActivity.this, fragment);

        return true;
    };





    protected void onCreate(Bundle savedInsanceState) {
        super.onCreate(savedInsanceState);
        setContentView(R.layout.v2_main_activity);
        initView();

        setCidades();
        initSpinnerCidade();


    }

    private void initView() {

        toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        spinner = findViewById(R.id.spinnerCidades);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private void initSpinnerCidade() {
        ArrayAdapter<Cidade> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.v2_adapter_spinner, cidades);
        spinner.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cidade = cidades.get(position);
                FragmentUtils.replace(MainActivity.this, MapaFragment.newInstance(cidade));
//                    EventBus.getDefault().post(new EventObject(cidade));
                Log.v("CIDADE", cidade.toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_default, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
//        Integer id = menu.get
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_sobre_os_pontos:
                modalInformacoes();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void modalInformacoes() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.v2_dialog_informacoes, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        builder.setCancelable(true);


        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


    private void setCidades() {
        cidades = new ArrayList<>();
        Cidade cidade = new Cidade(0, "Itaperuna", "-21.2002", "-41.8803");
        cidades.add(cidade);

    }



    @Override
    public void onBackPressed() {

        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.container);

        if (currentFragment instanceof DetalhesFragment) {
            ((DetalhesFragment) currentFragment).onBackPressed();


        } else {
            if(Map || Loc ||Sob){
                finish();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

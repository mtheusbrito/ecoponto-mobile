package com.cooperativa.ideias.ascender.ecoponto.v2.activitys;

import android.content.DialogInterface;
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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.cooperativa.ideias.ascender.ecoponto.R;
import com.cooperativa.ideias.ascender.ecoponto.Utils.FragmentUtils;
import com.cooperativa.ideias.ascender.ecoponto.v2.fragments.DetalhesFragment;
import com.cooperativa.ideias.ascender.ecoponto.v2.fragments.LocalidadesFragment;
import com.cooperativa.ideias.ascender.ecoponto.v2.fragments.MapaFragment;
import com.cooperativa.ideias.ascender.ecoponto.v2.fragments.SobreFragment;
import com.cooperativa.ideias.ascender.ecoponto.v2.models.Cidade;
import com.cooperativa.ideias.ascender.ecoponto.v2.models.Estado;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Spinner spinnerCidade, spinnerEstado;
    private Cidade cidade;
    private Estado estado;
    private BottomNavigationView navigation;
    private ArrayList<Cidade> cidades;
    private ArrayList<Estado> estados;
    private TextView textViewEstado, textViewCidade;
    private LinearLayout linearSpinners;

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
                linearSpinners.setVisibility(View.VISIBLE);
                fragment = MapaFragment.newInstance(cidade);

                break;

            case R.id.navigation_localidades:
                Loc= true;
                toolbar.setVisibility(View.VISIBLE);

                linearSpinners.setVisibility(View.GONE);
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
        initSpinnerEstado();



    }



    private void initView() {

        toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        linearSpinners = findViewById(R.id.linearSpinners);
        spinnerCidade = findViewById(R.id.spinnerCidades);
        spinnerEstado = findViewById(R.id.spinnerEstados);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        textViewEstado = findViewById(R.id.textEstado);
        textViewCidade = findViewById(R.id.textCidade);

    }

    private void initSpinnerEstado() {
        ArrayAdapter<Estado> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.v2_adapter_spinner, estados);
        spinnerEstado.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                estado = estados.get(position);
                List<Cidade> cidadesEstado = new ArrayList<>();
                for (Cidade cidade : cidades){

                  if(cidade.getEstado().equals(estado)){
                      cidadesEstado.add(cidade);
                  }


                }
                initSpinnerCidade(cidadesEstado);

//                cidade = cidades.get(position);
//                FragmentUtils.replace(MainActivity.this, MapaFragment.newInstance(cidade));
////                    EventBus.getDefault().post(new EventObject(cidade));
//                Log.v("CIDADE", cidade.toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void initSpinnerCidade(List<Cidade> cidadesEstado) {
        ArrayAdapter<Cidade> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.v2_adapter_spinner, cidadesEstado);
        spinnerCidade.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCidade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cidade = cidadesEstado.get(position);
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

        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.setCancelable(true);
        alertDialog.setTitle("Como o app funciona?");
        alertDialog.setMessage(getResources().getString(R.string.text_informações));

        alertDialog.show();

    }



    private void setCidades() {
        estados = new ArrayList<>();
        Estado  estado1 = new Estado(0,"RJ", "Rio de Janeiro");
        Estado estado2 = new Estado(1, "MG","Minas Gerais");
        estados.add(estado1);
        estados.add(estado2);


        cidades = new ArrayList<>();
        Cidade cidade1 = new Cidade(0, "Itaperuna", "-21.2002", "-41.8803", estado1);
        Cidade cidade2 = new Cidade(1, "Ouro Branco", "-20.5236", "-43.6914",estado2);
        cidades.add(cidade1);
        cidades.add(cidade2);

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

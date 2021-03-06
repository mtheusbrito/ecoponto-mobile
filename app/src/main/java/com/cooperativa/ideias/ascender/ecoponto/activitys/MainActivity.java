package com.cooperativa.ideias.ascender.ecoponto.activitys;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cooperativa.ideias.ascender.ecoponto.R;
import com.cooperativa.ideias.ascender.ecoponto.utils.FragmentUtils;
import com.cooperativa.ideias.ascender.ecoponto.fragments.DetalhesFragment;
import com.cooperativa.ideias.ascender.ecoponto.fragments.LocalidadesFragment;
import com.cooperativa.ideias.ascender.ecoponto.fragments.MapaFragment;
import com.cooperativa.ideias.ascender.ecoponto.fragments.SobreFragment;
import com.cooperativa.ideias.ascender.ecoponto.models.Cidade;
import com.cooperativa.ideias.ascender.ecoponto.models.Estado;

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
    private LinearLayout linearSpinners;

    public static int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION =1;


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
                Loc = true;
                toolbar.setVisibility(View.VISIBLE);
                linearSpinners.setVisibility(View.VISIBLE);
                fragment = LocalidadesFragment.newInstance(cidade);
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

   //Metodos RequestPermission para termos de permissão em RunTime...
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {

                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

            }
        } else {

        }



    }


    private void initView() {

        toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        linearSpinners = findViewById(R.id.linearSpinners);
        spinnerCidade = findViewById(R.id.spinnerCidades);
        spinnerEstado = findViewById(R.id.spinnerEstados);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



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
                //Passando a referência para exibição do Spinner nas classes...
//
                if(Loc)
                {
                    FragmentUtils.replace(MainActivity.this, LocalidadesFragment.newInstance(cidade));

                }else {
                    FragmentUtils.replace(MainActivity.this, MapaFragment.newInstance(cidade));

                }
//

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


        //Metodos Seters Estados para exibir opções do Spinner....
    private void setCidades() {
        estados = new ArrayList<>();
        Estado  estado1 = new Estado(0,"RJ", "Rio de Janeiro");
        Estado estado2 = new Estado(1, "MG","Minas Gerais");
        estados.add(estado1);
        estados.add(estado2);

        //Metodos Seters Cidades para exibir opções do Spinner....

        cidades = new ArrayList<>();
        Cidade cidade1 = new Cidade(0, "Itaperuna", "-21.2002", "-41.8803", estado1);
        Cidade cidade2 = new Cidade(1, "Ouro Branco", "-20.5236", "-43.6914",estado2);
        cidades.add(cidade1);
        cidades.add(cidade2);

    }



    @Override
    public void onBackPressed() {

        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if(!Sob){
            toolbar.setVisibility(View.VISIBLE);
        }

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

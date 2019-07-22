package com.cooperativa.ideias.ascender.ecoponto.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.cooperativa.ideias.ascender.ecoponto.R;
import com.cooperativa.ideias.ascender.ecoponto.fragments.LocalidadesFragment;
import com.cooperativa.ideias.ascender.ecoponto.fragments.MapaFragment;
import com.cooperativa.ideias.ascender.ecoponto.models.Cidade;
import com.cooperativa.ideias.ascender.ecoponto.models.Estado;
import com.cooperativa.ideias.ascender.ecoponto.utils.FragmentUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import static com.cooperativa.ideias.ascender.ecoponto.dao.ConfiguracoesFirebase.getPontos;

public class MainAxuiliar extends AppCompatActivity {
    //Variaveis MainActivity...
    private Toolbar toolbar;
    private Spinner spinnerCidade, spinnerEstado;
    private Cidade cidade;
    private Estado estado;
    private BottomNavigationView navigation;
    private ArrayList<Cidade> cidades;
    private ArrayList<Estado> estados;
    private TextView textViewEstado, textViewCidade;
    private LinearLayout linearSpinners;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_axuiliar);
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
        textViewEstado = findViewById(R.id.textEstado);
        textViewCidade = findViewById(R.id.textCidade);


    }


    private void initSpinnerEstado() {
        ArrayAdapter<Estado> adapter = new ArrayAdapter<>(MainAxuiliar.this, R.layout.v2_adapter_spinner, estados);
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
        ArrayAdapter<Cidade> adapter = new ArrayAdapter<>(MainAxuiliar.this, R.layout.v2_adapter_spinner, cidadesEstado);
        spinnerCidade.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCidade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cidade = cidadesEstado.get(position);
                //Passando a referência para exibição do Spinner nas classes...
                FragmentUtils.replace(MainAxuiliar.this, LocalidadesFragment.newInstance(cidade));

//                    EventBus.getDefault().post(new EventObject(cidade));
                Log.v("CIDADE", cidade.toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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




}

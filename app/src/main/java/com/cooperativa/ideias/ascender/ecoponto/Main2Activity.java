package com.cooperativa.ideias.ascender.ecoponto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.cooperativa.ideias.ascender.ecoponto.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    // Uma lista para armazenar todos os produtos de listpcdlist...
    List <Listapcd> listapcdList;
    private AdView mAdView;

    //o recyclerview
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main2 );
        // Metodo adicionado
        getSupportActionBar ( ).setDisplayHomeAsUpEnabled ( true ); //Mostrar o botão
        getSupportActionBar ( ).setHomeButtonEnabled ( true );      //Ativar o botão
        getSupportActionBar ( ).setTitle ( "EcoPonto" );     //Titulo para ser exibido na sua Action Bar em frente à seta

        //Anuncios googleAdMobi...
        mAdView = (AdView) findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        //obtendo o recycleview do layout xml recycleview.xml...
        recyclerView = (RecyclerView) findViewById ( R.id.recyclerView );
        recyclerView.setHasFixedSize ( true );
        recyclerView.setLayoutManager ( new LinearLayoutManager ( this ) );

        //inicializando a listpcdlist...
        listapcdList = new ArrayList <> ( );


        //lista para adicionar os itens a lista de exibicao...
        listapcdList.add (
                new Listapcd (
                        1,
                        "Segunda-Feira a Domingo",
                        "8hrs às 19hrs",
                        1,
                        "Itaperuna-Bairro Centro",
                        R.drawable.caminhaoecoponto ) );


        listapcdList.add (
                new Listapcd (
                        1,
                        "Segunda-Feira a Domingo",
                        "8hrs às 19hrs",
                        2,
                        "Itaperuna-Bairro Aeroporto",

                        R.drawable.caminhaoecoponto ) );

        listapcdList.add (
                new Listapcd (
                        1,
                        "Segunda-Feira a Domingo",
                        "8hrs às 19hrs",
                        3,
                        "Itaperuna-Bairro Caiçara",

                        R.drawable.caminhaoecoponto ) );


        listapcdList.add (
                new Listapcd (
                        1,
                        "Segunda-Feira a Domingo",
                        "8hrs às 19hrs",
                        4,
                        "Itaperuna-Bairro Lions",

                        R.drawable.caminhaoecoponto ) );

        listapcdList.add (
                new Listapcd (
                        1,
                        "Segunda-Feira a Domingo",
                        "8hrs às 19hrs",
                        5,
                        "Itaperuna- Bairro São Matheus",

                        R.drawable.caminhaoecoponto ) );

        listapcdList.add (
                new Listapcd (
                        1,
                        "Segunda-Feira a Domingo",
                        "7hrs às 17hrs",
                        6,
                        "Itaperuna-Ponto de Vista",

                        R.drawable.caminhaoecoponto ) );


        listapcdList.add (
                new Listapcd (
                        1,
                        "Sábados",
                        "7hrs às 11hrs",
                        7,
                        "Itaperuna-Cidade Nova",

                        R.drawable.caminhaoecoponto ) );


        listapcdList.add (
                new Listapcd (
                        1,
                        "Segunda-Feira a Sexta-Feira",
                        "8hrs às 16hrs",
                        8,
                        "Itaperuna-Bairro Cehab",

                        R.drawable.caminhaoecoponto ) );


        listapcdList.add (
                new Listapcd (
                        1,
                        "Segunda-Feira a Quinta-Feira",
                        "7hrs às 14hrs",
                        9,
                        "Raposo-Hotel Águas Claras",

                        R.drawable.caminhaoecoponto ) );


        listapcdList.add (
                new Listapcd (
                        1,
                        "Segunda-Feira a Sexta-Feira",
                        "8hrs às 21hrs",
                        10,
                        "Itaperuna-Cidade Nova",

                        R.drawable.caminhaoecoponto ) );


        listapcdList.add (
                new Listapcd (
                        1,
                        "Segunda-Feira a Sexta-Feira",
                        "8hrs às 16hrs",
                        11,
                        "Itaperuna-Bairro Surubi",

                        R.drawable.caminhaoecoponto ) );


        listapcdList.add (
                new Listapcd (
                        1,
                        "Segunda-Feira a Domingo",
                        "8hrs às 19hrs",
                        12,
                        "Itaperuna-Centro/Vinhosa",

                        R.drawable.caminhaoecoponto ) );


        listapcdList.add (
                new Listapcd (
                        1,
                        "Segunda-Feira a Sexta-Feira",
                        "8hrs às 16hrs",
                        13,
                        "Itaperuna-Bairro Avahy",

                        R.drawable.caminhaoecoponto ) );


        listapcdList.add (
                new Listapcd (
                        1,
                        "Segunda-Feira a Domingo",
                        "8hrs às 19hrs",
                        14,
                        "Itaperuna-Bairro Cehab",

                        R.drawable.caminhaoecoponto ) );


        listapcdList.add (
                new Listapcd (
                        1,
                        "Segunda-Feira a Domingo",
                        "8hrs às 19hrs",
                        15,
                        "Itaperuna-Cidade Nova",

                        R.drawable.caminhaoecoponto ) );


        listapcdList.add (
                new Listapcd (
                        1,
                        "Segunda-Feira a Sexta-Feira",
                        "8hrs às 16hrs",
                        16,
                        "Itaperuna-CRAS-São José",

                        R.drawable.caminhaoecoponto ) );


        listapcdList.add (
                new Listapcd (
                        1,
                        "Segunda-Feira a Sexta-Feira",
                        "8hrs às 16hrs",
                        17,
                        "Itaperuna-Bairro Vinhosa",

                        R.drawable.caminhaoecoponto ) );


        listapcdList.add (
                new Listapcd (
                        1,
                        "Segunda-Feira a Sexta-Feira",
                        "8hrs às 16hrs",
                        18,
                        "Itaperuna-Bairro Aeroporto",

                        R.drawable.caminhaoecoponto ) );


        //criando  recyclerview adapter ListaHorarioAdpter/ Variavel mCtx- ListapcdList...
        ListaHorarioAdapter adapter = new ListaHorarioAdapter (this, listapcdList );

        //configurando o  adapter do recyclerview...
        recyclerView.setAdapter ( adapter );
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId ( )) {
            case android.R.id.home:  //ID do  botão (gerado automaticamente pelo android...
                startActivity ( new Intent ( this, MainActivity.class ) );  //O efeito ao ser pressionado do botão (no caso abre a activity)
                finishAffinity ( );  //Método para matar a activity e não deixa-lá indexada na pilhagem
                break;
            default:
                break;
        }
        return true;
    }

}


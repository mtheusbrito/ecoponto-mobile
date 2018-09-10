package com.cooperativa.ideias.ascender.ecoponto;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.cooperativa.ideias.ascender.ecoponto.adapters.ColetasAdapter;
import com.cooperativa.ideias.ascender.ecoponto.models.Coleta;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColetasFragment extends Fragment {

    private ColetasAdapter adapter ;
    private RecyclerView recyclerView;
    private List<Coleta> coletas;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coletas, container, false);
        initView(view);
        instanciandoColetas();
        return view;
    }

    //lista de coletas
    private void instanciandoColetas() {

        Coleta c1 = new Coleta();
        c1.setDia("Segunda-Feira a Domingo");
        c1.setHorario("8hrs às 19hrs");
        c1.setQt(1);
        c1.setLocal("Itaperuna-Bairro Centro");

        Coleta c2 = new Coleta();
        c2.setDia("Segunda-Feira a Domingo");
        c2.setHorario("8hrs às 19hrs");
        c2.setQt(2);
        c2.setLocal("Itaperuna-Bairro Aeroporto");

        Coleta c3 = new Coleta();
        c3.setDia("Segunda-Feira a Domingo");
        c3.setHorario("8hrs às 19hrs");
        c3.setQt(3);
        c3.setLocal("Itaperuna-Bairro Caiçara");

        Coleta c4 = new Coleta();
        c4.setDia("Segunda-Feira a Domingo");
        c4.setHorario("8hrs às 19hrs");
        c4.setQt(4);
        c4.setLocal("Itaperuna-Bairro Lions");

        Coleta c5 = new Coleta();
        c5.setDia("Segunda-Feira a Domingo");
        c5.setHorario("8hrs às 19hrs");
        c5.setQt(5);
        c5.setLocal("Itaperuna- Bairro São Matheus");

        Coleta c6 = new Coleta();
        c6.setDia("Segunda-Feira a Domingo");
        c6.setHorario("7hrs às 17hrs");
        c6.setQt(6);
        c6.setLocal("Itaperuna-Ponto de Vista");

        Coleta c7 = new Coleta();
        c7.setDia("Sábados");
        c7.setHorario("7hrs às 11hrs");
        c7.setQt(7);
        c7.setLocal("Itaperuna-Cidade Nova");


        Coleta c8 = new Coleta();
        c8.setDia("Segunda-Feira a Sexta-Feira");
        c8.setHorario("8hrs às 16hrs");
        c8.setQt(8);
        c8.setLocal("Itaperuna-Bairro Cehab");

        Coleta c9 = new Coleta();
        c9.setDia("Segunda-Feira a Quinta-Feira");
        c9.setHorario("7hrs às 14hrs");
        c9.setQt(9);
        c9.setLocal("Raposo-Hotel Águas Claras");

        Coleta c10 = new Coleta();
        c10.setDia("Segunda-Feira a Sexta-Feira");
        c10.setHorario("8hrs às 21hrs");
        c10.setQt(10);
        c10.setLocal("Iteperuna-Cidade Nova");

         Coleta c11 = new Coleta();
        c11.setDia("Segunda-Feira a Sexta-Feira");
        c11.setHorario("8hrs às 16hrs");
        c11.setQt(11);
        c11.setLocal("Iteperuna-Bairro Surubi");

        Coleta c12 = new Coleta();
        c12.setDia("Segunda-Feira a Domingo");
        c12.setHorario("8hrs às 19hrs");
        c12.setQt(12);
        c12.setLocal("Iteperuna-Centro/Vinhosa");

        Coleta c13 = new Coleta();
        c13.setDia("Segunda-Feira a Sexta-Feira");
        c13.setHorario("8hrs às 16hrs");
        c13.setQt(13);
        c13.setLocal("Iteperuna-Bairro Avahy");

        Coleta c14 = new Coleta();
        c14.setDia("Segunda-Feira a Domingo");
        c14.setHorario("8hrs às 19hrs");
        c14.setQt(14);
        c14.setLocal("Iteperuna-Bairro Cehab");

        Coleta c15 = new Coleta();
        c15.setDia("Segunda-Feira a Domingo");
        c15.setHorario("8hrs às 19hrs");
        c15.setQt(15);
        c15.setLocal("Itaperuna-Cidade Nova");


        Coleta c16 = new Coleta();
        c16.setDia("Segunda-Feira a Sexta-Feira");
        c16.setHorario("8hrs às 16hrs");
        c16.setQt(16);
        c16.setLocal("Itaperuna-CRAS-São José");

        Coleta c17 = new Coleta();
        c17.setDia("Segunda-Feira a Sexta-Feira");
        c17.setHorario("8hrs às 16hrs");
        c17.setQt(17);
        c17.setLocal("Itaperuna-Bairro Vinhosa");

        Coleta c18 = new Coleta();
        c18.setDia("Segunda-Feira a Sexta-Feira");
        c18.setHorario("8hrs às 16hrs");
        c18.setQt(18);
        c18.setLocal("Itaperuna-Bairro Aeroporto");


        coletas = new ArrayList<>();
        coletas.addAll(Arrays.asList(c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17,c18)) ;

        adapter.atualiza(coletas);


    }
    //instaciando itens da view
    private void initView(View view) {
        getActivity().setTitle("Horários de coleta");
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new ColetasAdapter(getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration mDivider = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL
        );
//        recyclerView.addItemDecoration(mDivider);
        recyclerView.setAdapter(adapter);

    }



}


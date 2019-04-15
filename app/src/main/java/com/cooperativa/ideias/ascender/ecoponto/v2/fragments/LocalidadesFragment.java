package com.cooperativa.ideias.ascender.ecoponto.v2.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.cooperativa.ideias.ascender.ecoponto.R;
import com.cooperativa.ideias.ascender.ecoponto.Utils.GetDataFrom;
import com.cooperativa.ideias.ascender.ecoponto.Utils.RecyclerTouchListener;
import com.cooperativa.ideias.ascender.ecoponto.v2.DAO.ConfiguracoesFirebase;
import com.cooperativa.ideias.ascender.ecoponto.v2.adapters.LocalidadesAdapter;
import com.cooperativa.ideias.ascender.ecoponto.v2.models.Dia;
import com.cooperativa.ideias.ascender.ecoponto.v2.models.Ponto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LocalidadesFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Ponto> pontos;
    private LocalidadesAdapter adapter;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.v2_localidades_fragment, container, false);
        getActivity().setTitle("Localidades");
        initView(view);
        preencherLista();
        return view;
    }

    private void preencherLista() {
        pontos = new ArrayList<>();
        Query databasePontos = ConfiguracoesFirebase.getPontos();
        databasePontos.keepSynced(true);
        databasePontos.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot) {

                try{
                    pontos.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Ponto ponto = snapshot.getValue(Ponto.class);
                        pontos.add(ponto);

                    }

                    progressBar.setVisibility(View.GONE);
                    adapter.atualizar(pontos);


                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        adapter = new LocalidadesAdapter(getActivity(), pontos, recyclerView);
        recyclerView.setAdapter(adapter);
    }

    private void initView(View view) {
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        DividerItemDecoration mDivider = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL
        );
        recyclerView.addItemDecoration(mDivider);




    }

}

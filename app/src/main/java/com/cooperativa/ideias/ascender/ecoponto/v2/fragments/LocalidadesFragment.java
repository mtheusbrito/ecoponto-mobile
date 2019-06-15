package com.cooperativa.ideias.ascender.ecoponto.v2.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.cooperativa.ideias.ascender.ecoponto.R;
import com.cooperativa.ideias.ascender.ecoponto.v2.DAO.ConfiguracoesFirebase;
import com.cooperativa.ideias.ascender.ecoponto.v2.adapters.LocalidadesAdapter;
import com.cooperativa.ideias.ascender.ecoponto.v2.models.Ponto;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class LocalidadesFragment extends Fragment implements OnBackPressed {
    private RecyclerView recyclerView;
    private ArrayList<Ponto> pontos;
    private LocalidadesAdapter adapter;
    private ProgressBar progressBar;

    //Variaveis para AdMobi
    private AdView adView;





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.v2_localidades_fragment, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        getActivity().setTitle("Localidades");
        initView(view);
        preencherLista();
        return view;

    }
    private void showGoogleAdMobAds() {

        try {
            AdView adView = new AdView(getActivity());
            adView.setAdSize(AdSize.BANNER);
            adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        } catch (Exception e) {
            Log.e(TAG, "Error while getting ads", e);
        }
    }

   // public void AdMobi(View view){
     //   initView(view);
      //  adView = new AdView(getActivity());
       // adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
       // adView.setAdSize(AdSize.BANNER);
      // LinearLayout layout = (LinearLayout)view.findViewById(R.id.adView);
      //  AdView mAdView = (AdView) view.findViewById(R.id.adView);
      //  mAdView.addView(adView);
       // adView.addView(adView);
       // AdRequest adRequest = new AdRequest.Builder().build();
      ////  adView.loadAd(adRequest);
      //  return view;

   // }

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
        adView = view.findViewById(R.id.adView);
        admobAds();




    }

    private void admobAds() {

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onPause() {
        super.onPause();
        if (adView != null) {
            adView.pause();

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.pause();

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (adView != null) {
            adView.pause();

        }
    }
}


package com.cooperativa.ideias.ascender.ecoponto.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.cooperativa.ideias.ascender.ecoponto.R;
import com.cooperativa.ideias.ascender.ecoponto.activitys.MainActivity;
import com.cooperativa.ideias.ascender.ecoponto.dao.ConfiguracoesFirebase;
import com.cooperativa.ideias.ascender.ecoponto.adapters.LocalidadeAdapter;
import com.cooperativa.ideias.ascender.ecoponto.models.Cidade;
import com.cooperativa.ideias.ascender.ecoponto.models.Estado;
import com.cooperativa.ideias.ascender.ecoponto.models.Ponto;
import com.cooperativa.ideias.ascender.ecoponto.utils.ConstantsUtils;
import com.cooperativa.ideias.ascender.ecoponto.utils.FragmentUtils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class LocalidadesFragment extends Fragment implements OnBackPressed {
    private RecyclerView recyclerView;
    private ArrayList<Ponto> pontos;
    private LocalidadeAdapter adapter;
    private ProgressBar progressBar;

    private Bundle bundle;
    private Cidade cidade;
    private GoogleMap mMap;
    //Variaveis para AdMobi
    private AdView adView;





    //Instanciando objetos na classe Localidades...
    public static Fragment newInstance(Cidade cidade) {
        LocalidadesFragment localidadesFragment = new LocalidadesFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ConstantsUtils.CIDADE, cidade);
        localidadesFragment.setArguments(bundle);
        return localidadesFragment;

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.v2_localidades_fragment, container, false);
         //((AppCompatActivity) getActivity()).getSupportActionBar().show();
        //  getActivity().setTitle("Localidades");
//        MobileAds.initialize(getActivity(),"ca-app-pub-4036318734376935~9692211326");
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

        adapter = new LocalidadeAdapter(getActivity(), pontos, recyclerView);
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

    //retornando a cidade escolhida no spinner da classe Main
    private void getCidade() {
        bundle = getArguments();
        if (bundle != null) {
            cidade = bundle.getParcelable(ConstantsUtils.CIDADE);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(cidade.getLatitude()), Double.parseDouble(cidade.getLongitude())), 15));

        }
    }
}


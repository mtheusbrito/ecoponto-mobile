package com.cooperativa.ideias.ascender.ecoponto.fragments;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cooperativa.ideias.ascender.ecoponto.R;
import com.cooperativa.ideias.ascender.ecoponto.utils.CircleTransform;
import com.cooperativa.ideias.ascender.ecoponto.utils.ConstantsUtils;
import com.cooperativa.ideias.ascender.ecoponto.utils.FragmentUtils;
import com.cooperativa.ideias.ascender.ecoponto.dao.ConfiguracoesFirebase;
import com.cooperativa.ideias.ascender.ecoponto.models.Cidade;
import com.cooperativa.ideias.ascender.ecoponto.models.Ponto;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class MapaFragment extends Fragment implements OnMapReadyCallback, OnBackPressed {
    private ArrayList<Ponto> pontos;
    private GoogleMap mMap;
    private Bundle bundle;
    private Cidade cidade;


    public static Fragment newInstance(Cidade cidade) {
        MapaFragment mapaFragment = new MapaFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ConstantsUtils.CIDADE, cidade);
        mapaFragment.setArguments(bundle);
        return mapaFragment;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.v2_mapa_fragment, container, false);
        initView();
        return view;
    }
    private void initView() {
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        if(mMap ==null){
            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getCidade();
        getPontos();



//        mMap.setOnMapLongClickListener(latLng -> {
//            Bundle bundle = new Bundle();
//            bundle.putString(ConstantsUtils.LATITUDE, String.valueOf(latLng.latitude));
//            bundle.putString(ConstantsUtils.LONGITUDE, String.valueOf(latLng.longitude));
//            startActivity(new Intent(getActivity(), ProjetarActivity.class).putExtras(bundle));
//        });


    }
    private void getPontos() {
        pontos = new ArrayList<>();
        Query databasePontos = ConfiguracoesFirebase.getPontos();
        databasePontos.keepSynced(true);
        databasePontos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {

                    pontos.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Ponto ponto = snapshot.getValue(Ponto.class);
                        pontos.add(ponto);
                    }
                    setarPontos(pontos);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void setarPontos(ArrayList<Ponto> pontos) {
        for (Ponto ponto : pontos) {
            setarNoMapa(ponto);
        }
    }

    private void setarNoMapa(Ponto ponto) {
        try {
            final MarkerOptions markerOptions = new MarkerOptions();
            Double la = Double.valueOf(ponto.getLatitude());
            Double lo = Double.valueOf(ponto.getLongitude());
            LatLng latLng = new LatLng(la, lo);
            markerOptions.position(latLng).title(ponto.getLocal());


            Picasso.get().load(R.drawable.eco_list).resize(50, 50).transform(new CircleTransform()).into(new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    markerOptions.icon(BitmapDescriptorFactory.fromBitmap(bitmap));
                    Marker marker = mMap.addMarker(markerOptions);
                    marker.setTag(ponto);
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            });

            mMap.setOnInfoWindowClickListener(marker -> {
                Ponto ponto1 = (Ponto) marker.getTag();
                if (ponto1 != null) {
                            FragmentUtils.replace(getActivity(), new DetalhesFragment().newInstance(ponto1));
                }

            });

        } catch (Exception e) {
            e.printStackTrace();
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

    @Override
    public void onBackPressed() {

    }
}

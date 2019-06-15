package com.cooperativa.ideias.ascender.ecoponto.v2.activitys;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.cooperativa.ideias.ascender.ecoponto.Constants;
import com.cooperativa.ideias.ascender.ecoponto.R;
import com.cooperativa.ideias.ascender.ecoponto.Utils.ConstantsUtils;
import com.google.android.gms.maps.model.LatLng;

public class ProjetarActivity extends AppCompatActivity {

    private  String lat, lng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projetar);
        setTitle(getResources().getString(R.string.projetar));

        recuperarCoordenadas();
    }

    private void recuperarCoordenadas() {
       if(getIntent().getExtras()!=null){
           lat = getIntent().getExtras().getString(ConstantsUtils.LATITUDE);
           lng = getIntent().getExtras().getString(ConstantsUtils.LONGITUDE);
           Toast.makeText(this, lat +"\n" + lng, Toast.LENGTH_SHORT).show();
       }


    }
}

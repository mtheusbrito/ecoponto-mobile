package com.cooperativa.ideias.ascender.ecoponto.Activity.Activity.Activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivityNsr extends SupportMapFragment implements OnMapReadyCallback,
        LocationListener, ActivityCompat.OnRequestPermissionsResultCallback,
        GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMarkerDragListener {

    //private GoogleMap mMap;
    // private Marker currentLocationMaker;
    // private LatLng currentLocationLatLong;
    //private DatabaseReference mDatabase;
    // private Object markers;
    // private FragmentManager supportFragmentManager;
    // private LocationManager LocationManager;
    //  Context mContext;

    private Location location;
    private double longitude = -25.429675;
    private DatabaseReference databaseReference;
    private double latitude = -49.429675;
    private static final int REQUEST_PERMISSIONS = 1;
    private GoogleMap map;
    private ProgressDialog progressDialog;
    private LocationManager lm;
    //private LatLng myLocation;// variavel zoom automatico
    private LatLngBounds ITAPERUNA = new LatLngBounds(
            new LatLng(-21.239822, -41.844152), new LatLng(-21.177369, -41.935818));


    private static String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);


        if (!isLocationEnabled(getContext())) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setMessage("Para continuar, permita que o dispositivo ative a localização, que usa o serviço de GPS.");
            dialog.setPositiveButton("Ativar", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });
            AlertDialog alert = dialog.create();
            alert.show();
        }
    }

    public void initMaps() {
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this.getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();


        } else {

            LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 60000, (LocationListener) this);

        }

    }


    private void requestPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this.getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) ||
                ActivityCompat.shouldShowRequestPermissionRationale(this.getActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION)) {

            Toast.makeText(this.getContext(), "Permisão Negada", Toast.LENGTH_SHORT).show();

        } else {

            ActivityCompat.requestPermissions(this.getActivity(), PERMISSIONS, REQUEST_PERMISSIONS);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permission[], int[] grantResult) {
        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                if (grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this.getContext(), "Autorizado", Toast.LENGTH_SHORT).show();

                    initMaps();

                } else {

                    Toast.makeText(this.getContext(), "Permisão Negada", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }


    public void onMapReady(GoogleMap map) {
        this.map = map;
        if (lm != null) {
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();

            }

        }

        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this.getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);

        }


        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setZoomGesturesEnabled(true);
        map.setOnInfoWindowClickListener(this);

        //map.moveCamera(CameraUpdateFactory.newLatLngZoom(this.ITAPERUNA.getCenter(), 14));

        map.addMarker(new MarkerOptions().position(new LatLng(-21.2063722, -41.890727))
                .title("EcoPonto").snippet("Matriz São José do Avaí"));

        //Localização Paróquia São Benedito...
        map.addMarker(new MarkerOptions().position(new LatLng( -21.2138618, -41.8899645 ))
                .title("EcoPonto"). snippet("Paróquia São João Benedito"));

        //Localização Paróquia do Sagrado Coração...
        map.addMarker(new MarkerOptions().position(new LatLng( -21.2100406, -41.8659748 ))
                .title("EcoPonto"). snippet("Paróquia do Sagrado Coração de Jesus"));

        //Localização Paróquia Santa Rita de Cássia...
        map.addMarker(new MarkerOptions().position(new LatLng( -21.2100406, -41.8659748 ))
                .title("EcoPonto"). snippet("Paróquia Santa Rita de Cássia"));

        //Localização Paróquia Santa Rita de Cássia...
        map.addMarker(new MarkerOptions().position(new LatLng( -21.1923621, -41.8850054 ))
                .title("EcoPonto"). snippet("Paróquia Nossa Senhora do Rosário de Fátima e S. Geraldo Itaperuna"));


        //Localização Feira Popular Itaperuna...
        map.addMarker(new MarkerOptions().position(new LatLng( -21.1999504, -41.9133349 ))
                .title("EcoPonto"). snippet("Feira Popular Itaperuna"));

        //Localização Escola Nossa Senhora das Graças...
        map.addMarker(new MarkerOptions().position(new LatLng( -21.3038468, -42.1900789 ))
                .title("EcoPonto"). snippet("Escola Nossa Senhora das Graças"));

        //Localização Escola Nossa Senhora das Graças...
        map.addMarker(new MarkerOptions().position(new LatLng( -21.3038468, -42.1900789 ))
                .title("EcoPonto"). snippet("Escola Nossa Senhora das Graças"));

        //Localização Raposo Hotel Águas Claras...
        map.addMarker(new MarkerOptions().position(new LatLng(-21.100962, -42.1158227 ))
                .title("EcoPonto"). snippet("Raposo Hotel Águas Claras"));

        //Localização Poliesportivo Itaperuna...
        map.addMarker(new MarkerOptions().position(new LatLng(-21.1947464, -41.9071948 ))
                .title("EcoPonto"). snippet("Poliesportivo Itaperuna"));

        //Localização Colégio  Humberto de Campos...
        map.addMarker(new MarkerOptions().position(new LatLng(-21.1948126, -41.9246374 ))
                .title("EcoPonto"). snippet("Colégio  Humberto de Campos"));

        //Localização Igreja Metodista Central...
        map.addMarker(new MarkerOptions().position(new LatLng(-21.1940382, -41.8987281 ))
                .title("EcoPonto"). snippet("Igreja Metodista Central"));

        //Localização Igreja Nossa Senhora Das Graças...
        map.addMarker(new MarkerOptions().position(new LatLng(-21.2155716, -41.8806761 ))
                .title("EcoPonto"). snippet("Igreja Nossa Senhora Das Graças"));

        //Localização Igreja Tanque de Betesda...
        map.addMarker(new MarkerOptions().position(new LatLng(-21.1988365, -41.8995058 ))
                .title("EcoPonto"). snippet("Igreja Tanque de Betesda"));

        //Localização  CRAS-Colégio São José...
        map.addMarker(new MarkerOptions().position(new LatLng(-21.2028144, -41.8890882 ))
                .title("EcoPonto"). snippet("CRAS-Colégio São José"));


        //Localização  Colégio São José...
        map.addMarker(new MarkerOptions().position(new LatLng(-21.2021063, -41.8885662))
                .title("EcoPonto"). snippet("Colégio São José"));


        //Localização Colégio Lincon Barbosa...
        map.addMarker(new MarkerOptions().position(new LatLng(-21.1938476, -41.8854996))
                .title("Ponto: EcoPonto"). snippet("Colégio Lincon Barbosa"));

        //Localização Colégio Thedomiro...
        map.addMarker(new MarkerOptions().position(new LatLng(-21.1938467, -41.9008206))
                .title("Ponto: EcoPonto"). snippet("Colégio Thedomiro"));


        //Localização Colégio Bezerra de Menezes...
        map.addMarker(new MarkerOptions().position(new LatLng(-21.2137489, -41.8757103))
                .title("EcoPonto"). snippet("Colégio Bezerra de Menezes"));



        //Localização Colégio Elzo Galvão...
        map.addMarker(new MarkerOptions().position(new LatLng(-21.2044671, -41.8848098))
                .title("EcoPonto"). snippet("Colégio Elzo Galvão"));


        //Localização Igreja N.S de Lurdes...
        map.addMarker(new MarkerOptions().position(new LatLng(-21.1936083, -41.9010794))
                .title("EcoPonto"). snippet("Igreja N.S de Lurdes"));

        //Localização Ponto de Vista(Loteamente Dr.Edgar...
        map.addMarker(new MarkerOptions().position(new LatLng(-21.1919099, -41.9106979))
                .title("EcoPonto"). snippet("Ponto de Vista(Loteamente Dr.Edgar"));

        //Localização Secretaria do Ambiente Itaperuna...
        map.addMarker(new MarkerOptions().position(new LatLng(-21.191909, -41.9260189))
                .title("EcoPonto"). snippet("Secretaria do Ambiente Itaperuna"));


        //Localização Escola Padre Geraldo...
        map.addMarker(new MarkerOptions().position(new LatLng(-21.1976944, -41.8797811))
                .title("EcoPonto"). snippet("Escola Padre Geraldo"));

        CameraUpdateFactory.newLatLngZoom( new LatLng(-21.2159481,-42.1704429), (float) 14.0);
        CameraUpdateFactory.zoomTo(14.0f);
    }



    public void onLocationChanged(Location location) {

    }


    public void onStatusChanged(String provider, int status, Bundle extras) {

    }


    public void onProviderEnabled(String provider) {



    }


    public void onProviderDisabled(String provider) {


    }


    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        }else{
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }


    }




    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }


    @Override
    public void onInfoWindowClick(Marker marker) {

    }
}




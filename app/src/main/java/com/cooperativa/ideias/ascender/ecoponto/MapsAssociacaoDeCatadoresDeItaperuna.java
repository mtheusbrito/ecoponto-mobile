package com.cooperativa.ideias.ascender.ecoponto;

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
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.widget.Toast;

import com.cooperativa.ideias.ascender.ecoponto.fragments.EmpregoDialogFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsAssociacaoDeCatadoresDeItaperuna  extends SupportMapFragment implements OnMapReadyCallback,
        LocationListener, ActivityCompat.OnRequestPermissionsResultCallback,
        GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMarkerDragListener {

    private GoogleMap mMap;


    private Location location;
    private double longitude = -25.429675;
    private DatabaseReference databaseReference;
    private double latitude = -49.429675;
    private static final int REQUEST_PERMISSIONS = 1;
    private GoogleMap map;
    private ProgressDialog progressDialog;
    private LocationManager lm;


    private static String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getMapAsync ( this );

        if (!isLocationEnabled ( getContext ( ) )) {
            AlertDialog.Builder dialog = new AlertDialog.Builder ( getContext ( ) );
            dialog.setMessage ( "Para continuar, permita que o dispositivo ative a localização, que usa o serviço de GPS." );
            dialog.setPositiveButton ( "Ativar", new DialogInterface.OnClickListener ( ) {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent ( Settings.ACTION_LOCATION_SOURCE_SETTINGS );
                    startActivity ( intent );
                }
            } );
            AlertDialog alert = dialog.create ( );
            alert.show ( );
        }
    }

    public void initMaps() {
        if (ActivityCompat.checkSelfPermission ( this.getContext ( ), Manifest.permission.ACCESS_COARSE_LOCATION )
                != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission ( this.getContext ( ),
                Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions ( );


        } else {

            LocationManager lm = (LocationManager) getActivity ( ).getSystemService ( Context.LOCATION_SERVICE );
            location = lm.getLastKnownLocation ( LocationManager.GPS_PROVIDER );
            lm.requestLocationUpdates ( LocationManager.GPS_PROVIDER, 10, 60000, (LocationListener) this );

        }

    }


    private void requestPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale ( this.getActivity ( ),
                Manifest.permission.ACCESS_COARSE_LOCATION ) ||
                ActivityCompat.shouldShowRequestPermissionRationale ( this.getActivity ( ),
                        Manifest.permission.ACCESS_FINE_LOCATION )) {

            Toast.makeText ( this.getContext ( ), "Permisão Negada", Toast.LENGTH_SHORT ).show ( );

        } else {

            ActivityCompat.requestPermissions ( this.getActivity ( ), PERMISSIONS, REQUEST_PERMISSIONS );
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permission[], int[] grantResult) {
        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                if (grantResult.length > 0 && grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText ( this.getContext ( ), "Autorizado", Toast.LENGTH_SHORT ).show ( );

                    initMaps ( );

                } else {

                    Toast.makeText ( this.getContext ( ), "Permisão Negada", Toast.LENGTH_SHORT ).show ( );
                }
                return;
            }
        }
    }


    public void onMapReady(GoogleMap map) {
        this.map = map;
        if (lm != null) {
            if (location != null) {
                latitude = location.getLatitude ( );
                longitude = location.getLongitude ( );

            }

        }

        if (ActivityCompat.checkSelfPermission ( this.getContext ( ), Manifest.permission.ACCESS_COARSE_LOCATION )
                == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission ( this.getContext ( ),
                Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled ( true );

        }


        map.getUiSettings ( ).setZoomControlsEnabled ( true );
        map.getUiSettings ( ).setZoomGesturesEnabled ( true );
        map.setOnInfoWindowClickListener ( this );
        map.addMarker ( new MarkerOptions ( ).position ( new LatLng ( -21.2020524, -41.9424158 ) )
                .title ( "Nos encontre" ).snippet ( " Associação de Catadores de Itaperuna" ) );


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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt ( context.getContentResolver ( ), Settings.Secure.LOCATION_MODE );

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace ( );
                return false;
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        } else {
            locationProviders = Settings.Secure.getString ( context.getContentResolver ( ), Settings.Secure.LOCATION_PROVIDERS_ALLOWED );
            return !TextUtils.isEmpty ( locationProviders );
        }
    }
    //Metodo para Dialog pontos do mapa...
    public void onInfoWindowClick(Marker marker) {
        EmpregoDialogFragment empregoDialogFragment = new EmpregoDialogFragment ();
        empregoDialogFragment.show(getFragmentManager(), "empregoFragment");


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



}




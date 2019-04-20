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
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.cooperativa.ideias.ascender.ecoponto.fragments.InfoMapsDialogFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivityNsr extends SupportMapFragment implements OnMapReadyCallback,
        LocationListener, ActivityCompat.OnRequestPermissionsResultCallback, GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMarkerDragListener {


    private GoogleMap mMap;
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
   // private GoogleMap map;
    private ProgressDialog progressDialog;
    private LocationManager lm;
    private FragmentManager fragmentManager;
    private LatLng myLocation;

    // variavel zoom automatico
    private LatLngBounds ITAPERUNA = new LatLngBounds (
            new LatLng ( -21.2040682, -41.8965709 ), new LatLng ( -21.2040682, -41.8965709 ) );


    private static String[] PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        getMapAsync(this);
        initMaps();

        //  getMarkers();// getMarkes adicionado do metodo Database realtime Firebase...


        if (!isLocationEnabled(getContext())) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setMessage("Por favor, ative o GPS do seu smartphone para que o aplicativo funcione corretamente.");
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
        if(ActivityCompat.checkSelfPermission(this.getContext(),Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this.getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            requestPermissions();


        } else {

            LocationManager lm = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
            location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 60000, this);

        }

    }


    private void requestPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this.getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) ||
                ActivityCompat.shouldShowRequestPermissionRationale(this.getActivity(),
                        Manifest.permission.ACCESS_FINE_LOCATION)) {

            Toast.makeText(this.getContext(), "Permisão Negada", Toast.LENGTH_SHORT).show();

        }else {

            ActivityCompat.requestPermissions(this.getActivity(), PERMISSIONS, REQUEST_PERMISSIONS);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permission, int[] grantResult) {
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


        // Comecando aqui metodo para PopUp Dialogo Feira Popular...
        mMap.setInfoWindowAdapter ( new GoogleMap.InfoWindowAdapter ( ) {

            @Override
            // Return null here, so that getInfoContents() is called next.
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                // Inflate the layouts for the info window, title and snippet.
                View infoWindow = getLayoutInflater ( ).inflate ( R.layout.fragment_dialogo_feira_popular, null );

                TextView title = infoWindow.findViewById ( R.id.title );
                title.setText ( marker.getTitle ( ) );

                TextView snippet = infoWindow.findViewById ( R.id.snippet );
                snippet.setText ( marker.getSnippet ( ) );

                return infoWindow;
            }
        } );
    }




    public void onMapReady(GoogleMap map) {
        this.mMap = map;
        if (lm != null) {
            if (location != null) {
                latitude = location.getLatitude ( );
                longitude = location.getLongitude ( );

            }
        }
        if (ActivityCompat.checkSelfPermission ( this.getContext ( ), Manifest.permission.ACCESS_COARSE_LOCATION )
                == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission ( this.getContext ( ),
                Manifest.permission.ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled ( true );

        }






        mMap.getUiSettings ( ).setZoomControlsEnabled ( true );
        mMap.getUiSettings ( ).setZoomGesturesEnabled ( true );
        mMap.setOnInfoWindowClickListener ( this );
        mMap.moveCamera ( CameraUpdateFactory.newLatLngZoom ( this.ITAPERUNA.getCenter ( ), 14 ) );

        //Metodo para salvar dados no banco de dados...
        //LocationData locationData = new LocationData ( location.getLatitude ( ), location.getLongitude ( ) );
        //databaseReference.child ( "location" ).child ( String.valueOf ( new Date ( ).getTime ( ) ) ).setValue ( locationData );
        //Metodo para salvar dados no banco de dados...


        mMap.addMarker ( new MarkerOptions ( ).position ( new LatLng ( -21.2063722, -41.890727 ) )
                .title ( "Coleta Seletiva" ).snippet ( "Matriz São José do Avaí" )
                .icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        //Localização Paróquia São Benedito...
        mMap.addMarker ( new MarkerOptions ( ).position ( new LatLng ( -21.2138618, -41.8899645 ) )
                .title ( "Coleta Seletiva" ).snippet ( "Paróquia São João Benedito" )
                .icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        //Localização Paróquia do Sagrado Coração...
        mMap.addMarker ( new MarkerOptions ( ).position ( new LatLng ( -21.2100406, -41.8659748 ) )
                .title ( "Coleta Seletiva" ).snippet ( "Paróquia do Sagrado Coração de Jesus" )
                .icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        //Localização Paróquia Santa Rita de Cássia...
        mMap.addMarker ( new MarkerOptions ( ).position ( new LatLng ( -21.2100406, -41.8659748 ) )
                .title ( "Coleta Seletiva" ).snippet ( "Paróquia Santa Rita de Cássia" )
                .icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        //Localização Paróquia Santa Rita de Cássia...
        mMap.addMarker ( new MarkerOptions ( ).position ( new LatLng ( -21.1923621, -41.8850054 ) )
                .title ( "Coleta Seletiva" ).snippet ( "Paróquia Nossa Senhora do Rosário de Fátima e S. Geraldo Itaperuna" )
                .icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));


        //Localização Feira Popular Itaperuna...
        mMap.addMarker ( new MarkerOptions ( ).position ( new LatLng ( -21.1999504, -41.9133349 ) )
                .title ( "Coleta Seletiva" ).snippet ( "Feira Popular Itaperuna" )
                .icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        //Localização Escola Nossa Senhora das Graças...
        mMap.addMarker ( new MarkerOptions ( ).position ( new LatLng ( -21.3038468, -42.1900789 ) )
                .title ( "Coleta Seletiva" ).snippet ( "Escola Nossa Senhora das Graças" )
                .icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        //Localização Escola Nossa Senhora das Graças...
        mMap.addMarker ( new MarkerOptions ( ).position ( new LatLng ( -21.3038468, -42.1900789 ) )
                .title ( "Coleta Seletiva" ).snippet ( "Escola Nossa Senhora das Graças" )
                .icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        //Localização Raposo Hotel Águas Claras...
        mMap.addMarker ( new MarkerOptions ( ).position ( new LatLng ( -21.100962, -42.1158227 ) )
                .title ( "Coleta Seletiva" ).snippet ( "Raposo Hotel Águas Claras" )
                .icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        //Localização Poliesportivo Itaperuna...
        mMap.addMarker ( new MarkerOptions ( ).position ( new LatLng ( -21.1947464, -41.9071948 ) )
                .title ( "Coleta Seletiva" ).snippet ( "Poliesportivo Itaperuna" )
                .icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        //Localização Colégio  Humberto de Campos...
        mMap.addMarker ( new MarkerOptions ( ).position ( new LatLng ( -21.1948126, -41.9246374 ) )
                .title ( "Coleta Seletiva" ).snippet ( "Colégio  Humberto de Campos" )
                .icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        //Localização Igreja Metodista Central...
        mMap.addMarker ( new MarkerOptions ( ).position ( new LatLng ( -21.1940382, -41.8987281 ) )
                .title ( "Coleta Seletiva" ).snippet ( "Igreja Metodista Central" )
                .icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        //Localização Igreja Nossa Senhora Das Graças...
        mMap.addMarker ( new MarkerOptions ( ).position ( new LatLng ( -21.2155716, -41.8806761 ) )
                .title ( "Coleta Seletiva" ).snippet ( "Igreja Nossa Senhora Das Graças" )
                .icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        //Localização Igreja Tanque de Betesda...
        mMap.addMarker ( new MarkerOptions ( ).position ( new LatLng ( -21.1988365, -41.8995058 ) )
                .title ( "Coleta Seletiva" ).snippet ( "Igreja Tanque de Betesda" )
                .icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        //Localização  CRAS-Colégio São José...
        mMap.addMarker ( new MarkerOptions ( ).position ( new LatLng ( -21.2028144, -41.8890882 ) )
                .title ( "Coleta Seletiva" ).snippet ( "CRAS-Colégio São José" )
                .icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));


        //Localização  Colégio São José...
        mMap.addMarker ( new MarkerOptions ( ).position ( new LatLng ( -21.2021063, -41.8885662 ) )
                .title ( "Coleta Seletiva" ).snippet ( "Colégio São José" )
                .icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));


        //Localização Colégio Lincon Barbosa...
        mMap.addMarker ( new MarkerOptions ( ).position ( new LatLng ( -21.1938476, -41.8854996 ) )
                .title ( "Coleta Seletiva" ).snippet ( "Colégio Lincon Barbosa" )
                .icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        //Localização Colégio Thedomiro...
        mMap.addMarker ( new MarkerOptions ( ).position ( new LatLng ( -21.1938467, -41.9008206 ) )
                .title ( "Coleta Seletiva" ).snippet ( "Colégio Thedomiro" )
                .icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));


        //Localização Colégio Bezerra de Menezes...
        mMap.addMarker ( new MarkerOptions ( ).position ( new LatLng ( -21.2137489, -41.8757103 ) )
                .title ( "Coleta Seletiva" ).snippet ( "Colégio Bezerra de Menezes" )
                .icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));


        //Localização Colégio Elzo Galvão...
        mMap.addMarker ( new MarkerOptions ( ).position ( new LatLng ( -21.2044671, -41.8848098 ) )
                .title ( "Coleta Seletiva" ).snippet ( "Colégio Elzo Galvão" )
                .icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));


        //Localização Igreja N.S de Lurdes...
        mMap.addMarker ( new MarkerOptions ( ).position ( new LatLng ( -21.1936083, -41.9010794 ) )
                .title ( "Coleta Seletiva" ).snippet ( "Igreja N.S de Lurdes" )
                .icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        //Localização Ponto de Vista(Loteamente Dr.Edgar...
        mMap.addMarker ( new MarkerOptions ( ).position ( new LatLng ( -21.1919099, -41.9106979 ) )
                .title ( "Coleta Seletiva" ).snippet ( "Ponto de Vista(Loteamente Dr.Edgar" )
                .icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        //Localização Secretaria do Ambiente Itaperuna...
        mMap.addMarker ( new MarkerOptions ( ).position ( new LatLng ( -21.191909, -41.9260189 ) )
                .title ( "Coleta Seletiva" ).snippet ( "Secretaria do Ambiente Itaperuna" )
                .icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));


        //Localização Escola Padre Geraldo...
        mMap.addMarker ( new MarkerOptions ( ).position ( new LatLng ( -21.1976944, -41.8797811 ) )
                .title ( "Coleta Seletiva" ).snippet ( "Escola Padre Geraldo" )
                .icon( BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));


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
    public void onInfoWindowClick(Marker marker) {
        //Abrindo PopUp Para Ponto de Coleta Feira Popular...
        InfoMapsDialogFragment infoMapsDialogFragment = new InfoMapsDialogFragment ( );
        infoMapsDialogFragment.show ( getFragmentManager ( ), "empregoFragment" );
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




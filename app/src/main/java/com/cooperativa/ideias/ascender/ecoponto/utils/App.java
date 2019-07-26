package com.cooperativa.ideias.ascender.ecoponto.utils;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

public class App  extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Firebase Offline
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(getApplicationContext(), Integer.MAX_VALUE));
        Picasso built = builder.build();
        // built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);
    }
}
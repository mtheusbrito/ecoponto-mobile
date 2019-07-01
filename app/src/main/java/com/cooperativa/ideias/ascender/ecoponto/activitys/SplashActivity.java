package com.cooperativa.ideias.ascender.ecoponto.activitys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cooperativa.ideias.ascender.ecoponto.R;
import com.cooperativa.ideias.ascender.ecoponto.utils.ConstantsUtils;

public class SplashActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private Boolean primeiraVez;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v2_splash_activity);
        initView();

    }

    private void initView() {
        preferences = getSharedPreferences(ConstantsUtils.PREFERENCES, MODE_PRIVATE);
        primeiraVez = preferences.getBoolean(ConstantsUtils.FIRST, true);


        if (primeiraVez) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    SharedPreferences.Editor editor = preferences.edit();
                    primeiraVez = false;
                    editor.putBoolean(ConstantsUtils.FIRST, primeiraVez);
                    editor.apply();
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();


                }
            }, 5000);
        } else {
            startActivity(new Intent(SplashActivity.this,MainActivity.class));

            finish();

        }
    }
}

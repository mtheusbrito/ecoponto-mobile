package com.cooperativa.ideias.ascender.ecoponto;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cooperativa.ideias.ascender.ecoponto.R;

public class ViewPage extends AppCompatActivity {
     //Variavel para metodo Intent que chamará MainActivity...
    private Button btn;
// Metodo principal OnCreate...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_view_page );
       //Variavel btn com Widget Button para relacionar ao id...
        btn = (Button) findViewById (R.id.buttonMaps);

        // Instancias ViewPages...
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(this));

    }

    // Metodo para Button da ultima ViewPage com metodo onClick...
    public  void  btn(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish ();



    }

}



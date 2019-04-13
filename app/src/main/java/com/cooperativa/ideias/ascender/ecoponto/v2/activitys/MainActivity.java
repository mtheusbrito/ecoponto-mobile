package com.cooperativa.ideias.ascender.ecoponto.v2.activitys;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.cooperativa.ideias.ascender.ecoponto.R;
import com.cooperativa.ideias.ascender.ecoponto.Utils.ConstantsUtils;
import com.cooperativa.ideias.ascender.ecoponto.Utils.FragmentUtils;
import com.cooperativa.ideias.ascender.ecoponto.Utils.PermissionUtils;
import com.cooperativa.ideias.ascender.ecoponto.v2.fragments.LocalidadesFragment;
import com.cooperativa.ideias.ascender.ecoponto.v2.fragments.MapaFragment;
import com.cooperativa.ideias.ascender.ecoponto.v2.fragments.SobreFragment;

import java.lang.reflect.Field;

public class MainActivity  extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_mapa:
                   fragment = new MapaFragment();

                    break;

                case R.id.navigation_localidades:

                    fragment = new LocalidadesFragment();
                    break;
                case R.id.navigation_sobre:

                    fragment = new SobreFragment();
                    break;
            }

            FragmentUtils.replace(MainActivity.this, fragment);
            return true;
        }
    };


    protected  void onCreate(Bundle savedInsanceState){
        super.onCreate(savedInsanceState);
        setContentView(R.layout.v2_main_activity);





        FragmentUtils.replace(MainActivity.this, new MapaFragment());
        BottomNavigationView navigation = findViewById(R.id.navigation);
//        BottomNavigationViewHelper.removeShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    public static class BottomNavigationViewHelper {
        @SuppressLint("RestrictedApi")
        public static void removeShiftMode(BottomNavigationView view) {
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
            try {
                Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
                shiftingMode.setAccessible(true);
                shiftingMode.setBoolean(menuView, false);
                shiftingMode.setAccessible(false);
                for (int i = 0; i < menuView.getChildCount(); i++) {
                    BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                    //noinspection RestrictedApi
                    item.setShifting(false);
                    // set once again checked value, so view will be updated
                    //noinspection RestrictedApi
                    item.setChecked(item.getItemData().isChecked());
                }
            } catch (NoSuchFieldException e) {
                Log.e("BottomNav", "Unable to get shift mode field", e);
            } catch (IllegalAccessException e) {
                Log.e("BottomNav", "Unable to change value of shift mode", e);
            }
        }
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}

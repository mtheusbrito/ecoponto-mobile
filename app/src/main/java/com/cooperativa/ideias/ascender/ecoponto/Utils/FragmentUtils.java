package com.cooperativa.ideias.ascender.ecoponto.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.cooperativa.ideias.ascender.ecoponto.R;

public class FragmentUtils {
    public static void replace(AppCompatActivity activity, Fragment fragment){
        replace(activity, fragment, R.id.container);
    }

    private static void replace(AppCompatActivity activity, Fragment fragment, int container) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(container, fragment);
        transaction.commit();

    }
}

package com.cooperativa.ideias.ascender.ecoponto.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.ViewAnimationUtils;

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
    public static void replace(FragmentActivity activity, Fragment fragment){
        replace(activity, fragment, R.id.container);
    }

    private static void replace(FragmentActivity activity, Fragment fragment, int container) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(container, fragment , fragment.getClass().getSimpleName());
        transaction.addToBackStack(null);
        transaction.commit();

    }



    public static void replaceWithReturn(FragmentActivity activity, Fragment fragment){
        replaceWithReturn(activity, fragment, R.id.container);
    }

    private static void replaceWithReturn(FragmentActivity activity, Fragment fragment, int container) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(container, fragment, ConstantsUtils.DETALHES);
        transaction.addToBackStack(null);
        transaction.commit();

    }



    @SuppressLint("PrivateResource")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void circleReveal(AppCompatActivity context, int viewID, int posFromRight, boolean containsOverflow, final boolean isShow) {
        final View myView = context.findViewById(viewID);

        int width = myView.getWidth();

        if (posFromRight > 0)
            width -= (posFromRight * context.getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_material)) - (context.getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_material) / 2);
        if (containsOverflow)
            width -= context.getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_overflow_material);

        int cx = width;
        int cy = myView.getHeight() / 2;

        Animator anim;
        if (isShow)
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, (float) width);
        else
            anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, (float) width, 0);

        anim.setDuration((long) 220);


        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (!isShow) {
                    super.onAnimationEnd(animation);
                    myView.setVisibility(View.INVISIBLE);
                }
            }
        });


        if (isShow)
            myView.setVisibility(View.VISIBLE);


        anim.start();


    }




}

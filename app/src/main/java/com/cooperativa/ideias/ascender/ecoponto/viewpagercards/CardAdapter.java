package com.cooperativa.ideias.ascender.ecoponto.viewpagercards;


import android.support.v7.widget.CardView;
import android.view.ViewGroup;

public interface CardAdapter {

    public final int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();

    Object instantiateItem2(ViewGroup container, int position);
}

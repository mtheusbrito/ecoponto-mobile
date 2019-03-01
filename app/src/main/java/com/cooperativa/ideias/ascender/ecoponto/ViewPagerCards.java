package com.cooperativa.ideias.ascender.ecoponto;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cooperativa.ideias.ascender.ecoponto.viewpagercards.CardFragmentPagerAdapter;
import com.cooperativa.ideias.ascender.ecoponto.viewpagercards.ShadowTransformer;

import static com.cooperativa.ideias.ascender.ecoponto.MainActivity.dpToPixels;

public class ViewPagerCards extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_cards);


        // Parametros para utilização dos Cards como ViewPager...
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        CardFragmentPagerAdapter pagerAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(), dpToPixels(2, this));
        ShadowTransformer fragmentCardShadowTransformer = new ShadowTransformer(viewPager, pagerAdapter);
        fragmentCardShadowTransformer.enableScaling(true);

        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(false, fragmentCardShadowTransformer);
        viewPager.setOffscreenPageLimit(3);
    }
}

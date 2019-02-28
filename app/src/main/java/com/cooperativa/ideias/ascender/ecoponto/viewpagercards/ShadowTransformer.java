package com.cooperativa.ideias.ascender.ecoponto.viewpagercards;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;

// Classe em Java Paramatros, metodos, strings, funções para utilização viewpagercard...
//Criar uma Activity Main2 para que o ViewPage seja iniciado separadamente do MapsInicial...
/*
  Classe Java em desenvolvimento ainda. Observar a ideia principal do programador para não se torna ineficiente
  e assim atingindo uma qualidade e confiabilidade irritante ao usuário final.
  Criar ViewPager para mostrar tutoriais rápidos com a opção de seguir para um webview chamando o WebSite do EcoPonto
  que conterá informações, tutoriais para que o usuário final consiga ter uma visão maior sobre os projetos...
  A ideia é criar ViewPager em modo de Cards deslizantes am em vez de atividades em SplashCreen rolantes, para que
  seja mais intuitivo e bonito, visando sempre o usuário final ter uma experiência...

  Activity Main2 conterá o código que está criado na MainActivity temporariamente, assim destruindo o conflito de Card
  por cima do mapas que inciais. o XML está temporariamente também no Content_Main.XML...
 */
public class ShadowTransformer implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer {

    private ViewPager viewPager;
    private CardAdapter cardAdapter;
    private float lastOffset;
    private boolean scalingEnabled;

    public ShadowTransformer(ViewPager viewPager, CardAdapter adapter) {
        this.viewPager = viewPager;
        viewPager.addOnPageChangeListener(this);
        cardAdapter = adapter;
    }

    public void enableScaling(boolean enable) {
        if (scalingEnabled && !enable) {
            // Escolher o cartão principal...
            CardView currentCard = cardAdapter.getCardViewAt(viewPager.getCurrentItem());
            if (currentCard != null) {
                currentCard.animate().scaleY(1);
                currentCard.animate().scaleX(1);
            }
        }else if(!scalingEnabled && enable){
            // Inflar o cartão principal....
            CardView currentCard = cardAdapter.getCardViewAt(viewPager.getCurrentItem());
            if (currentCard != null) {
                //enlarge the current item
                currentCard.animate().scaleY(1.1f);
                currentCard.animate().scaleX(1.1f);
            }
        }
        scalingEnabled = enable;
    }

    @Override
    public void transformPage(View page, float position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int realCurrentPosition;
        int nextPosition;
        float baseElevation = cardAdapter.getBaseElevation();
        float realOffset;
        boolean goingLeft = lastOffset > positionOffset;

        // Se estamos indo para trás, o onPageScrolled recebe a última posição...
        // Ao em vez do atual...
        if (goingLeft) {
            realCurrentPosition = position + 1;
            nextPosition = position;
            realOffset = 1 - positionOffset;
        } else {
            nextPosition = position + 1;
            realCurrentPosition = position;
            realOffset = positionOffset;
        }

        // Evitando o travamento do overscroll...
        if (nextPosition > cardAdapter.getCount() - 1
                || realCurrentPosition > cardAdapter.getCount() - 1) {
            return;
        }

        CardView currentCard = cardAdapter.getCardViewAt(realCurrentPosition);

        // Esse valor pode ser nulo se um fragmento estiver sendo executado...
        // E as visualizações ainda não foram criadas...
        if (currentCard != null) {
            if (scalingEnabled) {
                currentCard.setScaleX((float) (1 + 0.1 * (1 - realOffset)));
                currentCard.setScaleY((float) (1 + 0.1 * (1 - realOffset)));
            }
            currentCard.setCardElevation((baseElevation + baseElevation
                    * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (1 - realOffset)));
        }

        CardView nextCard = cardAdapter.getCardViewAt(nextPosition);

        // Pode estar rolando rápido demais, para que o próximo card seja irrelevante...
        // Sendo assim já foi destruído ou um fragmento pode não ter sido criado...
        if (nextCard != null) {
            if (scalingEnabled) {
                nextCard.setScaleX((float) (1 + 0.1 * (realOffset)));
                nextCard.setScaleY((float) (1 + 0.1 * (realOffset)));
            }
            nextCard.setCardElevation((baseElevation + baseElevation
                    * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (realOffset)));
        }

        lastOffset = positionOffset;
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

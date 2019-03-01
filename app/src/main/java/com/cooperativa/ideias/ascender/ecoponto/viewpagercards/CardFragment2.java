package com.cooperativa.ideias.ascender.ecoponto.viewpagercards;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cooperativa.ideias.ascender.ecoponto.R;


public class CardFragment2 extends Fragment {

    private CardView cardView2;



    public static CardFragment2 getInstance2(int position) {
        CardFragment2 f2 = new CardFragment2();
        Bundle args2 = new Bundle();
        args2.putInt("position", position);
        f2.setArguments(args2);

        return f2;
    }

    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item2_viewpager, container, false);

        cardView2 = (CardView) view.findViewById(R.id.cardView2);
        cardView2.setMaxCardElevation(cardView2.getCardElevation() * CardAdapter.MAX_ELEVATION_FACTOR);

        TextView title22 = (TextView) view.findViewById(R.id.title22);
        Button button22 = (Button)view.findViewById(R.id.button22);


        title22.setText(String.format("Vamos GASTAR %d", getArguments().getInt("position")));
        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Botão Selecionado " + getArguments().getInt("position")
                        + "Clique!", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }




    public CardView getCardView() {
        return cardView2;
    }


}
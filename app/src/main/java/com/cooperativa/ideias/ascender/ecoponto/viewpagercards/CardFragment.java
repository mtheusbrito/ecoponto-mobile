package com.cooperativa.ideias.ascender.ecoponto.viewpagercards;

import android.annotation.SuppressLint;
import android.content.Intent;
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

import com.cooperativa.ideias.ascender.ecoponto.ASCItaperuna;
import com.cooperativa.ideias.ascender.ecoponto.EnviarEmail;
import com.cooperativa.ideias.ascender.ecoponto.MainActivity;
import com.cooperativa.ideias.ascender.ecoponto.ProtejaOsEcoPontos;
import com.cooperativa.ideias.ascender.ecoponto.R;
import com.cooperativa.ideias.ascender.ecoponto.Teste;

// Classe em Java Paramatros, metodos, strings, funções para utilização viewpagercard...
public class CardFragment extends Fragment {

    private CardView cardView;

    public static Fragment getInstance(int position) {
        CardFragment f = new CardFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        f.setArguments(args);

        return f;
    }

    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_viewpager, container, false);

        cardView = view.findViewById(R.id.cardView);
        cardView.setMaxCardElevation(cardView.getCardElevation() * CardAdapter.MAX_ELEVATION_FACTOR);

        final TextView title = view.findViewById(R.id.title);
        final Button button = view.findViewById(R.id.button);




        title.setText(String.format("Vamos ECONOMIZAR? %d", getArguments().getInt("position")));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Botão Selecionado " + getArguments().getInt("position")
                        + "Clique!", Toast.LENGTH_SHORT).show();
                //Botão FloatingActionBarMenu para enviar registo capturado pela Camera...
               // Intent intent = new Intent(getActivity(), MainActivity.class);
               // startActivity(intent);


            }
        });


        return view;
    }

        public CardView getCardView () {
            return cardView;
        }


}
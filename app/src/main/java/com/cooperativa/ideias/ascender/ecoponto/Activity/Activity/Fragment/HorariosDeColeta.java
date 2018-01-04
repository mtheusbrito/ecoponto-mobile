package com.cooperativa.ideias.ascender.ecoponto.Activity.Activity.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cooperativa.ideias.ascender.ecoponto.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HorariosDeColeta extends Fragment {


    public HorariosDeColeta() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_horarios_de_coleta, container,false);
        // Inflate the layout for this fragment

        String[] menuItens = {"Matriz São José do Avaí-  " +
                "-(Todos os dias de 8 às 19h)",

                "Paróquia São João Benedito- " +
                        "-(Todos os dias de 8 às 19h)",

                "Paróquia do Sagrado Coração de Jesus-  " +
                        "-(Todos os dias de 8 às 19h)",

                "Paróquia Santa Rita de Cássia-  " +
                        "-(Todos os dias de 8 às 19h)",

                "Paróquia Nossa Senhora do Rosário de Fátima- " +
                        "-(Todos os dias de 8 às 19h)",

                "Condomínio Ponto de Vista-" +
                        "-(Todos Os Dias de 7 às 17h)",

                "Feira Popular Itaperuna- " +
                        "-(Sábado de 7 às 11h)",

                "Escola Nossa Senhora das Graças- " +
                        "-(Segunda-feira a Sexta-feira de 8 às 16h)",

                "Raposo Hotel Águas Claras- " +
                        "-(Segunda-feira a Quinta-feira de 7 às 14h)",

                "POLIesportivo Itaperuna-" +
                "-(Segunda-feira a Sexta-feira de 8 às 21h)",

        "Colégio Municipal Humberto de Campos-" +
                "-(Segunda-feira a Sexta-feira de 8 às 16h)",

        "Igreja Metodista Central-" +
        "-(Todos os dias de 8 às 19h)",

        "Colegio São José-" +
        "-(Segunda-feira a Sexta-feira de 8 às 16h)",

        "Igreja Nossa Senhora Das Graças-" +
                "-(Todos os dias de 8 às 19h)",
        "Igreja Betesda" +
        "-(Todos os dias de 8 às 19h)",

        "CRAS-Colégio São José" +
        "-(Segunda-feira a Sexta-feira de 8 às 16h)",

        "Colégio São José" +
        "-(Segunda-feira a Sexta-feira de 8 às 16h)",

        "Colégio Lincon Barbosa" +
        "-(Segunda-feira a Sexta-feira de 8 às 16h)",

        "Colégio Thedomiro" +
        "-(Segunda-feira a sexta-feira de 8 às 16h)",

        "Colégio Bezerra de Menezes" +
        "-(Segunda-feira a Sexta-feira de 8 às 16h)",

        "Colégio Elzo Galvão" +
        "-(Segunda-feira a Sexta-feira de 8 às 16h)",

                "Escola Padre Geraldo" +
                        "-(Todos os dias de 8 às 16h)",

        "Igreja N.S de Lurdes" +
        "-(Todos os dias de 8 às 19h)",

        "Ponto de Vista- Loteamente Dr.Edgar" +
        "-(Todos os dias de 8 às 19h)",

        "Secretaria do Ambiente Itaperuna" +
        "-(Todos os dias de 8 às 18h"};

        ListView listView = (ListView) view.findViewById(R.id.menu);

        ArrayAdapter<String> ListViewAdapter = new ArrayAdapter<String>(
            getActivity(),
            android.R.layout.simple_list_item_1,
            menuItens
            );

            listView.setAdapter(ListViewAdapter);
            // Inflate the layout for this fragment
            return view;

    }


}

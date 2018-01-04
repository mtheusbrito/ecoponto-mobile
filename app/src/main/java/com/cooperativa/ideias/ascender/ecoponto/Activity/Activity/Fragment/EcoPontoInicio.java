package com.cooperativa.ideias.ascender.ecoponto.Activity.Activity.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cooperativa.ideias.ascender.ecoponto.Activity.Activity.Activity.MapsActivityClt;
import com.cooperativa.ideias.ascender.ecoponto.Activity.Activity.Activity.MapsActivityNsr;
import com.cooperativa.ideias.ascender.ecoponto.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EcoPontoInicio extends Fragment {


    public EcoPontoInicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_eco_ponto_inicio, container, false);

        Button ID = (Button) rootView.findViewById(R.id.button4);
        ID.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.ecoinicio, new MapsActivityClt(), "ASC");
                transaction.commit();
            }
        });

        Button IDE = (Button) rootView.findViewById(R.id.button5);
        IDE.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.ecoinicio, new MapsActivityNsr(), "ASCCI");
                transaction.commit();
            }

        });
        return rootView;


    }

}

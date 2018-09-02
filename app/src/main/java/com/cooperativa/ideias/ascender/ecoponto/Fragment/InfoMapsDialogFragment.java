package com.cooperativa.ideias.ascender.ecoponto.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cooperativa.ideias.ascender.ecoponto.ASCItaperuna;
import com.cooperativa.ideias.ascender.ecoponto.R;


public class InfoMapsDialogFragment extends DialogFragment {

    private Button btnassociados;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate ( R.layout.fragment_bar_dialog, container, false );

    }
        @Override
        public void onActivityCreated (Bundle savedInstanceState){
            super.onActivityCreated ( savedInstanceState );

        }
// Chamando atividade ao clicar no Button do FragmentDialog....
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        btnassociados = (Button)view.findViewById(R.id.btnassociados);

        btnassociados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ASCItaperuna.class);
                startActivity(intent);
                try {
                    InfoMapsDialogFragment.this.finalize ();
                } catch (Throwable throwable) {

                }
            }
        });

    }}











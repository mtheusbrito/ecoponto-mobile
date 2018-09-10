package com.cooperativa.ideias.ascender.ecoponto.fragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cooperativa.ideias.ascender.ecoponto.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TesteFragmentInfo extends DialogFragment {


    public TesteFragmentInfo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate ( R.layout.fragment_teste_fragment_info, container, false );
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated ( savedInstanceState );
    }
}
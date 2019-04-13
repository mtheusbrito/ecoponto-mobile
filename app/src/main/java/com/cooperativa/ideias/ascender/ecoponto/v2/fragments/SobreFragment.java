package com.cooperativa.ideias.ascender.ecoponto.v2.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cooperativa.ideias.ascender.ecoponto.R;

public class SobreFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.v2_sobre_fragment, container, false);
        getActivity().setTitle("Sobre");
        initView(view);

        return view;
    }

    private void initView(View view) {
    }
}

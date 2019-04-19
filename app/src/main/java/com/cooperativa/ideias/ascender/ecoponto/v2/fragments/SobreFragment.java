package com.cooperativa.ideias.ascender.ecoponto.v2.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cooperativa.ideias.ascender.ecoponto.R;

public class SobreFragment extends Fragment implements OnBackPressed{
    private ImageView imageView;
    public static final String URL = "http://www.ascenderideias.com.br/";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.v2_sobre_fragment, container, false);

        initView(view);
        return view;
    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }

    private void initView(View view) {
//        customTabService = new CustomTabService(URL);
//        customTabService.bindService(getActivity());
        imageView = view.findViewById(R.id.imageView13);
        imageView.setOnClickListener(v -> {


        openSite();


        });

    }

    private void openSite() {
//        Intent intentSite = new Intent(Intent.ACTION_VIEW);
//        intentSite.setData(Uri.parse(URL));
//       startActivity(intentSite);


        
    }

    @Override
    public void onBackPressed() {

    }
}

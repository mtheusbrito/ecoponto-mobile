package com.cooperativa.ideias.ascender.ecoponto;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cooperativa.ideias.ascender.ecoponto.Utils.GetDataFrom;
import com.squareup.picasso.Picasso;

public class SobreFragment extends Fragment {
    private ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sobre_mtheus, container, false);
       initView(view);
        return  view;
    }

    private void initView(View view) {
        getActivity().setTitle("Sobre");
        imageView = view.findViewById(R.id.image);
//        Picasso.get().load(R.drawable.sobre_eco).into(imageView);

    }
}

package com.cooperativa.ideias.ascender.ecoponto.v2.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cooperativa.ideias.ascender.ecoponto.R;
import com.cooperativa.ideias.ascender.ecoponto.Utils.ConstantsUtils;
import com.cooperativa.ideias.ascender.ecoponto.Utils.FragmentUtils;
import com.cooperativa.ideias.ascender.ecoponto.v2.models.Cidade;
import com.cooperativa.ideias.ascender.ecoponto.v2.models.Ponto;
import com.squareup.picasso.Picasso;

public class DetalhesFragment extends Fragment {

    private Bundle bundle;
    private Ponto ponto;
    private  Cidade cidade;
    private Integer screen;
    private TextView textViewLocal, textViewHorario, textViewDias, textViewDescricao;
    private ImageView imageView;

    public DetalhesFragment newInstance(Ponto ponto, Cidade cidade, Integer screen){
        DetalhesFragment detalhesFragment = new DetalhesFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ConstantsUtils.PONTO, ponto);
        bundle.putParcelable(ConstantsUtils.CIDADE, cidade);
        bundle.putInt(ConstantsUtils.SCREEN, screen);
        detalhesFragment.setArguments(bundle);
        return detalhesFragment;


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.v2_detalhes_fragment, container, false);
        getActivity().setTitle("Detalhes");
        initView(view);
        getDados();


        return view;

    }

    @SuppressLint("SetTextI18n")
    private void setDados() {
        if(ponto.getUrl().isEmpty() || ponto.getUrl() == null){
            Picasso.get().load(R.drawable.eco_list).into(imageView);
        }else {
            Picasso.get().load(ponto.getUrl()).into(imageView);
        }
        textViewLocal.setText(ponto.getLocal());
        textViewHorario.setText(ponto.getInicio() + " as " + ponto.getTermino());
        textViewDias.setText(ponto.getDiasInline());
        textViewDescricao.setText(ponto.getDescricao());

    }

    private void initView(View view) {
     textViewLocal = view.findViewById(R.id.textLocal);
     textViewHorario = view.findViewById(R.id.textHorario);
     textViewDias = view.findViewById(R.id.textDias);
     textViewDescricao = view.findViewById(R.id.textDescricao);
     imageView = view.findViewById(R.id.image);

    }

    private void getDados() {
        bundle = getArguments();
        if(bundle != null){
            ponto = bundle.getParcelable(ConstantsUtils.PONTO);
            cidade = bundle.getParcelable(ConstantsUtils.CIDADE);
            screen = bundle.getInt(ConstantsUtils.SCREEN);
            setDados();
        }
    }



    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    if(screen == 0){
                        FragmentUtils.replace(getActivity(),  MapaFragment.newInstance(cidade));
                    }else {
                        FragmentUtils.replace(getActivity(), new LocalidadesFragment());
                    }


                    return true;
                }

                return false;
            }
        });
    }
}

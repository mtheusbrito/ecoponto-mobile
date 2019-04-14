package com.cooperativa.ideias.ascender.ecoponto.v2.adapters;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cooperativa.ideias.ascender.ecoponto.R;
import com.cooperativa.ideias.ascender.ecoponto.v2.models.Dia;
import com.cooperativa.ideias.ascender.ecoponto.v2.models.Ponto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LocalidadesAdapter extends RecyclerView.Adapter<LocalidadesAdapter.ViewHolder> {
    private List<Ponto> pontos;
    private FragmentActivity activity;
    private RecyclerView recyclerView;


    public LocalidadesAdapter(FragmentActivity activity, List<Ponto>  pontos,  RecyclerView recyclerView) {
        this.pontos = pontos;
        this.activity = activity;
        this.recyclerView = recyclerView;

    }

    @NonNull
    @Override
    public LocalidadesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LocalidadesAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.v2_adapter_localidades, viewGroup, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull LocalidadesAdapter.ViewHolder viewHolder, int i) {
        final Ponto ponto = pontos.get(i);
        viewHolder.local.setText(ponto.local);
        viewHolder.horario.setText(ponto.inicio+ " as "+ponto.termino);


        StringBuilder dias = new StringBuilder();


        for (Map.Entry<String,Dia> d : ponto.getDias().entrySet()) {

            dias.append(" ").append(d.getValue().getLabel());
//            Toast.makeText(activity, pair.getValue().getLabel(), Toast.LENGTH_SHORT).show();

        }
        viewHolder.dias.setText(dias.toString());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }

    @Override
    public int getItemCount() {
        return pontos.size();
    }

    public void atualizar(List<Ponto> pontos) {
        this.pontos= pontos;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView local;
        private TextView horario;
        private TextView dias;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            local = itemView.findViewById(R.id.textLocal);
            horario = itemView.findViewById(R.id.textHorario);
            dias = itemView.findViewById(R.id.textDias);


        }
    }
}

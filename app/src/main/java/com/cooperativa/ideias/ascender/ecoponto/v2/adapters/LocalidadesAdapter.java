package com.cooperativa.ideias.ascender.ecoponto.v2.adapters;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cooperativa.ideias.ascender.ecoponto.R;
import com.cooperativa.ideias.ascender.ecoponto.Utils.CircleTransform;
import com.cooperativa.ideias.ascender.ecoponto.Utils.FragmentUtils;
import com.cooperativa.ideias.ascender.ecoponto.v2.fragments.DetalhesFragment;
import com.cooperativa.ideias.ascender.ecoponto.v2.models.Ponto;
import com.squareup.picasso.Picasso;

import java.util.List;

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
        Picasso.get().load(R.drawable.eco_list).transform(new CircleTransform()).into(viewHolder.imageView);

        viewHolder.dias.setText(ponto.getDiasInline());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentUtils.replaceWithReturn(activity, new DetalhesFragment().newInstance(ponto, null, 1));
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
        private ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            local = itemView.findViewById(R.id.textLocal);
            horario = itemView.findViewById(R.id.textHorario);
            dias = itemView.findViewById(R.id.textDias);
            imageView = itemView.findViewById(R.id.imageRow);


        }
    }
}

package com.cooperativa.ideias.ascender.ecoponto.adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cooperativa.ideias.ascender.ecoponto.R;
import com.cooperativa.ideias.ascender.ecoponto.Utils.CircleTransform;
import com.cooperativa.ideias.ascender.ecoponto.models.Coleta;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ColetasAdapter extends RecyclerView.Adapter<ColetasAdapter.ViewHolder> {
    private List<Coleta> coletas ;
    private FragmentActivity fragment;


    public ColetasAdapter(FragmentActivity fragment) {
        this.fragment = fragment;
        this.coletas = new ArrayList<>();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ColetasAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Coleta coleta = coletas.get(i);
        viewHolder.textViewTitle.setText(coleta.getDia());
        viewHolder.textViewShortDesc.setText(coleta.getHorario());
        viewHolder.textViewRating.setText(String.valueOf(coleta.getQt()));
        viewHolder.textViewTitle2.setText(coleta.getLocal());

        Picasso.get().load(R.drawable.caminhaoecoponto).transform(new CircleTransform()).into(viewHolder.imageView);


        //OnClick em cada item da lista de coletas
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });
    }

    @Override
    public int getItemCount() {
        return coletas.size();
    }

    public void atualiza(List<Coleta> coletas){
        this.coletas = coletas;
        this.notifyDataSetChanged();

    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewShortDesc,textViewRating,textViewTitle2;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewTitle2 = itemView.findViewById(R.id.textViewTitle2);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }
}

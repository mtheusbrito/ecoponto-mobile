package com.cooperativa.ideias.ascender.ecoponto.adapters;

import android.os.Build;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.cooperativa.ideias.ascender.ecoponto.R;
import com.cooperativa.ideias.ascender.ecoponto.models.Parceiro;

import java.util.List;

public class ParceiroAdapter extends RecyclerView.Adapter<ParceiroAdapter.ViewHolder> {
    private List<Parceiro> parceiros;
    private FragmentActivity activity;
    private RecyclerView recyclerView;

    public ParceiroAdapter(List<Parceiro> parceiros, FragmentActivity activity, RecyclerView recyclerView) {
        this.parceiros = parceiros;
        this.activity = activity;
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public ParceiroAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ParceiroAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.v2_adapter_parceiros, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ParceiroAdapter.ViewHolder viewHolder, int i) {
        try {

            final Parceiro parceiro = parceiros.get(i);
//            Picasso.get().load(parceiro.getUrl()).into(viewHolder.imageView);
            Glide.with(activity).load(parceiro.getUrl()).into(viewHolder.imageView);
        } catch (Exception e) {
            Log.e("PARCEIROS", e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return parceiros.size();

    }

    public void atualizar(List<Parceiro> parceiros) {
        this.parceiros = parceiros;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {


            super(itemView);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                itemView.setNestedScrollingEnabled(true);
            }
            imageView = itemView.findViewById(R.id.imageView);
            relativeLayout = itemView.findViewById(R.id.relative);
        }
    }
}

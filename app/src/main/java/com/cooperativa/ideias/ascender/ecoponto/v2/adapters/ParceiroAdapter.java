package com.cooperativa.ideias.ascender.ecoponto.v2.adapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.cooperativa.ideias.ascender.ecoponto.R;
import com.cooperativa.ideias.ascender.ecoponto.Utils.CircleTransform;
import com.cooperativa.ideias.ascender.ecoponto.v2.models.Parceiro;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ParceiroAdapter extends BaseAdapter {

    private  List<Parceiro> parceiros;
    private FragmentActivity activity;
    private LayoutInflater layoutInflater;
    private View view;

    public ParceiroAdapter(FragmentActivity activity,List<Parceiro> parceiros) {
        this.parceiros = parceiros;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return parceiros.size();
    }

    @Override
    public Object getItem(int position) {
        return parceiros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null){
            view = new View(activity);
            view = layoutInflater.inflate(R.layout.v2_adapter_parceiros, null);
            ImageView imageView = view.findViewById(R.id.imageView);

            try{
                Parceiro parceiro = parceiros.get(position);
                Picasso.get().load(parceiro.getUrl()).into(imageView);

            }catch (Exception e){
                Log.v("Error" , e.getMessage());
            }


        }

        return view;
    }

    public void atualiza(List<Parceiro> parceiros) {
        this.parceiros = parceiros;
        this.notifyDataSetChanged();
    }
}

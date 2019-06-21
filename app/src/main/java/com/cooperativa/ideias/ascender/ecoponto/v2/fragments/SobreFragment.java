package com.cooperativa.ideias.ascender.ecoponto.v2.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.cooperativa.ideias.ascender.ecoponto.R;
import com.cooperativa.ideias.ascender.ecoponto.v2.DAO.ConfiguracoesFirebase;
import com.cooperativa.ideias.ascender.ecoponto.v2.adapters.ParceiroAdapter;
import com.cooperativa.ideias.ascender.ecoponto.v2.models.Parceiro;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SobreFragment extends Fragment implements OnBackPressed{

    private TextView textViewCompartilhar;
    private GridView gridView;
    private List<Parceiro> parceiros;
    private ParceiroAdapter adapter;


    public static final String URL = "http://www.ascenderideias.com.br/";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.v2_sobre_fragment, container, false);

        initView(view);
        preencherLista();
        return view;
    }

    private void preencherLista() {
        Query databaseParceiros = ConfiguracoesFirebase.getParceiros();
        databaseParceiros.keepSynced(true);
        databaseParceiros.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try{
                    parceiros.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Parceiro parceiro = snapshot.getValue(Parceiro.class);

                        parceiros.add(parceiro);

                    }

                    adapter.atualiza(parceiros);
                    Log.v("PARCEIROS", parceiros.toString());

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

    }

    private void initView(View view) {
        parceiros = new ArrayList<>();
        gridView =  view.findViewById(R.id.gridView);
        adapter = new ParceiroAdapter( getActivity(), parceiros);
        gridView.setAdapter(adapter);
        textViewCompartilhar = view.findViewById(R.id.textCompartilhar);
        textViewCompartilhar.setOnClickListener(v -> compartilhar());




    }
    private void compartilhar(){
        Intent intentInvite = new Intent(Intent.ACTION_SEND);
        intentInvite.setType("text/plain");
        String body = "https://play.google.com/store/apps/details?id=com.cooperativa.ideias.ascender.ecoponto";
        String subject = "EcoPonto é um aplicativo para informar a população sobre onde destinar corretamente os resíduos que serão reciclados e/ou reutilizados. Facilitando a localização dos pontos de coleta na sua cidade.";
        intentInvite.putExtra(Intent.EXTRA_SUBJECT, subject);
        intentInvite.putExtra(Intent.EXTRA_TEXT, body);
        startActivity(Intent.createChooser(intentInvite, "Compartilhar usando"));
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

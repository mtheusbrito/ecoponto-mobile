package com.cooperativa.ideias.ascender.ecoponto;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cooperativa.ideias.ascender.ecoponto.R;

import java.text.BreakIterator;
import java.util.AbstractList;
import java.util.List;

import static com.google.android.gms.plus.PlusOneDummyView.TAG;

//Classe para gerenciar RecycleView...
public class ListaHorarioAdapter extends RecyclerView.Adapter<ListaHorarioAdapter.ProductViewHolder> {

    //variavel para inflar o layout...
    private Context mCtx;
    //variavel para armazenar todos os horarios na lista...
    private List <Listapcd> listapcdList;
    private Object adapterPosition;
    private int position;
    private View.OnClickListener clicklistener;


    public ListaHorarioAdapter(Main2Activity mCtx, List <Listapcd> listapcdList) {
        this.mCtx = (Context) mCtx;
        this.listapcdList = listapcdList;
    }

    //Classe para  representacao do recycleView e do RecycleViewHolder para representar dados a serem exibidos...
    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //inflando e devolvendo suporte de visao do layout Lis.xml...
        LayoutInflater inflater = LayoutInflater.from ( mCtx );
        View view = inflater.inflate ( R.layout.list, null );
        return new ProductViewHolder ( view );

    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {

        //obtendo a listapcd e sua posicao get.position...
        final Listapcd listapcd = listapcdList.get ( position );

        //vinculando os daados com o visor LayoutInflater mCtx...
        holder.textViewTitle.setText ( listapcd.getTitle ( ) );
        holder.textViewShortDesc.setText ( listapcd.getShortdesc ( ) );
        holder.textViewRating.setText ( String.valueOf ( listapcd.getRating ( ) ) );
        holder.textViewTitle2.setText ( listapcd.getTitle2 ( ) );



         // Metodo função para que o RecyleView se torne clickable...
        holder.linearLayout.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
               Toast.makeText (mCtx, "Clicado sobre" +listapcd.getTitle2 (), Toast.LENGTH_LONG).show ();

               // Log.d(TAG, "onClick: clicked on: " + listapcdList.get(position));

               // Toast.makeText(mCtx, (CharSequence) listapcdList.get(position), Toast.LENGTH_SHORT).show();

               // Intent intent = new Intent(mCtx, MapsActivityNsr.class);
               // intent.putExtra("image_url", mImages.get(position));
               // intent.putExtra("image_name", mImageNames.get(position));
               // mCtx.startActivity(intent);


            }
        } );



        holder.imageView.setImageDrawable ( mCtx.getResources ( ).getDrawable ( listapcd.getImage ( ) ) );
    }


    @Override
    public int getItemCount() {


        return listapcdList.size ( );


    }

    public Object getAdapterPosition() {
        return adapterPosition;
    }

    public int getPosition() {
        return position;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        //Variaveis para metodos de exibicao no RecycleView e CardView...
        TextView textViewTitle, textViewShortDesc, textViewRating, textViewTitle2;
        ImageView imageView;
        public BreakIterator url;//aquiiiiiiii
        public BreakIterator title;//aquiiiiiiii
        public BreakIterator description;//aquiiiiiiii

        // Paramatro para fazer com que o RecycleView se torne Clickable...
        public RelativeLayout linearLayout;

        public ProductViewHolder(final View itemView) {
            super ( itemView );


            textViewTitle = itemView.findViewById ( R.id.textViewTitle );
            textViewShortDesc = itemView.findViewById ( R.id.textViewShortDesc );
            textViewRating = itemView.findViewById ( R.id.textViewRating );
            textViewTitle2 = itemView.findViewById ( R.id.textViewTitle2 );
            imageView = itemView.findViewById ( R.id.imageView );
            // View para clickable...
            linearLayout = itemView.findViewById (R.id.linearLayout);

        }

    }
}

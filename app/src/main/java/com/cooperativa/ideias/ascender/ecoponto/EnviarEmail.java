package com.cooperativa.ideias.ascender.ecoponto;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cooperativa.ideias.ascender.ecoponto.R;

public class EnviarEmail extends AppCompatActivity {

    private EditText mEditTextTo;
    private EditText mEditTextSubject;
    private EditText mEditTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_enviar_email );

        mEditTextTo = (EditText) findViewById ( R.id.edit_text_to );
        mEditTextSubject = (EditText) findViewById ( R.id.edit_text_subject );
        mEditTextMessage = (EditText) findViewById ( R.id.edit_text_message );

        //Declação para inserção do Button na ActionBar para retornar...
        getSupportActionBar ( ).setDisplayHomeAsUpEnabled ( true ); //Mostrar o botão
        getSupportActionBar ( ).setHomeButtonEnabled ( true );      //Ativar o botão

        Button buttonSend = (Button) findViewById ( R.id.button_send );
        buttonSend.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                sendMail ( );
            }
        } );
    }


    private void sendMail() {
        String recipientList = mEditTextTo.getText ( ).toString ( );
        String[] recipients = recipientList.split ( "," );

        String message = mEditTextMessage.getText ( ).toString ( );
        String subject = mEditTextSubject.getText().toString();

        Intent emailIntent = new Intent ( Intent.ACTION_SENDTO );
        emailIntent.setData ( Uri.parse ( "mailto:" ) );
        String[] addresses = {"ascenderideias@gmail.com"};//Mascarando o digito usuario...
        emailIntent.putExtra(Intent.EXTRA_EMAIL, addresses);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        String title = getResources ( ).getString ( R.string.chooser_title );
        Intent chooser = Intent.createChooser ( emailIntent, title );
        if (emailIntent.resolveActivity ( getPackageManager ( ) ) != null) {
            startActivity ( chooser );
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId ( )) {
            case android.R.id.home:  //ID do  botão (gerado automaticamente pelo android...
                startActivity ( new Intent ( this, ProtejaOsEcoPontos.class ) );  //O efeito ao ser pressionado do botão (no caso abre a activity)
                finishAffinity ( );  //Método para matar a activity e não deixa-lá indexada na pilhagem
                break;
            default:
                break;
        }
        return true;
    }


}

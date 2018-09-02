package com.cooperativa.ideias.ascender.ecoponto;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.cooperativa.ideias.ascender.ecoponto.R;

public class VideoAssociacaoIFF extends AppCompatActivity {

    private WebView mWebView2;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_video_associacao_iff );

        mWebView2 = (WebView) findViewById(R.id.activity_main_webview);

        progressBar = (ProgressBar) findViewById(R.id.progressBar1);

        WebSettings webSettings = mWebView2.getSettings();
        webSettings.setJavaScriptEnabled(true);
        mWebView2.loadUrl("https://drive.google.com/file/d/1N1ZrGYZqx7UtO4PocNdsynjpwGZ88K95/view?usp=sharing");

        mWebView2.setWebViewClient( new OláCliente () );


    }

    class OláCliente extends WebViewClient{



        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url)
        {
            webView.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);

            progressBar.setVisibility(view.GONE);
        }

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    { //if back key processamento
        if((keyCode == KeyEvent.KEYCODE_BACK)&& mWebView2.canGoBack())
        {
            mWebView2.goBack();
            return true;

        }

        return super.onKeyDown(keyCode, event);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate O menu; Adicionando os itens ao menu da ActionBar...
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Acionar o item da barra de ação, clique aqui. A barra de ação
        // lida automaticamente com cliques no botão Home / Up, por tanto tempo
        // como você especifica uma atividade pai no AndroidManifest.xml
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement--Opções para menu Seleção Item
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                VideoAssociacaoIFF.this);

        // set Titulo
        alertDialogBuilder.setTitle("Sair");

        // set Diálogo de Mensagem
        alertDialogBuilder
                .setMessage("Você realmente deseja sair?")
                .setCancelable(false)
                .setPositiveButton("Sim",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if para botão finalizando atividade
                        // voltando para activity
                        VideoAssociacaoIFF.this.finish();
                    }
                })
                .setNegativeButton("Não",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if se para botão Não finalizando atividade...
                        // dialago e caixa para opção Não...
                        dialog.cancel();
                    }
                });

        // Criação da caixa de alerta diálago...
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show iv...
        alertDialog.show();
    }
}


package com.cooperativa.ideias.ascender.ecoponto;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.cooperativa.ideias.ascender.ecoponto.R;


import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.*;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class ProtejaOsEcoPontos extends AppCompatActivity {

    //Variveis para FloatingButtonActioMenu...
    private FloatingActionMenu fam;
    private FloatingActionButton fabcamera, fabchooseimage, fabemail, fabrelatorio;
    private static final int CAMERA_REQUEST = 123;
    private static final int PICK_FROM_GALLERY = 2;
    ImageView imageView;

    //Declações de variáveis para utilização FirebaseStorage...
    private static final String TAG = "UploadActivity";
    private ProgressDialog mProgressDialog;
    private Button mUploadobtn;

    //Variáveis para referências do Firebase...
    private Button buttonChoose;
    private Button buttonUpload;
    private TextView textViewShow;
    //firebase objects
    private StorageReference storageReference;
    private DatabaseReference mDatabase;
    private EditText imageName;
    //StorageReference storageReference;
    //constant to track image chooser intent
    private static final int PICK_IMAGE_REQUEST = 234;
    private TextView uploadId;


    //Variáriveis testes para novos metodos da camera....
    private Button btnChoose, btnUpload;
    private Uri filePath;

   private Context mContext;



    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_proteja_os_eco_pontos );


        // Declações de funcões para utilçização de Ids com Xml...
        fabemail = (FloatingActionButton) findViewById ( R.id.fab2 );
        fabrelatorio = (FloatingActionButton) findViewById ( R.id.fab3 );
        fabchooseimage = (FloatingActionButton) findViewById ( R.id.fab0 );
        fabcamera = (FloatingActionButton) findViewById ( R.id.fab1 );
        fam = (FloatingActionMenu) findViewById ( R.id.fab_menu );


        //Progress Dialog para utilização OnActivityResult ao FirebaseStorage...
        storageReference = FirebaseStorage.getInstance ( ).getReference ( );
        mProgressDialog = new ProgressDialog ( this );
        //Declações de instancias para Salvar imagens em firebase....
        imageView = (ImageView) findViewById ( R.id.uploadImage );
        // btnChoose = (Button) findViewById ( R.id.btnChoose );
        btnUpload = (Button) findViewById ( R.id.btnUpload );
        storageReference = FirebaseStorage.getInstance ( ).getReference ( );
        mDatabase = FirebaseDatabase.getInstance ( ).getReference ( Constants.DATABASE_PATH_UPLOADS );
        imageName = (EditText) findViewById(R.id.imageName);
          //Declação para inserção do Button na ActionBar para retornar...
        getSupportActionBar ( ).setDisplayHomeAsUpEnabled ( true ); //Mostrar o botão
        getSupportActionBar ( ).setHomeButtonEnabled ( true );      //Ativar o botão


        //Manipulação de mensagem ao "abrir e fechar menu" exibindo um Toast...
        fam.setOnMenuToggleListener ( new FloatingActionMenu.OnMenuToggleListener ( ) {
            @Override
            public void onMenuToggle(boolean opened) {
                if (opened) {
                    showToast ( "Menu aberto" );
                } else {
                    showToast ( "Menu fechado" );
                }
            }
        } );

        //Manipulando cada botão de ação do menu foatingActionButton...
        fabchooseimage.setOnClickListener ( onButtonClick ( ) );
        fabrelatorio.setOnClickListener ( onButtonClick ( ) );
        fabcamera.setOnClickListener ( onButtonClick ( ) );
        fabemail.setOnClickListener ( onButtonClick ( ) );

        fam.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                if (fam.isOpened ( )) {
                    fam.close ( true );
                }
            }
        } );
    }


    //Metodo para chamar ação e próxima atividade...
    private View.OnClickListener onButtonClick() {
        return new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                if (view == fabemail) {
                    Intent emailIntent = new Intent ( Intent.ACTION_SENDTO );
                    emailIntent.setData ( Uri.parse ( "mailto:" ) );
                    String[] addresses = {"ascenderideias@gmail.com"};
                    emailIntent.putExtra ( Intent.EXTRA_EMAIL, addresses );
                    emailIntent.putExtra ( Intent.EXTRA_SUBJECT, "Assunto das denúncias" );
                    emailIntent.putExtra ( Intent.EXTRA_TEXT, "Anexar imagem aqui da suas denúncias..." );
                    String title = getResources ( ).getString ( R.string.chooser_title );
                    Intent chooser = Intent.createChooser ( emailIntent, title );
                    if (emailIntent.resolveActivity ( getPackageManager ( ) ) != null) {
                        startActivity ( chooser );
                    }


                        //Botão FloatingActionBarMenu para enviar relatório...
                } else if (view == fabchooseimage) {
                    Intent intent = new Intent ( );
                    intent.setType ( "image/*" );
                    intent.setAction ( Intent.ACTION_GET_CONTENT );
                    startActivityForResult ( Intent.createChooser ( intent, "Select Picture" ), PICK_IMAGE_REQUEST );


                    //Botão FloatingActionBarMenu para usar camera nativa do android...
                } else if (view == fabcamera) {
                    Intent intent = new Intent ( MediaStore.ACTION_IMAGE_CAPTURE );
                    startActivityForResult ( intent, 0 );
                    fam.close ( true );

                } else if (view == fabrelatorio) {

                    //Botão FloatingActionBarMenu para enviar registo capturado pela Camera...
                    Intent intent =  new Intent(ProtejaOsEcoPontos.this, EnviarEmail.class);
                    startActivity ( intent );
                }
            }
        };
    }

    //Metodo void para exibir mensagem Toast ao menu FloatingActionBar...
    private void showToast(String msg) {
        Toast.makeText ( this, msg, Toast.LENGTH_SHORT ).show ( );





//Inicio Metodos, instancias, referências Firebase Upload Imagens...
        //btnChoose.setOnClickListener ( new View.OnClickListener ( ) {
        // @Override
        //public void onClick(View v) {
        // chooseImage ( );
        //}
        //} );


        btnUpload.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                uploadImage ( );
            }
        } );
    }

    //private void chooseImage() {
    //Intent intent = new Intent();
    //intent.setType("image/*");
    //intent.setAction(Intent.ACTION_GET_CONTENT);
    //startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    //}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult ( requestCode, resultCode, data );



        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData ( ) != null) {
            filePath = data.getData ( );
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap ( getContentResolver ( ), filePath );
                imageView.setImageBitmap ( bitmap );

            } catch (IOException e) {
                e.printStackTrace ( );
            }


        }
    }

    public String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver ( );
        MimeTypeMap mime = MimeTypeMap.getSingleton ( );
        return mime.getExtensionFromMimeType ( cR.getType ( uri ) );
    }

    private void uploadImage() {

        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog ( this );
            progressDialog.setTitle ( "Uploading..." );
            progressDialog.show ( );


            // obtendo a referência de armazenamento
            StorageReference sRef = storageReference.child ( Constants.STORAGE_PATH_UPLOADS + System.currentTimeMillis ( ) + "." + getFileExtension ( filePath ) );

            // adicionando o arquivo a referência
            sRef.putFile ( filePath )
                    .addOnSuccessListener ( new OnSuccessListener <UploadTask.TaskSnapshot> ( ) {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss ( );
                            //Toast.makeText ( ProtejaOsEcoPontos.this, "Uploaded", Toast.LENGTH_SHORT ).show ( );
                            //displaying success toast
                           Toast.makeText ( getApplicationContext ( ), "Imagem carregada... ", Toast.LENGTH_LONG ).show ( );

                            //creating the upload object to store uploaded image details
                            Upload upload = new Upload ( imageName.getText ( ).toString ( ).trim ( ), taskSnapshot.getTask ( ).toString ( ) );
                           // adicionar um upload ao banco de dados firebase
                            String uploadId = mDatabase.push().getKey ( );
                            mDatabase.child(uploadId).setValue(upload);
                        }
                    } )
                    .addOnFailureListener ( new OnFailureListener ( ) {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss ( );
                            Toast.makeText ( getApplicationContext ( ), exception.getMessage ( ), Toast.LENGTH_LONG ).show ( );
                        }
                    } )
                    .addOnProgressListener ( new OnProgressListener <UploadTask.TaskSnapshot> ( ) {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred ( ) / taskSnapshot
                                    .getTotalByteCount ( ));
                            progressDialog.setMessage ( "Uploaded " + (int) progress + "%..." );
                        }
                    } );

        } else {

            // exibir um erro se nenhum arquivo estiver selecionado
           Toast.makeText ( getApplicationContext ( ),"Por favor selecione" , Toast.LENGTH_LONG ).show ( );

        }


    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId ( )) {
            case android.R.id.home:  //ID do  botão (gerado automaticamente pelo android...
                startActivity ( new Intent ( this, MainActivity.class ) );  //O efeito ao ser pressionado do botão (no caso abre a activity)
                finishAffinity ( );  //Método para matar a activity e não deixa-lá indexada na pilhagem
                break;
            default:
                break;
        }
        return true;
    }




}


















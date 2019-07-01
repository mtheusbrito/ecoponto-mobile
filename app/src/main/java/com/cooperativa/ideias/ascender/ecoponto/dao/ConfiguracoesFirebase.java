package com.cooperativa.ideias.ascender.ecoponto.dao;

import com.cooperativa.ideias.ascender.ecoponto.utils.ConstantsUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ConfiguracoesFirebase  {


    private static DatabaseReference referenceFirebase;
    private static StorageReference storageReference;
    private static FirebaseAuth auth;


    public static DatabaseReference getFirebase(){

        if(referenceFirebase == null){
            referenceFirebase = FirebaseDatabase.getInstance().getReference();

        }
        return  referenceFirebase;


    }
    public  static StorageReference getStorage(){
        if(storageReference == null){
            storageReference = FirebaseStorage.getInstance().getReference();
        }
        return storageReference;
    }


    public static  FirebaseAuth getFirebaseAutenticacao(){

        if(auth == null ){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }


    //consulta de dados

    public static Query getPontos() {
        return FirebaseDatabase.getInstance().getReference(ConstantsUtils.BANCO_PONTOS);

    }

    public static Query getDias(String id) {
        return FirebaseDatabase.getInstance().getReference(ConstantsUtils.BANCO_PONTOS).child(id).child(ConstantsUtils.DIAS);
    }

    public static Query getParceiros() {
        return FirebaseDatabase.getInstance().getReference(ConstantsUtils.BANCO_PARCEIROS);
    }
}

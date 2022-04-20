package com.example.fatakat.Data.Api;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Firestore {
    CollectionReference db ;
    public Firestore(String databaseName){
        db = FirebaseFirestore.getInstance().collection(databaseName);
    }
    public Task createNewDocument(String documentId, HashMap<String,Object> data){
        return db.document(documentId).set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) Log.e("check","Firestore:Success");
                else Log.e("check","Firestore:Fail");
            }
        });
    }

    public Task updateDocument(String documentId, HashMap<String,Object> data){
        return db.document(documentId).update(data);
    }

    public Task<DocumentSnapshot> getDocument(String documentId){
        return db.document(documentId).get();
    }

    public Task deleteDocumentFeature(String documentId,HashMap<String,Object> data){
        return db.document(documentId).set(data);
    }
}

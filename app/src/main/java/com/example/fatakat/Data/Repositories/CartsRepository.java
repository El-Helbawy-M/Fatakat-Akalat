package com.example.fatakat.Data.Repositories;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.fatakat.Data.Api.Firestore;
import com.example.fatakat.Data.Models.AppUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.HashMap;
import java.util.Map;

public class CartsRepository {
    Firestore db = new Firestore("Carts");
    AppUser user;
    public CartsRepository(AppUser user){this.user = user;}
    public Task addNewCart(){
        return db.createNewDocument(user.email,new HashMap<String,Object>());
    }
    public Task addNewItemToCart(String itemName,HashMap<String,Object> data){
        return db.getDocument(user.email).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                HashMap<String, Object> itemNumber = (HashMap<String, Object>) task.getResult().get(itemName);

                if(itemNumber!=null) {
                    Long savedItemNumber = (Long) itemNumber.get("Item Number");
                    double savedTotalPrice = (double) itemNumber.get("Total");
                    ((HashMap<String, Object>) data.get(itemName)).put("Item Number",(Integer) ((HashMap<String, Object>) data.get(itemName)).get("Item Number") + savedItemNumber);
                    ((HashMap<String, Object>) data.get(itemName)).put("Total",(double) ((HashMap<String, Object>) data.get(itemName)).get("Total") + savedTotalPrice);
                }
                db.updateDocument(user.email,data);
            }
        });

    }
    public Task<DocumentSnapshot> getCart(){
        return db.getDocument(user.email);
    }
    public Task removeCartItem(HashMap<String,Object> data){return db.deleteDocumentFeature(user.email,data);}
}

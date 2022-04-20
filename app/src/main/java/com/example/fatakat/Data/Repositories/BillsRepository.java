package com.example.fatakat.Data.Repositories;

import com.example.fatakat.Data.Api.Firestore;
import com.example.fatakat.Data.Models.AppUser;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;

public class BillsRepository {
    Firestore db = new Firestore("Bills");
    AppUser user;
    public BillsRepository(AppUser user){this.user = user;}

    public Task addNewBillUser(){
        return db.createNewDocument(user.email,new HashMap<String,Object>());
    }
}

package com.example.fatakat.Core.Services;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.fatakat.Data.Api.Firestore;
import com.example.fatakat.Data.Models.AppUser;
import com.example.fatakat.Data.Repositories.BillsRepository;
import com.example.fatakat.Data.Repositories.CartsRepository;
import com.example.fatakat.Layout.Activites.HomeActivity;
import com.example.fatakat.Layout.Activites.RegisterActivity;
import com.example.fatakat.Layout.Fragments.SigninFragment;
import com.example.fatakat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

import java.io.Serializable;

public class Auther {
    FirebaseAuth auther = FirebaseAuth.getInstance();
    Context context;
    public Auther(Context context){this.context = context;}
    public Task register(String name,String email, String password) {
        return auther.createUserWithEmailAndPassword(email,password).addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("check", "createUserWithEmail:success");
                    FirebaseUser user = auther.getCurrentUser();
                    AppUser appUser =new AppUser(name,email);
                    new Firestore("Users").createNewDocument(appUser.email,appUser.getDataiInMap());
                    new CartsRepository(appUser).addNewCart();
                    new BillsRepository(appUser).addNewBillUser();
                    Intent intent = new Intent(context, HomeActivity.class);
                    intent.putExtra("User",(Serializable) user);
                    context.startActivity(intent);

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("check", "createUserWithEmail:failure", task.getException());
                    Toast.makeText(context, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
//                    updateUI(null);
                }
            }
        });
    }
    public Task signIn(String email,String password){
        return auther.signInWithEmailAndPassword(email,password).addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("check", "signInWithEmailAndPassword:success");
                    FirebaseUser user = auther.getCurrentUser();
                    new Firestore("Users").getDocument(user.getEmail()).addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            AppUser userData = new AppUser(task.getResult().get("Name").toString(),user.getEmail());
                            Intent intent = new Intent(context, HomeActivity.class);
                            intent.putExtra("User",(Serializable) userData);
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        }
                    });

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("check", "signInWithEmailAndPassword:failure", task.getException());
                    Toast.makeText(context, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();


//                    updateUI(null);
                }
            }
        });
    }
}

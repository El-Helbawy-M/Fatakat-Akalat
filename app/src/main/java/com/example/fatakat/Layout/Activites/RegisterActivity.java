package com.example.fatakat.Layout.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.fatakat.Layout.Fragments.RegisterFragment;
import com.example.fatakat.R;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_register_activity);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new RegisterFragment()).commit();

    }


}
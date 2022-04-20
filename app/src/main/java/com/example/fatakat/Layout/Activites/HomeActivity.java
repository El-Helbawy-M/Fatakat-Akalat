package com.example.fatakat.Layout.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.fatakat.Data.Models.AppUser;
import com.example.fatakat.Layout.Fragments.CartFragment;
import com.example.fatakat.Layout.Fragments.HomeFragment;
import com.example.fatakat.Layout.Fragments.ProfileFragment;
import com.example.fatakat.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public AppUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_activity_home);
        Intent intent = getIntent();
        user = (AppUser) intent.getSerializableExtra("User");
        getSupportFragmentManager().beginTransaction().replace(R.id.ScreenContent, new HomeFragment(user)).commit();
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.homeFragment);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.homeFragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.ScreenContent, new HomeFragment(user)).commit();
                return true;

            case R.id.cartFragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.ScreenContent, new CartFragment(user)).commit();
                return true;

            case R.id.profileFragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.ScreenContent, new ProfileFragment(user)).commit();
                return true;
        }
        return false;
    }
}
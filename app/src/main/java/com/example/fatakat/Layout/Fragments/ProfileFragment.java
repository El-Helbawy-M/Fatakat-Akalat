package com.example.fatakat.Layout.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fatakat.Data.Models.AppUser;
import com.example.fatakat.Layout.Activites.RegisterActivity;
import com.example.fatakat.R;


public class ProfileFragment extends Fragment {

    AppUser appUser;
    public ProfileFragment(AppUser appUser){
        this.appUser = appUser;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f_fragment_profile, container, false);
        TextView name = (TextView) view.findViewById(R.id.Name),email = (TextView) view.findViewById(R.id.Email);
        name.setText(appUser.name);
        email.setText(appUser.email);
        LinearLayout signOutButton = (LinearLayout) view.findViewById(R.id.SignOut);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }
}
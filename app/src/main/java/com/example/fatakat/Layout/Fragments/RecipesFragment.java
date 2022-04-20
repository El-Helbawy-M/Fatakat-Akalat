package com.example.fatakat.Layout.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fatakat.Data.Models.AppUser;
import com.example.fatakat.Data.Models.Meal;
import com.example.fatakat.Layout.Adapters.MealsAdapter;
import com.example.fatakat.R;

import java.util.ArrayList;


public class RecipesFragment extends Fragment {
    Context context;
    AppUser user;
    ArrayList<Meal> meals;
    public RecipesFragment(Context parentActivity, AppUser appUser, ArrayList<Meal> meals){
        this.context = parentActivity;
        this.user = appUser;
        this.meals = meals;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.f_fragment_recipes, container, false);
        RecyclerView mealsView = (RecyclerView) view.findViewById(R.id.meals);
        mealsView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mealsView.setAdapter(new MealsAdapter(meals,context,user));
        return view;
    }
}
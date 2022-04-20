package com.example.fatakat.Layout.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fatakat.Data.Models.AppUser;
import com.example.fatakat.Data.Models.Category;
import com.example.fatakat.Data.Models.Meal;

import com.example.fatakat.Data.Repositories.RecipesRepository;
import com.example.fatakat.Layout.Activites.HomeActivity;
import com.example.fatakat.Layout.Adapters.CategoryAdabter;
import com.example.fatakat.Layout.Adapters.MealsAdapter;
import com.example.fatakat.R;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private Object[][] categoriesIcons = {
            {"Hamburger",R.drawable.hamburger},
            {"Pizza",R.drawable.pizza},
            {"Fried",R.drawable.fried},
            {"Taco",R.drawable.taco},
            {"Soda",R.drawable.soda},
    };
    ArrayList<Meal> meals = new ArrayList<Meal>();
    AppUser user;
    private static HomeFragment instance;
    private static View view;
    public HomeFragment(AppUser user){this.user = user; instance=this;}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.f_fragment_home, container, false);
        ArrayList<Category> categories = new ArrayList<Category>();
        for(int x = 0;x < categoriesIcons.length;x++){

            categories.add(x,new Category((String) categoriesIcons[x][0],(int) categoriesIcons[x][1]));

        }
        RecyclerView categoriesView = (RecyclerView) view.findViewById(R.id.categories);
        categoriesView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false));
        categoriesView.setAdapter(new CategoryAdabter(categories,getActivity(),user,this,view));
        getChildFragmentManager().beginTransaction().replace(R.id.container,new NoDataFragment()).commit();
        new RecipesRepository(getActivity(),user).execute("hamburger");
        return view;
    }

    public static HomeFragment getInstance() {return instance;}
    public static View getTheView() {return view;}
}
package com.example.fatakat.Layout.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.fatakat.Data.Models.AppUser;
import com.example.fatakat.Data.Models.CartItem;
import com.example.fatakat.Data.Models.Meal;
import com.example.fatakat.Data.Repositories.CartsRepository;
import com.example.fatakat.Layout.Adapters.CartItemsAdapter;
import com.example.fatakat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartFragment extends Fragment {
    AppUser appUser;
    ArrayList<CartItem> meals = new ArrayList<CartItem>();
    HashMap<String,Object> data;
    public CartFragment(AppUser user){this.appUser = user;}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.f_fragment_cart, container, false);
        getData().addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.cartItems);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(new CartItemsAdapter(getActivity(),meals,recyclerView,data,appUser,(TextView) view.findViewById(R.id.Total)));
                countTotal(view);
                Button payButton = (Button) view.findViewById(R.id.payButton);
                View fragmentView = view;
                payButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new CartsRepository(appUser).removeCartItem(new HashMap<String,Object>()).addOnCompleteListener(new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                data.clear();
                                meals.clear();
                                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                recyclerView.setAdapter(new CartItemsAdapter(getActivity(), meals, recyclerView, data, appUser,(TextView) fragmentView.findViewById(R.id.Total)));
                                countTotal(fragmentView);
                            }
                        });
                    }
                });
            }
        });

        return view;
    }




    private void countTotal(View view){
        double total = 0;
        for(int x = 0 ; x < data.size();x++){
            total += (double) ((HashMap<String,Object>) data.get(data.keySet().toArray()[x])).get("Total");
        }
        TextView totalText = (TextView) view.findViewById(R.id.Total);
        totalText.setText(total+"");
    }


    private Task getData(){
        return new CartsRepository(appUser).getCart().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                data = (HashMap<String, Object>) task.getResult().getData();
                if(! data.isEmpty()){
                    for(int x = 0  ;x < data.size();x++){
                        Map<String,Object> mealData = (Map<String, Object>) data.get(data.keySet().toArray()[x]);
                        meals.add(new CartItem(mealData.get("Title").toString(),mealData.get("Image").toString(),(Long) mealData.get("Item Number"),(double) mealData.get("Price For One")));
                    }
                }
            }
        });
    }
}
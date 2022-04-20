package com.example.fatakat.Layout.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import static java.util.Map.entry;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fatakat.Data.Models.AppUser;
import com.example.fatakat.Data.Models.Meal;
import com.example.fatakat.Data.Repositories.CartsRepository;
import com.example.fatakat.Layout.Adapters.IngredientAdapter;
import com.example.fatakat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MealDiscrabtionActivity extends AppCompatActivity {
    private Meal meal;
    private AppUser user;
    private int itemNumber = 1;
    Map<String,Integer> images = Map.ofEntries(
            entry("beet",R.drawable.beet),
            entry("bell Pepper",R.drawable.bellpepper),
            entry("cabbage",R.drawable.cabbage),
            entry("carrot",R.drawable.carrot),
            entry("cucumber",R.drawable.cucumber),
            entry("eggplant",R.drawable.eggplant),
            entry("garlic",R.drawable.garlic),
            entry("lemon",R.drawable.lemon),
            entry("lettuce",R.drawable.lettuce),
            entry("mushroom",R.drawable.mushroom),
            entry("onion",R.drawable.onion),
            entry("peas",R.drawable.peas),
            entry("potato",R.drawable.potato),
            entry("radish",R.drawable.radish),
            entry("tomato",R.drawable.tomato),
            entry("salt",R.drawable.salt),
            entry("water",R.drawable.water),
            entry("sugar",R.drawable.sugar),
            entry("flour",R.drawable.flour)
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_activity_meal_discrabtion);
        Intent intent = getIntent();
        meal = (Meal) intent.getSerializableExtra("Meal");
        user = (AppUser) intent.getSerializableExtra("User");
        setUi();
        ArrayList<Integer> images = setIngredients();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.IngredientsIcons);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new IngredientAdapter(images));
    }
    private ArrayList<Integer> setIngredients(){
        ArrayList<Integer> data = new ArrayList<Integer>();
        for(int x = 0; x < meal.ingredients.length;x++){
            String cash = meal.ingredients[x];
            String[] words = cash.split(" ");
            for(int i = 0 ; i < words.length;i++){
                if(images.containsKey(words[i])){
                    data.add(images.get(words[i]));
                }
            }
        }
        return data;
    }

    private void setUi(){
        TextView title , descrabtion ,price;
        ImageView imageView = findViewById(R.id.MealImage);
        Glide.with(this).load(meal.imagePath).into(imageView);
        title = findViewById(R.id.MealTitle);
        descrabtion = findViewById(R.id.MealDescription);
        price = findViewById(R.id.price);
        title.setText(meal.title);
        price.setText(""+meal.price);
        descrabtion.setText(meal.descrabtion);
        Button addButton = (Button) findViewById(R.id.addButton),subButton = (Button) findViewById(R.id.subButton),addItemButton = (Button) findViewById(R.id.addItemButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemNumber++;
                TextView text = (TextView) findViewById(R.id.itemNumber);
                text.setText(""+itemNumber);

            }
        });
        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemNumber > 1){
                    itemNumber--;
                    TextView text = (TextView) findViewById(R.id.itemNumber);
                    text.setText(""+itemNumber);
                }
            }
        });
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String,Object> data = new HashMap<String,Object>(),subData = new HashMap<String,Object>();
                subData.put("Title",meal.title);
                subData.put("Item Number",itemNumber);
                subData.put("Price For One",meal.price);
                subData.put("Total",meal.price * itemNumber);
                subData.put("Image",meal.imagePath);
                data.put(meal.title,subData);
                new CartsRepository(user).addNewItemToCart(meal.title,data).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        Toast.makeText(MealDiscrabtionActivity.this, itemNumber+" items of "+meal.title+" have been added to the cart",
                                Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }
}
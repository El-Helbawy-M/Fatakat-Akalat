package com.example.fatakat.Data.Repositories;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fatakat.Data.Api.Spoonacular;
import com.example.fatakat.Data.Models.AppUser;
import com.example.fatakat.Data.Models.Good;
import com.example.fatakat.Data.Models.Meal;
import com.example.fatakat.Data.Models.Result;
import com.example.fatakat.Layout.Adapters.MealsAdapter;
import com.example.fatakat.Layout.Fragments.HomeFragment;
import com.example.fatakat.Layout.Fragments.NoDataFragment;
import com.example.fatakat.Layout.Fragments.RecipesFragment;
import com.example.fatakat.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecipesRepository extends AsyncTask<String,Void, String> {
    private Context context;
    private AppUser user;
    ArrayList<Meal> meals = new ArrayList<Meal>();
    public RecipesRepository(Context context,AppUser user){
        this.context = context;
        this.user = user;
    }

    @Override
    protected String doInBackground(String... objects) {
        getMealsOf(objects[0]);
        return objects[0];
    }

    public void getMealsOf(String query) {
        Log.e("check",query);
        Result response = new Spoonacular().getRecipes(query);
        if(response.type == Result.ResultType.GOOD){try {
            JSONObject data = new JSONObject(((Good<String>) response).data);
            JSONArray mealsList = data.getJSONArray("results");
            meals.clear();
            for (int x = 0; x < mealsList.length(); x++) {
                JSONObject meal = mealsList.getJSONObject(x);
                JSONArray ingredientsData = meal.getJSONArray("extendedIngredients");
                String[] ingredients = new String[ingredientsData.length()];
                for(int i= 0; i < ingredientsData.length();i++){
                    JSONObject ingredient = ingredientsData.getJSONObject(i);
                    ingredients[i] = ingredient.getString("originalName");
                }
                meals.add(new Meal(meal.getString("title"), meal.getString("summary"), meal.getString("image"),ingredients,meal.getDouble("pricePerServing")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }}
        else meals.clear();

    }
    @Override
    protected void onPostExecute(String query) {
        if(!meals.isEmpty()) {
            Log.e("check",meals.toString());
            HomeFragment.getInstance().getChildFragmentManager().beginTransaction().replace(R.id.container,new RecipesFragment(context,user,meals)).commit();
        }
        else HomeFragment.getInstance().getChildFragmentManager().beginTransaction().replace(R.id.container,new NoDataFragment()).commit();
        super.onPostExecute(query);
    }
}


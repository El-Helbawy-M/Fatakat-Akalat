package com.example.fatakat.Layout.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fatakat.Data.Models.AppUser;
import com.example.fatakat.Data.Models.Meal;
import com.example.fatakat.Layout.Activites.MealDiscrabtionActivity;
import com.example.fatakat.R;

import java.io.Serializable;
import java.util.ArrayList;

public class MealsAdapter extends RecyclerView.Adapter<MealsAdapter.MealViewHolder> {
    ArrayList<Meal> meals = new ArrayList<Meal>();
    final Context context;
    final AppUser user;
    public MealsAdapter(ArrayList<Meal> meals,Context context,AppUser user){
        this.user = user;
        this.meals = meals;
        this.context = context;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_meal_item,null,false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, final int position) {
        Meal meal = this.meals.get(position);
        holder.setData(meal);
        Glide.with(this.context).load(meal.imagePath).into(holder.image);
        holder.field.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MealDiscrabtionActivity.class);
                intent.putExtra("Meal",(Serializable) meals.get(position));
                intent.putExtra("User",(Serializable) user);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.meals.size();
    }

    class MealViewHolder extends RecyclerView.ViewHolder{
        TextView title,descrabtion;
        ImageView image;
        LinearLayout field;
        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            descrabtion = (TextView) itemView.findViewById(R.id.descrabtion);
            image = (ImageView) itemView.findViewById(R.id.image);
            field = (LinearLayout) itemView.findViewById(R.id.MealItem);
        }
        public void setData(Meal meal){
            title.setText(meal.title);
            meal.descrabtion = parseHTML(meal.descrabtion);
            descrabtion.setText(meal.descrabtion);
        }

        private String parseHTML(String src){
            src = src.replaceAll("<b>"," ");
            src = src.replaceAll("</b>"," ");
            src = src.replaceAll("</a>"," ");
            src = src.replaceAll("<a href="," ");
            return src;
        }
    }
}

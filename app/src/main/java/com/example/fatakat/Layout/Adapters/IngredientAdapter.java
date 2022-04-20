package com.example.fatakat.Layout.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fatakat.R;

import java.util.ArrayList;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {
    ArrayList<Integer> images ;

    public IngredientAdapter(ArrayList<Integer> images){
        this.images = images;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_ingredient_item,null,false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        holder.ingredientImage.setImageResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return this.images.size();
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder{
        ImageView ingredientImage;
        public IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientImage = (ImageView) itemView.findViewById(R.id.icon);
        }
    }
}

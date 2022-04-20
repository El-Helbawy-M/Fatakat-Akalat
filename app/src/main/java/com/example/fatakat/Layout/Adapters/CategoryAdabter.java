package com.example.fatakat.Layout.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fatakat.Data.Models.AppUser;
import com.example.fatakat.Data.Models.Category;
import com.example.fatakat.Data.Repositories.RecipesRepository;
import com.example.fatakat.R;

import java.util.ArrayList;

public class CategoryAdabter extends RecyclerView.Adapter<CategoryAdabter.CategoryHolder> {
    ArrayList<Category> categories;
    private Context context;
    private AppUser user;
    Fragment fragment;
    View view;
    public CategoryAdabter(ArrayList<Category> categories,Context context ,AppUser user,Fragment fragment,View view){this.user = user;this.categories = categories;this.context = context;this.fragment = fragment;this.view = view;}


    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_category_item,null,false);
        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        final Category category = categories.get(position);
        holder.name.setText(category.name);
        holder.categoryImage.setImageResource(category.image);
        holder.categoryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new RecipesRepository(context,user).execute(category.name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageButton categoryImage;
        public CategoryHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.categoryName);
            categoryImage = (ImageButton) itemView.findViewById(R.id.categoryImage);
        }
    }


}

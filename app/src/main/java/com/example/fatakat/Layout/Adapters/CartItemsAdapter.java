package com.example.fatakat.Layout.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fatakat.Data.Models.AppUser;
import com.example.fatakat.Data.Models.CartItem;
import com.example.fatakat.Data.Repositories.CartsRepository;
import com.example.fatakat.R;

import java.util.ArrayList;
import java.util.HashMap;

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.CartItemViewHolder> {
    ArrayList<CartItem> items;
    Context context;
    RecyclerView recyclerView;
    HashMap<String,Object> data;
    AppUser appUser;
    TextView total;
    public CartItemsAdapter(Context context,ArrayList<CartItem> items,RecyclerView recyclerView,HashMap<String,Object> data,AppUser user,TextView total){
        this.items = items;
        this.context = context;
        this.recyclerView = recyclerView;
        this.data = data;
        this.appUser = user;
        this.total = total;
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_cart_item,null,false);
        return new CartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {
        CartItem item = items.get(position);
        Glide.with(context).load(item.image).into(holder.image);
        holder.title.setText(item.title);
        holder.itemNumber.setText(item.itemNumber+"X");
        holder.price.setText(item.price+"");
        holder.settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context,holder.settingButton);
                popupMenu.getMenuInflater().inflate(R.menu.cart_item_setting_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                            data.remove(items.get(position).title);
                            items.remove(position);
                            recyclerView.setLayoutManager(new LinearLayoutManager(context));
                            recyclerView.setAdapter(new CartItemsAdapter(context,items,recyclerView,data,appUser,total));
                            new CartsRepository(appUser).removeCartItem(data);
                            countTotal(view);
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    private void countTotal(View view){
        double totalVal = 0;
        for(int x = 0 ; x < data.size();x++){
            totalVal += (double) ((HashMap<String,Object>) data.get(data.keySet().toArray()[x])).get("Total");
        }
        total.setText(totalVal+"");
    }

    public class CartItemViewHolder extends RecyclerView.ViewHolder{
        ImageButton settingButton;
        ImageView image;
        TextView title,itemNumber,price;
        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            settingButton = (ImageButton) itemView.findViewById(R.id.settingButton);
            image = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            itemNumber = (TextView) itemView.findViewById(R.id.itemNumber);
            price = (TextView) itemView.findViewById(R.id.price);
        }
    }
}

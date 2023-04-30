package com.example.rationedapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.rationedapp.dao.ShoppingListDao;
import com.example.rationedapp.database.ShoppingListDatabase;
import com.example.rationedapp.databinding.ShoppingListRvLayoutBinding;
import com.example.rationedapp.entity.PantryItem;

import java.util.List;

public class ShoppingListRecyclerViewAdapter extends RecyclerView.Adapter
        <ShoppingListRecyclerViewAdapter.ViewHolder> {
    private static List<PantryItem> pantryItems;
    private final OnItemClickListener listener;

    public ShoppingListRecyclerViewAdapter(List<PantryItem> pantryItems, OnItemClickListener listener) {
        this.pantryItems = pantryItems;
        this.listener = listener;
    }
    //creates a new viewholder that is constructed with a new View, inflated from a layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                             int viewType) {
        ShoppingListRvLayoutBinding binding=
                ShoppingListRvLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }
    // this method binds the view holder created with data that will be displayed
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        PantryItem item = pantryItems.get(position);
        viewHolder.binding.groceryNameText.setText(item.getName());
        viewHolder.binding.groceryCategoryText.setText(item.getCategory());
        viewHolder.bind(pantryItems.get(position), listener);
    }
    @Override
    public int getItemCount() {
        return pantryItems.size();
    }
    public void addPantryItems(List<PantryItem> results) {
        pantryItems = results;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ShoppingListRvLayoutBinding binding;
        public TextView name;
        public ViewHolder(ShoppingListRvLayoutBinding binding){
            super(binding.getRoot());
            this.binding = binding;
            TextView name = binding.groceryNameText;
            this.name = name;
        }

        public void bind(final PantryItem item, final OnItemClickListener listener) {
            name.setText(item.name);
            binding.ivItemDelete.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(PantryItem item);
    }
}
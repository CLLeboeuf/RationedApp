package com.example.rationedapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rationedapp.databinding.PantryRvLayoutBinding;
import com.example.rationedapp.entity.PantryItem;

import java.util.List;

public class PantryRecyclerViewAdapter extends RecyclerView.Adapter
        <PantryRecyclerViewAdapter.ViewHolder> {
    private static List<PantryItem> pantryItems;
    private final OnItemClickListener listener;

    public PantryRecyclerViewAdapter(List<PantryItem> pantryItems, OnItemClickListener listener) {
        this.pantryItems = pantryItems;
        this.listener = listener;
    }
    //creates a new viewholder that is constructed with a new View, inflated from a layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                             int viewType) {
        PantryRvLayoutBinding binding=
                PantryRvLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }
    // this method binds the view holder created with data that will be displayed
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        PantryItem item = pantryItems.get(position);
        viewHolder.binding.pantryItemNameText.setText(item.getName());
        viewHolder.binding.pantryItemCategoryText.setText(item.getCategory());
        viewHolder.binding.measurementTypeGet.setText(item.getMeasurementType());
        viewHolder.binding.measurementAmountGet.setText(Double.toString(item.getMeasurementAmount()));
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
        private PantryRvLayoutBinding binding;
        public ViewHolder(PantryRvLayoutBinding binding){
            super(binding.getRoot());
            this.binding = binding;


            binding.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //String position =String.valueOf(getAbsoluteAdapterPosition());
                    PantryItem pi= pantryItems.get(getAbsoluteAdapterPosition());
                    String details= pi.getName()+ " " + pi.getCategory();
                    //Log.i("position ",position);
                    Log.i("details: ",details);
                }
            });
        }

        public void bind(final PantryItem item, final OnItemClickListener listener) {
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
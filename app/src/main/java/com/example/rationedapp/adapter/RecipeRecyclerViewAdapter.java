package com.example.rationedapp.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.rationedapp.databinding.RecipeRvLayoutBinding;

import com.example.rationedapp.entity.Recipe;

import java.util.List;

public class RecipeRecyclerViewAdapter extends RecyclerView.Adapter
        <RecipeRecyclerViewAdapter.ViewHolder> {
    private static List<Recipe> recipes;
    private final OnItemClickListener listener;

    public RecipeRecyclerViewAdapter(List<Recipe> recipes, OnItemClickListener listener) {
        this.recipes = recipes;
        this.listener = listener;
    }

    //creates a new viewholder that is constructed with a new View, inflated from a layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {
        RecipeRvLayoutBinding binding =
                RecipeRvLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    // this method binds the view holder created with data that will be displayed
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Recipe item = recipes.get(position);
        viewHolder.binding.recipeNameTitle.setText(item.getName());
        viewHolder.binding.recipeDescriptionText.setText(item.getDescription());
        viewHolder.bind(recipes.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void addrecipes(List<Recipe> results) {
        recipes = results;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private RecipeRvLayoutBinding binding;

        public ViewHolder(RecipeRvLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final Recipe item, final RecipeRecyclerViewAdapter.OnItemClickListener listener) {
            binding.recipeDeleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(Recipe item);
    }
}
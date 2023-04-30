package com.example.rationedapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rationedapp.adapter.RecipeRecyclerViewAdapter;
import com.example.rationedapp.adapter.ShoppingListRecyclerViewAdapter;
import com.example.rationedapp.databinding.RecipeBookFragmentBinding;
import com.example.rationedapp.databinding.SearchRecipeLayoutBinding;
import com.example.rationedapp.databinding.ShoppingListFragmentBinding;
import com.example.rationedapp.entity.PantryItem;
import com.example.rationedapp.entity.Recipe;
import com.example.rationedapp.viewmodel.RecipeViewModel;
import com.example.rationedapp.viewmodel.ShoppingListViewModel;

import java.util.ArrayList;
import java.util.List;


public class RecipeBookFragment extends Fragment {

    private RecyclerView.LayoutManager layoutManager;
    private RecipeBookFragmentBinding binding;
    public List<Recipe> recipes;
    private RecipeRecyclerViewAdapter adapter;

    public RecipeBookFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = RecipeBookFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        recipes = new ArrayList<Recipe>();
        RecipeViewModel viewModel = new ViewModelProvider(requireActivity()).get(RecipeViewModel.class);
        viewModel.getAllRecipes().observe(getViewLifecycleOwner(), new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable final List<Recipe> recipes) {
                if (recipes != null) {
                    setListData(recipes);
                }
            }
        });

        adapter = new RecipeRecyclerViewAdapter(recipes, new RecipeRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Recipe item) {
                viewModel.delete(item);
            }
        });

        binding.searchRecipesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchRecipeFragment searchRecipeFragment = new SearchRecipeFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(container.getId(), searchRecipeFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        binding.addRecipeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.getRecipeName.getText().toString().trim();
                String category = binding.getRecipeDescription.getText().toString().trim();
                if (!name.isEmpty() || !category.isEmpty()) {
                    saveData(name, category);
                }
            }
        });

        layoutManager = new LinearLayoutManager(this.getContext());
        binding.recipeList.setLayoutManager(layoutManager);
        if (recipes != null) {
            adapter.addrecipes(recipes);
        }

        binding.recipeList.setAdapter(adapter);
        return view;
    }

    private void saveData(String name, String description) {
        RecipeViewModel viewModel = new ViewModelProvider(requireActivity()).get(RecipeViewModel.class);
        Recipe recipe = new Recipe(name, description);
        recipes.add(recipe);
        viewModel.insert(recipe);
        adapter.addrecipes(recipes);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setListData(List<Recipe> dataItemList) {
        //if data changed, set new list to adapter of recyclerview

        if (recipes == null) {
            recipes = new ArrayList<>();
        }
        recipes.clear();
        recipes.addAll(dataItemList);

        if (adapter != null) {
            adapter.addrecipes(dataItemList);
        }
    }
}

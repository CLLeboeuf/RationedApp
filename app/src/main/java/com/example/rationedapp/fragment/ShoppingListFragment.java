package com.example.rationedapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rationedapp.adapter.ShoppingListRecyclerViewAdapter;
import com.example.rationedapp.databinding.ShoppingListFragmentBinding;
import com.example.rationedapp.entity.PantryItem;
import com.example.rationedapp.viewmodel.ShoppingListViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListFragment extends Fragment {

    private RecyclerView.LayoutManager layoutManager;
    public  List<PantryItem> pantryItems;
    private ShoppingListRecyclerViewAdapter adapter;
    private ShoppingListFragmentBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ShoppingListFragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        pantryItems = new ArrayList<>();


        //this just creates a line divider between rows
        binding.groceryList.addItemDecoration(new
                DividerItemDecoration(this.getContext(), LinearLayoutManager.VERTICAL));

        ShoppingListViewModel viewModel = new ViewModelProvider(requireActivity()).get(ShoppingListViewModel.class);
        viewModel.getAllPantryItems().observe(getViewLifecycleOwner(), new Observer<List<PantryItem>>() {
                    @Override
                    public void onChanged(@Nullable final List<PantryItem> pantryItems) {
                        if (pantryItems != null) {
                            setListData(pantryItems);
                        }
                    }
                });
        adapter = new ShoppingListRecyclerViewAdapter(pantryItems, new ShoppingListRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PantryItem item) {
                viewModel.delete(item);
            }
        });
        layoutManager = new LinearLayoutManager(this.getContext());
        binding.groceryList.setLayoutManager(layoutManager);
        if (pantryItems != null) {
            adapter.addPantryItems(pantryItems);
        }
        binding.groceryList.setAdapter(adapter);


        binding.addGroceryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.getGroceryNameText.getText().toString().trim();
                String category= binding.getGroceryCategoryText.getText().toString().trim();
                if (!name.isEmpty() || !category.isEmpty()) {
                    saveData(name, category);
                }
            }
        });


        return view;
    }

    private void saveData(String name, String category) {
        ShoppingListViewModel viewModel = new ViewModelProvider(requireActivity()).get(ShoppingListViewModel.class);
        PantryItem pantryItem = new PantryItem(name, category,null,0);
        pantryItems.add(pantryItem);
        viewModel.insert(pantryItem);
        adapter.addPantryItems(pantryItems);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setListData(List<PantryItem> dataItemList) {
        //if data changed, set new list to adapter of recyclerview

        if (pantryItems == null) {
            pantryItems = new ArrayList<>();
        }
        pantryItems.clear();
        pantryItems.addAll(dataItemList);

        if (adapter != null) {
            adapter.addPantryItems(dataItemList);
        }
    }
}

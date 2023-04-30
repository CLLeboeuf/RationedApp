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

import com.example.rationedapp.adapter.PantryRecyclerViewAdapter;
import com.example.rationedapp.databinding.PantryFragmentBinding;
import com.example.rationedapp.entity.PantryItem;
import com.example.rationedapp.viewmodel.PantryViewModel;

import java.util.ArrayList;
import java.util.List;

public class PantryFragment extends Fragment {

    private PantryFragmentBinding addBinding;
    private RecyclerView.LayoutManager layoutManager;
    public  List<PantryItem> pantryItems;
    private PantryRecyclerViewAdapter adapter;
    private PantryFragmentBinding binding;
    private PantryViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = PantryFragmentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        pantryItems = new ArrayList<>();


        //this just creates a line divider between rows
        binding.pantryList.addItemDecoration(new
                DividerItemDecoration(this.getContext(), LinearLayoutManager.VERTICAL));

        PantryViewModel viewModel = new ViewModelProvider(requireActivity()).get(PantryViewModel.class);
        viewModel.getAllPantryItems().observe(getViewLifecycleOwner(), new Observer<List<PantryItem>>() {
            @Override
            public void onChanged(@Nullable final List<PantryItem> pantryItems) {
                if (pantryItems != null) {
                    setListData(pantryItems);
                }
            }
        });
        adapter = new PantryRecyclerViewAdapter(pantryItems, new PantryRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(PantryItem item) {
                viewModel.delete(item);
            }
        });
        layoutManager = new LinearLayoutManager(this.getContext());
        binding.pantryList.setLayoutManager(layoutManager);
        if (pantryItems != null) {
            adapter.addPantryItems(pantryItems);
        }
        binding.pantryList.setAdapter(adapter);


        binding.addGroceryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.getPantryItemText.getText().toString().trim();
                String category= binding.getPantryItemCategoryText.getText().toString().trim();
                String measurement = binding.getMeasurementTypeText.getText().toString().trim();
                Double amount = Double.parseDouble(binding.getAmountText.getText().toString().trim());
                if (!name.isEmpty() || !category.isEmpty()) {
                    saveData(name, category, measurement, amount);
                }
            }
        });
        return view;
    }

    private void saveData(String name, String category, String measurementType, Double amount) {
        PantryViewModel viewModel = new ViewModelProvider(requireActivity()).get(PantryViewModel.class);
        PantryItem pantryItem = new PantryItem(name, category,measurementType, amount);
        pantryItems.add(pantryItem);
        viewModel.insert(pantryItem);
        adapter.addPantryItems(pantryItems);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        addBinding = null;
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

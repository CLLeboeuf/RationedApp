package com.example.rationedapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.rationedapp.databinding.SearchRecipeLayoutBinding;
import com.example.rationedapp.entity.Recipe;
import com.example.rationedapp.retrofit.RetrofitClient;
import com.example.rationedapp.retrofit.RetrofitInterface;
import com.example.rationedapp.retrofit.SearchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchRecipeFragment extends Fragment {
    private static final String API_KEY = "\n" + "7e55a30f763ade99e78a51e42f2f82d1";
    private static final String SEARCH_ID_cx = "0c8b577c";
    private String keyword;
    private SearchRecipeLayoutBinding binding;
    private RetrofitInterface retrofitInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = SearchRecipeLayoutBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        super.onCreate(savedInstanceState);
        binding= SearchRecipeLayoutBinding.inflate(getLayoutInflater());
        retrofitInterface = RetrofitClient.getRetrofitService();
        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "clicked", Toast.LENGTH_SHORT).show();
                keyword=binding.searchGetTxt.getText().toString();
                Call<SearchResponse> callAsync =
                        retrofitInterface.customSearch(API_KEY,SEARCH_ID_cx, keyword);
                //makes an async request & invokes callback methods when the response returns
                callAsync.enqueue(new Callback<SearchResponse>() {
                    @Override
                    public void onResponse(Call<SearchResponse> call,
                                           Response<SearchResponse> response) {
                        if (response.isSuccessful()) {
                            List<Recipe> list = response.body().recipes;
//getting snippet from the object in the position 0
                            /*String name= list.get(0).getLabel();
                            String description= list.get(0).getUrl();

                            binding.recipeNameText.setText(name);
                            binding.recipeDescription.setText(description);*/
                            Toast.makeText(getContext(), "Response Received", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getContext(), "No response Received", Toast.LENGTH_SHORT).show();
                            Log.i("Error ","Response failed");
                        }
                    }
                    @Override
                    public void onFailure(Call<SearchResponse> call, Throwable t){
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT);
                    }
                });
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.mvvm;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm.adapter.ShoppingListAdapter;
import com.example.mvvm.model.ShoppingModel;
import com.example.mvvm.viewmodel.ShoppingListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ShoppingListAdapter.ItemClickListener {

    private List<ShoppingModel> shoppingModelList;
    private ShoppingListAdapter adapter;
    private ShoppingListViewModel viewModel;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this.getApplicationContext();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ShoppingListAdapter(mContext, shoppingModelList, this);
        recyclerView.setAdapter(adapter);

        //viewModel = new ViewModelProviders.of(this).get(ShoppingListViewModel.class);

        viewModel = ViewModelProviders.of(this).get(ShoppingListViewModel.class);
        viewModel.getShoppingList().observe(this, new Observer<List<ShoppingModel>>() {
            @Override
            public void onChanged(List<ShoppingModel> movieModels) {
                if (movieModels != null) {
                    shoppingModelList = movieModels;
                    adapter.setShoppingList(movieModels);
                    //noresult.setVisibility(View.GONE);
                } else {
                    // noresult.setVisibility(View.VISIBLE);
                }
            }
        });
        viewModel.makeApiCall();
    }

    @Override
    public void onShopClick(ShoppingModel model) {
        Toast.makeText(this, "" + model.getTitle(), Toast.LENGTH_SHORT).show();
    }
}


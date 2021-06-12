package com.example.mvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm.model.ShoppingModel;
import com.example.mvvm.network.APIService;
import com.example.mvvm.network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShoppingListViewModel extends ViewModel {

    private MutableLiveData<List<ShoppingModel>> shoppingList;

    public ShoppingListViewModel() {
        shoppingList = new MutableLiveData<>();
    }

    public MutableLiveData<List<ShoppingModel>> getShoppingList() {
        return shoppingList;
    }

    public void makeApiCall() {
        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<List<ShoppingModel>> call = apiService.getShoppingList();
        call.enqueue(new Callback<List<ShoppingModel>>() {
            @Override
            public void onResponse(Call<List<ShoppingModel>> call, Response<List<ShoppingModel>> response) {
                shoppingList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ShoppingModel>> call, Throwable t) {
                shoppingList.postValue(null);
            }
        });
    }
}

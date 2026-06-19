package com.example.app_movil_gastronomia.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.app_movil_gastronomia.core.UiState;
import com.example.app_movil_gastronomia.data.api.ProductoApi;
import com.example.app_movil_gastronomia.data.dto.ProductoDto;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductoRepositoryImpl implements ProductoRepository {

    private static final String TAG = "ProductoRepositoryImpl";

    private final ProductoApi productoApi;

    @Inject
    public ProductoRepositoryImpl(ProductoApi productoApi) {
        this.productoApi = productoApi;
    }

    @Override
    public LiveData<UiState<List<ProductoDto>>> getProductos() {
        MutableLiveData<UiState<List<ProductoDto>>> result = new MutableLiveData<>();
        result.setValue(UiState.loading());

        productoApi.getProductos().enqueue(new Callback<List<ProductoDto>>() {
            @Override
            public void onResponse(Call<List<ProductoDto>> call, Response<List<ProductoDto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    result.setValue(UiState.success(response.body()));
                } else {
                    result.setValue(UiState.error("Error del servidor, intente más tarde"));
                }
            }

            @Override
            public void onFailure(Call<List<ProductoDto>> call, Throwable t) {
                Log.e(TAG, "GetProductos network failure", t);
                result.setValue(UiState.error("No hay conexión a internet"));
            }
        });

        return result;
    }
}
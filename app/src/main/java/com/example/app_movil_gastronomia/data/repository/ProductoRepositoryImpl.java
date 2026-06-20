package com.example.app_movil_gastronomia.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.app_movil_gastronomia.core.UiState;
import com.example.app_movil_gastronomia.data.api.ProductoApi;
import com.example.app_movil_gastronomia.data.dto.ProductoDto;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Owns a single {@link MutableLiveData} instance ({@code _productListState})
 * that is reset to LOADING on every {@link #getProductos()} call and then
 * posted SUCCESS or ERROR. The instance is never reallocated, so observers
 * registered in the ViewModel constructor (via {@code observeForever}) keep
 * receiving emissions across retries without leaking.
 */
@Singleton
public class ProductoRepositoryImpl implements ProductoRepository {

    private static final String TAG = "ProductoRepositoryImpl";

    private final ProductoApi productoApi;
    private final MutableLiveData<UiState<List<ProductoDto>>> _productListState = new MutableLiveData<>();

    @Inject
    public ProductoRepositoryImpl(ProductoApi productoApi) {
        this.productoApi = productoApi;
    }

    @Override
    public LiveData<UiState<List<ProductoDto>>> getProductos() {
        // Reset the single shared instance to LOADING before the network call.
        _productListState.setValue(UiState.loading());

        productoApi.getProductos().enqueue(new Callback<List<ProductoDto>>() {
            @Override
            public void onResponse(@NonNull Call<List<ProductoDto>> call,
                                   @NonNull Response<List<ProductoDto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    _productListState.setValue(UiState.success(response.body()));
                } else {
                    _productListState.setValue(UiState.error("Error del servidor, intente más tarde"));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ProductoDto>> call, @NonNull Throwable t) {
                Log.e(TAG, "GetProductos network failure", t);
                _productListState.setValue(UiState.error("No hay conexión a internet"));
            }
        });

        return getProductListState();
    }

    @Override
    public LiveData<UiState<List<ProductoDto>>> getProductListState() {
        return _productListState;
    }
}

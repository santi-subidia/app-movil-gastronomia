package com.example.app_movil_gastronomia.ui.cajero;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_movil_gastronomia.core.UiState;
import com.example.app_movil_gastronomia.data.dto.ProductoDto;
import com.example.app_movil_gastronomia.data.repository.ProductoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProductListViewModel extends ViewModel {

    private final ProductoRepository productoRepository;
    private final MutableLiveData<UiState<List<ProductoDto>>> productState = new MutableLiveData<>();

    @Inject
    public ProductListViewModel(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
        loadProductos();
    }

    public LiveData<UiState<List<ProductoDto>>> getProductState() {
        return productState;
    }

    public void loadProductos() {
        productoRepository.getProductos().observeForever(productState::setValue);
    }

    public void retry() {
        loadProductos();
    }
}
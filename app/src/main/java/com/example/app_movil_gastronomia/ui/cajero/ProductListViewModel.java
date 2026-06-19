package com.example.app_movil_gastronomia.ui.cajero;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
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
    private final MediatorLiveData<UiState<List<ProductoDto>>> productState = new MediatorLiveData<>();

    @Inject
    public ProductListViewModel(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
        loadProductos();
    }

    public LiveData<UiState<List<ProductoDto>>> getProductState() {
        return productState;
    }

    public void loadProductos() {
        LiveData<UiState<List<ProductoDto>>> source = productoRepository.getProductos();
        productState.addSource(source, productState::setValue);
    }

    public void retry() {
        loadProductos();
    }
}
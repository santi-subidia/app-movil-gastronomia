package com.example.app_movil_gastronomia.data.repository;

import androidx.lifecycle.LiveData;

import com.example.app_movil_gastronomia.core.UiState;
import com.example.app_movil_gastronomia.data.dto.ProductoDto;

import java.util.List;

public interface ProductoRepository {

    LiveData<UiState<List<ProductoDto>>> getProductos();

    /**
     * Returns the single {@link LiveData} instance that holds the current
     * product-list state. The same instance is reused across every
     * {@link #getProductos()} call so observers (typically a
     * {@code ViewModel}) can register exactly once.
     */
    LiveData<UiState<List<ProductoDto>>> getProductListState();
}
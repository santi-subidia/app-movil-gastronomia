package com.example.app_movil_gastronomia.data.repository;

import androidx.lifecycle.LiveData;

import com.example.app_movil_gastronomia.core.UiState;
import com.example.app_movil_gastronomia.data.dto.ProductoDto;

import java.util.List;

public interface ProductoRepository {

    LiveData<UiState<List<ProductoDto>>> getProductos();
}
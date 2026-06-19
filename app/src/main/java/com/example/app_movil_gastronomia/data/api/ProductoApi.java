package com.example.app_movil_gastronomia.data.api;

import com.example.app_movil_gastronomia.data.dto.ProductoDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductoApi {

    @GET("api/productos")
    Call<List<ProductoDto>> getProductos();
}
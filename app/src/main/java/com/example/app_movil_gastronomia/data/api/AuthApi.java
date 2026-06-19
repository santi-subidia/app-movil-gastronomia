package com.example.app_movil_gastronomia.data.api;

import com.example.app_movil_gastronomia.data.dto.LoginRequest;
import com.example.app_movil_gastronomia.data.dto.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {

    @POST("api/auth/login")
    Call<LoginResponse> login(@Body LoginRequest request);
}
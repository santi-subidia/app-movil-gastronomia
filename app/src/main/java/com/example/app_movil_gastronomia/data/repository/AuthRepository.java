package com.example.app_movil_gastronomia.data.repository;

import androidx.lifecycle.LiveData;

import com.example.app_movil_gastronomia.core.UiState;
import com.example.app_movil_gastronomia.data.dto.LoginRequest;
import com.example.app_movil_gastronomia.data.dto.LoginResponse;

public interface AuthRepository {

    LiveData<UiState<LoginResponse>> login(LoginRequest request);
}
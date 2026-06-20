package com.example.app_movil_gastronomia.data.repository;

import androidx.lifecycle.LiveData;

import com.example.app_movil_gastronomia.core.UiState;
import com.example.app_movil_gastronomia.data.dto.LoginRequest;
import com.example.app_movil_gastronomia.data.dto.LoginResponse;

public interface AuthRepository {

    LiveData<UiState<LoginResponse>> login(LoginRequest request);

    /**
     * Returns the single {@link LiveData} instance that holds the current
     * login flow state. The same instance is reused across every
     * {@link #login(LoginRequest)} call so observers (typically a
     * {@code ViewModel}) can register exactly once.
     */
    LiveData<UiState<LoginResponse>> getLoginState();
}
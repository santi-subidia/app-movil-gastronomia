package com.example.app_movil_gastronomia.data.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.app_movil_gastronomia.core.TokenManager;
import com.example.app_movil_gastronomia.core.UiState;
import com.example.app_movil_gastronomia.data.api.AuthApi;
import com.example.app_movil_gastronomia.data.dto.ErrorResponse;
import com.example.app_movil_gastronomia.data.dto.LoginRequest;
import com.example.app_movil_gastronomia.data.dto.LoginResponse;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepositoryImpl implements AuthRepository {

    private static final String TAG = "AuthRepositoryImpl";

    private final AuthApi authApi;
    private final TokenManager tokenManager;

    @Inject
    public AuthRepositoryImpl(AuthApi authApi, TokenManager tokenManager) {
        this.authApi = authApi;
        this.tokenManager = tokenManager;
    }

    @Override
    public LiveData<UiState<LoginResponse>> login(LoginRequest request) {
        MutableLiveData<UiState<LoginResponse>> result = new MutableLiveData<>();
        result.setValue(UiState.loading());

        authApi.login(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse body = response.body();
                    tokenManager.saveToken(body.getToken(), body.getRolNombre(), body.getId());
                    result.setValue(UiState.success(body));
                } else {
                    String errorMsg = "Error de autenticación";
                    try {
                        if (response.errorBody() != null) {
                            String errorBody = response.errorBody().string();
                            // Try to parse as ErrorResponse JSON
                            com.google.gson.Gson gson = new com.google.gson.Gson();
                            ErrorResponse errorResponse = gson.fromJson(errorBody, ErrorResponse.class);
                            if (errorResponse != null && errorResponse.getMensaje() != null) {
                                errorMsg = errorResponse.getMensaje();
                            }
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Error parsing error body", e);
                    }
                    result.setValue(UiState.error(errorMsg));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e(TAG, "Login network failure", t);
                result.setValue(UiState.error("No hay conexión a internet"));
            }
        });

        return result;
    }
}
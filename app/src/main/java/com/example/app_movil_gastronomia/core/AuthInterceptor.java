package com.example.app_movil_gastronomia.core;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttp interceptor that injects the stored JWT as an Authorization Bearer header
 * into every outgoing request. On a 401 response, clears the local session
 * so the app can redirect to login.
 */
public class AuthInterceptor implements Interceptor {

    private final TokenManager tokenManager;

    public AuthInterceptor(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();

        String token = tokenManager.getToken();
        Request.Builder requestBuilder = originalRequest.newBuilder();

        if (token != null) {
            requestBuilder.header("Authorization", "Bearer " + token);
        }

        Response response = chain.proceed(requestBuilder.build());

        if (response.code() == 401) {
            tokenManager.clearToken();
        }

        return response;
    }
}
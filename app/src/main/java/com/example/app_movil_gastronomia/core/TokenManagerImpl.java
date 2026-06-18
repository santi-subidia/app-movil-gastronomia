package com.example.app_movil_gastronomia.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;

/**
 * EncryptedSharedPreferences-backed implementation of {@link TokenManager}.
 * Stores JWT token, role name, and user ID with E2E encryption at rest.
 * Falls back gracefully if MasterKey initialization fails.
 */
public class TokenManagerImpl implements TokenManager {

    private static final String TAG = "TokenManagerImpl";
    private static final String PREFS_FILE_NAME = "secure_auth_prefs";
    private static final String KEY_TOKEN = "jwt_token";
    private static final String KEY_ROLE = "rol_nombre";
    private static final String KEY_USER_ID = "user_id";

    private final SharedPreferences encryptedPrefs;

    public TokenManagerImpl(Context context) {
        SharedPreferences prefs;
        try {
            MasterKey masterKey = new MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();

            prefs = EncryptedSharedPreferences.create(
                    context,
                    PREFS_FILE_NAME,
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (Exception e) {
            Log.e(TAG, "Failed to initialize EncryptedSharedPreferences, falling back to clear prefs", e);
            prefs = context.getSharedPreferences(PREFS_FILE_NAME + "_fallback", Context.MODE_PRIVATE);
        }
        this.encryptedPrefs = prefs;
    }

    @Override
    public void saveToken(String token, String rolNombre, int userId) {
        encryptedPrefs.edit()
                .putString(KEY_TOKEN, token)
                .putString(KEY_ROLE, rolNombre)
                .putInt(KEY_USER_ID, userId)
                .apply();
    }

    @Override
    public String getToken() {
        return encryptedPrefs.getString(KEY_TOKEN, null);
    }

    @Override
    public String getRole() {
        return encryptedPrefs.getString(KEY_ROLE, null);
    }

    @Override
    public int getUserId() {
        return encryptedPrefs.getInt(KEY_USER_ID, -1);
    }

    @Override
    public boolean hasToken() {
        return getToken() != null;
    }

    @Override
    public void clearToken() {
        encryptedPrefs.edit().clear().apply();
    }
}
package com.example.app_movil_gastronomia.data.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Request body for {@code PATCH /api/pedidos/{id}/estado}.
 *
 * <p>Spec PED-DTO-001: the new estado is sent as its apiValue string
 * (e.g. {@code "EnPreparacion"}). A wrapper object is used instead of
 * a raw enum body so the contract stays explicit and easy to extend.</p>
 */
public class CambiarEstadoRequest {

    @SerializedName("nuevoEstado")
    private String nuevoEstado;

    public CambiarEstadoRequest(String nuevoEstado) {
        this.nuevoEstado = nuevoEstado;
    }

    public String getNuevoEstado() {
        return nuevoEstado;
    }

    public void setNuevoEstado(String nuevoEstado) {
        this.nuevoEstado = nuevoEstado;
    }
}

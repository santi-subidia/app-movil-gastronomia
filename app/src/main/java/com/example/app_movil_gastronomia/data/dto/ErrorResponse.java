package com.example.app_movil_gastronomia.data.dto;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {

    @SerializedName("mensaje")
    private String mensaje;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
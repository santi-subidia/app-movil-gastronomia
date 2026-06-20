package com.example.app_movil_gastronomia.data.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Request body for {@code POST /api/cajas/apertura}.
 *
 * <p>Spec CAJ-DTO-001: serialized JSON must contain exactly the
 * keys {@code usuarioAperturaId} and {@code montoApertura}. Both
 * fields are required by the server, so they are kept as
 * primitives — same null-omit convention as
 * {@code CrearProductoRequest} / {@code CrearPedidoRequest}.</p>
 */
public class AbrirCajaRequest {

    @SerializedName("usuarioAperturaId")
    private int usuarioAperturaId;

    @SerializedName("montoApertura")
    private double montoApertura;

    public AbrirCajaRequest(int usuarioAperturaId, double montoApertura) {
        this.usuarioAperturaId = usuarioAperturaId;
        this.montoApertura = montoApertura;
    }

    public int getUsuarioAperturaId() {
        return usuarioAperturaId;
    }

    public void setUsuarioAperturaId(int usuarioAperturaId) {
        this.usuarioAperturaId = usuarioAperturaId;
    }

    public double getMontoApertura() {
        return montoApertura;
    }

    public void setMontoApertura(double montoApertura) {
        this.montoApertura = montoApertura;
    }
}

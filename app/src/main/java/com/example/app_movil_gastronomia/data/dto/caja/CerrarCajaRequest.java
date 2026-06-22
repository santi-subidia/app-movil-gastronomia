package com.example.app_movil_gastronomia.data.dto.caja;

import com.google.gson.annotations.SerializedName;

/**
 * Request body for {@code POST /api/cajas/{id}/cierre}.
 *
 * <p>Spec CAJ-DTO-001: serialized JSON must contain exactly the
 * keys {@code usuarioCierreId}, {@code montoCierreTeorico} and
 * {@code montoCierreReal}. All three fields are required by the
 * server, so they are kept as primitives.</p>
 */
public class CerrarCajaRequest {

    @SerializedName("usuarioCierreId")
    private int usuarioCierreId;

    @SerializedName("montoCierreTeorico")
    private double montoCierreTeorico;

    @SerializedName("montoCierreReal")
    private double montoCierreReal;

    public CerrarCajaRequest(int usuarioCierreId, double montoCierreTeorico, double montoCierreReal) {
        this.usuarioCierreId = usuarioCierreId;
        this.montoCierreTeorico = montoCierreTeorico;
        this.montoCierreReal = montoCierreReal;
    }

    public int getUsuarioCierreId() {
        return usuarioCierreId;
    }

    public void setUsuarioCierreId(int usuarioCierreId) {
        this.usuarioCierreId = usuarioCierreId;
    }

    public double getMontoCierreTeorico() {
        return montoCierreTeorico;
    }

    public void setMontoCierreTeorico(double montoCierreTeorico) {
        this.montoCierreTeorico = montoCierreTeorico;
    }

    public double getMontoCierreReal() {
        return montoCierreReal;
    }

    public void setMontoCierreReal(double montoCierreReal) {
        this.montoCierreReal = montoCierreReal;
    }
}

package com.example.app_movil_gastronomia.data.dto.caja;

import com.google.gson.annotations.SerializedName;

/**
 * Wire-format representation of a caja returned by the
 * {@code /api/cajas} endpoints (list, get-by-id, apertura and
 * cierre responses).
 *
 * <p>Spec CAJ-DTO-001: the 11 fields map 1:1 to the JSON
 * contract in {@code doc/API_REFERENCIA.md} §3.4. Cierre-related
 * fields ({@code usuarioCierreId}, {@code usuarioCierreNombre},
 * {@code fechaCierre}, {@code montoCierreTeorico},
 * {@code montoCierreReal}) are typed as boxed wrappers so Gson
 * keeps them {@code null} for an open caja that has no cierre
 * data yet — open cajas never have cierre values, so a primitive
 * default would silently corrupt the deserialized state.</p>
 */
public class CajaDto {

    @SerializedName("id")
    private int id;

    @SerializedName("usuarioAperturaId")
    private int usuarioAperturaId;

    @SerializedName("usuarioAperturaNombre")
    private String usuarioAperturaNombre;

    @SerializedName("usuarioCierreId")
    private Integer usuarioCierreId;

    @SerializedName("usuarioCierreNombre")
    private String usuarioCierreNombre;

    @SerializedName("fechaApertura")
    private String fechaApertura;

    @SerializedName("fechaCierre")
    private String fechaCierre;

    @SerializedName("montoApertura")
    private double montoApertura;

    @SerializedName("montoCierreTeorico")
    private Double montoCierreTeorico;

    @SerializedName("montoCierreReal")
    private Double montoCierreReal;

    @SerializedName("estado")
    private String estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsuarioAperturaId() {
        return usuarioAperturaId;
    }

    public void setUsuarioAperturaId(int usuarioAperturaId) {
        this.usuarioAperturaId = usuarioAperturaId;
    }

    public String getUsuarioAperturaNombre() {
        return usuarioAperturaNombre;
    }

    public void setUsuarioAperturaNombre(String usuarioAperturaNombre) {
        this.usuarioAperturaNombre = usuarioAperturaNombre;
    }

    public Integer getUsuarioCierreId() {
        return usuarioCierreId;
    }

    public void setUsuarioCierreId(Integer usuarioCierreId) {
        this.usuarioCierreId = usuarioCierreId;
    }

    public String getUsuarioCierreNombre() {
        return usuarioCierreNombre;
    }

    public void setUsuarioCierreNombre(String usuarioCierreNombre) {
        this.usuarioCierreNombre = usuarioCierreNombre;
    }

    public String getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public double getMontoApertura() {
        return montoApertura;
    }

    public void setMontoApertura(double montoApertura) {
        this.montoApertura = montoApertura;
    }

    public Double getMontoCierreTeorico() {
        return montoCierreTeorico;
    }

    public void setMontoCierreTeorico(Double montoCierreTeorico) {
        this.montoCierreTeorico = montoCierreTeorico;
    }

    public Double getMontoCierreReal() {
        return montoCierreReal;
    }

    public void setMontoCierreReal(Double montoCierreReal) {
        this.montoCierreReal = montoCierreReal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

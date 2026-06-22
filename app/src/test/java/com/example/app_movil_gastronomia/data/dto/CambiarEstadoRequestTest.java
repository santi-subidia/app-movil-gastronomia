package com.example.app_movil_gastronomia.data.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.app_movil_gastronomia.data.dto.pedido.CambiarEstadoRequest;
import com.example.app_movil_gastronomia.data.dto.pedido.EstadoPedidoEnum;
import com.google.gson.Gson;

import org.junit.Test;

/**
 * Spec PED-DTO-001: {@link CambiarEstadoRequest} wraps the new estado
 * as a {@code String} (the apiValue from {@link EstadoPedidoEnum}).
 * The wire format is {@code {"nuevoEstado":"EnPreparacion"}}.
 */
public class CambiarEstadoRequestTest {

    private final Gson gson = new Gson();

    @Test
    public void serializesWithNuevoEstadoKey() {
        CambiarEstadoRequest request = new CambiarEstadoRequest("EnPreparacion");
        String json = gson.toJson(request);

        assertTrue("json must contain 'nuevoEstado', got: " + json,
                json.contains("\"nuevoEstado\""));
        assertTrue("json must contain the apiValue, got: " + json,
                json.contains("\"EnPreparacion\""));
    }

    @Test
    public void roundTripsApiValue() {
        String sample = "{\"nuevoEstado\":\"ListoParaRetirar\"}";
        CambiarEstadoRequest parsed = gson.fromJson(sample, CambiarEstadoRequest.class);

        assertEquals("ListoParaRetirar", parsed.getNuevoEstado());
    }

    @Test
    public void constructorStoresApiValue() {
        CambiarEstadoRequest request = new CambiarEstadoRequest("EnCamino");
        assertEquals("EnCamino", request.getNuevoEstado());
    }

    @Test
    public void setterUpdatesApiValue() {
        CambiarEstadoRequest request = new CambiarEstadoRequest("Pendiente");
        request.setNuevoEstado("Entregado");
        assertEquals("Entregado", request.getNuevoEstado());
    }
}

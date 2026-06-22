package com.example.app_movil_gastronomia.data.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.example.app_movil_gastronomia.data.dto.caja.AbrirCajaRequest;
import com.google.gson.Gson;

import org.junit.Test;

/**
 * Spec CAJ-DTO-001: the request body for
 * {@code POST /api/cajas/apertura} must serialize to a JSON
 * object with exactly the keys {@code usuarioAperturaId} and
 * {@code montoApertura}, matching the server contract in
 * {@code doc/API_REFERENCIA.md} §3.4. Both fields are required,
 * so they are kept as primitives.
 */
public class AbrirCajaRequestTest {

    private final Gson gson = new Gson();

    @Test
    public void serializesAllFieldsWithExpectedKeys() {
        AbrirCajaRequest request = new AbrirCajaRequest(1, 5000.0);

        String json = gson.toJson(request);

        AbrirCajaRequest parsed = gson.fromJson(json, AbrirCajaRequest.class);
        assertEquals(1, parsed.getUsuarioAperturaId());
        assertEquals(5000.0, parsed.getMontoApertura(), 0.0001);

        assertTrue("json must contain 'usuarioAperturaId', got: " + json,
                json.contains("\"usuarioAperturaId\""));
        assertTrue("json must contain 'montoApertura', got: " + json,
                json.contains("\"montoApertura\""));
    }

    @Test
    public void roundTripsSampleJsonFromApiReference() {
        // Exact body from doc/API_REFERENCIA.md §3.4 POST /api/cajas/apertura
        String sample = "{"
                + "\"usuarioAperturaId\": 1,"
                + "\"montoApertura\": 5000.00"
                + "}";

        AbrirCajaRequest parsed = gson.fromJson(sample, AbrirCajaRequest.class);

        assertNotNull(parsed);
        assertEquals(1, parsed.getUsuarioAperturaId());
        assertEquals(5000.0, parsed.getMontoApertura(), 0.0001);
    }

    @Test
    public void gettersReturnConstructorValues() {
        AbrirCajaRequest request = new AbrirCajaRequest(7, 1234.56);

        assertEquals(7, request.getUsuarioAperturaId());
        assertEquals(1234.56, request.getMontoApertura(), 0.0001);
    }

    @Test
    public void settersUpdateFieldValues() {
        AbrirCajaRequest request = new AbrirCajaRequest(1, 100.0);
        request.setUsuarioAperturaId(2);
        request.setMontoApertura(200.0);

        assertEquals(2, request.getUsuarioAperturaId());
        assertEquals(200.0, request.getMontoApertura(), 0.0001);
    }
}

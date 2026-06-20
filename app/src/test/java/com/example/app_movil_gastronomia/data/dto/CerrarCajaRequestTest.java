package com.example.app_movil_gastronomia.data.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.google.gson.Gson;

import org.junit.Test;

/**
 * Spec CAJ-DTO-001: the request body for
 * {@code POST /api/cajas/{id}/cierre} must serialize to a JSON
 * object with exactly the keys {@code usuarioCierreId},
 * {@code montoCierreTeorico} and {@code montoCierreReal},
 * matching the server contract in
 * {@code doc/API_REFERENCIA.md} §3.4. All three fields are
 * required, so they are kept as primitives.
 */
public class CerrarCajaRequestTest {

    private final Gson gson = new Gson();

    @Test
    public void serializesAllFieldsWithExpectedKeys() {
        CerrarCajaRequest request = new CerrarCajaRequest(1, 25000.0, 24850.0);

        String json = gson.toJson(request);

        CerrarCajaRequest parsed = gson.fromJson(json, CerrarCajaRequest.class);
        assertEquals(1, parsed.getUsuarioCierreId());
        assertEquals(25000.0, parsed.getMontoCierreTeorico(), 0.0001);
        assertEquals(24850.0, parsed.getMontoCierreReal(), 0.0001);

        assertTrue("json must contain 'usuarioCierreId', got: " + json,
                json.contains("\"usuarioCierreId\""));
        assertTrue("json must contain 'montoCierreTeorico', got: " + json,
                json.contains("\"montoCierreTeorico\""));
        assertTrue("json must contain 'montoCierreReal', got: " + json,
                json.contains("\"montoCierreReal\""));
    }

    @Test
    public void roundTripsSampleJsonFromApiReference() {
        // Exact body from doc/API_REFERENCIA.md §3.4 POST /api/cajas/{id}/cierre
        String sample = "{"
                + "\"usuarioCierreId\": 1,"
                + "\"montoCierreTeorico\": 25000.00,"
                + "\"montoCierreReal\": 24850.00"
                + "}";

        CerrarCajaRequest parsed = gson.fromJson(sample, CerrarCajaRequest.class);

        assertNotNull(parsed);
        assertEquals(1, parsed.getUsuarioCierreId());
        assertEquals(25000.0, parsed.getMontoCierreTeorico(), 0.0001);
        assertEquals(24850.0, parsed.getMontoCierreReal(), 0.0001);
    }

    @Test
    public void gettersReturnConstructorValues() {
        CerrarCajaRequest request = new CerrarCajaRequest(7, 5000.0, 4950.0);

        assertEquals(7, request.getUsuarioCierreId());
        assertEquals(5000.0, request.getMontoCierreTeorico(), 0.0001);
        assertEquals(4950.0, request.getMontoCierreReal(), 0.0001);
    }

    @Test
    public void settersUpdateFieldValues() {
        CerrarCajaRequest request = new CerrarCajaRequest(1, 100.0, 100.0);
        request.setUsuarioCierreId(2);
        request.setMontoCierreTeorico(200.0);
        request.setMontoCierreReal(195.0);

        assertEquals(2, request.getUsuarioCierreId());
        assertEquals(200.0, request.getMontoCierreTeorico(), 0.0001);
        assertEquals(195.0, request.getMontoCierreReal(), 0.0001);
    }
}

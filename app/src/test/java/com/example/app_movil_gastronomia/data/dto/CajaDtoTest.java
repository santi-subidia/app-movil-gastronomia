package com.example.app_movil_gastronomia.data.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.junit.Test;

/**
 * Spec CAJ-DTO-001: {@link CajaDto} deserializes from the
 * {@code GET /api/cajas} response shape. The 11 fields match the
 * wire format 1:1. Boxed {@code Integer}/{@code Double} fields
 * ({@code usuarioCierreId}, {@code usuarioCierreNombre},
 * {@code fechaCierre}, {@code montoCierreTeorico},
 * {@code montoCierreReal}) must stay {@code null} for an open
 * caja that has no cierre data yet.
 *
 * <p>Sample JSON is taken directly from
 * {@code doc/API_REFERENCIA.md} §3.4 GET /api/cajas.</p>
 */
public class CajaDtoTest {

    private final Gson gson = new Gson();

    private static final String OPEN_CAJA_JSON = "{"
            + "\"id\": 1,"
            + "\"usuarioAperturaId\": 1,"
            + "\"usuarioAperturaNombre\": \"cajero1\","
            + "\"usuarioCierreId\": null,"
            + "\"usuarioCierreNombre\": null,"
            + "\"fechaApertura\": \"2026-06-18T08:00:00Z\","
            + "\"fechaCierre\": null,"
            + "\"montoApertura\": 5000,"
            + "\"montoCierreTeorico\": null,"
            + "\"montoCierreReal\": null,"
            + "\"estado\": \"abierta\""
            + "}";

    private static final String CLOSED_CAJA_JSON = "{"
            + "\"id\": 2,"
            + "\"usuarioAperturaId\": 1,"
            + "\"usuarioAperturaNombre\": \"cajero1\","
            + "\"usuarioCierreId\": 2,"
            + "\"usuarioCierreNombre\": \"cajero2\","
            + "\"fechaApertura\": \"2026-06-18T08:00:00Z\","
            + "\"fechaCierre\": \"2026-06-18T20:00:00Z\","
            + "\"montoApertura\": 5000,"
            + "\"montoCierreTeorico\": 25000,"
            + "\"montoCierreReal\": 24850,"
            + "\"estado\": \"cerrada\""
            + "}";

    @Test
    public void deserializesOpenCajaFromSampleJson() {
        CajaDto dto = gson.fromJson(OPEN_CAJA_JSON, CajaDto.class);

        assertNotNull(dto);
        assertEquals(1, dto.getId());
        assertEquals(1, dto.getUsuarioAperturaId());
        assertEquals("cajero1", dto.getUsuarioAperturaNombre());
        assertNull(dto.getUsuarioCierreId());
        assertNull(dto.getUsuarioCierreNombre());
        assertEquals("2026-06-18T08:00:00Z", dto.getFechaApertura());
        assertNull(dto.getFechaCierre());
        assertEquals(5000.0, dto.getMontoApertura(), 0.0001);
        assertNull(dto.getMontoCierreTeorico());
        assertNull(dto.getMontoCierreReal());
        assertEquals("abierta", dto.getEstado());
    }

    @Test
    public void deserializesClosedCajaWithAllCierreFields() {
        CajaDto dto = gson.fromJson(CLOSED_CAJA_JSON, CajaDto.class);

        assertNotNull(dto);
        assertEquals(2, dto.getId());
        assertEquals(1, dto.getUsuarioAperturaId());
        assertEquals("cajero1", dto.getUsuarioAperturaNombre());
        assertEquals(Integer.valueOf(2), dto.getUsuarioCierreId());
        assertEquals("cajero2", dto.getUsuarioCierreNombre());
        assertEquals("2026-06-18T08:00:00Z", dto.getFechaApertura());
        assertEquals("2026-06-18T20:00:00Z", dto.getFechaCierre());
        assertEquals(5000.0, dto.getMontoApertura(), 0.0001);
        assertEquals(Double.valueOf(25000.0), dto.getMontoCierreTeorico());
        assertEquals(Double.valueOf(24850.0), dto.getMontoCierreReal());
        assertEquals("cerrada", dto.getEstado());
    }

    @Test
    public void serializesAllFieldsWithExpectedKeys() {
        CajaDto dto = new CajaDto();
        dto.setId(7);
        dto.setUsuarioAperturaId(3);
        dto.setUsuarioAperturaNombre("ana");
        dto.setUsuarioCierreId(4);
        dto.setUsuarioCierreNombre("luis");
        dto.setFechaApertura("2026-06-20T09:00:00Z");
        dto.setFechaCierre("2026-06-20T18:00:00Z");
        dto.setMontoApertura(1000.0);
        dto.setMontoCierreTeorico(2000.0);
        dto.setMontoCierreReal(1995.0);
        dto.setEstado("cerrada");

        String json = gson.toJson(dto);

        assertTrue("json must contain 'id', got: " + json, json.contains("\"id\""));
        assertTrue("json must contain 'usuarioAperturaId', got: " + json, json.contains("\"usuarioAperturaId\""));
        assertTrue("json must contain 'usuarioAperturaNombre', got: " + json, json.contains("\"usuarioAperturaNombre\""));
        assertTrue("json must contain 'usuarioCierreId', got: " + json, json.contains("\"usuarioCierreId\""));
        assertTrue("json must contain 'usuarioCierreNombre', got: " + json, json.contains("\"usuarioCierreNombre\""));
        assertTrue("json must contain 'fechaApertura', got: " + json, json.contains("\"fechaApertura\""));
        assertTrue("json must contain 'fechaCierre', got: " + json, json.contains("\"fechaCierre\""));
        assertTrue("json must contain 'montoApertura', got: " + json, json.contains("\"montoApertura\""));
        assertTrue("json must contain 'montoCierreTeorico', got: " + json, json.contains("\"montoCierreTeorico\""));
        assertTrue("json must contain 'montoCierreReal', got: " + json, json.contains("\"montoCierreReal\""));
        assertTrue("json must contain 'estado', got: " + json, json.contains("\"estado\""));
    }

    @Test
    public void roundTripsClosedCajaViaSerializeThenDeserialize() {
        CajaDto original = new CajaDto();
        original.setId(11);
        original.setUsuarioAperturaId(5);
        original.setUsuarioAperturaNombre("sofia");
        original.setUsuarioCierreId(6);
        original.setUsuarioCierreNombre("pablo");
        original.setFechaApertura("2026-06-19T08:00:00Z");
        original.setFechaCierre("2026-06-19T20:00:00Z");
        original.setMontoApertura(7500.0);
        original.setMontoCierreTeorico(30000.0);
        original.setMontoCierreReal(29990.0);
        original.setEstado("cerrada");

        String json = gson.toJson(original);
        CajaDto parsed = gson.fromJson(json, CajaDto.class);

        assertNotNull(parsed);
        assertEquals(11, parsed.getId());
        assertEquals(5, parsed.getUsuarioAperturaId());
        assertEquals("sofia", parsed.getUsuarioAperturaNombre());
        assertEquals(Integer.valueOf(6), parsed.getUsuarioCierreId());
        assertEquals("pablo", parsed.getUsuarioCierreNombre());
        assertEquals("2026-06-19T08:00:00Z", parsed.getFechaApertura());
        assertEquals("2026-06-19T20:00:00Z", parsed.getFechaCierre());
        assertEquals(7500.0, parsed.getMontoApertura(), 0.0001);
        assertEquals(Double.valueOf(30000.0), parsed.getMontoCierreTeorico());
        assertEquals(Double.valueOf(29990.0), parsed.getMontoCierreReal());
        assertEquals("cerrada", parsed.getEstado());
    }

    @Test
    public void boxedCierreFieldsAreOmittedFromJsonWhenNull() {
        CajaDto dto = new CajaDto();
        dto.setId(1);
        dto.setUsuarioAperturaId(1);
        dto.setUsuarioAperturaNombre("cajero1");
        dto.setFechaApertura("2026-06-18T08:00:00Z");
        dto.setMontoApertura(5000.0);
        dto.setEstado("abierta");
        // usuarioCierreId, usuarioCierreNombre, fechaCierre,
        // montoCierreTeorico, montoCierreReal stay null

        String json = gson.toJson(dto);
        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

        assertTrue("usuarioCierreId must be omitted when null, got: " + json,
                !obj.has("usuarioCierreId"));
        assertTrue("usuarioCierreNombre must be omitted when null, got: " + json,
                !obj.has("usuarioCierreNombre"));
        assertTrue("fechaCierre must be omitted when null, got: " + json,
                !obj.has("fechaCierre"));
        assertTrue("montoCierreTeorico must be omitted when null, got: " + json,
                !obj.has("montoCierreTeorico"));
        assertTrue("montoCierreReal must be omitted when null, got: " + json,
                !obj.has("montoCierreReal"));
    }

    @Test
    public void defaultConstructorsLeaveFieldsAtDefaults() {
        CajaDto dto = new CajaDto();

        assertEquals(0, dto.getId());
        assertEquals(0, dto.getUsuarioAperturaId());
        assertNull(dto.getUsuarioAperturaNombre());
        assertNull(dto.getUsuarioCierreId());
        assertNull(dto.getUsuarioCierreNombre());
        assertNull(dto.getFechaApertura());
        assertNull(dto.getFechaCierre());
        assertEquals(0.0, dto.getMontoApertura(), 0.0001);
        assertNull(dto.getMontoCierreTeorico());
        assertNull(dto.getMontoCierreReal());
        assertNull(dto.getEstado());
    }

    @Test
    public void gettersReturnSetterValues() {
        CajaDto dto = new CajaDto();
        dto.setId(99);
        dto.setUsuarioAperturaId(10);
        dto.setUsuarioAperturaNombre("u10");
        dto.setUsuarioCierreId(20);
        dto.setUsuarioCierreNombre("u20");
        dto.setFechaApertura("2026-06-20T08:00:00Z");
        dto.setFechaCierre("2026-06-20T20:00:00Z");
        dto.setMontoApertura(1234.5);
        dto.setMontoCierreTeorico(5678.9);
        dto.setMontoCierreReal(5670.0);
        dto.setEstado("cerrada");

        assertEquals(99, dto.getId());
        assertEquals(10, dto.getUsuarioAperturaId());
        assertEquals("u10", dto.getUsuarioAperturaNombre());
        assertEquals(Integer.valueOf(20), dto.getUsuarioCierreId());
        assertEquals("u20", dto.getUsuarioCierreNombre());
        assertEquals("2026-06-20T08:00:00Z", dto.getFechaApertura());
        assertEquals("2026-06-20T20:00:00Z", dto.getFechaCierre());
        assertEquals(1234.5, dto.getMontoApertura(), 0.0001);
        assertEquals(Double.valueOf(5678.9), dto.getMontoCierreTeorico());
        assertEquals(Double.valueOf(5670.0), dto.getMontoCierreReal());
        assertEquals("cerrada", dto.getEstado());
    }
}

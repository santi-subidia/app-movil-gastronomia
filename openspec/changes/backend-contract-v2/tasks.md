# Tasks: backend-contract-v2

## Review Workload Forecast

| Field | Value |
|-------|-------|
| Total estimated changed lines | ~450 |
| 400-line budget risk | Low (per PR) |
| Chained PRs recommended | Yes |
| Suggested split | PR1 → PR2 → PR3 → PR4 (stacked to main) |
| Delivery strategy | ask-always |
| Chain strategy | stacked-to-main |

Decision needed before apply: Yes
Chained PRs recommended: Yes
Chain strategy: stacked-to-main
400-line budget risk: Low

### Suggested Work Units

| Unit | Goal | Est. Lines |
|------|------|-----------|
| PR1 | Catalog infrastructure | ~135 |
| PR2 | DTO cleanup + SignalR rename | ~140 |
| PR3 | CrearPedido UI refactor | ~100 |
| PR4 | Estado body + cajas abiertas | ~75 |

## PR1: Catalog Infrastructure (NEW)

- [x] 1.1 Create `CatalogoItemDto` in `data/dto/catalogo/`
- [x] 1.2 Create `EstadosPedidoApi` — `GET api/catalogo/estados-pedido`
- [x] 1.3 Create `MetodoPagoApi` — `GET api/catalogo/metodos-pago`
- [x] 1.4 Create `MetodoVentaApi` — `GET api/catalogo/metodos-venta`
- [x] 1.5 Create `CatalogoRepository` interface — load/resolve/isReady
- [x] 1.6 Create `CatalogoRepositoryImpl` — eager singleton with `Map<String,Integer>` cache
- [x] 1.7 Wire 3 `@Provides` in `NetworkModule` for catalog APIs
- [x] 1.8 Wire `@Binds` in `RepositoryModule` for `CatalogoRepository`
- [x] 1.9 Write `CatalogoItemDtoTest` — Gson serialize/deserialize
- [x] 1.10 Write `CatalogoRepositoryImplTest` — cache, resolve, isReady

## PR2: DTO Cleanup + SignalR Rename (MODIFY / DELETE)

- [ ] 2.1–2.3 Remove `sector` from `CrearDemoraRequest`, `ActualizarDemoraRequest`, `DemoraDto`
- [ ] 2.4 Remove `usuarioAperturaId` from `AbrirCajaRequest`; constructor takes `montoApertura`
- [ ] 2.5 Remove `usuarioCierreId` from `CerrarCajaRequest`; constructor takes 2 doubles
- [ ] 2.6 Remove `usuarioAperturaId`/`usuarioCierreId` from `CajaDto`
- [ ] 2.7 Delete `CambiarEstadoRequest.java`; scrub all imports
- [ ] 2.8 Update `PedidoApi.cambiarEstado()` to `@Body int nuevoEstadoId`
- [ ] 2.9 Rename `PosicionGPSMessage` → `PosicionGPSActualizadaMessage`
- [ ] 2.10 Rename `getPosicionGPS()` → `getPosicionGPSActualizada()` in `SignalRService`
- [ ] 2.11 Update `SignalRServiceImpl`: field, handler `"PosicionGPS"` → `"PosicionGPSActualizada"`
- [ ] 2.12 Update `MapaViewModel` — use renamed getter, import new message class
- [ ] 2.13 Remove `tokenManager.getUserId()` from `CajaViewModel.abrirCaja()`/`cerrarCaja()`
- [ ] 2.14 Delete `CambiarEstadoRequestTest.java`
- [ ] 2.15 Update `AbrirCajaRequestTest`, `CerrarCajaRequestTest`, `CajaDtoTest` — drop ID assertions

## PR3: CrearPedido UI Refactor (CREATE / MODIFY)

- [ ] 3.1 Create `DetalleLine` POJO in `ui/pedido/` with 4 fields
- [ ] 3.2 Refactor `DetalleAdapter`: `List<CrearDetalleRequest>` → `List<DetalleLine>`
- [ ] 3.3 Refactor `CrearPedidoFragment`: list type, addDetalle, buildRequest mapping
- [ ] 3.4 Add `buildRequest()` in `CrearPedidoViewModel` — maps `DetalleLine` → `CrearDetalleRequest`
- [ ] 3.5 Write mapping test: `DetalleLine` → `CrearDetalleRequest` fields match

## PR4: Estado Body + Cajas Abiertas (MODIFY)

- [ ] 4.1 Inject `CatalogoRepository` into `PedidoRepositoryImpl`; resolve via `resolveEstadoId()`, send `@Body int`
- [ ] 4.2 Add `@GET("api/cajas/abiertas")` to `CajaApi`
- [ ] 4.3 Add `getCajasAbiertas()` + `getCajasAbiertasState()` to `CajaRepository`
- [ ] 4.4 Implement `getCajasAbiertas()` in `CajaRepositoryImpl` with LOADING/SUCCESS/ERROR
- [ ] 4.5 Update `PedidoRepositoryImplTest` — mock uses `int` body, add catalog mock
- [ ] 4.6 Write `CajaRepositoryImplTest` for `getCajasAbiertas` states

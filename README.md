# RapiGo — Backend

Spring Boot 4.1.0 · Java 21 · JPA + H2 (en memoria) · Maven Wrapper

## Ejecutar el servidor

```bash
./mvnw spring-boot:run
```

El servidor arranca en [http://localhost:8080](http://localhost:8080).

La consola H2 queda disponible en [http://localhost:8080/h2-console](http://localhost:8080/h2-console) (JDBC URL: `jdbc:h2:mem:rapigo`).

## Ejecutar las pruebas

```bash
./mvnw test
```

> **IMPORTANTE:** Con el codigo actual las 3 pruebas deben FALLAR. Eso es lo esperado para la demo.
> Un resultado verde indicaria que los bugs fueron corregidos accidentalmente.

Salida esperada:

```
[ERROR] Tests run: 3, Failures: 3, Errors: 0, Skipped: 0
```

## Bugs intencionales (NO corregir)

| Ticket | Tipo | Severidad | Descripcion |
|---|---|---|---|
| **RG-204** | Correctivo | Alta | `CartService.applyCoupon` no verifica si el cupon ya fue aplicado; se puede agregar el mismo cupon multiples veces acumulando descuentos hasta que el total sea negativo. |
| **RG-205** | Correctivo | Media | `CartService.checkout` calcula `total = subtotal - discount` olvidando sumar `deliveryFee` (S/5.00); la empresa pierde S/5 por cada pedido. |
| **RG-207** | Perfectivo | Media | `RestaurantService.list` usa `equals` en vez de `equalsIgnoreCase` al filtrar por cocina; buscar `"pizza"` (minuscula) devuelve lista vacia aunque existe "Pizza". |

## Endpoints principales

```
GET  /api/restaurants?cuisine=   Lista de restaurantes (filtro opcional)
GET  /api/restaurants/{id}/menu  Menu de un restaurante
POST /api/cart                   Crear carrito
GET  /api/cart/{id}              Ver carrito
POST /api/cart/{id}/items        Agregar item  { menuItemId, qty }
POST /api/cart/{id}/coupon       Aplicar cupon { code }
POST /api/cart/{id}/checkout     Confirmar pedido
```

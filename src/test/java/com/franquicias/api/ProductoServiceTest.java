package com.franquicias.api;

import com.franquicias.api.model.Producto;
import com.franquicias.api.model.Sucursal;
import com.franquicias.api.repository.ProductoRepository;
import com.franquicias.api.repository.SucursalRepository;
import com.franquicias.api.service.ProductoService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductoServiceTest {

    private final ProductoRepository productoRepository = Mockito.mock(ProductoRepository.class);
    private final SucursalRepository sucursalRepository = Mockito.mock(SucursalRepository.class);

    private final ProductoService service = new ProductoService(productoRepository, sucursalRepository);

    @Test
    void deberiaCrearProducto() {

        Sucursal sucursal = new Sucursal();
        sucursal.setId(1L);

        Mockito.when(sucursalRepository.findById(1L)).thenReturn(Optional.of(sucursal));

        Producto productoGuardado = new Producto();
        productoGuardado.setId(1L);
        productoGuardado.setNombre("Producto Test");
        productoGuardado.setStock(100);

        Mockito.when(productoRepository.save(Mockito.any(Producto.class)))
                .thenReturn(productoGuardado);

        Producto resultado = service.crearProducto("Producto Test", 100, 1L);

        assertNotNull(resultado);
        assertEquals("Producto Test", resultado.getNombre());
        assertEquals(100, resultado.getStock());
    }

    @Test
    void deberiaActualizarStock() {

        Producto producto = new Producto();
        producto.setId(1L);
        producto.setStock(50);

        Mockito.when(productoRepository.findById(1L))
                .thenReturn(Optional.of(producto));

        Mockito.when(productoRepository.save(Mockito.any(Producto.class)))
                .thenReturn(producto);

        Producto resultado = service.actualizarStock(1L, 200);

        assertEquals(200, resultado.getStock());
    }

    @Test
    void deberiaLanzarErrorSiProductoNoExiste() {

        Mockito.when(productoRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            service.actualizarStock(99L, 100);
        });
    }
}
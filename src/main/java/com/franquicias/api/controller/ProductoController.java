package com.franquicias.api.controller;

import com.franquicias.api.model.Producto;
import com.franquicias.api.service.ProductoService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import com.franquicias.api.dto.ProductoTopDTO;
import com.franquicias.api.dto.StockRequest;
import com.franquicias.api.dto.NombreRequest;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @PostMapping
    public Producto crear(@RequestBody ProductoRequest request) {
        return service.crearProducto(
                request.getNombre(),
                request.getStock(),
                request.getSucursalId()
        );
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminarProducto(id);
    }

    @PutMapping("/{id}/stock")
    public Producto actualizarStock(@PathVariable Long id, @RequestBody StockRequest request) {

        if (request.getStock() < 0) {
            throw new RuntimeException("Stock no puede ser negativo");
        }

        return service.actualizarStock(id, request.getStock());
    }

    @GetMapping("/top-stock/{franquiciaId}")
    public List<ProductoTopDTO> obtenerTopProductos(@PathVariable Long franquiciaId) {
        return service.obtenerTopProductosPorFranquicia(franquiciaId);
    }

    @PutMapping("/{id}/nombre")
    public Producto actualizarNombre(@PathVariable Long id, @RequestBody NombreRequest request) {

        if (request.getNombre() == null || request.getNombre().isEmpty()) {
            throw new RuntimeException("Nombre no puede estar vacío");
        }

        return service.actualizarNombre(id, request.getNombre());
    }


}

@Data
class ProductoRequest {
    private String nombre;
    private int stock;
    private Long sucursalId;
}
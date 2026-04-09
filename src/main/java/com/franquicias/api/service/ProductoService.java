package com.franquicias.api.service;

import com.franquicias.api.dto.ProductoTopDTO;
import com.franquicias.api.model.Producto;
import com.franquicias.api.model.Sucursal;
import com.franquicias.api.repository.ProductoRepository;
import com.franquicias.api.repository.SucursalRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final SucursalRepository sucursalRepository;

    public ProductoService(ProductoRepository productoRepository, SucursalRepository sucursalRepository) {
        this.productoRepository = productoRepository;
        this.sucursalRepository = sucursalRepository;
    }

    public Producto crearProducto(String nombre, int stock, Long sucursalId) {

        Sucursal sucursal = sucursalRepository.findById(sucursalId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Sucursal no encontrada"
));

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setStock(stock);
        producto.setSucursal(sucursal);

        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
        productoRepository.deleteById(id);
    }

    public Producto actualizarStock(Long id, int nuevoStock) {

        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Producto no encontrado"
        ));

        producto.setStock(nuevoStock);

        return productoRepository.save(producto);
    }

    public List<ProductoTopDTO> obtenerTopProductosPorFranquicia(Long franquiciaId) {
        return productoRepository.findTopProductosByFranquicia(franquiciaId);
    }

    public Producto actualizarNombre(Long id, String nuevoNombre) {

        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Producto no encontrado"
        ));

        producto.setNombre(nuevoNombre);

        return productoRepository.save(producto);
    }

}
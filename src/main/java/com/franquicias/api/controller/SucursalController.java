package com.franquicias.api.controller;

import com.franquicias.api.model.Sucursal;
import com.franquicias.api.service.SucursalService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;
import com.franquicias.api.dto.NombreRequest;

@RestController
@RequestMapping("/sucursales")
public class SucursalController {

    private final SucursalService service;

    public SucursalController(SucursalService service) {
        this.service = service;
    }

    @PostMapping
    public Sucursal crear(@RequestBody SucursalRequest request) {
        return service.crearSucursal(request.getNombre(), request.getFranquiciaId());
    }

    @PutMapping("/{id}/nombre")
    public Sucursal actualizarNombre(@PathVariable Long id, @RequestBody NombreRequest request) {
        return service.actualizarNombre(id, request.getNombre());
    }
}

@Data
class SucursalRequest {
    private String nombre;
    private Long franquiciaId;
}
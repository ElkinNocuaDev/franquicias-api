package com.franquicias.api.controller;

import com.franquicias.api.model.Franquicia;
import com.franquicias.api.service.FranquiciaService;
import org.springframework.web.bind.annotation.*;
import com.franquicias.api.dto.NombreRequest;

@RestController
@RequestMapping("/franquicias")
public class FranquiciaController {

    private final FranquiciaService service;

    public FranquiciaController(FranquiciaService service) {
        this.service = service;
    }

    @PostMapping
    public Franquicia crear(@RequestBody Franquicia franquicia) {
        return service.crearFranquicia(franquicia);
    }

    @PutMapping("/{id}/nombre")
    public Franquicia actualizarNombre(@PathVariable Long id, @RequestBody NombreRequest request) {
        return service.actualizarNombre(id, request.getNombre());
    }

}
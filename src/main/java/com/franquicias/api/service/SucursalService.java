package com.franquicias.api.service;

import com.franquicias.api.model.Franquicia;
import com.franquicias.api.model.Sucursal;
import com.franquicias.api.repository.FranquiciaRepository;
import com.franquicias.api.repository.SucursalRepository;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class SucursalService {

    private final SucursalRepository sucursalRepository;
    private final FranquiciaRepository franquiciaRepository;

    public SucursalService(SucursalRepository sucursalRepository, FranquiciaRepository franquiciaRepository) {
        this.sucursalRepository = sucursalRepository;
        this.franquiciaRepository = franquiciaRepository;
    }

    public Sucursal crearSucursal(String nombre, Long franquiciaId) {

        Franquicia franquicia = franquiciaRepository.findById(franquiciaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Franquicia no encontrada"
        ));

        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(nombre);
        sucursal.setFranquicia(franquicia);

        return sucursalRepository.save(sucursal);
    }

    public Sucursal actualizarNombre(Long id, String nuevoNombre) {

        Sucursal sucursal = sucursalRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Sucursal no encontrada"
        ));

        sucursal.setNombre(nuevoNombre);

        return sucursalRepository.save(sucursal);
    }
}
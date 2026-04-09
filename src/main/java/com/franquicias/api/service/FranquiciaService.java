package com.franquicias.api.service;

import com.franquicias.api.model.Franquicia;
import com.franquicias.api.repository.FranquiciaRepository;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class FranquiciaService {

    private final FranquiciaRepository repository;

    public FranquiciaService(FranquiciaRepository repository) {
        this.repository = repository;
    }

    public Franquicia crearFranquicia(Franquicia franquicia) {
        return repository.save(franquicia);
    }

    public Franquicia actualizarNombre(Long id, String nuevoNombre) {

        Franquicia franquicia = repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Franquicia no encontrada"
        ));

        franquicia.setNombre(nuevoNombre);

        return repository.save(franquicia);
    }

}
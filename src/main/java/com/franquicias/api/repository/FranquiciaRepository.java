package com.franquicias.api.repository;

import com.franquicias.api.model.Franquicia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranquiciaRepository extends JpaRepository<Franquicia, Long> {
}
package com.franquicias.api.repository;

import com.franquicias.api.dto.ProductoTopDTO;
import com.franquicias.api.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

@Query("""
SELECT new com.franquicias.api.dto.ProductoTopDTO(
    p.id,
    p.nombre,
    p.stock,
    p.sucursal.id,
    p.sucursal.nombre
)
FROM Producto p
WHERE p.stock = (
    SELECT MAX(p2.stock)
    FROM Producto p2
    WHERE p2.sucursal.id = p.sucursal.id
)
AND p.sucursal.franquicia.id = :franquiciaId
""")
List<ProductoTopDTO> findTopProductosByFranquicia(@Param("franquiciaId") Long franquiciaId);

}

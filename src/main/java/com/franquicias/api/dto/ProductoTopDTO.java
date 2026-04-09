package com.franquicias.api.dto;

public class ProductoTopDTO {

    private Long productoId;
    private String productoNombre;
    private int stock;
    private Long sucursalId;
    private String sucursalNombre;

    public ProductoTopDTO(Long productoId, String productoNombre, int stock,
                          Long sucursalId, String sucursalNombre) {
        this.productoId = productoId;
        this.productoNombre = productoNombre;
        this.stock = stock;
        this.sucursalId = sucursalId;
        this.sucursalNombre = sucursalNombre;
    }

    // getters
    public Long getProductoId() { return productoId; }
    public String getProductoNombre() { return productoNombre; }
    public int getStock() { return stock; }
    public Long getSucursalId() { return sucursalId; }
    public String getSucursalNombre() { return sucursalNombre; }
}
package com.franquicias.api;

import com.franquicias.api.controller.ProductoController;
import com.franquicias.api.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoService productoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deberiaCrearProducto() throws Exception {

        String requestJson = """
        {
            "nombre": "Producto Test",
            "stock": 100,
            "sucursalId": 1
        }
        """;

        mockMvc.perform(post("/productos")
                .contentType("application/json")
                .content(requestJson))
                .andExpect(status().isOk());
    }
}
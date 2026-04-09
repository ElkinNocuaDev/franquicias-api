# 🚀 Franquicias API

API REST desarrollada en **Spring Boot** para la gestión de franquicias, sucursales y productos, desplegada en la nube usando Docker.

---

## 🌍 URL pública

```
https://franquicias-api-production-c38d.up.railway.app
```

---

## 🧠 Descripción

La API permite:

* Crear franquicias
* Crear sucursales asociadas a una franquicia
* Crear productos con stock por sucursal
* Actualizar stock de productos
* Eliminar productos
* Actualizar nombres (franquicia, sucursal, producto)
* Obtener el producto con mayor stock por sucursal de una franquicia

---

## 🛠️ Tecnologías utilizadas

* Java 17
* Spring Boot
* Spring Data JPA
* MySQL (Railway)
* Docker
* Maven

---

## ⚙️ Configuración

### Variables de entorno (.env)

Crear archivo `.env` en la raíz del proyecto:

```
DB_URL=jdbc:mysql://<host>:<port>/<db>
DB_USER=<user>
DB_PASSWORD=<password>
```

---

## ▶️ Ejecución local

### 1. Compilar

```
./mvnw clean package
```

### 2. Ejecutar

```
./mvnw spring-boot:run
```

---

## 🐳 Docker

### Build

```
docker build -t franquicias-api .
```

### Run

```
docker run --env-file .env -p 8080:8080 franquicias-api
```

---

# 📬 Pruebas con Postman

---

## 🔹 1. Crear franquicia

**POST**

```
/franquicias
```

Body:

```json
{
  "nombre": "Franquicia Demo"
}
```

---

## 🔹 2. Crear sucursal

**POST**

```
/sucursales
```

Body:

```json
{
  "nombre": "Sucursal Centro",
  "franquiciaId": 1
}
```

---

## 🔹 3. Crear producto

**POST**

```
/productos
```

Body:

```json
{
  "nombre": "Sixpack Aguila",
  "stock": 100,
  "sucursalId": 1
}
```

---

## 🔹 4. Actualizar stock

**PUT**

```
/productos/1/stock
```

Body:

```json
{
  "stock": 200
}
```

---

## 🔹 5. Actualizar nombre

**PUT**

```
/productos/1/nombre
```

Body:

```json
{
  "nombre": "Producto actualizado"
}
```

---

## 🔹 6. Eliminar producto

**DELETE**

```
/productos/1
```

---

## 🔹 7. Obtener producto con mayor stock por sucursal

**GET**

```
/productos/top-stock/1
```

Respuesta:

```json
[
  {
    "productoId": 2,
    "productoNombre": "Sixpack Aguila",
    "stock": 200,
    "sucursalId": 1,
    "sucursalNombre": "Sucursal Centro"
  }
]
```

---

## 🔹 8. Manejo de errores

**PUT**

```
/productos/999/stock
```

Respuesta:

```json
{
  "status": 404,
  "error": "Not Found"
}
```

---

# 🧠 Decisiones técnicas

* Uso de **DTOs** para evitar exponer entidades y reducir payload
* Uso de **JPA con queries optimizadas** para resolver consultas complejas
* Manejo correcto de errores HTTP (`404`, `400`)
* Separación por capas: Controller, Service, Repository
* Configuración mediante variables de entorno

---

## 🧪 Pruebas unitarias

La aplicación incluye pruebas unitarias para validar la lógica de negocio y los controladores REST, asegurando el correcto funcionamiento de cada capa.

### Tipos de pruebas implementadas

- **Service Tests (Mockito)**  
  Se prueban los servicios de forma aislada utilizando mocks para simular dependencias como repositorios, validando la lógica de negocio sin necesidad de conexión a la base de datos.

- **Controller Tests (MockMvc)**  
  Se simulan peticiones HTTP sin levantar el servidor completo, verificando el comportamiento de los endpoints y las respuestas HTTP.

- **Spring Context Test**  
  Se valida que el contexto de la aplicación cargue correctamente mediante un test básico (`contextLoads`).

---

### ▶️ Ejecutar pruebas

```bash
.\mvnw.cmd test

# ☁️ Despliegue

La aplicación está desplegada en Railway utilizando Docker.

---

# 👨‍💻 Autor

Desarrollado por Elkin Nocua.

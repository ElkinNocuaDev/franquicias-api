# ---------- BUILD STAGE ----------
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# Copiar todo el proyecto
COPY . .

# Compilar el proyecto
RUN mvn clean package -DskipTests

# ---------- RUNTIME STAGE ----------
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copiar el jar desde el stage anterior
COPY --from=build /app/target/franquicias-api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
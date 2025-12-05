# ===== Etapa 1: Build del JAR usando Maven + JDK 17 =====
FROM maven:3.9.4-eclipse-temurin-17-alpine AS build

# Carpeta de trabajo dentro del contenedor
WORKDIR /app

# Copia el pom.xml y la configuración de Maven primero (para cachear dependencias)
COPY pom.xml ./
COPY .mvn ./.mvn

# Copia wrapper si lo tienes
COPY mvnw ./
RUN chmod +x mvnw

# Descarga dependencias (offline, cacheable)
RUN mvn dependency:go-offline

# Copia el código fuente
COPY src ./src

# Build del JAR sin tests
RUN mvn clean package -DskipTests

# ===== Etapa 2: Imagen final para ejecutar el JAR =====
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copia el JAR generado desde la etapa de build
COPY --from=build /app/target/miapp-0.0.1-SNAPSHOT.jar ./miapp.jar

# Expone el puerto que Spring Boot va a usar
EXPOSE 8080

# Ejecuta la app usando el puerto dinámico de Railway
CMD ["sh", "-c", "java -jar miapp.jar --server.port=$PORT"]

# Usa Maven + JDK 17 Alpine para build
FROM maven:3.9.4-eclipse-temurin-17-alpine AS build

WORKDIR /app

# Copia archivos de configuración de Maven
COPY pom.xml ./ 
COPY .mvn ./.mvn

# Copia wrapper si lo tienes
COPY mvnw ./ 
RUN chmod +x mvnw

# Descarga dependencias
RUN mvn dependency:go-offline

# Copia el código fuente
COPY src ./src

# Build final sin tests
RUN mvn clean package -DskipTests

# Imagen final para correr el JAR
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copia el JAR generado
COPY --from=build /app/target/miapp-0.0.1-SNAPSHOT.jar ./miapp.jar

# Expone el puerto de Spring Boot
EXPOSE 8080

# Ejecuta la app usando el puerto asignado por Railway
CMD ["sh", "-c", "java -jar miapp.jar --server.port=$PORT"]

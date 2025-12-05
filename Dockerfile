# Usa la imagen oficial de Maven con JDK 17
FROM maven:3.9.4-eclipse-temurin-17-alpine

WORKDIR /app

# Copia pom.xml y wrapper de Maven primero para cachear dependencias
COPY pom.xml ./
COPY mvnw ./
COPY .mvn ./.mvn

# Da permisos de ejecución al wrapper
RUN chmod +x mvnw

# Descarga dependencias (cacheable)
RUN ./mvnw dependency:go-offline

# Copia el código fuente
COPY src ./src

# Build final del JAR sin tests para acelerar
RUN ./mvnw clean package -DskipTests

# Expone el puerto de Spring Boot (por defecto 8080)
EXPOSE 8080

# Ejecuta la app con el nombre correcto del JAR
CMD ["java", "-jar", "target/miapp-0.0.1-SNAPSHOT.jar"]

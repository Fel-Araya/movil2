# Usa la imagen oficial de Maven con JDK 17
FROM maven:3.9.4-eclipse-temurin-17-alpine

# Directorio de trabajo
WORKDIR /app

# Copia pom.xml y el wrapper de Maven para cachear dependencias
COPY pom.xml ./ 
COPY mvnw ./ 
COPY .mvn ./.mvn

# Da permisos de ejecución al wrapper
RUN chmod +x mvnw

# Descarga dependencias para cache (acelerar builds)
RUN ./mvnw dependency:go-offline

# Copia el código fuente
COPY src ./src

# Build final del JAR sin tests
RUN ./mvnw clean package -DskipTests

# Puerto dinámico asignado por Railway
ENV PORT=8080
EXPOSE ${PORT}

# Ejecuta la aplicación usando el JAR generado
CMD ["sh", "-c", "java -jar target/miapp-0.0.1-SNAPSHOT.jar --server.port=$PORT"]

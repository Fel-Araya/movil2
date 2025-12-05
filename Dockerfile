# Usa la imagen oficial de Maven con JDK 17
FROM maven:3.9.4-eclipse-temurin-17-alpine

WORKDIR /app

# Copia pom.xml y wrapper para cachear dependencias
COPY pom.xml ./ 
COPY mvnw ./ 
COPY .mvn ./.mvn

# Da permisos al wrapper
RUN chmod +x mvnw

# Descarga dependencias para cache
RUN ./mvnw dependency:go-offline

# Copia el código fuente
COPY src ./src

# Build del JAR sin tests
RUN ./mvnw clean package -DskipTests

# Puerto dinámico asignado por Railway
ENV PORT=8080
EXPOSE ${PORT}

# Ejecuta la app con el puerto dinámico
CMD ["sh", "-c", "java -jar target/miapp-0.0.1-SNAPSHOT.jar --server.port=$PORT"]

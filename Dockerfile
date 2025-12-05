# Usa la imagen oficial de Maven con JDK 21
FROM maven:3.9.4-eclipse-temurin-21-alpine

# Directorio de trabajo
WORKDIR /app

# Copia el pom.xml y el wrapper de Maven primero para cachear dependencias
COPY pom.xml ./
COPY mvnw ./
COPY .mvn ./.mvn

# Descarga dependencias (cacheable)
RUN ./mvnw dependency:go-offline

# Copia el resto del código fuente
COPY src ./src

# Construye el JAR (sin tests para acelerar)
RUN ./mvnw clean package -DskipTests

# Expone el puerto que usa tu app (si es Spring Boot normalmente 8080)
EXPOSE 8080

# Ejecuta la app
CMD ["java", "-jar", "target/njdemo-0.0.1-SNAPSHOT.jar"]

# Usa la imagen oficial de Maven con JDK 21
FROM maven:3.9.4-eclipse-temurin-21-alpine

WORKDIR /app

# Copia pom.xml y wrapper de Maven
COPY pom.xml ./
COPY mvnw ./
COPY .mvn ./.mvn

# Dale permisos de ejecución a mvnw
RUN chmod +x mvnw

# Descarga dependencias (cacheable)
RUN ./mvnw dependency:go-offline

# Copia el resto del código fuente
COPY src ./src

# Construye el JAR sin tests
RUN ./mvnw clean package -DskipTests

# Expone el puerto que usa tu app
EXPOSE 8080

# Ejecuta la app
CMD ["java", "-jar", "target/njdemo-0.0.1-SNAPSHOT.jar"]

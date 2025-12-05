# Usa la imagen oficial de Eclipse Temurin con JDK 21 y Alpine
FROM eclipse-temurin:21-jdk-alpine

# Crea y cambia al directorio de la app
WORKDIR /app

# Copia todo el código al contenedor
COPY . ./

# Construye la app con Gradle, saltando tests
RUN ./gradlew clean build -x test

# Ejecuta la app
CMD ["java", "-jar", "build/libs/njdemo-0.0.1-SNAPSHOT.jar"]

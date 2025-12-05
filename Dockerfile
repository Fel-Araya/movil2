FROM eclipse-temurin:21-jdk-alpine

RUN apk add --no-cache bash

WORKDIR /app

# Copia gradlew y build.gradle
COPY gradlew build.gradle ./
RUN chmod +x gradlew

# Descarga dependencias (cacheable)
RUN ./gradlew build -x test --dry-run

# Copia todo el código restante
COPY . ./

# Build final
RUN ./gradlew clean build -x test

# Ejecuta la app
CMD ["java", "-jar", "build/libs/njdemo-0.0.1-SNAPSHOT.jar"]

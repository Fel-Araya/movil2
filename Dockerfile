FROM eclipse-temurin:21-jdk-alpine

RUN apk add --no-cache bash curl unzip

# Instala Gradle
ARG GRADLE_VERSION=8.4
RUN curl -sLo gradle.zip https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip \
    && unzip gradle.zip -d /opt \
    && rm gradle.zip
ENV PATH="/opt/gradle-${GRADLE_VERSION}/bin:${PATH}"

WORKDIR /app

# Copia solo build.gradle (y settings.gradle si existe)
COPY build.gradle ./

# Copia todo el código
COPY . ./

# Build final
RUN gradle clean build -x test --no-daemon

# Ejecuta la app
CMD ["java", "-jar", "build/libs/njdemo-0.0.1-SNAPSHOT.jar"]

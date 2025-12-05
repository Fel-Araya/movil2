FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY njdemo-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "njdemo-0.0.1-SNAPSHOT.jar"]

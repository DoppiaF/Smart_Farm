######
FROM maven:3.8-openjdk-11-slim AS build

# Set the working directory in the container
WORKDIR /app


# Copia il file pom.xml e scarica le dipendenze
COPY pom.xml .
# Copia il codice sorgente
COPY src ./src

RUN mvn clean package
#RUN mvn dependency:go-offline
RUN mvn dependency:copy-dependencies


# Use an OpenJDK image to run the application
FROM openjdk:11-jdk-slim
WORKDIR /app
COPY target/smart-farm-management-1.0-SNAPSHOT.jar /app.jar
# Run the jar file
ENTRYPOINT ["java", "--module-path", "target/dependency", "--add-modules", "javafx.controls,javafx.fxml", "-jar", "/app.jar"]
######
FROM maven:3.8-openjdk-11-slim AS build

# Set the working directory in the container
WORKDIR /app


# Copia il file pom.xml e scarica le dipendenze
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia il codice sorgente
COPY src ./src

RUN mvn clean package

# Use an OpenJDK image to run the application
FROM openjdk:11-jdk-slim
WORKDIR /app
COPY --from=build ./target/smart-farm-management-1.0-SNAPSHOT.jar ./smart-farm-management-1.0-SNAPSHOT.jar
CMD ["java", "-jar", "smart-farm-management-1.0-SNAPSHOT.jar"]
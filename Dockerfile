######
FROM maven:3.8-openjdk-11-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

RUN mvn clean package

# Use an OpenJDK image to run the application
FROM openjdk:11-jdk-slim
WORKDIR /app
COPY --from=build ./target/smart-farm-management-1.0-SNAPSHOT.jar ./smart-farm-management-1.0-SNAPSHOT.jar
CMD ["java", "-jar", "smart-farm-management-1.0-SNAPSHOT.jar"]
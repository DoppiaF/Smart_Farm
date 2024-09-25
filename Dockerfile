######
FROM maven:3.8-openjdk-11-slim AS build

# Set the working directory in the container
WORKDIR /app


# Copia il file pom.xml e scarica le dipendenze
COPY pom.xml .

#RUN mvn dependency:go-offline
RUN mvn dependency:resolve
RUN mvn dependency:copy-dependencies

# Copia il codice sorgente
COPY src ./src
RUN mvn package

# Use an OpenJDK image to run the application
FROM openjdk:11-jdk-slim
WORKDIR /app

# Install necessary libraries for JavaFX
RUN apt-get update && apt-get install -y \
    libgl1-mesa-glx \
    libgl1-mesa-dri \
    libx11-6 \
    libxext6 \
    libxrender1 \
    libgtk-3-0 \
    libxtst6 \
    libxi6 \
    libfreetype6 \
    default-mysql-client \
    && rm -rf /var/lib/apt/lists/*

COPY target/smart-farm-management-1.0-SNAPSHOT.jar /app.jar


#COPY --from=build app.jar .
COPY --from=build /app/target/dependency/* ./libs/

# Run the jar file
ENTRYPOINT ["java", "--module-path", "./libs/", "--add-modules", "javafx.controls,javafx.fxml", "-Dprism.order=sw", "-Djava.awt.headless=true", "-jar", "/app.jar"]
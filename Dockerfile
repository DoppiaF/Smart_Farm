FROM openjdk:11-jdk-slim
VOLUME /tmp
COPY target/smart-farm-management-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

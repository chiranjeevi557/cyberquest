# Use an OpenJDK base image to run the Spring Boot application
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the local target directory into the container
COPY target/otp-generation-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot application listens on (default: 8080)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]

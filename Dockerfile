# Use an OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot JAR file into the container
COPY target/your-application.jar app.jar

# Expose the port on which the application will run
EXPOSE 8080

# Command to run
# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]

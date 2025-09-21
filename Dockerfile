# Use a base image with Java
FROM openjdk:21-slim

# Set working directory
WORKDIR /app

# Copy the Maven build output JAR into the image
COPY target/API1-0.0.1-SNAPSHOT.jar app.jar

# Expose port (optional, for documentation)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
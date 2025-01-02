# Use a base image with JDK 17 (OpenJDK 17)
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the pre-built JAR file from GitHub storage (use the path where you stored the JAR file)
COPY artifacts/customer-service-artifact.jar /app/customer-service-artifact.jar

# Expose the port your application will run on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/customer-service-artifact.jar"]

# Use the base image with Java 17 JDK
FROM openjdk:17-jdk-alpine

# Create a non-root user and group (e.g., appuser)
RUN addgroup -S springgroup && adduser -S springuser -G springgroup

# Set working directory to /app for the user
WORKDIR /app

# Copy the Spring Boot JAR file to the container
COPY target/cors-0.0.1-SNAPSHOT.jar /app/app.jar

# Ensure the non-root user has the correct permissions for the app files
RUN chown -R springuser:springgroup /app

# Expose port 8443 for HTTPS connections
EXPOSE 8443

# Switch to the non-root user
USER springuser

# Run the application as the non-root user
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]

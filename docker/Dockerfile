# Use JDK 21 base image
FROM eclipse-temurin:21-jdk

# Set working directory
WORKDIR /app

# Copy the built JAR into the container
COPY target/*.jar app.jar

# Pass Spring Profile as a build argument (default: dev)
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}

# Create the h2 directory
RUN mkdir -p h2

# Copy the test_db file into the h2 directory
COPY ./h2/test_db.mv.db ./h2/test_db.mv.db

# Create the storages directory
RUN mkdir -p storages

# Expose application port
EXPOSE 8080

# Command to run the application
CMD ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "app.jar"]
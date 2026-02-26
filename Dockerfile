# ---------------------------------------------------------
# Stage 1: Build Stage
# This stage uses Maven with JDK 17 to compile the
# Spring Boot application and generate the JAR file.
# ---------------------------------------------------------
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Set working directory inside the container
WORKDIR /app

# Copy only pom.xml first to leverage Docker layer caching
# This prevents re-downloading dependencies if source code changes
COPY pom.xml .

# Copy the source code into the container
COPY src ./src

# Package the application and skip tests to speed up build
RUN mvn clean package -DskipTests


# ---------------------------------------------------------
# Stage 2: Runtime Stage
# This stage creates a lightweight runtime image using
# Eclipse Temurin JDK 17 to run the generated JAR file.
# ---------------------------------------------------------
FROM eclipse-temurin:17-jdk

# Set working directory for runtime container
WORKDIR /app

# Copy the generated JAR file from the builder stage
# This ensures only the compiled artifact is included
COPY --from=builder /app/target/smart-clinic-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 for the Spring Boot application
EXPOSE 8080

# Define the command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]

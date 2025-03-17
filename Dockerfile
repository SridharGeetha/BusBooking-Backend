# Step 1: Use an official Maven image to build the application
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Set working directory inside the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the entire source code
COPY src ./src
RUN mvn clean package -DskipTests

# Step 2: Use an official JDK image to run the application
FROM eclipse-temurin:17-jdk

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/booking-0.0.1-SNAPSHOT.jar .

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/booking-0.0.1-SNAPSHOT.jar"]

FROM gradle:7.5.1-jdk17 as build
WORKDIR /usr/app
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew bootJar -x test

# Use the official OpenJDK 17 base image
FROM openjdk:17

# Set the working directory in the container
WORKDIR /app

COPY --from=build /usr/app/build/libs/management-0.0.1-SNAPSHOT.jar /usr/app/management.jar

# Expose the port on which the Spring Boot application will run
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "/usr/app/management.jar"]

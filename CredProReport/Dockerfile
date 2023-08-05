#FROM openjdk:17-alpine
#WORKDIR /opt
#ENV PORT 8080
#EXPOSE 8080

#COPY target/*.jar /opt/app.jar
#ENTRYPOINT exec java $JAVA_OPTS -jar app.jar


# Use the official OpenJDK base image for Java
FROM openjdk:17-alpine

# Set the working directory in the container
WORKDIR /opt

# Copy the Spring Boot application JAR file into the container
COPY target/*.jar /opt/app.jar

# Expose the port your Spring Boot application is running on
EXPOSE 8080

# Start the Spring Boot application when the container starts
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar

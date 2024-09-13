# Start with a base image containing Java runtime
FROM openjdk:17-jdk-alpine

# Add a volume pointing to /tmp
VOLUME /tmp

# The application's jar file
ARG JAR_FILE=target/Eurekams-0.0.1.jar

# Add the application's jar to the container
COPY ${JAR_FILE} Eurekams-0.0.1.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/Eurekams-0.0.1.jar"]

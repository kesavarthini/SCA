# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine


VOLUME /var/lib/docker-images

# Make port 9090 available to the world outside this container
EXPOSE 9090

# The application's jar file

# Add the application's jar to the container
ADD order-service/target/order-service-0.0.1-SNAPSHOT.jar order-service-0.0.1-SNAPSHOT.jar

# Run the jar file 
ENTRYPOINT ["java","-jar","order-service-0.0.1-SNAPSHOT.jar"]
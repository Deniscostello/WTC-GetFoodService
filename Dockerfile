FROM openjdk:17-jdk-alpine
ENV PROFILE=docker
WORKDIR /app
COPY target/WTC-GetFoodService-0.0.1-SNAPSHOT.jar /app
COPY env.properties /app
EXPOSE 8081
CMD ["java", "-jar", "WTC-GetFoodService-0.0.1-SNAPSHOT.jar"]

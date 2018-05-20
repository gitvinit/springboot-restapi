FROM openjdk:8
COPY target/springboot-restapi-1.0.jar /app.jar
EXPOSE 8080/tcp
ENTRYPOINT ["java", "-jar", "/app.jar"]
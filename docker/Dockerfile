FROM openjdk:8-jdk-alpine
COPY *.jar app.jar
ENTRYPOINT ["java","-jar","app.jar", "--spring.config.location=file:/config/application.properties"]

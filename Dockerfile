
FROM adoptopenjdk/openjdk11:jdk-11.0.5_10-alpine-slim
RUN mkdir /app
COPY /build/libs/*.jar /app/chat-service.jar
ENTRYPOINT ["java", "-jar", "/app/chat-service.jar"]

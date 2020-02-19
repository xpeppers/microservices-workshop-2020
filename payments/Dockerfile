FROM openjdk:8-jre-alpine
WORKDIR /app
COPY build/libs/payments-1.0-SNAPSHOT.jar ./app.jar
EXPOSE 8484
CMD ["java", "-jar", "app.jar"]
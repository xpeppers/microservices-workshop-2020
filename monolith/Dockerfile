FROM gradle:6.1-jdk8 AS builder
WORKDIR /app
COPY . .
RUN gradle clean jar


FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/xcommerce-1.0-SNAPSHOT.jar ./app.jar
EXPOSE 8282
CMD ["java", "-jar", "app.jar"]
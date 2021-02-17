FROM openjdk:8-jre-alpine AS base
WORKDIR /app
EXPOSE 8081

FROM maven:3.6.1-jdk-8-slim AS build
WORKDIR /app
COPY . .
RUN mvn install

FROM base AS final
WORKDIR /app
COPY --from=build /app/target/ath-qr-generator-0.0.1-SNAPSHOT.jar ./app.jar
#CMD ["java", "-Xmx200m", "-jar", "app.jar"]
ENTRYPOINT ["java","-Djava.security.edg=file:/dev/./urandom","-jar","./app.jar"]
FROM maven:3.8.5-openjdk-18 AS build
COPY src /src
COPY pom.xml /
RUN --mount=type=cache,target=/root/.m2 mvn -f /pom.xml -P dev clean package -DskipTests=true

FROM openjdk:18-oracle
COPY --from=build target/offers-api-0.0.1-SNAPSHOT.jar /offers-api-0.0.1.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "/offers-api-0.0.1.jar"]

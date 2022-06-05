FROM openjdk:17-oracle
COPY target/offers-api-0.0.1-SNAPSHOT.jar /offers-api-0.0.1.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/offers-api-0.0.1.jar"]
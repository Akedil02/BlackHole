
FROM maven:3.9.4-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .


RUN mvn dependency:resolve


COPY src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/BlackHoleTourismAgencyBook-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=10s --start-period=40s --retries=3 \
    CMD wget --quiet --tries=1 --spider http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]

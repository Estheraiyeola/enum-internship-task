FROM maven:3-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -DskipTests
FROM eclipse-temurin:17-alpine
COPY --from=build /target/enum-0.0.1-SNAPSHOT.jar enum.jar
ENTRYPOINT ["java","-Dspring.profiles.active=render","-jar","enum.jar"]
EXPOSE 9191
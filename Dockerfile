FROM maven:3.8.3-openjdk-17 AS build
#FROM maven:3-eclipse-temurin-11-alpine As build
COPY src /home/app/src
COPY pom.xml /home/app
WORKDIR /home/app
RUN mvn clean package -Dmaven.test.skip=true # Build jar file

# Stage 2: Create the final Docker image
FROM openjdk:17-jdk
COPY --from=build /home/app/target/*.jar watchlist.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "watchlist.jar"]
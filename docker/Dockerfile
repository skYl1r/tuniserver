FROM maven:3.6-jdk-8-alpine AS builder
WORKDIR /opt/app
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn -e -B -DskipTests package

FROM openjdk:8-jre-alpine
WORKDIR /opt
EXPOSE 8080
COPY --from=builder /opt/app/target/BackEndApplication-0.0.1-SNAPSHOT.jar ./app.jar
CMD ["java", "-jar", "-Dspring.profiles.active=docker", "app.jar"]

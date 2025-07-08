
FROM maven:3.9.6-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
RUN ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw clean package -DskipTests



FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY wait-for-it.sh .
RUN chmod +x wait-for-it.sh

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

CMD ["./wait-for-it.sh", "mysql-db:3306", "--timeout=60", "--strict", "--", "java", "-jar", "app.jar"]

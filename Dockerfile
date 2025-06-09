# Build aşaması — geçerli JDK 23 içeren Maven imajı kullan
FROM maven:3.9.6-eclipse-temurin AS builder

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run aşaması — JDK 23 runtime ile uygulamayı başlat
FROM eclipse-temurin:23-jdk-alpine

WORKDIR /app
COPY --from=builder /app/target/english-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

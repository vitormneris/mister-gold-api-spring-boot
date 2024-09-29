FROM docker.io/library/eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

COPY . .

RUN ./gradlew build --no-daemon

FROM docker.io/library/eclipse-temurin:21-jdk-alpine
WORKDIR /app

COPY --from=build /app/build/libs/mistergold-0.0.1-SNAPSHOT.jar /app/mistergold-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/app/mistergold-0.0.1-SNAPSHOT.jar"]

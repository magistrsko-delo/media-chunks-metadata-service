FROM openjdk:11.0.4-jre-slim

RUN mkdir /app

WORKDIR /app

ADD ./api/target/api-1.0.0-SNAPSHOT.jar /app

EXPOSE 8004 9003

CMD ["java", "-jar", "api-1.0.0-SNAPSHOT.jar"]
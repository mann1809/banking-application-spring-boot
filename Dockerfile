FROM openjdk:22-jdk

COPY /target/app-0.0.1-SNAPSHOT.jar banking-system.jar

EXPOSE 8082

CMD ["java", "-jar", "banking-system.jar"]
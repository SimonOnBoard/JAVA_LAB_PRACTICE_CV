FROM openjdk:9-jdk
VOLUME /tmp
ARG JAR_FILE=cv-project/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]

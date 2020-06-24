FROM openjdk:8-jre-alpine
LABEL maintainer="wcdevops@wyzcrew.com"
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /opt/registrator.jar
VOLUME [ "/app-data" ]
ENTRYPOINT ["java", "-jar", "/opt/registrator.jar"]
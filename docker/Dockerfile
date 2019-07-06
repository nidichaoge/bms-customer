FROM openjdk:8-jdk-alpine
MAINTAINER mouse
VOLUME /tmp
ADD bms-customer-config-0.0.1-SNAPSHOT.jar docker.jar
ENTRYPOINT ["java","-jar","/docker.jar"]
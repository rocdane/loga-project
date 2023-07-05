FROM openjdk:19
VOLUME /tmp
COPY ./target/*.jar intelligence-service.jar
ENTRYPOINT ["java","-jar","/intelligence-service.jar"]
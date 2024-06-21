FROM openjdk:17-alpine
LABEL authors="omerS"
VOLUME /tmp
EXPOSE 4000
COPY target/graphSpring-1.0.0.jar graph-spring-1.0.0.jar
ENTRYPOINT ["java","-jar","/graph-spring-1.0.0.jar"]

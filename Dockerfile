FROM openjdk:8
ADD target/ems.jar ems.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","/ems.jar"]
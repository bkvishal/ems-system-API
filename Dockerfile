FROM openjdk:8
COPY target/ems.jar ems.jar
EXPOSE 9090
CMD ["java","-jar","/ems.jar"]
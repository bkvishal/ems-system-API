FROM openjdk:8
ADD target/ems-system.jar ems-system.jar
ENTRYPOINT ["java", "-jar", "ems-system.jar"]
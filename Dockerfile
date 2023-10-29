FROM adoptopenjdk/maven-openjdk8:latest
ADD target/assessment-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
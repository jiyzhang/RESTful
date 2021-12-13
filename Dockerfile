FROM openjdk:11
ADD target/RESTful-0.0.2-SNAPSHOT.jar restful.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/restful.jar"]

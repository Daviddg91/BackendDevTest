FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} mockHytstrixRunaway-1.0.jar
ENTRYPOINT ["java","-jar","/mockHytstrixRunaway-1.0.jar"]
EXPOSE  8080
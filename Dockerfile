FROM openjdk:17
ENV APP_HOME=/usr/app/
WORKDIR /app

ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} app.jar
EXPOSE 8080
CMD ["java","-jar","app.jar"]
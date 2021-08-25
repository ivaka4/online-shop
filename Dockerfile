#FROM openjdk:16-alpine3.13
#
#EXPOSE 8080
#
#WORKDIR /app
#
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
#
#COPY src ./src
#
#WORKDIR src/main/java/com/example/onlineshop
#RUN javac OnlineShopApplication.java
#CMD ["OnlineShopApplication", "java"]

FROM openjdk:13
ARG JAR_FILE=/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

#FROM opendjdk:13-jdk-alpine
#ADD /target/online-shop.jar online-shop.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "/online-shop.jar"]
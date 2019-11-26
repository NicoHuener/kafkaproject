FROM java:8-jdk-alpine
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY ./JavaProject/out/artifacts/Hello_World/Hello_World.jar /usr/src/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Hello_World.jar"]
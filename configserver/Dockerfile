FROM openjdk:11
WORKDIR /workdir/
COPY out/libs /workdir/java/libs
COPY src/main/resources/ /workdir/java/configs/
COPY out/configserver.jar /workdir/java/configserver.jar
ENTRYPOINT ["sh","-c","java  -jar /workdir/java/configserver.jar"]
#会自动重启pod
#CMD ["java","-jar", "/workdir/java/configserver.jar"]
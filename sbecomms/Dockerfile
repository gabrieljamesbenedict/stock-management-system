FROM openjdk:17-oracle
LABEL mentainer="ltgabriel@mapua.edu.ph"
WORKDIR /opt/app
EXPOSE 8080
COPY target/sbecomms-1.0-SNAPSHOT.jar /opt/app/sbecomms.jar
ENTRYPOINT ["java", "-jar", "sbecomms.jar"]
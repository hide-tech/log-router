FROM adoptopenjdk/openjdk14

COPY build/libs/log-router-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

FROM adoptopenjdk/openjdk14 AS builder
WORKDIR workspace
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} log-router-0.0.1-SNAPSHOT.jar
RUN java -Djarmode=layertools -jar log-router-0.0.1-SNAPSHOT.jar extract
FROM adoptopenjdk/openjdk14
RUN useradd spring
USER spring
WORKDIR workspace
COPY --from=builder workspace/dependencies/ ./
COPY --from=builder workspace/spring-boot-loader/ ./
COPY --from=builder workspace/snapshot-dependencies/ ./
COPY --from=builder workspace/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]


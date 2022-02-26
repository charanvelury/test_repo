FROM openjdk:11-jre-slim
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} all-concepts-app.jar
RUN mkdir -p /usr/share/poc_project_files
ENTRYPOINT ["java","-jar","/all-concepts-app.jar"]
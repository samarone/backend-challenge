FROM openjdk:8-jre-alpine
LABEL maintainer="samarone@gmail.com"
ENV APP_FILE discovery-service-1.0-SNAPSHOT.jar
ENV APP_HOME /app
RUN mkdir -p $APP_HOME
COPY target/$APP_FILE $APP_HOME/app.jar
WORKDIR $APP_HOME
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
EXPOSE 8061
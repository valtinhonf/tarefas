FROM maven:3.8.1-openjdk-17-slim AS build
COPY src /usr/app/src
COPY pom.xml /usr/app
RUN mvn -f /usr/app/pom.xml clean package -DskipTests

FROM openjdk:17-alpine

ENV LANG='pt_BR.UTF-8' LANGUAGE='pt_BR.UTF-8' LC_ALL='pt_BR.UTF-8'

WORKDIR /usr/app
COPY --from=build /usr/app/target/tarefas-0.0.1-SNAPSHOT.jar /usr/app/tarefas-0.0.1-SNAPSHOT.jar
COPY --from=build /usr/app/src/main/resources /usr/app/src/main/resources
RUN mkdir temp
# COPY start.sh start.sh

RUN apk add tzdata

ENV TZ=America/Sao_Paulo
RUN cp /usr/share/zoneinfo/$TZ /etc/localtime
CMD [ "java", "-Dfile.encoding=UTF-8", "-jar", "apicerne-0.0.1-SNAPSHOT.jar" ]
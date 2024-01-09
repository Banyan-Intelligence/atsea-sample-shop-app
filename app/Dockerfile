FROM node:latest AS storefront
WORKDIR /usr/src/atsea/app/react-app
COPY react-app .
RUN npm install
RUN npm run build

FROM maven:latest AS appserver
WORKDIR /usr/src/atsea
COPY pom.xml .
RUN mvn -B -f pom.xml -s /usr/share/maven/ref/settings-docker.xml dependency:resolve
COPY . .
RUN mvn -B -s /usr/share/maven/ref/settings-docker.xml package -DskipTests

FROM openjdk:8-jdk-alpine
RUN adduser -Dh /home/gordon gordon
WORKDIR /static
COPY --from=storefront /usr/src/atsea/app/react-app/build/ .
WORKDIR /app
COPY --from=appserver /usr/src/atsea/target/AtSea-0.0.1-SNAPSHOT.jar .

ARG jmx_port=9011
ARG jmx_prome_port=8099
COPY jmx_prometheus_javaagent-0.20.0.jar /app/jmx_prometheus_javaagent-0.20.0.jar
COPY jmx_config.yaml /app/jmx_config.yaml

EXPOSE $jmx_prome_port
ENV JAVA_OPTS="-Dcom.sun.management.jmxremote \
               -Dcom.sun.management.jmxremote.port=$jmx_port \
               -Dcom.sun.management.jmxremote.rmi.port=$jmx_port \
               -Dcom.sun.management.jmxremote.authenticate=false \
               -Dcom.sun.management.jmxremote.ssl=false \
               -Djava.rmi.server.hostname=0.0.0.0"


EXPOSE 5005
EXPOSE 8080

ENTRYPOINT exec java $JAVA_OPTS -javaagent:"jmx_prometheus_javaagent-0.20.0.jar=8099:jmx_config.yaml" -jar /app/AtSea-0.0.1-SNAPSHOT.jar --debug --spring.profiles.active=postgres


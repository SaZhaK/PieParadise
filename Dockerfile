FROM maven:3.6-alpine as DEPS

WORKDIR .
RUN mkdir -p /application

RUN mvn clean install

COPY /target/SpringTest-1.0-SNAPSHOT.war /application/SpringTest.war

CMD java -Dserver.port=$PORT $JAVA_OPTS -jar /application/SpringTest.war
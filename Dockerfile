FROM adoptopenjdk/openjdk8:latest

WORKDIR .
RUN mkdir -p /application
COPY /target/SpringTest-1.0-SNAPSHOT.war /application/SpringTest.war

CMD java -Dserver.port=$PORT $JAVA_OPTS -jar /application/SpringTest.war
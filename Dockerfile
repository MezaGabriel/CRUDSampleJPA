FROM openjdk:8-jre-alpine
ENV GABRIEL_FILE my-restservice.jar
ENV GABRIEL_HOME /usr/verticles
EXPOSE 8095
COPY target/$GABRIEL_FILE $GABRIEL_HOME/
WORKDIR $GABRIEL_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["exec java -jar $GABRIEL_FILE"]
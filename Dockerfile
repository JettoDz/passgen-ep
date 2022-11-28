FROM ubuntu

WORKDIR /opt/

COPY target/passgen.war .

RUN apt-get update && apt-get install -yqq openjdk-17-jdk && apt-get autoremove 

EXPOSE 8080

CMD ["touch", "/var/log/passgen.log"]
CMD ["touch", "/var/log/err.passgen.log"]

CMD ["java", "-jar", "/opt/passgen.war"]
